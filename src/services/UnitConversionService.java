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
                TemperatureConversor.convert(conversionRequest.getFrom(), conversionRequest.getTo(), conversionRequest.getValue());
                break;
            case "weight":
                WeightConversor.convert(conversionRequest.getFrom(), conversionRequest.getTo(), conversionRequest.getValue());
                break;
            case "length":
                LengthConversor.convert(conversionRequest.getFrom(), conversionRequest.getTo(), conversionRequest.getValue());
                break;
            default:
                System.out.println("Não foi possivel covnerter a unidade: " + unit);
        }

        return 0;
    }


}
