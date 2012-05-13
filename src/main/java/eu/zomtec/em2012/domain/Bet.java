package eu.zomtec.em2012.domain;

import java.util.Date;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
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
    @DateTimeFormat(style = "M-")
    private Date lastBetChange;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date lastBetCalculation;

    @NotNull
    @Enumerated
    private BetStatus betStatus;

    @Enumerated
    private BetScoreType scoreType;

    private Integer score;
}
