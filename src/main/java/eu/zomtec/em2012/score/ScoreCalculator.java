package eu.zomtec.em2012.score;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import eu.zomtec.em2012.domain.Bet;
import eu.zomtec.em2012.domain.BetScoreType;
import eu.zomtec.em2012.domain.Game;
import eu.zomtec.em2012.domain.GameStatus;

@Component
public class ScoreCalculator {
	public void reviewBets(Game game, List<Bet> bets) {
		for (Bet bet : bets) {
			reviewBet(game, bet);
		}
	}

	protected void reviewBet(Game game, Bet bet) {
		final GameStatus gameStatus = game.getGameStatus();

		if (GameStatus.UPCOMMING.equals(gameStatus)) {
			bet.setBetStatus(gameStatus.getBetStatus());
		} else {
			final BetScoreType betScoreType = detectBetScoreType(game, bet);
			bet.setScoreType(betScoreType);
			bet.setScore(betScoreType.getPoints());
			bet.setBetStatus(gameStatus.getBetStatus());
			bet.setLastBetCalculation(new Date());
		}
	}
	
	public BetScoreType detectBetScoreType(Game game, Bet bet) {
		if (game.getScoreHome().equals(bet.getScoreHome()) && game.getScoreAway().equals(bet.getScoreAway())) {
			return BetScoreType.SCORE_MATCH;
		} else if ((game.getScoreHome() - game.getScoreAway()) == (bet.getScoreHome() - bet.getScoreAway())) {
			return BetScoreType.SCORE_DIFFERENCE;
		} else if (!game.getScoreHome().equals(game.getScoreAway()) && (game.getScoreHome() > game.getScoreAway() == bet.getScoreHome() > bet.getScoreAway())) {
			return BetScoreType.SCORE_WINNER;
		} else {
			return BetScoreType.NOTHING;
		}
	}
}
