package com.blogspot.javanbswing.sp;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author ranga
 *
 */

public class ServiceProviderImpl2Test 
{
    @Test
    public void testImpl2()
    {
        ServiceProviderImpl2 impl2 = new ServiceProviderImpl2();
        Assert.assertEquals("Moon", impl2.serviceMethod());
    }
}
