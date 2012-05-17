package eu.zomtec.em2012.web;

import java.security.Principal;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.zomtec.em2012.domain.BetUser;
import eu.zomtec.em2012.domain.News;
import eu.zomtec.em2012.score.HighScore;
import eu.zomtec.em2012.score.HighScoreService;

@RequestMapping("/public/**")
@Controller
public class PublicController {

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
    
    @RequestMapping(value="/register")
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
}
