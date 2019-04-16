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

import java.util.ArrayList;
import java.util.List;

public class DepositAccountTest {

    @Test
    public void GIVEN_validDepositAccount_WHEN_deposit_THEN_amountTooSmall() {

        DepositAccount depositAccount = Mockito.mock(DepositAccount.class);

        Mockito.when(depositAccount.getBalance()).thenReturn(200D);
        Mockito.when(depositAccount.getInterest()).thenReturn(0.2);

        boolean result = depositAccount.deposit(100D);

        Assert.assertEquals(false, result);

        Mockito.verify(depositAccount, Mockito.times(0)).getBalance();
    }

    @Test
    public void GIVEN_validDepositAccount_WHEN_calculateYearlyInterestLeft_THEN_interestLeft() {

        DepositAccount depositAccount = Mockito.mock(DepositAccount.class);

        Mockito.when(depositAccount.getBalance()).thenReturn(500D);

        double result = depositAccount.calculateYearlyInterestLeft();

        System.out.println(result);
        //Assert.assertEquals(900, result, 0);
        Mockito.verify(depositAccount, Mockito.times(0)).getBalance();
    }
}
