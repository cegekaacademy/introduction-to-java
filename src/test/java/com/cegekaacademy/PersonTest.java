package com.cegekaacademy;

import com.cegekaacademy.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class PersonTest {

    @Test
    public void GIVEN_pidValid_WHEN_setPid_THEN_setPid(){
        Person person = new Person("elena", "2970418090012", null);
        String pid = "2970418090014";
        person.setPid(pid);
        Assert.assertEquals(pid, person.getPid());
    }

    @Test(expected = IllegalStateException.class)
    public void GIVEN_pidInvalid_WHEN_setPid_THEN_throwException(){
        Person person = new Person("elena", "297041809001", null);
    }

    @Test
    public void GIVEN_pidValid_WHEN_calculateAge_THEN_returnAge(){
        Person person = new Person("elena", "2970410090012", null);
        int age = person.calculateAge();
        Assert.assertEquals(22, age);
    }

}
