public class TypeDetector {
    public static String detectType(String value) {
        if (value == null || value.trim().isEmpty()) {
            return "String";
        }
        String trimmed = value.trim();

        // Проверка на Boolean
        if (isBoolean(trimmed)) return "Boolean";
        
        // Проверка на Integer
        if (isInteger(trimmed)) return "Integer";

        // Проверка на Long
        if (isLong(trimmed)) return "Long";
        
        // Проверка на Double/Float
        if (isDouble(trimmed)) return "Double";
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

    private static boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return str.matches("-?\\d+");
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            // Убедимся, что это действительно дробное число
            return str.matches("-?\\d*\\.\\d+([eE][+-]?\\d+)?") || 
                   str.matches("-?\\d+[eE][+-]?\\d+");
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
