package app.ui.gui.employee.specialist;

import app.controller.App;
import app.controller.CreateReportController;
import app.domain.model.Company;
import app.domain.model.stores.TestStore;
import app.domain.model.test.Test;
import app.domain.model.test.TestDiagnosis;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TestReportUI implements Initializable {

    @FXML
    private ChoiceBox<String> testChoice;
    @FXML
    private Label label;
    @FXML
    private TextArea diagnosisArea;

    private CreateReportController crc = new CreateReportController();
    private TestDiagnosis td;
    private String id;

    private final SceneController sceneController = new SceneController();
    private final PopUpMessages popUpMessages = new PopUpMessages();

    public void confirm(ActionEvent event) throws IOException {
        try {
            crc.createTestReport(id, td, diagnosisArea.getText());
            crc.setState(id);
            popUpMessages.informationMessage(event, "Diagnosis created with success!");
        }catch (Exception e) {
            popUpMessages.errorMessage(event, "Error with diagnosis creation!");
            return;
        }
        sceneController.switchToScene(event, Constants.SPECIALIST_UI_PATH);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Company company = App.getInstance().getCompany();
        TestStore testStore = company.getTestStore();

        for (Test test : testStore.getTests()) {
            if (test.getState().equals(Test.State.ANALYZED)) {
                testChoice.getItems().add(test.getTestCode());
            }
        }
        selectedResults();
    }

    public void returnBtn(ActionEvent event) {
        try {
            sceneController.switchToScene(event, Constants.SPECIALIST_UI_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }

        App app = App.getInstance();
        app.doLogout();
    }

    private void selectedResults() {

        testChoice.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            id = testChoice.getValue();
            td = crc.createTestDiagnosis(id);
            label.setText(td.getDiagnosis());
        });
    }
}
