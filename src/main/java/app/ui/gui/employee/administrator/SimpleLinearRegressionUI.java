package app.ui.gui.employee.administrator;

import app.controller.App;
import app.controller.PropertiesController;
import app.controller.LinearRegressionController;
import app.domain.model.Company;
import app.domain.model.Data;
import app.domain.model.SimpleLinearRegression;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class SimpleLinearRegressionUI implements Initializable {

    @FXML
    private ComboBox<String> variableCbb;
    @FXML
    private ListView<String> resultArea;

    private SceneController sceneController = new SceneController();
    private PopUpMessages popUpMessages = new PopUpMessages();

    private Company company = App.getInstance().getCompany();

    private Data data = company.getData();
    private Properties proper = PropertiesController.getProperties();

    private final String twoCases = "%.02f";
    private final String fourCases = "%.04f";

    //private SimpleLinearRegression simpleLinearRegression;

    private double[] positiveCovidTestsPerDayHistoric;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        variableCbb.getItems().add("Covid-19 tests");
        variableCbb.getItems().add("Mean age");
    }

    public void returnBtn(ActionEvent event) {
        try {
            sceneController.switchToScene(event, Constants.ADMIN_UI_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }

        App app = App.getInstance();
        app.doLogout();
    }

    public void confirm(ActionEvent event) {
        LinearRegressionController lrc = new LinearRegressionController();
        if (variableCbb.getValue() == null) {
            popUpMessages.errorMessage(event, "System Error\n\nVariable cannot be blank");
        } else {
            if (variableCbb.getValue().equals("Covid-19 tests")) {
                linearRegressionCovidTests(lrc);
            } else if (variableCbb.getValue().equals("Mean age")) {
                linearRegressionMeanAge(lrc);
            }
        }
    }

    private void linearRegressionCovidTests(LinearRegressionController lrc) {
        double[] v1 = lrc.getPositiveCovidTestsPerDayInterval();
        double[] v2 = lrc.getCovidTestsPerDayInterval();

        positiveCovidTestsPerDayHistoric = lrc.getPositiveCovidTestsPerDayHistoric();

        SimpleLinearRegression simpleLinearRegressionCovid = new SimpleLinearRegression(v2, v1);

        resultArea.getItems().clear();
        outputLinear(simpleLinearRegressionCovid);
        outputOtherStatistics(simpleLinearRegressionCovid);
        outputHypothesis(simpleLinearRegressionCovid);
        outputAnova(simpleLinearRegressionCovid);
        outputPrediction(simpleLinearRegressionCovid);

    }

    private void linearRegressionMeanAge(LinearRegressionController lrc) {
        double[] v0 = lrc.getAges();
        double[] v1 = lrc.getPositiveCovidTestsPerDayInterval();

        positiveCovidTestsPerDayHistoric = lrc.getPositiveCovidTestsPerDayHistoric();

        SimpleLinearRegression simpleLinearRegressionAge = new SimpleLinearRegression(v0, v1);

        resultArea.getItems().clear();
        outputLinear(simpleLinearRegressionAge);
        outputOtherStatistics(simpleLinearRegressionAge);
        outputHypothesis(simpleLinearRegressionAge);
        outputAnova(simpleLinearRegressionAge);
        outputPrediction(simpleLinearRegressionAge);
    }


    private void outputLinear(SimpleLinearRegression simpleLinearRegression) {
        resultArea.getItems().add("Linear");
        resultArea.getItems().add("The regression model fitted using data from the interval");

        resultArea.getItems().add(simpleLinearRegression.toString());
        resultArea.getItems().add("");
    }

    private void outputOtherStatistics(SimpleLinearRegression simpleLinearRegression) {
        double r2 = simpleLinearRegression.getR2();
        double r = simpleLinearRegression.getR();


        resultArea.getItems().add("Other statistics");
        resultArea.getItems().add("R2 = " + String.format(fourCases, r2));
        resultArea.getItems().add("R = " + String.format(fourCases, r));
        resultArea.getItems().add("");
    }

    private void outputHypothesis(SimpleLinearRegression simpleLinearRegression) {
        double t = simpleLinearRegression.getT(1 - data.getConfidenceLevelIC());
        double tObs1 = simpleLinearRegression.getTObs1();
        resultArea.getItems().add("Hypothesis tests for regression coefficients");
        resultArea.getItems().add("HO:b=0 (a=0) H1: b<>0 (a<>0)");
        resultArea.getItems().add("T = " + String.format(fourCases, t));
        resultArea.getItems().add("T_obs = " + String.format(fourCases, tObs1));
        if (t < tObs1) {
            resultArea.getItems().add("Decision: No reject H0");
        } else {
            resultArea.getItems().add("Decision: Reject H0");
        }
        resultArea.getItems().add("");
        double tObs2 = simpleLinearRegression.getTObs2();
        resultArea.getItems().add("HO:a=0 H1: a<>0");
        resultArea.getItems().add("T = " + String.format(fourCases, t));
        resultArea.getItems().add("T_obs = " + String.format(fourCases, tObs2));

        if (t < tObs2) {
            resultArea.getItems().add("Decision: No reject H0");
        } else {
            resultArea.getItems().add("Decision: Reject H0");
        }
        resultArea.getItems().add("");
    }

    private void outputAnova(SimpleLinearRegression simpleLinearRegression) {

        double n = simpleLinearRegression.getN();
        double rss = simpleLinearRegression.getRss();
        double ssr = simpleLinearRegression.getSsr();
        double mse = simpleLinearRegression.getMse();
        resultArea.getItems().add("Significance model with Anova");
        resultArea.getItems().add("H0: b=0  H1:b<>0");
        resultArea.getItems().add("                        df             SS           MS           F");
        resultArea.getItems().add("Regression          1           " + String.format(fourCases, ssr) + "      " + String.format(fourCases, ssr) + "      " + String.format(fourCases, ssr / mse));
        resultArea.getItems().add("Residual             " + (n - 2) + "           " + String.format(fourCases, rss) + "       " + String.format(fourCases, (rss / (simpleLinearRegression.getN() - 2))));
        resultArea.getItems().add("Total                 " + (n - 1) + "           " + String.format(fourCases, rss + ssr));
        resultArea.getItems().add("");

        double f0 = simpleLinearRegression.getF0();
        double f = simpleLinearRegression.getF(data.getSignificanceLevelAnova());

        resultArea.getItems().add(String.format(fourCases, ssr / mse) + " > f" + data.getSignificanceLevelAnova() + ",(1," + (n - 2) + ")=" + String.format(fourCases, f));

        if (f > f0) {
            resultArea.getItems().add("Decision: No reject H0");
        } else {
            resultArea.getItems().add("Decision: Reject H0");
        }

        resultArea.getItems().add("");

    }

    private void outputPrediction(SimpleLinearRegression simpleLinearRegression) {

        int historicalDate = data.getHistoricalDaysInt();
        int rows = historicalDate + 1;

        Calendar calendar = Calendar.getInstance();
        Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        calendar.setTime(date);
        resultArea.getItems().add("Prediction values");
        resultArea.getItems().add("Date                    Number of OBSERVED positive cases                    Number of ESTIMATED positive cases                    " + (100 - data.getConfidenceLevelIC()) + ("% intervals"));

        int a = 0;
        for (int c = 1; c < rows; c++) {
            calendar.add(Calendar.DATE, -1);
            if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                resultArea.getItems().add(String.format("%02d", calendar.get(Calendar.DATE)) + "/" + String.format("%02d", (calendar.get(Calendar.MONTH) + 1)) + "/" + calendar.get(Calendar.YEAR) + "                                    " + positiveCovidTestsPerDayHistoric[a] + "                                                                              " + String.format("%.02f", simpleLinearRegression.predict(positiveCovidTestsPerDayHistoric[a])) + "                                                   " + String.format("%.02f", simpleLinearRegression.lowerLimit(positiveCovidTestsPerDayHistoric[a], data.getConfidenceLevelIC())) + " -> " + String.format("%.02f", simpleLinearRegression.upperLimit(positiveCovidTestsPerDayHistoric[a], data.getConfidenceLevelIC())));
                a++;
            } else {
                c--;
            }
        }
    }
}
