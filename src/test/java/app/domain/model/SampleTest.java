package app.domain.model;

import app.domain.model.test.Sample;
import org.junit.Test;

import static org.junit.Assert.*;

public class SampleTest {


    @Test
    public void getBarcode() {

        Sample sample = new Sample("12345678909", "100001230000", "2021/05/27 18:17:23");

        assertEquals("12345678909", sample.getBarcode());
    }

    @Test
    public void getTestID() {
        Sample sample = new Sample("12345678909", "100001230000", "2021/05/27 18:17:23");

        assertEquals("100001230000", sample.getTestID());
    }

    @Test
    public void getDate() {
        Sample sample = new Sample("12345678909", "100001230000", "2021/05/27 18:17:23");

        assertEquals("2021/05/27 18:17:23", sample.getDate());
    }
}