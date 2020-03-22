package NBPConnector;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;


import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;


public class NBPConnector {
    Map<String, Double> waluty;

    public void generate() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.nbp.pl/api/exchangerates/tables/A?format=json"))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String body = response.body();
        JSONArray myResponse = new JSONArray(response.body());
        JSONObject jsonObject = myResponse.getJSONObject(0);
        JSONArray waluty = (JSONArray) jsonObject.get("rates");
        Map<String, Double> map = new HashMap<>();
        for (int i = 0; i < waluty.length(); i++) {
            JSONObject obj = waluty.getJSONObject(i);
            map.put(obj.get("code").toString(), (Double)obj.get("mid"));
        }
        map.put("PLN", 1.0);
        this.waluty = map;
    }

    public Double exchangeRate(String name) {
        try {
            return waluty.get(name);
        } catch (Exception e){
            System.out.println("exchangeRateException");
        }
        return 0.0;
    }

    public Double calculate(String from, String to, Double howMuch){
        Double exchangeRateFrom = exchangeRate(from);
        Double exchangeRateTo = exchangeRate(to);
        Double result = howMuch*exchangeRateFrom/exchangeRateTo;
        return result;
    }

    public NBPConnector() {
        generate();

    }
}
