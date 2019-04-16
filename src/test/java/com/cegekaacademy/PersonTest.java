package com.cegekaacademy;

import com.cegekaacademy.model.Person;
import org.junit.Assert;
import org.junit.Test;

public class PersonTest {

    @Test
    public void GIVEN_validPID_WHEN_calculateAge_THEN_returnCorrectAge() {
        Person person = new Person(null, "1920915296610", null);
        int age = person.calculateAge();
        Assert.assertEquals(26, age);
    }

    @Test
    public void GIVEN_invalidPID_WHEN_calculateAge_THEN_return0() {
        Person person = new Person(null, "4920915296610", null);
        int age = person.calculateAge();
        Assert.assertEquals(0, age);
    }

}
