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

public class equipmentModule {
    public static Scanner inp = new Scanner(System.in);
    private static String equipmentFilePath = "C:\\Users\\Frince\\comlabmonitoringsystem\\src\\main\\resources\\equipmentStorage.json";
    private static File equipmentFile = new File(equipmentFilePath);
    private static JsonArray jsonArray;
    private static Gson gson = new Gson();

    // Pinagsama ko na yung equipment at borrower Module kasi almost same functions
    // lang naman
    public static void initialize() throws IOException {
        if (equipmentFile.createNewFile()) {
            try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(equipmentFile))) {
                jsonWriter.beginArray();
                jsonWriter.endArray();

            } catch (Exception e) {
                System.out.println("An error occurred in creating the file.");
            }
            System.out.println("Equipment record created successfully");
        } else {
            System.out.println("Equipment record already created");
        }
    }

    public static String pickEquipmentStatus() {
        String equipmentStatus;
        System.out.println("\nPlease select the status of the equipment:");
        System.out.println("1. Available");
        System.out.println("2. Unavailable");
        System.out.println("3. Damaged");
        System.out.println("4. Missing");
        System.out.print("Enter the number of the status (1 or 2): ");
        String userOption = inp.next();

        switch (userOption) {
            case "1":
                equipmentStatus = "Available";
                break;
            case "2":
                equipmentStatus = "Unavailable";
                break;
            case "3":
                equipmentStatus = "Damaged";
                break;
            case "4":
                equipmentStatus = "Missing";
                break;
            default:
                System.out.println("Invalid input, please try again.");
                equipmentStatus = null;
                break;
        }
        return equipmentStatus;
    }

    public static void addEquipment() throws IOException {
        equipmentObject equipmentObject;
        System.out.println("\nPlease fill out the following information.");
        System.out.print("Equipment ID: ");
        String equipmentID = inp.nextLine();
        System.out.print("Equipment Name: ");
        String equipmentName = inp.nextLine();
        System.out.print("Description: ");
        String description = inp.nextLine();
        String equipmentStatus = pickEquipmentStatus();

        equipmentObject = new equipmentObject(equipmentID, equipmentName, description, equipmentStatus);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(equipmentFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            jsonArray.add(gson.toJsonTree(equipmentObject));
            utilityClass.putToJson(jsonArray, equipmentFile);
            System.out.println("Equipment added successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred, please try again.");
        }
    }

    public static void displayEquipmentList() throws IOException {
        equipmentObject[] equipmentList = getEquipmentList();
        String equipment;
        System.out.println("\nEquipment list:");
        for (int i = 0; i < equipmentList.length; i++) {
            equipment = Integer.toString(i + 1) + ". " + "Equipment ID: " +
                    equipmentList[i].getEquipmentID() + ", Equipment Name: "
                    + equipmentList[i].getEquipmentName() + ", Description: "
                    + equipmentList[i].getEquipmentDescription() + ", Status: "
                    + equipmentList[i].getEquipmentStatus() + ", Borrow status: "
                    + equipmentList[i].getBorrowedStatus();
            System.out.println(equipment);
        }
    }

    public static void displayBorrowedEquipmentList() throws IOException {
        equipmentObject[] equipmentList = getEquipmentList();
        String equipment;
        System.out.println("\nBorrowed equipment list:");
        try {
            for (int i = 0; i < equipmentList.length; i++) {
                if (equipmentList[i].getBorrowedStatus().equals("Borrowed")) {
                    equipment = Integer.toString(i + 1) + ". " + "Equipment ID: " +
                            equipmentList[i].getEquipmentID() + ", Equipment Name: "
                            + equipmentList[i].getEquipmentName() + ", Description: " +
                            equipmentList[i].getEquipmentDescription() + ", Status: " +
                            equipmentList[i].getEquipmentStatus() + ", Borrow status: " +
                            equipmentList[i].getBorrowedStatus() + "\n - Borrowed by: " +
                            equipmentList[i].getBorrowerName() + " - Borrowed at: " +
                            equipmentList[i].getDateBorrowed() + " - To be returned at: " +
                            equipmentList[i].getDateReturn();
                    System.out.println(equipment);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("There is no borrowed equipment yet.");
        }

    }

    public static equipmentObject[] getEquipmentList() throws IOException {
        equipmentObject[] equipmentList = {};

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(equipmentFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            equipmentList = gson.fromJson(jsonArray, equipmentObject[].class);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (NullPointerException e) {
            System.out.println("File is empty.");
        }

        return equipmentList;
    }

    public static void removeEquipment() throws IOException {
        System.out.println("\nWhich equipment would you like to remove? (Refer to the list below)");
        displayEquipmentList();
        System.out.print("Enter the equipment No. (refer to the list) which you want to remove: ");
        try {
            int equipmentNo = Integer.parseInt(inp.nextLine());
            removeEquipmentRecord(equipmentNo - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter a valid input (refer to the list).");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid input (refer to the list).");
        }

    }

    public static void removeEquipmentRecord(int index) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(equipmentFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            jsonArray.remove(index);
            utilityClass.putToJson(jsonArray, equipmentFile);
            System.out.println("Equipment removed successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid input.");
        } catch (Exception e) {
            System.out.println("An error occurred, please try again.");
        }
    }

    public static void updateEquipment() throws IOException {
        System.out.println("\nWhich equipment would you like to update? (Refer to the list below)");
        displayEquipmentList();
        System.out.print("Enter the equipment No. (refer to the list) which you want to update: ");
        try {
            int equipmentNo = Integer.parseInt(inp.nextLine());
            updateEquipmentRecord(equipmentNo - 1);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid input (refer to the list).");
        }
    }

    public static String pickBorrowedStatus() {
        String borrowedStatus = null;
        System.out.println("\nPlease select the borrowed status of the equipment:");
        System.out.println("1. Borrowed");
        System.out.println("2. Available for borrow");
        System.out.println("3. Returned");
        System.out.println("4. Not available for borrow");
        System.out.print("Enter the number of the status (1 or 2): ");
        String userOption = inp.nextLine();

        switch (userOption) {
            case "1":
                borrowedStatus = "Borrowed";
                break;
            case "2":
                borrowedStatus = "Available for borrow";
                break;
            case "3":
                borrowedStatus = "Returned";
                break;
            case "4":
                borrowedStatus = "Not available for borrow";
                break;
            default:
                System.out.println("Invalid input, please try again.");
                break;
        }
        return borrowedStatus;
    }

    public static void updateEquipmentRecord(int index) throws IOException {
        equipmentObject equipmentObject;
        String equipmentStatus;
        String borrowerName, dateBorrowed, dateReturn, borrowedStatus;

        System.out.println("\nPlease fill out the following information, put N/A if not applicable.");
        borrowedStatus = pickBorrowedStatus();
        System.out.print("Borrower Name: ");
        borrowerName = inp.nextLine();
        System.out.print("Date Borrowed: ");
        dateBorrowed = inp.nextLine();
        System.out.print("Returning Date: ");
        dateReturn = inp.nextLine();
        equipmentStatus = pickEquipmentStatus();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(equipmentFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            equipmentObject = gson.fromJson(jsonArray.get(index), equipmentObject.class);
            equipmentObject.setEquipmentStatus(equipmentStatus);
            equipmentObject.setBorrowerName(borrowerName);
            equipmentObject.setDateBorrowed(dateBorrowed);
            equipmentObject.setDateReturn(dateReturn);
            equipmentObject.setBorrowedStatus(borrowedStatus);
            jsonArray.set(index, gson.toJsonTree(equipmentObject));

            utilityClass.putToJson(jsonArray, equipmentFile);
            System.out.println("Equipment updated successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid input.");
        } catch (Exception e) {
            System.out.println("An error occurred, please try again.");
        }
    }

}
