package eu.zomtec.em2012.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class News {

    private String title;

    @Size(max = 99999)
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date creation;
    
    public static List<News> findNewsEntries(int maxResults) {
        return entityManager().createQuery("SELECT o FROM News o ORDER BY o.id DESC", News.class).setMaxResults(maxResults).getResultList();
    }
}
