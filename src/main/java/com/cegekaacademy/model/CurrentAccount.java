package com.cegekaacademy.model;

public class CurrentAccount extends BankAccount {

    public CurrentAccount(String iban, double balance) {
        super(iban, balance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 3000) {
            return false;
        }

        return super.withdraw(amount);
    }

    public boolean transfer(BankAccount destination, double amount) {
        if (amount > 3000 || amount < 0) {
            throw new IllegalStateException("The sum is over the limit of this bank account type!");
        } else return super.transfer(destination, amount);
    }
}