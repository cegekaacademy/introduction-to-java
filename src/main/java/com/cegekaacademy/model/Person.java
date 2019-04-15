package com.cegekaacademy.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Person {

    private String name;
    private String pid;
    private Address address;

    public Person(String name, String pid, Address address) {
        if (pid == null || pid.length() != 13) {
            throw new IllegalStateException("Pid should have exactly 13 characters");
        }

        if (name.contains("[0-9]+")) {
            throw new IllegalStateException("Name should only have letters");
        }

        this.name = name;
        this.pid = pid;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.contains("[0-9]+"))
            throw new IllegalStateException("Name should only have letters");

        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        if (pid == null || pid.length() != 13)
            throw new IllegalStateException("Pid should have exactly 13 characters");

        this.pid = pid;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int determineYearFromPID(int sexId, String yearOfBirthDigits) {
        int yearOfBirth;

        switch (sexId) {
            case 1:
            case 2:
            case 7:
            case 8:
                return yearOfBirth = Integer.valueOf("19" + yearOfBirthDigits);
            case 4:
            case 3:
                return yearOfBirth = Integer.valueOf("18" + yearOfBirthDigits);
            case 5:
            case 6:
                return yearOfBirth = Integer.valueOf("20" + yearOfBirthDigits);
        }

        return 0;
    }

    public int calculateAge() {
        int sexId = Integer.valueOf(this.pid.substring(0, 1));          // 2
        String yearOfBirthDigits = this.pid.substring(1, 3);            // 92
        int monthOfBirth = Integer.valueOf(this.pid.substring(3, 5));   // 01
        int dayOfBirth = Integer.valueOf(this.pid.substring(5, 7));     // 14

        switch (sexId) {
            case 0:
            case 9:
            throw new IllegalStateException("First digit of the PID must be included in [1,8]");
        }

        int yearOfBirth = determineYearFromPID(sexId, yearOfBirthDigits);
        if (yearOfBirth != 0)
            return (int) ChronoUnit.YEARS.between(LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth), LocalDate.now());
        else
            return 0;
    }
}
