package com.cegekaacademy.model;

public class DepositAccount extends BankAccount {
//TODO ADAUGA DOBANDA
    float interesetRate;
    int period;
    public DepositAccount(String iban, double balance,float rate,int period) {
        super(iban, balance);
        this.interesetRate=rate;
        this.period=period;
    }

    public float getInteresetRate() {
        return interesetRate;
    }

    public void setInteresetRate(float interesetRate) {
        this.interesetRate = interesetRate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
    public float determinateRate(){
        if(interesetRate<0||period<12)
        {
            throw  new IllegalStateException();
        }
        double result=0;
        if(this.getBalance()<0)
        {
           return 0;
        }
        result= (this.getBalance()*this.interesetRate*this.period/12);
        this.setBalance(this.getBalance()+result);
        return (float) result;
    }

    public boolean deposit(double amount) {
        if (amount < 200) {
            return false;
        }

        return super.deposit(amount);
    }
}
