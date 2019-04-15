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

    public boolean transfer(BankAccount destination, double amount) {
        if (destination == null)
            throw new IllegalStateException("Invalid destination");
        if (this.balance >= amount) {
            if (destination instanceof DepositAccount) {
                if (amount <= 200)
                    destination.setBalance(destination.getBalance() + amount);
                else
                    throw new IllegalStateException("The sum is over the limit of this bank account type!");
            } else destination.setBalance(destination.getBalance() + amount);
        } else
            throw new IllegalStateException("Not enough money!");
        return true;
    }
}
