package ksv.acc;

import ksv.InsufficientFundsException;

public abstract class Account {
    protected static long nextId = 0;
    protected long id;
    protected String name;
    protected double balance;


    public Account(String name, double balance) {
        this.id = ++nextId;
        this.name = name;
        if (balance < 0) {
            throw new IllegalArgumentException("Недостаточно средств на счете");
        }
        this.balance = balance;
    }

    public Account() {
        this("Гость", 0);
    }


    public void withdraw(double amount) throws InsufficientFundsException {
        if (balance - amount < 0) {
            throw new InsufficientFundsException("Недостаточно средств на счете");
        }
        balance -= amount;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("Account[id=%d, name=%s, balance=%.2f, ", id, name, balance);
    }
}
