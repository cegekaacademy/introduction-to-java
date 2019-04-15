package com.cegekaacademy.model;
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
        int genderAndCentury = Integer.parseInt(pid.substring(0,1));
        int century;
        if( genderAndCentury == GenderAndCentury.MALE1800.getValue() || genderAndCentury == GenderAndCentury.FEMALE1800.getValue()) {
            century = 1800;
        } else if(genderAndCentury == GenderAndCentury.MALE1900.getValue() || genderAndCentury == GenderAndCentury.FEMLE1900.getValue()) {
            century = 1900;
        } else {
            century = 2000;
        }
        int birthYear = century + Integer.parseInt(pid.substring(1,3));
        int birthMonth = Integer.parseInt(pid.substring(3,5));
        int birthDay = Integer.parseInt(pid.substring(5,7));
        LocalDate birthDate = LocalDate.of(birthYear,birthMonth,birthDay);
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate,currentDate);
        int age = period.getYears();
        return age;
    }
}
