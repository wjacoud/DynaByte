package app.domain.model.adpaters;

import app.domain.model.test.RefValue;
import app.controller.PropertiesController;
import com.example3.CovidReferenceValues1API;

import java.util.Properties;

/**
 * adpter responsible for the connection between the program and the CovidReferenceValues1API
 *
 * @author Ricardo Faria 1201405
 */
public class RefValueAdapter1 implements RefValueAdapter {
    CovidReferenceValues1API api;
    Properties proper;


    /**
     * constructor that initializes the API
     */
    public RefValueAdapter1() {
        this.api = new CovidReferenceValues1API();
        proper = PropertiesController.getProperties();
        if(proper == null){
            throw new IllegalArgumentException();
        }
    }


    /**
     * return the metric of the parameter entered
     *
     * @param idParameter parameter code
     * @return metric
     */
    public String getMetric(String idParameter) {
        return api.usedMetric(idParameter, Integer.parseInt(proper.getProperty("access.KEY")));
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
        return api.getMinReferenceValue(idParameter, Integer.parseInt(proper.getProperty("access.KEY")));
    }


    /**
     * return the maxinum reference value of the parameter entered
     *
     * @param idParameter parameter code
     * @return maxinum reference value
     */
    public double getMaxRefValue(String idParameter) {
        return api.getMaxReferenceValue(idParameter, Integer.parseInt(proper.getProperty("access.KEY")));
    }
}
