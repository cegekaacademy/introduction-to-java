package com.cegekaacademy.model;

public class DepositAccount extends BankAccount {

    private double dobanda;

    public DepositAccount(String iban, double balance, double dobanda) {
        super(iban, balance);
        this.dobanda = dobanda;
    }

    public boolean deposit(double amount) {
        if (amount < 200) {
            return false;
        }
        return super.deposit(amount);
    }
}
