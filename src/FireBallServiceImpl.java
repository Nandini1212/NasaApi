//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FireBallServiceImpl  {
    public String API_URL = "https://ssd-api.jpl.nasa.gov/fireball.api";
    int THRESHOLD_CAPACITY = 2;

    public FireBallServiceImpl() {
    }


    public List<FireBall> getDataFromAPICall(String minDate) {
        try {
            StringBuilder url = new StringBuilder(this.API_URL.concat("?date-min=").concat(minDate).concat("&req-loc=true"));
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url.toString())).build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 400) {
                System.out.println("BAD_REQUEST");
                throw new Exception();
            } else if (response.statusCode() == 500) {
                System.out.println("INTERNAL_SERVER_ERROR");
                throw new Exception();
            } else if (response.statusCode() == 300) {
                System.out.println("FORBIDDEN ERROR");
                throw new Exception();
            } else if (response.statusCode() == 405) {
                System.out.println("Method Not Allowed");
                throw new Exception();
            } else if (response.statusCode() == 503) {
                System.out.println("Service Unavailable");
                throw new Exception();
            } else if (response.statusCode() == 200) {
                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(response.body());
                int count = Integer.valueOf(json.get("count").toString());
                if (this.THRESHOLD_CAPACITY > count) {
                    return Collections.emptyList();
                } else {
                    JSONArray array = new JSONArray();
                    array.add(json.get("data"));
                    List<FireBall> fireBalls = new ArrayList<>();
                    for (int i = 0; i < count; ++i) {
                        JSONArray jsonArray = (JSONArray) ((JSONArray) array.get(0)).get(i);
                        //if (!Util.isNull(new Object[]{jsonArray.get(Fields.TOTAL_IMPACTED_ENERGY.getValue()), jsonArray.get(Fields.LATITUDE.getValue()), jsonArray.get(Fields.LATITUDE_DIRECTION.getValue()), jsonArray.get(Fields.LONGITUDE.getValue()), jsonArray.get(Fields.LONGITUDE_DIRECTION.getValue())})) {
                            FireBall fireball = new FireBall();
                            fireball.setTotalImpactEnergy(Double.parseDouble(jsonArray.get(Fields.TOTAL_IMPACTED_ENERGY.getValue()).toString()));
                            fireball.setLatitude(Double.parseDouble(jsonArray.get(Fields.LATITUDE.getValue()).toString()));
                            fireball.setLatDir(jsonArray.get(Fields.LATITUDE_DIRECTION.getValue()).toString());
                            fireball.setLongitude(Double.parseDouble(jsonArray.get(Fields.LONGITUDE.getValue()).toString()));
                            fireball.setLongDir(jsonArray.get(Fields.LONGITUDE_DIRECTION.getValue()).toString());
                            fireBalls.add(fireball);
                            System.out.println(" in fields :" + jsonArray.get(Fields.TOTAL_IMPACTED_ENERGY.getValue()).toString());
                        //}
                    }
                    return fireBalls;
                }
            }
        } catch (Exception exception) {
            return Collections.emptyList();
        }
        return Collections.emptyList();
    }
}