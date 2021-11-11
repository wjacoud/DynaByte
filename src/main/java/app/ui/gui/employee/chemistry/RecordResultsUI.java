package app.ui.gui.employee.chemistry;

import app.controller.App;
import app.controller.RecordTestResultController;
import app.domain.model.Company;
import app.domain.model.stores.SampleStore;
import app.domain.model.stores.TestStore;
import app.domain.model.test.Sample;
import app.domain.model.test.Test;
import app.domain.model.testComponents.Parameter;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RecordResultsUI implements Initializable {

    @FXML
    private ChoiceBox<String> testChoice;
    @FXML
    private Button but;
    @FXML
    private TextField textField;
    @FXML
    private Label label;

    private final SceneController sceneController = new SceneController();
    private final PopUpMessages popUpMessages = new PopUpMessages();

    private final RecordTestResultController rttc = new RecordTestResultController();
    private List<Parameter> parameterList = new ArrayList<>();
    private boolean all = false;
    private int c = 0;

    Company company = App.getInstance().getCompany();

    public void confirm(ActionEvent event) {
        if (all) {
            rttc.setState(true);
            popUpMessages.informationMessage(event, "Success\n\nAll results have been saved");
            try {
                sceneController.switchToScene(event, Constants.CHEMISTRY_UI_PATH);
            } catch (Exception e) {
                popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
            }
        } else {
            popUpMessages.errorMessage(event, "System Error\n\nParameters without results");
        }
    }

    public void next(ActionEvent event) {

        try {
            label.setText(parameterList.get(c).getName());
            rttc.addTestParameterResult(parameterList.get(c).getCode(), Double.parseDouble(textField.getText()));
            textField.setText("");
            c++;
            if (c == parameterList.size()) {
                label.setText("No more parameters");
                but.setVisible(false);
                textField.setVisible(false);
                all = true;
            }else{
                label.setText(parameterList.get(c).getName());
            }
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nThe value must be a number");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.setVisible(false);
        but.setVisible(false);
        textField.setVisible(false);
        SampleStore sampleStore = company.getSampleStore();
        TestStore testStore = company.getTestStore();

        for (Sample sample : sampleStore.getSampleList()) {
            for (Test test : testStore.getTests()) {
                if (sample.getTestID().equals(test.getTestCode())) {
                    if (test.getState().equals(Test.State.SAMPLE_COLLECTED)) {
                        testChoice.getItems().add(sample.getBarcode());
                    }
                }
            }
        }
        selectedList();
    }

    public void returnBtn(ActionEvent event) {
        try {
            sceneController.switchToScene(event, Constants.CHEMISTRY_UI_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }

        App app = App.getInstance();
        app.doLogout();
    }

    private void selectedList() {
        testChoice.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            testChoice.setDisable(true);
            parameterList = rttc.getParameters(testChoice.getValue());
            label.setVisible(true);
            but.setVisible(true);
            textField.setVisible(true);
            label.setText(parameterList.get(0).getName());
        });
    }
}
