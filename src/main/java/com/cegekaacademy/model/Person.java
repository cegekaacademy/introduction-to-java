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
        int sexId = Integer.valueOf(pid.substring(0, 1));
        if (sexId == 0 || sexId == 7 || sexId == 8 || sexId ==9){
            throw new IllegalStateException("Invalid PID");
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

        if (sexId == 1 || sexId == 2) {
            int yearOfBirth = Integer.valueOf("19" + yearOfBirthDigits);
            return (int) ChronoUnit.YEARS.between(LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth), LocalDate.now());
        }
        else if (sexId == 3 || sexId == 4) {
            int yearOfBirth = Integer.valueOf("18" + yearOfBirthDigits);
            return (int) ChronoUnit.YEARS.between(LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth), LocalDate.now());
        }
        else if (sexId == 5 || sexId == 6) {
            int yearOfBirth = Integer.valueOf("20" + yearOfBirthDigits);
            return (int) ChronoUnit.YEARS.between(LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth), LocalDate.now());
        }

        // TODO implement all the other cases

        return 0;
    }
}
