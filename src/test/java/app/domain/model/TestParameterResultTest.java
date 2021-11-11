package app.domain.model;

import app.domain.model.test.RefValue;
import app.domain.model.test.TestParameterResult;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestParameterResultTest {

    @Test
    public void getRefValue(){
        RefValue expected = new RefValue("mm/hr", 1.0, 10.0);

        TestParameterResult testParameterResult = new TestParameterResult("WBC00",5.0);
        testParameterResult.setRefValue(expected);

        RefValue actual = testParameterResult.getRefValue();

        assertEquals(expected, actual);
    }

    @Test
    public void getRefValueWrong(){
        TestParameterResult testParameterResult = new TestParameterResult("WBC00",5.0);

        RefValue refvalue = testParameterResult.getRefValue();

        Assert.assertNull(refvalue);
    }

    @Test
    public void setRefValue(){
        RefValue refValue = new RefValue("mm/hr", 1.0, 10.0);
        TestParameterResult testParameterResult = new TestParameterResult("WBC00",5.0);
        Assert.assertTrue(testParameterResult.setRefValue(refValue));
    }


    @Test
    public void getMetric(){
        double expected = 5.0;
        TestParameterResult testParameterResult = new TestParameterResult("WBC00", expected);


        assertEquals(String.valueOf(expected), String.valueOf(testParameterResult.getResult()));
    }
}