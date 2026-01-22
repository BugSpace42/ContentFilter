public class TypeDetector {
    public static String detectType(String value) {
        if (value == null || value.trim().isEmpty()) {
            return "String";
        }
        String trimmed = value.trim();
        
        // 1. Проверка на Boolean
        if (isBoolean(trimmed)) return "Boolean";
        
        // 2. Проверка на Integer
        if (isInteger(trimmed)) return "Integer";
        return "String";
    }
    
    private static boolean isBoolean(String str) {
        return str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false");
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
