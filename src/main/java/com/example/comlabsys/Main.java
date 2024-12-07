package com.example.comlabsys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonWriter;

public class Main {
    public static Scanner inp = new Scanner(System.in);
    public static File userDbase = new File(
            "C:\\Users\\Frince\\comlabmonitoringsystem\\src\\main\\resources\\userDbase.json");
    public static String userDbasePath = "C:\\Users\\Frince\\comlabmonitoringsystem\\src\\main\\resources\\userDbase.json";

    public static void printDivider() {
        for (int i = 0; i < 10; i++) {
            System.out.print("====");
        }
        System.out.println("");
    }

    private static boolean validateCredentials(String username, String password) {
        Gson gson = new Gson();
        JsonArray jsonArray;
        boolean isValid = true;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userDbasePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            for (int i = 0; i < jsonArray.size(); i++) {
                studentData studentData = gson.fromJson(jsonArray.get(i), studentData.class);
                if (studentData.getUsername().equals(username) && !studentData.getPassword().equals(password)) {
                    System.out.println("Wrong password, please try again.");
                    return false;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return isValid;
    }

    // method para ma basa ng program yung txt file
    public static void readRecord(String username, String password) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonArray jsonArray;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userDbasePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            jsonArray.forEach(jsonElement -> {
                studentData studentData = gson.fromJson(jsonElement, studentData.class);
                if (studentData.getUsername().equals(username) && studentData.getPassword().equals(password)) {
                    System.out.println("Welcome " + studentData.getName());
                }
            });
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void changePassword(String name, String username, String password) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonArray jsonArray;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userDbasePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            for (int i = 0; i < jsonArray.size(); i++) {
                studentData studentData = gson.fromJson(jsonArray.get(i), studentData.class);
                if (studentData.getUsername().equals(username) && studentData.getName().equals(name)) {
                    System.out.println("Account found");
                    studentData.setPassword(password);
                    jsonArray.set(i, gson.toJsonTree(studentData));

                    try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(userDbase))) {
                        gson.toJson(jsonArray, jsonWriter);
                        System.out.println("Password changed successfully");
                    }
                    break;
                } else {
                    System.out.println("Account not found");
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void writeRecord(studentData studentData) throws IOException {
        Gson gson = new Gson();
        JsonArray jsonArray;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userDbase))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
        } catch (FileNotFoundException e) {
            jsonArray = new JsonArray();
        } catch (NullPointerException e) {
            jsonArray = new JsonArray();
        }

        jsonArray.add(gson.toJsonTree(studentData));

        try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(userDbase))) {
            gson.toJson(jsonArray, jsonWriter);
        }
    }

    private static void registerAdmin() {
        try {
            writeRecord(new studentData("ADMIN", "ADMIN", "ADMIN", "ADMIN", "admin", "1234"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void registerUser() {
        boolean registrationStatus = false;
        studentData newUser;
        System.out.println("Registration module");
        System.out.print("Enter username: ");
        String username = inp.next();
        System.out.print("Enter password: ");
        String password = inp.next();
        System.out.print("Enter name: ");
        String name = inp.next();
        System.out.print("Enter course: ");
        String course = inp.next();
        System.out.print("Enter year level: ");
        String year = inp.next();
        System.out.print("Enter section: ");
        String section = inp.next();

        newUser = new studentData(name, course, year, section, username, password);
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
        logIn();
    }

    private static void forgotPassword() {
        String name, username, password;
        System.out.println("Enter name: ");
        name = inp.next();
        System.out.println("Enter username: ");
        username = inp.next();
        System.out.println("Enter new password: ");
        password = inp.next();

        try {
            changePassword(name, username, password);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void logIn() {

        String userOption, username, password;
        printDivider();
        System.out.println("1. Login as Student");
        System.out.println("2. Login as Admin");
        System.out.println("3. Register");
        System.out.println("4. Forgot Password");
        System.out.print("Go to: ");
        userOption = inp.next();
        printDivider();

        switch (userOption) {
            case "1":
                System.out.print("Enter username: ");
                username = inp.next();
                System.out.print("Enter password: ");
                password = inp.next();
                try {
                    if (validateCredentials(username, password)) {
                        readRecord(username, password);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "2":
                System.out.print("Enter username: ");
                username = inp.next();
                System.out.print("Enter password: ");
                password = inp.next();
                try {
                    if (validateCredentials(username, password)) {
                        readRecord(username, password);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "3":
                registerUser();
                break;
            case "4":
                forgotPassword();
                break;
            default:
                System.out.println("--Invalid Input--");
        }

    }

    public static void main(String[] args) throws IOException {
        if (userDbase.createNewFile()) {
            try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(userDbase))) {
                jsonWriter.beginArray();
                jsonWriter.endArray();

            } catch (Exception e) {
                // TODO: handle exception
            }
            registerAdmin();
            System.out.println("record created successfully");
        } else {
            System.out.println("Record already created");
        }
        logIn();
    }
}
