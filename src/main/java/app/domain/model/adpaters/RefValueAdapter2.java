package app.domain.model.adpaters;

import app.domain.model.test.RefValue;
import com.example2.EMRefValue;
import com.example2.ExternalModule2API;

/**
 * adpter responsible for the connection between the program and the ExternalModule2API
 *
 * @author Ricardo Faria 1201405
 */
public class RefValueAdapter2 implements RefValueAdapter {
    ExternalModule2API api;


    /**
     * constructor that initializes the API
     */
    public RefValueAdapter2() {
        this.api = new ExternalModule2API();
    }


    /**
     * return the metric of the parameter entered
     *
     * @param idParameter parameter code
     * @return metric
     */
    public String getMetric(String idParameter) {
        return api.getMetricsFor(idParameter);
    }


    /**
     * return the reference value of the parameter entered
     *
     * @param param parameter code
     * @return reference value
     */
    public RefValue getRefValue(String param) {
        return new RefValue(getMetric(param), getMinRefValue(param), getMaxRefValue(param));
    }


    /**
     * return the mininum reference value of the parameter entered
     *
     * @param idParameter parameter code
     * @return mininum reference value
     */
    public double getMinRefValue(String idParameter) {
        EMRefValue emRefValue = api.getReferenceFor(idParameter);
        return emRefValue.getMinValue();
    }


    /**
     * return the maxinum reference value of the parameter entered
     *
     * @param idParameter parameter code
     * @return maxinum reference value
     */
    public double getMaxRefValue(String idParameter) {
        EMRefValue emRefValue = api.getReferenceFor(idParameter);
        return emRefValue.getMaxValue();
    }
}
