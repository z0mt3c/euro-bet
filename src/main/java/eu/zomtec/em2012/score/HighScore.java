package eu.zomtec.em2012.score;

public class HighScore {
	private String username;
	private Integer totalScore;
	private Long userId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "HighScore [userId=" + userId + ", username=" + username
				+ ", totalScore=" + totalScore + "]";
	}
}
