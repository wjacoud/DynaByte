package app.ui.gui.employee.administrator;

import app.controller.App;
import app.controller.ParameterCategoryController;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class ParameterCategoryUI {
    @FXML
    private TextField codeTxt;
    @FXML
    private TextField nameTxt;

    private final PopUpMessages popUpMessages = new PopUpMessages();

    private ParameterCategoryController pcc = new ParameterCategoryController();

    private final SceneController sceneController = new SceneController();

    public void confirm(ActionEvent event) {
        try {
            pcc.createParameterCategory(codeTxt.getText(),nameTxt.getText());
            if(pcc.saveParameterCategory()){
                popUpMessages.informationMessage(event,"Parameter Category created with success!");

            }else{
                popUpMessages.informationMessage(event,"Error with Parameter Category creation!");
            }
            try {
                sceneController.switchToScene(event, Constants.ADMIN_UI_PATH);
            } catch (Exception error) {
                popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
            }
        }catch(Exception error) {
            popUpMessages.errorMessage(event,"System error\n\nError with category data!");
        }
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
}
