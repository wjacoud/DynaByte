package app.ui.gui;

import app.controller.AuthController;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import auth.mappers.dto.UserRoleDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Objects;

public class AuthUI {
    @FXML
    private TextField emailTxt;
    @FXML
    private PasswordField passwordTxt;

    private AuthController ctrl;
    private int maxAttempts = 3;

    private final SceneController sceneController = new SceneController();
    private final PopUpMessages popUpMessages = new PopUpMessages();

    public void confirm(ActionEvent event) {

        ctrl = new AuthController();

        String id = emailTxt.getText();
        String pwd = passwordTxt.getText();

        boolean success = ctrl.doLogin(id, pwd);

        if (!success) {
            maxAttempts--;

            if (maxAttempts <= 0) {
                try {
                    sceneController.switchToScene(event, Constants.MAIN_MENU_PATH);
                    return;
                } catch (Exception error) {
                    popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
                }
            }
            popUpMessages.warningMessage(event, "Invalid UserId and/or Password. \n You have  " + maxAttempts + " more attempt(s).");
        }

        if (success) {
            List<UserRoleDTO> roles = this.ctrl.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                System.out.println("User has not any role assigned.");
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    switch (role.getDescription()) {
                        case "ADMINISTRATOR":
                            try {
                                sceneController.switchToScene(event, Constants.ADMIN_UI_PATH);
                            } catch (Exception error) {
                                popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
                            }
                            break;
                        case "CHEMISTRYTECHNOLOGIST":
                            try {
                                sceneController.switchToScene(event, Constants.CHEMISTRY_UI_PATH);
                            } catch (Exception error) {
                                popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
                            }
                            break;
                        case "LABORATORYCOORDINATOR":
                            try {
                                sceneController.switchToScene(event, Constants.COORDINATOR_UI_PATH);
                            } catch (Exception error) {
                                popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
                            }
                            break;
                        case "MEDICALLABTECHNICIAN":
                            try {
                                sceneController.switchToScene(event, Constants.MEDICAL_TECH_UI_PATH);
                            } catch (Exception error) {
                                popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
                            }
                            break;
                        case "RECEPTIONIST":
                            try {
                                sceneController.switchToScene(event, Constants.RECEPTIONIST_PATH);
                            } catch (Exception error) {
                                popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
                            }
                            break;
                        case "SPECIALISTDOCTOR":
                            try {
                                sceneController.switchToScene(event, Constants.SPECIALIST_UI_PATH);
                            } catch (Exception error) {
                                popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
                            }
                            break;
                        case "CLIENT":
                            try {
                                sceneController.switchToScene(event, Constants.CLIENT_UI_PATH);
                            } catch (Exception error) {
                                popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
                            }
                            break;
                    }
                } else {
                    System.out.println("User did not select a role.");
                }
            }
        }
    }

    public void returnBtn(ActionEvent event) {
        try {
            sceneController.switchToScene(event, Constants.MAIN_MENU_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }

        logout();
    }

    private void logout() {
        ctrl.doLogout();
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1)
            return roles.get(0);
        else
            return (UserRoleDTO) Utils.showAndSelectOne(roles, "Select the role you want to adopt in this session:");
    }
}