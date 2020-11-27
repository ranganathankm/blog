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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author ranga
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final String keyHash;
    private final long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(30);
    
    private final ObjectMapper obj = new ObjectMapper();
    
    public AuthenticationFilter(String key, AuthenticationManager authenticationManager) {
        this.keyHash = key;
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        System.out.println("exp-time:" + EXPIRATION_TIME);
        
        
        Date exp = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        System.out.println("exp-date:" + exp);
        System.out.println("exp-time-mills:" + exp.getTime());
        
        Key key = Keys.hmacShaKeyFor(this.keyHash.getBytes());
        final String authName = auth.getName();
        System.out.println("auth name:" + authName);
        Claims claims = Jwts.claims().setSubject(authName);
        
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        if(!authorities.isEmpty()) {
            String role = authorities.iterator().next().getAuthority();
            System.out.println("role-check:" + role);
            claims.put(SecurityConstants.ROLE, role);
        }
        System.out.println("user:" + authName);
        String token = Jwts.builder().setClaims(claims).signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(exp).compact();
        res.addHeader(SecurityConstants.TOKEN, token);
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

}
