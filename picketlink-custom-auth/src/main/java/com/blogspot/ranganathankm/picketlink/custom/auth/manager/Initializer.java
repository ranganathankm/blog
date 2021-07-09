package com.blogspot.ranganathankm.picketlink.custom.auth.manager;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.picketlink.idm.credential.encoder.SHAPasswordEncoder;
import org.picketlink.idm.credential.encoder.PasswordEncoder;
import com.blogspot.ranganathankm.picketlink.custom.auth.model.AppRole;
import com.blogspot.ranganathankm.picketlink.custom.auth.model.AppUser;
import com.blogspot.ranganathankm.picketlink.custom.auth.model.RoleMaster;
import com.blogspot.ranganathankm.picketlink.custom.auth.model.UserPassword;
import com.blogspot.ranganathankm.picketlink.custom.auth.model.UserRole;

/**
 *
 * @author ranga
 */
@Singleton
@Startup
public class Initializer
{
    @PersistenceContext
    private EntityManager em;
    
    private final PasswordEncoder passwordEncoder = new SHAPasswordEncoder(512);
    
    @PostConstruct
    public void create()
    {
        //create roles if it doesn't exist
        for(AppRole appRole: AppRole.values()){
            if(null == getRoleMaster(appRole)) {
                RoleMaster rm = new RoleMaster();
                rm.setName(appRole);
                em.persist(rm);
            }
        }

        RoleMaster adminRm = getRoleMaster(AppRole.ADMIN);
        RoleMaster userRm = getRoleMaster(AppRole.USER);
        
        createUser("user1", "user one", "last", "user1@foo.com", "9111111111", userRm);
        createUser("admin1", "admin", "last", "admin1@foo.com", "9222222222", adminRm);
    }

    private RoleMaster getRoleMaster(AppRole appRole)
    {
        try {
            TypedQuery<RoleMaster> rmQry = em.createNamedQuery("RoleMaster.findByName", RoleMaster.class);
            rmQry.setParameter("name", appRole);
            return rmQry.getSingleResult();
        }
        catch(NoResultException nre) {
            return null;
        }
    }
    
    private void createUser(String loginName, String firstName, String lastName, String email, String mobile, RoleMaster role)
    {
        AppUser appUser = new AppUser();
        appUser.setLoginName(loginName);
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
        appUser.setEmail(email);
        appUser.setMobile(mobile);
        appUser.setActive(true);
        em.persist(appUser);

        UserPassword up = new UserPassword();
        up.setAppUser(appUser);
        up.setPasswordHash(passwordEncoder.encode("password"));
        em.persist(up);
        
        UserRole ur = new UserRole();
        ur.setAppUser(appUser);
        ur.setRole(role);
        em.persist(ur);
    }
}
