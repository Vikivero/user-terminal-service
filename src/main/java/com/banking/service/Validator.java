package com.banking.service;

public class Validator {
    public boolean validateAccNumber(String accountNumber) {
        try {
            Long.parseLong(accountNumber);
        } catch(NumberFormatException nfe) {
            return false;
        }
        //could have been an if, but this will return a true/false value anyway
        return String.valueOf(accountNumber).length() == 8;
    }
    public boolean validatePassword(String password) {
        //can't be an empty String
        return password.trim().length() > 0;
    }
}
