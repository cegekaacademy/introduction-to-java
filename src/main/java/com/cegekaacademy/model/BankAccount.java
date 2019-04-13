package com.cegekaacademy.model;

public abstract class BankAccount {

    private String iban;
    private double balance;

    public BankAccount(String iban, double balance) {
        this.iban = iban;
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        this.balance = this.balance - amount;
        return true;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }

        this.balance = this.balance + amount;
        return true;
    }

    public boolean transfer(BankAccount destination, double amount){

        double sursaBalanta=this.balance;
        double destinatieBalanta=destination.balance;

        if (destination.deposit(amount) && this.withdraw(amount))
            return true;

        else{
            this.setBalance(sursaBalanta);
            destination.setBalance(destinatieBalanta);
            return false;
        }


    }
}
