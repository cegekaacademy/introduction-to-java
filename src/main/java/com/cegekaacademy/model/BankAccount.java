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

    public void setBalance(double balance) throws IllegalStateException {
        if (balance == 0) {
            throw new IllegalStateException();
        }
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

        if (destination == null) {
            throw new IllegalStateException("Destination required");
        }

        if (amount < 1 || amount > this.balance) {
            throw new IllegalStateException("Incorrect amount");
        }

        this.balance -= amount;
        destination.setBalance(destination.getBalance() + amount);

        return true;
    }
}
