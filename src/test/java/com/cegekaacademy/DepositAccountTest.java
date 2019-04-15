package com.cegekaacademy;

import com.cegekaacademy.model.DepositAccount;
import org.junit.Assert;
import org.junit.Test;

public class DepositAccountTest {

    @Test
    public void GIVEN_amountValid_WHEN_deposit_THEN_returnTrue(){
        DepositAccount depositAccount = new DepositAccount("iban", 1000, 0.1, 12);
        boolean result = depositAccount.deposit(200);
        Assert.assertTrue(result);
        Assert.assertEquals(1200, depositAccount.getBalance(), 0.01);
    }

    @Test
    public void GIVEN_amountLessThan200_WHEN_deposit_THEN_returnFalse(){
        DepositAccount depositAccount = new DepositAccount("iban", 1000, 0.1, 12);
        boolean result = depositAccount.deposit(199);
        Assert.assertFalse(result);

    }

    @Test
    public void GIVEN_amountLessThan0_WHEN_deposit_THEN_returnFalse(){
        DepositAccount depositAccount = new DepositAccount("iban", 1000, 0.1, 12);
        boolean result = depositAccount.deposit(0);
        Assert.assertFalse(result);

    }

    @Test
    public void GIVEN_balanceAndInterestAndPeriodOfTimeValid_WHEN_getRate_THEN_returnRate(){
        DepositAccount depositAccount = new DepositAccount("iban", 1000,0.01,12);
        double rate = depositAccount.getRate();
        Assert.assertEquals(10,rate,0.01);
    }

    @Test
    public void GIVEN_balanceInvalid_WHEN_getRate_THEN_return0(){
        DepositAccount depositAccount = new DepositAccount("iban", 0,0.01,12);
        double rate = depositAccount.getRate();
        Assert.assertEquals(0,rate,0.01);
    }

    @Test
    public void GIVEN_interestInvalid_WHEN_getRate_THEN_return0(){
        DepositAccount depositAccount = new DepositAccount("iban", 1000,0,12);
        double rate = depositAccount.getRate();
        Assert.assertEquals(0,rate,0.01);
    }

    @Test
    public void GIVEN_periodOfTimeInvalid_WHEN_getRate_THEN_return0(){
        DepositAccount depositAccount = new DepositAccount("iban", 0,0.01,0);
        double rate = depositAccount.getRate();
        Assert.assertEquals(0,rate,0.01);
    }

}
