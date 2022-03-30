package co.edu.escuelaing.parcial2;

import co.edu.escuelaing.parcial2.services.MathServiceImpl;
import co.edu.escuelaing.parcial2.utils.Errors;
import com.google.gson.JsonObject;
import static spark.Spark.*;

// java -cp "target/classes;target/dependency/*" co.edu.escuelaing.parcial2.SparkWebServer
public class SparkWebServer {
    public static void main( String[] args ) {
        final MathServiceImpl mathServiceImpl = new MathServiceImpl();
        final Errors errors = new Errors();

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

                    String value = req.queryParams("value");

                    if(value != null && mathServiceImpl.isValueValid(value)) {
                        return mathServiceImpl.calculateCos(Double.parseDouble(value));
                    } else {
                        JsonObject error = errors.formatError();
                        error.addProperty("value", value);

                        return error;
                    }
                });
            });
            path("/exp", () -> {
                get("", (req, res) -> {
                    res.type("application/json");

                    String value = req.queryParams("value");

                    if(value != null && mathServiceImpl.isValueValid(value)) {
                        return mathServiceImpl.calculateExp(Double.parseDouble(value));
                    } else {
                        JsonObject error = errors.formatError();
                        error.addProperty("value", value);

                        return error;
                    }
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