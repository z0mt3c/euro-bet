package eu.zomtec.em2012.updater;

import java.util.Date;
import java.util.Map;

public class League {
	private boolean tournament;
	private Long leagueId;
	private Date timestamp;
	private Map<Long, Match> matches;

	public boolean isTournament() {
		return tournament;
	}

	public void setTournament(boolean tournament) {
		this.tournament = tournament;
	}

	public Long getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Long leagueId) {
		this.leagueId = leagueId;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Map<Long, Match> getMatches() {
		return matches;
	}

	public void setMatches(Map<Long, Match> matches) {
		this.matches = matches;
	}
}
