package co.edu.escuelaing.sparkdockerdemolive;

import co.edu.escuelaing.sparkdockerdemolive.services.items.ItemServiceImpl;
import static spark.Spark.*;

// java -cp "target/classes;target/dependency/*" co.edu.escuelaing.sparkdockerdemolive.SparkWebServer
public class SparkWebServer {

    public static void main( String[] args ) {
        final ItemServiceImpl itemServiceImpl = new ItemServiceImpl();

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

                    System.out.println("GET RECEIVED");

                    return itemServiceImpl.getAllItems();
                });
            });
            path("/messages", () -> {
                post("", (req, res) -> {
                    res.type("application/json");

                    if (req.body() != null) {
                        itemServiceImpl.addItem(req.body());
                    }

                    return itemServiceImpl.getAllItems();
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