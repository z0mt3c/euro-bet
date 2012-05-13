package eu.zomtec.em2012.calculator;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "/META-INF/spring/applicationContext.xml" })
public class BootstrapTest extends AbstractJUnit4SpringContextTests {
	
	@Test 
	public void testIntegrationOver() {
		Assert.assertTrue(true);
	}
}