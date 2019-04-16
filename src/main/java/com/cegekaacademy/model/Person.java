package com.cegekaacademy.model;

import com.cegekaacademy.utils.Helper;

import java.util.Calendar;

public class Person {

    private String name;
    private String pid;
    private Address address;

    public Person(String name, String pid, Address address) {
        if (!Helper.validatePid(pid)) {
            throw new IllegalStateException("Invalid pid");
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
        this.pid = pid;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int calculateAge() {
        int firstDigit = Character.getNumericValue(this.pid.charAt(0));
        int cnpYear = Character.getNumericValue(this.pid.charAt(1)) * 10 + Character.getNumericValue(this.pid.charAt(2));
        int birthYear;
        switch (firstDigit) {
            case 1:
            case 2:
                birthYear = 1900;
                break;
            case 3:
            case 4:
                birthYear = 1800;
                break;
            case 5:
            case 6:
                birthYear = 2000;
                break;
            default:
                throw new IllegalStateException("Pid should start with a digit between 1-6");
        }
        birthYear += cnpYear;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - birthYear;
    }
}
