package com.banking.service;

import com.banking.service.connection.ConnectionHandler;
import com.banking.service.connection.SocketResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Authenticator {

    private final ConnectionHandler handler;

    public Authenticator() {
        handler = ConnectionHandler.getInstance();
    }

    public long authenticate() {

        long validAccountNumber;
        String validPassword;

        //try with resources
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            Validator validator = new Validator();

            //validation loops moved to separate methods
            try {
                validAccountNumber = getAndValidateAccountNumber(reader, validator);
                validPassword = getAndValidatePassword(reader, validator);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            SocketResponse authResponse = handler.sendAuthenticationRequest(validAccountNumber, validPassword);

            if (authResponse.isSuccessful()) {
                System.out.println("Correct credentials.");
                //return bez niczego jesli funkcja jest void zwroci po prostu ta funkcje
                return validAccountNumber;
            }

            System.out.println(authResponse.getErrorMessage());
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
