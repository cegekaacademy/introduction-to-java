package com.cegekaacademy;


import com.cegekaacademy.bank.BankClient;
import com.cegekaacademy.model.BankAccount;
import com.cegekaacademy.model.CurrentAccount;
import com.cegekaacademy.model.DepositAccount;
import com.cegekaacademy.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class BankClientTest {

    @Test(expected = IllegalStateException.class)
    public void GIVEN_noAccountsAndNoPerson_WHEN_creatingNewBankClient_THEN_returnThrowException(){
        BankClient bankClient = new BankClient(null,new ArrayList<>());
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

    @Test(expected = IllegalStateException.class)
    public void GIVEN_oneNullAccount_WHEN_creatingBankClient_THEN_returnThrowException() {
        CurrentAccount currentAccount = null;
        DepositAccount depositAccount = new DepositAccount("fdsafdsafsda", 500);
        Person person = Mockito.mock(Person.class);
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(depositAccount);
        bankAccounts.add(currentAccount);
        BankClient client = new BankClient(person,bankAccounts);
    }

    @Test
    public void GIVEN_twoValidAccounts_WHEN_getTotalBalance_THEN_returnTotalBalance() {
        CurrentAccount currentAccount = new CurrentAccount("gfdgdfgds", 400);
        DepositAccount depositAccount = new DepositAccount("gfdsgdsf",600);
        Person person = Mockito.mock(Person.class);
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(depositAccount);
        bankAccounts.add(currentAccount);
        BankClient client = new BankClient(person,bankAccounts);
        Assert.assertEquals(1000,client.getTotalBalance(),0.001);
    }
}
