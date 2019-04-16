package com.cegekaacademy;


import com.cegekaacademy.bank.BankClient;
import com.cegekaacademy.model.BankAccount;
import com.cegekaacademy.model.CurrentAccount;
import com.cegekaacademy.model.DepositAccount;
import com.cegekaacademy.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.plugins.MockitoPlugins;

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

    @Test
    public void GIVEN_validBankClient_WHEN_getTotalBalance_THEN_returnBalance() {
        CurrentAccount currentAccount = Mockito.mock(CurrentAccount.class);
        DepositAccount depositAccount = Mockito.mock(DepositAccount.class);
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(currentAccount);
        bankAccounts.add(depositAccount);
        Person person = Mockito.mock(Person.class);

        BankClient bankClient = new BankClient(person,bankAccounts);

        Mockito.when(currentAccount.getBalance()).thenReturn(100D);
        Mockito.when(depositAccount.getBalance()).thenReturn(200D);

        double result = bankClient.getTotalBalance();

        Assert.assertEquals(300D, result, 0);

        Mockito.verify(depositAccount, Mockito.times(1)).getBalance();
    }

    @Test
    public void GIVEN_validBankClient_WHEN_calculateYoungBonus_THEN_returnTheBonus() {
        CurrentAccount currentAccount = Mockito.mock(CurrentAccount.class);
        DepositAccount depositAccount = Mockito.mock(DepositAccount.class);
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(currentAccount);
        bankAccounts.add(depositAccount);
        Person person = Mockito.mock(Person.class);

        BankClient bankClient = new BankClient(person,bankAccounts);

        Mockito.when(currentAccount.getBalance()).thenReturn(100D);
        Mockito.when(depositAccount.getBalance()).thenReturn(200D);
        Mockito.when(person.calculateAge()).thenReturn(23);

        double result = bankClient.calculateYoungBonus();

        Assert.assertEquals(7, result, 0);

        Mockito.verify(depositAccount, Mockito.times(1)).getBalance();
    }
}
