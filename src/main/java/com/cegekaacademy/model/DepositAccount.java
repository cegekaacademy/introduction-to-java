package com.cegekaacademy.model;

public class DepositAccount extends BankAccount {

    //TODO add interest

    public DepositAccount(String iban, double balance) {
        super(iban, balance);
    }

    @Override
    public boolean deposit(double amount) {

        if (amount < 200) {
            return false;
        }

        return super.deposit(amount);
    }
}
