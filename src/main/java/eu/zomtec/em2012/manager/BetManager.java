package eu.zomtec.em2012.manager;

import java.util.Date;

import org.springframework.stereotype.Component;

import eu.zomtec.em2012.domain.Bet;
import eu.zomtec.em2012.domain.BetUser;
import eu.zomtec.em2012.domain.Game;

@Component
public class BetManager {
	public boolean placeBet(Long userId, Long gameId, Integer scoreHome, Integer scoreAway) {
		final BetUser user = BetUser.findBetUser(userId);
		final Game game = Game.findGame(userId);
		
		Bet bet = Bet.findBetByBetUserAndGame(user, game);
		
		if (bet != null) {
			bet.setScoreHome(scoreHome);
			bet.setScoreAway(scoreAway);
			bet.setLastBetChange(new Date());
			bet.merge();
		} else {
			bet = new Bet();
			bet.setBetUser(user);
			bet.setGame(game);
			bet.setScoreHome(scoreHome);
			bet.setScoreAway(scoreAway);
			bet.setLastBetChange(new Date());
			bet.persist();
		}
		
		return true;
	}
}
