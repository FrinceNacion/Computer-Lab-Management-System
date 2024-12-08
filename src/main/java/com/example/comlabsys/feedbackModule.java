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

public class feedbackModule {
    public static Scanner inp = new Scanner(System.in);
    private static File feedbackFile = new File(
            "C:\\Users\\Frince\\comlabmonitoringsystem\\src\\main\\resources\\feedbackStorage.json");
    private static String feedbackFilePath = "C:\\Users\\Frince\\comlabmonitoringsystem\\src\\main\\resources\\feedbackStorage.json";
    private static JsonArray jsonArray;
    private static Gson gson = new Gson();

    public static void initializeFeedbackModule() throws IOException {
        if (feedbackFile.createNewFile()) {
            try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(feedbackFile))) {
                jsonWriter.beginArray();
                jsonWriter.endArray();

            } catch (Exception e) {
                System.out.println("An error occurred in creating the file.");
            }
            System.out.println("Feedback record created successfully");
        } else {
            System.out.println("Feedback record already created");
        }
    }

    public static void addFeedback() throws IOException {
        String submittedBy, dateSubmitted, feedbackHeader, feedbackBody;
        feedbackObject feedback;
        submittedBy = accountFunctions.getCurrentUserByUid(accountFunctions.getUid()).getUsername();
        dateSubmitted = utilityClass.getCurrentDate();

        System.out.print("Write your feedback header: ");
        feedbackHeader = inp.nextLine();
        System.out.print("Write your feedback body: ");
        feedbackBody = inp.nextLine();
        feedback = new feedbackObject(submittedBy, dateSubmitted, feedbackHeader, feedbackBody);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(feedbackFile))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            System.out.println("Feedback posted successfully");

        } catch (FileNotFoundException e) {
            jsonArray = new JsonArray();
        } catch (NullPointerException e) {
            jsonArray = new JsonArray();
        }

        jsonArray.add(gson.toJsonTree(feedback));

        utilityClass.putToJson(jsonArray, feedbackFile);
    }

    public static void displayFeedbackList() throws IOException {
        feedbackObject[] feedbackList = getFeedbackList();

        if (feedbackList.length != 0) {
            for (int i = 0; i < feedbackList.length; i++) {
                String feedback = Integer.toString(i + 1) + ". " + feedbackList[i].getFeedbackHeader()
                        + " submitted by " + feedbackList[i].getSubmittedBy() + " on "
                        + feedbackList[i].getDateSubmitted();
                System.out.println(feedback);
            }
        } else {
            System.out.println("There is no feedback yet.");
        }
    }

    public static feedbackObject[] getFeedbackList() throws IOException {
        feedbackObject[] feedbackList = {};

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(feedbackFilePath))) {
            jsonArray = gson.fromJson(bufferedReader, JsonArray.class);
            feedbackList = gson.fromJson(jsonArray, feedbackObject[].class);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (NullPointerException e) {
            System.out.println("File is empty.");
        }

        return feedbackList;
    }
}
