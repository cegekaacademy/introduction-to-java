package com.cegekaacademy.model;

import com.cegekaacademy.model.Address;
import com.cegekaacademy.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class PersonTest {

    @Test(expected = IllegalStateException.class)
    public void GIVEN_wrongPID_WHEN_constructorWithThreeParameters_THEN_returnThrowException() {
        Address address = Mockito.mock(Address.class);
        Person person = new Person("Andrei Neagu", null, address);
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_wrongPID_WHEN_setPid_THEN_returnThrowException() {
        Address address = Mockito.mock(Address.class);
        Person person = new Person("Andrei Neagu", null, address);
        person.setPid("12312515");
    }

    @Test
    public void GIVEN_correctCNP_WHEN_calculateAge_THEN_returnCorrectAge() {
        Address address = Mockito.mock(Address.class);
        Person person = new Person("Andrei Neagu", "1971125460024", address);
        Assert.assertEquals(21, person.calculateAge());
        Person person2 = new Person("Andrei Neagu", "5091124460024", address);
        Assert.assertEquals(9, person2.calculateAge());
    }
}
