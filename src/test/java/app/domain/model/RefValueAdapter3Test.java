package app.domain.model;

import app.domain.model.adpaters.RefValueAdapter3;
import app.domain.model.test.RefValue;
import org.junit.Test;

import static org.junit.Assert.*;

public class RefValueAdapter3Test {


    RefValueAdapter3 api = new RefValueAdapter3();

    @Test
    public void checkGetMetric1(){
        String idParam = "ESR00";
        String expected = "mm/hr";

        String actual = api.getMetric(idParam);

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMetric2(){
        String idParam = "HB000";
        String expected = "g/L";

        String actual = api.getMetric(idParam);

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMetric3(){
        String idParam = "MCH00";
        String expected = "pg";

        String actual = api.getMetric(idParam);

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMetric4(){
        String idParam = "MCHC0";
        String expected = "g/L";

        String actual = api.getMetric(idParam);

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMetric5(){
        String idParam = "MCV00";
        String expected = "fl";

        String actual = api.getMetric(idParam);

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMetric6(){
        String idParam = "PLT00";
        String expected = "10e9L";

        String actual = api.getMetric(idParam);

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMetric7(){
        String idParam = "RBC00";
        String expected = "10e12L";

        String actual = api.getMetric(idParam);

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMetric8(){
        String idParam = "WBC00";
        String expected = "10e9L";

        String actual = api.getMetric(idParam);

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMetricWrong(){
        String idParam = "aaaa";
        String expected = "10e9L";

        String actual = api.getMetric(idParam);

        assertNotEquals(expected, actual);
    }


    @Test
    public void checkGetMinRefValue1(){
        String idParam = "ESR00";
        String expected = "1.0";

        String actual = String.valueOf(api.getMinRefValue(idParam));

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMinRefValue2(){
        String idParam = "HB000";
        String expected = "130.0";

        String actual = String.valueOf(api.getMinRefValue(idParam));

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMinRefValue3(){
        String idParam = "MCH00";
        String expected = "27.0";

        String actual = String.valueOf(api.getMinRefValue(idParam));

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMinRefValue4(){
        String idParam = "MCHC0";
        String expected = "320.0";

        String actual = String.valueOf(api.getMinRefValue(idParam));

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMinRefValue5(){
        String idParam = "MCV00";
        String expected = "80.0";

        String actual = String.valueOf(api.getMinRefValue(idParam));

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMinRefValue6(){
        String idParam = "PLT00";
        String expected = "150.0";

        String actual = String.valueOf(api.getMinRefValue(idParam));

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMinRefValue7(){
        String idParam = "RBC00";
        String expected = "4.5";

        String actual = String.valueOf(api.getMinRefValue(idParam));

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMinRefValue8(){
        String idParam = "WBC00";
        String expected = "4.0";

        String actual = String.valueOf(api.getMinRefValue(idParam));

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMinRefValueWrong(){
        String idParam = "aaaa";
        String expected = "4.0";

        String actual = String.valueOf(api.getMinRefValue(idParam));

        assertNotEquals(expected, actual);
    }

    @Test
    public void checkGetMaxRefValue1(){
        String idParam = "ESR00";
        String expected = "10.0";

        String actual = String.valueOf(api.getMaxRefValue(idParam));

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMaxRefValue2(){
        String idParam = "HB000";
        String expected = "180.0";

        String actual = String.valueOf(api.getMaxRefValue(idParam));

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMaxRefValue3(){
        String idParam = "MCH00";
        String expected = "32.0";

        String actual = String.valueOf(api.getMaxRefValue(idParam));

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMaxRefValue4(){
        String idParam = "MCHC0";
        String expected = "360.0";

        String actual = String.valueOf(api.getMaxRefValue(idParam));

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMaxRefValue5(){
        String idParam = "MCV00";
        String expected = "100.0";

        String actual = String.valueOf(api.getMaxRefValue(idParam));

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMaxRefValue6(){
        String idParam = "PLT00";
        String expected = "450.0";

        String actual = String.valueOf(api.getMaxRefValue(idParam));

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMaxRefValue7(){
        String idParam = "RBC00";
        String expected = "6.5";

        String actual = String.valueOf(api.getMaxRefValue(idParam));

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMaxRefValue8(){
        String idParam = "WBC00";
        String expected = "11.0";

        String actual = String.valueOf(api.getMaxRefValue(idParam));

        assertEquals(expected, actual);
    }

    @Test
    public void checkGetMaxRefValueWrong(){
        String idParam = "aaaa";
        String expected = "11.0";

        String actual = String.valueOf(api.getMaxRefValue(idParam));

        assertNotEquals(expected, actual);
    }

    @Test
    public void getRefValue(){
        RefValue expected = new RefValue("WBC00", 0.0, 4.0);

        RefValue actual = api.getRefValue("WBC00");

        assertEquals(expected.getClass(), actual.getClass());
    }
}