package com.cegekaacademy.bank;

import com.cegekaacademy.exceptions.BalanceException;

public interface BankCalculator {

    double getTotalBalance() throws BalanceException;

    double calculateSeniorityBonus();
}
