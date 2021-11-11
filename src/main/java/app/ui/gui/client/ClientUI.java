package app.ui.gui.client;

import app.controller.App;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;

public class ClientUI {
    public void returnBtn(ActionEvent event) {
        SceneController sceneController = new SceneController();
        PopUpMessages popUpMessages = new PopUpMessages();

        try {
            sceneController.switchToScene(event, Constants.MAIN_MENU_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }

        App app = App.getInstance();
        app.doLogout();
    }

    public void testsBtn(ActionEvent event){
        SceneController sceneController = new SceneController();
        PopUpMessages popUpMessages = new PopUpMessages();

        try {
            sceneController.switchToScene(event, Constants.CHECK_TESTS_UI_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }
    }

    public void dataBtn(ActionEvent event){
        SceneController sceneController = new SceneController();
        PopUpMessages popUpMessages = new PopUpMessages();

        try {
            sceneController.switchToScene(event, Constants.UPDATE_DATA_UI_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }
    }
}
