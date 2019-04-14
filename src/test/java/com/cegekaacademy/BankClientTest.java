package com.cegekaacademy;


import com.cegekaacademy.bank.BankClient;
import com.cegekaacademy.exception.BankAccountsNullException;
import com.cegekaacademy.model.BankAccount;
import com.cegekaacademy.model.CurrentAccount;
import com.cegekaacademy.model.Person;
import org.junit.Assert;
import org.junit.Test;
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

        Mockito.when(person.calculateAge()).thenReturn(49);
        //Mockito.when(currentAccount.getBalance()).thenReturn(1000D);

        double result = bankClient.calculateSeniorityBonus();

        Assert.assertEquals(0,result,0);

        Mockito.verify(person, Mockito.times(1)).calculateAge();
        //Mockito.verify(currentAccount, Mockito.times(1)).getBalance();
    }

    @Test(expected = BankAccountsNullException.class)
    public void GIVEN_noAccountsAndValidPerson_WHEN_calculateTotalBalance_THEN_returnException(){
        Person person = Mockito.mock(Person.class);
        BankClient bankClient = new BankClient(person,new ArrayList<>());
        bankClient.getTotalBalance();
    }
    @Test(expected = BankAccountsNullException.class)
    public void GIVEN_noAccountsAndInvalidPerson_WHEN_calculateTotalBalance_THEN_returnException(){
        CurrentAccount currentAccount = Mockito.mock(CurrentAccount.class);
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(currentAccount);
        BankClient bankClient = new BankClient(null,bankAccounts);
        bankClient.getTotalBalance();
    }
    @Test
    public void GIVEN_accountsAndPersonValid_WHEN_calculateTotalBalance_THEN_returnBonus(){
        CurrentAccount currentAccount1 = Mockito.mock(CurrentAccount.class);
        CurrentAccount currentAccount2 = Mockito.mock(CurrentAccount.class);
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(currentAccount1);
        bankAccounts.add(currentAccount2);
        Person person = Mockito.mock(Person.class);

        BankClient bankClient = new BankClient(person,bankAccounts);

        Mockito.when(currentAccount1.getBalance()).thenReturn(300D);
        Mockito.when(currentAccount2.getBalance()).thenReturn(500D);


        double result = bankClient.getTotalBalance();

        Assert.assertEquals(800,result,0.01);

        Mockito.verify(currentAccount1, Mockito.times(1)).getBalance();
        Mockito.verify(currentAccount2, Mockito.times(1)).getBalance();
    }

}
