package com.example.comlabsys;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
}
