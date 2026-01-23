import java.math.BigDecimal;

public class StatisticsNumbers<T extends Number & Comparable<T>> {
    private T minValue = null;
    private T maxValue = null;
    private Number sumValue = null;
    private Number avgValue = null;
    private int numberOfValues = 0;

    public void addNumber(T value) {
        if (numberOfValues == 0) {
            numberOfValues = 1;
            minValue = value;
            maxValue = value;
            avgValue = value;
            sumValue = value;
            return;
        }
        numberOfValues++;
        if (value.compareTo(minValue) < 0) {
            minValue = value;
        }
        if (value.compareTo(maxValue) > 0) {
            maxValue = value;
        }

        // Суммирование с сохранением точности
        sumValue = addNumbers(sumValue, value);
        
        // Вычисление среднего
        avgValue = divideNumber(sumValue, numberOfValues);
    }

    private Number addNumbers(Number a, Number b) {
        // Для Long
        if (a instanceof Long && b instanceof Long) {
            return ((Long) a) + ((Long) b);
        }
        // Для Integer
        else if (a instanceof Integer && b instanceof Integer) {
            return ((Integer) a) + ((Integer) b);
        }
        // Для Double
        else if (a instanceof Double || b instanceof Double) {
            return a.doubleValue() + b.doubleValue();
        }
        // Для Float
        else if (a instanceof Float || b instanceof Float) {
            return a.floatValue() + b.floatValue();
        }
        else {
            return new BigDecimal(a.toString()).add(new BigDecimal(b.toString()));
        }
    }
    
    private Number divideNumber(Number a, int divisor) {
        // Для Long - возвращаем Double
        if (a instanceof Long) {
            return a.doubleValue() / divisor;
        }
        // Для Integer - возвращаем Double
        else if (a instanceof Integer) {
            return a.doubleValue() / divisor;
        }
        // Для Double
        else if (a instanceof Double) {
            return ((Double) a) / divisor;
        }
        // Для Float
        else if (a instanceof Float) {
            return ((Float) a) / divisor;
        }
        // Для BigDecimal
        else if (a instanceof BigDecimal) {
            return ((BigDecimal) a).divide(BigDecimal.valueOf(divisor), 10, java.math.RoundingMode.HALF_UP);
        }
        // Общий случай
        else {
            return a.doubleValue() / divisor;
        }
    }

    public String getShortStatistics() {
        String statistics = String.format("Количество записанных значений: %d\n", numberOfValues);
        return statistics;
    }
    
    public String getFullStatistics() {
        String statistics = String.format("Количество записанных значений: %d\n", numberOfValues);
        statistics += String.format("Минимальное записанное значение: %d", minValue);
        statistics += String.format("Максимальное записанное значение: %d", maxValue);
        statistics += String.format("Сумма записанных значений: %d", sumValue);
        statistics += String.format("Среднее записанных значений: %d", avgValue);
        return statistics;
    }
}