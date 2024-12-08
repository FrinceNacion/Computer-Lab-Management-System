package com.example.comlabsys;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonWriter;

public class utilityClass {
    public static File feedbackFile = new File(
            "C:\\Users\\Frince\\comlabmonitoringsystem\\src\\main\\resources\\feedbackStorage.json");
    public static File userFile = new File(
            "C:\\Users\\Frince\\comlabmonitoringsystem\\src\\main\\resources\\userDbase.json");
    public static Gson gson = new Gson();

    public static void printDivider() {
        for (int i = 0; i < 10; i++) {
            System.out.print("========");
        }
        System.out.println("");
    }

    public static void putToJson(JsonArray array, File file) throws IOException {

        try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(file))) {
            gson.toJson(array, jsonWriter);
        }
    }

    public static String getCurrentDate() {
        LocalDate date = LocalDate.now();
        return date.toString();
    }
}
