package com.example.comlabsys;

import java.io.IOException;
import java.util.Scanner;

public class userPage {
    public static Scanner inp = new Scanner(System.in);

    public static void studentPage() throws IOException {
        boolean onRun = true;
        while (onRun) {
            System.out.println("\nWelcome to student's board, what would you like to do?");
            System.out.println("1. Post a feedback");
            System.out.println("2. Submit a Lab monitoring form (" + computerLabMonitoringModule.getNumOfOnlineForms()
                    + " form(s) online)");
            System.out.println("3. Exit");
            System.out.print("Go to: ");
            String userOption = inp.next();

            switch (userOption) {
                case "1":
                    feedbackModule.addFeedback();
                    break;
                case "2":
                    computerLabMonitoringModule.submitComputerLabMonitoringForm();
                    break;
                case "3":
                    onRun = false;
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
        }

    }

    public static void computerLabMonitoringPage() throws IOException {
        System.out.println("\nWelcome to computer lab monitoring page, what would you like to do?");
        System.out.println("1. Post a Computer Lab Monitoring form");
        System.out.println("2. Modify a Computer Lab Monitoring form");
        System.out.println("3. Exit");
        System.out.print("Go to: ");
        String userOption = inp.next();

        switch (userOption) {
            case "1":
                computerLabMonitoringModule.postComputerLabMonitoringForm();
                break;
            case "2":
                computerLabMonitoringModule.modifyForm();
                break;
            case "3":
                break;
            default:
                System.out.println("Invalid input, please try again.");
                break;
        }
    }

    public static void facultyPage() throws IOException {
        boolean onRun = true;
        while (onRun) {
            System.out.println("\nWelcome to faculty's board, what would you like to do?");
            System.out.println("1. Report a problem");
            System.out.println("2. Computer Lab Monitoring form ");
            System.out.println("3. Computer Laboratory equipment record");
            System.out.println("4. See borrow record");
            System.out.println("5. Request for software");
            System.out.println("6. Exit");
            System.out.print("Go to: ");
            String userOption = inp.next();

            switch (userOption) {
                case "1":
                    reportModule.addReport();
                    break;

                case "2":
                    computerLabMonitoringPage();
                    break;

                case "3":
                    equipmentModule.displayEquipmentList();
                    break;

                case "4":
                    equipmentModule.displayBorrowedEquipmentList();
                    break;

                case "5":
                    System.out.println("Request for software");
                    break;

                case "6":
                    onRun = false;
                    break;

                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
        }
    }

    public static void userMain(String accountType) throws IOException {
        utilityClass.printDivider();
        switch (accountType) {
            case "student":
                studentPage();
                break;
            case "faculty":
                facultyPage();
                break;
            default:
                System.out.println("Invalid account type, please try again.");
                break;
        }
    }
}
