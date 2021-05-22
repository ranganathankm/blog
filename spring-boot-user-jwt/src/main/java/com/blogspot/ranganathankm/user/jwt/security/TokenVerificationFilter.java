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
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author ranga
 */
@Slf4j
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

        UsernamePasswordAuthenticationToken authentication = null;
    
        String token = request.getHeader(SecurityConstants.HEADER_NAME);
        
        if (token != null && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
            
            Claims user = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(keyHash.getBytes()))
                    .parseClaimsJws(token)
                    .getBody();

            if (user != null) {
                String uname = user.getSubject();
                log.info("token verified for {}", uname);
                String role = user.get(SecurityConstants.ROLE).toString();
                List<UserDetailsImpl.RoleGrantedAuthority> asList = Arrays.asList(new UserDetailsImpl.RoleGrantedAuthority(role));
                authentication = new UsernamePasswordAuthenticationToken(user, null, asList);
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        chain.doFilter(request, response);
    }
    
}
