package com.cegekaacademy.model;

public class DepositAccount extends BankAccount {

    private double interest;
    private int periodOfTime;

    public DepositAccount(String iban, double balance, double interest, int periodOfTime) {
        super(iban, balance);
        this.interest = interest;
        this.periodOfTime = periodOfTime;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getPeriodOfTime() {
        return periodOfTime;
    }

    public void setPeriodOfTime(int periodOfTime) {
        this.periodOfTime = periodOfTime;
    }

    @Override
    public boolean deposit(double amount) {
        if (amount < 200) {
            return false;
        }

        return super.deposit(amount);
    }

    public double getRate(){
        if(this.getBalance() <= 0 || this.interest<=0 || this.periodOfTime <= 0){
            return 0;
        }

        return this.getBalance()*this.interest*this.periodOfTime/12;
    }

    public void addInterestRate(){

        this.setBalance(this.getRate() + this.getBalance());
    }
}
