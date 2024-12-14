package com.example.comlabsys;

import java.io.IOException;
import java.util.Scanner;

public class userPage {
    public static Scanner inp = new Scanner(System.in);

    public static void studentPage() throws IOException {
        System.out.println("Welcome to student's board, what would you like to do?");
        System.out.println("1. Post a feedback");
        System.out.println("2. Submit a Lab monitoring form");
        System.out.println("3. Computer Laboratory equipment record");
        System.out.println("4. See borrow record");
        System.out.println("5. Exit");
        System.out.print("Go to: ");
        String userOption = inp.next();

        switch (userOption) {
            case "1":
                System.out.println("Post a feedback");
                feedbackModule.addFeedback();
                break;

            default:
                break;
        }
    }

    public static void facultyPage() throws IOException {
        System.out.println("Welcome to faculty's board, what would you like to do?");
        System.out.println("1. Report a problem");
        System.out.println("2. Monitor Lab and equipment");
        System.out.println("3. Computer Laboratory equipment record");
        System.out.println("4. See borrow record");
        System.out.println("5. Request for software");
        System.out.println("6. Exit");
        System.out.print("Go to: ");
        String userOption = inp.next();

        switch (userOption) {
            case "1":
                System.out.println("Report a problem");
                reportModule.addReport();
                break;

            default:
                break;
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
