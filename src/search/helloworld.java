package search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class helloworld {

    public static void main(String[] args)throws Exception {

        File carData = new File("./src/data/carData-02-17-20.csv");
        File dirveOptions = new File ("./src/data/driveOptions.csv");

        BufferedReader br = new BufferedReader(new FileReader(carData));
        BufferedReader driveopts = new BufferedReader(new FileReader(dirveOptions));

        String data;
        String targets[];
        ArrayList<String> carNames = new ArrayList<>();
        String yearTemp = null;
        String modelTemp = null;

        while ((data = driveopts.readLine()) != null) {
            targets = data.split(",");
            System.out.println(targets[2]);
            System.out.println(targets[3]);
            System.out.println(targets[4]);

            if (targets[2].contains("19")) {
                yearTemp = "2019";
            }
            else if (targets[2].contains("20")) {
                yearTemp = "2020";
            }
            else if (targets[2].contains("18")) {
                yearTemp = "2018";
            }

            carNames.add(yearTemp + " " + targets[3] + " " + targets[4]);
        }

        for (int x = 0; x < carNames.size(); x++)
        {
            System.out.println(carNames.get(x));
        }


        //Parsing cheat sheet (create data formater)
        //Year formate has to be 4 numbers (ex. 2019, 2018, 2020)
        //BMW
        //Porsche
        //Cadillac
        //Maserati
        //Audi
        //Jaguar
        //Ford
        //Jeep
        //Subaru
        //Chevrolet
        //Honda
        //Kia
        //Lincoln
        //
        //Hits
        //2019 BMW 740 i
        //2019 Audi A7 3.0T
        //2020 BMW X7 - get zip_codes
        //2020 BMW X5
        //
        //2020 Chevrolet Traverse
        //

        while ((data = br.readLine()) != null) {
            if (data.contains("2019 Lincoln Navigator")) {
                System.out.println(data);
            }
        }


        /*
                int i = 0;
        while (i < carNames.size()) {

            data = br.readLine();

            if (data.contains(carNames.get(i))) {
                System.out.println(data);
            }
            i++;
        }
         */
    }
}

