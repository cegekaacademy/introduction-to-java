package com.cegekaacademy;

import com.cegekaacademy.model.CurrentAccount;
import com.cegekaacademy.model.DepositAccount;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class BankAccountTest {

    @Test
    public void testCurrentAccount() {
        CurrentAccount currentAccount = new CurrentAccount("iban", 2000);

        boolean result = currentAccount.withdraw(200);

        Assert.assertEquals(1800, currentAccount.getBalance(), 0);
        Assert.assertTrue(result);
    }

    @Test
    public void GIVEN_depositAccountValidDestinationAndAmountBiggerOrEqualThanLimit_WHEN_transfer_THEN_returnTrue() {
        CurrentAccount currentAccount = new CurrentAccount("iban", 1000D);
        DepositAccount destinationAccount = new DepositAccount("iban", 1);
        double amount = 200;
        Assert.assertTrue(currentAccount.transfer(destinationAccount, amount));
        Assert.assertEquals(800D, currentAccount.getBalance(), 0);
        Assert.assertEquals(201D, destinationAccount.getBalance(), 0);
    }

    @Test
    public void GIVEN_depositAccountDestinationAndAmountSmallerThanLimit_WHEN_transfer_THEN_returnFalse() {
        CurrentAccount currentAccount = new CurrentAccount("iban", 1000D);
        DepositAccount depositAccount = Mockito.mock(DepositAccount.class);
        Mockito.when(depositAccount.getBalance()).thenReturn(1D);
        double amount = 190;
        Assert.assertFalse(currentAccount.transfer(depositAccount, amount));
        Assert.assertEquals(1000D, currentAccount.getBalance(), 0);
        Assert.assertEquals(1D, depositAccount.getBalance(), 0);
        Mockito.verify(depositAccount, Mockito.times(1)).getBalance();
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_depositAccountDestinationAndAmountSmallerThanLimitAndNotEnougMoneyInAccount_WHEN_transfer_THEN_throwException() {
        CurrentAccount currentAccount = new CurrentAccount("iban", 195);
        DepositAccount depositAccount = Mockito.mock(DepositAccount.class);
        Mockito.when(depositAccount.getBalance()).thenReturn(1D);
        double amount = 200;
        currentAccount.transfer(depositAccount, amount);
        Assert.assertEquals(195, currentAccount.getBalance(), 0);
        Assert.assertEquals(1D, depositAccount.getBalance(), 0);
        Mockito.verify(depositAccount, Mockito.times(1)).getBalance();
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_invalidDestinationAccount_WHEN_transfer_THEN_ThrowException() {
        CurrentAccount currentAccount = new CurrentAccount("iban", 200D);
        currentAccount.transfer(null, 20D);
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_currentAccountDestinationAndNotEnoughMoneyInAccount_WHEN_transfer_THEN_throwException() {
        CurrentAccount currentAccount = new CurrentAccount("iban", 195);
        CurrentAccount destinationAccount = Mockito.mock(CurrentAccount.class);
        Mockito.when(destinationAccount.getBalance()).thenReturn(1D);
        double amount = 210;
        currentAccount.transfer(destinationAccount, amount);
        Assert.assertEquals(195D, currentAccount.getBalance(), 0);
        Assert.assertEquals(1D, destinationAccount.getBalance(), 0);
        Mockito.verify(destinationAccount, Mockito.times(1)).getBalance();
    }

    @Test
    public void GIVEN_currentAccountDestinationAndAmountGreaterThanLimit_WHEN_transfer_THEN_returnFalse() {
        CurrentAccount currentAccount = new CurrentAccount("iban", 3195);
        CurrentAccount destinationAccount = Mockito.mock(CurrentAccount.class);
        Mockito.when(destinationAccount.getBalance()).thenReturn(1D);
        double amount = 3005;
        Assert.assertFalse(currentAccount.transfer(destinationAccount, amount));
        Assert.assertEquals(3195D, currentAccount.getBalance(), 0);
        Assert.assertEquals(1D, destinationAccount.getBalance(), 0);
    }

}
