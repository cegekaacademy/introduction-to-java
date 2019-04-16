package com.cegekaacademy;

import com.cegekaacademy.model.CurrentAccount;
import com.cegekaacademy.model.DepositAccount;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class CurrentAccountTest {

    @Test
    public void testCurrentAccount() {
        CurrentAccount currentAccount = new CurrentAccount("iban", 2000);

        boolean result = currentAccount.withdraw(200);

        Assert.assertEquals(1800, currentAccount.getBalance(), 0);
        Assert.assertTrue(result);
    }

    @Test
    public void GIVEN_depositAccountDestinationAndAmountSmallerThanLimit_WHEN_transfer_THEN_returnTrue() {
        CurrentAccount currentAccount = new CurrentAccount("iban", 1000D);
        DepositAccount depositAccount = Mockito.mock(DepositAccount.class);
        Mockito.when(depositAccount.getBalance()).thenReturn(1D);
        double amount = 200;
        Assert.assertTrue(currentAccount.transfer(depositAccount, amount));
        Mockito.verify(depositAccount, Mockito.times(1)).getBalance();
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_depositAccountDestinationAndAmountGreaterThanLimit_WHEN_transfer_THEN_throwException() {
        CurrentAccount currentAccount = new CurrentAccount("iban", 1000D);
        DepositAccount depositAccount = Mockito.mock(DepositAccount.class);
        Mockito.when(depositAccount.getBalance()).thenReturn(1D);
        double amount = 205;
        currentAccount.transfer(depositAccount, amount);
        Mockito.verify(depositAccount, Mockito.times(1)).getBalance();
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_depositAccountDestinationAndAmountSmallerThanLimitAndNotEnougMoneyInAccount_WHEN_transfer_THEN_throwException() {
        CurrentAccount currentAccount = new CurrentAccount("iban", 195);
        DepositAccount depositAccount = Mockito.mock(DepositAccount.class);
        Mockito.when(depositAccount.getBalance()).thenReturn(1D);
        double amount = 200;
        currentAccount.transfer(depositAccount, amount);
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
        Mockito.verify(destinationAccount, Mockito.times(1)).getBalance();
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_currentAccountDestinationAndAmoutGreaterThanLimit_WHEN_trasnfer_THEN_throwException() {
        CurrentAccount currentAccount = new CurrentAccount("iban", 3195);
        CurrentAccount destinationAccount = Mockito.mock(CurrentAccount.class);
        Mockito.when(destinationAccount.getBalance()).thenReturn(1D);
        double amount = 3005;
        currentAccount.transfer(destinationAccount, amount);
        Mockito.verify(destinationAccount, Mockito.times(1)).getBalance();
    }

}
