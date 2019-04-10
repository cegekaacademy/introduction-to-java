package com.cegekaacademy.bank;

import com.cegekaacademy.model.BankAccount;
import com.cegekaacademy.model.DepositAccount;
import com.cegekaacademy.model.Person;

import java.util.List;

public class BankClient implements BankCalculator {

    private Person person;
    private List<BankAccount> bankAccounts;

    public BankClient(Person person, List<BankAccount> bankAccounts) {
        this.person = person;
        this.bankAccounts = bankAccounts;
    }

    public double getTotalBalance() {
        // TODO
        return 0;
    }

    public double calculateSeniorityBonus() {
        if (this.person == null || this.bankAccounts == null || this.bankAccounts.size() == 0) {
            return -1;
        }

        int age = person.calculateAge();
        double balance = 0;
        if (age > 50) {
            for (BankAccount bankAccount : this.bankAccounts) {
                balance += (bankAccount instanceof DepositAccount ? 0.05 : 0.01)
                        * bankAccount.getBalance();
            }
        }

        return balance;
    }

}
