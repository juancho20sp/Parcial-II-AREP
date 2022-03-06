package co.edu.escuelaing.sparkdockerdemolive;

import co.edu.escuelaing.sparkdockerdemolive.services.items.ItemServiceImpl;

import static spark.Spark.*;

// java -cp "target/classes;target/dependency/*" co.edu.escuelaing.sparkdockerdemolive.SparkWebServer
public class SparkWebServer {

    public static void main( String[] args ) {
//        final ConverterServiceImpl converterServiceImpl = new ConverterServiceImpl();
//        final Errors errors = new Errors();
        final ItemServiceImpl itemServiceImpl = new ItemServiceImpl();

        // Set the port
        port(getPort());

        // $
        System.out.println("Spark running on port: " + getPort());

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
//                    String value = req.queryParams("value");
//
//                    if(value != null && converterServiceImpl.isValueValid(value)) {
//                        return converterServiceImpl.celsiusToFahrenheit(Double.parseDouble(value));
//                    } else {
//                        JsonObject error = errors.formatError();
//                        error.addProperty("value", value);
//
//                        return error;
//                    }
                    return itemServiceImpl.getAllItems();
                });
            });
            path("/messages", () -> {
                post("", (req, res) -> {
                    res.type("application/json");

                    System.out.println(req.body());

                    if (req.body() != null) {
                        itemServiceImpl.addItem(req.body());
                    }

//                    String value = req.queryParams("value");
//                    req.b
//
//                    if(value != null && converterServiceImpl.isValueValid(value)) {
//                        return converterServiceImpl.fahrenheitToCelsius(Double.parseDouble(value));
//                    } else {
//                        JsonObject error = errors.formatError();
//                        error.addProperty("value", value);
//
//                        return error;
//                    }
                    String[] response = {req.body()};
                    return response;
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