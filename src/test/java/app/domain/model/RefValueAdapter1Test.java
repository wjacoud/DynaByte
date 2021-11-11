package app.domain.model;

import app.domain.model.adpaters.RefValueAdapter1;
import app.domain.model.test.RefValue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RefValueAdapter1Test {

    RefValueAdapter1 api = new RefValueAdapter1();

    @Test
    public void checkGetMetric(){
        String idParam = "IgGAN";
        String expected = "Index (S/C) Value";

        String actual = api.getMetric(idParam);

        assertEquals(expected, actual);
    }


    @Test
    public void checkGetMetricWrong(){
        String idParam = "aaaaa";
        String expected = "Index (S/C) Value";

        String actual = api.getMetric(idParam);

        assertNotEquals(expected, actual);
    }


    @Test
    public void checkGetMinRefValue(){
        String idParam = "IgGAN";
        String expected = "0.0";

        String actual = String.valueOf(api.getMinRefValue(idParam));

        assertEquals(expected, actual);
    }


    @Test
    public void checkGetMinRefValueWrong(){
        String idParam = "aaa";
        String expected = "0.0";

        String actual = String.valueOf(api.getMinRefValue(idParam));

        assertNotEquals(expected, actual);
    }


    @Test
    public void checkGetMaxRefValue(){
        String idParam = "IgGAN";
        String expected = "1.4";

        String actual = String.valueOf(api.getMaxRefValue(idParam));

        assertEquals(expected, actual);
    }


    @Test
    public void checkGetMaxRefValueWrong(){
        String idParam = "aaa";
        String expected = "1.4";

        String actual = String.valueOf(api.getMaxRefValue(idParam));

        assertNotEquals(expected, actual);
    }

    @Test
    public void getRefValue(){
        RefValue expected = new RefValue("Index (S/C) Value", 0.0, 1.4);

        RefValue actual = api.getRefValue("Index (S/C) Value");

        assertEquals(expected.getClass(), actual.getClass());
    }
}