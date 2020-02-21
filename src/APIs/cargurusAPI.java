package APIs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class cargurusAPI {

    private static final String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:58.0) Gecko/20100101 Firefox/58.0";

    private static final String Accept = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";

    private static final String AcceptLanguage = "en-US,en;q=0.5";

    private static final String AcceptEncoding = "gzip, deflate, br";

    private static final String Connection = "keep-alive";

    private static final String UpgradeInsecureRequests = "1";

    static String site_URL = null;

    public static void main(String args[]) {


        try {

            URL obj = new URL("https://www.cargurus.com/Cars/getCarPickerReferenceDataAJAX.action?showInactive=false&useInventoryService=true&localCountryCarsOnly=true&outputFormat=REACT&quotableCarsOnly=false");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept", Accept);
            con.setRequestProperty("Accept-Language", AcceptLanguage);
            con.setRequestProperty("DNT", "1");
            con.setRequestProperty("Upgrade-Insecure-Requests", UpgradeInsecureRequests);

            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            Scanner sc = new Scanner(obj.openStream());

            String inline = null;

            while(sc.hasNext()) {
                inline += (sc.nextLine() + "\n");
            }

            String temp[];
            String bmw = null;
            temp = inline.split("models");

            for (int i = 0; i < temp.length; i++ ) {
                System.out.println(temp[i] + "\n");
                if (temp[i].contains("BMW"))
                {
                    bmw = temp[i];
                }
            }

            String temp2[] = null;

            System.out.println("bmw " + bmw);

            ArrayList<String> results = new ArrayList<>();
            temp2 = bmw.split("\\{");
            String result = null;
            String temp3[];
            for (int j = 1; j < temp2.length - 1; j++) {


                System.out.println("Temp2[1] " + temp2[j]);

                temp3 = temp2[j].split(",");

                System.out.println("temp3[0] " + temp3[0]);
                System.out.println("temp3[1] " + temp3[1]);

                String temp4[];
                String temp5[];

                temp4 = temp3[0].split("\"");
                temp5 = temp3[1].split("\"");
                System.out.println("temp4[3] " + temp4[3]);
                System.out.println("temp5[3] " + temp5[3]);

                result = "BMW" + " " + temp4[3] + " " + temp5[3];

                System.out.println("\n" + result);
                results.add("BMW" + " " + temp4[3] + ": " + temp5[3]);
            }
            //System.out.println("\nJSON data in string format");
            //System.out.println(inline);

            sc.close();

            System.out.println("\n");

            for (int x = 0; x < results.size(); x++)
            {
                System.out.println(results.get(x));
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
