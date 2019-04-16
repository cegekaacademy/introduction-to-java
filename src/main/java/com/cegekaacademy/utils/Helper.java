package com.cegekaacademy.utils;

public class Helper {
    public static boolean validatePid(String pid) {
        if (pid == null || pid.length() != 13) {
            throw new IllegalStateException("Pid should have exactly 13 characters");
        }
        String key = "279146358279";
        int sum = 0;
        for (int i = 0; i < 12; i++)
            sum += Character.getNumericValue(key.charAt(i)) * Character.getNumericValue(pid.charAt(i));
        sum = sum % 11;
        if (sum == 10)
            sum /= 10;
        return sum == Character.getNumericValue(pid.charAt(12));
    }
}
