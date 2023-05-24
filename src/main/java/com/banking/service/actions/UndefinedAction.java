package com.banking.service.actions;

import javax.naming.OperationNotSupportedException;

public class UndefinedAction implements Action {
    @Override
    public void executeAction() throws OperationNotSupportedException {
        throw new OperationNotSupportedException("wrong action");
    }
}
