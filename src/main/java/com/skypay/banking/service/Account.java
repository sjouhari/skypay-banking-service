package com.skypay.banking.service;

import com.skypay.banking.models.Statement;
import com.skypay.banking.exception.InsufficientBalanceException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {

    private List<Statement> statements = new ArrayList<>();
    int balance = 0;

    @Override
    public void deposit(int amount) {
        if(amount <= 0) throw new IllegalArgumentException("Deposit amount must be greater than 0");
        statements.add(new Statement(amount, LocalDateTime.now()));
        balance += amount;
        System.out.println("\u2705 Deposit successful!");
    }

    @Override
    public void withdraw(int amount) {
        if(amount > balance) throw new InsufficientBalanceException("Insufficient balance.");
        statements.add(new Statement(-amount, LocalDateTime.now()));
        balance -= amount;
        System.out.println("Withdraw successful!");
    }

    @Override
    public void printStatement() {
        List<Statement> reversedStatements = new ArrayList<>(statements.reversed());

        int currentBalance = 0;
        System.out.println("\nDate\t\t||\tAmount\t||\tBalance");
        for (Statement statement : reversedStatements) {
            System.out.println(statement.date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\t||\t" + statement.amount() + "\t||\t" + (balance - currentBalance));
            currentBalance += statement.amount();
        }
    }

}
