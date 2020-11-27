package com.blogspot.ranganathankm.user.jwt.security;

import com.blogspot.ranganathankm.user.jwt.model.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ranga
 */
public class TokenVerificationFilter extends BasicAuthenticationFilter {

    private final String keyHash;

    public TokenVerificationFilter(String key, AuthenticationManager authManager) {
        super(authManager);
        this.keyHash = key;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        UsernamePasswordAuthenticationToken authentication = authenticate(request);
        if(null != authentication) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
    
    private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_NAME);
        if (token != null) {
            Claims user = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(keyHash.getBytes()))
                    .parseClaimsJws(token)
                    .getBody();

            if (user != null) {
                String uname = user.getSubject();
                String role = user.get(SecurityConstants.ROLE).toString();
                System.out.println("user:"+ uname + "' with role:" + role);
                List<UserDetailsImpl.RoleGrantedAuthority> asList = Arrays.asList(new UserDetailsImpl.RoleGrantedAuthority(role));
                return new UsernamePasswordAuthenticationToken(user, null, asList);
            }
        }
        return null;
    }
}
