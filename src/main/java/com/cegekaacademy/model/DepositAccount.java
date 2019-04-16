package com.cegekaacademy.model;

public class DepositAccount extends BankAccount {

    private double interest;

    public DepositAccount(String iban, double balance, double interest) {
        super(iban, balance);
        this.interest = interest;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    @Override
    public boolean deposit(double amount) {
        if (amount < 200) {
            return false;
        }

        return super.deposit(amount);
    }
}
