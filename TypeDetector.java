public class TypeDetector {
    public static DataType detectType(String value) {
        if (value == null || value.trim().isEmpty()) {
            return DataType.STRING;
        }
        String trimmed = value.trim();
        
        // Проверка на Integer
        if (isInteger(trimmed)) return DataType.INTEGER;

        // Проверка на Long
        if (isLong(trimmed)) return DataType.INTEGER;
        
        // Проверка на Float
        if (isFloat(trimmed)) return DataType.FLOAT;
        return DataType.STRING;
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
    
    private static boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            // Убедимся, что это действительно дробное число
            return str.matches("-?\\d*\\.\\d+([eE][+-]?\\d+)?") || 
                   str.matches("-?\\d+[eE][+-]?\\d+");
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
