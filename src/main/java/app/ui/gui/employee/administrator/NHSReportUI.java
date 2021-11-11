package app.ui.gui.employee.administrator;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.Data;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class NHSReportUI implements Initializable {

    @FXML
    private TextField historicalDaysTxt;
    @FXML
    private TextField confidenceLevelTxt;
    @FXML
    private TextField significanceLevelAnovaTxt;
    @FXML
    private ComboBox<String> modelCbb;
    @FXML
    private ComboBox<String> typeCbb;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;


    private final SceneController sceneController = new SceneController();
    private final PopUpMessages popUpMessages = new PopUpMessages();

    private final Company company = App.getInstance().getCompany();
    private LocalDate todayDate = LocalDate.now();
    private Data data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = company.getData();
        modelCbb.getItems().add("Simple Linear Regression");
        modelCbb.getItems().add("Multi Linear Regression");

        typeCbb.getItems().add("Daily");
        typeCbb.getItems().add("Weekly");
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
        boolean check = true;

        if (historicalDaysTxt.getText().equals("")) {
            popUpMessages.errorMessage(event, "System Error\n\nHistory Days cannot be blank");
            check = false;
        }

        if (confidenceLevelTxt.getText().equals("")) {
            popUpMessages.errorMessage(event, "System Error\n\nConfidence Level cannot be blank");
            check = false;
        }

        if (significanceLevelAnovaTxt.getText().equals("")) {
            popUpMessages.errorMessage(event, "System Error\n\nSignificance Level Anova cannot be blank");
            check = false;
        }

        if (startDatePicker.getValue() == null) {
            popUpMessages.errorMessage(event, "System Error\n\nStart day not set");
            check = false;
        }

        if (endDatePicker.getValue() == null) {
            popUpMessages.errorMessage(event, "System Error\n\nEnd day not set");
            check = false;
        }


        if (modelCbb.getValue() == null) {
            popUpMessages.errorMessage(event, "System Error\n\nInvalid day\nLinear regression model - option not set");
            check = false;
        }

        if (Period.between(endDatePicker.getValue(), todayDate).getDays() < 0) {
            popUpMessages.errorMessage(event, "System Error\n\nInvalid day\nFinal day passed the current day");
            check = false;
        }

        if (check && setInformation(event)) {
            changeWindow(event);
        }
    }

    private boolean setInformation(ActionEvent event) {
        try {
            double confidenceLevelTxtint = Double.parseDouble(confidenceLevelTxt.getText()) / 100;
            double confidenceLevelAnovaTxtint = Double.parseDouble(significanceLevelAnovaTxt.getText()) / 100;
            data.setInformation(historicalDaysTxt.getText(), String.valueOf(confidenceLevelTxtint), startDatePicker.getValue(), endDatePicker.getValue(), String.valueOf(confidenceLevelAnovaTxtint));
            return true;
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System error\n\n" + error.getMessage());
            return false;
        }
    }


    private void changeWindow(ActionEvent event) {
        try {
            if (modelCbb.getValue().equals("Simple Linear Regression")) {
                sceneController.switchToScene(event, Constants.SIMPLE_LINEAR_REGRESSION_PATH);
            } else {
                if (modelCbb.getValue().equals("Multi Linear Regression")) {
                    sceneController.switchToScene(event, Constants.MULTI_LINEAR_REGRESSION_PATH);
                } else {
                    sceneController.switchToScene(event, Constants.ADMIN_UI_PATH);
                }
            }
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }
    }
}
