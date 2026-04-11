package services.enums;

public enum WeightUnit {
    GRAM, KILOGRAM, TONNE;

    public static WeightUnit fromString(String unit) {
        try {
            return WeightUnit.valueOf(unit.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unidade de peso inválida: " + unit);
        }
    }
}
