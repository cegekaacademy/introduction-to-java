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
        // TODO implement me
        if(destination == null || this.getIban().equals(destination.getIban())){
            throw new IllegalStateException("Bank account not found");
        }
        if (amount<=0 || amount> this.balance)
            return false;
        if (destination instanceof DepositAccount && amount < 200)
            return false;
        if (this instanceof CurrentAccount && amount > 3000)
            return false;

            this.balance = this.balance - amount;
            destination.deposit(amount);
            return true;


    }
}
