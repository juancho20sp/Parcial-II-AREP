package co.edu.escuelaing.parcial2.utils;

import com.google.gson.JsonObject;

public class Errors {
    /**
     * Method in charge of returning a "Format Error" response.
     * @return A JSON with the response
     */
    public JsonObject formatError() {
        JsonObject formatError = new JsonObject();

        formatError.addProperty("status", 400);
        formatError.addProperty("message", "The value sent is not valid.");

        return formatError;
    }
}
