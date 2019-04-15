package com.cegekaacademy;


import com.cegekaacademy.bank.BankClient;
import com.cegekaacademy.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class MyFirstDummyTest {

    CurrentAccount currentAccount;
    List<BankAccount> bankAccounts = new ArrayList<>();
    Person person;
    DepositAccount depositAccount;
    BankClient bankClient;
    BankClient bankClient2;

    @Before
    public void setUp() {
        currentAccount = Mockito.mock(CurrentAccount.class);
        bankAccounts.add(currentAccount);
        depositAccount = Mockito.mock(DepositAccount.class);
        person = Mockito.mock(Person.class);
        bankClient = new BankClient(person, bankAccounts);
        bankAccounts.add(depositAccount);
    }

    @Test
    public void test() {
        CurrentAccount currentAccount = new CurrentAccount("iban", 2000);

        currentAccount.withdraw(200);
        double expectedValue = 1800;
        Assert.assertEquals(expectedValue, currentAccount.getBalance(), 0.001);
        Assert.assertTrue(currentAccount.withdraw(200));
    }

    @Test
    public void GIVEN_accountsAndPersonValid_WHEN_calculateSeniorityBonus_THEN_returnBonus() {


        Mockito.when(person.calculateAge()).thenReturn(51);
        Mockito.when(currentAccount.getBalance()).thenReturn(1000D);

        double result = bankClient.calculateSeniorityBonus();

        Assert.assertEquals(20, result, 0);

        Mockito.verify(person, Mockito.times(1)).calculateAge();
        Mockito.verify(currentAccount, Mockito.times(1)).getBalance();
    }

    @Test
    public void GIVEN_currentAndDepositAccountsValidAndPersonInvalid_WHEN_calculateSeniorityBonus_THEN_returnBonus() {
        Mockito.when(person.calculateAge()).thenReturn(50);
        Mockito.when(currentAccount.getBalance()).thenReturn(1000D);
        Mockito.when(depositAccount.getBalance()).thenReturn(100D);
        double result = bankClient.calculateSeniorityBonus();

        Assert.assertEquals(25D, result, 0);

        Mockito.verify(person, Mockito.times(1)).calculateAge();
        Mockito.verify(currentAccount, Mockito.times(1)).getBalance();
        Mockito.verify(depositAccount, Mockito.times(1)).getBalance();
    }

    @Test
    public void GIVEN_bankClient_WHEN_getTotalBalance_THEN_returnTotal() {
        Mockito.when(currentAccount.getBalance()).thenReturn(1000D);
        Mockito.when(depositAccount.getBalance()).thenReturn(100D);

        double result = bankClient.getTotalBalance();

        Assert.assertEquals(1100D, result, 0);
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_bankClientAndBankAccountsInvalid_WHEN_getTotalBalance_THEN_throwIllegalStateException() {
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();
        BankClient secondBankClient = new BankClient(person, bankAccounts);

        secondBankClient.getTotalBalance();
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_bankAccountWithNullPerson_WHEN_calculateSeniorityBonus_THEN_throwIllegalStateException() throws IllegalStateException {
        BankClient bankClient = new BankClient(null, bankAccounts);

        bankClient.calculateSeniorityBonus();

        Mockito.verify(person, Mockito.times(0)).calculateAge();
        for (BankAccount bankAccount : bankAccounts) {
            Mockito.verify(bankAccount, times(0)).getBalance();
        }
    }

    @Test
    public void GIVEN_currentAccount_WHEN_withdraw_returnFalse() throws IllegalStateException {
        CurrentAccount currentAccount = Mockito.mock(CurrentAccount.class);
        Assert.assertFalse(currentAccount.withdraw(4000));
        Mockito.verify(currentAccount, Mockito.times(1)).withdraw(4000);
    }

    @Test
    public void GIVEN_depositAccount_WHEN_deposit_THEN_returnFalse() {
        DepositAccount depositAccount = Mockito.mock(DepositAccount.class);
        Assert.assertFalse(depositAccount.deposit(199));
        Mockito.verify(depositAccount, Mockito.times(1)).deposit(199);
    }

    @Test
    public void GIVEN_personCorrectPID_WHEN_calculateAge_THEN_returnAge() {
        Person person = new Person("Mike", "1960318123456", Mockito.mock(Address.class));
        int age = person.calculateAge();

        Assert.assertEquals(23, age);
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_personWithWrongPID_WHEN_calculateAge_THEN_throwException() {
        Person person = new Person("Mike", "9960318123456", Mockito.mock(Address.class));
        int age = person.calculateAge();
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_bankAccountWithoutDestionation_WHEN_transfer_THEN_throwException() {
        CurrentAccount currentAccount = mock(CurrentAccount.class);

        boolean result = currentAccount.transfer(null, 100);
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_bankAccountWithIncorrectValue_WHEN_transfer_THEN_throwException() {
        BankAccount bankAccount = mock(BankAccount.class);
        BankAccount secondBankAccount = mock(BankAccount.class);

        boolean result = bankAccount.transfer(secondBankAccount, 0);
    }

    @Test
    public void GIVEN_bankAccountAndDestinationAndAmountValid_WHEN_transfer_THEN_returnTrue() {
        CurrentAccount currentAccount = mock(CurrentAccount.class);
        CurrentAccount secondCurrentAccount = mock(CurrentAccount.class);

        boolean result = currentAccount.transfer(secondCurrentAccount, 100);

        Assert.assertTrue(result);
    }
}
