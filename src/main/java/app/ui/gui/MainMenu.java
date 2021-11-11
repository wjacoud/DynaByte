package app.ui.gui;

import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;

public class MainMenu{

    private final SceneController sceneController = new SceneController();
    private final PopUpMessages popUpMessages = new PopUpMessages();

    public void loginBtn(ActionEvent event) {
        try {
            sceneController.switchToScene(event, Constants.AUTH_UI_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }
    }

    public void devTeamBtn(ActionEvent event) {
        try {
            sceneController.switchToScene(event, Constants.DEV_TEAM_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }
    }
}
