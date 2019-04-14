package com.cegekaacademy;

import com.cegekaacademy.exception.BankAccountNullException;
import com.cegekaacademy.model.CurrentAccount;
import com.cegekaacademy.model.DepositAccount;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class DepositAccountTest {
    @Test
    public void GIVEN_amountValid_WHEN_withdraw_THEN_returnTrue(){
        CurrentAccount currentAccount = new CurrentAccount("iban",2000);

        boolean result =  currentAccount.withdraw(200);

        Assert.assertEquals(1800,currentAccount.getBalance(),0);
        Assert.assertTrue(result);
    }

    @Test
    public void GIVEN_amountGreaterThanBalance_WHEN_withdraw_THEN_returnFalse(){
        DepositAccount depositAccount=new DepositAccount("iban",2000);
        boolean result=depositAccount.withdraw(2100);
        Assert.assertEquals(2000,depositAccount.getBalance(),0);
        Assert.assertFalse(result);
    }
    @Test
    public void GIVEN_amountLessThanZero_WHEN_withdraw_THEN_returnFalse(){
        DepositAccount depositAccount=new DepositAccount("iban",2000);
        boolean result=depositAccount.withdraw(-100);
        Assert.assertEquals(2000,depositAccount.getBalance(),0);
        Assert.assertFalse(result);
    }

    @Test
    public void GIVEN_amountValid_WHEN_deposit_THEN_returnTrue(){
        DepositAccount depositAccount=new DepositAccount("iban",2000);
        boolean result=depositAccount.deposit(400);
        Assert.assertEquals(2400,depositAccount.getBalance(),0);
        Assert.assertTrue(result);
    }

    @Test
    public void GIVEN_amountLessThen200_WHEN_deposit_THEN_returnTrue(){
        DepositAccount depositAccount=new DepositAccount("iban",2000);
        boolean result=depositAccount.deposit(100);
        Assert.assertEquals(2000,depositAccount.getBalance(),0);
        Assert.assertFalse(result);
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
        DepositAccount depositAccount=new DepositAccount("IBAN",2000);
        DepositAccount depositAccountDestination = Mockito.mock(DepositAccount.class);

        Mockito.when(depositAccountDestination.deposit(400)).thenReturn(true);
        Mockito.when(depositAccountDestination.getBalance()).thenReturn(400D);

        boolean result=depositAccount.transfer(depositAccountDestination,400);

        Assert.assertTrue(result);
        Assert.assertEquals(1600,depositAccount.getBalance(),0);
        Assert.assertEquals(400,depositAccountDestination.getBalance(),0);

        Mockito.verify(depositAccountDestination, Mockito.times(1)).deposit(400);
        Mockito.verify(depositAccountDestination, Mockito.times(2)).getBalance();

    }
    @Test
    public void GIVEN_bankAccountDestinationValidAndAmountLessThan200_WHEN_transfer_THEN_returnFalse() throws BankAccountNullException {
        DepositAccount depositAccount=new DepositAccount("IBAN",5000);
        DepositAccount depositAccountDestination = Mockito.mock(DepositAccount.class);

        Mockito.when(depositAccountDestination.deposit(100)).thenReturn(false);
        Mockito.when(depositAccountDestination.getBalance()).thenReturn(0D);

        boolean result=depositAccount.transfer(depositAccountDestination,100);

        Assert.assertFalse(result);
        Assert.assertEquals(5000,depositAccount.getBalance(),0);
        Assert.assertEquals(0,depositAccountDestination.getBalance(),0);

        Mockito.verify(depositAccountDestination, Mockito.times(1)).deposit(100);
        Mockito.verify(depositAccountDestination, Mockito.times(2)).getBalance();

    }
    @Test
    public void GIVEN_bankAccountDestinationValidAndAmountGreaterThanBalance_WHEN_transfer_THEN_returnFalse() throws BankAccountNullException {
        DepositAccount depositAccount=new DepositAccount("IBAN",2000);
        DepositAccount depositAccountDestination = Mockito.mock(DepositAccount.class);

        Mockito.when(depositAccountDestination.deposit(2100)).thenReturn(false);
        Mockito.when(depositAccountDestination.getBalance()).thenReturn(0D);

        boolean result=depositAccount.transfer(depositAccountDestination,2100);

        Assert.assertFalse(result);
        Assert.assertEquals(2000,depositAccount.getBalance(),0);
        Assert.assertEquals(0,depositAccountDestination.getBalance(),0);

        Mockito.verify(depositAccountDestination, Mockito.times(1)).deposit(2100);
        Mockito.verify(depositAccountDestination, Mockito.times(2)).getBalance();

    }
    @Test(expected = BankAccountNullException.class)
    public void GIVEN_bankAccountDestinationNull_WHEN_calculateTotalBalance_THEN_returnException() throws BankAccountNullException {
        DepositAccount depositAccount=new DepositAccount("IBAN",2000);
        depositAccount.transfer(null,100);
    }
    @Test
    public void GIVEN_bankAccountInterestRateValidAndPeriodOfTimeValid_WHEN_calculateGetInterest_THEN_returnDouble(){
        DepositAccount depositAccount=new DepositAccount("IBAN",2000,0.03,6);
        double result=depositAccount.getInterest();
        Assert.assertEquals(30D,result,0.01);
    }

    @Test
    public void GIVEN_bankAccountInterestRateInvalidAndPeriodOfTimeValid_WHEN_calculateGetInterest_THEN_returnZero(){
        DepositAccount depositAccount=new DepositAccount("IBAN",2000,-0.03,6);
        double result=depositAccount.getInterest();
        Assert.assertEquals(0,result,0.01);
    }

    @Test
    public void GIVEN_bankAccountInterestRateValidAndPeriodOfTimeInvalid_WHEN_calculateGetInterest_THEN_returnZero(){
        DepositAccount depositAccount=new DepositAccount("IBAN",2000,-0.03,6);
        double result=depositAccount.getInterest();
        Assert.assertEquals(0,result,0.01);
    }

    @Test
    public void GIVEN_bankAccountInterestInvalid_WHEN_calculateGetInterestAfterTaxDeduction_THEN_returnZero(){
        DepositAccount depositAccount=new DepositAccount("IBAN",2000,-0.03,6);
        double result=depositAccount.getInterestAfterTaxDeduction();
        Assert.assertEquals(0,result,0.01);
    }

    @Test
    public void GIVEN_bankAccountInterestValid_WHEN_calculateGetInterestAfterTaxDeduction_THEN_returnDouble(){
        DepositAccount depositAccount=new DepositAccount("IBAN",2000,0.03,6);
        double result=depositAccount.getInterestAfterTaxDeduction();
        Assert.assertEquals(29.7D,result,0.01);
    }
    @Test
    public void GIVEN_bankAccountInterestValid_WHEN_calculateAddInterest_THEN_returnDouble(){
        DepositAccount depositAccount=new DepositAccount("IBAN",2000,0.03,6);
        depositAccount.addInterest();
        Assert.assertEquals(2029.7D,depositAccount.getBalance(),0.01);
    }
    @Test
    public void GIVEN_bankAccountInterestInvalid_WHEN_calculateAddInterest_THEN_returnZero(){
        DepositAccount depositAccount=new DepositAccount("IBAN",2000,-0.03,6);
        depositAccount.addInterest();
        Assert.assertEquals(2000,depositAccount.getBalance(),0.01);
    }
}