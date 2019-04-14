package com.cegekaacademy;

import com.cegekaacademy.exception.BankAccountNullException;
import com.cegekaacademy.model.CurrentAccount;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

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
        CurrentAccount currentAccount=new CurrentAccount("iban",4000);
        boolean result=currentAccount.withdraw(3100);
        Assert.assertEquals(4000,currentAccount.getBalance(),0);
        Assert.assertFalse(result);
    }
    @Test
    public void GIVEN_amountGreaterThanBalance_WHEN_withdraw_THEN_returnFalse(){
        CurrentAccount currentAccount=new CurrentAccount("iban",2000);
        boolean result=currentAccount.withdraw(2100);
        Assert.assertEquals(2000,currentAccount.getBalance(),0);
        Assert.assertFalse(result);
    }
    @Test
    public void GIVEN_amountLessThanZero_WHEN_withdraw_THEN_returnFalse(){
        CurrentAccount currentAccount=new CurrentAccount("iban",2000);
        boolean result=currentAccount.withdraw(-200);
        Assert.assertEquals(2000,currentAccount.getBalance(),0);
        Assert.assertFalse(result);
    }

    @Test
    public void GIVEN_amountValid_WHEN_deposit_THEN_returnTrue(){
        CurrentAccount currentAccount=new CurrentAccount("iban",2000);
        boolean result=currentAccount.deposit(200);
        Assert.assertEquals(2200,currentAccount.getBalance(),0);
        Assert.assertTrue(result);
    }
    @Test
    public void GIVEN_amountLessThanZero_WHEN_deposit_THEN_returnFalse(){
        CurrentAccount currentAccount=new CurrentAccount("iban",2000);
        boolean result=currentAccount.deposit(-200);
        Assert.assertEquals(2000,currentAccount.getBalance(),0);
        Assert.assertFalse(result);
    }

    @Test
    public void GIVEN_bankAccountDestinationValidAndAmountValid_WHEN_transfer_THEN_returnTrue() throws BankAccountNullException {
        CurrentAccount currentAccount=new CurrentAccount("IBAN",2000);
        CurrentAccount currentAccountDestination = Mockito.mock(CurrentAccount.class);

        Mockito.when(currentAccountDestination.deposit(300)).thenReturn(true);
        Mockito.when(currentAccountDestination.getBalance()).thenReturn(300D);

        boolean result=currentAccount.transfer(currentAccountDestination,300);

        Assert.assertTrue(result);
        Assert.assertEquals(1700,currentAccount.getBalance(),0);
        Assert.assertEquals(300,currentAccountDestination.getBalance(),0);

        Mockito.verify(currentAccountDestination, Mockito.times(1)).deposit(300);
        Mockito.verify(currentAccountDestination, Mockito.times(2)).getBalance();

    }
    @Test
    public void GIVEN_bankAccountDestinationValidAndAmountGreaterThan3000_WHEN_transfer_THEN_returnFalse() throws BankAccountNullException {
        CurrentAccount currentAccount=new CurrentAccount("IBAN",5000);
        CurrentAccount currentAccountDestination = Mockito.mock(CurrentAccount.class);

        Mockito.when(currentAccountDestination.deposit(3100)).thenReturn(false);
        Mockito.when(currentAccountDestination.getBalance()).thenReturn(0D);

        boolean result=currentAccount.transfer(currentAccountDestination,3100);

        Assert.assertFalse(result);
        Assert.assertEquals(5000,currentAccount.getBalance(),0);
        Assert.assertEquals(0,currentAccountDestination.getBalance(),0);

        Mockito.verify(currentAccountDestination, Mockito.times(1)).deposit(3100);
        Mockito.verify(currentAccountDestination, Mockito.times(2)).getBalance();

    }
    @Test
    public void GIVEN_bankAccountDestinationValidAndAmountGreaterThanBalance_WHEN_transfer_THEN_returnFalse() throws BankAccountNullException {
        CurrentAccount currentAccount=new CurrentAccount("IBAN",2000);
        CurrentAccount currentAccountDestination = Mockito.mock(CurrentAccount.class);

        Mockito.when(currentAccountDestination.deposit(2100)).thenReturn(false);
        Mockito.when(currentAccountDestination.getBalance()).thenReturn(0D);

        boolean result=currentAccount.transfer(currentAccountDestination,2100);

        Assert.assertFalse(result);
        Assert.assertEquals(2000,currentAccount.getBalance(),0);
        Assert.assertEquals(0,currentAccountDestination.getBalance(),0);

        Mockito.verify(currentAccountDestination, Mockito.times(1)).deposit(2100);
        Mockito.verify(currentAccountDestination, Mockito.times(2)).getBalance();

    }
    @Test(expected = BankAccountNullException.class)
    public void GIVEN_bankAccountDestinationNull_WHEN_calculateTotalBalance_THEN_returnException() throws BankAccountNullException {
        CurrentAccount currentAccount=new CurrentAccount("IBAN",2000);
        currentAccount.transfer(null,100);
    }

}
