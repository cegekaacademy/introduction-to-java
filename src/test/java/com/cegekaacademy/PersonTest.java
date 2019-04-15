package com.cegekaacademy;

import com.cegekaacademy.bank.BankClient;
import com.cegekaacademy.model.Address;
import com.cegekaacademy.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class PersonTest {


    @Test(expected = IllegalStateException.class)
    public void GIVEN_pidLengthDifferentThan13_WHEN_creatingNewObject_THEN_returnThrowException(){
        Address address = Mockito.mock(Address.class);
        Person person= new Person("name","123", address);
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_pidInvalid_WHEN_creatingNewObject_THEN_returnThrowException(){
        Address address = Mockito.mock(Address.class);
        Person person= new Person("name","8234566778890", address);
    }
    @Test
    public void GIVEN_personValid_WHEN_calculateAge_THEN_returnAge(){
        Address address = Mockito.mock(Address.class);
        Person person1 = new Person("name", "2950511443322",address);
        Assert.assertEquals(23,person1.calculateAge(),0);

        Person person2 = new Person("name", "3950511443322",address);
        Assert.assertEquals(123,person2.calculateAge(),0);

        Person person3 = new Person("name", "5000511443322",address);
        Assert.assertEquals(18,person3.calculateAge(),0);
    }
}
