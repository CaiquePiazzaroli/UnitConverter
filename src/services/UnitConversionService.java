package services;

import model.ConversionRequest;
import services.strategies.LengthConversor;
import services.strategies.TemperatureConversor;
import services.strategies.WeightConversor;

import java.util.Map;
import java.util.function.ToDoubleFunction;

public class UnitConversionService {

    // Interface para representar o metodo convert
    // toda classe que tiver um metodo com a mesma assintura que execute
    // podera ser considerada como uma implementacao da interface abaixo
    // Ex: Na classe TemperatureConversor convert(String from, String to, double value)
    @FunctionalInterface
    interface ConversionAction {
        double execute(String from, String to, double value);
    }

    // Mapa que registra qual conversor usar para cada categoria
    private static final Map<String, ConversionAction> STRATEGIES = Map.of(
            "temperature", TemperatureConversor::convert,
            "weight",      WeightConversor::convert,
            "length",      LengthConversor::convert
    );

    public static double convert(ConversionRequest request) {
        String unitCategory = request.getUnit().toLowerCase();

        ConversionAction strategy = STRATEGIES.get(unitCategory);

        if (strategy == null) {
            throw new IllegalArgumentException("Categoria de unidade não suportada: " + unitCategory);
        }

        return strategy.execute(
                request.getFrom(),
                request.getTo(),
                request.getValue()
        );
    }
}