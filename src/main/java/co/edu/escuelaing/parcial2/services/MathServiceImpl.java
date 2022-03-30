package co.edu.escuelaing.parcial2.services;

import com.google.gson.JsonObject;

public class MathServiceImpl {
    MathService mathService;

    public MathServiceImpl(){
        this.mathService = new MathService();
    }

    /**
     * Verify if the value sent is actually a double number
     * @param value
     * @return True if its a double, false otherwise
     */
    public boolean isValueValid(String value){
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Take the cosine of a number
     * @param value A double type number.
     * @return The JSON response.
     */
    public JsonObject calculateCos(double value){
        double res = this.mathService.cos(value);

        // Create the Json Object
        JsonObject json = new JsonObject();

        json.addProperty("status", 200);
        json.addProperty("operation", "cos");
        json.addProperty("input", value);
        json.addProperty("output", res);

        return json;
    }

    /**
     * Take euler to the power of a number
     * @param value A double type number.
     * @return The JSON response.
     */
    public JsonObject calculateExp(double value){
        double res = this.mathService.exp(value);

        // Create the Json Object
        JsonObject json = new JsonObject();

        json.addProperty("status", 200);
        json.addProperty("operation", "exp");
        json.addProperty("input", value);
        json.addProperty("output", res);

        return json;
    }

}
