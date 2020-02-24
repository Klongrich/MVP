package driver;

import APIs.cargurusAPI;

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

                if (!id.contains("x"))
                    test.createData(id, year, Integer.toString(i));
                line = buff.readLine();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
