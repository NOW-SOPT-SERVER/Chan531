package org.bank.domain;

public class Customer {

    private final String name;
    private final Account account;

    public Customer(String name) {
        this.name = name;
        this.account = new Account();
    }

    public String getName() {
        return this.name;
    }

    public boolean matchName(String name) {
        return this.name.equals(name);
    }

    public void remit(long amount) {
        this.account.decreaseAccountBalance(amount);
    }

    public void beDeposited(long amount){
        this.account.increaseAccountBalance(amount);
    }
}
