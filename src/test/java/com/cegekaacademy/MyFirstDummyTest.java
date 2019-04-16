package com.cegekaacademy;


import com.cegekaacademy.bank.BankClient;
import com.cegekaacademy.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MyFirstDummyTest {


    @Test
    public void testCurrentAccount()
    {
        CurrentAccount currentAccount = new CurrentAccount("iban", 2000);

        boolean result =  currentAccount.withdraw(200);

        Assert.assertEquals(1800, currentAccount.getBalance(),0.1);
        Assert.assertTrue(result);

    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_currentAccountValidAndWithdrawAmountInvalid_WHEN_withdraw_THEN_return_false()
    {
        CurrentAccount currentAccount = Mockito.mock(CurrentAccount.class);
        BankClient bankClient = new BankClient(null, new ArrayList<>());

        bankClient.calculateSeniorityBonus();

    }


    @Test
    public void GIVEN_accountsAndPersonValid_WHEN_calculateSeniorityBonus_THEN_returnBonus()
    {
        CurrentAccount currentAccount = Mockito.mock(CurrentAccount.class);

        List<BankAccount> bankAccounts = new ArrayList<>();

        bankAccounts.add(currentAccount);
        Person pers = Mockito.mock(Person.class);


        BankClient bankClient = new BankClient(pers, bankAccounts);
        Mockito.when(pers.calculateAge()).thenReturn(51);
        Mockito.when(currentAccount.getBalance()).thenReturn(1000D);


        double result = bankClient.calculateSeniorityBonus();

        Assert.assertEquals(0.02*1000, result,0);

        Mockito.verify(pers, Mockito.times(1)).calculateAge();
        Mockito.verify(currentAccount, Mockito.times(1)).getBalance();

    }

    //check age when sexId != 1/2 or 5/6
    //check dobanda for seniority
    //check dobanda for regular account

    //check transfer both cases - true and false

    //total balance of BankClient


    @Test
    public void GIVEN_validPerson_WHEN_sexIdNotValid_THEN_ageZero()
    {

        Address adr = Mockito.mock(Address.class);
        Person pers = new Person("name", "8455656545676", adr);
        int actualAge = pers.calculateAge();

        Assert.assertEquals(0, actualAge);
    }

    @Test
    public void GIVEN_validPerson_WHEN_sexIdValidYearValidMonthNotValid_THEN_ageLessThanZero()
    {

        Address adr = Mockito.mock(Address.class);
        Person pers = new Person("name", "2455656545676", adr);
        int actualAge = pers.calculateAge();

        Assert.assertEquals(-1, actualAge);
    }


    @Test
    public void GIVENvalidPerson_WHENvalidMonthNotValidDay_THEN_ageEqualsMinus3()
    {

        Address adr = Mockito.mock(Address.class);

        Person pers = new Person("name", "2961244132925", adr);
        int actualAge = pers.calculateAge();


        Assert.assertEquals(-3, actualAge);
    }


    @Test
    public void GIVENvalidPerson_WHENvalidBirthday_THEN_ageGreaterThanZero()
    {
        //current day > birth day
        Address adr = Mockito.mock(Address.class);

        Person pers = new Person("name", "2961213132925", adr);
        int actualAge = pers.calculateAge();


        Assert.assertEquals(22, actualAge);
    }


    @Test
    public void GIVENvalidPerson_WHENvalidBirthday_THEN_ageGreaterThanZero2()
    {
        //current day < birth day
        Address adr = Mockito.mock(Address.class);

        Person pers = new Person("name", "2960410132925", adr);
        int actualAge = pers.calculateAge();


        Assert.assertEquals(23, actualAge);
    }



    @Test
    public void CheckTransfer()
    {
        //2 bank accounts

        BankAccount bankAccount = new CurrentAccount("iban1", 7000F);
        BankAccount transferBankAccount = new CurrentAccount("iban2", 4000F);

        bankAccount.transfer(transferBankAccount, 3400D);

        Assert.assertEquals(transferBankAccount.getBalance(), 7400D, 0);

    }

    @Test
    public void CheckTransferWithdraw()
    {
        //2 bank accounts

        BankAccount bankAccount = new CurrentAccount("iban1", 7000F);
        BankAccount transferBankAccount = new CurrentAccount("iban2", 4000F);

        bankAccount.transfer(transferBankAccount, 3400D);

        Assert.assertEquals(bankAccount.getBalance(), 3600F, 0);

    }



    @Test
    public void GetTotalBalanceDepositAndRegular(){

       CurrentAccount currentAccount = Mockito.mock(CurrentAccount.class);
       DepositAccount depositAccount = Mockito.mock(DepositAccount.class);

       Person pers = Mockito.mock(Person.class);

        List<BankAccount> accounts = new ArrayList<>();

        accounts.add(currentAccount);
        accounts.add(depositAccount);
       BankClient bankClient = new BankClient(pers,accounts);


       Mockito.when(currentAccount.getBalance()).thenReturn(3000D);
       Mockito.when(depositAccount.getBalance()).thenReturn(4000D);

       Assert.assertEquals(7000F, bankClient.getTotalBalance(), 0);

       Mockito.verify(currentAccount, Mockito.times(1)).getBalance();
       Mockito.verify(depositAccount, Mockito.times(1)).getBalance();

    }


    @Test
    public void Dobanda()
    {
        CurrentAccount currentAccount = new CurrentAccount("ibann", 4000D);

        currentAccount.setDobanda(20);


        Assert.assertEquals(4800D,currentAccount.getBalance(), 0);


    }




}
