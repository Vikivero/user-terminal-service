package com.banking;

import com.banking.service.ActionHandler;
import com.banking.service.Authenticator;

public class Main {
    public static void main(String[] args) {
        //our entry point

        Authenticator authenticator = new Authenticator();
        long accountNumber = authenticator.authenticate();
//        long accountNumber = 12345678L;

        ActionHandler actionHandler = new ActionHandler(accountNumber);

        // Main Loop
        while (true) {
            int action = actionHandler.getActionFromUser();

            actionHandler.executeAction(action);
        }
    }


}

