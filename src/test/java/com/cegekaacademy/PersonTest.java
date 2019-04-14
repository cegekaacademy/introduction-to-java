package com.cegekaacademy;

import com.cegekaacademy.model.Person;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void GIVEN_personPIDValid_WHEN_constructorPerson_THEN_returnPerson(){
        Person person = new Person("Ioana","2970802420019",null);
        assertNotNull(person);
        assertEquals("Ioana",person.getName());
        assertEquals("2970802420019",person.getPid());
        assertEquals(null,person.getAddress());
    }
    @Test(expected = IllegalStateException.class)
    public void GIVEN_personPIDNull_WHEN_constructorPerson_THEN_returnException(){
        Person person = new Person("Ioana",null,null);
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_personPIDLengthLessThan13_WHEN_constructorPerson_THEN_returnException(){
        Person person = new Person("Ioana","1223333",null);
    }

    @Test
    public void GIVEN_personPidValid_WHEN_calculateAge_THEN_returnInt(){
        Person person = new Person("Ioana","2970802420019",null);
        int result=person.calculateAge();
        assertEquals(21,result);
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_personPidInvalid_WHEN_calculateAge_THEN_returnException(){
        Person person = new Person("Ioana",null,null);
        int result=person.calculateAge();

    }

}