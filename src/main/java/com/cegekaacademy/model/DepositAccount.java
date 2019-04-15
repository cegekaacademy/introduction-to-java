package com.cegekaacademy.model;

public class DepositAccount extends BankAccount {
    //to do dobanda
    private double interest;

    public DepositAccount(String iban, double balance,double interest) {
        super(iban, balance);
        this.interest = interest;
    }
    public void setInterest(double interest){
        this.interest = interest;
    }
    public double getInterest (){ return interest;}

    public boolean deposit(double amount) {
        if (amount < 200) {
            return false;
        }

        return super.deposit(amount);
    }
}
