package com.blogspot.ranganathankm.sb.ws.user.without.stomp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author ranga
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig
        extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password("pass1").roles("USER")
                .and()
                .withUser("user2").password("pass2").roles("USER")
                ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
            .authorizeRequests()
            .antMatchers("/index.html", "/app.html", "/landing.html", "/home.html", "/login.html", "/loggedout.html"
                    ).permitAll()
	    .antMatchers("/fonts/**").permitAll()
	    .antMatchers("/img/**").permitAll()
	    .antMatchers("/scripts/**").permitAll()
            .antMatchers("/user/**").authenticated()
            .and().formLogin().loginPage("/login.html");
    }
}
