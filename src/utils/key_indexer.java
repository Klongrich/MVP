package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class key_indexer {


    public ArrayList<indexer> get_index_values(String data[], String name) {


        String temp3[] = null;
        ArrayList<indexer> results = new ArrayList<>();

        for (int j = 1; j < data.length - 1; j++) {


            System.out.println("data[1] " + data[j]);

            temp3 = data[j].split(",");

            System.out.println("temp3[0] " + temp3[0]);
            System.out.println("temp3[1] " + temp3[1]);

            String temp4[];
            String temp5[];

            temp4 = temp3[0].split("\"");
            temp5 = temp3[1].split("\"");
            System.out.println("temp4[3] " + temp4[3]);
            System.out.println("temp5[3] " + temp5[3]);

            //result = "BMW" + " " + temp4[3] + " " + temp5[3];

            indexer index = new indexer((name + " " + temp4[3]), temp5[3]);
            results.add(index);
        }
        //System.out.println("\nJSON data in string format");
        //System.out.println(inline);

        System.out.println("\n");

        for (int x = 0; x < results.size(); x++)
        {
            System.out.println(results.get(x));
        }

        return (results);
    }

}
