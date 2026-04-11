package services.strategies;

import java.util.Map;
import java.util.function.DoubleUnaryOperator;

public class TemperatureConversor {

    // Mapa de funções para evitar if-else gigantesco
    // Key: "FROM-TO", Value: Operação matemática
    private static final Map<String, DoubleUnaryOperator> CONVERSIONS = Map.of(
            "CELSIUS-FAHRENHEIT", v -> (v * 9 / 5) + 32,
            "FAHRENHEIT-CELSIUS", v -> (v - 32) * 5 / 9,
            "CELSIUS-KELVIN",     v -> v + 273.15,
            "KELVIN-CELSIUS",     v -> v - 273.15,
            "KELVIN-FAHRENHEIT",  v -> (v - 273.15) * 9 / 5 + 32,
            "FAHRENHEIT-KELVIN",  v -> (v - 32) * 5 / 9 + 273.15
    );


    public static double convert(String from, String to, double value) {

        // Se from e to forem iguais, retorna o valor
        if (from.equalsIgnoreCase(to)) return value;

        // Cria uma chave formado pelas variaveis from e to ex: CELSIUS-FAHRENHEIT
        String key = (from + "-" + to).toUpperCase().trim();

        // Pega o resultado da operação na constante CONVERSIONS utilizando a chave construida
        DoubleUnaryOperator operation = CONVERSIONS.get(key);

        // Verificando se o resultado é nulo
        if (operation == null) {
            throw new UnsupportedOperationException("Conversão de " + from + " para " + to + " não suportada.");
        }

        // Retorna se houver resultado
        return operation.applyAsDouble(value);
    }
}
