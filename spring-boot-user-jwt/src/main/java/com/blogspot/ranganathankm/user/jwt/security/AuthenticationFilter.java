package com.blogspot.ranganathankm.user.jwt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.security.core.GrantedAuthority;

import com.blogspot.ranganathankm.user.jwt.model.UserLoginDto;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author ranga
 */
@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final String keyHash;
    
    private final ObjectMapper obj = new ObjectMapper();
    private final Integer expMinutes;
    
    public AuthenticationFilter(String key, Integer expMinutes, AuthenticationManager authenticationManager) {
        this.keyHash = key;
        this.expMinutes = expMinutes;
        log.info("Expiration time (in minutes): {}", expMinutes);
        super.setAuthenticationManager(authenticationManager);
        
        super.setFilterProcessesUrl("/authenticate"); 
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            UserLoginDto ulDto = obj.readValue(req.getInputStream(), UserLoginDto.class);

            final UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(ulDto.getUsername(),
                    ulDto.getPassword(), new ArrayList<>());
            return getAuthenticationManager().authenticate(authRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(expMinutes);
        
        Date exp = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        
        Key key = Keys.hmacShaKeyFor(this.keyHash.getBytes());
        final String authName = auth.getName();
        log.info("user successfully authenitcated: {}", authName);
        Claims claims = Jwts.claims().setSubject(authName);
        
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        if(!authorities.isEmpty()) {
            String role = authorities.iterator().next().getAuthority();
            claims.put(SecurityConstants.ROLE, role);
        }
        String token = Jwts.builder().setClaims(claims).signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(exp).compact();
        res.addHeader(SecurityConstants.TOKEN, token);
    }

}
