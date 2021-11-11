package app.ui.gui.employee.receptionist;

import app.controller.App;
import app.controller.CreateClientController;
import app.domain.model.Company;
import app.domain.model.client.Client;
import app.domain.model.stores.ClientsStore;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class RegistClientUI implements Initializable {

    @FXML
    private TextField citizenTxt;
    @FXML
    private TextField nhsTxt;
    @FXML
    private TextField tinTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField phoneTxt;
    @FXML
    private TextField emailTxt;
    @FXML
    private TextField addressTxt;
    @FXML
    private DatePicker dateTxt;
    @FXML
    private RadioButton sexM;
    @FXML
    private RadioButton sexF;

    private CreateClientController ccc = new CreateClientController();
    private ClientsStore store;
    private Client client;

    private String sex = null;

    private final PopUpMessages popUpMessages = new PopUpMessages();

    private final SceneController sceneController = new SceneController();

    public void confirm(ActionEvent event) {
        if(dateTxt.getValue()==null){
            popUpMessages.errorMessage(event, "You must select the birth date!");
        }else {
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate localDate = LocalDate.of(dateTxt.getValue().getYear(), dateTxt.getValue().getMonth(), dateTxt.getValue().getDayOfMonth());
            java.util.Date date = java.util.Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
            if (sexM.isSelected()) {
                sex = "male";
            } else {
                sex = "female";
            }

            try {
                ccc.createClient(citizenTxt.getText(), nhsTxt.getText(), date, tinTxt.getText(), nameTxt.getText(), phoneTxt.getText(), sex);
                if (ccc.saveClient()) {
                    ccc.addUser(emailTxt.getText(), nameTxt.getText());
                    client = store.getClientFromClientEmail(emailTxt.getText());
                    client.setAddress(addressTxt.getText());
                    popUpMessages.informationMessage(event, "Client registered with success!");

                } else {
                    popUpMessages.informationMessage(event, "Error with client creation!");
                }
                try {
                    sceneController.switchToScene(event, Constants.RECEPTIONIST_PATH);
                } catch (Exception error) {
                    popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
                }
            } catch (Exception e) {
                popUpMessages.errorMessage(event, "System Error\n\nClient data error\n" + e.getMessage());
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        App app = App.getInstance();
        Company company = app.getCompany();
        store = company.getClientStore();
    }

    public void returnBtn(ActionEvent event) {

        try {
            sceneController.switchToScene(event, Constants.RECEPTIONIST_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }

        App app = App.getInstance();
        app.doLogout();
    }
}
