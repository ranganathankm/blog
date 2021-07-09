package com.blogspot.ranganathankm.sp;

import com.blogspot.ranganathankm.sp.api.ServiceInterface;
import java.util.ServiceLoader;

/**
 * @author ranga
 *
 */

public class ClientTest 
{
    public static void main( String[] args )
    {
        ServiceLoader<ServiceInterface> serviceLoader
            = ServiceLoader.load(ServiceInterface.class);
        ServiceInterface api = serviceLoader.iterator().next();
        System.out.println("from " + api.serviceMethod());
    }
}
