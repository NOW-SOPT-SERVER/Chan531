package org.bank;

import org.bank.domain.Customer;
import org.bank.domain.RemittanceManager;
import org.bank.view.Input;
import org.bank.view.Output;

import java.util.Scanner;

public class BankManager {

    private final int SERVICE_END = -1;
    private final int SERVICE_CONTINUE = 0;
    private final int SERVICE_INTRODUCTION = 1;
    private final int OPEN_ACCOUNT = 2;
    private final int ACCESS_ACCOUNT = 3;
    private final int REMIT_COMMAND = 1;

    private final RemittanceManager remittanceManager = new RemittanceManager();
    private final Input input = new Input();
    private final Output output = new Output();
    private final Scanner scanner = new Scanner(System.in);
    private Customer customer;
    private Customer payee = new Customer("payee");

    public void run() {
        while (true) {
            int initInput = inputInitLevel();
            if (manageInitCommand(initInput) == SERVICE_END) {
                continue;
            }
            int accountInput = inputAccountLevel();
            manageAccountCommand(accountInput);
            output.successRemit();
            int lastInput = inputLastLevel();
            if (lastInput == SERVICE_END) {
                break;
            }
        }
    }

    private int inputInitLevel() {
        return input.initLevelMessages(scanner);
    }

    private int manageInitCommand(int initInput) {
        switch (initInput) {
            case SERVICE_INTRODUCTION -> output.introduceService();
            case OPEN_ACCOUNT -> {
                String name = input.openAccount(scanner);
                manageOpenAccountCommand(name);
                output.successOpenAccount();
            }
            case ACCESS_ACCOUNT -> {
                String name = input.accessAccount(scanner);
                if (manageAccessAccountCommand(name)) {
                    return SERVICE_CONTINUE;
                }
            }
        }
        return SERVICE_END;
    }

    private void manageOpenAccountCommand(String name) {
        customer = new Customer(name);
    }

    private boolean manageAccessAccountCommand(String name) {
        if (customer.matchName(name)) {
            output.successAccessAccount();
            return true;
        }
        output.failAccessAccount();
        return false;
    }

    private int inputAccountLevel() {
        return input.inputAccountLevelMessage(scanner, customer);
    }

    private void manageAccountCommand(int accountInput) {
        switch (accountInput) {
            case REMIT_COMMAND -> remittanceManager.remitMoney(customer, payee, input.inputRemittanceMessage(scanner));
        }
    }

    private int inputLastLevel() {
        return input.inputLastLevelMessage(scanner);
    }
}
