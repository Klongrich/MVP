package data;
import java.io.*;
import java.net.*;
import java.net.URLConnection;
import java.io.IOException;

public class carsforsaleData {

    private static final String GET_URL = "https://www.carsforsale.com/Search?SearchTypeID=2&ZipCode=44011&Radius=100&Makes=Acura&Models=Integra&Conditions=Used&PageNumber=1&OrderBy=relevance&OrderDirection=desc";

    private static final String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:58.0) Gecko/20100101 Firefox/58.0";

    private static final String Accept = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";

    private static final String AcceptLanguage = "en-US,en;q=0.5";

    private static final String AcceptEncoding = "gzip, deflate, br";

    private static final String origin = "https://www.carsforsale.com";

    private static final String Cookie = "ASP.NET_SessionId=jss5lcky0hwthovbviysiyeb; ProfileId=; AvatarUrl=; FirstName=; LastName=; Initials=; Email=; Phone=; LoggedIn=False; MenagerieExternalEndpoint=https://cdn-static-blob.carsforsale.com; serverid=extweb202|XkHOx|XkHKI; __cfduid=d76ce63b7d46cf21a58c440b2c05470a51581369885; D_IID=5342B290-25B7-3F56-90C8-C9A6DDB55E7C; D_UID=86E0E6D6-99F7-37FF-919F-61F1BC277E8C; D_ZID=1A2DBF15-9C65-3A48-B8AF-4E669687A56C; D_ZUID=08162C2B-0375-3A3A-9DDF-BA19FE338B31; D_HID=05F21AAE-E3A1-3105-B34C-A48742754F66; D_SID=24.182.48.162:KDLTqmKimRkaYokSMUXLNv0BP/aSPsaaNKgDKd1D0E8; _ga=GA1.2.514880285.1581369882; _gid=GA1.2.1349311082.1581369882; __auc=d10d372417030fd76f0b5ad7b3b; cfsZipCode=44011";

    private static final String Connection = "keep-alive";


    public static void main(String args[]) {

        try {
                URL obj = new URL(GET_URL);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");

                con.setRequestProperty("User-Agent", USER_AGENT);
                con.setRequestProperty("Accept" , Accept);
                con.setRequestProperty("Accept-Language", AcceptLanguage);
                //con.setRequestProperty("Accept-Encoding", AcceptEncoding);
                //con.setRequestProperty("orgin", origin);
                //con.setRequestProperty("Cookie", Cookie);
                //con.setRequestProperty("Connection", Connection);

                int responseCode = con.getResponseCode();
                System.out.println("GET Response Code :: " + responseCode);


                if (responseCode == HttpURLConnection.HTTP_OK) { // success

                    InputStreamReader stream = new InputStreamReader(con.getInputStream());
                    BufferedReader buff = new BufferedReader(stream);

                    String line = buff.readLine();


                        System.out.println(line);


                } else {
                    System.out.println("GET request not worked");
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
