package com.cegekaacademy;

import com.cegekaacademy.model.CurrentAccount;
import com.cegekaacademy.model.DepositAccount;
import org.junit.Assert;
import org.junit.Test;

public class CurrentAccountTest {

    @Test
    public void GIVEN_amountNegative_WHEN_withdraw_THEN_returnFalse(){
        CurrentAccount currentAccount = new CurrentAccount("iban", 2000D);
        double amount = -1D;
        boolean result = currentAccount.withdraw(amount);
        Assert.assertFalse(result);
    }

    @Test
    public void GIVEN_amountGreaterThanBalance_WHEN_withdraw_THEN_returnFalse(){
        CurrentAccount currentAccount = new CurrentAccount("iban", 2000D);
        double amount = 3000D;
        boolean result = currentAccount.withdraw(amount);
        Assert.assertFalse(result);
    }

    @Test
    public void GIVEN_amountGreaterThanLimit_WHEN_withdraw_THEN_returnFalse(){
        CurrentAccount currentAccount = new CurrentAccount("iban", 2000D);
        double amount = 3001D;
        boolean result = currentAccount.withdraw(amount);
        Assert.assertFalse(result);
    }
    @Test
    public void GIVEN_amountValid_WHEN_withdraw_THEN_returnTrue(){
        CurrentAccount currentAccount = new CurrentAccount("iban",2000);

        boolean result =  currentAccount.withdraw(200);

        Assert.assertEquals(1800,currentAccount.getBalance(),0);
        Assert.assertTrue(result);
    }

    @Test
    public void GIVEN_currentAccountAndAmountGreaterThanLimit_WHEN_transfer_THEN_returnFalse(){
        CurrentAccount currentAccount = new CurrentAccount("iban", 2000D);
        double amount = 3001D;

        DepositAccount depositAccount = new DepositAccount("iban2", 1000D,0.07D);
        boolean result = currentAccount.transfer(depositAccount,amount);
        Assert.assertFalse(result);


    }
}