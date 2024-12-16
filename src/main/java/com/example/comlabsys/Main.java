package com.example.comlabsys;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner inp = new Scanner(System.in);

    /**
     * Prompts user to log in as student or admin, register, forgot password, or
     * exit.
     * If user chooses to log in, it will verify the credentials and go to userPage
     * or adminPage
     * depending on the account type. If user chooses to register, it will go to
     * registerUser.
     * If user chooses forgot password, it will go to forgotPassword.
     * If user chooses exit, it will terminate the program.
     *
     * @throws IOException if there is an error when reading or writing to the file.
     */
    private static void logIn() throws IOException {
        String userOption, username, password;
        boolean onRun = true;
        boolean isLoggedIn = false;
        do {
            utilityClass.printDivider();
            System.out.println("1. Login as Student / Faculty");
            System.out.println("2. Login as Admin");
            System.out.println("3. Register");
            System.out.println("4. Forgot Password");
            System.out.println("5. Exit");
            System.out.print("Go to: ");
            userOption = inp.next();
            utilityClass.printDivider();

            switch (userOption) {
                case "1":
                    System.out.print("\nEnter username: ");
                    username = inp.next();
                    System.out.print("Enter password: ");
                    password = inp.next();
                    try {
                        accountFunctions.readRecord(username, password);
                        isLoggedIn = true;
                        userPage.userMain(
                                accountFunctions.getCurrentUserByCredentials(username, password).getAccountType());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (NullPointerException e) {
                        System.out.println("No account found, please try again.");
                    }
                    break;
                case "2":
                    System.out.print("\nEnter username: ");
                    username = inp.next();
                    System.out.print("Enter password: ");
                    password = inp.next();
                    try {
                        accountFunctions.readRecord(username, password);
                        isLoggedIn = true;
                        adminPage.adminMain(
                                accountFunctions.getCurrentUserByCredentials(username, password).getAccountType());
                    } catch (NullPointerException e) {
                        System.out.println("No account found, please try again.");
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
            System.out.println("");
        } while (onRun && !isLoggedIn);
    }

    // Click nyo yung Run command dito \/
    public static void main(String[] args) throws IOException {
        equipmentModule.initialize();
        accountFunctions.initialize();
        feedbackModule.initializeFeedbackModule();
        reportModule.initializeReportModule();
        computerLabMonitoringModule.initializeComputerLabMonitoringModule();
        logIn();
    }
}
