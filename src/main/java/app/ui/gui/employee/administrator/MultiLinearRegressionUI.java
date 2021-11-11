package app.ui.gui.employee.administrator;

import app.controller.App;
import app.controller.LinearRegressionController;
import app.domain.model.Company;
import app.domain.model.Data;
import app.domain.model.MultiLinearRegression;
import app.domain.model.client.Client;
import app.domain.model.stores.TestStore;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class MultiLinearRegressionUI implements Initializable {

    @FXML
    private ListView<String> resultArea;

    private SceneController sceneController = new SceneController();
    private PopUpMessages popUpMessages = new PopUpMessages();

    private Company company = App.getInstance().getCompany();
    private Data data = company.getData();
    private TestStore testStore = company.getTestStore();

    private final String twoCases = "%.02f";
    private final String fourCases = "%.04f";

    private double[] agesInsideTheHistoricalDays;
    private double[] agesInsideTheDateInterval;
    private double[] covidTestsPerDayInsideTheHistoricalDays;
    private double[] covidTestsPerDayInsideTheIntervalOfDates;
    private double[] positiveCovidTestsPerDayInsideTheHistoricalInterval;
    private double[] positiveCovidTestsPerDayInsideTheDateInterval;
    private List<Client> clientsWithTests;
    private LinearRegressionController lrc = new LinearRegressionController();


    private double significanceLevelCoefficient = data.getConfidenceLevelIC();
    private double significanceLevelAnova = data.getSignificanceLevelAnova();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        multiRegression();

    }


    public void multiRegression() {
        double[] v3 = lrc.getPositiveCovidTestsPerDayHistoric();

        double[] covidTestsPerDayInsideTheDateInterval = lrc.getCovidTestsPerDayInterval();
        double[] agesInsideTheDateInterval = lrc.getAges();
        double[][] matrix = new double[lrc.getCovidTestsPerDayInterval().length][2];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = covidTestsPerDayInsideTheDateInterval[i];
            matrix[i][1] = agesInsideTheDateInterval[i];
        }

        MultiLinearRegression multiLinearRegression = new MultiLinearRegression(lrc.getPositiveCovidTestsPerDayInterval(), matrix);

        multiOutputLinear(multiLinearRegression);
        multiOutputOtherStatistics(multiLinearRegression);
        multiOutputHypothesis(multiLinearRegression);
        multiOutputAnova(multiLinearRegression);
        multiOutputPrediction(multiLinearRegression, v3, matrix);

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

    private void multiOutputLinear(MultiLinearRegression multiLinearRegression) {

        resultArea.getItems().clear();
        resultArea.getItems().add("Multi Linear");
        resultArea.getItems().add("The regression model fitted using data from the interval");
        resultArea.getItems().add(multiLinearRegression.toString());
        resultArea.getItems().add("");

    }

    private void multiOutputOtherStatistics(MultiLinearRegression multiLinearRegression) {
        double r2 = multiLinearRegression.getR2();
        double r = multiLinearRegression.getR();
        double r2adjusted = multiLinearRegression.getR2adjusted();

        resultArea.getItems().add("Other statistics");
        resultArea.getItems().add("R2 = " + String.format(fourCases, r2));
        resultArea.getItems().add("R2adjusted = " + String.format(fourCases, r2adjusted));
        resultArea.getItems().add("R = " + String.format(fourCases, r));
        resultArea.getItems().add("");

    }

    private void multiOutputHypothesis(MultiLinearRegression multiLinearRegression) {
        double tObs = multiLinearRegression.getTObs(0);
        double t = multiLinearRegression.getT(significanceLevelCoefficient);

        resultArea.getItems().add("Hypothesis tests for regression coefficients");
        resultArea.getItems().add("H0: betta0=0   H1: betta0!=0");
        resultArea.getItems().add("T = " + String.format(fourCases, t));
        resultArea.getItems().add("T_obs = " + String.format(fourCases, tObs));
        if (t > tObs) {
            resultArea.getItems().add("Decision: No reject H0");
        } else {
            resultArea.getItems().add("Decision: Reject H0");
        }

        tObs = multiLinearRegression.getTObs(1);

        resultArea.getItems().add("");
        resultArea.getItems().add("H0: betta1=0   H1: betta1!=0 ");
        resultArea.getItems().add("T = " + String.format(fourCases, t));
        resultArea.getItems().add("T_obs = " + String.format(fourCases, tObs));
        if (t > tObs) {
            resultArea.getItems().add("Decision: No reject H0");
        } else {
            resultArea.getItems().add("Decision: Reject H0");
        }

        tObs = multiLinearRegression.getTObs(2);

        resultArea.getItems().add("");
        resultArea.getItems().add("H0: betta2=0   H1: betta2!=0 ");
        resultArea.getItems().add("T = " + String.format(fourCases, t));
        resultArea.getItems().add("T_obs = " + String.format(fourCases, tObs));
        if (t > tObs) {
            resultArea.getItems().add("Decision: No reject H0");
        } else {
            resultArea.getItems().add("Decision: Reject H0");
        }
        resultArea.getItems().add("");
    }

    private void multiOutputAnova(MultiLinearRegression multiLinearRegression) {
        int n = multiLinearRegression.getN();
        double rss = multiLinearRegression.getSqe();
        double ssr = multiLinearRegression.getSqr();
        double mse = multiLinearRegression.getMqe();
        resultArea.getItems().add("Significance model with Anova");
        resultArea.getItems().add("H0: b=0  H1:b<>0 ");
        resultArea.getItems().add("                         df              SS            MS             F");
        resultArea.getItems().add("Regression          2           " + String.format(fourCases, ssr) + "      " + String.format(fourCases, ssr / 2) + "      " + String.format(fourCases, (ssr / 2) / mse));
        resultArea.getItems().add("Residual             " + (n - 3) + "           " + String.format(fourCases, rss) + "      " + String.format(fourCases, mse));
        resultArea.getItems().add("Total                  " + (n - 1) + "           " + (rss + ssr));
        resultArea.getItems().add("");
        double f0 = multiLinearRegression.getF0();
        double f = multiLinearRegression.getF(significanceLevelAnova);


        resultArea.getItems().add(String.format(fourCases, ((ssr / 2) / mse)) + " > f" + significanceLevelAnova + ",(2," + (n - 2) + ")=" + String.format(fourCases, f));

        if (f > f0) {
            resultArea.getItems().add("Decision: No reject H0");
        } else {
            resultArea.getItems().add("Decision: Reject H0");
        }
        resultArea.getItems().add("");

    }

    private void multiOutputPrediction(MultiLinearRegression multiLinearRegression, double[] positiveCovidTestsPerDayHistoric, double[][] x) {
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
                resultArea.getItems().add(String.format("%02d", calendar.get(Calendar.DATE)) + "/" + String.format("%02d", (calendar.get(Calendar.MONTH) + 1)) + "/" + calendar.get(Calendar.YEAR) + "                                  " + positiveCovidTestsPerDayHistoric[a] + "                                                                            " + String.format(twoCases, multiLinearRegression.predict(x[a])) + "                                                   " + String.format(twoCases, multiLinearRegression.lowerLimit(x[a], significanceLevelCoefficient)) + "->" + String.format(twoCases, multiLinearRegression.upperLimit(x[a], significanceLevelCoefficient)));
                a++;
            } else {
                c--;
            }
        }
    }
}
