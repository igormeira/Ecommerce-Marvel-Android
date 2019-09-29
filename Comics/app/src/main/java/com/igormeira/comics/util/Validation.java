package com.igormeira.comics.util;

public class Validation {

    public boolean isValidLogin(String email, String password) {
        if (isValidEmail(email) && isValidPassword(password)) return true;
        return false;
    }

    private boolean isValidPassword(String password) {
        if (password != null && !password.equals("")) return true;
        return false;
    }

    private boolean isValidEmail(String email) {
        if (email != null && !email.isEmpty()) return true;
        return false;
    }

}
