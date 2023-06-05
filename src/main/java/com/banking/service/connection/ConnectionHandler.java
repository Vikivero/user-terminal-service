package com.banking.service.connection;

public class ConnectionHandler {
    private static final ConnectionHandler instance = new ConnectionHandler();
    private final SocketConnectionClient client;

    public static ConnectionHandler getInstance() {
        return instance;
    }

    private ConnectionHandler() {
        client = new SocketConnectionClient();
    }

    public SocketResponse sendWithdrawRequest(long accountNumber, double amount) {
        SocketRequest sr = new SocketRequest(accountNumber, SocketRequest.RequestType.WITHDRAW);
        sr.getBody().put("amount", amount);

        return client.makeRequest(sr);
    }

    public SocketResponse sendDepositRequest(long accountNumber, double amount) {
        SocketRequest sr = new SocketRequest(accountNumber, SocketRequest.RequestType.DEPOSIT);
        sr.getBody().put("amount", amount);

        return client.makeRequest(sr);
    }

    public SocketResponse sendFundsTransferRequest(long accountNumber, long targetAccountNumber, double amount) {
        SocketRequest sr = new SocketRequest(accountNumber, SocketRequest.RequestType.TRANSFER);
        sr.getBody().put("targetAccountNumber", targetAccountNumber);
        sr.getBody().put("amount", amount);

        return client.makeRequest(sr);
    }

    public SocketResponse sendGetAccountRequest(long accountNumber) {
        SocketRequest sr = new SocketRequest(accountNumber, SocketRequest.RequestType.GETACCOUNT);

        return client.makeRequest(sr);
    }

    public SocketResponse sendAuthenticationRequest(long accountNumber, String password) {
        SocketRequest sr = new SocketRequest(accountNumber, SocketRequest.RequestType.AUTHENTICATION);
        sr.getBody().put("password", password);

        return client.makeRequest(sr);
    }
}
