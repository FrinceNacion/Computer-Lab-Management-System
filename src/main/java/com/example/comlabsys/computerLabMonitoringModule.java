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
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonWriter;

public class computerLabMonitoringModule {
    public static Scanner inp = new Scanner(System.in);
    private static File computerLabMonitoringFile = new File(
            "C:\\Users\\Frince\\comlabmonitoringsystem\\src\\main\\resources\\computerLabMonitoringStorage.json");
    private static String computerLabMonitoringFilePath = "C:\\Users\\Frince\\comlabmonitoringsystem\\src\\main\\resources\\computerLabMonitoringStorage.json";
    private static JsonArray jsonArray;
    private static Gson gson = new Gson();

    public static void initializeComputerLabMonitoringModule() throws IOException {
        if (computerLabMonitoringFile.createNewFile()) {
            try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(computerLabMonitoringFile))) {
                jsonWriter.beginArray();
                jsonWriter.endArray();

            } catch (Exception e) {
                System.out.println("An error occurred in creating the file.");
            }
            System.out.println("Computer Lab Monitoring record created successfully");
        } else {
            System.out.println("Computer Lab Monitoring record already created");
        }
    }

    public static void postComputerLabMonitoringForm() throws IOException {
        String roomName, description, status;
        System.out.println("Please fill out the following information, put N/A if not applicable.");
        System.out.print("Room Name: ");
        roomName = inp.nextLine();
        System.out.print("Description: ");
        description = inp.nextLine();
        status = "Online";

        computerLabMonitoringFormObject computerLabMonitoringFormObject = new computerLabMonitoringFormObject(roomName,
                description, status);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(computerLabMonitoringFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
        } catch (FileNotFoundException e) {
            jsonArray = new JsonArray();
        } catch (NullPointerException e) {
            jsonArray = new JsonArray();
        }

        jsonArray.add(gson.toJsonTree(computerLabMonitoringFormObject));

        utilityClass.putToJson(jsonArray, computerLabMonitoringFile);

    }

    public static void submitComputerLabMonitoringForm() {
        System.out.println("Computer Lab Monitoring form submitted successfully.");
    }

    public static String getNumOfOnlineForms() throws IOException {
        computerLabMonitoringFormObject[] computerLabMonitoringFormList = getComputerLabMonitoringFormList();
        int numOfOnlineForms = 0;

        for (int i = 0; i < computerLabMonitoringFormList.length; i++) {
            if (computerLabMonitoringFormList[i].getStatus().equals("Online")) {
                numOfOnlineForms++;
            }
        }

        return numOfOnlineForms + "";
    }

    public static void displayOnlineComputerLabMonitoringFormList() throws IOException {
        computerLabMonitoringFormObject[] computerLabMonitoringFormList = getComputerLabMonitoringFormList();

        if (computerLabMonitoringFormList.length != 0) {
            for (int i = 0; i < computerLabMonitoringFormList.length; i++) {
                if (computerLabMonitoringFormList[i].getStatus().equals("Online")) {
                    String computerLabMonitoringForm = Integer.toString(i + 1) + ". Room Name: " +
                            computerLabMonitoringFormList[i].getRoomName() + ", Posted By: " +
                            computerLabMonitoringFormList[i].getPostedBy() + ", Posted at: " +
                            computerLabMonitoringFormList[i].getTime() + ", Date: " +
                            computerLabMonitoringFormList[i].getDate() + ", Description: " +
                            computerLabMonitoringFormList[i].getDescription() + ", Status: " +
                            computerLabMonitoringFormList[i].getStatus();
                    System.out.println(computerLabMonitoringForm);
                }
            }
        } else {
            System.out.println("There is no computer lab monitoring form yet.");
        }
    }

    public static void displayComputerLabMonitoringFormList() throws IOException {
        computerLabMonitoringFormObject[] computerLabMonitoringFormList = getComputerLabMonitoringFormList();

        if (computerLabMonitoringFormList.length != 0) {
            for (int i = 0; i < computerLabMonitoringFormList.length; i++) {
                String computerLabMonitoringForm = Integer.toString(i + 1) + ". Room Name: "
                        + computerLabMonitoringFormList[i].getRoomName() + ", Posted By: "
                        + computerLabMonitoringFormList[i].getPostedBy() + ", Posted at: "
                        + computerLabMonitoringFormList[i].getTime() + ", Date: "
                        + computerLabMonitoringFormList[i].getDate() + ", Description: "
                        + computerLabMonitoringFormList[i].getDescription() + ", Status: "
                        + computerLabMonitoringFormList[i].getStatus();
                System.out.println(computerLabMonitoringForm);
            }
        } else {
            System.out.println("There is no computer lab monitoring form yet.");
        }

    }

    public static computerLabMonitoringFormObject[] getComputerLabMonitoringFormList() throws IOException {
        computerLabMonitoringFormObject[] computerLabMonitoringFormList = {};

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(computerLabMonitoringFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            computerLabMonitoringFormList = gson.fromJson(jsonArray, computerLabMonitoringFormObject[].class);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (NullPointerException e) {
            System.out.println("File is empty.");
        } catch (JsonSyntaxException e) {
            System.out.println("Here!!");
        }

        return computerLabMonitoringFormList;
    }

    public static void modifyForm() throws IOException {
        System.out.println("Please enter the index of the form you want to modify (Refer to the list).");
        displayComputerLabMonitoringFormList();
        try {
            modifyComputerLabMonitoringFormRecord(inp.nextInt() - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter a valid input.");
        }
    }

    public static void modifyComputerLabMonitoringFormRecord(int index) throws IOException {
        computerLabMonitoringFormObject computerLabMonitoringFormObject;

        System.out.println("Please fill out the following information, put N/A if not applicable.");
        System.out.print("Enter description: ");
        String description = inp.nextLine().length() != 0 ? inp.nextLine() : "N/A";
        String status = pickStatus();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(computerLabMonitoringFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            computerLabMonitoringFormObject = gson.fromJson(jsonArray.get(index),
                    computerLabMonitoringFormObject.class);
            computerLabMonitoringFormObject.setDescription(description);
            computerLabMonitoringFormObject.setStatus(status);
            jsonArray.set(index, gson.toJsonTree(computerLabMonitoringFormObject));

            utilityClass.putToJson(jsonArray, computerLabMonitoringFile);
            System.out.println("Computer Lab Monitoring form updated successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid input.");
        } catch (Exception e) {
            System.out.println("An error occurred, please try again.");
        }

    }

    public static String pickStatus() {
        String status = "Online";
        System.out.println("Please choose the status of the computer lab monitoring form.");
        System.out.println("1. Online");
        System.out.println("2. Offline");
        System.out.print("Enter the number of the status (1 or 2): ");
        String userOption = inp.next();

        switch (userOption) {
            case "1":
                status = "Online";
                break;
            case "2":
                status = "Offline";
                break;
            default:
                System.out.println("Invalid input, please try again.");
                break;
        }
        return status;
    }

    public static void removeForm() throws IOException {
        System.out.println("Which form would you like to remove?");
        displayComputerLabMonitoringFormList();
        System.out.println("Please enter the index of the form you want to remove (Refer to the list): ");
        try {
            removeComputerLabMonitoringFormRecord(inp.nextInt() - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter a valid input.");
        }
    }

    public static void removeComputerLabMonitoringFormRecord(int index) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(computerLabMonitoringFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            jsonArray.remove(index);
            utilityClass.putToJson(jsonArray, computerLabMonitoringFile);
            System.out.println("Computer Lab Monitoring form removed successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid input.");
        } catch (Exception e) {
            System.out.println("An error occurred, please try again.");
        }
    }

}
