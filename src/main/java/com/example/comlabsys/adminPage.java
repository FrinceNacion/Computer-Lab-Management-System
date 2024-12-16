package com.example.comlabsys;

import java.io.IOException;
import java.util.Scanner;

public class adminPage {
    public static Scanner inp = new Scanner(System.in);

    // User management module
    public static void manageUsers() {
        utilityClass.printDivider();
        System.out.println("\nUser management page, what would you like to do?");
        System.out.println("1. See all accounts");
        System.out.println("2. Remove user account");
        System.out.println("3. Modify user account");
        System.out.println("4. Back");
        System.out.print("Go to: ");
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
        System.out.println("\nFeedback management page, what would you like to do?");
        System.out.println("1. See all feedbacks");
        System.out.println("2. Remove feedback");
        System.out.println("3. Back");
        System.out.print("Go to: ");
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
        System.out.println("\nReport management page, what would you like to do?");
        System.out.println("1. See all reports");
        System.out.println("2. Remove report");
        System.out.println("3. Back");
        System.out.print("Go to: ");
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
        System.out.println("\nComputer Lab Monitoring form management page, what would you like to do?");
        System.out.println("1. See all Computer Lab Monitoring forms");
        System.out.println("2. Remove Computer Lab Monitoring form");
        System.out.println("3. Back");
        System.out.print("Go to: ");
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

    public static void manageEquipment() throws IOException {
        System.out.println("\nEquipment management page, what would you like to do?");
        System.out.println("1. See all equipments");
        System.out.println("2. See borrowed equipments");
        System.out.println("3. Add new equipment");
        System.out.println("4. Update equipment");
        System.out.println("5. Remove equipment");
        System.out.println("6. Back");
        System.out.print("Go to: ");
        String userOption = inp.next();

        switch (userOption) {
            case "1":
                equipmentModule.displayEquipmentList();
                break;
            case "2":
                equipmentModule.displayBorrowedEquipmentList();
                break;
            case "3":
                equipmentModule.addEquipment();
                break;
            case "4":
                equipmentModule.updateEquipment();
                break;
            case "5":
                equipmentModule.removeEquipment();
                break;
            case "6":
                break;
            default:
                System.out.println("Invalid input, please try again.");
                break;
        }
    }

    public static void manageSoftwareRequest() throws IOException {
        System.out.println("\nSoftware Requests management page, what would you like to do?");
        System.out.println("1. See request/s");
        System.out.println("2. Update request status");
        System.out.println("3. Back");
        System.out.print("Go to: ");
        String userOption = inp.next();

        switch (userOption) {
            case "1":
                softwareRequestModule.displaySoftwareObjectList();
                break;
            case "2":
                softwareRequestModule.updateSoftwareRequest();
                break;
            case "3":
                break;
            default:
                System.out.println("Invalid input, please try again");
                break;
        }

    }

    public static void adminOptionPage() throws IOException {
        boolean onRun = true;
        while (onRun) {
            String adminOption;
            utilityClass.printDivider();
            System.out.println("\nWelcome to admin's board, what would you like to do?");
            System.out.println("1. Manage users");
            System.out.println("2. Manage Feedbacks");
            System.out.println("3. Manage Reports");
            System.out.println("4. Manage Computer Lab Monitoring forms");
            System.out.println("5. Manage Equipments");
            System.out.println("6. Manage Software Requests");
            System.out.println("7. Exit");
            System.out.print("Go to: ");
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
                    manageEquipment();
                    break;
                case "6":
                    manageSoftwareRequest();
                    break;
                case "7":
                    System.out.println("Exit");
                    onRun = false;
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
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
