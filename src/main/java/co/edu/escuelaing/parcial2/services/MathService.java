package co.edu.escuelaing.parcial2.services;

import java.lang.Math;

public class MathService {
    /**
     * Take the cosine of the given value
     * @param value A double number
     * @return The cosine of the given value, as a double.
     */
    public double cos(double value) {
        double valueInRadians = Math.toRadians(value);
        double cosine = Math.cos(valueInRadians);

        // $
        System.out.println("Cos: " + cosine);

        return cosine;
    }

    /**
     * Take euler to the power of value
     * @param value A double number
     * @return The euler to the power of value, as a double.
     */
    public double exp(double value) {
        double expResult = Math.exp(value);

        // $
        System.out.println("Exp: " + expResult);

        return expResult;
    }
}
