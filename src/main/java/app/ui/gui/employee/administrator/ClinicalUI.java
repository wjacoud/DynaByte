package app.ui.gui.employee.administrator;

import app.controller.App;
import app.controller.CreateClinicalController;
import app.domain.model.testComponents.TestType;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClinicalUI implements Initializable {
    @FXML
    private TextField laboratoryTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField addressTxt;
    @FXML
    private TextField tinTxt;
    @FXML
    private TextField phoneTxt;
    @FXML
    private ListView<String> typesList;

    private final SceneController sceneController = new SceneController();
    private final PopUpMessages popUpMessages = new PopUpMessages();

    private CreateClinicalController ccc = new CreateClinicalController();
    private List<String> testTypeSelectedList = new ArrayList<>();
    private List<String> banTT = new ArrayList<>();

    public void confirm(ActionEvent event) throws IOException {

        testTypeSelectedList.add("Blood");
        String[] testTypesChoosed = new String[testTypeSelectedList.size()];
        for (int c = 0; c < testTypesChoosed.length; c++) {
            testTypesChoosed[c] = testTypeSelectedList.get(c);
        }
        try {
            ccc.createClinical(laboratoryTxt.getText(), nameTxt.getText(), addressTxt.getText(), tinTxt.getText(), phoneTxt.getText(), testTypesChoosed);
            if (ccc.saveClinical()) {
                popUpMessages.informationMessage(event, "Clinical registered with success!");

            } else {
                popUpMessages.errorMessage(event, "Error with clinical creation!");
            }
        } catch (Exception e) {
            popUpMessages.errorMessage(event, "System Error\n\nClinical data error\n" + e.getMessage());
        }
        sceneController.switchToScene(event, Constants.ADMIN_UI_PATH);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        banTT.add("Blood");
        if (fillList())
            selectedList();
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

    private boolean fillList() {
        typesList.getItems().clear();
        boolean found = false;
        List<TestType> testTypeList = ccc.getTestType();
        boolean check = false;

        for (TestType testType : testTypeList) {
            for (String ban : banTT) {
                if (testType.getDescription().equalsIgnoreCase(ban)) {
                    check = true;
                }
            }
            if (!check) {
                found = true;
                typesList.getItems().add(testType.getDescription());
            }
            check = false;
        }

        if (!found) {
            typesList.getItems().add("There is no tests types available");
            return false;
        }
        return true;
    }

    private void selectedList() {

        typesList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            testTypeSelectedList.add(t1);
            banTT.add(t1);
            fillList();
        });
    }
}
