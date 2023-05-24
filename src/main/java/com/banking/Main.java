package com.banking;

import com.banking.service.Authenticator;

public class Main {
    public static void main(String[] args) {
        //our entry point
        Authenticator authenticator = new Authenticator();
        authenticator.requestAuthentication();

    }

    public static void printMainUI() {
        System.out.println("Input the number corresponding to action you want to perform:\n" +
                "0: Exit\n" +
                "1: Show Balance\n" +
                "2: Transfer To Own account\n" +
                "3: Cash Withdrawl\n" +
                "4: Funds Transfer");
    }
}

