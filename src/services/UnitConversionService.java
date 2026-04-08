package services;

import model.ConversionRequest;
import services.strategies.LengthConversor;
import services.strategies.TemperatureConversor;
import services.strategies.WeightConversor;

public class UnitConversionService {
    public static double convert(ConversionRequest conversionRequest) {
        String unit = conversionRequest.getUnit();
        switch (unit.toLowerCase()) {
            case "temperature":
                return TemperatureConversor.convert(conversionRequest.getFrom(), conversionRequest.getTo(), conversionRequest.getValue());
            case "weight":
                return WeightConversor.convert(conversionRequest.getFrom(), conversionRequest.getTo(), conversionRequest.getValue());
            case "length":
                return LengthConversor.convert(conversionRequest.getFrom(), conversionRequest.getTo(), conversionRequest.getValue());
            default:
                System.out.println("Não foi possivel covnerter a unidade: " + unit);
        }
        return 0;
    }


}
