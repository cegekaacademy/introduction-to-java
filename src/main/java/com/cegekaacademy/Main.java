package com.cegekaacademy;

import com.cegekaacademy.model.Person;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        Person person = new Person("Cosmin Alex", "1950201170030", null);
        System.out.println(person.calculateAge());
    }
}
