package eu.zomtec.em2012.updater;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LeagueParser {
	private static final String MATCHES2 = "matches";
	private static final String TIMESTAMP = "timestamp";
	private static final String IS_TOURNAMENT = "is_tournament";
	private static final String LEAGUE_ID = "leagueID";
	private static final String TOURNAMENT_GROUP = "tournament_group";
	private static final String STATUS = "status";
	private static final String PHASE = "phase";
	private static final String START_TIME = "startTime";
	private static final String TEAM2_SCORE = "team2Score";
	private static final String TEAM1_SCORE = "team1Score";
	private static final String TEAM2_ID = "team2Id";
	private static final String TEAM1_ID = "team1Id";
	private static final String MATCH_ID = "matchID";
	
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	static {
		SDF.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
	
	public League parse(String string) throws JSONException, ParseException {
		JSONObject leagueObj = new JSONObject(string);
		
		final League league = new League();
		league.setLeagueId(leagueObj.getLong(LEAGUE_ID));
		league.setTournament(leagueObj.getBoolean(IS_TOURNAMENT));
		league.setTimestamp(SDF.parse(leagueObj.getString(TIMESTAMP)));
		
		final JSONArray matchesArray = leagueObj.getJSONArray(MATCHES2);
		final int length = matchesArray.length();
		final HashMap<Long, Match> matches = new HashMap<Long, Match>(length);
		
		for (int i = 0; i < length; i++) {
			final JSONObject jsonObject = matchesArray.getJSONObject(i);
			final Match match = parseMatch(jsonObject);
			matches.put(match.getMatchId(), match);
		}
		
		league.setMatches(matches);
		return league;
	}

	private Match parseMatch(JSONObject jsonObject) throws JSONException, ParseException {
		final Match match = new Match();
		
		if (!jsonObject.isNull(TOURNAMENT_GROUP)) {
			match.setTournamentGroup(jsonObject.getString(TOURNAMENT_GROUP));
		}
		
		match.setMatchId(jsonObject.getLong(MATCH_ID));
		
		match.setTeamIdHome(jsonObject.getLong(TEAM1_ID));
		match.setTeamIdAway(jsonObject.getLong(TEAM2_ID));
		match.setScoreHome(jsonObject.getInt(TEAM1_SCORE));
		match.setScoreAway(jsonObject.getInt(TEAM2_SCORE));
		
		match.setStartTime(SDF.parse(jsonObject.getString(START_TIME)));
		
		if (!jsonObject.isNull(PHASE)) {
			match.setPhase(jsonObject.getInt(PHASE));
		}
		
		match.setStatus(MatchStatus.valueOf(jsonObject.getString(STATUS).toUpperCase()));
		return match;
	}
}
