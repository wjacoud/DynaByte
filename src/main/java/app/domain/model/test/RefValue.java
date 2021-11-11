package app.domain.model.test;

/**
 * class responsible for the reference value
 *
 * @author Ricardo Faria 1201405
 */
public class RefValue implements java.io.Serializable{
    private String metric;
    private double minRefValue;
    private double maxRefValue;


    /**
     * Constructor that create a reference value
     *
     * @param metric      metric
     * @param minRefValue mininum reference value
     * @param maxRefValue maxinum reference value
     */
    public RefValue(String metric, double minRefValue, double maxRefValue) {
        this.metric = metric;
        this.minRefValue = minRefValue;
        this.maxRefValue = maxRefValue;
    }


    /**
     * return the metric
     *
     * @return metric
     */
    public String getMetric() {
        return metric;
    }


    /**
     * return the mininum reference value
     *
     * @return mininum reference value
     */
    public double getMinRefValue() {
        return minRefValue;
    }


    /**
     * return the maxinum reference value
     *
     * @return maxinum reference value
     */
    public double getMaxRefValue() {
        return maxRefValue;
    }
}
