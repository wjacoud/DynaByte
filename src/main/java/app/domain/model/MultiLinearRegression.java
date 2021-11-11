package app.domain.model;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;

/**
 * Class responsible for performing the calculations related to multilinear regression
 *
 * @author Ricardo Faria 1201405 and Rodrigo Oliveira 1201406
 */
public class MultiLinearRegression {
    private MatrixManipulation matrixManipulation = new MatrixManipulation();
    private double[][] c;
    private double f0;
    private double intercept;
    private double slope1;
    private double slope2;
    private double r2;
    private double r2adjusted;
    private double r;
    private double[] betta;
    private double sqt;
    private double sqr;
    private double sqe;
    private double mqr;
    private double mqe;
    private int n;
    private int k;

    /**
     * Constructor responsible for creating auxiliary matrices and vectors and for calling calculation methods.
     *
     * @param x matrix with covid tests per day inside the date interval and with ages inside the date interval
     * @param y positive covid tests per day into array inside interval
     */
    public MultiLinearRegression(double[] y, double[][] x) {
        double[][] m1 = new double[x.length][x[0].length + 1];
        for (int c = 0; c < m1.length; c++) {
            m1[c][0] = 1;
            for (int j = 1; j < m1[c].length; j++) {
                m1[c][j] = x[c][j - 1];
            }
        }

        k = 2;
        n = y.length;

        double[][] x1 = matrixManipulation.transposedMatrix(m1);
        double[][] x2 = matrixManipulation.multiplyMatrices(x1, m1);
        double[][] x3 = matrixManipulation.invertMatrix(x2);
        double[][] x4 = matrixManipulation.multiplyMatrices(x3, matrixManipulation.transposedMatrix(m1));

        betta = new double[x4.length];

        for (int j = 0; j < x4.length; j++) {
            for (int m = 0; m < x4[0].length; m++) {
                betta[j] += x4[j][m] * y[m];
            }
        }

        c = matrixManipulation.invertMatrix(matrixManipulation.multiplyMatrices(matrixManipulation.transposedMatrix(m1), m1));

        intercept = betta[0];
        slope1 = betta[1];
        slope2 = betta[2];

        calculateSQ(y, m1);

        calculateR();

        calculateMQ();
    }

    /**
     * calculates all SQ
     */
    private void calculateSQ(double[] y, double[][] x) {
        //SQR
        double[][] bettaM = new double[betta.length][1];
        for (int i = 0; i < betta.length; i++) {
            bettaM[i][0] = betta[i];
        }
        x = matrixManipulation.transposedMatrix(x);

        double[] x2 = new double[x[0].length];
        for (int i = 0; i < x[0].length; i++) {
            for (int j = 0; j < bettaM.length; j++) {
                x2[i] += x[j][i] * bettaM[j][0];
            }
        }

        double x3 = 0;
        for (int i = 0; i < y.length; i++) {
            x3 += y[i] * x2[i];
        }

        sqr = x3 - y.length * Math.pow(calculateYm(y), 2);


        //SQT
        int n = y.length;
        double y1 = 0;
        for (double v : y) {
            y1 += v * v;
        }

        sqt = y1 - n * Math.pow(calculateYm(y), 2);


        //SQE
        sqe = sqt - sqr;
    }

    /**
     * calculates all R
     */
    private void calculateR() {
        //R2
        r2 = sqr / sqt;

        //R
        r = Math.sqrt(r2);

        //R2Adjusted
        r2adjusted = (1 - (((n - 1.0) / (n - (k + 1))) * (1 - r2)));
    }

    /**
     * calculates all MS
     */
    private void calculateMQ() {
        mqr = sqr / k;
        mqe = sqe / (n - (k + 1));
        f0 = mqr / mqe;
    }

    /**
     * calculates the ym
     *
     * @return the ym
     */
    private double calculateYm(double[] y) {
        double ym = 0;

        for (double v : y) {
            ym += v;
        }
        return ym / y.length;
    }

    /**
     * Returns the expected response y given the value of the predictor
     * variable x.
     *
     * @param x the value of the predictor variable
     * @return the expected response y given the value of the predictor
     * variable x
     */
    public double predict(double[] x) {
        double predictValue = betta[0];
        for (int c = 1; c < x.length; c++) {
            predictValue += betta[c] * x[c];
        }
        if (predictValue < 0){
            predictValue = 0;
        }
        return predictValue;
    }

