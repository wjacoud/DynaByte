package app.ui.gui.employee.medicalTech;

import app.controller.App;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;

public class MedicalTechUI {
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

    public void recordSampleBtn(ActionEvent event){
        SceneController sceneController = new SceneController();
        PopUpMessages popUpMessages = new PopUpMessages();

        try {
            sceneController.switchToScene(event, Constants.CREATE_SAMPLE_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }
    }
}
