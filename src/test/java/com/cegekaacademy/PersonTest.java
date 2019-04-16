package com.cegekaacademy;

import com.cegekaacademy.model.Address;
import com.cegekaacademy.model.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.Period;

public class PersonTest {

    private Address address;

    @Before
    public void setUp() {
        address = Mockito.mock(Address.class);
    }

    @Test
    public void testCalculateAgeForPersonBornIn2000s() {
        Person person = new Person("name","5020321023453", address);
        LocalDate birthDate = LocalDate.of(2002,3,21);
        LocalDate currentDate = LocalDate.now();
        Assert.assertEquals(Period.between(birthDate,currentDate).getYears(),person.calculateAge());
    }

    @Test
    public void testCalculateAgeForPersonBornIn1900s() {
        Person person = new Person("name","2850416023453", address);
        LocalDate birthDate = LocalDate.of(1985,4,16);
        LocalDate currentDate = LocalDate.now();
        Assert.assertEquals(Period.between(birthDate,currentDate).getYears(),person.calculateAge());
    }

    @Test
    public void testCalculateAgeForPersonBornIn1800s() {
        Person person = new Person("name","3960321023453", address);
        LocalDate birthDate = LocalDate.of(1896,3,21);
        LocalDate currentDate = LocalDate.now();
        Assert.assertEquals(Period.between(birthDate,currentDate).getYears(),person.calculateAge());
    }
}
