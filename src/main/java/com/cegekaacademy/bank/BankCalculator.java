package com.cegekaacademy.bank;

import com.cegekaacademy.exception.GetTotalBalanceException;

public interface BankCalculator {

    double getTotalBalance() throws GetTotalBalanceException;

    double calculateSeniorityBonus();
}
