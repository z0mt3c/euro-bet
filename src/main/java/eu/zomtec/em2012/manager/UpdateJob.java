package eu.zomtec.em2012.manager;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import eu.zomtec.em2012.domain.Bet;
import eu.zomtec.em2012.domain.Game;
import eu.zomtec.em2012.domain.GameStatus;
import eu.zomtec.em2012.score.ScoreCalculator;
import eu.zomtec.em2012.updater.League;
import eu.zomtec.em2012.updater.LeagueService;
import eu.zomtec.em2012.updater.Match;
import eu.zomtec.em2012.updater.MatchStatus;

@Component
public class UpdateJob {
	private static final Logger LOG = Logger.getLogger(UpdateJob.class);
	
	@Autowired
	private LeagueService leagueService;
	
	@Autowired
	private ScoreCalculator scoreCalculator;
	
	@Scheduled(fixedRate=15000)
	@Async
	public void process() throws ClientProtocolException, IOException, JSONException, ParseException {
		final List<Game> games = findGames();
		
		if (games.isEmpty()) {
			LOG.info("No games need to be updated!");
			return;
		}
		
		final League league = leagueService.loadLeague();
		LOG.info(games.size()+" games found - loaded new scores: "+league);
		
		for (Game game : games) {
			boolean recalculateScores = false;

			if (!GameStatus.RECALCULATE.equals(game.getGameStatus())) {
				recalculateScores |= updateScore(league, game);
				game.merge();
			} else {
				recalculateScores = true;
			}
			
			if (recalculateScores) {
				LOG.info("Recalculating scores...");
				final TypedQuery<Bet> betsQuery = Bet.findBetsByGame(game);
				final List<Bet> bets = betsQuery.getResultList();
				scoreCalculator.reviewBets(game, bets);
				saveBets(bets);
			}
		}
	}
	
	private void saveBets(List<Bet> bets) {
		for (Bet bet : bets) {
			bet.merge();
		}
	}

	private boolean updateScore(League league, Game game) {
		final Match match = league.getMatches().get(game.getExternalGameId());
		
		validateMatchVsGame(match, game);
		
		boolean change = false;
		change |= !match.getScoreHome().equals(game.getScoreHome());
		change |= !match.getScoreAway().equals(game.getScoreAway());
		final GameStatus newGameStatus = getGameStatusForMatchStatus(match.getStatus());
		change |= !newGameStatus.equals(game.getGameStatus());
		
		if (change) {
			game.setScoreAway(match.getScoreAway());
			game.setScoreHome(match.getScoreHome());
			game.setGameStatus(newGameStatus);
			game.setLastScoreUpdate(new Date());
		}
		
		return change;
	}
	
	private void validateMatchVsGame(Match match, Game game) {
		// throw exception when ids dont match!
	}

	private GameStatus getGameStatusForMatchStatus(MatchStatus matchStatus) {
		switch (matchStatus) {
		case ACTIVE:
			return GameStatus.RUNNING;
		case FINISHED:
			return GameStatus.FINISHED;
		default:
		case SCHEDULED:
			return GameStatus.UPCOMMING;
		}
	}

	private List<Game> findGames() {
		final GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(GregorianCalendar.MINUTE, Game.CLOSE_BET_BEFORE_GAME_IN_MINUTES);
		final TypedQuery<Game> gamesQuery = Game.findGamesByKickOffLessThanEqualsAndNotFinished(calendar.getTime());
		final List<Game> games = gamesQuery.getResultList();
		return games;
	}
}
