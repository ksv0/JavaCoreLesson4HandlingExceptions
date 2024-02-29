package ksv;

import ksv.acc.Account;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    List<Account> accounts;

    List<Transaction> transactions;

    public Bank() {
        accounts = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }
    public void removeAccount(Account account) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i) == account) {
                accounts.remove(i);
            }
        }
    }
    public List<Account> getAccounts() {
        return accounts;
    }
    public Account getAccount(long id) {
        for (Account account : accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }



    public void viewRecipients() {
        System.out.println("Список счетов");

        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    public void transfer(double amount, Account currentAccount, Account account) throws InsufficientFundsException {
        Transaction transaction = new Transaction(amount, currentAccount, account);
        transaction.transfer();
        transactions.add(transaction);
    }

    public void deposit(long id, double amount) {
        getAccount(id).deposit(amount);
    }

    public void withdraw(long id, double amount) throws InsufficientFundsException {
        getAccount(id).withdraw(amount);
    }
}
