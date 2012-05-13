package eu.zomtec.em2012.domain;

public enum BetScoreType {
	SCORE_MATCH(3), SCORE_DIFFERENCE(2), SCORE_WINNER(1), NOTHING(0);
	
	final int points;

	private BetScoreType(int points) {
		this.points = points;
	} 
}
