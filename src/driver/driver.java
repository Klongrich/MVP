package driver;

import APIs.*;

import java.io.FileWriter;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class driver extends Thread {

    int page_start_number = 0;
    String zip_code;

    public driver(int page_start_number, String zip_code) {
            this.page_start_number = page_start_number;
            this.zip_code = zip_code;
    }

    DateFormat df = new SimpleDateFormat("MM-dd-yy");
    Date dateobj = new Date();

    public void run(){

        ArrayList<car> carData = new ArrayList<car>();
        ArrayList<ArrayList<car>> allCarData = new ArrayList<ArrayList<car>>();

        carsAPI conn = new carsAPI(zip_code);

        long startTime;
        long endTime;

        long fullStartTime;
        long fullEndTime;

        fullStartTime = System.nanoTime();
        for (int i = page_start_number; i < page_start_number + 50; i++) {

            startTime = System.nanoTime();

            carData = conn.getData("https://www.cars.com/for-sale/searchresults.action/?dealerType=all&page=" + i + "&perPage=100&rd=100&searchSource=GN_REFINEMENT&sort=relevance&stkTypId=28881&zc=" + zip_code);
            allCarData.add(carData);

            endTime = System.nanoTime();
            System.out.println(i + ": Duration: " + ((endTime - startTime) / 1000000000) + " seconds");
        }
        fullEndTime = System.nanoTime();

        System.out.println("Full Duration: " + ((fullEndTime - fullStartTime) / 1000000000) + " seconds");

        try {
            FileWriter fw = new FileWriter("/home/kyle/myfiles/java/carProject/src/data/carData-" + df.format(dateobj) + ".csv", true);
            fw.write("Name,Price,Miles,Zip Code\n");

            for (int j = 0; j < allCarData.size(); j++) {
                for (int x = 0; x < allCarData.get(j).size(); x++) {
                    fw.write(allCarData.get(j).get(x).getName() + "," + allCarData.get(j).get(x).getPrice() + "," + allCarData.get(j).get(x).getMiles() + "," + allCarData.get(j).get(x).getZip_code() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Data Collection Complete");
    }
}