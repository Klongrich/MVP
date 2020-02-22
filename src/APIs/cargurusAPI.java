package APIs;

import utils.key_indexer;
import utils.indexer;

import java.io.BufferedReader;
import java.io.FileWriter;
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

import javax.swing.plaf.synth.SynthTextAreaUI;

import static javax.swing.text.html.HTML.Tag.U;

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

            String rawData = null;

            while(sc.hasNext()) {
                rawData += (sc.nextLine() + "\n");
            }

            String bmw = null;
            String lexus = null;
            String ford = null;

            String temp[];
            temp = rawData.split("models");

            for (int i = 0; i < temp.length; i++ ) {
                System.out.println(temp[i] + "\n");
                if (temp[i].contains("BMW"))
                {
                    bmw = temp[i];
                }
                else if (temp[i].contains("Ferrari"))
                {
                    lexus = temp[i];
                }
                else if (temp[i].contains("Ford"))
                {
                    ford = temp[i];
                }
            }

            ArrayList<ArrayList<indexer>> allResults = new ArrayList<>();
            ArrayList<indexer> bmwResults = new ArrayList<>();
            ArrayList<indexer> lexusReults = new ArrayList<>();
            ArrayList<indexer> fordResults = new ArrayList<>();


            key_indexer test = new key_indexer();

            bmwResults = test.get_index_values(bmw.split("\\{"), "BMW");
            lexusReults = test.get_index_values(lexus.split("\\{"), "Ferrari");
            fordResults = test.get_index_values(ford.split("\\{"), "Ford");

            allResults.add(bmwResults);
            allResults.add(lexusReults);
            allResults.add(fordResults);

            //System.out.println("\nJSON data in string format");
            //System.out.println(inline);

            sc.close();

            for (int x = 0; x < allResults.size(); x++)
            {
                for (int j = 0; j < allResults.get(x).size(); j++)
                {
                    System.out.println(allResults.get(x).get(j).getName() + "," + allResults.get(x).get(j).getID());

                }
            }

            /*
            try {
                FileWriter fw = new FileWriter("/home/kyle/myfiles/java/carProject/src/data/indexValues.csv", true);
                fw.write("Model, ID, \n");

                for (int x = 0; x < allResults.size(); x++)
                {
                    for (int j = 0; j < allResults.get(x).size(); j++)
                    {
                        fw.write(allResults.get(x).get(j).getName() + "," + allResults.get(x).get(j).getID() + "\n");

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            */

            System.out.println("\n");

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
