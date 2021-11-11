package app.ui.gui.employee.administrator;

import app.controller.App;
import app.controller.RegisterEmployeeController;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class RegistEmployeeUI implements Initializable {
    SceneController sceneController = new SceneController();
    PopUpMessages popUpMessages = new PopUpMessages();

    RegisterEmployeeController rec = new RegisterEmployeeController();

    @FXML
    private TextField nameTxt;
    @FXML
    private TextField addressTxt;
    @FXML
    private TextField phoneTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private TextField socTxt;
    @FXML
    private TextField docTxt;
    @FXML
    private ComboBox<String> roleChoice;

    public void confirm(ActionEvent event) {
        String role = roleChoice.getValue();
        switch (role) {
            case "Administrator":
                try {
                    if(rec.createAdministrator(nameTxt.getText(), addressTxt.getText(), phoneTxt.getText(), emailTxt.getText(), socTxt.getText())){
                        if(rec.saveAdministrator()) {
                            popUpMessages.informationMessage(event, "Administrator created with success!");
                        }else{
                            popUpMessages.informationMessage(event, "Administrator already exists!");
                        }
                    }
                } catch (Exception e) {
                    popUpMessages.informationMessage(event, "Error with Administrator creation!\n"+ e.getMessage());
                }
                break;
            case "Receptionist":
                try {
                    if(rec.createReceptionist(nameTxt.getText(), addressTxt.getText(), phoneTxt.getText(), emailTxt.getText(), socTxt.getText())){
                        if(rec.saveReceptionist()){
                        popUpMessages.informationMessage(event,"Receptionist created with success!");
                        }else{
                            popUpMessages.informationMessage(event, "Receptionist already exists!");
                        }
                    }
                } catch (Exception e) {
                    popUpMessages.informationMessage(event, "Error with Receptionist creation!\n"+ e.getMessage());
                }
                break;
            case "Medical Lab Technician":
                try {
                    if(rec.createMedicalLabTechnician(nameTxt.getText(), addressTxt.getText(), phoneTxt.getText(), emailTxt.getText(), socTxt.getText())){
                        if(rec.saveMedicalLabTechnician()){
                        popUpMessages.informationMessage(event,"Medical Lab Technician created with success!");
                        }else{
                            popUpMessages.informationMessage(event, "Medical Lab Technician already exists!");
                        }
                    }
                } catch (Exception e) {
                    popUpMessages.informationMessage(event, "Error with Medical Lab Technician creation!\n" + e.getMessage());
                }
                break;
            case "Chemistry Technologist":
                try {
                    if(rec.createChemistryTechnologist(nameTxt.getText(), addressTxt.getText(), phoneTxt.getText(), emailTxt.getText(), socTxt.getText())){
                        if(rec.saveChemistryTechnologist()){
                        popUpMessages.informationMessage(event,"Chemistry Technologist created with success!");
                        }else{
                            popUpMessages.informationMessage(event, "Chemistry Technologist already exists!");
                        }
                    }
                } catch (Exception e) {
                    popUpMessages.informationMessage(event, "Error with Chemistry Technologist creation!\n" + e.getMessage());
                }
                break;
            case "Specialist Doctor":
                try {
                    if(rec.createSpecialistDoctor(nameTxt.getText(), addressTxt.getText(), phoneTxt.getText(), emailTxt.getText(), socTxt.getText(), docTxt.getText())){
                        if(rec.saveSpecialistDoctor()){
                        popUpMessages.informationMessage(event,"Specialist Doctor created with success!");
                        }else{
                            popUpMessages.informationMessage(event, "Specialist Doctor already exists!");
                        }
                    }
                } catch (Exception e) {
                    popUpMessages.informationMessage(event, "Error with Specialist Doctor creation!\n" + e.getMessage());
                }
                break;
            case "Laboratory Coordinator":
                try {
                    if(rec.createLaboratoryCoordinator(nameTxt.getText(), addressTxt.getText(), phoneTxt.getText(), emailTxt.getText(), socTxt.getText())){
                        if(rec.saveLaboratoryCoordinator()){
                        popUpMessages.informationMessage(event,"Laboratory Coordinator created with success!");
                        }else{
                            popUpMessages.informationMessage(event, "Laboratory Coordinator already exists!");
                        }
                    }
                } catch (Exception e) {
                    popUpMessages.informationMessage(event, "Error with Laboratory Coordinator creation!\n" + e.getMessage());
                }
                break;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roleChoice.getItems().add("Administrator");
        roleChoice.getItems().add("Receptionist");
        roleChoice.getItems().add("Medical Lab Technician");
        roleChoice.getItems().add("Chemistry Technologist");
        roleChoice.getItems().add("Specialist Doctor");
        roleChoice.getItems().add("Laboratory Coordinator");
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
