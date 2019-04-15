package com.cegekaacademy.model;

import com.cegekaacademy.model.CurrentAccount;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class CurrentAccountTest {

    @Test
    public void GIVEN_withdrawValue_WHEN_withdraw_THEN_returnCorrect() {
        CurrentAccount currentAccount = new CurrentAccount("iban", 2000);

        boolean result = currentAccount.withdraw(200);

        Assert.assertEquals(1800, currentAccount.getBalance(), 0);
        Assert.assertTrue(result);
    }

    @Test
    public void GIVEN_withdrawValueNotCorrectOrOverTheLimit_WHEN_withdraw_THEN_returnFalse() {
        CurrentAccount currentAccount = new CurrentAccount("iban", 2000);

        boolean result = currentAccount.withdraw(-1);
        boolean result2 = currentAccount.withdraw(3001);

        Assert.assertFalse(result);
        Assert.assertFalse(result2);
    }

    @Test
    public void GIVEN_amountCorrect_WHEN_deposit_THEN_returnTrue() {
        CurrentAccount currentAccount = new CurrentAccount("iban", 2000);

        boolean result = currentAccount.deposit(200);
        Assert.assertEquals(2200, currentAccount.getBalance(), 0.01d);
        Assert.assertTrue(result);
    }

    @Test
    public void GIVEN_amountCorrectAndExistingDestination_WHEN_transfer_THEN_returnTrue() {
        CurrentAccount currentAccount = new CurrentAccount("iban", 2000);
        CurrentAccount currentAccountMock = Mockito.mock(CurrentAccount.class);

        Mockito.when(currentAccountMock.deposit(200)).thenReturn(true);
        boolean result = currentAccount.transfer(currentAccountMock, 200);
        Assert.assertEquals(1800, currentAccount.getBalance(), 0.01d);
        Assert.assertTrue(result);
    }

}
