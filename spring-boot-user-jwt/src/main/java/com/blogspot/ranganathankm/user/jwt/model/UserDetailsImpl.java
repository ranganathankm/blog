package com.blogspot.ranganathankm.user.jwt.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author ranga
 */
public class UserDetailsImpl implements UserDetails
{
    private static final String ROLE_ = "ROLE_";
    
    private final AppUser appUser;
    private final String passwordHash;
    private final AppRole appRole;

    public UserDetailsImpl(AppUser appUser, String passwordHash, AppRole appRole) {
        this.appUser = appUser;
        this.passwordHash = passwordHash;
        this.appRole = appRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<RoleGrantedAuthority> roleSet = new HashSet<>();
        roleSet.add(new RoleGrantedAuthority(ROLE_+ appRole.toString()));
        return roleSet;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return appUser.getLoginName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return appUser.isActive();
    }

    @Override
    public boolean isAccountNonLocked() {
        return appUser.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return appUser.isActive();
    }

    @Override
    public boolean isEnabled() {
        return appUser.isActive();
    }
    
    public static class RoleGrantedAuthority implements GrantedAuthority {

        private final String roleName;

        public RoleGrantedAuthority(String roleName) {
            this.roleName = roleName;
        }

        @Override
        public String getAuthority() {
            return roleName;
        }
        
    }
    
}