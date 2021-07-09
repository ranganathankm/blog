package com.blogspot.ranganathankm.picketlink.custom.auth.manager;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.picketlink.Identity;
import org.picketlink.annotations.PicketLink;
import org.picketlink.authentication.BaseAuthenticator;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.credential.encoder.SHAPasswordEncoder;
import org.picketlink.idm.credential.encoder.PasswordEncoder;
import org.picketlink.idm.model.basic.User;
import com.blogspot.ranganathankm.picketlink.custom.auth.model.AppUser;
import com.blogspot.ranganathankm.picketlink.custom.auth.model.UserPassword;

/**
 *
 * @author ranga
 */
@PicketLink
public class CustomAuthenticator extends BaseAuthenticator
{
    @Inject
    private DefaultLoginCredentials credentials;

    @Inject
    private FacesContext facesContext;
    
    @Inject
    private Identity identity;
    
    @PersistenceContext
    private EntityManager em;
    
    private final PasswordEncoder passwordEncoder = new SHAPasswordEncoder(512);
    
    @Override
    public void authenticate()
    {
        boolean authenticated = false;
        String loginName = credentials.getUserId();
        TypedQuery<AppUser> usrQry = em.createNamedQuery("AppUser.findByLoginName", AppUser.class);
        usrQry.setParameter("loginName", loginName);
        AppUser appUser = null;
        try {
            appUser = usrQry.getSingleResult();
        }
        catch(NoResultException nre) {
            appUser = null;
        }
        if(null != appUser) {
            TypedQuery<UserPassword> pwdQry = em.createNamedQuery("UserPassword.findByUserId", UserPassword.class);
            pwdQry.setParameter("id", appUser.getId());
            UserPassword up = pwdQry.getSingleResult();
            authenticated = passwordEncoder.verify(credentials.getPassword(), up.getPasswordHash());
        }
        if(authenticated) {
            setStatus(AuthenticationStatus.SUCCESS);
            User user = new User(loginName);
            user.setId(String.valueOf(appUser.getId()));
            user.setFirstName(appUser.getFirstName());
            user.setLastName(appUser.getLastName());
            user.setEmail(appUser.getEmail());
            setAccount(user);
        } else {
            setStatus(AuthenticationStatus.FAILURE);
            facesContext.addMessage(null, new FacesMessage(
                    "Authentication Failure - The username or password you provided were invalid."));
        }        
    }
    
}
