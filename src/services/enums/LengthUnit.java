package services.enums;

public enum LengthUnit {
    CM, METRE, KM;

    public static LengthUnit fromString(String unit) {
        try {
            return LengthUnit.valueOf(unit.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unidade de comprimento inválida: " + unit);
        }
    }
}
