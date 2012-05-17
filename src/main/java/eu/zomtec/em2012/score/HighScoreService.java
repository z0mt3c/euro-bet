package eu.zomtec.em2012.score;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class HighScoreService {
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
	    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	private static final String SQL_QUERY_HIGHSCORE_TEMP = "SELECT bet_user.username AS username, SUM(bet.score) AS totalScore, bet_user.id AS userId FROM bet INNER JOIN bet_user ON bet.bet_user = bet_user.id WHERE bet.bet_status IN (2,3) GROUP BY bet.bet_user ORDER BY totalScore DESC";
	private static final String SQL_QUERY_HIGHSCORE_FINAL = "SELECT bet_user.username AS username, SUM(bet.score) AS totalScore, bet_user.id AS userId FROM bet INNER JOIN bet_user ON bet.bet_user = bet_user.id WHERE bet.bet_status IN (3) GROUP BY bet.bet_user ORDER BY totalScore DESC";
	
	public List<HighScore> getHighScoreTemp(int limit) {
		final List<HighScore> list = namedParameterJdbcTemplate.query(SQL_QUERY_HIGHSCORE_TEMP+" LIMIT "+limit, new HashMap<String, String>(0), new BeanPropertyRowMapper<HighScore>(HighScore.class));
		return list;
	}
	
	public List<HighScore> getHighScoreFinal(int limit) {
		final List<HighScore> list = namedParameterJdbcTemplate.query(SQL_QUERY_HIGHSCORE_FINAL+" LIMIT "+limit, new HashMap<String, String>(0), new BeanPropertyRowMapper<HighScore>(HighScore.class));
		return list;
	}
}
