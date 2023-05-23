package com.banking;

import com.banking.service.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        //our entry point
        printAuthenticationInterface();
    }

    public static void printAuthenticationInterface() {

        //try with resources
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            Validator validator = new Validator();
            long parsedEnteredNumber;
            String validatedEnteredPassword;

            //validation loop
            while (true) {
                System.out.println("Please input the account number: ");
                String enteredNumber = reader.readLine();
                if (!validator.validateAccNumber(enteredNumber)) {
                    System.out.println("Please input a valid account number.");
                    continue;
                }
                parsedEnteredNumber = Long.parseLong(enteredNumber);
                break;
            }
            while (true) {
                System.out.println("Please input the password: ");
                String enteredPassword = reader.readLine();
                if (!validator.validatePassword(enteredPassword)) {
                    System.out.println("Please input a valid password.");
                    continue;
                }
                validatedEnteredPassword = enteredPassword;
                break;
            }
            System.out.println(parsedEnteredNumber + " " + validatedEnteredPassword);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

