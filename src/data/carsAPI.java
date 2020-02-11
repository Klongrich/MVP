package data;

import java.io.*;
import java.net.*;
import java.net.URLConnection;
import java.io.IOException;
import java.util.ArrayList;

public class carsAPI {

    //private static final String GET_URL = "https://www.cars.com/for-sale/searchresults.action/?dealerType=all&page=10&perPage=100&rd=100&searchSource=GN_REFINEMENT&sort=relevance&stkTypId=28881&zc=44011";

    private static final String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:58.0) Gecko/20100101 Firefox/58.0";

    private static final String Accept = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";

    private static final String AcceptLanguage = "en-US,en;q=0.5";

    private static final String AcceptEncoding = "gzip, deflate, br";

    private static final String Cookie = "affiliate=national; srp-external-cpo=audi-original-logo; cars-abtest=unused; ak_bmsc=42D9E345BC1A9B0758E2F24A4E7B265C1734006FB950000065CA415E16806C58~plO5X7nYkbnmmS7PDpI7AWwnbjPTDof9fBETgVWjPo3BBkjRE8oHshppKvQ8ar1BiJy/rFL5/AgcQK3KvuM4Pv+22O4gp9c7+YjUtVr40mOiF/Ao9Tk+Jza4xioZQ1QLyZYNYHconEJNAHJWmQkkegZCloyp8xlMNSPrHH0+TrnfZ2t6H7cKKQdYd1Qcv+9dhxqbzHRvNhTc8D83m24ek5K/kewqyqeCPPx8jHvbRZgzc4FnCE0Z7qLfZT+dgxgCIb; s_fid=4276CD81132CBF41-0CCB720A747A99CE; s_lv=1581372541757; s_ppv=Used%2520Cars%2FSearch%2FResults-CAM-inventory%2C2%2C2%2C670; s_tp=32771; s_cc=true; _gcl_au=1.1.299791796.1581369955; _ga=GA1.2.1426574322.1581369955; _ga_LGBH9NL64W=GS1.1.1581372540.2.1.1581372541.59; s_vi=[CS]v1|2F20E5338515DB81-60000B4DC7A7D955[CE]; optimizelyEndUserId=oeu1581369956489r0.3538804638326035; Registration=currentUserId:o1Vt2I4pNf3++hVmSruSF3SUyC00a/NaNbKILlApzoCI2TYZ3XQm90luJThowk8BYljm6buywGm6pR4qm2PVR8a5tLyRiOlB; returningUser=1581369961097; CarsVisitor=%7B%22pcid%22%3A%22null%22%2C%22pdid%22%3A%221355911218385192837346031012493900%22%7D; BIGipServercars_docker_userprofiles_prd=4198634668.16415.0000; rollout=94; bm_sv=A1ABD11976D84C8A1A3780DBE42E0DCA~nxQI3icI0Bl7JxOHmVNlg/oKnLGGln5S5ulwn96RLR/8FV4uKIYgOIfJN60fInZfrnJ+Whn83m4JkKhrtC9sREMKkozX4qhGKQo7MPYuijuB9PSapyDVyl7zsCM5JVVeZEINzHaCRIBmasieDdYFPMlwfq5msq4FdaLuSJUsgZ8=; zipcode=44011; BIGipServercars_composite=2587956396.23296.0000; _gid=GA1.2.1018130920.1581369958; smtrrmkr=637169669821614313%5E017030fe-b8be-4741-847b-6d0a673bc037%5E017030fe-b8be-4662-b075-5d7e75ad6783%5E0%5E24.182.48.162; QSI_HistorySession=https%3A%2F%2Fwww.cars.com%2Ffor-sale%2Fsearchresults.action%2F%3FdealerType%3Dall%26page%3D1%26perPage%3D100%26rd%3D10%26searchSource%3DGN_REFINEMENT%26sort%3Drelevance%26stkTypId%3D28881%26zc%3D44011~1581370160494%7Chttps%3A%2F%2Fwww.cars.com%2Ffor-sale%2Fsearchresults.action%2F%3FdealerType%3Dall%26page%3D1%26perPage%3D500%26rd%3D10%26searchSource%3DGN_REFINEMENT%26sort%3Drelevance%26stkTypId%3D28881%26zc%3D44011~1581370174017; di_roxanne__visit_id=1683500212; di_roxanne__visitor_id=2774349802; kppid_managed=LfVzInXp; s_sq=%5B%5BB%5D%5D; searchByPayment=false; ADRUM=s=1581369979293&r=https%3A%2F%2Fwww.cars.com%2F%3F0; CarsSidCookie=5824955468714723540865551048389695799; adCatInfo=All|All|DEFAULT_DEFAULT; adZoneInfo=44011|neohio_west|cleveland; BIGipServercars_docker_rendering_prd=4215411884.15109.0000; s_lv_s=Less%20than%201%20day; _gat_gtag_UA_50492232_1=1";

    private static final String Connection = "keep-alive";

    private static final String UpgradeInsecureRequests= "1";



    public ArrayList<car> getData(String GET_URL) {

        ArrayList<car> carData = new ArrayList<car>();
        parse_data_helper parse = new parse_data_helper();

        try {
            URL obj = new URL(GET_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept" , Accept);
            con.setRequestProperty("Accept-Language", AcceptLanguage);
            //con.setRequestProperty("Accept-Encoding", AcceptEncoding);
            //con.setRequestProperty("Cookie", Cookie);
            //con.setRequestProperty("Connection", Connection);
            con.setRequestProperty("Upgrade-Insecure-Requests", UpgradeInsecureRequests);

            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);


            if (responseCode == HttpURLConnection.HTTP_OK) { // success

                InputStreamReader stream = new InputStreamReader(con.getInputStream());
                BufferedReader buff = new BufferedReader(stream);

                String line = buff.readLine();

                String price = null;
                String miles = null;
                String name = null;

                while (line != null) {

                    if (line.contains("listing-row__details")) {

                        for (int i = 0; i < 15; i++) {

                            if (line.contains("payment-section"))
                            {
                                buff.readLine();
                                price = parse.remove_comma(buff.readLine());
                            }

                            if (line.contains("listing-row__mileage"))
                            {
                                miles = parse.remove_comma(buff.readLine());
                            }

                            if (line.contains("listing-row__title"))
                            {
                                name = buff.readLine();
                            }
                            line = buff.readLine();
                        }
                        car newCar = new car(name, price, miles);
                        carData.add(newCar);
                    }
                    line = buff.readLine();
                }

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
        return (carData);
    }
}
