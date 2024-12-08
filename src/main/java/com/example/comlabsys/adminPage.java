package com.example.comlabsys;

import java.io.IOException;
import java.util.Scanner;

public class adminPage {
    public static Scanner inp = new Scanner(System.in);

    // User management module
    public static void manageUsers() {
        utilityClass.printDivider();
        System.out.println("User management page, what would you like to do?");
        System.out.println("1. See all accounts");
        System.out.println("2. Remove user account");
        System.out.println("3. Modify user account");
        System.out.println("4. Back");
        System.out.println("Go to: ");
        String userOption = inp.next();

        switch (userOption) {
            case "1":
                System.out.println("See all accounts");
                accountFunctions.printUserList();
                break;
            case "2":
                System.out.println("Remove user account");
                accountFunctions.removeUser();
                break;
            case "3":
                System.out.println("Modify user account");
                accountFunctions.modifyAccount();
                break;
            case "4":
                System.out.println("Back");
                break;
            default:
                System.out.println("Invalid input, please try again.");
                break;
        }

    }

    public static void adminMain() throws IOException {
        boolean onRun = true;
        while (onRun) {
            String adminOption;
            utilityClass.printDivider();
            System.out.println("Welcome to admin's board, what would you like to do?");
            System.out.println("1. Manage users");
            System.out.println("2. Feedback/s");
            System.out.println("3. Reports");
            System.out.println("4. See Lab record");
            System.out.println("5. See Equipment record");
            System.out.println("6. See Borrow record");
            System.out.println("7. Requested softwares");
            System.out.println("8. Exit");
            System.out.println("Go to: ");
            adminOption = inp.next();

            switch (adminOption) {
                case "1":
                    System.out.println("Manage users");
                    manageUsers();
                    break;
                case "2":
                    utilityClass.printDivider();
                    System.out.println("Feedback/s");
                    feedbackModule.displayFeedbackList();
                    break;
                case "3":
                    System.out.println("Reports");
                    break;
                case "4":
                    System.out.println("See Lab record");
                    break;
                case "5":
                    System.out.println("See Equipment record");
                    break;
                case "6":
                    System.out.println("See Borrow record");
                    break;
                case "7":
                    System.out.println("Requested softwares");
                    break;
                case "8":
                    System.out.println("Exit");
                    onRun = false;
                    break;
                default:
                    break;
            }
            utilityClass.printDivider();
        }
    }
}
