package com.banking;

import com.banking.service.Authenticator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        //our entry point

//        Authenticator authenticator = new Authenticator();
//        authenticator.requestAuthentication();

        int action = getActionFromUser();
        System.out.println("You have selected the action number: " + action);
    }

    public static int getActionFromUser() {
        String errorMsg = "Please input a number from 0 to 4: ";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Input the number corresponding to action you want to perform: \n" +
                        "0: Exit\n" +
                        "1: Show Balance\n" +
                        "2: Transfer To Own account\n" +
                        "3: Cash Withdrawal\n" +
                        "4: Funds Transfer");
                int userInputTemp;
                try {
                    userInputTemp = Integer.parseInt(reader.readLine());
                } catch (NumberFormatException nfe) {
                    System.out.println(errorMsg);
                    continue;
                }
                if (userInputTemp < 0 || userInputTemp > 4) {
                    System.out.println(errorMsg);
                    continue;
                }

                return userInputTemp;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

