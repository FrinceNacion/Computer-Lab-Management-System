package com.example.comlabsys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

public class Main {
    public static Scanner inp = new Scanner(System.in);
    public static File userDbase = new File(
            "C:\\Users\\Frince\\comlabmonitoringsystem\\src\\main\\resources\\userDbase.json");
    public static String userDbasePath = "C:\\Users\\Frince\\comlabmonitoringsystem\\src\\main\\resources\\userDbase.json";
    public static List<studentData> studentRecord = new LinkedList<studentData>();

    public static void printDivider() {
        for (int i = 0; i < 10; i++) {
            System.out.print("===");
        }
        System.out.println("");
    }

    // method para ma basa ng program yung txt file
    public static void readRecord(String username, String password) throws FileNotFoundException {
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(userDbasePath));
        studentData studentData = gson.fromJson(bufferedReader, studentData.class);
        System.out.println(studentData.getUsername());
    }

    public static void writeRecord(studentData studentData) throws IOException {
        Gson newStudent = new Gson();
        if (userDbase.exists()) {

        }

        try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(userDbase));) {
            jsonWriter.beginObject();
            jsonWriter.name("name").value(studentData.getName());
            jsonWriter.name("course").value(studentData.getCourse());
            jsonWriter.name("year").value(studentData.getYear());
            jsonWriter.name("section").value(studentData.getSection());
            jsonWriter.name("username").value(studentData.getUsername());
            jsonWriter.name("password").value(studentData.getPassword());
            jsonWriter.endObject();
        }
    }

    /**
     * To validate the credentials given by the user. Will be use in Login and
     * Register module
     * 
     * @param username
     * @param password
     * @returns true if the credentials matches the one in the txt file. False if
     *          not.
     */
    private static boolean validateCredentials(String username, String password) {
        return false;
    }

    /**
     * User registration module
     */
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
        logIn();
    }

    /**
     * Login module
     */
    public static void logIn() {
        String userOption, username, password;
        printDivider();
        System.out.println("1. Login as Student");
        System.out.println("2. Login as Admin");
        System.out.println("3. Register");
        System.out.println("2. Forgot Password");
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
                    readRecord(username, password);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                // Validate account
                break;
            case "2":
                System.out.print("Enter username: ");
                username = inp.next();
                System.out.print("Enter password: ");
                password = inp.next();
                // Validate account
                break;
            case "3":
                registerUser();
                break;
            case "4":
                // To forgot password module
                break;
            default:
                System.out.println("--Invalid Input--");
        }

    }

    public static void main(String[] args) throws Exception {
        if (userDbase.createNewFile()) {
            System.out.println("record created successfully");
        } else {
            System.out.println("Record already created");
        }
        logIn();
    }
}
