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
        if (pid.charAt(0) == '1' || pid.charAt(0) == '2')
            year = (1900 + Integer.parseInt("" + pid.charAt(1) + pid.charAt(2)));
        if (pid.charAt(0) == '3' || pid.charAt(0) == '4')
            year = (1800 + Integer.parseInt("" + pid.charAt(1) + pid.charAt(2)));
        if (pid.charAt(0) == '5' || pid.charAt(0) == '6')
            year = (2000 + Integer.parseInt("" + pid.charAt(1) + pid.charAt(2)));

        int month = Integer.parseInt("" + pid.charAt(3) + pid.charAt(4));
        int day = Integer.parseInt("" + pid.charAt(5) + pid.charAt(6));

        Calendar birthDate = Calendar.getInstance();
        birthDate.set(year, month, day);
        Calendar currentDate = Calendar.getInstance();
        long ageInDays = TimeUnit.MILLISECONDS
                .toDays(Math.abs(currentDate.getTimeInMillis() - birthDate.getTimeInMillis()));
        return (int) (ageInDays / 365);

    }

}
