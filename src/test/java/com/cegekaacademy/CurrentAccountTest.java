package com.cegekaacademy;

import com.cegekaacademy.exception.TransferException;
import com.cegekaacademy.model.CurrentAccount;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class CurrentAccountTest {
    @Test
    public void GIVEN_amountOver3000_WHEN_withDraw_THEN_returnFalse(){
        CurrentAccount currentAccount=new CurrentAccount("iban1",4500);
        boolean resultedWithDraw=currentAccount.withdraw(3500);
        Assert.assertFalse(resultedWithDraw);
    }
    @Test
    public void GIVEN_amountOverBalance_WHEN_withDraw_THEN_returnFalse(){
        CurrentAccount currentAccount=new CurrentAccount("iban1",1500);
        boolean resultedWithDraw=currentAccount.withdraw(1700);
        Assert.assertFalse(resultedWithDraw);
    }
    @Test
    public void GIVEN_negativeAmount_WHEN_withDraw_THEN_returnFalse(){
        CurrentAccount currentAccount=new CurrentAccount("iban1",1500);
        boolean resultedWithDraw=currentAccount.withdraw(-700);
        Assert.assertFalse(resultedWithDraw);
    }
    @Test
    public void GIVEN_amountValid_WHEN_withDraw_THEN_returnTrue(){
        CurrentAccount currentAccount=new CurrentAccount("iban1",1500);
        boolean resultedWithDraw=currentAccount.withdraw(700);
        Assert.assertTrue(resultedWithDraw);
    }
    @Test
    public void GIVEN_amountValid_WHEN_withDraw_THEN_decreaseBalance(){
        CurrentAccount currentAccount=new CurrentAccount("iban1",1500);
        boolean resultedWithDraw=currentAccount.withdraw(700);
        Assert.assertEquals(800,currentAccount.getBalance(),0.0);
    }
    @Test
    public void GIVEN_amountNegative_WHEN_deposit_THEN_returnFalse(){
        CurrentAccount currentAccount=new CurrentAccount("iban1",1500);
        boolean resultedWithDraw=currentAccount.deposit(-700);
        Assert.assertFalse(resultedWithDraw);
    }
    @Test
    public void GIVEN_amountValid_WHEN_deposit_THEN_returnTrue(){
        CurrentAccount currentAccount=new CurrentAccount("iban1",1500);
        boolean resultedWithDraw=currentAccount.deposit(700);
        Assert.assertTrue(resultedWithDraw);
    }
    @Test
    public void GIVEN_amountNegative_WHEN_deposit_THEN_increaseBalance(){
        CurrentAccount currentAccount=new CurrentAccount("iban1",1500);
        boolean resultedWithDraw=currentAccount.deposit(700);
        Assert.assertEquals(2200,currentAccount.getBalance(),0.0);
    }
    @Test(expected = TransferException.class)
    public void GIVEN_destinationNull_WHEN_transfer_THEN_throwTransferException() throws TransferException {
        CurrentAccount currentAccount1=new CurrentAccount("iban1",1500);
        CurrentAccount currentAccount2=new CurrentAccount("iban2",2300);
        boolean resultTransfer=currentAccount1.transfer(null,1200);
    }
    @Test
    public void GIVEN_amountNegative_WHEN_transfer_THEN_returnFalse() throws TransferException {
        CurrentAccount currentAccount1=new CurrentAccount("iban1",1500);
        CurrentAccount currentAccount2=new CurrentAccount("iban2",2300);
        boolean resultTransfer=currentAccount1.transfer(currentAccount2,-100);
        Assert.assertFalse(resultTransfer);
    }
    @Test
    public void GIVEN_amountOverBalance_WHEN_transfer_THEN_returnFalse() throws TransferException {
        CurrentAccount currentAccount1=new CurrentAccount("iban1",1500);
        CurrentAccount currentAccount2=new CurrentAccount("iban2",2300);
        boolean resultTransfer=currentAccount1.transfer(currentAccount2,1600);
        Assert.assertFalse(resultTransfer);
    }
    @Test
    public void GIVEN_destinationAndAmountValid_WHEN_transfer_THEN_returnTrue() throws TransferException {
        CurrentAccount currentAccount1=new CurrentAccount("iban1",1500);
        CurrentAccount currentAccount2= Mockito.mock(CurrentAccount.class);
        Mockito.when(currentAccount2.getBalance()).thenReturn(500D);
        Mockito.when(currentAccount2.deposit(500d)).thenReturn(true);
        boolean resultTransfer=currentAccount1.transfer(currentAccount2,100);
        Assert.assertTrue(resultTransfer);
        Assert.assertEquals(1400,currentAccount1.getBalance(),0.0);
        Assert.assertEquals(500,currentAccount2.getBalance(),0.0);
        Mockito.verify(currentAccount2,Mockito.times(1)).getBalance();
    }

}
