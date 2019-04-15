package com.cegekaacademy.model;

public class CurrentAccount extends BankAccount {

    public CurrentAccount(String iban, double balance) {
        super(iban, balance);
    }

    public boolean withdraw(double amount) {
        return !(amount > 3000) && super.withdraw(amount);
    }

    @Override
    public boolean transfer(BankAccount destination, double amount) {
        return super.transfer(destination, amount);
    }
}
