package com.cegekaacademy.model;

public class CurrentAccount extends BankAccount {


    //TODO dobanda

    public void setDobanda(int percentage)
    {
        super.setBalance(this.getBalance() + this.getBalance() * (percentage)/100);
    }


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
