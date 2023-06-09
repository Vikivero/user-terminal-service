package com.banking.service.actions;

import com.banking.entity.BankAccount;
import com.banking.service.AccountService;
import com.banking.service.Validator;
import com.banking.service.connection.ConnectionHandler;
import com.banking.service.connection.SocketResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DepositAction implements Action {
    private static final String ACTION_NAME = "Deposit money";
    private final AccountService accountService;
    private final Validator validator;

    private final ConnectionHandler connectionHandler;

    public DepositAction() {
        accountService = new AccountService();
        validator = new Validator();
        connectionHandler = ConnectionHandler.getInstance();
    }

    @Override
    public void executeAction(long accountNumber) {
        // Request user
        BankAccount bankAccount = accountService.getByAccountNumber(accountNumber);
        if (bankAccount == null) {
            System.out.println("Something went wrong, your bank account can not be found. Please contact your administrator!");
            returnToMainMenu(true);
            return;
        }

        while (true) {
            double amount = getAmountFromUser();
            SocketResponse response = connectionHandler.sendDepositRequest(bankAccount.getAccountNumber(), amount);

            boolean finished = tryToCompleteAction(response);

            if (finished) {
                break;
            }
        }
    }

    @Override
    public void printGreetings() {
        System.out.printf(GREETING_MSG, ACTION_NAME);
    }

    private double getAmountFromUser() {
        System.out.println("Enter the amount you want to deposit and insert money (by pressing Enter).\n" +
                "Please use deciamal or floating point number (dot as delimiter):");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String enteredAmount = br.readLine();
                if (validator.validateMoneyAmount(enteredAmount)) {
                    double amount = Double.parseDouble(enteredAmount);
                    System.out.printf("You are going to deposit %,.2f credits. Please wait...\n", amount);
                    return amount;
                } else {
                    System.out.println("Invalid amount. Please enter the correct amount using decimal or floating point number (dot as delimiter):");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
