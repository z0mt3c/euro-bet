package eu.zomtec.em2012.domain;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class BetUserRole {

    @NotNull
    @Column(unique=true)
    private String name;

	public BetUserRole(String name) {
		super();
		this.name = name;
	}
}
