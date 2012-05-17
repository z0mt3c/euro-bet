package eu.zomtec.em2012.web;

import java.util.HashMap;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import eu.zomtec.em2012.domain.Bet;
import eu.zomtec.em2012.domain.BetUser;
import eu.zomtec.em2012.domain.Game;
import eu.zomtec.em2012.domain.GameGroup;
import eu.zomtec.em2012.manager.BetManager;

/**
 * Should contain the start page later - now just for testing.
 */
@RequestMapping("/start/**")
@Controller
public class StartController {
	
	private static final long DUMMY_USER_ID = 1L;
	@Autowired
	private BetManager betManager;

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping("index")
    public ModelAndView index(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    	return groups(modelMap, request, response);
    }
    
    @RequestMapping("groups")
    public ModelAndView groups(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    	final List<GameGroup> gameGroups = GameGroup.findAllGameGroupsBySortOrder();
    	modelMap.put("gameGroups", gameGroups);
        return new ModelAndView("start/group-list", modelMap);
    }
    
    @RequestMapping("games/{groupId}")
    public ModelAndView games(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, @PathVariable Long groupId) {
    	final GameGroup gameGroup = GameGroup.findGameGroup(groupId);
    	modelMap.put("gameGroup", gameGroup);
    	
    	final TypedQuery<Game> gamesQuery = Game.findGamesByGameGroupOrderByStart(gameGroup);
    	final List<Game> games = gamesQuery.getResultList();
    	modelMap.put("games", games);
    	
    	final HashMap<Long, Bet> bets = betManager.getBetsForGames(DUMMY_USER_ID, games);
    	modelMap.put("bets", bets);
    	
    	return new ModelAndView("start/game-list", modelMap);
    }
    
    @RequestMapping("game/{gameId}")
    public ModelAndView game(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, @PathVariable Long gameId) {
    	final Game game = Game.findGame(gameId);
    	modelMap.put("game", game);
    	
    	final TypedQuery<Bet> betsQery = Bet.findBetByBetGameOrderByScoreType(game);
    	final List<Bet> bets = betsQery.getResultList();
    	modelMap.put("bets", bets);
    	
    	return new ModelAndView("start/game", modelMap);
    }
    
    @RequestMapping("user/{userId}")
    public ModelAndView user(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, @PathVariable Long userId) {
    	final BetUser betUser = BetUser.findBetUser(userId);
    	modelMap.put("betUser", betUser);
    	modelMap.put("pageTitle", "User: "+betUser.getUsername());
    	
    	final TypedQuery<Bet> betsQery = Bet.findBetByUserOrderByKickOff(betUser);
    	final List<Bet> bets = betsQery.getResultList();
    	modelMap.put("bets", bets);
    	
    	return new ModelAndView("start/user", modelMap);
    }
    
    
    @RequestMapping(value="bet", method=RequestMethod.POST)
    public @ResponseBody String bet(@RequestParam long gameId, @RequestParam Integer home, @RequestParam Integer away) {
    	final Game game = Game.findGame(gameId);
    	
    	if (!game.isBetOpen()) {
    		return Boolean.FALSE.toString();
    	} else {
    		return Boolean.valueOf(betManager.placeBet(DUMMY_USER_ID, gameId, home, away)).toString();
    	}
    }
}
