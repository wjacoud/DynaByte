package app.ui.gui.employee.administrator;

import app.controller.App;
import app.controller.ParameterController;
import app.domain.model.Company;
import app.domain.model.testComponents.ParameterCategory;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class ParameterUI implements Initializable {

    @FXML
    private TextField codeTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField descTxt;
    @FXML
    private ChoiceBox<String> catChoice;
    PopUpMessages popUpMessages = new PopUpMessages();

    private Company company = App.getInstance().getCompany();

    ParameterController pcont = new ParameterController();

    SceneController sceneController = new SceneController();

    public void confirm(ActionEvent event) {
        try {
            ParameterCategory cat = getCategory();
            pcont.createParameter(codeTxt.getText(),nameTxt.getText(),descTxt.getText(),cat);
            if(pcont.saveParameter()){
                popUpMessages.informationMessage(event,"Parameter created with success!");

            }else{
                popUpMessages.informationMessage(event,"Error with Parameter creation!");
            }
            try {
                sceneController.switchToScene(event, Constants.ADMIN_UI_PATH);
            } catch (Exception error) {
                popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
            }
        }catch(Exception error) {
            popUpMessages.errorMessage(event,"System error\n\nError with parameter data!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(ParameterCategory pc : company.getParameterCategoryStore().getPcList())
        catChoice.getItems().add(pc.getName());
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

    private ParameterCategory getCategory() {
        for(ParameterCategory pc : company.getParameterCategoryStore().getPcList()) {
            if(catChoice.getValue().equals(pc.getName())){
                return pc;
            }
        }
        return null;
    }
}
