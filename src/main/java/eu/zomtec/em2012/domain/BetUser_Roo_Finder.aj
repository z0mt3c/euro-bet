// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.zomtec.em2012.domain;

import eu.zomtec.em2012.domain.BetUser;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect BetUser_Roo_Finder {
    
    public static TypedQuery<BetUser> BetUser.findBetUsersByUsernameEquals(String username) {
        if (username == null || username.length() == 0) throw new IllegalArgumentException("The username argument is required");
        EntityManager em = BetUser.entityManager();
        TypedQuery<BetUser> q = em.createQuery("SELECT o FROM BetUser AS o WHERE o.username = :username", BetUser.class);
        q.setParameter("username", username);
        return q;
    }
    
    public static TypedQuery<BetUser> BetUser.findBetUsersByUsernameLike(String username) {
        if (username == null || username.length() == 0) throw new IllegalArgumentException("The username argument is required");
        username = username.replace('*', '%');
        if (username.charAt(0) != '%') {
            username = "%" + username;
        }
        if (username.charAt(username.length() - 1) != '%') {
            username = username + "%";
        }
        EntityManager em = BetUser.entityManager();
        TypedQuery<BetUser> q = em.createQuery("SELECT o FROM BetUser AS o WHERE LOWER(o.username) LIKE LOWER(:username)", BetUser.class);
        q.setParameter("username", username);
        return q;
    }
    
}
