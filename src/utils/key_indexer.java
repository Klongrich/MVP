package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

public class key_indexer {


    public static void main(String args[]) throws Exception {


            Object obj = new JSONParser().parse(new FileReader("./src/utils/carIndex.json"));

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            // typecasting obj to JSONObject
            Map address = ((Map)jo.get("allMakerModels"));

        Iterator<Map.Entry> itr1 = address.entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry pair = itr1.next();

            System.out.println(pair.getKey() + " : " + pair.getValue() + "\n");
            System.out.println(pair.getValue());
        }

    }
}
