package com.cegekaacademy;

import com.cegekaacademy.model.Address;
import com.cegekaacademy.model.Person;
import org.junit.Assert;
import org.junit.Test;

public class PersonTest {
    @Test
    public void GIVEN_nameAndPidAndAddressValid_WHEN_creatingObject_THEN_returnPerson(){
        Person person=new Person("Name","1212323112123",new Address("Bucuresti","Street1",20));
        Assert.assertNotNull(person);
        Assert.assertEquals("Name",person.getName());
        Assert.assertEquals("1212323112123",person.getPid());
    }
    @Test
    public void GIVEN_nullObject_WHEN_creatingObject_THEN_returnNull(){
        Person person=null;
        Assert.assertNull(person);
    }
    @Test(expected=IllegalStateException.class)
    public void GIVEN_pidInvalid_WHEN_creatingObject_THEN_returnIllegalStateException(){
        Person person=new Person("Name","1212323113",new Address("Bucuresti","Street1",20));
    }
    @Test(expected=IllegalStateException.class)
    public void GIVEN_nullPid_WHEN_creatingObject_THEN_returnIllegalStateException(){
        Person person=new Person("Name",null,new Address("Bucuresti","Street1",20));
    }
    @Test
    public void GIVEN_personPidValid_WHEN_calculateAge_THEN_returnAge(){
        Person person=new Person("Name","1970203425074",new Address("Bucuresti","Street1",20));
        int ageResult=person.calculateAge();
        Assert.assertEquals(22,ageResult,0.0);
    }
    @Test
    public void GIVEN_personPidInvalid_WHEN_calculateAge_THEN_return0(){
        Person person=new Person("Name","9970203425074",new Address("Bucuresti","Street1",20));
        int ageResult=person.calculateAge();
        Assert.assertEquals(0,ageResult,0.0);
    }

}
