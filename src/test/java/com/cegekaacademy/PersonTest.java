package com.cegekaacademy;

import com.cegekaacademy.model.Person;
import org.junit.Assert;
import org.junit.Test;


public class PersonTest {

    @Test
    public void GIVEN_pidValid_WHEN_constructorPerson_THEN_returnPerson(){
        Person person = new Person("Ana","2970802420054",null);
        Assert.assertNotNull(person);
        Assert.assertEquals("Ana", person.getName());
        Assert.assertEquals("2970802420054", person.getPid());
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_pidNull_WHEN_constructorPerson_THEN_returnPerson(){
       new Person("Ana",null,null);
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_pidLengthLessThan13_WHEN_constructorPerson_THEN_returnPerson(){
        new Person("Ana","12345",null);
    }

    @Test
    public void GIVEN_personPidValid_WHEN_withdraw_THEN_returnAge(){
        Person person = new Person("Ana","2970802420054",null);
        int age = person.calculateAge();
        Assert.assertEquals(21,age);
    }

}