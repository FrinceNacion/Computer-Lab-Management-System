package com.example.comlabsys;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner inp = new Scanner(System.in);

    private static void logIn() throws IOException {
        String userOption, username, password;
        boolean onRun = true;
        boolean isLoggedIn = false;
        do {
            utilityClass.printDivider();
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Register");
            System.out.println("4. Forgot Password");
            System.out.println("5. Exit");
            System.out.print("Go to: ");
            userOption = inp.next();
            utilityClass.printDivider();

            switch (userOption) {
                case "1":
                    System.out.print("Enter username: ");
                    username = inp.next();
                    System.out.print("Enter password: ");
                    password = inp.next();
                    try {
                        if (accountFunctions.validateCredentials(username, password)) {
                            accountFunctions.readRecord(username, password);
                            isLoggedIn = true;
                            userPage.userMain(
                                    accountFunctions.getCurrentUserByCredentials(username, password).getAccountType());
                        } else {
                            System.out.println("No account found, please try again.");
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    System.out.print("Enter username: ");
                    username = inp.next();
                    System.out.print("Enter password: ");
                    password = inp.next();
                    try {
                        if (accountFunctions.validateCredentials(username, password)) {
                            accountFunctions.readRecord(username, password);
                            isLoggedIn = true;
                            adminPage.adminMain();
                        } else {
                            System.out.println("No account found, please try again.");
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    accountFunctions.registerUser();
                    break;
                case "4":
                    accountFunctions.forgotPassword();
                    break;
                case "5":
                    onRun = false;
                    break;
                default:
                    System.out.println("--Invalid Input--");
            }
        } while (onRun && !isLoggedIn);
    }

    public static void main(String[] args) throws IOException {
        accountFunctions.initialize();
        feedbackModule.initializeFeedbackModule();
        logIn();
    }
}
