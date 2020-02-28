package APIs;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import search.averages;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class cargurusAPI {

    private static final String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:58.0) Gecko/20100101 Firefox/58.0";

    private static final String Accept = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";

    private static final String AcceptLanguage = "en-US,en;q=0.5";

    private static final String AcceptEncoding = "gzip, deflate, br";

    private static final String Connection = "keep-alive";

    private static final String UpgradeInsecureRequests = "1";

    private String meta_file_path = null;
    private String csv_file_path = null;
    private String averages_file_path = null;

    public cargurusAPI() {};

    public void createData(String ID, String year, String index) {

        DateFormat df = new SimpleDateFormat("MM-dd-yy");
        Date dateobj = new Date();

        try {

            URL url = new URL("https://www.cargurus.com/Cars/searchResults.action" +
                    "?zip=33409" +
                    "&inventorySearchWidgetType=AUTO" +
                    "&nonShippableBaseline=73" +
                    "&sortDir=ASC" +
                    "&sourceContext=RecentSearches_true_0" +
                    "&distance=5000" +
                    "&sortType=DEAL_SCORE" +
                    "&endYear=2020" +
                    "&entitySelectingHelper.selectedEntity=" + ID +
                    "&startYear=" + year +
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

            try {
                FileWriter fw = new FileWriter("/home/kyle/myfiles/java/carProject/src/data/DriveOpts/meta/" + df.format(dateobj) + "-" + ID +  "-" + index + ".json", true);
                fw.write(rawData.substring(4));
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Object obj = new JSONParser().parse(new FileReader("./src/data/DriveOpts/meta/" + df.format(dateobj) + "-" + ID + "-" + index + ".json"));

            if (obj != null) {
                JSONArray plzfuckingwork = (JSONArray) obj;

                ArrayList<Double> price = new ArrayList<>();
                ArrayList<Long> mileage = new ArrayList<>();
                ArrayList<String> name = new ArrayList<>();
                ArrayList<Long> daysOnMarket = new ArrayList<>();
                ArrayList<String> location = new ArrayList<>();

                if (!plzfuckingwork.isEmpty()) {
                    for (int x = 0; x < plzfuckingwork.size(); x++) {

                        Map address = ((Map) plzfuckingwork.get(x));

                        Iterator<Map.Entry> itr1 = address.entrySet().iterator();
                        while (itr1.hasNext()) {
                            Map.Entry pair = itr1.next();

                            //System.out.println(pair.getKey() + " : " + pair.getValue());

                            if (pair.getKey().equals("listingTitle"))
                                name.add((String) pair.getValue());
                            else if (pair.getKey().equals("price"))
                                price.add((double) pair.getValue());
                            else if (pair.getKey().equals("sellerCity"))
                                location.add((String) pair.getValue());
                            else if (pair.getKey().equals("mileage"))
                                mileage.add((long) pair.getValue());
                            else if (pair.getKey().equals("daysOnMarket"))
                                daysOnMarket.add((long) pair.getValue());
                        }
                    }

                    int i;
                    double priceTotal = 0;
                    long   mileTotal = 0;
                    long daysonmarketTotal = 0;

                    i = 0;
                    try {
                        FileWriter fw = new FileWriter("/home/kyle/myfiles/java/carProject/src/data/DriveOpts/cvs/" + df.format(dateobj) + "-"+ get_key_name(ID) + "-" + index + ".csv", true);
                        fw.write("Name,Price,Miles,City,State,DaysOnMarket\n");

                        for (int j = 0; j < name.size() - 1; j++) {

                            if (name.size() > j && price.size() > j && mileage.size() > j && location.size() > j && daysOnMarket.size() > j) {
                                fw.write(name.get(j) + "," + price.get(j) + "," + mileage.get(j) + "," + location.get(j) + "," + daysOnMarket.get(j) + "\n");
                                priceTotal += price.get(j);
                                mileTotal += mileage.get(j);
                                daysonmarketTotal += daysOnMarket.get(j);
                                i++;
                            }
                        }
                        fw.write("\n");
                        fw.write("\n");
                        fw.write("Price Average" + "," + "Mile Average" + "," + "days on market Average" + "\n");
                        fw.write("," + (priceTotal / i) + "," + (mileTotal / i) + ",,," + (daysonmarketTotal / i) + "\n");
                        fw.close();

                        FileWriter averages = new FileWriter("/home/kyle/myfiles/java/carProject/src/data/DriveOpts/averages-"  + df.format(dateobj) + ".csv", true);
                        averages.write(get_key_name(ID) + "-" + index + "," + (priceTotal / i) + "," + (mileTotal / i) + "," + (daysonmarketTotal / i) + "\n");
                        averages.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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

    private String get_key_name(String id)
    {
        String answer = null;
        String line = null;
        String temp[];

        BufferedReader csvReader = null;
        try {
            csvReader = new BufferedReader(new FileReader("./src/data/indexValues.csv"));
            while ((line = csvReader.readLine()) != null) {
                temp = line.split(",");
                if (temp[1].equals(id)) {
                    csvReader.close();
                    return (temp[0]);
                }
            }
            csvReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (answer);
    }
}
