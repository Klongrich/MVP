package search;






import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class searchCargurus {

    private static final String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:58.0) Gecko/20100101 Firefox/58.0";

    private static final String Accept = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";

    private static final String AcceptLanguage = "en-US,en;q=0.5";

    private static final String AcceptEncoding = "gzip, deflate, br";

    private static final String Connection = "keep-alive";

    private static final String UpgradeInsecureRequests = "1";

    static String site_URL = null;

    public static void main(String args[]) {


        try {

            URL url = new URL("https://www.cargurus.com/Cars/searchResults.action" +
                                    "?zip=33409" +
                                    "&inventorySearchWidgetType=AUTO" +
                                    "&nonShippableBaseline=73" +
                                    "&sortDir=ASC" +
                                    "&sourceContext=RecentSearches_true_0" +
                                    "&distance=5000" +
                                    "&sortType=DEAL_SCORE" +
                                    "&endYear=20202" +
                                    "&entitySelectingHelper.selectedEntity=d2113" +
                                    "&startYear=2019" +
                                    "&offset=-3" +
                                    "&maxResults=15" +
                                    "&filtersModified=true");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept", Accept);
            con.setRequestProperty("Accept-Language", AcceptLanguage);
            con.setRequestProperty("DNT", "1");
            con.setRequestProperty("Upgrade-Insecure-Requests", UpgradeInsecureRequests);

            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            Scanner sc = new Scanner(url.openStream());

            String rawData = null;

            while (sc.hasNext()) {
                rawData += (sc.nextLine());
            }

            /*
            try {
                FileWriter fw = new FileWriter("/home/kyle/myfiles/java/carProject/src/data/carDataRaw.json", true);
                fw.write(rawData);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            */

            Object obj = new JSONParser().parse(new FileReader("./src/data/carDataRaw.json"));

            JSONArray plzfuckingwork = (JSONArray) obj;

           // System.out.println(plzfuckingwork.get(0));

            for (int x = 0; x < plzfuckingwork.size(); x++) {

                Map address = ((Map) plzfuckingwork.get(x));

                Iterator<Map.Entry> itr1 = address.entrySet().iterator();
                while (itr1.hasNext()) {
                    Map.Entry pair = itr1.next();

                    if (pair.getKey().equals("price"))
                        System.out.println(pair.getKey() + " : " + pair.getValue() + "\n");
                    //System.out.println(pair.getValue());
                }
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
