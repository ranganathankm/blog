package com.blogspot.ranganathankm.picketlink.custom.auth.manager;

import org.picketlink.config.SecurityConfigurationBuilder;
import org.picketlink.event.SecurityConfigurationEvent;
import javax.enterprise.event.Observes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.picketlink.config.http.AuthorizationConfiguration;
import org.picketlink.config.http.PathConfiguration;
import org.picketlink.http.AuthenticationRequiredException;
import org.picketlink.http.authorization.PathAuthorizer;
import com.blogspot.ranganathankm.picketlink.custom.auth.model.AppRole;

/**
 *
 * @author ranga
 */
public class HttpSecurityConfiguration
{

    public void onInit(@Observes SecurityConfigurationEvent event)
    {
        SecurityConfigurationBuilder builder = event.getBuilder();
        builder.http()
                .forGroup("JSF Protected Pages").authenticateWith()
                    .form().loginPage("/faces/general/login.xhtml")
                    .errorPage("/faces/general/error.xhtml")
                    .restoreOriginalRequest()
                    .redirectTo("/faces/general/accessDenied.xhtml").whenForbidden()
                    .redirectTo("/faces/general/login.xhtml").whenException(AuthenticationRequiredException.class)
                    .forPath("/*.xhtml", "JSF Protected Pages")
                .authorizeWith().authorizer(CustomPathAuthorizer.class)
                .forPath("/faces/general/*").unprotected()
                .forPath("/logout").logout()
                ;
    }
}
