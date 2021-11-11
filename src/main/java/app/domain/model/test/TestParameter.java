package app.domain.model.test;

import app.domain.model.testComponents.Parameter;

/**
 * class responsible for the Test Parameter
 *
 * @author  Ricardo Faria 1201405 and Gonçalo Pereira 1201500
 */
public class TestParameter implements java.io.Serializable{

    private String idTest;
    private Parameter parameter;
    private TestParameterResult testParameterResult;


    /**
     * constructor that create a full TestParameter object
     * @param idTest idTest
     * @param parameter parameter
     */
    public TestParameter(String idTest, Parameter parameter){
        this.idTest = idTest;
        this.parameter = parameter;
        testParameterResult = null;
    }


    /**
     * return the test id
     * @return test id
     */
    public String getIdTest() {
        return idTest;
    }


    /**
     * return the parameter
     * @return parameter
     */
    public Parameter getParameter() {
        return parameter;
    }


    /**
     * return the test parameter result
     * @return test parameter result
     */
    public TestParameterResult getTestParameterResult() {
        return testParameterResult;
    }


    /**
     * set a new value to test parameter result
     * @param testParameterResult test parameter result
     * @return success of the set
     */
    public boolean setTestParameterResult(TestParameterResult testParameterResult) {
        this.testParameterResult = testParameterResult;
        return true;
    }
}
