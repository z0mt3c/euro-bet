package eu.zomtec.em2012.domain;

import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class TeamGroup {

    @NotNull
    private String name;

    public TeamGroup(String name) {
		super();
		this.name = name;
	}
	
	public TeamGroup() {
		super();
	}
}
