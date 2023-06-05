package com.banking.service;

import com.banking.entity.BankAccount;
import com.banking.service.connection.ConnectionHandler;
import com.banking.service.connection.SocketResponse;

public class AccountService {
    private final ConnectionHandler connectionHandler;

    public AccountService() {
        this.connectionHandler = ConnectionHandler.getInstance();
    }

    public BankAccount getByAccountNumber(long accountNumber) {
        SocketResponse socketResponse = connectionHandler.sendGetAccountRequest(accountNumber);

        if (socketResponse.isSuccessful()) {
            return (BankAccount) socketResponse.getData();
        } else {
            return  null;
        }
    }
}
