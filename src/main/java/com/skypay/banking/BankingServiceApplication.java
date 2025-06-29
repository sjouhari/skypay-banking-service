package com.skypay.banking;

import com.skypay.banking.exception.InsufficientBalanceException;
import com.skypay.banking.service.Account;
import com.skypay.banking.service.AccountService;

import java.util.Scanner;

public class BankingServiceApplication {

    public static void main(String[] args) {
        System.out.println("🏦 Banking Service Application");
        System.out.println("Welcome to SkyPay Banking!");

        Scanner scanner = new Scanner(System.in);
        AccountService account = new Account();

        int choice = 1;

        while (choice != 0) {
            System.out.println("\n📋 Menu:");
            System.out.println("1️⃣  Deposit");
            System.out.println("2️⃣  Withdraw");
            System.out.println("3️⃣  Print Statement");
            System.out.println("0️⃣  Exit");
            System.out.print("\n➡️  Enter your choice: ");

            choice = readValidInteger(scanner);

            switch (choice) {
                case 1:
                    try {
                        System.out.print("💰 Enter the amount to deposit: ");
                        account.deposit(readValidInteger(scanner));
                    } catch (IllegalArgumentException e) {
                        System.out.println("⚠️  " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.print("💸 Enter the amount to withdraw: ");
                        account.withdraw(readValidInteger(scanner));
                    } catch (InsufficientBalanceException e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                    break;
                case 3:
                    account.printStatement();
                    break;
                case 0:
                    System.out.println("🚪 Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("❗ Invalid choice. Try again.");
                    break;
            }
        }
    }

    private static int readValidInteger(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.print("❌ Invalid input. Please enter a valid number: ");
                scanner.next(); // discard invalid input
            }
        }
    }
}