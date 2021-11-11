package app.ui.gui.employee.coordinator;

import app.controller.App;
import app.controller.PerformanceReportController;
import app.domain.model.performance.PerformanceData;
import app.domain.model.performance.PerformanceReport;
import app.domain.model.stores.PerformanceReportStore;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class GraphPerformanceReportUI implements Initializable {

    @FXML
    private CategoryAxis x;
    @FXML
    private CategoryAxis y;
    @FXML
    private LineChart<String, Integer> lineChart;
    @FXML
    private TextArea resultText;

    private PerformanceReportController prc = new PerformanceReportController();
    private PerformanceReportStore performanceReportStore = prc.getList();
    private PerformanceReport performanceReport = performanceReportStore.getLastPerformanceReport();

    private final SceneController sceneController = new SceneController();
    private final PopUpMessages popUpMessages = new PopUpMessages();

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
        XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
        XYChart.Series<String, Integer> series3 = new XYChart.Series<>();
        series1.setName("Tests");
        series2.setName("Results");
        series3.setName("Diagnostics");
        Integer period = 0;
        int startLessPeriod = 0;
        int endLessPeriod = 0;
        for (PerformanceData performanceData : performanceReport.getBasicPeriodicDataList()) {
            period++;
            if (performanceReport.getDateLessEffectiveStart().isEqual(performanceData.getDateTime())) {
                startLessPeriod = period;
            }
            if (performanceReport.getDateLessEffectiveEnd().isEqual(performanceData.getDateTime())) {
                endLessPeriod = period;
            }
            String periodString = period.toString();
            series1.getData().add(new XYChart.Data<>(periodString, performanceData.getNewTests()));
            series2.getData().add(new XYChart.Data<>(periodString, performanceData.getNewResults()));
            series3.getData().add(new XYChart.Data<>(periodString, performanceData.getNewDiagnostics()));
        }
        resultText.setText("Less Effective period\nFrom " + performanceReport.getDateLessEffectiveStart().format(dtf) + " Start period: " + startLessPeriod + "\nTo " + performanceReport.getDateLessEffectiveEnd().format(dtf) + " End period: " + endLessPeriod);
        lineChart.getData().addAll(series1, series2, series3);
    }

    public void returnBtn(ActionEvent event) {
        try {
            sceneController.switchToScene(event, Constants.PERFORMANCE_REPORT_UI_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }

        App app = App.getInstance();
        app.doLogout();
    }

}
