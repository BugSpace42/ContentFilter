public class Statistics {
    StatisticsNumbers<Long> statisticsInteger = new StatisticsNumbers<>();
    StatisticsNumbers<Float> statisticsFloat = new StatisticsNumbers<>();
    
    public void addValue(String value, DataType type) {
        switch (type) {
            case INTEGER:
                statisticsInteger.addNumber(Long.valueOf(value));
                break;
            case FLOAT:
                statisticsFloat.addNumber(Float.valueOf(value));
                break;
            default:
                break;
            
        }
    }
    public String shortStatistics(DataType type) {
        switch (type) {
            case INTEGER:
                return statisticsInteger.getShortStatistics();
            case FLOAT:
                return statisticsFloat.getShortStatistics();
            default:
                return "";
        }
    }
}
