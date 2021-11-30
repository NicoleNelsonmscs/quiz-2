package ui;

import dataaccess.SensorReadingJSONParser;
import domain.SensorReading;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<SensorReading> sensorReadings = new ArrayList<SensorReading>();

        try {
            SensorReadingJSONParser.readFile("D:\\2742 Java II\\nnelson-2742\\nnelson2742exf1/resources/readings.json");
            sensorReadings = SensorReadingJSONParser.getSensorReadings();
        } catch (Exception e) {
            System.out.println(e);
        }
        for (SensorReading r : sensorReadings) {
            System.out.println(r.toString());
        }
    }
}