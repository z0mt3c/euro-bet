// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.zomtec.em2012.domain;

import eu.zomtec.em2012.domain.Game;
import eu.zomtec.em2012.domain.GameDataOnDemand;
import eu.zomtec.em2012.domain.GameIntegrationTest;
import java.util.List;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect GameIntegrationTest_Roo_IntegrationTest {
    
    declare @type: GameIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: GameIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: GameIntegrationTest: @Transactional;
    
    @Autowired
    private GameDataOnDemand GameIntegrationTest.dod;
    
    @Test
    public void GameIntegrationTest.testCountGames() {
        Assert.assertNotNull("Data on demand for 'Game' failed to initialize correctly", dod.getRandomGame());
        long count = Game.countGames();
        Assert.assertTrue("Counter for 'Game' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void GameIntegrationTest.testFindGame() {
        Game obj = dod.getRandomGame();
        Assert.assertNotNull("Data on demand for 'Game' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Game' failed to provide an identifier", id);
        obj = Game.findGame(id);
        Assert.assertNotNull("Find method for 'Game' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Game' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void GameIntegrationTest.testFindAllGames() {
        Assert.assertNotNull("Data on demand for 'Game' failed to initialize correctly", dod.getRandomGame());
        long count = Game.countGames();
        Assert.assertTrue("Too expensive to perform a find all test for 'Game', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Game> result = Game.findAllGames();
        Assert.assertNotNull("Find all method for 'Game' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Game' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void GameIntegrationTest.testFindGameEntries() {
        Assert.assertNotNull("Data on demand for 'Game' failed to initialize correctly", dod.getRandomGame());
        long count = Game.countGames();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Game> result = Game.findGameEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Game' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Game' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void GameIntegrationTest.testFlush() {
        Game obj = dod.getRandomGame();
        Assert.assertNotNull("Data on demand for 'Game' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Game' failed to provide an identifier", id);
        obj = Game.findGame(id);
        Assert.assertNotNull("Find method for 'Game' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyGame(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Game' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void GameIntegrationTest.testMergeUpdate() {
        Game obj = dod.getRandomGame();
        Assert.assertNotNull("Data on demand for 'Game' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Game' failed to provide an identifier", id);
        obj = Game.findGame(id);
        boolean modified =  dod.modifyGame(obj);
        Integer currentVersion = obj.getVersion();
        Game merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Game' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void GameIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'Game' failed to initialize correctly", dod.getRandomGame());
        Game obj = dod.getNewTransientGame(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Game' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Game' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'Game' identifier to no longer be null", obj.getId());
    }
    
}
