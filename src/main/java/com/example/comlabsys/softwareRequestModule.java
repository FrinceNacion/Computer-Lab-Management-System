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

public class softwareRequestModule {
    public static Scanner inp = new Scanner(System.in);
    private static String softwareFilePath = "C:\\Users\\Frince\\comlabmonitoringsystem\\src\\main\\resources\\softwareStorage.json";
    private static File softwareFile = new File(softwareFilePath);
    private static JsonArray jsonArray;
    private static Gson gson = new Gson();

    public static void initialize() throws IOException {
        if (softwareFile.createNewFile()) {
            try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(softwareFile))) {
                jsonWriter.beginArray();
                jsonWriter.endArray();

            } catch (Exception e) {
                System.out.println("An error occurred in creating the file.");
            }
            System.out.println("Software record created successfully");
        } else {
            System.out.println("Software record already created");
        }
    }

    public static String pickDecision(String question) {
        String answer = "N/A";
        System.out.println(question + " (y/n)");
        String userOption = inp.nextLine();

        switch (userOption.toLowerCase()) {
            case "y":
                answer = "Yes";
                break;
            case "n":
                answer = "No";
                break;
            default:
                System.out.println("Invalid input, please try again");
                break;
        }
        return answer;
    }

    public static void addSoftwareRequest() throws IOException {
        softwareObject software;
        userAccount currentUser = accountFunctions.getCurrentUserByUid(accountFunctions.getUid());
        String softwareName, softwareVersion, submittedBy;
        String isFreeware, isLicensed;

        System.out.println("Please fill out the following information. ");
        System.out.print("Software Name: ");
        softwareName = inp.nextLine();
        System.out.print("Software Version: ");
        softwareVersion = inp.nextLine();
        submittedBy = currentUser.getName();
        isFreeware = pickDecision("Is software a freeware? ");
        isLicensed = pickDecision("Is software licensed? ");
        software = new softwareObject(softwareName, softwareVersion, submittedBy, isFreeware, isLicensed);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(softwareFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            jsonArray.add(gson.toJsonTree(software));
            utilityClass.putToJson(jsonArray, softwareFile);
            System.out.println("Software requested successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred, please try again.");
        }
    }

    public static void displaySoftwareObjectList() throws IOException {
        softwareObject[] softwareObjectList = getsoftwareRequestList();
        String software;

        System.out.println("\n Requested Softwares List: ");
        try {
            for (int i = 0; i < softwareObjectList.length; i++) {
                software = Integer.toString(i + 1) + ". "
                        + "Software Name: " + softwareObjectList[i].getSoftwareName()
                        + ", Software Version: " + softwareObjectList[i].getSoftwareVersion()
                        + ", License: " + softwareObjectList[i].getIsLicensed()
                        + ", Free Ware: " + softwareObjectList[i].getIsFreeware()
                        + ", Requested by: " + softwareObjectList[i].getSubmittedBy();
                System.out.println(software);
            }
        } catch (NullPointerException e) {
            System.out.println("There is no request yet.");
        }
    }

    public static softwareObject[] getsoftwareRequestList() throws IOException {
        softwareObject[] softwareObjectList = {};

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(softwareFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            softwareObjectList = gson.fromJson(jsonArray, softwareObject[].class);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (NullPointerException e) {
            System.out.println("File is empty.");
        }

        return softwareObjectList;
    }

    public static void updateSoftwareRequest() throws IOException {
        System.out.println("\n Which request would you like to update? (Refer to the list below)");
        displaySoftwareObjectList();
        System.out.println("Enter the request No. (refer to the list) which you want to update: ");

        try {
            int requestNo = Integer.parseInt(inp.nextLine());
            updateSoftwareRequestRecord(requestNo);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid input (refer to the list).");
        }
    }

    public static void updateSoftwareRequestRecord(int index) throws FileNotFoundException, IOException {
        softwareObject softwareObject;
        String requestStatus;

        System.out.println("\nPlease fill out the following information");
        requestStatus = pickRequestStatus();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(softwareFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            softwareObject = gson.fromJson(jsonArray.get(index), softwareObject.class);

            softwareObject.setRequestStatus(requestStatus);
            jsonArray.set(index, gson.toJsonTree(softwareObject));
            System.out.println("Request updated successfully");
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid input.");
        } catch (Exception e) {
            System.out.println("An error occurred, please try again.");
        }

    }

    public static String pickRequestStatus() {
        String requestStatus = "N/A";
        String useroption;

        System.out.println("\nPlease select the status for the request.");
        System.out.println("1. Approved");
        System.out.println("2. Denied");
        useroption = inp.nextLine();

        switch (useroption) {
            case "1":
                requestStatus = "Approved";
                break;
            case "2":
                requestStatus = "Denied";
                break;
            default:
                System.out.println("Invalid input, please try again.");
                break;
        }
        return requestStatus;
    }

}
