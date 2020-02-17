package driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Threads {

    public static void main (String arg[]) {

        ArrayList<String> zip_codes = new ArrayList<>();

        zip_codes.add("44011"); //1.) Avon, OH
        zip_codes.add("94027"); //2.) Atherton, CA
        zip_codes.add("20817"); //3.) Potomac, MD
        zip_codes.add("33109"); //4.) Miami Beach, FL
        zip_codes.add("33480"); //5.) Palms Beach, FL
        zip_codes.add("19035"); //6.) Gladwyne, PA
        zip_codes.add("90067"); //7.) Los Angeles, CA
        zip_codes.add("60043"); //8.) Kenilworth, IL
        zip_codes.add("02493"); //9.) Weston, MA
        zip_codes.add("06831"); //10) Greenwich, CT
        zip_codes.add("98039"); //11) Medina, WA
        zip_codes.add("11568"); //12) Old Westbury, NY
        zip_codes.add("45243"); //13) Indian Hill, OH
        zip_codes.add("19807"); //14) Wilmington, DE
        zip_codes.add("28207"); //15) Charlotte, NC

        int page_displacement = 50;

        for (int i = 0; i < zip_codes.size(); i++) {
            for (int x = 1; x < 10; x++) {
                driver temp = new driver(x * page_displacement, zip_codes.get(i));
                temp.start();
            }
            try {
                TimeUnit.MINUTES.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
