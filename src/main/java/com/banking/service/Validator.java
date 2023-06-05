package com.banking.service;

public class Validator {
    public boolean validateAccNumber(String accountNumber) {
        try {
            Long.parseLong(accountNumber);
        } catch (NumberFormatException nfe) {
            return false;
        }
        //could have been an if, but this will return a true/false value anyway
        return accountNumber.length() == 8;
    }

    public boolean validatePassword(String password) {
        //can't be an empty String
        return password.trim().length() > 0;
    }

    public boolean validateMoneyAmount(String amount) {
        if (amount.trim().length() == 0) {
            return false;
        }
        try {
            double parsed = Double.parseDouble(amount);
            return parsed >= 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
