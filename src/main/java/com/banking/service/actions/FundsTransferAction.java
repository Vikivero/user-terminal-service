package com.banking.service.actions;

import com.banking.entity.BankAccount;
import com.banking.service.AccountService;
import com.banking.service.Validator;
import com.banking.service.connection.ConnectionHandler;
import com.banking.service.connection.SocketResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FundsTransferAction implements Action{
    private static final String ACTION_NAME = "Transfer Funds";

    private static final String GET_AMOUNT_MESSAGE = "Your balance is %s credits.\n" +
            "Enter the amount you want to transfer and press Enter\n" +
            "Please use floating point number (dot as delimiter):\n";
    private static final String GET_ACCOUNT_NUMBER_MESSAGE = "Please enter the recipient account number and press Enter:";

    private final AccountService accountService;
    private final Validator validator;

    private final ConnectionHandler connectionHandler;

    public FundsTransferAction() {
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
            double amount = getAmountFromUser(bankAccount.getAccountBalance());
            long targetAccountNumber = getTargetAccountNumberFromUser();

            if (targetAccountNumber == accountNumber) {
                System.out.println("You are trying to perform transfer to your current account. Try again.");
                continue;
            }

            SocketResponse response = connectionHandler.sendFundsTransferRequest(bankAccount.getAccountNumber(), targetAccountNumber, amount);

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

    private long getTargetAccountNumberFromUser() {
        System.out.println(GET_ACCOUNT_NUMBER_MESSAGE);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String enteredAccountNumber = br.readLine();

                if (validator.validateAccNumber(enteredAccountNumber)) {
                    long accNumber = Long.parseLong(enteredAccountNumber);
                    System.out.printf("Your are going to send money to the account number %d. Please wait...\n", accNumber);
                    return accNumber;
                } else {
                    System.out.println("Invalid account number. Please enter the correct one:");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private double getAmountFromUser(double accountBalance) {
        System.out.printf(GET_AMOUNT_MESSAGE, String.format("%,.2f", accountBalance));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                String enteredAmount = br.readLine();

                if (validator.validateMoneyAmount(enteredAmount)) {
                    double amount = Double.parseDouble(enteredAmount);
                    System.out.printf("Your are going to transfer %,.2f credits. Please wait...\n", amount);
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
