package services.strategies;

public class TemperatureConversor {

    public static double convert(String from, String to, double value) {
        System.out.println("De: " + from);
        System.out.println("Para: " + to);
        System.out.println("O valor:" + value);

        if(from.equalsIgnoreCase("celsius") && to.equalsIgnoreCase("fahrenheit")) {
            return celsiusToFahrenheit(value);
        } else if (from.equalsIgnoreCase("fahrenheit") && to.equalsIgnoreCase("celsius")) {
            return fahrenheitToCelsius(value);
        }

        return 0.0;
    }

    private static double celsiusToFahrenheit(double value) {
        //(0 °C × 9/5) + 32 = 32 °F
        System.out.println("Celsius to fahrenheit");
        return (value * ((float) 9/5)) + 32;
    }

    private static double fahrenheitToCelsius(double value) {
        //(32 °F − 32) × 5/9 = 0 °C
        System.out.println("Fah to celsius");
        return (value - 32) * 5/9;

    }

}
