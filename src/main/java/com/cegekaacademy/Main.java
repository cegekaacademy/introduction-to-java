package com.cegekaacademy;

import com.cegekaacademy.bank.BankClient;
import com.cegekaacademy.model.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        Person Maria = new Person("Fiodor", "2960120133911", new Address("Bucharest", "Splaiul Independentei,", 290));
        DepositAccount dep = new DepositAccount("iban", 100D);
        CurrentAccount current = new CurrentAccount("iban1", 200D);

        dep.setInterest(0.2);

        ArrayList<BankAccount> lista = new ArrayList<>();
        lista.add(dep);
        lista.add(current);
        BankClient bank = new BankClient(Maria, lista);

        System.out.println(bank.calculateYoungBonus());

    }
}
