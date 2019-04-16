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

        double totalBalance = 0.0D;
        for(BankAccount account : bankAccounts)
        {
            totalBalance += account.getBalance();
        }

        return totalBalance;
    }

    public double calculateSeniorityBonus() {
        if (person == null || this.bankAccounts == null || this.bankAccounts.isEmpty()) {
            throw new IllegalStateException("Client not found");
        }

        if (person.calculateAge() < 50) {
            return 0;
        }

        int bonus = 0;
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount instanceof DepositAccount) {
                bonus += 0.05 * bankAccount.getBalance();
            } else {
                bonus += 0.02 * bankAccount.getBalance();
            }
        }

        return bonus;
    }

}
