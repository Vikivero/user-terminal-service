package com.banking.service.actions;

import javax.naming.OperationNotSupportedException;

public interface Action {
    void executeAction() throws OperationNotSupportedException;
}
