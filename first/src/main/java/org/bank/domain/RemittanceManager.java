package org.bank.domain;

public class RemittanceManager {

    private final long REMIT_MAX_LIMIT_AMOUNT = 1000000000;
    private final String EXCEED_MAX_POSSIBLE_REMIT_AMOUNT = "송금 가능 금액을 초과하였습니다.";

    public void remitMoney(Customer remittor, Customer payee, long amount) {
        checkMaxRemitAmount(amount);
        remittor.remit(amount);
        payee.beDeposited(amount);
    }

    private void checkMaxRemitAmount(long amount) {
        if (amount > REMIT_MAX_LIMIT_AMOUNT) {
            throw new IllegalArgumentException(EXCEED_MAX_POSSIBLE_REMIT_AMOUNT);
        }
    }
}
