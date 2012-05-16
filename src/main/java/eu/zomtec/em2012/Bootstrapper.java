package eu.zomtec.em2012;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eu.zomtec.em2012.domain.Game;
import eu.zomtec.em2012.domain.GameGroup;
import eu.zomtec.em2012.domain.GameStatus;
import eu.zomtec.em2012.domain.Team;
import eu.zomtec.em2012.domain.TeamGroup;
import eu.zomtec.em2012.updater.League;
import eu.zomtec.em2012.updater.LeagueService;
import eu.zomtec.em2012.updater.Match;

@Component
public class Bootstrapper implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger LOG = Logger.getLogger(Bootstrapper.class);
	
	@Autowired
	private LeagueService leagueService;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			bootstrap();
		}
	}

	private void bootstrap() {
		if (TeamGroup.findAllTeamGroups().isEmpty()) {
			LOG.info("Bootstrapping started...");
			init();
			LOG.info("Bootstrapping finsied!");
		} else {
			LOG.info("Bootstrapping skipped!");
		}
	}

	private void init() {
		TeamGroup teamGroupA = new TeamGroup("A");
		TeamGroup teamGroupB = new TeamGroup("B");
		TeamGroup teamGroupC = new TeamGroup("C");
		TeamGroup teamGroupD = new TeamGroup("D");
		TeamGroup teamGroupTBD = new TeamGroup("TBD");
		
		teamGroupA.persist();
		teamGroupB.persist();
		teamGroupC.persist();
		teamGroupD.persist();
		teamGroupTBD.persist();
		
		Team teamNA = new Team("TBD",teamGroupTBD, -1L);
		Team polen = new Team("Polen",teamGroupA, 1317L);
		Team ukraine = new Team("Ukraine",teamGroupD, 1315L);
		Team deutschland = new Team("Deutschland",teamGroupB, 940L);
		Team italien = new Team("Italien",teamGroupC, 924L);
		Team niederlande = new Team("Niederlande",teamGroupB, 936L);
		Team spanien = new Team("Spanien",teamGroupC, 932L);
		Team england = new Team("England",teamGroupD, 946L);
		Team daenemark = new Team("D\u00e4nemark",teamGroupB, 931L);
		Team frankreich = new Team("Frankreich",teamGroupD, 928L);
		Team griechenland = new Team("Griechenland",teamGroupA, 1306L);
		Team russland = new Team("Russland",teamGroupA, 1335L);
		Team schweden = new Team("Schweden",teamGroupD, 1318L);
		Team irland = new Team("Irland",teamGroupC, 1327L);
		Team kroatien = new Team("Kroatien",teamGroupC, 951L);
		Team portugal = new Team("Portugal",teamGroupB, 1323L);
		Team tschechien = new Team("Tschechien",teamGroupA, 1333L);
		
		teamNA.persist();
		polen.persist();
		ukraine.persist();
		deutschland.persist();
		italien.persist();
		niederlande.persist();
		spanien.persist();
		england.persist();
		daenemark.persist();
		frankreich.persist();
		griechenland.persist();
		russland.persist();
		schweden.persist();
		irland.persist();
		kroatien.persist();
		portugal.persist();
		tschechien.persist();
		
		try {
			final League league = leagueService.loadLeague();
			final Map<Long, Match> matchesMap = league.getMatches();
			final Collection<Match> matches = matchesMap.values();
			
			// Create tournament groups
			final HashSet<String> tournamentGroupNames = new HashSet<String>();
			for (Match match : matches) {
				final String tournamentGroup = match.getTournamentGroup();
				tournamentGroupNames.add(tournamentGroup);
			}
			
			final HashMap<String, GameGroup> gameGroups = new HashMap<String, GameGroup>();
			for (String tournamentGroup : tournamentGroupNames) {
				GameGroup gameGroup = new GameGroup(tournamentGroup, 0);
				gameGroup.persist();
				gameGroups.put(tournamentGroup, gameGroup);
			}
			
			for (Match match : matches) {
				Game game = new Game();
				game.setGameGroup(gameGroups.get(match.getTournamentGroup()));
				game.setGameStatus(GameStatus.getByMatchStatus(match.getStatus()));
				game.setKickOff(match.getStartTime());
				game.setScoreAway(match.getScoreAway());
				game.setScoreHome(match.getScoreHome());
				game.setTeamHome(Team.findTeamsByExternalTeamIdEquals(match.getTeamIdHome()).getSingleResult());
				game.setTeamAway(Team.findTeamsByExternalTeamIdEquals(match.getTeamIdAway()).getSingleResult());
				game.setExternalGameId(match.getMatchId());
				game.persist();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
