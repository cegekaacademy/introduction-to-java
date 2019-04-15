package com.cegekaacademy;

import com.cegekaacademy.model.DepositAccount;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class DepositAccountTest {
    @Test
    public void GIVEN_informatisValid_WHEN_creatingObject_THEN_createNotNullObject(){
        DepositAccount depositAccount=new DepositAccount("iban",1500,0.3f,10);
        Assert.assertNotNull(depositAccount);
        Assert.assertEquals("iban",depositAccount.getIban());
        Assert.assertEquals(1500,depositAccount.getBalance(),0.0);
        Assert.assertEquals(0.3f,depositAccount.getInteresetRate(),0.0);
        Assert.assertEquals(10,depositAccount.getPeriod());
    }
    @Test
    public void GIVEN_nullObject_WHEN_creatingObject_THEN_createNullObject(){
        DepositAccount depositAccount=null;
        Assert.assertNull(depositAccount);
    }
    @Test(expected = IllegalStateException.class)
    public void GIVEN_interestRateInvalid_WHEN_determinateRate_THEN_throwIllegalStateException(){
        DepositAccount depositAccount=new DepositAccount("iban",1500,-20f,15);
        float rezultRate=depositAccount.determinateRate();
    }
    @Test(expected = IllegalStateException.class)
    public void GIVEN_periodInvalid_WHEN_determinateRate_THEN_throwIllegalStateException(){
        DepositAccount depositAccount=new DepositAccount("iban",1500,0.20f,10);
        float rezultRate=depositAccount.determinateRate();
    }
    @Test
    public void GIVEN_balanceInvalid_WHEN_determinateRate_THEN_return0(){
        DepositAccount depositAccount=new DepositAccount("iban",0,20f,12);
        float rezultatRate=depositAccount.determinateRate();
        Assert.assertEquals(0,rezultatRate,0.0);
    }
    @Test
    public void GIVEN_informationsValid_WHEN_determinateRate_THEN_returnRate(){
        DepositAccount depositAccount= Mockito.mock(DepositAccount.class);
        Mockito.when(depositAccount.getBalance()).thenReturn(1500d);
        Mockito.when(depositAccount.getPeriod()).thenReturn(15);
        Mockito.when(depositAccount.getInteresetRate()).thenReturn(0.2f);
        Mockito.when(depositAccount.determinateRate()).thenReturn(120f);
        float rezultat=depositAccount.determinateRate();
        Assert.assertEquals(120f,rezultat,0.0);
        Assert.assertEquals(1500,depositAccount.getBalance(),0.0);
    }
    @Test
    public void GIVEN_amountInvalid_WHEN_deposit_THEN_returnFalse(){
        DepositAccount depositAccount=new DepositAccount("iban",1500,0.3f,10);
        boolean rezultDeposit=depositAccount.deposit(100);
        Assert.assertFalse(rezultDeposit);
    }
    @Test
    public void GIVEN_amountInvalidNegative_WHEN_deposit_THEN_returnFalse(){
        DepositAccount depositAccount=new DepositAccount("iban",1500,0.3f,10);
        boolean rezultDeposit=depositAccount.deposit(-100);
        Assert.assertFalse(rezultDeposit);
    }
    @Test
    public void GIVEN_amountValid_WHEN_deposit_THEN_returnTrue(){
        DepositAccount depositAccount=new DepositAccount("iban",1500,0.3f,10);
        boolean rezultDeposit=depositAccount.deposit(300);
        Assert.assertTrue(rezultDeposit);
    }
    @Test
    public void GIVEN_amountValid_WHEN_deposit_THEN_increasedBalance(){
        DepositAccount depositAccount=new DepositAccount("iban",1500,0.3f,10);
        boolean rezultDeposit=depositAccount.deposit(300);
        Assert.assertEquals(1800,depositAccount.getBalance(),0.0);
    }

}
