package co.edu.escuelaing.parcial2.proxy;

import co.edu.escuelaing.parcial2.proxy.services.HttpRequestMaker;

import static spark.Spark.*;

// java -cp "target/classes;target/dependency/*" co.edu.escuelaing.parcial2.SparkWebServer
public class SparkProxy {
    public static void main( String[] args ) {
        RoundR
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

                    String url = r

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
        return 4567; //returns default port if heroku-port isn't set
    }

    static int getTargetServerOne() {
        if (System.getenv("SERVER_ONE") != null) {
            return System.getenv("SERVER_ONE");
        }
        return "ec2-54-225-12-51.compute-1.amazonaws.com:4567"; //returns default port if heroku-port isn't set
    }

    static int getTargetServerTwo() {
        if (System.getenv("SERVER_TWO") != null) {
            return System.getenv("SERVER_TWO");
        }
        return null; //returns default port if heroku-port isn't set
    }

}