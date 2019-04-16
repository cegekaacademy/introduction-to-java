package com.cegekaacademy;

import com.cegekaacademy.model.BankAccount;
import com.cegekaacademy.model.CurrentAccount;
import com.cegekaacademy.model.DepositAccount;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class BankAccountTest {

    @Test
    public void testTransferWithNoDestination() {
        BankAccount account1 = Mockito.mock(BankAccount.class);
        BankAccount account2 = null;
        Assert.assertEquals(false,account1.transfer(account2,300));
    }

    @Test
    public void testTransferWithAmountZero() {
        BankAccount account1 = Mockito.mock(BankAccount.class);
        BankAccount account2 = Mockito.mock(BankAccount.class);
        Assert.assertEquals(false, account1.transfer(account2,0));
    }

    @Test
    public void testTransferWithAmountGreatherThanBalance() {
        BankAccount account1 = Mockito.mock(BankAccount.class);
        BankAccount account2 = new CurrentAccount("Gfdgfsd",400);
        Assert.assertEquals(false, account2.transfer(account1,401));
    }

    @Test
    public void tesTransferWithMaximumAmountAllowed() {
        BankAccount account1 = new DepositAccount("fdsfdsfdsa", 500);
        BankAccount account2 = new CurrentAccount("gdfgdfgds", 400);
        account1.transfer(account2,500);
        Assert.assertEquals(0,account1.getBalance(),0);
        Assert.assertEquals(900,account2.getBalance(),0);
    }

    @Test
    public void testTransferWithMinimumAmountAllowed() {
        BankAccount account1 = new DepositAccount("fdsfdsfdsa", 500);
        BankAccount account2 = new CurrentAccount("gdfgdfgds", 400);
        account1.transfer(account2,1);
        Assert.assertEquals(499,account1.getBalance(),0);
        Assert.assertEquals(401,account2.getBalance(),0);
    }
}
