package com.cegekaacademy.model;

public class DepositAccount extends BankAccount {

    public void addInterest(int numberOfMonths) {
        setBalance(getBalance() * 0.2 * numberOfMonths);
    }

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