    /**
     * Return the the upper limit of the interval
     *
     * @param x0    the value of the predictor variable
     * @param alpha significance level coefficient
     * @return the upper limit of the interval
     */
    public double upperLimit(double[] x0, double alpha) {
        double[] x1 = new double[x0.length + 1];

        x1[0] = 1;

        System.arraycopy(x0, 0, x1, 1, x1.length - 1);

        double[][] x2 = new double[x1.length][1];

        for (int c = 0; c < x1.length; c++) {
            x2[c][0] = x1[c];
        }

        double[] cx = matrixManipulation.multiplyMatrixVector(c, x1);

        double x3 = 0;

        for (int c = 0; c < cx.length; c++) {
            x3 += cx[c] * x2[c][0];
        }

        return predict(x0) + getT(alpha) * Math.sqrt(mqe * (1 + x3));
    }

    /**
     * Return the the lower limit of the interval
     *
     * @param x0    the value of the predictor variable
     * @param alpha significance level coefficient
     * @return the lower limit of the interval
     */
    public double lowerLimit(double[] x0, double alpha) {
        double[] x1 = new double[x0.length + 1];

        x1[0] = 1;

        System.arraycopy(x0, 0, x1, 1, x1.length - 1);

        double[][] x2 = new double[x1.length][1];

        for (int c = 0; c < x1.length; c++) {
            x2[c][0] = x1[c];
        }

        double[] cx = matrixManipulation.multiplyMatrixVector(c, x1);

        double x3 = 0;

        for (int c = 0; c < cx.length; c++) {
            x3 += cx[c] * x2[c][0];
        }

        return predict(x0) - getT(alpha) * Math.sqrt(mqe * (1 + x3));
    }

    /**
     * return the t student distribution value
     *
     * @param alpha significance level coefficient
     * @return the t student distributionvalue
     */
    public double getT(double alpha) {
        TDistribution td = new TDistribution(n - k - 1);
        return td.inverseCumulativeProbability(1 - alpha);
    }

    /**
     * return the t student value observed
     *
     * @return the t student value observed
     */
    public double getTObs(int index) {
        return betta[index] / Math.sqrt(mqe * c[index][index]);
    }

    /**
     * return the fisher–snedecor distribution value
     *
     * @param alpha significance level coefficient
     * @return the Fisher–snedecor distribution value
     */
    public double getF(double alpha) {
        FDistribution fDistribution = new FDistribution(k, n - (k + 1));
        return fDistribution.inverseCumulativeProbability(1 - alpha);
    }

    /**
     * Returns the coefficient of determination R^2.
     *
     * @return the coefficient of determination R^2,
     * which is a real number between 0 and 1
     */
    public double getR2() {
        return r2;
    }

    /**
     * return the sample correlation coefficient
     *
     * @return the sample correlation coefficient
     */
    public double getR() {
        return r;
    }

    /**
     * Returns the coefficient of determination R^2 adjusted.
     *
     * @return the coefficient of determination R^2 adjusted,
     * which is a real number between 0 and 1
     */
    public double getR2adjusted() {
        return r2adjusted;
    }

    /**
     * return the regression sum of squares
     *
     * @return the regression sum of squares
     */
    public double getSqr() {
        return sqr;
    }

    /**
     * return the residual sum of squares
     *
     * @return the residual sum of squares
     */
    public double getSqe() {
        return sqe;
    }

    /**
     * return the mean square error
     *
     * @return the mean square error
     */
    public double getMqe() {
        return mqe;
    }

    /**
     * return the test statistcs
     *
     * @return the test statistics
     */
    public double getF0() {
        return f0;
    }

    /**
     * return the residue/diversion number
     *
     * @return the residue/diversion number
     */
    public int getN() {
        return n;
    }

    /**
     * Returns a string representation of the simple linear regression model.
     *
     * @return a string representation of the simple linear regression model,
     * including the best-fit line and the coefficient of determination
     * R^2
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("^y").append(String.format("%.2fx1 + %.2fx2 + %.2f", slope1, slope2, intercept));
        return sb.toString();
    }
}
