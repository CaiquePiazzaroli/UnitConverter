package services.enums;

public enum TemperatureUnit {
    CELSIUS, FAHRENHEIT, KELVIN;

    public static TemperatureUnit fromString(String unit) {
        try {
            return TemperatureUnit.valueOf(unit.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unidade de temperatura inválida: " + unit);
        }
    }
}
