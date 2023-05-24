package com.banking.service.actions;

public class ExitAction implements Action{

    @Override
    public void executeAction() {
        System.out.println("Good bye!");
        System.exit(0);
    }
}
