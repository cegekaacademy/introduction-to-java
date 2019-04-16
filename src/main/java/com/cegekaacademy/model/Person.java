package com.cegekaacademy.model;

import jdk.internal.cmm.SystemResourcePressureImpl;

import java.util.Calendar;

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
        this.pid = pid;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int calculateAge() {

        int age = 0;
        int yearOfBirth = Integer.parseInt(this.pid.substring(1, 3));
        int monthOfBirth = Integer.parseInt(this.pid.substring(3,5));
        switch (Character.getNumericValue(this.pid.charAt(0))){
            case 1:
                System.out.println("Persoana este nascuta intre 1 ianuarie 1900 și 31 decembrie 1999");
                age = Calendar.getInstance().get(Calendar.YEAR) - (1900 + yearOfBirth);
                break;
            case 2:
                System.out.println("Persoana este nascuta intre 1 ianuarie 1900 și 31 decembrie 1999");
                age = Calendar.getInstance().get(Calendar.YEAR) - (1900 + yearOfBirth);
                break;
            case 3:
                System.out.println("Persoana este nascuta intre 1 ianuarie 1800 și 31 decembrie 1899");
                age = Calendar.getInstance().get(Calendar.YEAR) - (1800 + yearOfBirth);
                break;
            case 4:
                System.out.println("Persoana este nascuta intre 1 ianuarie 1800 și 31 decembrie 1899");
                age = Calendar.getInstance().get(Calendar.YEAR) - (1800 + yearOfBirth);
                break;
            case 5:
                System.out.println("Persoana este nascuta intre 1 ianuarie 2000 și 31 decembrie 2099");
                age = Calendar.getInstance().get(Calendar.YEAR) - (2000 + yearOfBirth);
                break;
            case 6:
                System.out.println("Persoana este nascuta intre 1 ianuarie 2000 și 31 decembrie 2099");
                age = Calendar.getInstance().get(Calendar.YEAR) - (2000 + yearOfBirth);
                break;
        }

        if(age == 0) {
            System.out.println("S-a produce o erore in sistem");
            return 0;
        }

        if (monthOfBirth > Calendar.getInstance().get(Calendar.MONTH)){
            age--;
        }

        return age;
    }
}
