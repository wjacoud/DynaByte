package app.domain.model;

import app.domain.model.test.TestParameter;
import app.domain.model.test.TestParameterResult;
import app.domain.model.testComponents.Parameter;
import app.domain.model.testComponents.ParameterCategory;
import org.junit.Assert;
import org.junit.Test;

public class TestParameterTest {

    /*
    @Test
    public void checkTestParameter() {
        ParameterCategory cat = new ParameterCategory("12348", "cat");
        Parameter parameter = new Parameter("12345", "param", "param", cat);
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(parameter);
        TestParameter testParameter = new TestParameter("000000000001", parameterList);
    }
    @Test
    public void checkGetIdTest() {
        ParameterCategory cat = new ParameterCategory("12348", "cat");
        Parameter parameter = new Parameter("12345", "param", "param", cat);
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(parameter);
        TestParameter testParameter = new TestParameter("000000000001", parameterList);

        Assert.assertEquals("000000000001",testParameter.getIdTest());
    }
    @Test
    public void checkGetParameterList() {
        ParameterCategory cat = new ParameterCategory("12348", "cat");
        Parameter parameter = new Parameter("12345", "param", "param", cat);
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(parameter);
        TestParameter testParameter = new TestParameter("000000000001", parameterList);

        Assert.assertEquals(parameterList,testParameter.getParameterList());
    }
     */
    @Test
    public void getIdTest() {
        TestParameter testParameter = new TestParameter("000000000001", null);
        Assert.assertEquals("000000000001", testParameter.getIdTest());
    }

    @Test
    public void setTestParameterResultMutant(){
        ParameterCategory pc = new ParameterCategory("11111","name");
        Parameter p = new Parameter("22222","name","aa",pc);
        TestParameterResult tpr = new TestParameterResult("22222",30);
        TestParameter tp = new TestParameter("00000",p);

        Assert.assertTrue(tp.setTestParameterResult(tpr));
    }
}
