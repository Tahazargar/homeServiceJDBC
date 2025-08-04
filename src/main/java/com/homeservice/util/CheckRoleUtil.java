package com.homeservice.util;

public class CheckRoleUtil {
    public static boolean isAdmin(int role) {
        return role == 2;
    }

    public static boolean isCustomer(int role) {
        return role == 0;
    }

    public static boolean isExpert(int role) {
        return role == 1;
    }
}
