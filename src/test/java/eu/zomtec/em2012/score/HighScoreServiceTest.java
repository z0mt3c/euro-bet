package eu.zomtec.em2012.score;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class HighScoreServiceTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	private HighScoreService highScoreService;
	
	@Test 
	public void testIntegration() {
		Assert.assertTrue(true);
		final List<HighScore> scores = highScoreService.getHighScoreTemp(5);
		Assert.assertNotNull(scores);
		final List<HighScore> scores2 = highScoreService.getHighScoreFinal(5);
		Assert.assertNotNull(scores2);
	}
}