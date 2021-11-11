package app.ui.gui.employee.coordinator;

import app.controller.App;
import app.controller.PerformanceReportController;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class PerformanceReportUI implements Initializable {

    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private ComboBox<String> algorithmChoice;
    @FXML
    private TextArea resultTextArea;

    private PerformanceReportController prc = new PerformanceReportController();

    private final SceneController sceneController = new SceneController();
    private final PopUpMessages popUpMessages = new PopUpMessages();

    private final LocalDate todayDate = LocalDate.now();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        algorithmChoice.getItems().add("Brute-force algorithm");
        algorithmChoice.getItems().add("Benchmark ISEP algorithm");
    }

    public void returnBtn(ActionEvent event) {
        try {
            sceneController.switchToScene(event, Constants.COORDINATOR_UI_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }

        App app = App.getInstance();
        app.doLogout();
    }

    public void confirm(ActionEvent event) {
        boolean check = true;

        if (startDatePicker.getValue() == null) {
            popUpMessages.errorMessage(event, "System Error\n\nStart day not set");
            check = false;
        }

        if (endDatePicker.getValue() == null) {
            popUpMessages.errorMessage(event, "System Error\n\nEnd day not set");
            check = false;
        }

        if (Period.between(startDatePicker.getValue(), endDatePicker.getValue()).isNegative()) {
            popUpMessages.errorMessage(event, "System Error\n\nInterval of dates invalid");
            check = false;
        }


        if (Period.between(endDatePicker.getValue(), todayDate).getDays() < 0) {
            popUpMessages.errorMessage(event, "System Error\n\nInvalid day\nFinal day passed the current day");
            check = false;
        }

        if (algorithmChoice.getValue() == null) {
            popUpMessages.errorMessage(event, "System Error\n\nChosen Algorithm - option not set");
            check = false;
        }

        setInformation(event);
        changeWindow(event, check);
    }

    private void setInformation(ActionEvent event) {
        try {
            prc.savePerformanceReport();
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System error\n\n" + error.getMessage());
        }
    }

    private void changeWindow(ActionEvent event, boolean check) {
        if (check) {
            try {
                sceneController.switchToScene(event, Constants.GRAPH_PERFORMANCE_REPORT_UI_PATH);
            } catch (Exception error) {
                popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
            }
        }
    }

    public void previewBtn(ActionEvent event) {
        try {
            String algo = algorithmChoice.getValue();
            int algoId = 0;
            if (algo.equals("Benchmark ISEP algorithm"))
                algoId = 1;
            prc.createReport(startDatePicker.getValue(), endDatePicker.getValue(), algoId);
            resultTextArea.setText(prc.getDataPerformanceReportSummary());
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System error\n\n" + error.getMessage());
        }
    }

}
