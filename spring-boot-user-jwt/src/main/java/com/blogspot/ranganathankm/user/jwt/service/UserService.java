package com.blogspot.ranganathankm.user.jwt.service;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.blogspot.ranganathankm.user.jwt.model.AppRole;
import com.blogspot.ranganathankm.user.jwt.model.AppUser;
import com.blogspot.ranganathankm.user.jwt.model.UserDetailsImpl;
import com.blogspot.ranganathankm.user.jwt.model.UserPassword;
import com.blogspot.ranganathankm.user.jwt.model.UserRole;
import com.blogspot.ranganathankm.user.jwt.repo.AppUserRepository;
import com.blogspot.ranganathankm.user.jwt.repo.UserPasswordRepository;
import com.blogspot.ranganathankm.user.jwt.repo.UserRoleRepository;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author ranga
 * An utility service to get the user details
 */
@Service
public class UserService implements UserDetailsService
{
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private UserPasswordRepository userPasswordRepository;

    @Autowired
    private UserRoleRepository urr;    
    
    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findFirstByLoginName(loginName).orElseThrow(() -> 
            new UsernameNotFoundException(
                    String.format("User: %s, not found", loginName))
        );

        UserPassword up = userPasswordRepository.findByAppUser(appUser);
        List<UserRole> roleList = urr.findByAppUser(appUser);

        //for now, only one role per user
        return new UserDetailsImpl(appUser, up.getPasswordHash(), roleList.get(0).getRole().getName());
    }

    public Optional<AppUser> getAppUser(Integer userId)
    {
        return appUserRepository.findById(userId);
    }

}
