package com.cegekaacademy.model;

public class DepositAccount extends BankAccount {

    private float interestRate;
    private int depositPeriod;

    public DepositAccount(String iban, double balance) {
        super(iban, balance);
    }

    public DepositAccount(String iban, double balance, float interestRate, int depositPeriod) {
        super(iban, balance);
        this.interestRate = interestRate;
        this.depositPeriod = depositPeriod;
    }

    @Override
    public boolean deposit(double amount) {
        if (amount < 200) {
            return false;
        }

        return super.deposit(amount);
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public int getDepositPeriod() {
        return depositPeriod;
    }

    public void setDepositPeriod(int depositPeriod) {
        this.depositPeriod = depositPeriod;
    }

    public double calculateRate(){

        if(this.getBalance() <= 0 || this.interestRate <= 0){
            return 0;
        }

        return this.getBalance() * this.interestRate * this.depositPeriod / 12;
    }

    public void addInterestToBalance(){

        this.setBalance(this.getBalance() + this.calculateRate());
    }
}
