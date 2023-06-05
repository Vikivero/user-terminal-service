package com.banking.service.actions;

import com.banking.entity.BankAccount;
import com.banking.service.AccountService;

public class ShowBalanceAction implements Action {
    private static final String ACTION_NAME = "Show Balance";
    private static final String ACTION_MESSAGE = "Your balance is %s credits.\n";
    private final AccountService accountService;

    public ShowBalanceAction() {
        accountService = new AccountService();
    }

    @Override
    public void executeAction(long accountNumber) {
        // Request user
        BankAccount bankAccount = accountService.getByAccountNumber(accountNumber);
        // Show balance
        if (bankAccount == null) {
            System.out.println("Something went wrong, your bank account can not be found. Please contact your administrator!");
            returnToMainMenu(true);
            return;
        }

        System.out.printf(ACTION_MESSAGE, String.format("%,.2f", bankAccount.getAccountBalance()));
        returnToMainMenu(true);
    }

    @Override
    public void printGreetings() {
        System.out.printf(GREETING_MSG, ACTION_NAME);
    }
}
