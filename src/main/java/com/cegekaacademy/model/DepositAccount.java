package com.cegekaacademy.model;

import java.util.Calendar;

public class DepositAccount extends BankAccount {

    double interest = 0.2;

    public DepositAccount(String iban, double balance) {
        super(iban, balance);
    }


    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double calculateYearlyInterestLeft(){
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);

        System.out.println("luna curenta " + currentMonth);
        System.out.println((0.2 * this.getBalance() ) * (12 - currentMonth));
        return ( 0.2 * this.getBalance() ) * (12 - currentMonth);
    }

    @Override
    public boolean deposit(double amount) {
        /*if (amount < 200D) {
            return false;
        }*/
        System.out.print("adaug in cont");
        return super.deposit(amount /*+ amount * interest*/);
    }
}
