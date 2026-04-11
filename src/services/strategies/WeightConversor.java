package services.strategies;

import java.util.Map;
import java.util.function.DoubleUnaryOperator;

public class WeightConversor {

    private static final Map<String, DoubleUnaryOperator> CONVERSIONS = Map.of(
            "TONNE-GRAM", v -> v * 1000 * 1000,
            "TONNE-KILOGRAM", v -> v * 1000,
            "GRAM-TONNE",     v -> v / 1000 / 1000,
            "GRAM-KILOGRAM", v -> v / 1000,
            "KILOGRAM-TONNE", v -> v / 1000,
            "KILOGRAM-GRAM", v -> v * 1000
    );


    public static double convert(String from, String to, double value) {

        if(from.equalsIgnoreCase(to)) return value;

        String key = (from + "-" + to).toUpperCase();

        DoubleUnaryOperator operation = CONVERSIONS.get(key);

        if(operation == null) {
            throw new UnsupportedOperationException("Conversão de " + from + " para " + to + " não suportada.");
        }

        return operation.applyAsDouble(value);
    }
}
