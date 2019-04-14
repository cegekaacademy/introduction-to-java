package com.cegekaacademy.model;

import com.cegekaacademy.exceptions.TransferException;

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

    public boolean transfer(BankAccount destination, double amount) throws TransferException {
        if(destination == null || amount == 0){
            throw new TransferException();
        }

        double destinationBalance = destination.getBalance();
        double currentBalance = this.getBalance();

        if(this.withdraw(amount) && destination.deposit(amount)){
            return true;
        }
        else{
            this.setBalance(currentBalance);
            destination.setBalance(destinationBalance);

            return false;
        }

    }
}
