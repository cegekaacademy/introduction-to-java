package com.cegekaacademy.model;

public class CurrentAccount extends BankAccount {
//adaugat dobanda

    private static final int dobanda=3;

    public CurrentAccount(String iban, double balance) {
        super(iban, balance);
    }

    public boolean withdraw(double amount) {

        if (amount > 3000 ) {
            return false;
        }

        return super.withdraw(amount+dobanda);
    }

    public boolean transfer(BankAccount destination, double amount) {

        if(amount<100 || amount>50000)
        {
            return false;
        }
       return super.transfer(destination, amount);
    }

}
