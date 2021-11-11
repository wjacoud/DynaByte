package app.ui.gui.employee.administrator;

import app.controller.App;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;

public class AdminUI {

    SceneController sceneController = new SceneController();
    PopUpMessages popUpMessages = new PopUpMessages();

    public void returnBtn(ActionEvent event) {

        try {
            sceneController.switchToScene(event, Constants.MAIN_MENU_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }

        App app = App.getInstance();
        app.doLogout();
    }

    public void employeeBtn(ActionEvent event){

        try {
            sceneController.switchToScene(event, Constants.CREATE_EMPLOYEE_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }
    }

    public void clinicalBtn(ActionEvent event){

        try {
            sceneController.switchToScene(event, Constants.CREATE_CLINICAL_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }
    }

    public void parameterBtn(ActionEvent event){

        try {
            sceneController.switchToScene(event, Constants.PARAMETER_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }
    }

    public void parameterCategoryBtn(ActionEvent event){

        try {
            sceneController.switchToScene(event, Constants.PARAMETER_CATEGORY_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }
    }

    public void testTypeBtn(ActionEvent event){

        try {
            sceneController.switchToScene(event, Constants.CREATE_TEST_TYPE_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }
    }

    public void nhsBtn(ActionEvent event){

        try {
            sceneController.switchToScene(event, Constants.NHS_REPORT_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }
    }
}
