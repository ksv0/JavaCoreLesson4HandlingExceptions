package ksv;

import ksv.acc.Account;

public class Transaction {
    private static int nextId = 0;
    private final int id;
    private final Double amount;
    private final Account from;
    private final Account to;

    public Transaction(Double amount, Account from, Account to) {
        id = ++nextId;
        this.amount = amount;
        this.from = from;
        this.to = to;
    }
    public void transfer() throws InsufficientFundsException {
        from.withdraw(amount);
        to.deposit(amount);
    }
    public void refund() throws InsufficientFundsException {
        from.deposit(amount);
        to.withdraw(amount);
    }

    public int getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public Account getFrom() {
        return from;
    }

    public Account getTo() {
        return to;
    }

}
