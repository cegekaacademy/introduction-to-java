package com.cegekaacademy.utils;

import com.cegekaacademy.model.GenderAndCentury;
import com.cegekaacademy.model.Person;

import java.time.LocalDate;

public class PersonUtils {

    public static LocalDate getBirthdateFromPID(String pid) {
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
        return  birthDate;
    }
}
