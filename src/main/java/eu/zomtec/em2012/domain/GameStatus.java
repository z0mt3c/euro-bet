package eu.zomtec.em2012.domain;

import eu.zomtec.em2012.updater.MatchStatus;

public enum GameStatus {
	UPCOMMING, RUNNING, FINISHED, RECALCULATE;
	
	public static final GameStatus getByMatchStatus(MatchStatus matchStatus) {
		switch (matchStatus) {
		case ACTIVE:
			return RUNNING;
		case FINISHED:
			return FINISHED;
		case SCHEDULED:
			return UPCOMMING;
		default:
			return null;
		}
	}
}
