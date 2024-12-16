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

    /**
     * Initializes the userStorage.json file and creates an admin account if the
     * file doesn't exist.
     * 
     * @throws IOException
     */
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

    /**
     * Retrieves a userAccount object from the userStorage.json file based on the
     * username and password
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @return a userAccount object if the user is found, null otherwise
     * @throws IOException if an error occurs while reading the file
     */
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

    /**
     * Retrieves a userAccount object from the userStorage.json file based on the
     * uid (Unique Identifier)
     * 
     * @param uid the unique identifier of the user
     * @return a userAccount object if the user is found, null otherwise
     * @throws IOException if an error occurs while reading the file
     */
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

    /**
     * Returns the unique identifier (UID) of the current user.
     *
     * @return the UID of the current user as a String
     */
    public static String getUid() {
        return uid;
    }

    /**
     * Reads a user's record from the userStorage.json file.
     * 
     * @param username the username of the user
     * @param password the password of the user
     * @throws FileNotFoundException if the file does not exist
     */
    public static void readRecord(String username, String password) throws FileNotFoundException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            for (int i = 0; i < jsonArray.size(); i++) {
                userAccount userData = gson.fromJson(jsonArray.get(i), userAccount.class);
                if (userData.getUsername().equals(username) && userData.getPassword().equals(password)) {
                    System.out.println("Welcome " + userData.getName());
                    uid = userData.getUid();
                    System.out.println("Your UID is " + getUid());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Writes a userAccount object to the userStorage.json file.
     * Turns the array inside the Json into the jsonArray dataType then writes it to
     * the file with the new object
     * 
     * If the file does not exist, it will be created. If the file is empty, a new
     * JsonArray will be created.
     * 
     * @param studentData a userAccount object to be written to the file
     * @throws IOException if an error occurs while writing to the file
     */
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

    /**
     * Changes the password of the user with the given name and username.
     * 
     * @param name     the name of the user
     * @param username the username of the user
     * @param password the new password
     * @throws FileNotFoundException if the file does not exist
     */
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
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Registers an admin user with the default username "admin" and password "1234"
     * and the account type "ADMIN".
     * This method is called when the userStorage.json file is initialized.
     * 
     * @throws IOException if an error occurs while writing to the file
     */
    public static void registerAdmin() {
        try {
            writeRecord(new userAccount("N/A", "N/A", "N/A", "N/A", "admin", "1234", "ADMIN"));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Registers a user account. This method will prompt the user to fill out the
     * necessary information, create a new userAccount object, and write it to the
     * userStorage.json file.
     * 
     * @throws IOException if an error occurs while writing to the file
     */
    public static void registerUser() {
        boolean registrationStatus = false;
        userAccount newUser;
        System.out.println("\nPlease fill out the following information. Put N/A if not applicable.");
        System.out.print("Enter username: ");
        String username = inp.nextLine();
        System.out.print("Enter password: ");
        String password = inp.nextLine();
        System.out.print("Enter name: ");
        String name = inp.nextLine();
        System.out.print("Enter course: ");
        String course = inp.nextLine();
        System.out.print("Enter student/faculty Id: ");
        String Id = inp.nextLine();
        System.out.print("Enter year and section: ");
        String section = inp.nextLine();
        String accountType = pickAccountType();

        newUser = new userAccount(name, course, Id, section, username, password, accountType);
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

    /**
     * Prompts the user to select an account type and returns the corresponding
     * string.
     * 
     * @return "student" if the user selects option 1, "faculty" if the user selects
     *         option 2,
     *         or an empty string if the input is invalid.
     */
    public static String pickAccountType() {
        String accountType;
        System.out.println("\nPlease choose your account type.");
        System.out.println("1. Student");
        System.out.println("2. Faculty");
        System.out.print("Enter account type (1 or 2): ");
        accountType = inp.next();

        switch (accountType) {
            case "1":
                accountType = "student";
                break;
            case "2":
                accountType = "faculty";
                break;
            default:
                System.out.println("Invalid input, please try again.");
                break;
        }
        return accountType;
    }

    /**
     * Allows the user to change their password by providing their name and
     * username.
     * The new password is then updated in the userStorage.json file.
     * If the file does not exist, a FileNotFoundException is thrown.
     * 
     * @throws FileNotFoundException if the file does not exist
     */
    public static void forgotPassword() {
        String name, username, password;
        System.out.print("Enter name: ");
        name = inp.nextLine();
        System.out.print("Enter username: ");
        username = inp.nextLine();
        System.out.print("Enter new password: ");
        password = inp.nextLine();

        try {
            changePassword(name, username, password);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of all userAccount objects from the userStorage.json file.
     * 
     * @return an array of userAccount objects
     * @throws FileNotFoundException if the file does not exist
     */
    public static userAccount[] getUserList() {
        userAccount userData[] = {};

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            userData = gson.fromJson(jsonArray, userAccount[].class);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return userData;
    }

    /**
     * Prints the list of all user accounts to the console.
     * The list includes the user's name, username, ID number, course, section, and
     * account type.
     * A divider is printed before the list, and each user is displayed with their
     * respective index.
     */
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

    /**
     * Prompts the user to enter the number of the user account to remove, referring
     * to the list of all user accounts printed by printUserList() method.
     * The account is then removed from the userStorage.json file.
     * 
     * @throws IOException if an error occurs while writing to the file
     */
    public static void removeUser() {
        System.out.println("\nWhich account would you like to remove?");
        printUserList();
        System.out.print("Enter the account No. (refer to the list) which you want to remove: ");
        int accountNo = inp.nextInt();
        try {
            removeUserRecord(accountNo - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes a user account from the userStorage.json file.
     * 
     * @param index the index of the user account to remove, as printed by the
     *              printUserList() method
     * @throws IOException if an error occurs while writing to the file
     */
    public static void removeUserRecord(int index) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(userFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            jsonArray.remove(index);

            utilityClass.putToJson(jsonArray, userFile);
            System.out.println("Account removed successfully");
        } catch (Exception e) {
            System.out.println("An error occurred, please try again.");
        }
    }

    /**
     * Prompts the user to enter the number of the user account to modify, referring
     * to the list of all user accounts printed by printUserList() method.
     * The modifyRecord() method is then called with the index of the user account
     * to modify.
     * 
     * @throws IOException if an error occurs while writing to the file
     */
    public static void modifyAccount() {
        System.out.print("\nEnter account No. (refer to the list): ");
        int accountNo = inp.nextInt();
        try {
            modifyRecord(accountNo - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void modifyRecord(int index) throws IOException {
        userAccount userData;

        System.out.println("\nPlease fill out the following information. Put N/A if not applicable.");
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
        String userType = pickAccountType();

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
