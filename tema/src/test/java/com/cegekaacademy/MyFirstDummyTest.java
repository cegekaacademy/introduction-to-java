package com.cegekaacademy;


import com.cegekaacademy.bank.BankClient;
import com.cegekaacademy.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.omg.CORBA.Current;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyFirstDummyTest {

    @Test
    public void testCurrentAccount()
    {
        CurrentAccount currentAccount=new CurrentAccount("iban",2000);
        boolean result=currentAccount.withdraw(200);
        Assert.assertEquals(1797,currentAccount.getBalance(),0.1);
        assertTrue(result);

    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_currentAccountBalanceValidAndWithdrawAmmountInvalid_WHEN_withdraw_THEN_returnFalse()
    {
        BankClient bankClient=new BankClient(null,new ArrayList<>());
        bankClient.calculateSeniorityBonus();
    }

    @Test
    public void GIVEN_accountsAndPersonValid_WHEN_calculateSeniorityBonus_THEN_returnBonus()
    {
        CurrentAccount currentAccount= Mockito.mock(CurrentAccount.class);
        List<BankAccount> bankAccounts =new ArrayList<>();
        bankAccounts.add(currentAccount);
        Person person=Mockito.mock(Person.class);
        BankClient bankClient=new BankClient(person,bankAccounts);

        Mockito.when(person.calculateAge()).thenReturn(51);
        Mockito.when(currentAccount.getBalance()).thenReturn(1000D);

        double result=bankClient.calculateSeniorityBonus();
        Assert.assertEquals(0.02*1000,result,0);

        Mockito.verify(person,Mockito.times(1)).calculateAge();
        Mockito.verify(currentAccount,Mockito.times(1)).getBalance();

    }

    @Test
    public void GIVEN_accountsValidAndPersonValid_WHEN_calculateSeniorityBonusDepositAccount_THEN_returnBonus()
    {
        DepositAccount depositAccount=Mockito.mock(DepositAccount.class);
        List<BankAccount> bankAccounts =new ArrayList<>();
        bankAccounts.add(depositAccount);
        Person person=Mockito.mock(Person.class);
        BankClient bankClient=new BankClient(person,bankAccounts);
        Mockito.when(person.calculateAge()).thenReturn(60);
        Mockito.when(depositAccount.getBalance()).thenReturn(2000D);
        double result=bankClient.calculateSeniorityBonus();
        Assert.assertEquals(0.05*2000,result,0);
    }


    @Test
    public void GIVEN_accountsAndPersonValid_WHEN_getTotalBalance_THEN_returnTotal()
    {
        CurrentAccount currentAccount= Mockito.mock(CurrentAccount.class);
        CurrentAccount currentAccount1= Mockito.mock(CurrentAccount.class);
        CurrentAccount currentAccount2= Mockito.mock(CurrentAccount.class);
        List<BankAccount> bankAccounts=new ArrayList<>();
        bankAccounts.add(currentAccount);
        bankAccounts.add(currentAccount1);
        bankAccounts.add(currentAccount2);

        Person person=Mockito.mock(Person.class);
        BankClient bankClient=new BankClient(person,bankAccounts);
        Mockito.when(currentAccount.getBalance()).thenReturn(2000D);
        Mockito.when(currentAccount1.getBalance()).thenReturn(1500D);
        Mockito.when(currentAccount2.getBalance()).thenReturn(1500D);

        double total=bankClient.getTotalBalance();

        Assert.assertEquals(5000,total,0);

    }

    @Test(expected=IllegalStateException.class)
    public void GIVEN_accountsInvalidAndPersonValid_WHEN_getTotalBalance_THEN_returnTotal()
    {
        List<BankAccount> bankAccounts=new ArrayList<>();
        Person person=Mockito.mock(Person.class);
        BankClient bankClient=new BankClient(person, bankAccounts);
        bankClient.getTotalBalance();
    }

   @Test
    public void GIVEN_personValid_WHEN_calculateAge_THEN_returnAge()
   {
       Address address=Mockito.mock(Address.class);
       Person person=new Person("aaa","2970929460025",address);
       int varsta=person.calculateAge();
       assertEquals(21,varsta);

       Person person2=new Person("aaa","5090212601025",address);
       int varsta2=person2.calculateAge();
       assertEquals(10,varsta2);

   }

   @Test
    public void testDepositAccount()
   {
       DepositAccount depositAccount=new DepositAccount("cont",5000D);
       boolean result=depositAccount.deposit(1000);
       assertEquals(6000,depositAccount.getBalance(),0.1);
       assertTrue(result);

       boolean resultN=depositAccount.deposit(-100);
       assertFalse(resultN);
   }

    @Test
    public void GIVEN_accountsAndPersonValidAge40_WHEN_calculateSeniorityBonus_THEN_returnBonus()
    {
        CurrentAccount currentAccount= Mockito.mock(CurrentAccount.class);
        List<BankAccount> bankAccounts =new ArrayList<>();
        bankAccounts.add(currentAccount);
        Person person=Mockito.mock(Person.class);
        BankClient bankClient=new BankClient(person,bankAccounts);

        Mockito.when(person.calculateAge()).thenReturn(40);
        Mockito.when(currentAccount.getBalance()).thenReturn(1000D);

        double result=bankClient.calculateSeniorityBonus();
        Assert.assertEquals(0,result,0);

    }


    @Test(expected = IllegalStateException.class)
    public void GIVEN_accountsInvalidAndPersonValid_WHEN_calculateSeniorityBonus_THEN_returnBonus()
    {
        CurrentAccount currentAccount= Mockito.mock(CurrentAccount.class);
        Person person=Mockito.mock(Person.class);
        BankClient bankClient=new BankClient(person,null);
        bankClient.calculateSeniorityBonus();
    }

    @Test
    public void testTransfer()
    {
        CurrentAccount currentAccount=new CurrentAccount("cont",1000);
        CurrentAccount currentAccount2=new CurrentAccount("cont2",1000);
        boolean result=currentAccount.transfer(currentAccount2,10);
        assertEquals(990,currentAccount.getBalance(),0.1);
        assertEquals(1010,currentAccount2.getBalance(),0);
        assertTrue(result);

    }









}
