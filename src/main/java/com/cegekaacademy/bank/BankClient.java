package com.cegekaacademy.bank;

import com.cegekaacademy.model.BankAccount;
import com.cegekaacademy.model.DepositAccount;
import com.cegekaacademy.model.Person;

import java.util.List;

public class BankClient implements BankCalculator {

    private Person person;
    private List<BankAccount> bankAccounts;

    public BankClient(Person person, List<BankAccount> bankAccounts) {
        if (person == null) {
            throw new IllegalStateException("A bank client must be a person");
        }
        if (bankAccounts.isEmpty() || bankAccounts == null) {
            throw new IllegalStateException("A client must have at least one bank account");
        }
        this.bankAccounts = bankAccounts;
        this.person = person;
    }

    public double getTotalBalance() throws IllegalStateException {
        double total = 0;
        if (this.bankAccounts.size() == 0 || this.bankAccounts == null)
            throw new IllegalStateException("Method requires one or more bank accounts");
        for (BankAccount bankAccount : bankAccounts) {
            total += bankAccount.getBalance();
        }
        return total;
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
