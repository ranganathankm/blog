package com.blogspot.javanbswing.sp;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author ranga
 *
 */

public class ServiceProviderImpl1Test 
{
    @Test
    public void testImpl1()
    {
        ServiceProviderImpl1 impl1 = new ServiceProviderImpl1();
        Assert.assertEquals("Sun", impl1.serviceMethod());
    }
}
