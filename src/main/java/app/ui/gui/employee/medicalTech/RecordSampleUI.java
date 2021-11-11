package app.ui.gui.employee.medicalTech;

import app.controller.App;
import app.controller.RecordSampleController;
import app.domain.model.Company;
import app.domain.model.client.Client;
import app.domain.model.stores.TestStore;
import app.domain.model.test.Test;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class RecordSampleUI implements Initializable {
    @FXML
    private ChoiceBox testChoice;
    @FXML
    private TextField samplesTxt;

    private RecordSampleController rsc = new RecordSampleController();
    private String[] arr;

    private Client client;

    private final SceneController sceneController = new SceneController();
    private final PopUpMessages popUpMessages = new PopUpMessages();

    public void confirm(ActionEvent event) throws IOException {
        try {
            arr = testChoice.getValue().toString().split(", ");
            int c = Integer.parseInt(samplesTxt.getText());
            for (int f = 0; f < c; f++) {
                rsc.createSample(arr[0]);
                rsc.saveSample();
                rsc.setState(arr[0]);
            }
        } catch (Exception e) {
            popUpMessages.errorMessage(event, "Error!\nChoose a test and insert the number of samples.");
            return;
        }
        popUpMessages.informationMessage(event, "Samples saved with success!");
        sceneController.switchToScene(event, Constants.MEDICAL_TECH_UI_PATH);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Company company = App.getInstance().getCompany();
        TestStore testStore = company.getTestStore();

        for (Test test : testStore.getTests())
            if (test.getState().equals(Test.State.REGISTERED)) {
                client = rsc.getClientFromTest(test);
                testChoice.getItems().add(test.getTestCode() + ", " + client.getName());
            }

    }

    public void returnBtn(ActionEvent event) {
        try {
            sceneController.switchToScene(event, Constants.MEDICAL_TECH_UI_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }

        App app = App.getInstance();
        app.doLogout();
    }

}
