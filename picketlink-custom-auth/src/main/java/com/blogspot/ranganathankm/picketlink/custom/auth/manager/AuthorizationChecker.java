package com.blogspot.ranganathankm.picketlink.custom.auth.manager;

import java.io.Serializable;
import org.picketlink.Identity;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.picketlink.idm.model.basic.*;
import com.blogspot.ranganathankm.picketlink.custom.auth.model.AppRole;
import com.blogspot.ranganathankm.picketlink.custom.auth.model.UserRole;

/**
 * @author Ranga
 *
 */
@Named (value = "authChecker")
public class AuthorizationChecker implements Serializable
{
    
    @Inject
    private Identity identity;
    
    @PersistenceContext
    private EntityManager em;    
    
    public boolean isLoggedIn() {
        return identity.isLoggedIn();
    }
    
    public String getUserLoginName() {
        return ((User)identity.getAccount()).getLoginName();
    }
    
    public boolean isAdmin() {
        return hasApplicationRole(AppRole.ADMIN.toString());
    }

    public boolean isUser() {
        return hasApplicationRole(AppRole.USER.toString());
    }
    
    public boolean hasApplicationRole(String roleName) {
        TypedQuery<UserRole> urQry = em.createNamedQuery("UserRole.findByUserId", UserRole.class);
        //remember that PL user object has a String id!
        Integer id = Integer.valueOf(((User)(identity.getAccount())).getId());
        urQry.setParameter("id", id);
        
        for(UserRole ur : urQry.getResultList()) {
            if(roleName.equalsIgnoreCase(ur.getRole().getName().toString())) {
                return true;
            }
        }
        return false;
    }
}