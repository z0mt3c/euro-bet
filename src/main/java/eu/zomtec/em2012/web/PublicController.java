package eu.zomtec.em2012.web;

import java.security.Principal;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.zomtec.em2012.domain.BetUser;
import eu.zomtec.em2012.domain.News;
import eu.zomtec.em2012.score.HighScore;
import eu.zomtec.em2012.score.HighScoreService;

@RequestMapping("/public/**")
@Controller
public class PublicController {
	
	private static final String REGEX_USERNAME = "^[a-zA-Z0-9-_.]{3,20}$";
	
	@Autowired
	private HighScoreService highScoreService;
	
	@RequestMapping(value={"/","/news"})
	public String news(ModelMap modelMap, Principal principal) {
		final List<News> news = News.findNewsEntries(10);
		modelMap.put("news", news);
		
		final List<HighScore> highScores = highScoreService.getHighScoreTemp();
		modelMap.put("scores_temp", highScores);
		
		final BetUser user = getUser(principal);
		if (user != null) {
			modelMap.put("scores_temp_my", highScoreService.getHighScorePartForUser(user.getId(), 2, highScores));
		}
		
		return "public/news";
	}
	
	private BetUser getUser(Principal principal) {
		if (principal == null || StringUtils.isBlank(principal.getName())) {
			return null;
		}
		
		final TypedQuery<BetUser> userQuery = BetUser.findBetUsersByUsernameEquals(principal.getName());
    	final BetUser user = userQuery.getSingleResult();
		return user;
	}
	
    @RequestMapping(value="/scores")
    public String scores(ModelMap modelMap) {
    	return "public/scores";
    }
    
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String register(ModelMap modelMap) {
    	return "public/register";
    }
    
    @RequestMapping(value="/scores/temp")
    public String finalScores(ModelMap modelMap) {
    	final List<HighScore> finalScores = highScoreService.getHighScoreFinal(1000);
    	modelMap.put("scores_final", finalScores);
    	
        return "public/score-board";
    }
    
    @RequestMapping(value="/scores/final")
    public String tempScores(ModelMap modelMap) {
    	final List<HighScore> tempScores = highScoreService.getHighScoreTemp(1000);
    	modelMap.put("scores_temp", tempScores);
    	
        return "public/score-board";
    }
    
    @RequestMapping(value="/register", method = RequestMethod.POST)
    @ResponseBody
    public String registerPost(ModelMap modelMap, @RequestParam String username, @RequestParam String password, @RequestParam String password2, @RequestParam String email) {
    	username = StringUtils.trim(username);
    	
    	final StringBuilder sb = new StringBuilder();
    	
    	if (!Pattern.matches(REGEX_USERNAME, username)) {
    		sb.append("<li>Username is invalid (Minimum length: 3, Allowed characters: A-Z, 0-9, -, _, .)</li>");
    	} else if (BetUser.countBetUsers(username) > 0) {
    		sb.append("<li>Username already in use</li>");
    	} 
    	
    	if (StringUtils.isBlank(password) || password.length() < 3) {
    		sb.append("<li>Password must be at least 3 characters long</li>");
    	} else if (!password.equals(password2)) {
    		sb.append("<li>Passwords do not match</li>");
    	}
    	
    	if (StringUtils.isBlank(email) || !Pattern.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", email)) {
    		sb.append("<li>Email invalid</li>");
    	} else if (BetUser.countBetUsersByEmail(email) > 0) {
    		sb.append("<li>Email already in use</li>");
    	}
    	
    	final String errors = sb.toString();
    	
		if (StringUtils.isNotBlank(errors)) {
			return errors;
		} else {
			BetUser betUser = new BetUser();
			betUser.setUsername(username);
			betUser.setPassword(password);
			betUser.setEnabled(false);
			betUser.setEmail(email);
			betUser.setMoney(0);
			betUser.persist();
			
			return "success";
		}
    }
}