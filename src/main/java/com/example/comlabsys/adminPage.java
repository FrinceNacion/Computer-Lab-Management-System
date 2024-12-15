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

    public static void manageFeedbacks() throws IOException {
        utilityClass.printDivider();
        System.out.println("Feedback management page, what would you like to do?");
        System.out.println("1. See all feedbacks");
        System.out.println("2. Remove feedback");
        System.out.println("3. Back");
        System.out.println("Go to: ");
        String feedbackOption = inp.next();

        switch (feedbackOption) {
            case "1":
                System.out.println("See all feedbacks");
                feedbackModule.displayFeedbackList();
                break;
            case "2":
                System.out.println("Remove feedback");
                feedbackModule.removeFeedback();
                break;
            case "3":
                System.out.println("Back");
                break;
            default:
                System.out.println("Invalid input, please try again.");
                break;
        }
    }

    public static void manageReports() throws IOException {
        utilityClass.printDivider();
        System.out.println("Report management page, what would you like to do?");
        System.out.println("1. See all reports");
        System.out.println("2. Remove report");
        System.out.println("3. Back");
        System.out.println("Go to: ");
        String reportOption = inp.next();

        switch (reportOption) {
            case "1":
                System.out.println("See all reports");
                reportModule.displayReportList();
                break;
            case "2":
                System.out.println("Remove report");
                reportModule.removeReport();
                break;
            case "3":
                System.out.println("Back");
                break;
            default:
                System.out.println("Invalid input, please try again.");
                break;
        }
    }

    public static void manageComputerLabMonitoringForms() throws IOException {
        System.out.println("Computer Lab Monitoring form management page, what would you like to do?");
        System.out.println("1. See all Computer Lab Monitoring forms");
        System.out.println("2. Remove Computer Lab Monitoring form");
        System.out.println("3. Back");
        System.out.println("Go to: ");
        String userOption = inp.next();

        switch (userOption) {
            case "1":
                System.out.println("See all Computer Lab Monitoring forms");
                computerLabMonitoringModule.displayComputerLabMonitoringFormList();
                break;
            case "2":
                System.out.println("Remove Computer Lab Monitoring form");
                computerLabMonitoringModule.removeForm();
                break;
            case "3":
                System.out.println("Back");
                break;
            default:
                System.out.println("Invalid input, please try again.");
                break;
        }
    }

    public static void adminOptionPage() throws IOException {
        boolean onRun = true;
        while (onRun) {
            String adminOption;
            utilityClass.printDivider();
            System.out.println("Welcome to admin's board, what would you like to do?");
            System.out.println("1. Manage users");
            System.out.println("2. Manage Feedbacks");
            System.out.println("3. Manage Reports");
            System.out.println("4. Manage Computer Lab Monitoring forms");// <- lagay ko dito 3 modules
            System.out.println("5. See Equipment record");
            System.out.println("6. See Borrow record");
            System.out.println("7. Requested softwares");
            System.out.println("8. Exit");
            System.out.println("Go to: ");
            adminOption = inp.next();

            switch (adminOption) {
                case "1":
                    manageUsers();
                    break;
                case "2":
                    manageFeedbacks();
                    break;
                case "3":
                    manageReports();
                    break;
                case "4":
                    manageComputerLabMonitoringForms();
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
        }
    }

    public static void adminMain(String accountType) throws IOException {
        if (accountType.equals("ADMIN")) {
            adminOptionPage();
        } else {
            System.out.println("Invalid account type.");
        }

    }
}
