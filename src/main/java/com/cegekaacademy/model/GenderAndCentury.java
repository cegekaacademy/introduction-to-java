package com.cegekaacademy.model;

public enum GenderAndCentury {
    MALE1900(1),
    FEMLE1900(2),
    MALE1800(3) ,
    FEMALE1800 (4),
    MALE2000 (5),
    FEMALE2000(6);

    private int value;

    GenderAndCentury(int i) {
        this.value = i;
    }

    public int getValue(){
        return this.value;
    }
}
