package app.ui.gui.client;

import app.controller.App;
import app.controller.UpdateClientController;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateDataUI implements Initializable {
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField phoneTxt;
    @FXML
    private TextField addressTxt;
    @FXML
    private RadioButton sexM;
    @FXML
    private RadioButton sexF;

    private final PopUpMessages popUpMessages = new PopUpMessages();

    private final SceneController sceneController = new SceneController();

    private UpdateClientController ucc = new UpdateClientController();

    public void confirm(ActionEvent event) throws IOException {
        try {
            ucc.editName(nameTxt.getText());
            ucc.editPhoneNumber(phoneTxt.getText());
            ucc.editAddress(addressTxt.getText());
            if (sexM.isSelected()) {
                ucc.editSex("male");
            } else if (sexF.isSelected()) {
                ucc.editSex("female");
            }
            try {
                sceneController.switchToScene(event, Constants.CLIENT_UI_PATH);
            } catch (Exception error) {
                popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
            }
            popUpMessages.informationMessage(event, "Data updated with success!");
        } catch (Exception e) {
            popUpMessages.errorMessage(event, "System Error\n\nClient data error\n" + e.getMessage());
            sceneController.switchToScene(event, Constants.CLIENT_UI_PATH);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameTxt.setText(ucc.getName());
        phoneTxt.setText(ucc.getPhoneNumber());
        addressTxt.setText(ucc.getAddress());
        if (ucc.getSex().equalsIgnoreCase("male")) {
            sexM.fire();
        } else {
            sexF.fire();
        }
    }

    public void returnBtn(ActionEvent event) {

        try {
            sceneController.switchToScene(event, Constants.CLIENT_UI_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }

        App app = App.getInstance();
        app.doLogout();
    }
}
