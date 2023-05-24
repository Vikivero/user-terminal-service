package com.banking;

import com.banking.service.ActionHandler;

public class Main {
    public static void main(String[] args) {
        //our entry point

//        Authenticator authenticator = new Authenticator();
//        authenticator.requestAuthentication();

        ActionHandler actionHandler = new ActionHandler();
        int action = actionHandler.getActionFromUser();

        actionHandler.executeAction(action);
    }


}

