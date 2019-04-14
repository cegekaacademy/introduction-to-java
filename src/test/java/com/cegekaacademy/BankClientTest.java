package com.cegekaacademy;


import com.cegekaacademy.bank.BankClient;
import com.cegekaacademy.exceptions.BalanceException;
import com.cegekaacademy.model.BankAccount;
import com.cegekaacademy.model.CurrentAccount;
import com.cegekaacademy.model.DepositAccount;
import com.cegekaacademy.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class BankClientTest {

    @Test(expected = IllegalStateException.class)
    public void GIVEN_noAccountsAndNoPerson_WHEN_calculateSeniorityBonus_THEN_returnThrowException(){
        BankClient bankClient = new BankClient(null,new ArrayList<>());

        bankClient.calculateSeniorityBonus();
    }

    @Test
    public void GIVEN_accountsAndPersonValid_WHEN_calculateSeniorityBonus_THEN_returnBonus(){
        CurrentAccount currentAccount = Mockito.mock(CurrentAccount.class);
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(currentAccount);
        Person person = Mockito.mock(Person.class);

        BankClient bankClient = new BankClient(person,bankAccounts);

        Mockito.when(person.calculateAge()).thenReturn(50);
        Mockito.when(currentAccount.getBalance()).thenReturn(1000D);

        double result = bankClient.calculateSeniorityBonus();

        Assert.assertEquals(20,result,0);

        Mockito.verify(person, Mockito.times(1)).calculateAge();
        Mockito.verify(currentAccount, Mockito.times(1)).getBalance();
    }


    @Test
    public void GIVEN_accountsValidAndPersonUnder50_WHEN_calculateSeniorityBonus_THEN_return0(){
        CurrentAccount currentAccount = Mockito.mock(CurrentAccount.class);
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(currentAccount);
        Person person = Mockito.mock(Person.class);

        BankClient bankClient = new BankClient(person,bankAccounts);

        Mockito.when(person.calculateAge()).thenReturn(49);
        double result = bankClient.calculateSeniorityBonus();

        Assert.assertEquals(0,result,0);
        Mockito.verify(person, Mockito.times(1)).calculateAge();
    }

    @Test
    public void GIVEN_depositAccountAndPersonValid_WHEN_calculateSeniorityBonus_THEN_returnBonus(){
        DepositAccount depositAccount = Mockito.mock(DepositAccount.class);
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(depositAccount);
        Person person = Mockito.mock(Person.class);

        BankClient bankClient = new BankClient(person,bankAccounts);

        Mockito.when(person.calculateAge()).thenReturn(50);
        Mockito.when(depositAccount.getBalance()).thenReturn(1000D);

        double result = bankClient.calculateSeniorityBonus();

        Assert.assertEquals(50,result,0);

        Mockito.verify(person, Mockito.times(1)).calculateAge();
        Mockito.verify(depositAccount, Mockito.times(1)).getBalance();
    }

    @Test(expected = BalanceException.class)
    public void GIVEN_noAccountsAndNoPerson_WHEN_getTotalBalance_THEN_returnThrowException() throws BalanceException {
        BankClient bankClient = new BankClient(null,new ArrayList<>());

        bankClient.getTotalBalance();
    }

    @Test
    public void GIVEN_accountsAndPersonValid_WHEN_getTotalBalance_THEN_returnTotal() throws BalanceException {
        DepositAccount depositAccount = Mockito.mock(DepositAccount.class);
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(depositAccount);
        Person person = Mockito.mock(Person.class);

        BankClient bankClient = new BankClient(person, bankAccounts);

        Mockito.when(depositAccount.getBalance()).thenReturn(1000D);
        double totalBalance = bankClient.getTotalBalance();

        Assert.assertEquals(1000, totalBalance, 0.01);
        Mockito.verify(depositAccount, Mockito.times(1)).getBalance();
    }
}
