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
        int sexId = Integer.valueOf(this.pid.substring(0, 1));          // 2
        String yearOfBirthDigits = this.pid.substring(1, 3);            // 92
        int monthOfBirth = Integer.valueOf(this.pid.substring(3, 5));   // 01
        int dayOfBirth = Integer.valueOf(this.pid.substring(5, 7));     // 14

        int yearOfBirth = 0;
        if (sexId == 1 || sexId == 2)
        {
            yearOfBirth = Integer.valueOf("19" + yearOfBirthDigits);
        }

        if(sexId == 5 || sexId == 6)
        {
            yearOfBirth = Integer.valueOf("20" + yearOfBirthDigits);
        }

        if(yearOfBirth == 0)
        {
            return 0;
        }
        if(monthOfBirth < 1 || monthOfBirth > 12)
            return -1;


        if(yearOfBirth % 4 == 0) // leap year
        {
            if(monthOfBirth == 2 ){
                if(dayOfBirth > 29)
                    return -2;
            }

        }
        else
        {
            if(monthOfBirth == 2)
                if(dayOfBirth > 28)
                    return -2;
        }

        if(monthOfBirth == 1 || monthOfBirth == 3 || monthOfBirth == 5 || monthOfBirth == 7
             || monthOfBirth == 8 || monthOfBirth == 10 || monthOfBirth == 12)
        {
            if(dayOfBirth > 31)
                return -3;
        }


        if(monthOfBirth == 4 || monthOfBirth == 6 || monthOfBirth == 9 || monthOfBirth == 11)
        {
            if(dayOfBirth > 30)
                return -3;
        }


        // TODO implement all the other cases

        return (int) ChronoUnit.YEARS.between(LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth), LocalDate.now());
    }
}
