package com.cegekaacademy.model;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class Person {
    private String name;
    private String pid;
    private Address address;

    public Person(String name, String pid, Address address) {
        if (pid == null || pid.length() != 13) {
            throw new IllegalStateException("Pid should have exactly 13 characters");
        }
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
        if (pid == null || pid.length() != 13) {
            throw new IllegalStateException("Pid should have exactly 13 characters");
        }
        this.pid = pid;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int calculateAge() {
        int year = 0;
        char firstNumber = pid.charAt(0);
        if (firstNumber == '1' || firstNumber == '2') {
            year = 1900 + Integer.parseInt(pid.substring(1, 3));
        } else if (firstNumber == '3' || firstNumber == '4') {
            year = 1800 + Integer.parseInt(pid.substring(1, 3));
        } else if (firstNumber == '5' || firstNumber == '6') {
            year = 2000 + Integer.parseInt(pid.substring(1, 3));
        }
        int month = Integer.parseInt(pid.substring(3, 5));
        int day = Integer.parseInt(pid.substring(5, 7));
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(year, month, day);
        Calendar currentDate = Calendar.getInstance();
        long daysPassed = TimeUnit.MILLISECONDS.toDays(Math.abs(currentDate.getTimeInMillis() - birthDate.getTimeInMillis()));

        return (int) daysPassed / 365;

    }
}
