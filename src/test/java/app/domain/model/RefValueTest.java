package app.domain.model;

import app.domain.model.test.RefValue;
import org.junit.Assert;
import org.junit.Test;


public class RefValueTest {

    RefValue refValue = new RefValue("mm/hr", 1.0, 10.0);

    @Test
    public void checkGetMetric(){
        String expected = "mm/hr";
        Assert.assertEquals(expected, refValue.getMetric());
    }

    @Test
    public void checkGetMetricWrong(){
        String expected = "mm";
        Assert.assertNotEquals(expected, refValue.getMetric());
    }

    @Test
    public void checkGetRefMin(){
        String expected = "1.0";
        Assert.assertEquals(expected, String.valueOf(refValue.getMinRefValue()));
    }

    @Test
    public void checkGetRefMinWrong(){
        String expected = "0.0";
        Assert.assertNotEquals(expected, String.valueOf(refValue.getMinRefValue()));
    }

    @Test
    public void checkGetRefMax(){
        String expected = "10.0";
        Assert.assertEquals(expected, String.valueOf(refValue.getMaxRefValue()));
    }

    @Test
    public void checkGetRefMaxWrong(){
        String expected = "0.0";
        Assert.assertNotEquals(expected, String.valueOf(refValue.getMaxRefValue()));
    }
}