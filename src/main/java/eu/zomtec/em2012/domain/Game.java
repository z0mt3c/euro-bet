package eu.zomtec.em2012.domain;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findGamesByGameGroup", "findGamesByKickOffLessThanEquals" })
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"game_group","team_home","team_away"}))
public class Game {

    @Transient
    public static final int CLOSE_BET_BEFORE_GAME_IN_MINUTES = 10;

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

    @NotNull
    @Column(unique=true)
    private Long externalGameId;

    public static TypedQuery<eu.zomtec.em2012.domain.Game> findGamesByGameGroupOrderByStart(GameGroup gameGroup) {
        if (gameGroup == null) throw new IllegalArgumentException("The gameGroup argument is required");
        EntityManager em = entityManager();
        TypedQuery<Game> q = em.createQuery("SELECT o FROM Game AS o WHERE o.gameGroup = :gameGroup ORDER BY o.gameStatus DESC, o.kickOff ASC", Game.class);
        q.setParameter("gameGroup", gameGroup);
        return q;
    }

    @Transient
    public boolean isBetOpen() {
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(GregorianCalendar.MINUTE, CLOSE_BET_BEFORE_GAME_IN_MINUTES);
        return GameStatus.UPCOMMING.equals(gameStatus) && calendar.getTime().before(kickOff);
    }

    public static TypedQuery<eu.zomtec.em2012.domain.Game> findGamesByKickOffLessThanEqualsAndNotFinished(Date kickOff) {
        if (kickOff == null) throw new IllegalArgumentException("The kickOff argument is required");
        EntityManager em = Game.entityManager();
        TypedQuery<Game> q = em.createQuery("SELECT o FROM Game AS o WHERE o.kickOff <= :kickOff AND o.gameStatus != :gameStatus", Game.class);
        q.setParameter("kickOff", kickOff);
        q.setParameter("gameStatus", GameStatus.FINISHED);
        return q;
    }
    
    @Override
    public String toString() {
		return teamHome.toString() + " : " + teamAway.toString() + "(" + getId() + ")";
    }
}
