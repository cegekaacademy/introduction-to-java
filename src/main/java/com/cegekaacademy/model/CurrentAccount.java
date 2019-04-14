package com.cegekaacademy.model;

import com.cegekaacademy.exceptions.TransferException;

public class CurrentAccount extends BankAccount {

    public CurrentAccount(String iban, double balance) {
        super(iban, balance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 3000) {
            return false;
        }

        return super.withdraw(amount);
    }

    @Override
    public boolean transfer(BankAccount destination, double amount) throws TransferException {

        if(amount > 1000){
            return false;
        }


        return super.transfer(destination, amount);
    }
}
