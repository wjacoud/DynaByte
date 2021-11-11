package app.domain.model.adpaters;

import app.domain.model.test.RefValue;

/**
 * Interface for the adpters
 *
 * @author Ricardo Faria 1201405
 */
public interface RefValueAdapter {

    /**
     * return the reference value of the parameter entered
     *
     * @param param parameter code
     * @return reference value
     */
    RefValue getRefValue(String param);


    /**
     * return the metric of the parameter entered
     *
     * @param param parameter code
     * @return metric
     */
    String getMetric(String param);


    /**
     * return the minimun value of the value reference of the parameter entered
     *
     * @param param parameter code
     * @return minimum value
     */
    double getMinRefValue(String param);


    /**
     * return the maximum value of the value reference of the parameter entered
     *
     * @param param parameter code
     * @return maximum value
     */
    double getMaxRefValue(String param);
}
