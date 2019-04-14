package com.cegekaacademy;

import com.cegekaacademy.exception.TransferException;
import com.cegekaacademy.model.DepositAccount;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class DepositAccountTest {
    @Test
    public void GIVEN_amountValid_WHEN_withdraw_THEN_returnTrue(){
        DepositAccount depositAccount = new DepositAccount("iban",2000);
        boolean result =  depositAccount.withdraw(200);
        Assert.assertEquals(1800,depositAccount.getBalance(),0);
        Assert.assertTrue(result);
    }

    @Test
    public void GIVEN_amountGreaterThanBalance_WHEN_withdraw_THEN_returnFalse(){
        DepositAccount depositAccount = new DepositAccount("iban",500);
        boolean result = depositAccount.withdraw(600);
        Assert.assertFalse(result);
        Assert.assertEquals("verif. ca balanta nu s-a modificat ",500, depositAccount.getBalance(),0);
    }

    @Test
    public void GIVEN_amountNegative_WHEN_withdraw_THEN_returnFalse(){
        DepositAccount depositAccount = new DepositAccount("iban",500);
        boolean result = depositAccount.withdraw(-100);
        Assert.assertFalse(result);
        Assert.assertEquals("verif. ca balanta nu s-a modificat ",500, depositAccount.getBalance(),0);
    }

    @Test
    public void GIVEN_amountValid_WHEN_deposit_THEN_returnTrue(){
        DepositAccount depositAccount = new DepositAccount("iban",500);
        boolean result = depositAccount.deposit(300);
        Assert.assertTrue(result);
        Assert.assertEquals("verif. ca balanta s-a modificat ",800, depositAccount.getBalance(),0);
    }

    @Test
    public void GIVEN_amountNegative_WHEN_deposit_THEN_returnFalse(){
        DepositAccount depositAccount = new DepositAccount("iban",500);
        boolean result = depositAccount.deposit(-10);
        Assert.assertFalse(result);
        Assert.assertEquals("verif. ca balanta nu s-a modificat ",500, depositAccount.getBalance(),0);
    }

    @Test
    public void GIVEN_amountLessThan200_WHEN_deposit_THEN_returnFalse(){
        DepositAccount depositAccount = new DepositAccount("iban",500);
        boolean result = depositAccount.deposit(100);
        Assert.assertFalse(result);
        Assert.assertEquals("verif. ca balanta nu s-a modificat ",500, depositAccount.getBalance(),0);
    }

    @Test
    public void GIVEN_destinationAndAmountValid_WHEN_transfer_THEN_returnTrue() throws TransferException {
        DepositAccount depositAccount = new DepositAccount("iban",5000);
        DepositAccount depositAccountDestination = Mockito.mock(DepositAccount.class);

        Mockito.when(depositAccountDestination.deposit(300D)).thenReturn(true);
        Mockito.when(depositAccountDestination.getBalance()).thenReturn(300D);

        boolean result = depositAccount.transfer(depositAccountDestination,300);

        Assert.assertTrue(result);
        Assert.assertEquals(4700, depositAccount.getBalance(),0);
        Assert.assertEquals(300,depositAccountDestination.getBalance(),0);

        Mockito.verify(depositAccountDestination, Mockito.times(1)).deposit(300D);
        Mockito.verify(depositAccountDestination, Mockito.times(2)).getBalance();
    }

    @Test
    public void GIVEN_destinationValidAndAmountLessThan200_WHEN_transfer_THEN_returnFalse() throws TransferException {
        DepositAccount depositAccount = new DepositAccount("iban",5000);
        DepositAccount depositAccountDestination = Mockito.mock(DepositAccount.class);

        Mockito.when(depositAccountDestination.deposit(100D)).thenReturn(false);
        boolean result = depositAccount.transfer(depositAccountDestination,100);
        Mockito.when(depositAccountDestination.getBalance()).thenReturn(0D);

        Assert.assertFalse(result);
        Assert.assertEquals(5000,depositAccount.getBalance(),0);
        Assert.assertEquals(0,depositAccountDestination.getBalance(),0);

        Mockito.verify(depositAccountDestination, Mockito.times(1)).deposit(100D);
        Mockito.verify(depositAccountDestination, Mockito.times(2)).getBalance();
    }

    @Test
    public void GIVEN_destinationValidAndAmountGraterThanBalance_WHEN_transfer_THEN_returnFalse() throws TransferException {
        DepositAccount depositAccount = new DepositAccount("iban",5000);
        DepositAccount depositAccountDestination = Mockito.mock(DepositAccount.class);

        Mockito.when(depositAccountDestination.deposit(6000D)).thenReturn(true);
        boolean result = depositAccount.transfer(depositAccountDestination,6000);
        Mockito.when(depositAccountDestination.getBalance()).thenReturn(0D);

        Assert.assertFalse(result);
        Assert.assertEquals(5000, depositAccount.getBalance(),0);
        Assert.assertEquals(0,depositAccountDestination.getBalance(),0);

        Mockito.verify(depositAccountDestination, Mockito.times(0)).deposit(3100D);
        Mockito.verify(depositAccountDestination, Mockito.times(2)).getBalance();
    }

    @Test(expected = TransferException.class)
    public void GIVEN_destinationNullAndAmountValid_WHEN_transfer_THEN_returnThrowTransferException() throws TransferException {
        DepositAccount depositAccount = new DepositAccount("iban",5000);
        depositAccount.transfer(null,2000);
    }

    @Test
    public void GIVEN_depositAccountValid_WHEN_calculateRate_THEN_returnRate(){
        DepositAccount depositAccount = new DepositAccount("iban",200,0.3f,2);
        double rate = depositAccount.calculateRate();
        Assert.assertEquals(10,rate,0.01);
    }

    @Test
    public void GIVEN_depositAccountWithBalanceNegative_WHEN_calculateRate_THEN_returnZero(){
        DepositAccount depositAccount = new DepositAccount("iban",-100,0.3f,2);
        double rate = depositAccount.calculateRate();
        Assert.assertEquals(0, rate,0);
    }

    @Test
    public void GIVEN_depositAccountWithInterestRateNegative_WHEN_calculateRate_THEN_returnZero(){
        DepositAccount depositAccount = new DepositAccount("iban",100,-0.3f,2);
        double rate = depositAccount.calculateRate();
        Assert.assertEquals(0, rate,0);
    }

    @Test
    public void GIVEN_depositAccountValid_WHEN_addInterestToBalance_THEN_returnNewBalance(){
        DepositAccount depositAccount = new DepositAccount("iban",100,0.3f,2);
        depositAccount.addInterestToBalance();
        Assert.assertEquals(105, depositAccount.getBalance(),0.01);
    }
    @Test
    public void GIVEN_depositAccountWithInterestRateNegative_WHEN_addInterestToBalance_THEN_returnOldBalance(){
        DepositAccount depositAccount = new DepositAccount("iban",100,-0.3f,2);
        depositAccount.addInterestToBalance();
        Assert.assertEquals(100, depositAccount.getBalance(),0.01);
    }

}