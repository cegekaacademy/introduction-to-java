package com.cegekaacademy;

import com.cegekaacademy.utils.Helper;
import org.junit.Assert;
import org.junit.Test;

public class HelperTest {
    @Test(expected = IllegalStateException.class)
    public void testValidatePid() {
        Assert.assertTrue(Helper.validatePid("1950201170030"));
        Assert.assertTrue(Helper.validatePid("1410116364866"));
        Assert.assertTrue(Helper.validatePid("6170406520572"));
        Assert.assertFalse(Helper.validatePid("1122334455123"));
        Helper.validatePid("1950201");
    }
}
