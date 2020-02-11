package driver;

import data.car;
import data.carsAPI;

import java.util.ArrayList;

public class driver {

    public static void main(String args[]) {

        ArrayList<car> carData = new ArrayList<car>();
        carsAPI conn = new carsAPI();

        carData = conn.getData();

        for (int i = 0; i < carData.size(); i++) {
            System.out.println("Name: " + carData.get(i).getName());
            System.out.println("Price: " + carData.get(i).getPrice());
            System.out.println("Miles: " + carData.get(i).getMiles());
            System.out.println();
        }
    }
}
