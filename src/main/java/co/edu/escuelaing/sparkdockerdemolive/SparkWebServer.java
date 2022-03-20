package co.edu.escuelaing.sparkdockerdemolive;

import co.edu.escuelaing.sparkdockerdemolive.services.database.MongoDBConnection;
import java.util.ArrayList;

import static spark.Spark.*;

// java -cp "target/classes;target/dependency/*" co.edu.escuelaing.sparkdockerdemolive.SparkWebServer
public class SparkWebServer {
    public static void main( String[] args ) {
        // Database connection and operations
        MongoDBConnection mongoDBConnection = new MongoDBConnection();

        // Set the port
        port(getPort());

        get("hello", (req,res) -> "Hello Docker!");

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
            path("/messages", () -> {
                get("", (req, res) -> {
                    res.type("application/json");
                    mongoDBConnection.createConnection();

                    ArrayList<String> allItems = mongoDBConnection.getAllItems();

                    mongoDBConnection.closeConnection();

                    return allItems;
                });
            });
            path("/messages", () -> {
                post("", (req, res) -> {
                    res.type("application/json");
                    mongoDBConnection.createConnection();

                    if (req.body() != null) {
                        mongoDBConnection.addItem(req.body());
                    }

                    ArrayList<String> allItems = mongoDBConnection.getAllItems();

                    mongoDBConnection.closeConnection();

                    return allItems;
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

}