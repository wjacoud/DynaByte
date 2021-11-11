package app.domain.model.test;

/**
 * class responsible for the TestParameterResult
 *
 * @author Ricardo Faria 1201405
 */
public class TestParameterResult implements java.io.Serializable{

    private String idParameter;
    private double result;
    private RefValue refValue;

    /**
     * Constructor that create a full TestParameterResult object
     *
     * @param idParameter parameter id
     * @param result      result of the parameter
     */
    public TestParameterResult(String idParameter, double result) {
        this.idParameter = idParameter;
        this.result = result;
    }


    /**
     * return the result of the parameter
     *
     * @return result of the parameter
     */
    public double getResult() {
        return result;
    }


    /**
     * return the reference value
     *
     * @return reference value
     */
    public RefValue getRefValue() {
        return refValue;
    }


    /**
     * set the new reference value
     *
     * @param refValue reference value
     * @return success of the set
     */
    public boolean setRefValue(RefValue refValue) {
        this.refValue = refValue;
        return true;
    }
}
