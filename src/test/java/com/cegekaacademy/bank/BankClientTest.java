package com.cegekaacademy.bank;


import com.cegekaacademy.bank.BankClient;
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
    public void GIVEN_noAccountsAndNoPerson_WHEN_calculateSeniorityBonus_THEN_returnThrowException() {
        BankClient bankClient = new BankClient(null, new ArrayList<>());

        bankClient.calculateSeniorityBonus();
    }

    @Test
    public void GIVEN_accountsAndPersonValid_WHEN_calculateSeniorityBonus_THEN_returnBonus() {
        CurrentAccount currentAccount = Mockito.mock(CurrentAccount.class);
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(currentAccount);
        Person person = Mockito.mock(Person.class);

        BankClient bankClient = new BankClient(person, bankAccounts);
        Mockito.when(person.calculateAge()).thenReturn(49);

        double result = bankClient.calculateSeniorityBonus();

        Assert.assertEquals(0, result, 0);

        Mockito.verify(person, Mockito.times(1)).calculateAge();
    }

    @Test
    public void GIVEN_accountsAndValidPerson_WHEN_getTotalBalance_THEN_returnBalance() {
        CurrentAccount currentAccount = Mockito.mock(CurrentAccount.class);
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(currentAccount);
        bankAccounts.add(currentAccount);
        Person person = Mockito.mock(Person.class);
        BankClient bankClient = new BankClient(person, bankAccounts);

        Mockito.when(currentAccount.getBalance()).thenReturn(4000d);

        double balanceResult = bankClient.getTotalBalance();
        Assert.assertEquals(8000d, balanceResult, 0.01d);

        Mockito.verify(currentAccount, Mockito.times(2)).getBalance();
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_noAccounts_WHEN_getTotalBalance_THEN_returnThrowException() {
        BankClient bankClient = new BankClient(null, null);
        bankClient.getTotalBalance();
    }
}
