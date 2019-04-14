package com.cegekaacademy;

import com.cegekaacademy.model.CurrentAccount;
import org.junit.Assert;
import org.junit.Test;

public class CurrentAccountTest {

    @Test
    public void testCurrentAccount(){
        CurrentAccount currentAccount = new CurrentAccount("iban",2000);

        boolean result =  currentAccount.withdraw(200);

        Assert.assertEquals(1800,currentAccount.getBalance(),0);
        Assert.assertTrue(result);
    }
}
