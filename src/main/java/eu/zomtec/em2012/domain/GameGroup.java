package eu.zomtec.em2012.domain;

import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findGameGroupsByNameEquals" })
public class GameGroup {

    @NotNull
    private String name;

    private Integer sortOrder;

    public GameGroup(String name, Integer sortOrder) {
        super();
        this.name = name;
        this.sortOrder = sortOrder;
    }
}
