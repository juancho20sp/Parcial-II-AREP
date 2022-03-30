package main.java.co.edu.escuelaing.parcial2;

import main.java.co.edu.escuelaing.parcial2.services.HttpRequestMaker;
import main.java.co.edu.escuelaing.parcial2.services.RoundRobin;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static spark.Spark.*;

// java -cp "target/classes;target/dependency/*" co.edu.escuelaing.parcial2.SparkWebServer
public class SparkProxy {
    public static void main( String[] args ) {
        HttpRequestMaker httpRequestMaker = new HttpRequestMaker();

        // Set the port
        port(getPort());


        // Allow CORS
        options("/*",
                (request, response) -> {
                    String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        path("/api/v1", () -> {
            path("/cos", () -> {
                get("", (req, res) -> {
                    res.type("application/json");

                    return httpRequestMaker.makeRequest(req);

                });
            });
            path("/exp", () -> {
                get("", (req, res) -> {
                    res.type("application/json");

                    return httpRequestMaker.makeRequest(req);
                });
            });
        });
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 1234; //returns default port if heroku-port isn't set
    }

}