package com.blogspot.javanbswing.spring.custom.auth.service;

import com.blogspot.javanbswing.spring.custom.auth.model.AppRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserService userService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic()
        .and()
        .authorizeRequests()
            .antMatchers("/index.html", "/app.html", "/home.html", "/login.html").permitAll()
	    .antMatchers("/fonts/**").permitAll()
	    .antMatchers("/img/**").permitAll()
	    .antMatchers("/scripts/**").permitAll()
	    .antMatchers("/styles/**").permitAll()
	    .antMatchers("/views/**").authenticated()
            .antMatchers("/userpages/**").hasAnyRole(AppRole.USER.toString(), AppRole.ADMIN.toString())
            .antMatchers("/adminpages/**").hasRole(AppRole.ADMIN.toString())
	.and()
	.formLogin()
            .loginPage("/app.html!#/login")
            .permitAll()
        .and()
	.logout()
	;
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