package eu.zomtec.em2012.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import eu.zomtec.em2012.domain.Bet;
import eu.zomtec.em2012.domain.BetStatus;
import eu.zomtec.em2012.domain.BetUser;
import eu.zomtec.em2012.domain.Game;

@Component
public class BetManager {
	public boolean placeBet(Long userId, Long gameId, Integer scoreHome, Integer scoreAway) {
		final BetUser user = BetUser.findBetUser(userId);
		final Game game = Game.findGame(gameId);
		
		TypedQuery<Bet> betQuery = Bet.findBetByBetUserAndGame(user, game);
		final List<Bet> resultList = betQuery.getResultList();
		
		if (!resultList.isEmpty()) {
			final Bet bet = resultList.get(0);
			bet.setScoreHome(scoreHome);
			bet.setScoreAway(scoreAway);
			bet.setLastBetChange(new Date());
			bet.setBetStatus(BetStatus.OPEN);
			bet.merge();
		} else {
			final Bet bet = new Bet();
			bet.setBetUser(user);
			bet.setGame(game);
			bet.setScoreHome(scoreHome);
			bet.setScoreAway(scoreAway);
			bet.setBetStatus(BetStatus.OPEN);
			bet.setLastBetChange(new Date());
			bet.persist();
		}
		
		return true;
	}

	public HashMap<Long,Bet> getBetsForGames(long userId, List<Game> games) {
		final BetUser user = BetUser.findBetUser(userId);
		final TypedQuery<Bet> bets = Bet.findBetByBetUserAndGame(user, games);
		final List<Bet> resultList = bets.getResultList();
		final HashMap<Long, Bet> betMap = new HashMap<Long, Bet>(resultList.size());
		
		for (Bet bet : resultList) {
			betMap.put(bet.getGame().getId(), bet);
		}
		
		return betMap;
	}
}
