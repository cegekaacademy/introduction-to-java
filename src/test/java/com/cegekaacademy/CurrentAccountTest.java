package com.cegekaacademy;

import com.cegekaacademy.exceptions.TransferException;
import com.cegekaacademy.model.CurrentAccount;
import org.junit.Assert;
import org.junit.Test;

public class CurrentAccountTest {

    @Test
    public void GIVEN_currentAccountAndAmountValid_WHEN_withdraw_THEN_returnTrue(){
        CurrentAccount currentAccount = new CurrentAccount("iban",2000);

        boolean result =  currentAccount.withdraw(200);

        Assert.assertEquals(1800,currentAccount.getBalance(),0);
        Assert.assertTrue(result);
    }

    @Test
    public void GIVEN_amountInvalid_WHEN_withdraw_THEN_returnFalse(){
        CurrentAccount currentAccount = new CurrentAccount("iban", 2000);
        boolean result = currentAccount.withdraw(3001);
        Assert.assertFalse(result);
    }

    @Test
    public void GIVEN_amountInvalidUnder0_WHEN_withdraw_THEN_returnFalse(){
        CurrentAccount currentAccount = new CurrentAccount("iban", 2000);
        boolean result = currentAccount.withdraw(-100);
        Assert.assertFalse(result);
    }

    @Test
    public void GIVEN_amountInvalidGreaterThanBalance_WHEN_withdraw_THEN_returnFalse(){
        CurrentAccount currentAccount = new CurrentAccount("iban", 2000);
        boolean result = currentAccount.withdraw(2100);
        Assert.assertFalse(result);
    }

    @Test(expected = TransferException.class)
    public void GIVEN_destinationAndAmountInvalid_WHEN_transfer_THEN_throwException() throws TransferException {
        CurrentAccount currentAccount = new CurrentAccount("iban", 1000);
        CurrentAccount destinationAccount = null;
        currentAccount.transfer(destinationAccount, 0);
    }

    @Test
    public void GIVEN_destiantionValid_WHEN_transfer_THEN_returnTrue() throws TransferException {
        CurrentAccount currentAccount = new CurrentAccount("iban", 2300);
        CurrentAccount destinationAccount = new CurrentAccount("iban2", 1000);

        boolean result = currentAccount.transfer(destinationAccount,300);

        Assert.assertEquals(2000, currentAccount.getBalance(), 0.01);
        Assert.assertEquals(1300, destinationAccount.getBalance(), 0.01);
        Assert.assertTrue(result);
    }

    @Test
    public void GIVEN_amountInvalid_WHEN_transfer_THEN_returnFalse() throws TransferException {
        CurrentAccount currentAccount = new CurrentAccount("iban", 2300);
        CurrentAccount destinationAccount = new CurrentAccount("iban2", 1000);

        boolean result = currentAccount.transfer(destinationAccount,1001);
        Assert.assertFalse(result);
    }
}
