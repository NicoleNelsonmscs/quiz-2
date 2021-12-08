package ui;

import dataaccess.SensorReadingJSONParser;
import domain.Sensor;
import domain.SensorReading;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<SensorReading> sensorReadings = new ArrayList<SensorReading>();
        Sensor sensor = new Sensor(2,1,1,"Heat Register");

        try {
            SensorReadingJSONParser.readFile("D:\\2742 Java II\\nnelson-2742\\nnelson2742exf1/resources/readings.json");
            sensor.setSensorReadings(SensorReadingJSONParser.getSensorReadings());
        } catch (Exception e) {
            System.out.println(e);
        }
//        for (SensorReading r : sensorReadings) {
//            System.out.println(r.toString());
//       }

        int index = sensor.findMinReadingIndex();
        System.out.println("\nMin: index = " + index + ", "+ sensor.getSensorReadings().get(index).toString());
        index = sensor.findMaxReadingIndex();
        System.out.println("\nMax: index = " + index + ", "+ sensor.getSensorReadings().get(index).toString());

        index = sensor.findMinReadingIndex(84,95);
        System.out.println("\nMin between index 84 and 95: index = " + index + ", "+ sensor.getSensorReadings().get(index).toString());
        index = sensor.findMaxReadingIndex(8,18);
        System.out.println("\nMax between index 8 and 18: index = " + index + ", "+ sensor.getSensorReadings().get(index).toString());

//        //invalid indexes
//        index = sensor.findMaxReadingIndex(-1,95);
//        System.out.println("\nMin between index 84 and 95: index = " + index + ", "
//                + sensor.getSensorReadings().get(index).toString());
//        index = sensor.findMaxReadingIndex(8,1000);
//        System.out.println("\nMax between index 8 and 18: index = " + index +
//                ", "+ sensor.getSensorReadings().get(index).toString());

        try{
            index = sensor.findMaxReadingIndex(-1,10);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }

        try{
            index = sensor.findMaxReadingIndex(1,-1);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }

        try{
            index = sensor.findMinReadingIndex(10,9);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }

        try{
            index = sensor.findMinReadingIndex(100,118);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }

        index = sensor.findNextCycleMaxIndex(0);
        System.out.println("First max after index 0: index = " + index + ", "+ sensor.getSensorReadings().get(index).toString());

        index = sensor.findNextCycleMinIndex(5);
        System.out.println("First max after index 5: index = " + index + ", "+ sensor.getSensorReadings().get(index).toString());

        int prevIndex = 0;
        index=sensor.findNextCycleMaxIndex(0);
        while (index > prevIndex){
            prevIndex = index;
            System.out.println("Index: " + index + ", Max: " + sensor.getSensorReading(index));
            index = sensor.findNextCycleMinIndex(index);
            if(index > prevIndex)
                System.out.println("Index: " + index + ", Min: " + sensor.getSensorReading(index));
            index = sensor.findNextCycleMaxIndex(index);
        }

    }
}