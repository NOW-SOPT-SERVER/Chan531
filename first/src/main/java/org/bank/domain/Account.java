package org.bank.domain;

public class Account {

    private final long INIT_ACCOUNT_SUM_VALUE = 5000L;
    private final String NOT_ENOUGH_MONEY_IN_THE_ACCOUNT = "계좌에 돈이 부족합니다.";

    private final String accountNumber;
    private long accountBalance;

    public Account() {
        this.accountNumber = generateRandomAccountNumber();
        this.accountBalance = initAccountBalance();
    }

    private String generateRandomAccountNumber() {
        return null;
    }

    private long initAccountBalance() {
        return INIT_ACCOUNT_SUM_VALUE;
    }

    public void decreaseAccountBalance(long amount) {
        checkEnoughAccountBalanceInTheAccount(amount);
        this.accountBalance -= amount;
    }

    public void increaseAccountBalance(long amount) {
        this.accountBalance += amount;
    }

    private void checkEnoughAccountBalanceInTheAccount(long amount) {
        if (amount > this.accountBalance) {
            throw new IllegalArgumentException(NOT_ENOUGH_MONEY_IN_THE_ACCOUNT);
        }
    }
}
