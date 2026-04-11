package services.strategies;

import java.util.Map;
import java.util.function.DoubleUnaryOperator;

public class LengthConversor {

    private static final Map<String, DoubleUnaryOperator> CONVERSIONS = Map.of(
            "CM-METRE",    v -> v / 100.0,
            "CM-KM",       v -> v / 100_000.0,
            "METRE-CM",    v -> v * 100.0,
            "METRE-KM",    v -> v / 1000.0,
            "KM-CM",       v -> v * 100_000.0,
            "KM-METRE",    v -> v * 1000.0
    );

    public static double convert(String from, String to, double value) {
        if (from.equalsIgnoreCase(to)) return value;

        // Normalização das strings para garantir que batam com as chaves do Map
        String key = (from + "-" + to).toUpperCase().trim();

        DoubleUnaryOperator operation = CONVERSIONS.get(key);

        if (operation == null) {
            throw new UnsupportedOperationException("Conversão de comprimento não suportada para: " + key);
        }

        return operation.applyAsDouble(value);
    }
}
