package com.cegekaacademy.model;

import java.time.LocalDate;

public class Person {

    private String name;
    private String pid;
    private Address address;

    public Person(String name, String pid, Address address) {
        this.name = name;
        this.pid = pid;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int calculateAge() {
        int sexId = Integer.valueOf(this.pid.substring(0, 1));
        String yearOfBirthDigits = this.pid.substring(1, 3);
        int currentYear = LocalDate.now().getYear();

        if (sexId == 0 || sexId == 1) {
            int yearOfBirth = Integer.valueOf("19" + yearOfBirthDigits);
            return currentYear - yearOfBirth;
        }

        // TODO implement all the other cases

        return 0;
    }
}
