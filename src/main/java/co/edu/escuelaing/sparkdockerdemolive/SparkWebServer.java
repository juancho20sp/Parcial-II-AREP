package co.edu.escuelaing.sparkdockerdemolive;

import co.edu.escuelaing.sparkdockerdemolive.services.database.MongoDBConnection;
//import co.edu.escuelaing.sparkdockerdemolive.services.items.ItemServiceImpl;
import java.util.ArrayList;

import static spark.Spark.*;

// java -cp "target/classes;target/dependency/*" co.edu.escuelaing.sparkdockerdemolive.SparkWebServer
public class SparkWebServer {
    public static void main( String[] args ) {
//        final ItemServiceImpl itemServiceImpl = new ItemServiceImpl();
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
                    // $
                    System.out.println("GET RECEIVED");

                    res.type("application/json");
                    mongoDBConnection.createConnection();

                    // $
                    System.out.println(" --- --- ");
                    System.out.println("CONNECTION CREATED");
                    System.out.println(" --- --- ");

                    ArrayList<String> allItems = mongoDBConnection.getAllItems();

                    // $
                    System.out.println(" --- --- ");
                    System.out.println("TRANSACTION DONE");
                    System.out.println(" --- --- ");

                    mongoDBConnection.closeConnection();

                    // $
                    System.out.println(" --- --- ");
                    System.out.println("CONNECTION CLOSED");
                    System.out.println(" --- --- ");

                    // $
                    System.out.println(" --- --- ");
                    System.out.println(allItems);
                    System.out.println(" --- --- ");

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

                    // $
                    System.out.println("INSERTED AND WAITING FOR ALL ITEMS");

                    ArrayList<String> allItems = mongoDBConnection.getAllItems();

                    mongoDBConnection.closeConnection();

                    return allItems;
//                    return itemServiceImpl.getAllItems();
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