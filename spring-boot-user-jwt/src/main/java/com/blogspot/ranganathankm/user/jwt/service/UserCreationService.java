package com.blogspot.ranganathankm.user.jwt.service;

import com.blogspot.ranganathankm.user.jwt.model.AppRole;
import com.blogspot.ranganathankm.user.jwt.model.AppUser;
import com.blogspot.ranganathankm.user.jwt.model.UserPassword;
import com.blogspot.ranganathankm.user.jwt.model.UserRole;
import com.blogspot.ranganathankm.user.jwt.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author ranga
 * 
 */
@Service
public class UserCreationService
{
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleMasterRepository roleMasterRepository;
    @Autowired
    private UserPasswordRepository userPasswordRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;    
    
    @Transactional
    public AppUser addUser(String loginName, String password, AppRole role)
            throws DataAccessException
    {
        AppUser appUser = new AppUser();
        appUser.setLoginName(loginName);
        return addUser(appUser, password, role);
    }
    
    @Transactional
    public AppUser addUser(String loginName, String password, String firstName, String lastName, String email, String mobile, AppRole role)
            throws DataAccessException
    {
        AppUser appUser = new AppUser();
        appUser.setLoginName(email);
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
        appUser.setEmail(email);
        appUser.setMobile(mobile);
        return addUser(appUser, password, role);
    }

    @Transactional
    public AppUser addUser(AppUser appUser, String password, AppRole role) {
        appUser.setActive(true);
        appUserRepository.save(appUser);
        
        UserRole userRole = new UserRole();
        userRole.setAppUser(appUser);
        userRole.setRole(roleMasterRepository.findByName(role));
        userRoleRepository.save(userRole);
        
        UserPassword userPassword = new UserPassword();
        userPassword.setAppUser(appUser);
        userPassword.setPasswordHash(passwordEncoder.encode(password));
        userPasswordRepository.save(userPassword);
        
        return appUser;
    }    
   
}
