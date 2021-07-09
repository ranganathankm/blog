package com.blogspot.ranganathankm.spring.custom.auth.service;

import com.blogspot.ranganathankm.spring.custom.auth.model.AppUser;
import com.blogspot.ranganathankm.spring.custom.auth.repo.UserRoleRepository;
import com.blogspot.ranganathankm.spring.custom.auth.repo.RoleMasterRepository;
import com.blogspot.ranganathankm.spring.custom.auth.repo.UserPasswordRepository;
import com.blogspot.ranganathankm.spring.custom.auth.repo.AppUserRepository;
import com.blogspot.ranganathankm.spring.custom.auth.model.AppRole;
import com.blogspot.ranganathankm.spring.custom.auth.model.UserPassword;
import com.blogspot.ranganathankm.spring.custom.auth.model.UserRole;
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
    public AppUser addUser(String loginName, String password, String firstName, String lastName, String email, String mobile, AppRole role)
            throws DataAccessException
    {
        AppUser appUser = new AppUser();
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
        appUser.setEmail(email);
        appUser.setLoginName(email);
        appUser.setMobile(mobile);
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
