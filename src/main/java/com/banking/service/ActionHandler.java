package com.banking.service;

import com.banking.service.actions.*;

import javax.naming.OperationNotSupportedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ActionHandler {
    private final long accountNumber;

    public ActionHandler(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void executeAction(int action) {
        Action selectedAction = switch (action) {
            case 1 -> new ShowBalanceAction();
            case 2 -> new DepositAction();
            case 3 -> new CashWithdrawalAction();
            case 4 -> new FundsTransferAction();
            case 0 -> new ExitAction();
            default -> new UndefinedAction();
        };

        try {
            selectedAction.printGreetings();
            selectedAction.executeAction(accountNumber);
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public int getActionFromUser() {
        String errorMsg = "Please input a number from 0 to 4: ";
        String delimiter = "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n";
        String header = "User Terminal v0.1\n";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println(delimiter +
                    header +
                    delimiter +
                    "Hi! Your account number is " + accountNumber + "\n" +
                    "Input the number corresponding to action you want to perform: \n" +
                    "1: Show Balance\n" +
                    "2: Deposit money\n" +
                    "3: Cash Withdrawal\n" +
                    "4: Funds Transfer:\n" +
                    "0: Exit\n" +
                    "------------------");
            int userInputAction = -1;
            try {
                userInputAction = Integer.parseInt(br.readLine());
            } catch (NumberFormatException nfe) {
                System.out.println(errorMsg);
                continue;
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (userInputAction < 0 || userInputAction > 4) {
                System.out.println(errorMsg);
                continue;
            }

            return userInputAction;
        }

    }
}
