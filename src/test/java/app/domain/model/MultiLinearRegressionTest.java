package app.domain.model;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MultiLinearRegressionTest {
    
    double[] v2 = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 2.0, 3.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    double[][] matrix = {{0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {2.0, 0.0}, {3.0, 0.0}, {2.0, 20.0}, {0.0, 20.0}, {0.0, 20.0}, {0.0, 0.0}, {1.0, 0.0}, {0.0, 0.0}, {0.0, 20.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}, {0.0, 0.0}};
    
    MultiLinearRegression mlr = new MultiLinearRegression(v2, matrix);

    @Test
    public void mlrCorrect() {
        new MultiLinearRegression(v2, matrix);
    }

    @Test(expected = NullPointerException.class)
    public void mlrWrong1() {
        double[][] matrix = null;
        new MultiLinearRegression(v2, matrix);
    }

    @Test(expected = NullPointerException.class)
    public void mlrWrong2() {
        double[] v0 = null;
        new MultiLinearRegression(v0, matrix);
    }

    @Test
    public void getTWrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", mlr.getT(0.9));
        Assert.assertNotEquals(expected, result);
    }

    @Test(expected = OutOfRangeException.class)
    public void getTNull() {
        mlr.getT(50);
    }

    @Test
    public void getFWrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", mlr.getF(0.9));
        Assert.assertNotEquals(expected, result);
    }

    @Test(expected = OutOfRangeException.class)
    public void getFNull() {
        mlr.getF(50);
    }

    @Test
    public void getF0Wrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", mlr.getF0());
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getNCorrect() {
        String expected = "20";
        String result = String.valueOf(mlr.getN());
        Assert.assertEquals(expected, result);
    }

    @Test
    public void getNWrong() {
        String expected = "0";
        String result = String.valueOf(mlr.getN());
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getRWrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", mlr.getR());
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getR2Wrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", mlr.getR2());
        Assert.assertNotEquals(expected, result);
    }


    @Test
    public void getR2AdjustedWrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", mlr.getR2adjusted());
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getMSEWrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", mlr.getMqe());
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getRssWrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", mlr.getSqe());
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getSsrWrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", mlr.getSqr());
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getTObsWrong() {
        String expected = "0.0000";
        String result = String.format("%.04f", mlr.getTObs(1));
        Assert.assertNotEquals(expected, result);
    }

    @Test
    public void getPredictedWrong() {
        double[] v2 = {1, 2};
        String expected = "0.0000";
        String result = String.format("%.04f", mlr.predict(v2));
        Assert.assertNotEquals(expected, result);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getPredictedOut() {
        mlr.predict(v2);
    }

    @Test
    public void getUpperWrong() {
        double[] v2 = {1, 2};
        String expected = "0.0000";
        String result = String.format("%.04f", mlr.upperLimit(v2, 0.9));
        Assert.assertNotEquals(expected, result);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getUpperNull1() {
        mlr.upperLimit(v2, 0.9);
    }

    @Test(expected = OutOfRangeException.class)
    public void getUpperNull2() {
        double[] v2 = {1, 2};
        mlr.upperLimit(v2, 5);
    }

    @Test
    public void getLowerWrong() {
        double[] v2 = {1, 2};
        String expected = "0.0000";
        String result = String.format("%.04f", mlr.lowerLimit(v2, 0.9));
        Assert.assertNotEquals(expected, result);
    }

    @Test(expected = OutOfRangeException.class)
    public void getLowerNull1() {
        double[] v2 = {1, 2};
        mlr.lowerLimit(v2, 5);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void getLowerNull2() {
        mlr.lowerLimit(v2, 0.9);
    }

    @Test
    public void getToStringWrong() {
        String expected = " ";
        String result = mlr.toString();
        Assert.assertNotEquals(expected, result);
    }
}