package com.banking.service;

import com.banking.service.actions.*;

import javax.naming.OperationNotSupportedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ActionHandler {
    public void executeAction(int action) {
        Action selectedAction = null;
        switch(action) {
            case 0:
                selectedAction = new ExitAction();
                break;
            case 1:
                selectedAction = new ShowBalanceAction();
                break;
            case 2:
                selectedAction = new DepositAction();
                break;
            case 3:
                selectedAction = new CashWithdrawal();
                break;
            case 4:
                selectedAction = new FundsTransfer();
                break;
            default:
                selectedAction = new UndefinedAction();
        }

        try {
            selectedAction.executeAction();
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public int getActionFromUser() {
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

                System.out.println("You have selected the action number: " + userInputTemp);
                return userInputTemp;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
