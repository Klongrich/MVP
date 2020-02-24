package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

class get_all_index_values {

    private static final String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:58.0) Gecko/20100101 Firefox/58.0";

    private static final String Accept = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";

    private static final String AcceptLanguage = "en-US,en;q=0.5";

    private static final String AcceptEncoding = "gzip, deflate, br";

    private static final String Connection = "keep-alive";

    private static final String UpgradeInsecureRequests = "1";

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
            String audi = null; //Audi
            String porsche = null; //Porsche
            String maserati = null; //Maserati
            String cadillac = null; //Cadillac
            String jaguar = null; //Jaguar
            String merceds = null; //Mercedes-Benz
            String volkswagen = null; //Volkswagen

            String cheverolet = null; //Chevrolet
            String tesla = null; //Tesla
            String lincoln = null; //Lincoln
            String honda = null; //Honda
            String kia = null; //Kia
            String jeep = null; //"Jeep"
            String subaru = null; //Subaru

            String temp[];
            temp = rawData.split("models");

            for (int i = 0; i < temp.length; i++ ) {
                System.out.println(temp[i] + "\n");
                if (temp[i].contains("BMW"))
                {
                    bmw = temp[i];
                }
                else if (temp[i].contains("Lexus"))
                {
                    lexus = temp[i];
                }
                else if (temp[i].contains("Ford"))
                {
                    ford = temp[i];
                }
                else if (temp[i].contains("Audi"))
                {
                    audi = temp[i];
                }
                else if (temp[i].contains("Porsche"))
                {
                    porsche = temp[i];
                }
                else if (temp[i].contains("Maserati"))
                {
                    maserati = temp[i];
                }
                else if (temp[i].contains("Cadillac"))
                {
                    cadillac = temp[i];
                }
                else if (temp[i].contains("Jaguar"))
                {
                    jaguar = temp[i];
                }
                else if (temp[i].contains("Mercedes-Benz"))
                {
                    merceds = temp[i];
                }
                else if (temp[i].contains("Volkswagen"))
                {
                    volkswagen = temp[i];
                }

                else if (temp[i].contains("Chevrolet"))
                {
                    cheverolet = temp[i];
                }
                else if (temp[i].contains("Tesla"))
                {
                    tesla = temp[i];
                }
                else if (temp[i].contains("Lincoln"))
                {
                    lincoln = temp[i];
                }
                else if (temp[i].contains("Honda"))
                {
                    honda = temp[i];
                }
                else if (temp[i].contains("Kia"))
                {
                    kia = temp[i];
                }
                else if (temp[i].contains("\"Jeep\""))
                {
                    jeep = temp[i];
                }
                else if (temp[i].contains("Subaru"))
                {
                    subaru = temp[i];
                }

            }

            ArrayList<ArrayList<indexer>> allResults = new ArrayList<>();
            ArrayList<indexer> bmwResults = new ArrayList<>();
            ArrayList<indexer> lexusReults = new ArrayList<>();
            ArrayList<indexer> fordResults = new ArrayList<>();
            ArrayList<indexer> audiResults = new ArrayList<>();
            ArrayList<indexer> porscheResults = new ArrayList<>();
            ArrayList<indexer> maseratiResults = new ArrayList<>();
            ArrayList<indexer> cadillacResults = new ArrayList<>();
            ArrayList<indexer> jaguarResults = new ArrayList<>();

            ArrayList<indexer> mercedesResults = new ArrayList<>();
            ArrayList<indexer> volkswagenResults = new ArrayList<>();

            ArrayList<indexer> chevroletResults = new ArrayList<>();
            ArrayList<indexer> teslaResults = new ArrayList<>();
            ArrayList<indexer> lincolnResults = new ArrayList<>();
            ArrayList<indexer> hondaResults = new ArrayList<>();
            ArrayList<indexer> kiaResults = new ArrayList<>();
            ArrayList<indexer> jeepResults = new ArrayList<>();
            ArrayList<indexer> subaruResults = new ArrayList<>();


            key_indexer test = new key_indexer();

            bmwResults = test.get_index_values(bmw.split("\\{"), "BMW");
            lexusReults = test.get_index_values(lexus.split("\\{"), "Lexus");
            fordResults = test.get_index_values(ford.split("\\{"), "Ford");
            audiResults = test.get_index_values(audi.split("\\{"), "Audi");
            porscheResults = test.get_index_values(porsche.split("\\{"), "Porsche");
            maseratiResults = test.get_index_values(maserati.split("\\{"), "Maserati");
            cadillacResults = test.get_index_values(cadillac.split("\\{"), "Cadillac");
            jaguarResults = test.get_index_values(jaguar.split("\\{"), "Jaguar");

            mercedesResults = test.get_index_values(merceds.split("\\{"), "Merceds-Benz");
            volkswagenResults = test.get_index_values(volkswagen.split("\\{"), "Volkswagen");

            chevroletResults = test.get_index_values(cheverolet.split("\\{"), "Chevrolet");
            teslaResults = test.get_index_values(tesla.split("\\{"), "Tesla");
            lincolnResults = test.get_index_values(lincoln.split("\\{"), "Lincoln");
            hondaResults = test.get_index_values(honda.split("\\{"), "Honda");
            kiaResults = test.get_index_values(kia.split("\\{"), "Kia");
            jeepResults = test.get_index_values(jeep.split("\\{"), "Jeep");
            subaruResults = test.get_index_values(subaru.split("\\{"), "Subaru");

            allResults.add(bmwResults);
            allResults.add(lexusReults);
            allResults.add(fordResults);
            allResults.add(audiResults);
            allResults.add(porscheResults);
            allResults.add(maseratiResults);
            allResults.add(cadillacResults);
            allResults.add(jaguarResults);

            allResults.add(mercedesResults);
            allResults.add(volkswagenResults);

            allResults.add(chevroletResults);
            allResults.add(teslaResults);
            allResults.add(lincolnResults);
            allResults.add(hondaResults);
            allResults.add(kiaResults);
            allResults.add(jeepResults);
            allResults.add(subaruResults);

            sc.close();

            /*
            for (int x = 0; x < allResults.size(); x++)
            {
                for (int j = 0; j < allResults.get(x).size(); j++)
                {
                    System.out.println(allResults.get(x).get(j).getName() + "," + allResults.get(x).get(j).getID());

                }
            }
            */

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
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

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
