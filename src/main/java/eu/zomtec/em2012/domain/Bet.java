package eu.zomtec.em2012.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NoResultException;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findBetsByGame" })
public class Bet {

    @NotNull
    @ManyToOne
    private BetUser betUser;

    @NotNull
    @ManyToOne
    private Game game;

    private Integer scoreHome;

    private Integer scoreAway;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Date lastBetChange;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Date lastBetCalculation;

    @NotNull
    @Enumerated
    private BetStatus betStatus;

    @Enumerated
    private BetScoreType scoreType;

    private Integer score;
    
    
    public static TypedQuery<Bet> findBetByBetUserAndGame(BetUser betUser, Game game) {
        if (game == null) throw new IllegalArgumentException("The game argument is required");
        if (betUser == null) throw new IllegalArgumentException("The betUser argument is required");
        EntityManager em = Bet.entityManager();
        TypedQuery<Bet> q = em.createQuery("SELECT o FROM Bet AS o WHERE o.game = :game AND o.betUser = :betUser", Bet.class);
        q.setParameter("game", game);
        q.setParameter("betUser", betUser);
        return q;
    }
    
    public static TypedQuery<Bet> findBetByBetUserAndGame(BetUser betUser, List<Game> games) {
    	if (games == null || games.isEmpty()) throw new IllegalArgumentException("The games argument is required");
    	if (betUser == null) throw new IllegalArgumentException("The betUser argument is required");
    	EntityManager em = Bet.entityManager();
    	TypedQuery<Bet> q = em.createQuery("SELECT o FROM Bet AS o WHERE o.betUser = :betUser AND o.game IN :games", Bet.class);
    	q.setParameter("games", games);
    	q.setParameter("betUser", betUser);
    	return q;
    }
}
