package com.cegekaacademy.bank;

import com.cegekaacademy.model.BankAccount;
import com.cegekaacademy.model.DepositAccount;
import com.cegekaacademy.model.Person;

import java.util.List;

public class BankClient implements BankCalculator {

    private Person person;
    private List<BankAccount> bankAccounts;

    public BankClient(Person person, List<BankAccount> bankAccounts) {
        if (person == null || bankAccounts == null || bankAccounts.isEmpty()) {
            throw new IllegalStateException("Client not found");
        }
        for(BankAccount account : bankAccounts) {
            if(account == null) {
                throw new IllegalStateException("At least one bank account not found");            }
        }
        this.person = person;
        this.bankAccounts = bankAccounts;
    }

    @Override
    public double getTotalBalance() {
        double totalBalance = 0;
        for(BankAccount account : bankAccounts) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    @Override
    public double calculateSeniorityBonus() {
        if (person.calculateAge() < 50) {
            return 0;
        }
        double bonus = 0;
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
