package com.cegekaacademy.model;

public class DepositAccount extends BankAccount {

    private double interestPercent;
    private int durationInMonth;


    public DepositAccount(String iban, double balance, double interestPercent, int durationInMonth) {
        super(iban, balance);
        this.interestPercent = interestPercent;
        this.durationInMonth = durationInMonth;
    }

    public double getInterestPercent() {
        return interestPercent;
    }

    public void setInterestPercent(double interestProcent) {
        this.interestPercent = interestProcent;
    }

    public int getDurationInMonth() {
        return durationInMonth;
    }

    public void setDurationInMonth(int durationInMonth) {
        this.durationInMonth = durationInMonth;
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

    public double getInterest(){
        if(getBalance()<0 && interestPercent <0)
            return 0;

     return (this.getBalance()*interestPercent*durationInMonth/12);
    }
    public double getInterestAfterTaxDeduction(){
        double interest=getInterest();
        return interest-0.01*interest;
    }

    public void addInterest(){

        this.setBalance(this.getBalance()+getInterestAfterTaxDeduction());
    }
}
