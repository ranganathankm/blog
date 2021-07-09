package com.blogspot.ranganathankm.picketlink.custom.auth.manager;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ranga
 */
public class Resources
{
    @Produces
    @RequestScoped
    public FacesContext produceFacesContext()
    {
        return FacesContext.getCurrentInstance();
    }

}
