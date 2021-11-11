package app.domain.model;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleLinearRegressionTest {

    double[] v1 = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0, 2.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    double[] v2 = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0, 3.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    SimpleLinearRegression slr = new SimpleLinearRegression(v1, v2);

    @Test
    public void slrCorrect() {
        new SimpleLinearRegression(v1, v2);
    }

    @Test(expected = NullPointerException.class)
    public void slrWrong1() {
        double[] v0 = null;
        new SimpleLinearRegression(v0, v2);
    }

    @Test(expected = NullPointerException.class)
    public void slrWrong2() {
        double[] v0 = null;
        new SimpleLinearRegression(v1, v0);
    }

    @Test
    public void getTWrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", slr.getT(0.9));
        Assert.assertNotEquals(expected, result);
    }

    @Test(expected = OutOfRangeException.class)
    public void getTNull() {
        slr.getT(50);
    }

    @Test
    public void getFWrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", slr.getF(0.9));
        Assert.assertNotEquals(expected, result);
    }

    @Test(expected = OutOfRangeException.class)
    public void getFNull() {
        slr.getF(50);
    }

    @Test
    public void getF0Wrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", slr.getF0());
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getNCorrect() {
        String expected = "20";
        String result = String.valueOf(slr.getN());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getNWrong() {
        String expected = "0";
        String result = String.valueOf(slr.getN());
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getRWrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", slr.getR());
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getR2Wrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", slr.getR2());
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getMSEWrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", slr.getMse());
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getRssWrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", slr.getMse());
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getSsrWrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", slr.getSsr());
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getTObs1Wrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", slr.getTObs1());
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getTObs2Wrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", slr.getTObs2());
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getPredictedWrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", slr.predict(5));
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getUpperWrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", slr.upperLimit(5, 0.9));
        Assert.assertNotEquals(expected, result);
    }

    @Test(expected = OutOfRangeException.class)
    public void getUpperNull() {
        slr.upperLimit(5, 5);
    }

    @Test
    public void getLowerWrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", slr.lowerLimit(5, 0.9));
        Assert.assertNotEquals(expected, result);
    }

    @Test(expected = OutOfRangeException.class)
    public void getLowerNull() {
        slr.lowerLimit(5, 5);
    }

    @Test
    public void getToStringWrong() {
        String expected = " ";
        String result = slr.toString();
        Assert.assertNotEquals(expected, result);
    }
}