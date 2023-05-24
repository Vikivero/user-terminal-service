package com.banking.service;

public class ActionHandler {
    public void executeAction(int action) {

        switch(action) {
            case 0:
               executeExitAction();
                break;
            case 1:
                executeShowBalanceAction();
                break;
            case 2:
                TransferToOwnAccount();
                break;
            case 3:
                CashWithdrawal();
                break;
            case 4:
                FundsTransfer();
                break;
            default:
                // code block
        }

    }

    private void FundsTransfer() {
        System.out.println("");
    }

    private void CashWithdrawal() {
        System.out.println("");
    }

    private void TransferToOwnAccount() {
        System.out.println("");
    }

    private void executeShowBalanceAction() {
        System.out.println("Show balance");

    }

    private void executeExitAction() {
        System.out.println("Good bye!");
        System.exit(0);
    }
}
