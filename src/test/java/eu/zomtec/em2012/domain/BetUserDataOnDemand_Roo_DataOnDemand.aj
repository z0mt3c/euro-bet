// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.zomtec.em2012.domain;

import eu.zomtec.em2012.domain.BetUser;
import eu.zomtec.em2012.domain.BetUserDataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect BetUserDataOnDemand_Roo_DataOnDemand {
    
    declare @type: BetUserDataOnDemand: @Component;
    
    private Random BetUserDataOnDemand.rnd = new SecureRandom();
    
    private List<BetUser> BetUserDataOnDemand.data;
    
    public BetUser BetUserDataOnDemand.getNewTransientBetUser(int index) {
        BetUser obj = new BetUser();
        setEnabled(obj, index);
        setPassword(obj, index);
        setUsername(obj, index);
        return obj;
    }
    
    public void BetUserDataOnDemand.setEnabled(BetUser obj, int index) {
        Boolean enabled = Boolean.TRUE;
        obj.setEnabled(enabled);
    }
    
    public void BetUserDataOnDemand.setPassword(BetUser obj, int index) {
        String password = "password_" + index;
        if (password.length() > 100) {
            password = password.substring(0, 100);
        }
        obj.setPassword(password);
    }
    
    public void BetUserDataOnDemand.setUsername(BetUser obj, int index) {
        String username = "username_" + index;
        if (username.length() > 30) {
            username = new Random().nextInt(10) + username.substring(1, 30);
        }
        obj.setUsername(username);
    }
    
    public BetUser BetUserDataOnDemand.getSpecificBetUser(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        BetUser obj = data.get(index);
        Long id = obj.getId();
        return BetUser.findBetUser(id);
    }
    
    public BetUser BetUserDataOnDemand.getRandomBetUser() {
        init();
        BetUser obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return BetUser.findBetUser(id);
    }
    
    public boolean BetUserDataOnDemand.modifyBetUser(BetUser obj) {
        return false;
    }
    
    public void BetUserDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = BetUser.findBetUserEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'BetUser' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<BetUser>();
        for (int i = 0; i < 10; i++) {
            BetUser obj = getNewTransientBetUser(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
