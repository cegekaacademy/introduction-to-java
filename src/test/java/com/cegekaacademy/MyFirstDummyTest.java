package com.cegekaacademy;


import com.cegekaacademy.bank.BankClient;
import com.cegekaacademy.model.BankAccount;
import com.cegekaacademy.model.CurrentAccount;
import com.cegekaacademy.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.omg.CORBA.Current;

import java.util.ArrayList;
import java.util.List;

public class MyFirstDummyTest {

    @Test
    public void testCurrentAccount() {

        CurrentAccount currentAccount =
                new CurrentAccount("iban", 2000);

        boolean result = currentAccount.withdraw(200);

        Assert.assertEquals(1800, currentAccount.getBalance(), 0);
        Assert.assertTrue(result);
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_currentAccountValidAndWithdrawAmountInvalid_WHEN_withdraw_THEN_returnFalse() {

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
        Mockito.when(person.calculateAge()).thenReturn(55);
        Mockito.when(currentAccount.getBalance()).thenReturn(1000D);

        double result = bankClient.calculateSeniorityBonus();

        Assert.assertEquals(20, result, 0);
        Mockito.verify(person, Mockito.times(1)).calculateAge();
        Mockito.verify(currentAccount, Mockito.times(1)).getBalance();

    }


}
