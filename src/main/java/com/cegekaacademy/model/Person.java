package com.cegekaacademy.model;
import com.cegekaacademy.utils.PersonUtils;

import java.time.LocalDate;
import java.time.Period;

public class Person {

    private String name;
    private String pid;
    private Address address;

    public Person(String name, String pid, Address address) {
        this.setPid(pid);
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

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
        LocalDate birthDate = PersonUtils.getBirthdateFromPID(this.pid);
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate,currentDate);
        int age = period.getYears();
        return age;
    }
}
