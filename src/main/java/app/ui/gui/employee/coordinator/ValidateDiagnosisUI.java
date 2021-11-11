package app.ui.gui.employee.coordinator;

import app.controller.App;
import app.controller.ValidateTestReportController;
import app.domain.model.Company;
import app.domain.model.stores.TestStore;
import app.domain.model.test.Test;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ValidateDiagnosisUI implements Initializable {

    @FXML
    private ChoiceBox<String> testChoice;
    @FXML
    private Label label;

    private String id;
    private String report;
    private String diagnosis;
    private TestStore testStore;

    private ValidateTestReportController vtrc = new ValidateTestReportController();

    private final SceneController sceneController = new SceneController();
    private final PopUpMessages popUpMessages = new PopUpMessages();

    public void confirm(ActionEvent event) throws IOException {
        try {
            vtrc.validateTestReport(id);
        }catch(Exception e){
            popUpMessages.errorMessage(event, "Error with diagnosis validation!");
            return;
        }
        popUpMessages.informationMessage(event, "Test validated with success!");
        sceneController.switchToScene(event, Constants.COORDINATOR_UI_PATH);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Company company = App.getInstance().getCompany();
        testStore = company.getTestStore();

        label.setText("");

        for (Test test : testStore.getTests()) {
            if (test.getState().equals(Test.State.DIAGNOSED)) {
                testChoice.getItems().add(test.getTestCode());
            }
        }
        selectedResults();
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

    private void selectedResults() {

        testChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                id = (String) testChoice.getValue();
                for(Test test : testStore.getTests()) {
                    if(test.getTestCode().equalsIgnoreCase(id)) {
                        report = test.getReport().getReport();
                        diagnosis = test.getReport().getDiagnosis().getDiagnosis();
                    }
                }
                label.setText(report+", "+diagnosis);
            }
        });
    }
}
