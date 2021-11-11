package app.domain.model;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;

/******************************************************************************
 *  Compute least squares solution to y = beta * x + alpha.
 *  Simple linear regression.
 ******************************************************************************/

/**
 * The code LinearRegression class performs a simple linear regression
 * on an set of n data points (y_i, x_i).
 * That is, it fits a straight line y = alpha + beta * x,
 * (where y is the response variable, x is the predictor variable,
 * alpha is the y-intercept, and beta is the slope)
 * that minimizes the sum of squared residuals of the linear regression model.
 * It also computes associated statistics, including the coefficient of
 * determination R^2 and the standard deviation of the
 * estimates for the slope and y-intercept.
 */
public class SimpleLinearRegression {
    private double intercept; // y-intercept alpha -> y-interceção
    private double slope;     // slope beta -> decline
    private double r2;        // coefficient of determination -> coeficiente de determinação
    private double r;         // sample correlation coefficient -> coeficiente de correlação amostral
    private double rss;       // residual sum of squares -> soma dos quadrados dos resíduos -> se
    private double ssr;       // regression sum of squares -> soma dos quadrados dos desvios -> sr
    private double sst;       // total sum of squares -> soma dos quadrados dos desvios totais -> st
    private double xxbar;     // Sxx
    private double yybar;     // Syy
    private double xybar;     // Sxy
    private double xbar;
    private double ybar;
    private double msr;       // regression mean square -> média quadratica dos desvios -> msr
    private double mse;       // mean square error -> média quadratica dos residuos -> mse
    private double f0;        // test statistics -> estatistica do teste -> f0
    private int n;            // residue/diversion number -> numero de desvios/residuos -> n
    double[] y;
    double[] x;

    /**
     * Performs a linear regression on the data points (y[i], x[i]).
     *
     * @param x the values of the predictor variable
     * @param y the corresponding values of the response variable
     * @throws IllegalArgumentException if the lengths of the two arrays are not equal
     */
    public SimpleLinearRegression(double[] x, double[] y) {
        this.y = y;
        this.x = x;

        n = x.length;

        double sumX = 0.0;
        double sumY = 0.0;
        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
        }
        xbar = sumX / n;
        ybar = sumY / n;

        xxbar = 0.0;
        yybar = 0.0;
        xybar = 0.0;
        for (int i = 0; i < n; i++) {
            xxbar += (x[i] - xbar) * (x[i] - xbar);
            yybar += (y[i] - ybar) * (y[i] - ybar);
            xybar += (x[i] - xbar) * (y[i] - ybar);
        }

        slope = xybar / xxbar;
        intercept = ybar - slope * xbar;

        calculateSQ(x, y);

        calculateR();

        calculateMS();
    }

    /**
     * calculates all SQ
     */
    private void calculateSQ(double[] x, double[] y) {
        rss = 0.0;
        ssr = 0.0;
        for (int i = 0; i < n; i++) {
            double fit = slope * x[i] + intercept;
            rss += (fit - y[i]) * (fit - y[i]);
            ssr += (fit - ybar) * (fit - ybar);
        }
        sst = ssr + rss;
    }

    /**
     * calculates all R
     */
    private void calculateR() {
        r2 = ssr / yybar;
        r = Math.sqrt(r2);
    }

    /**
     * calculates all MS
     */
    private void calculateMS() {
        msr = ssr;
        mse = rss / (n - 2);
        f0 = msr / mse;
    }

    /**
     * Returns the expected response y given the value of the predictor
     * variable x.
     *
     * @param x the value of the predictor variable
     * @return the expected response y given the value of the predictor
     * variable x
     */
    public double predict(double x) {
        double predict = (slope * x + intercept);
        if (predict < 0) {
            return 0;
        }
        return predict;
    }

    /**
     * Return the the upper limit of the interval
     *
     * @param x     the value of the predictor variable
     * @param alpha significance level coefficient
     * @return the upper limit of the interval
     */
    public double upperLimit(double x, double alpha) {
        double y = predict(x);
        double s = Math.sqrt(rss / (n - 2));
        double criticalValue = getT(alpha);
        return y + criticalValue * s * Math.sqrt(1.0 / n + (Math.pow((x - xbar), 2) / xxbar));
    }

    /**
     * Return the the lower limit of the interval
     *
     * @param x     the value of the predictor variable
     * @param alpha significance level coefficient
     * @return the lower limit of the interval
     */
    public double lowerLimit(double x, double alpha) {
        double y = predict(x);
        double criticalValue = getT(alpha);
        double s = Math.sqrt(rss / (n - 2));
        return y - criticalValue * s * Math.sqrt(1.0 / n + (Math.pow((x - xbar), 2) / xxbar));
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
     * return the t student value observed (calculate with the slope)
     *
     * @return the t student value observed
     */
    public double getTObs1() {
        double a = 1.0 / (n - 2);
        double b = 0;
        for (int i = 0; i < n; i++) {
            b += Math.pow((y[i] - predict(x[i])), 2);
        }
        double c = Math.sqrt(a * b);

        return (slope / (c / Math.sqrt(xxbar)));
    }

    /**
     * return the t student value observed (calculate with the intercept
     *
     * @return the t student value observed
     */
    public double getTObs2() {
        double a = 1.0 / (n - 2);
        double b = 0;
        for (int i = 0; i < n; i++) {
            b += Math.pow((y[i] - predict(x[i])), 2);
        }
        double c = Math.sqrt(a * b);

        return (intercept / (c * Math.sqrt((1.0 / n) + (Math.pow(xbar, 2) / xxbar))));
    }

    /**
     * return the t student distribution value
     *
     * @param alpha significance level coefficient
     * @return the t student distributionvalue
     */
    public double getT(double alpha) {
        TDistribution td = new TDistribution(n - 2);
        return td.inverseCumulativeProbability(1 - (alpha / 2));
    }

    /**
     * return the fisher–snedecor distribution value
     *
     * @param alpha significance level coefficient
     * @return the Fisher–snedecor distribution value
     */
    public double getF(double alpha) {
        FDistribution fd = new FDistribution(1, n - 2);
        return fd.inverseCumulativeProbability(1 - alpha);
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
     * return the residual sum of squares
     *
     * @return the residual sum of squares
     */
    public double getRss() {
        return rss;
    }

    /**
     * return the regression sum of squares
     *
     * @return the regression sum of squares
     */
    public double getSsr() {
        return ssr;
    }

    /**
     *
     */

    /**
     * return the mean square error
     *
     * @return the mean square error
     */
    public double getMse() {
        return mse;
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
        sb.append("^y").append(String.format("%.2f x + %.2f", slope, intercept));
        return sb.toString();
    }
}