package com.cegekaacademy;

import com.cegekaacademy.bank.BankClient;
import com.cegekaacademy.exception.TransferException;
import com.cegekaacademy.model.BankAccount;
import com.cegekaacademy.model.CurrentAccount;
import com.cegekaacademy.model.DepositAccount;
import com.cegekaacademy.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class CurrentAccountTest {

    @Test
    public void GIVEN_amountValid_WHEN_withdraw_THEN_returnTrue(){
        CurrentAccount currentAccount = new CurrentAccount("iban",2000);
        boolean result =  currentAccount.withdraw(200);
        Assert.assertEquals(1800,currentAccount.getBalance(),0);
        Assert.assertTrue(result);
    }

    @Test
    public void GIVEN_amountGreaterThan3000_WHEN_withdraw_THEN_returnFalse(){
        CurrentAccount currentAccount = new CurrentAccount("iban",4000);
        boolean result = currentAccount.withdraw(3100);
        Assert.assertFalse(result);
        Assert.assertEquals("verif. ca balanta nu s-a modificat ",4000,currentAccount.getBalance(),0);
    }

    @Test
    public void GIVEN_amountGreaterThanBalance_WHEN_withdraw_THEN_returnFalse(){
        CurrentAccount currentAccount = new CurrentAccount("iban",500);
        boolean result = currentAccount.withdraw(600);
        Assert.assertFalse(result);
        Assert.assertEquals("verif. ca balanta nu s-a modificat ",500,currentAccount.getBalance(),0);
    }

    @Test
    public void GIVEN_amountNegative_WHEN_withdraw_THEN_returnFalse(){
        CurrentAccount currentAccount = new CurrentAccount("iban",500);
        boolean result = currentAccount.withdraw(-100);
        Assert.assertFalse(result);
        Assert.assertEquals("verif. ca balanta nu s-a modificat ",500,currentAccount.getBalance(),0);
    }

    @Test
    public void GIVEN_amountValid_WHEN_deposit_THEN_returnTrue(){
        CurrentAccount currentAccount = new CurrentAccount("iban",500);
        boolean result = currentAccount.deposit(100);
        Assert.assertTrue(result);
        Assert.assertEquals("verif. ca balanta s-a modificat ",600,currentAccount.getBalance(),0);
    }

    @Test
    public void GIVEN_amountNegative_WHEN_deposit_THEN_returnFalse(){
        CurrentAccount currentAccount = new CurrentAccount("iban",500);
        boolean result = currentAccount.deposit(-10);
        Assert.assertFalse(result);
        Assert.assertEquals("verif. ca balanta nu s-a modificat ",500,currentAccount.getBalance(),0);
    }

    @Test
    public void GIVEN_destinationAndAmountValid_WHEN_transfer_THEN_returnTrue() throws TransferException {
        CurrentAccount currentAccount = new CurrentAccount("iban",5000);
        CurrentAccount currentAccountDestination = Mockito.mock(CurrentAccount.class);

        Mockito.when(currentAccountDestination.deposit(300D)).thenReturn(true);
        Mockito.when(currentAccountDestination.getBalance()).thenReturn(300D);

        boolean result = currentAccount.transfer(currentAccountDestination,300);

        Assert.assertTrue(result);
        Assert.assertEquals(4700,currentAccount.getBalance(),0);
        Assert.assertEquals(300,currentAccountDestination.getBalance(),0);

        Mockito.verify(currentAccountDestination, Mockito.times(1)).deposit(300D);
        Mockito.verify(currentAccountDestination, Mockito.times(2)).getBalance();
    }

    @Test
    public void GIVEN_destinationValidAndAmountGraterThan3000_WHEN_transfer_THEN_returnFalse() throws TransferException {
        CurrentAccount currentAccount = new CurrentAccount("iban",5000);
        CurrentAccount currentAccountDestination = Mockito.mock(CurrentAccount.class);

        Mockito.when(currentAccountDestination.deposit(3100D)).thenReturn(true);

        boolean result = currentAccount.transfer(currentAccountDestination,3100);

        Mockito.when(currentAccountDestination.getBalance()).thenReturn(0D);

        Assert.assertFalse(result);
        Assert.assertEquals(5000,currentAccount.getBalance(),0);
        Assert.assertEquals(0,currentAccountDestination.getBalance(),0);

        Mockito.verify(currentAccountDestination, Mockito.times(0)).deposit(3100D);
        Mockito.verify(currentAccountDestination, Mockito.times(2)).getBalance();
    }

    @Test
    public void GIVEN_destinationValidAndAmountGraterThanBalance_WHEN_transfer_THEN_returnFalse() throws TransferException {
        CurrentAccount currentAccount = new CurrentAccount("iban",5000);
        CurrentAccount currentAccountDestination = Mockito.mock(CurrentAccount.class);

        Mockito.when(currentAccountDestination.deposit(6000D)).thenReturn(true);

        boolean result = currentAccount.transfer(currentAccountDestination,6000);

        Mockito.when(currentAccountDestination.getBalance()).thenReturn(0D);

        Assert.assertFalse(result);
        Assert.assertEquals(5000,currentAccount.getBalance(),0);
        Assert.assertEquals(0,currentAccountDestination.getBalance(),0);

        Mockito.verify(currentAccountDestination, Mockito.times(0)).deposit(3100D);
        Mockito.verify(currentAccountDestination, Mockito.times(2)).getBalance();
    }

    @Test(expected = TransferException.class)
    public void GIVEN_destinationNullAndAmountValid_WHEN_transfer_THEN_returnThrowTransferException() throws TransferException {
        CurrentAccount currentAccount = new CurrentAccount("iban",5000);
        currentAccount.transfer(null,2000);
    }
}
