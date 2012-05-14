package eu.zomtec.em2012.domain;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
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
@RooJpaActiveRecord(finders = { "findGamesByGameGroup" })
public class Game {

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Date kickOff;

    @NotNull
    @ManyToOne
    private Team teamHome;

    @NotNull
    @ManyToOne
    private Team teamAway;

    private Integer scoreHome;

    private Integer scoreAway;

    @NotNull
    @Enumerated
    private GameStatus gameStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Date lastScoreUpdate;

    @NotNull
    @ManyToOne
    private GameGroup gameGroup;
    
    public static TypedQuery<Game> findGamesByGameGroupOrderByStart(GameGroup gameGroup) {
        if (gameGroup == null) throw new IllegalArgumentException("The gameGroup argument is required");
        EntityManager em = entityManager();
        TypedQuery<Game> q = em.createQuery("SELECT o FROM Game AS o WHERE o.gameGroup = :gameGroup ORDER BY o.kickOff ASC, o.gameStatus ASC", Game.class);
        q.setParameter("gameGroup", gameGroup);
        return q;
    }
}
