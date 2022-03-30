package co.edu.escuelaing.parcial2.proxy.services;

import spark.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpRequestMaker {
    RoundRobin roundRobin = new RoundRobin();

    public String makeRequest(Request req, String url) {
        String[] urlParts = url.split(":" + req.port());

        String baseURL = urlParts[0];

        String value = req.queryParams("value");

        String targetURL = baseURL + ":" + req.port() + req.uri() + "?value=" + value;


        String htmlResponse = "";

        try {
            // Create the URL
            URL siteURL = new URL(targetURL);

            // Create the connection
            URLConnection urlConnection = siteURL.openConnection();

            // Get headers
            Map<String, List<String>> headers = urlConnection.getHeaderFields();

            Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();

            // Print headers
            for(Map.Entry<String, List<String>> entry : entrySet){
                String headerName = entry.getKey();

                if (headerName != null) {
                    System.out.println(headerName + ": ");
                }

                List<String> headerValues = entry.getValue();

                for(String myValue: headerValues) {
                    System.out.println(value);
                }

                System.out.println(" ");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(siteURL.openStream()));

            String inputLine = null;

            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
                htmlResponse += inputLine;
            }

        } catch (MalformedURLException ex) {
            System.out.println("URL mal formada");
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }

        return htmlResponse;
    }
}
