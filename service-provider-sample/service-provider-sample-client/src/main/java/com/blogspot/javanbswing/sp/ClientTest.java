package com.blogspot.javanbswing.sp;

import java.util.Iterator;
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
        
        
        Iterator<ServiceInterface> iterator = serviceLoader.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next().getClass());
        }
    }
}
