package com.blogspot.ranganathankm.picketlink.custom.auth.manager;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.picketlink.Identity;
import org.picketlink.config.http.PathConfiguration;
import org.picketlink.http.authorization.PathAuthorizer;
import com.blogspot.ranganathankm.picketlink.custom.auth.model.AppRole;

/**
 *
 * @author ranga
 */
public class CustomPathAuthorizer implements PathAuthorizer
{
    @Inject
    private AuthorizationChecker authChecker;

    private final Map<String,String[]> pathAndRoles = new HashMap<>();

    public CustomPathAuthorizer()
    {
        pathAndRoles.put("/user/", new String[] {AppRole.USER.toString()});
        pathAndRoles.put("/admin/", new String[] {AppRole.ADMIN.toString()});
        pathAndRoles.put("/common/", new String[] {AppRole.USER.toString(), AppRole.ADMIN.toString()});
    }
    
    @Override
    public boolean authorize(PathConfiguration pc, HttpServletRequest hsr, HttpServletResponse hsr1)
    {
        String pathInfo = hsr.getPathInfo();
        System.out.println(pathInfo);
        for (Map.Entry<String, String[]> entry : pathAndRoles.entrySet()) {
            String path = entry.getKey();
            if(pathInfo.startsWith(path)) {
                for (String role : entry.getValue()) {
                    if(authChecker.hasApplicationRole(role)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
