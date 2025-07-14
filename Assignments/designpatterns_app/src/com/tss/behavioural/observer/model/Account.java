package com.tss.behavioural.observer.model;

import java.util.LinkedList;
import java.util.List;

public class Account {
    private int accountNumber;
    private String name;
    private double balance;
    private List<INotifier> notifiers = new LinkedList<>();

    public Account(int accountNumber, String name) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = 0.0;
    }

    public void deposit(double amount, String baseMessage) {
        if (notifiers.isEmpty()) {
            System.out.println("No notification mode selected. Please add at least one.");
        }
        balance += amount;
        notifyAllObservers(baseMessage + " Deposit of " + amount + " successful. Balance: " + balance);
    }

    public void withdraw(double amount, String baseMessage) throws InsufficientFundsException {
        if (notifiers.isEmpty()) {
            System.out.println("No notification mode selected. Please add at least one.");
        }

        if (balance >= amount) {
            balance -= amount;
            notifyAllObservers(baseMessage + " Withdrawal of " + amount + " successful. Balance: " + balance);
        } else {
            notifyAllObservers(baseMessage + " Withdrawal of " + amount + " failed due to insufficient balance. Balance: " + balance);
            throw new InsufficientFundsException("Insufficient balance for withdrawal.");
        }
    }

    public void registerNotifier(INotifier notifier) {
        for (INotifier n : notifiers) {
            if (n.getName().equalsIgnoreCase(notifier.getName())) {
                System.out.println(notifier.getName() + " notifier is already registered.");
                return;
            }
        }
        notifiers.add(notifier);
        System.out.println(notifier.getName() + " notifier registered.");
    }

    public void removeNotifier(String name) {
        boolean removed = notifiers.removeIf(n -> n.getName().equalsIgnoreCase(name));
        if (removed) {
            System.out.println(name + " notifier removed.");
        } else {
            System.out.println(name + " notifier was not registered.");
        }
    }

    public void printRegisteredNotifiers() {
        if (notifiers.isEmpty()) {
            System.out.println("No notifiers registered.");
        } else {
            System.out.println("Currently registered notifiers:");
            for (INotifier notifier : notifiers) {
                System.out.println("- " + notifier.getName());
            }
        }
    }

    private void notifyAllObservers(String message) {
        for (INotifier notifier : notifiers) {
            notifier.sendNotification(this, message);
        }
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account {" +
                "accountNumber=" + accountNumber +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
