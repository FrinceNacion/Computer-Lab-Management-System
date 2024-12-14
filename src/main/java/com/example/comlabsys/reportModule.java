package com.example.comlabsys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonWriter;

public class reportModule {
    public static Scanner inp = new Scanner(System.in);
    private static File reportFile = new File(
            "C:\\Users\\Frince\\comlabmonitoringsystem\\src\\main\\resources\\reportStorage.json");
    private static String reportFilePath = "C:\\Users\\Frince\\comlabmonitoringsystem\\src\\main\\resources\\reportStorage.json";
    private static JsonArray jsonArray;
    private static Gson gson = new Gson();

    public static void initializeReportModule() throws IOException {
        if (reportFile.createNewFile()) {
            try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(reportFile))) {
                jsonWriter.beginArray();
                jsonWriter.endArray();

            } catch (Exception e) {
                System.out.println("An error occurred in creating the file.");
            }
            System.out.println("Report record created successfully");
        } else {
            System.out.println("Report record already created");
        }
    }

    public static void addReport() throws IOException {
        String reportHeader, reportBody, dateSubmitted, submittedBy;
        reportObject report;

        submittedBy = accountFunctions.getCurrentUserByUid(accountFunctions.getUid()).getUsername();
        dateSubmitted = utilityClass.getCurrentDate();

        System.out.print("Write your report header: ");
        reportHeader = inp.nextLine();
        System.out.print("Write your report body: ");
        reportBody = inp.nextLine();

        report = new reportObject(reportHeader, reportBody, dateSubmitted, submittedBy);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(reportFile))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            System.out.println("Report posted successfully");

        } catch (FileNotFoundException e) {
            jsonArray = new JsonArray();
        } catch (NullPointerException e) {
            jsonArray = new JsonArray();
        }

        jsonArray.add(gson.toJsonTree(report));

        utilityClass.putToJson(jsonArray, reportFile);
    }

    public static void displayReportList() throws IOException {
        reportObject[] reportList = getReportList();

        utilityClass.printDivider();
        if (reportList.length != 0) {
            for (int i = 0; i < reportList.length; i++) {
                String report = Integer.toString(i + 1) + ". " + reportList[i].getReportHeader()
                        + " submitted by " + reportList[i].getSubmittedBy() + " on "
                        + reportList[i].getDateSubmitted();
                System.out.println(report);
            }
        } else {
            System.out.println("There is no report yet.");
        }
    }

    public static reportObject[] getReportList() throws IOException {
        reportObject[] reportList = {};

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(reportFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            reportList = gson.fromJson(jsonArray, reportObject[].class);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (NullPointerException e) {
            System.out.println("File is empty.");
        }

        return reportList;

    }

    // admin functions
    public static void removeReport() throws IOException {
        System.out.println("Which report would you like to remove?");
        displayReportList();
        System.out.print("Enter the report No. (refer to the list) which you want to remove: ");
        int reportNo = inp.nextInt();
        removeReportRecord(reportNo - 1);
    }// punta kayo sa sa Live share na extension tas click nyo yung session chat

    public static void removeReportRecord(int index) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(reportFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            jsonArray.remove(index);
            utilityClass.putToJson(jsonArray, reportFile);
            System.out.println("Report removed successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid input.");
        } catch (Exception e) {
            System.out.println("An error occurred, please try again.");
        }
    }
}
