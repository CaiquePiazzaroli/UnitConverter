package services;

import model.ConversionRequest;

public class UnitConversionService {
    public static double convert(ConversionRequest conversionRequest) {
        System.out.println(conversionRequest.getUnit());
        System.out.println(conversionRequest.getFrom());
        System.out.println(conversionRequest.getTo());
        System.out.println(conversionRequest.getValue());
        return 0;
    }


}
