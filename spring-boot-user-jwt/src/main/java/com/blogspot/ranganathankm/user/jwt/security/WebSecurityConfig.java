package com.blogspot.ranganathankm.user.jwt.security;

import com.blogspot.ranganathankm.user.jwt.model.AppAuth;
import com.blogspot.ranganathankm.user.jwt.service.UserService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true,
    prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Value("${jwt.key.hash}")
    private String keyHash;

    @Value("${jwt.expiration.in.minutes}")
    private Integer expMinutes;
    
    @Value("${cors.allowed.origins}")
    private String corsAllowedOrigins;
        
    @Autowired
    private UserService userService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new AuthenticationFilter(keyHash, expMinutes, authenticationManager()))
                .addFilter(new TokenVerificationFilter(keyHash, authenticationManager()))
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/signup").permitAll()
                .antMatchers("/unsecured/*").permitAll()
                .antMatchers("/general/*").authenticated()
                .antMatchers("/normal/*").hasAuthority(AppAuth.USER)
                .antMatchers("/admin/*").hasAuthority(AppAuth.ADMIN)
                .anyRequest().denyAll()
                ;
        
        http.exceptionHandling().authenticationEntryPoint(
              (request, response, ex) -> {
                  response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                      ex.getMessage());
              });        
        
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
          = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }    
}