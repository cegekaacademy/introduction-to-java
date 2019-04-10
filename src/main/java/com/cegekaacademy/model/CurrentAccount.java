package com.cegekaacademy.model;

public class CurrentAccount extends BankAccount {

    public CurrentAccount(String iban, double balance) {
        super(iban, balance);
    }

    public boolean withdraw(double amount) {
        if (amount > 3000) {
            return false;
        }

        return super.withdraw(amount);
    }

}
