package com.cegekaacademy;

import com.cegekaacademy.bank.BankClient;
import com.cegekaacademy.model.CurrentAccount;
import com.cegekaacademy.model.DepositAccount;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class DepositAccountTest {
    @Test
    public void GIVEN_amountNegative_WHEN_deposit_THEN_returnFalse(){
        DepositAccount depositAccount = new DepositAccount("iban", 2000D, 0.7D);
        double amount = -1D;
        boolean result = depositAccount.deposit(amount);
        Assert.assertFalse(result);
    }
    @Test
    public void GIVEN_amountSmallerThanLimit_WHEN_deposit_THEN_returnFalse(){
        DepositAccount depositAccount = new DepositAccount("iban", 2000D, 0.7D);
        double amount = 199;
        boolean result = depositAccount.deposit(amount);
        Assert.assertFalse(result);
    }

    @Test
    public void GIVEN_amountValid_WHEN_deposit_THEN_returnTrue(){
        DepositAccount depositAccount = new DepositAccount("iban", 2000D, 0.7D);
        double amount = 500D;
        boolean result = depositAccount.deposit(amount);
        Assert.assertTrue(result);
        Assert.assertEquals(2500D,depositAccount.getBalance(),0);
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_noDestinationAccount_WHEN_transfer_THEN_returnThrowException(){
       DepositAccount depositAccount = new DepositAccount("iban",3000D, 0.7);
       depositAccount.transfer(null, 100D);
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_sameAccount_WHEN_transfer_THEN_returnThrowException(){
        DepositAccount depositAccount = new DepositAccount("iban",3000D, 0.7);
        depositAccount.transfer(depositAccount, 100D);
    }
    @Test
    public void GIVEN_amountNegative_WHEN_transfer_THEN_returnFalse(){
        DepositAccount depositAccount = new DepositAccount("iban", 2000D, 0.7D);
        double amount = -100D;

        DepositAccount depositAccount1 = new DepositAccount("iban2", 1000D,0.07D);
        boolean result = depositAccount.transfer(depositAccount1,amount);
        Assert.assertFalse(result);
    }

    @Test
    public void GIVEN_amountGreaterThanBalance_WHEN_transfer_THEN_returnFalse(){
        DepositAccount depositAccount = new DepositAccount("iban", 2000D, 0.7D);
        double amount = 2001D;

        DepositAccount depositAccount1 = new DepositAccount("iban2", 1000D,0.07D);
        boolean result = depositAccount.transfer(depositAccount1,amount);
        Assert.assertFalse(result);
    }

    @Test
    public void GIVEN_depositAccountAndAmountSmallerThanLimit_WHEN_transfer_THEN_returnFalse(){
        DepositAccount depositAccount = new DepositAccount("iban", 2000D, 0.7D);
        double amount = 100D;

        DepositAccount depositAccount1 = new DepositAccount("iban2", 1000D,0.07D);
        boolean result = depositAccount.transfer(depositAccount1,amount);
        Assert.assertFalse(result);


    }
    @Test
    public void GIVEN_amountValid_WHEN_transfer_THEN_returnTrue(){
        DepositAccount depositAccount = new DepositAccount("iban", 2000D, 0.7D);
        double amount = 1000D;

        DepositAccount depositAccount1 = new DepositAccount("iban2", 1000D,0.07D);
        boolean result = depositAccount.transfer(depositAccount1,amount);

        Assert.assertTrue(result);
        Assert.assertEquals(1000D, depositAccount.getBalance(),0);
        Assert.assertEquals(2000D, depositAccount1.getBalance(),0);


    }
}
