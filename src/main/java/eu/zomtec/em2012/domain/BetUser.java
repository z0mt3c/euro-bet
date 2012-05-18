package eu.zomtec.em2012.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;

@RooJavaBean
@RooJpaActiveRecord(finders = { "findBetUsersByUsernameEquals" })
public class BetUser {

    @NotNull
    @Size(min = 3, max = 30)
    @Column(unique=true)
    private String username;

    @NotNull
    @Size(max = 100)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<BetUserRole> roles = new HashSet<BetUserRole>();

    @NotNull
    private Boolean enabled;
    
    @Override
    public String toString() {
		return username + "(" + getId() + ")";
    }
}
