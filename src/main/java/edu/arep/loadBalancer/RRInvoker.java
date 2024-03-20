package edu.arep.loadBalancer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RRInvoker {
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final List<Integer> PORTS = Arrays.asList(35001, 35002, 35003);
    private static final List<String> CONTAINERS = Arrays.asList("logservice1", "logservice2", "logservice3");
    private static final List<String> LOG_SERVICE_URLS = buildUrls();
    private static int currentLogService = 0;


    public static String invoke(String message) throws IOException {
        URL obj = getLogServiceUrl(message);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        //The following invocation perform the connection implicitly before getting the code

        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuffer response = new StringBuffer();

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return response.toString();
    }

    private static List<String> buildUrls() {
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < PORTS.size(); i++) {
            urls.add("http://" + CONTAINERS.get(i) + ":" + PORTS.get(i) + "/logservice");
        }
        return urls;
    }

    private static URL getLogServiceUrl(String message) throws MalformedURLException {
        String getUrl = LOG_SERVICE_URLS.get(currentLogService);
        currentLogService = (currentLogService + 1) % LOG_SERVICE_URLS.size(); //round robin
        return new URL(getUrl + "?msg=" + message);
    }

}
