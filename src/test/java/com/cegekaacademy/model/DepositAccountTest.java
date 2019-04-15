package com.cegekaacademy.model;

import com.cegekaacademy.model.DepositAccount;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class DepositAccountTest {

    @Test
    public void GIVEN_amountUnderTheLimit_WHEN_deposit_THEN_returnFalse() {
        DepositAccount depositAccount = Mockito.mock(DepositAccount.class);
        Assert.assertFalse(depositAccount.deposit(190));
    }
}
