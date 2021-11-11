package app.controller;

import app.controller.App;
import app.controller.PropertiesController;
import app.controller.LinearRegressionController;
import app.domain.model.Data;
import app.domain.model.MultiLinearRegression;
import app.domain.model.SimpleLinearRegression;
import app.domain.model.adpaters.NHSAdapter;
import app.domain.shared.Constants;

import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Class responsible for creating and sending the nhs report
 *
 * @author Ricardo Faria 1201405
 */
public class ReportTaskController extends TimerTask {

    private Properties proper = PropertiesController.getProperties();
    private Data data;
    private double significanceLevelCoefficient = Double.parseDouble(proper.getProperty("significanceLevel.COEFFICIENT"));
    private double significanceLevelEstimated = Double.parseDouble(proper.getProperty("significanceLevel.ESTIMATED"));
    private double significanceLevelAnova = Double.parseDouble(proper.getProperty("significanceLevel.ANOVA"));

    private final String twoCases = "%.02f";
    private final String fourCases = "%.04f";

    /**
     * Empty constructor
     */
    public ReportTaskController() {
        //empty
    }

    /**
     * Method responsible for calling output methods.
     * Is also responsible for sending the nhs report and writing the logger.
     */
    @Override
    public void run() {
        data = App.getInstance().getCompany().getData();
        setInformation();

        LinearRegressionController lrc = new LinearRegressionController();

        double[] v3 = lrc.getPositiveCovidTestsPerDayHistoric();
        double[] v4 = lrc.getCovidTestsPerDayHistoric();

        if (proper.getProperty("regression.MODEL").equalsIgnoreCase("linear")) {
            double[] v0 = lrc.getAges();
            double[] v1 = lrc.getPositiveCovidTestsPerDayInterval();
            double[] v2 = lrc.getCovidTestsPerDayInterval();

            SimpleLinearRegression simpleLinearRegressionAge = new SimpleLinearRegression(v0, v1);
            SimpleLinearRegression simpleLinearRegressionPositive = new SimpleLinearRegression(v2, v1);

            StringBuilder sb = new StringBuilder();

            if (simpleLinearRegressionPositive.getR2() > simpleLinearRegressionAge.getR2()) {
                sb.append("Positive Covid Tests chosen\n");
                sb.append(simpleOutputLinear(simpleLinearRegressionPositive));
                sb.append(simpleOutputOtherStatistics(simpleLinearRegressionPositive));
                sb.append(simpleOutputHypothesis(simpleLinearRegressionPositive));
                sb.append(simpleOutputAnova(simpleLinearRegressionPositive));
                sb.append(simpleOutputPrediction(simpleLinearRegressionPositive, v3, v4));
            } else {
                sb.append("Mean Age chosen\n");
                sb.append(simpleOutputLinear(simpleLinearRegressionAge));
                sb.append(simpleOutputOtherStatistics(simpleLinearRegressionAge));
                sb.append(simpleOutputHypothesis(simpleLinearRegressionAge));
                sb.append(simpleOutputAnova(simpleLinearRegressionAge));
                sb.append(simpleOutputPrediction(simpleLinearRegressionAge, v3, v4));
            }

            NHSAdapter nhsAdapter = new NHSAdapter();
            nhsAdapter.write(sb.toString());
            writeLog();

        } else if (proper.getProperty("regression.MODEL").equalsIgnoreCase("multi")) {
            double[] covidTestsPerDayInsideTheDateInterval = lrc.getCovidTestsPerDayInterval();
            double[] agesInsideTheDateInterval = lrc.getAges();
            double[][] matrix = new double[lrc.getCovidTestsPerDayInterval().length][2];

            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = covidTestsPerDayInsideTheDateInterval[i];
                matrix[i][1] = agesInsideTheDateInterval[i];
            }

            MultiLinearRegression multiLinearRegression = new MultiLinearRegression(lrc.getPositiveCovidTestsPerDayInterval(), matrix);

            StringBuilder sb = new StringBuilder();
            sb.append(multiOutputLinear(multiLinearRegression));
            sb.append(multiOutputOtherStatistics(multiLinearRegression));
            sb.append(multiOutputHypothesis(multiLinearRegression));
            sb.append(multiOutputAnova(multiLinearRegression));
            sb.append(multiOutputPrediction(multiLinearRegression, v3, matrix));

            NHSAdapter nhsAdapter = new NHSAdapter();
            nhsAdapter.write(sb.toString());
            writeLog();
        }
    }

    /**
     * Responsible method to go to the properties file to get the necessary information (startDayStr, endDayStr, startDay, endDay, historicalDays)
     */
    private void setInformation() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String startDayStr = proper.getProperty("start.DAY");
        String endDayStr = proper.getProperty("end.DAY");
        LocalDate startDay = LocalDate.parse(startDayStr, dateTimeFormatter);
        LocalDate endDay = LocalDate.parse(endDayStr, dateTimeFormatter);
        String historicalDays = proper.getProperty("historical.DAY");

        data.setInformation(historicalDays, String.valueOf(significanceLevelCoefficient), startDay, endDay, String.valueOf(significanceLevelAnova));
    }

    /**
     * Create a string with the regression model
     *
     * @param simpleLinearRegression simple linear regression
     * @return regression model string
     */
    public String simpleOutputLinear(SimpleLinearRegression simpleLinearRegression) {
        StringBuilder sb = new StringBuilder();

        sb.append("Simple Linear");
        sb.append("\n");
        sb.append("The regression model fitted using data from the interval\n");
        sb.append(simpleLinearRegression).append("\n");
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Create a string with the other statistics
     *
     * @param simpleLinearRegression simple linear regression
     * @return other statistics string
     */
    public String simpleOutputOtherStatistics(SimpleLinearRegression simpleLinearRegression) {
        StringBuilder sb = new StringBuilder();
        double r2 = simpleLinearRegression.getR2();
        double r = simpleLinearRegression.getR();

        sb.append("Other statistics").append("\n");
        sb.append("R2 = ").append(String.format(fourCases, r2)).append("\n");
        sb.append("R = ").append(String.format(fourCases, r)).append("\n");
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Create a string with the hypothesis tests
     *
     * @param simpleLinearRegression simple linear regression
     * @return hypothesis tests string
     */
    public String simpleOutputHypothesis(SimpleLinearRegression simpleLinearRegression) {
        StringBuilder sb = new StringBuilder();

        double t = simpleLinearRegression.getT(significanceLevelCoefficient);
        double tObs1 = simpleLinearRegression.getTObs1();
        sb.append("Hypothesis tests for regression coefficients\n");
        sb.append("HO:b=0 (a=0) H1: b<>0 (a<>0)\n");
        sb.append("T = ").append(String.format(fourCases, t)).append("\n");
        sb.append("T_obs = ").append(String.format(fourCases, tObs1)).append("\n");
        sb.append("Decision: ");
        if (t > tObs1) {
            sb.append("No reject H0\n");
        } else {
            sb.append("Reject H0\n");
        }

        double tObs2 = simpleLinearRegression.getTObs2();
        sb.append("\n");
        sb.append("HO:a=0 H1: a<>0\n");
        sb.append("T = ").append(String.format(fourCases, t)).append("\n");
        sb.append("T_obs = ").append(String.format(fourCases, tObs2)).append("\n");
        sb.append("Decision: ");
        if (t > tObs2) {
            sb.append("No reject H0\n");
        } else {
            sb.append("Reject H0\n");
        }
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Create a string with the anova
     *
     * @param simpleLinearRegression simple linear regression
     * @return anova string
     */
    public String simpleOutputAnova(SimpleLinearRegression simpleLinearRegression) {
        StringBuilder sb = new StringBuilder();

        double n = simpleLinearRegression.getN();
        double rss = simpleLinearRegression.getRss();
        double ssr = simpleLinearRegression.getSsr();
        double mse = simpleLinearRegression.getMse();
        sb.append("Significance model with Anova\n");
        sb.append("H0: b=0  H1:b<>0 \n");
        sb.append("                    df             SS           MS           F\n");
        sb.append("Regression          1           ").append(String.format(fourCases, ssr)).append("      ").append(String.format(fourCases, ssr)).append("      ").append(String.format(fourCases, ssr / mse)).append("\n");
        sb.append("Residual            ").append(n - 2).append("           ").append(String.format(fourCases, rss)).append("      ").append(String.format(fourCases, (rss / (simpleLinearRegression.getN() - 2)))).append("\n");
        sb.append("Total               ").append(n - 1).append("           ").append(String.format(fourCases, rss + ssr)).append("\n");

        double f0 = simpleLinearRegression.getF0();
        double f = simpleLinearRegression.getF(significanceLevelAnova);
        sb.append("\n");
        sb.append(String.format(fourCases, ssr / mse)).append(" > f").append(significanceLevelAnova).append(",(1,").append(n-2).append(")=").append(String.format(fourCases, f)).append("\n");
        sb.append("Decision: ");
        if (f > f0) {
            sb.append("No reject H0\n");
        } else {
            sb.append("Reject H0\n");
        }
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Create a string with the predictions
     *
     * @param simpleLinearRegression simple linear regression
     * @return predictions string
     */
    public String simpleOutputPrediction(SimpleLinearRegression simpleLinearRegression, double[] positiveCovidTestsPerDayHistoric, double[] covidTests) {
        StringBuilder sb = new StringBuilder();

        int historicalDate = data.getHistoricalDaysInt();
        int rows = historicalDate + 1;

        Calendar calendar = Calendar.getInstance();
        Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        calendar.setTime(date);

        sb.append("Prediction values\n");
        sb.append("Date                    Number of OBSERVED positive cases                    Number of ESTIMATED positive cases                    " + (100 - data.getConfidenceLevelIC()) + ("% intervals\n"));

        int a = 0;
        for (int c = 1; c < rows; c++) {
            calendar.add(Calendar.DATE, -1);
            if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                sb.append(String.format("%02d", calendar.get(Calendar.DATE)) + "/" + String.format("%02d", (calendar.get(Calendar.MONTH) + 1)) + "/" + calendar.get(Calendar.YEAR) + "                            ");
                sb.append(positiveCovidTestsPerDayHistoric[a] + "                                                    ");
                sb.append(String.format(twoCases, simpleLinearRegression.predict(covidTests[a])));
                sb.append("                                      ");
                sb.append(String.format(twoCases, simpleLinearRegression.lowerLimit(covidTests[a], significanceLevelCoefficient))).append(" -> ").append(String.format(twoCases, simpleLinearRegression.upperLimit(covidTests[a], significanceLevelCoefficient))).append("\n");
                a++;
            } else {
                c--;
            }
        }

        return sb.toString();
    }

    /**
     * Create a string with the regression model
     *
     * @param multiLinearRegression multi linear regression
     * @return regression model string
     */
    public String multiOutputLinear(MultiLinearRegression multiLinearRegression) {
        StringBuilder sb = new StringBuilder();

        sb.append("Multi Linear");
        sb.append("\n");
        sb.append("The regression model fitted using data from the interval\n");
        sb.append(multiLinearRegression).append("\n");
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Create a string with the other statistics
     *
     * @param multiLinearRegression multi linear regression
     * @return other statistics model string
     */
    public String multiOutputOtherStatistics(MultiLinearRegression multiLinearRegression) {
        StringBuilder sb = new StringBuilder();
        double r2 = multiLinearRegression.getR2();
        double r = multiLinearRegression.getR();
        double r2adjusted = multiLinearRegression.getR2adjusted();

        sb.append("Other statistics").append("\n");
        sb.append("R2 = ").append(String.format(fourCases, r2)).append("\n");
        sb.append("R2adjusted = ").append(String.format(fourCases, r2adjusted)).append("\n");
        sb.append("R = ").append(String.format(fourCases, r)).append("\n");
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Create a string with the hypothesis tests
     *
     * @param multiLinearRegression multi linear regression
     * @return hypothesis tests string
     */
    public String multiOutputHypothesis(MultiLinearRegression multiLinearRegression) {
        StringBuilder sb = new StringBuilder();
        double tObs = multiLinearRegression.getTObs(0);
        double t = multiLinearRegression.getT(significanceLevelCoefficient);

        sb.append("Hypothesis tests for regression coefficients\n");
        sb.append("H0: betta0=0   H1: betta0!=0\n");
        sb.append("T = ").append(String.format(fourCases, t)).append("\n");
        sb.append("T_obs = ").append(String.format(fourCases, tObs)).append("\n");
        sb.append("Decision: ");
        if (t > tObs) {
            sb.append("No reject H0\n");
        } else {
            sb.append("Reject H0\n");
        }

        tObs = multiLinearRegression.getTObs(1);
        sb.append("\n");
        sb.append("H0: betta1=0   H1: betta1!=0 \n");
        sb.append("T = ").append(String.format(fourCases, t)).append("\n");
        sb.append("T_obs = ").append(String.format(fourCases, tObs)).append("\n");
        sb.append("Decision: ");
        if (t > tObs) {
            sb.append("No reject H0\n");
        } else {
            sb.append("Reject H0\n");
        }

        tObs = multiLinearRegression.getTObs(2);
        sb.append("\n");
        sb.append("H0: betta2=0   H1: betta2!=0 \n");
        sb.append("T = ").append(String.format(fourCases, t)).append("\n");
        sb.append("T_obs = ").append(String.format(fourCases, tObs)).append("\n");
        sb.append("Decision: ");
        if (t > tObs) {
            sb.append("No reject H0\n");
        } else {
            sb.append("Reject H0\n");
        }
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Create a string with the anova
     *
     * @param multiLinearRegression multi linear regression
     * @return anova string
     */
    public String multiOutputAnova(MultiLinearRegression multiLinearRegression) {
        StringBuilder sb = new StringBuilder();

        int n = multiLinearRegression.getN();
        double rss = multiLinearRegression.getSqe();
        double ssr = multiLinearRegression.getSqr();
        double mse = multiLinearRegression.getMqe();
        sb.append("Significance model with Anova\n");
        sb.append("H0: b=0  H1:b<>0 \n");
        sb.append("                    df             SS           MS           F\n");
        sb.append("Regression          2           ").append(String.format(fourCases, ssr)).append("      ").append(String.format(fourCases, ssr / 2)).append("      ").append(String.format(fourCases, (ssr / 2) / mse)).append("\n");
        sb.append("Residual            ").append(n - 3).append("           ").append(String.format(fourCases, rss)).append("      ").append(String.format(fourCases, mse)).append("\n");
        sb.append("Total               ").append(n - 1).append("           ").append(rss + ssr).append("\n");

        double f0 = multiLinearRegression.getF0();
        double f = multiLinearRegression.getF(significanceLevelAnova);
        sb.append("\n");
        sb.append(String.format(fourCases, ((ssr / 2) / mse))).append(" > f").append(significanceLevelAnova).append(",(2,").append(n-2).append(")=").append(String.format(fourCases, f)).append("\n");
        sb.append("Decision: ");
        if (f > f0) {
            sb.append("No reject H0\n");
        } else {
            sb.append("Reject H0\n");
        }
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Create a string with the predictions
     *
     * @param multiLinearRegression multi linear regression
     * @return predictions string
     */
    public String multiOutputPrediction(MultiLinearRegression multiLinearRegression, double[] positiveCovidTestsPerDayHistoric, double[][] x) {
        StringBuilder sb = new StringBuilder();

        int historicalDate = data.getHistoricalDaysInt();
        int rows = historicalDate + 1;

        Calendar calendar = Calendar.getInstance();
        Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        calendar.setTime(date);

        sb.append("Prediction values\n");
        sb.append("Date                    Number of OBSERVED positive cases                    Number of ESTIMATED positive cases                    " + (100 - data.getConfidenceLevelIC()) + ("% intervals\n"));

        int a = 0;
        int b = x.length - 1;
        for (int c = 1; c < rows; c++) {
            calendar.add(Calendar.DATE, -1);
            if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                sb.append(String.format("%02d", calendar.get(Calendar.DATE)) + "/" + String.format("%02d", (calendar.get(Calendar.MONTH) + 1)) + "/" + calendar.get(Calendar.YEAR) + "                            ");
                sb.append(positiveCovidTestsPerDayHistoric[a] + "                                                    ");
                sb.append(String.format(twoCases, multiLinearRegression.predict(x[b])));
                sb.append("                                      ");
                sb.append(String.format(twoCases, multiLinearRegression.lowerLimit(x[b], significanceLevelCoefficient))).append("-").append(String.format(twoCases, multiLinearRegression.upperLimit(x[b], significanceLevelCoefficient))).append("\n");
                a++;
                b--;
            } else {
                c--;
            }
        }
        return sb.toString();
    }

    /**
     * Method responsible for writing the logger
     */
    private void writeLog() {
        Logger logger = Logger.getLogger("Logger");
        FileHandler fh;

        try {
            File file1 = new File(Constants.LOG_PATH);
            file1.mkdir();
            fh = new FileHandler(Constants.LOGFILE_PATH, true);
            logger.addHandler(fh);
            SimpleFormatter formatter;
            formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.setUseParentHandlers(false);
            logger.info("Report sent to NHS");
        } catch (Exception error) {
            //empty
        }
    }
}
