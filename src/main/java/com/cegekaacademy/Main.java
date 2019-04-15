package com.cegekaacademy;

import com.cegekaacademy.model.Address;
import com.cegekaacademy.model.Person;

public class Main {

    public static void main(String[] args) {
        Person p = new Person("Andrei", "1971125460024", new Address("Bucharest", "Sos. iancului", 10));
        System.out.println(p.calculateAge());
    }
}
