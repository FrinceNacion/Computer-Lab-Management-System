package com.example.comlabsys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonWriter;

public class accountFunctions {
    private static Scanner inp = new Scanner(System.in);
    private static File userFile = new File(
            "C:\\Users\\Frince\\comlabmonitoringsystem\\src\\main\\resources\\userStorage.json");
    public static String userFilePath = "C:\\Users\\Frince\\comlabmonitoringsystem\\src\\main\\resources\\userStorage.json";
    private static JsonArray jsonArray;
    private static Gson gson = new Gson();
    private static String uid = "";

    public static void initialize() throws IOException {

        if (userFile.createNewFile()) {
            try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(userFile))) {
                jsonWriter.beginArray();
                jsonWriter.endArray();

            } catch (Exception e) {
                System.out.println("An error occurred in creating the file.");
            }
            registerAdmin();
            System.out.println("record created successfully");
        } else {
            System.out.println("Record already created");
        }
    }

    public static userAccount getCurrentUserByCredentials(String username, String password) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            for (int i = 0; i < jsonArray.size(); i++) {
                userAccount userData = gson.fromJson(jsonArray.get(i), userAccount.class);
                if (userData.getUsername().equals(username) && userData.getPassword().equals(password)) {
                    return userData;
                }
            }
        }
        return null;
    }

    public static userAccount getCurrentUserByUid(String uid) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            for (int i = 0; i < jsonArray.size(); i++) {
                userAccount userData = gson.fromJson(jsonArray.get(i), userAccount.class);
                if (userData.getUid().equals(uid)) {
                    return userData;
                }
            }
        }
        return null;
    }

    public static String getUid() {
        return uid;
    }

    public static boolean validateCredentials(String username, String password) {
        boolean isValid = true;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            for (int i = 0; i < jsonArray.size(); i++) {
                userAccount userData = gson.fromJson(jsonArray.get(i), userAccount.class);
                if (userData.getUsername().equals(username) && !userData.getPassword().equals(password)) {
                    System.out.println("Wrong password, please try again.");
                    return false;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return isValid;
    }

    public static void readRecord(String username, String password) throws FileNotFoundException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            jsonArray.forEach(jsonElement -> {
                userAccount userData = gson.fromJson(jsonElement, userAccount.class);
                if (userData.getUsername().equals(username) && userData.getPassword().equals(password)) {
                    System.out.println("Welcome " + userData.getName());
                    uid = userData.getUid();
                    System.out.println("Your UID is " + getUid());
                }
            });
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public static void writeRecord(userAccount studentData) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userFile))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
        } catch (FileNotFoundException e) {
            jsonArray = new JsonArray();
        } catch (NullPointerException e) {
            jsonArray = new JsonArray();
        }

        jsonArray.add(gson.toJsonTree(studentData));

        utilityClass.putToJson(jsonArray, userFile);
    }

    public static void changePassword(String name, String username, String password) throws FileNotFoundException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            for (int i = 0; i < jsonArray.size(); i++) {
                userAccount userData = gson.fromJson(jsonArray.get(i), userAccount.class);
                if (userData.getUsername().equals(username) && userData.getName().equals(name)) {
                    System.out.println("Account found");
                    userData.setPassword(password);
                    jsonArray.set(i, gson.toJsonTree(userData));

                    utilityClass.putToJson(jsonArray, userFile);
                    System.out.println("Password changed successfully");
                    break;
                } else {
                    System.out.println("Account not found");
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void registerAdmin() {
        try {
            writeRecord(new userAccount("N/A", "N/A", "N/A", "N/A", "admin", "1234", "ADMIN"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void registerUser() {
        boolean registrationStatus = false;
        userAccount newUser;
        System.out.println("Please fill out the following information. Put N/A if not applicable.");
        System.out.print("Enter username: ");
        String username = inp.next();
        System.out.print("Enter password: ");
        String password = inp.next();
        System.out.print("Enter name: ");
        String name = inp.next();
        System.out.print("Enter course: ");
        String course = inp.next();
        System.out.print("Enter student/faculty Id: ");
        String Id = inp.next();
        System.out.print("Enter year and section: ");
        String section = inp.next();
        System.out.print("Enter user type: ");
        String userType = inp.next();

        newUser = new userAccount(name, course, Id, section, username, password, userType);
        try {
            writeRecord(newUser);
            registrationStatus = true;
        } catch (IOException e) {
            System.out.println(e);
        }

        if (registrationStatus) {
            System.out.println("Registration successful, you can now login.");
        } else {
            System.out.println("Registration failed, please try again.");
        }
    }

    public static void forgotPassword() {
        String name, username, password;
        System.out.print("Enter name: ");
        name = inp.next();
        System.out.print("Enter username: ");
        username = inp.next();
        System.out.print("Enter new password: ");
        password = inp.next();

        try {
            changePassword(name, username, password);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // functions for admin
    public static userAccount[] getUserList() {
        userAccount userData[] = {};

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            userData = gson.fromJson(jsonArray, userAccount[].class);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return userData;
    }

    public static void printUserList() {
        userAccount[] userList = getUserList();
        utilityClass.printDivider();
        System.out.println("List of users: ");
        System.out.println("No. | Name | Username | ID Number | Course | Section  | Account Type");
        for (int i = 0; i < userList.length; i++) {
            String user = Integer.toString(i + 1) + ". " + userList[i].getName() + " - " + userList[i].getUsername()
                    + " - " + userList[i].getId() + " - " + userList[i].getCourse() + " - "
                    + userList[i].getSection() + " - " + userList[i].getAccountType();
            System.out.println(user);
        }
    }

    public static void removeUser() {
        System.out.print("Enter account No. (refer to the list): ");
        int accountNo = inp.nextInt();
        try {
            removeRecord(accountNo - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeRecord(int index) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            jsonArray.remove(index);

            utilityClass.putToJson(jsonArray, userFile);
            System.out.println("Account removed successfully");
        } catch (Exception e) {
            System.out.println("An error occurred, please try again.");
        }
    }

    public static void modifyAccount() {
        System.out.print("Enter account No. (refer to the list): ");
        int accountNo = inp.nextInt();
        try {
            modifyRecord(accountNo - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void modifyRecord(int index) throws IOException {
        userAccount userData;

        System.out.println("Please fill out the following information. Put N/A if not applicable.");
        System.out.print("Enter username: ");
        String username = inp.next();
        System.out.print("Enter name: ");
        String name = inp.next();
        System.out.print("Enter course: ");
        String course = inp.next();
        System.out.print("Enter student/faculty Id: ");
        String Id = inp.next();
        System.out.print("Enter year and section: ");
        String section = inp.next();
        System.out.print("Enter user type: ");
        String userType = inp.next();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            userData = gson.fromJson(jsonArray.get(index), userAccount.class);
            userData.setUsername(username);
            userData.setName(name);
            userData.setCourse(course);
            userData.setId(Id);
            userData.setSection(section);
            userData.setAccountType(userType);
            jsonArray.set(index, gson.toJsonTree(userData));

            try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(userFile))) {
                gson.toJson(jsonArray, jsonWriter);
                System.out.println("Account modified successfully");
            } catch (Exception e) {
                System.out.println("An error occurred, please try again.");
            }
        }

    }
}
