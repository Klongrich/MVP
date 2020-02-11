package driver;

import data.car;
import data.carsAPI;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class driver {

    public static void main(String args[]) {

        ArrayList<car> carData = new ArrayList<car>();
        ArrayList<ArrayList<car>> allCarData = new ArrayList<ArrayList<car>>();

        carsAPI conn = new carsAPI();

        long startTime;
        long endTime;


        for (int i = 1; i < 3; i++) {

            startTime = System.nanoTime();

            carData = conn.getData("https://www.cars.com/for-sale/searchresults.action/?dealerType=all&page=" + i + "&perPage=100&rd=100&searchSource=GN_REFINEMENT&sort=relevance&stkTypId=28881&zc=44011");
            allCarData.add(carData);

            endTime = System.nanoTime();
            System.out.println(i + ": Duration: " + ((endTime - startTime) / 1000) + " seconds");
        }

        System.out.println("test");
        try {
            FileWriter fw = new FileWriter("/home/kyle/myfiles/java/carProject/src/driver/carData.csv", true);
            fw.write("Name,Price,Miles,Close\n");

            for (int j = 0; j < allCarData.size(); j++) {
                for (int x = 0; x < allCarData.get(j).size(); x++) {
                    fw.write(allCarData.get(j).get(x).getName() + "," + allCarData.get(j).get(x).getPrice() + "," + allCarData.get(j).get(x).getMiles() + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("test");

    }
}
