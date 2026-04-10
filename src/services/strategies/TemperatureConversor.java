package services.strategies;

public class TemperatureConversor {

    public static double convert(String from, String to, double value) {
        if(from.equalsIgnoreCase("celsius") && to.equalsIgnoreCase("fahrenheit")) {
            return celsiusToFahrenheit(value);
        } else if (from.equalsIgnoreCase("fahrenheit") && to.equalsIgnoreCase("celsius")) {
            return fahrenheitToCelsius(value);
        }
        return 0.0;
    }

    private static double celsiusToFahrenheit(double celsius) {
        System.out.println("Celsius to fahrenheit");
        return (celsius * ((float) 9/5)) + 32;
    }

    private static double fahrenheitToCelsius(double fahrenheit) {
        System.out.println("Fah to celsius");
        return (fahrenheit - 32) * 5/9;
    }

    private static double kelvinToFahrenheit(double kelvin) {
        System.out.println("Kelvin to fahrenheit");
        return  (kelvin - 273.15) * 9/5 + 32;
    }

    private static double fahrenheitToKelvin(double fahrenheit) {
        System.out.println("fahrenheit to Kelvin");
        return (fahrenheit - 32) * 5/9 + 273.15;
    }

    private static double celsiusToKelvin(double celsius) {
        System.out.println("Celsius to kelvin");
        return celsius + 273.15;
    }

    private static double KelvinToCelsius(double kelvin) {
        System.out.println("kelvin to celsius");
        return kelvin - 273.15;
    }

}
