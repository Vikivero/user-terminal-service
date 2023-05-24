package com.banking.service;

import com.banking.entity.Account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Authenticator {
    public void requestAuthentication() {

        long parsedEnteredNumber;
        String validatedEnteredPassword;
        AccountService accountService = new AccountService();

        //try with resources
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                Validator validator = new Validator();

                //validation loops moved to separate methods
                parsedEnteredNumber = getAndValidateAccountNumber(reader, validator);
                validatedEnteredPassword = getAndValidatePassword(reader, validator);

                System.out.println(parsedEnteredNumber + " " + validatedEnteredPassword);

                Account account = accountService.getByAccountNumber(parsedEnteredNumber);

                if (account != null && account.getPassword().equals(validatedEnteredPassword)) {
                    System.out.println("Correct credentials.");
                    //return bez niczego jesli funkcja jest void zwroci po prostu ta funkcje
                    return;
                }
                System.out.println("Incorrect account number or password.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAndValidatePassword(BufferedReader reader, Validator validator) throws IOException {
        while (true) {
            System.out.println("Please input the password: ");
            String enteredPassword = reader.readLine();
            if (!validator.validatePassword(enteredPassword)) {
                System.out.println("Please input a valid password.");
                continue;
            }
            return enteredPassword;
        }
    }

    private long getAndValidateAccountNumber(BufferedReader reader, Validator validator) throws IOException {

        while (true) {
            System.out.println("Please input the account number: ");
            String enteredNumber = reader.readLine();
            if (!validator.validateAccNumber(enteredNumber)) {
                System.out.println("Please input a valid account number.");
                continue;
            }
            return Long.parseLong(enteredNumber);
        }
    }
}
