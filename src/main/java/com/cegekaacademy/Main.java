package com.cegekaacademy;

import com.cegekaacademy.bank.BankClient;
import com.cegekaacademy.model.Address;
import com.cegekaacademy.model.BankAccount;
import com.cegekaacademy.model.DepositAccount;
import com.cegekaacademy.model.Person;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {
        Person person = new Person("asfsa","2970415090032", new Address("Fdsfs","fsdfds",49));
        System.out.println(person.calculateAge());
        BankAccount account = new DepositAccount("fdsafsafds",200);
        ArrayList<BankAccount> list = new ArrayList<>();
        list.add(account);
        BankClient client = new BankClient(person, list);
    }
}
