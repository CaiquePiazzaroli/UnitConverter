package util;

import com.google.gson.Gson;
import model.ConversionRequest;
import services.enums.TemperatureUnit;
import services.enums.WeightUnit; // Importe os outros enums
import services.enums.LengthUnit;

public class ConversionRequestParser {
    private static final Gson gson = new Gson();

    public static ConversionRequest parse(String json) {
        ConversionRequest request = gson.fromJson(json, ConversionRequest.class);
        validateRequest(request);
        return request;
    }

    private static void validateRequest(ConversionRequest request) {
        if (request == null) throw new IllegalArgumentException("Corpo da requisição vazio");
        if (request.getUnit() == null) throw new IllegalArgumentException("O campo 'unit' é obrigatório");

        String category = request.getUnit().toLowerCase();

        switch (category) {
            case "temperature":
                TemperatureUnit.fromString(request.getFrom());
                TemperatureUnit.fromString(request.getTo());
                break;
            case "weight":
                WeightUnit.fromString(request.getFrom());
                WeightUnit.fromString(request.getTo());
                break;
            case "length":
                LengthUnit.fromString(request.getFrom());
                LengthUnit.fromString(request.getTo());
                break;
            default:
                throw new IllegalArgumentException("Categoria de unidade desconhecida: " + category);
        }
    }
}