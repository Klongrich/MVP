package driver;

import APIs.cargurusAPI;

import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class driver2 {

    public static void main(String arg[]) {


        cargurusAPI test = new cargurusAPI();

        try {
            FileReader fr = new FileReader("./src/data/Important.csv");
            BufferedReader buff = new BufferedReader(fr);

            String line;
            String temp[];
            String id;
            String year;
            int i;

            i = 1;
            line = buff.readLine();
            while (line != null) {

                temp = line.split(",");

                id = temp[0];
                year = temp[1];

                if (!id.contains("x")) {
                    test.createData(id, year, Integer.toString(i));
                    System.out.println(id + " " + year);
                }
                line = buff.readLine();
                i++;
                System.out.println("sleeping...");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("awake...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
