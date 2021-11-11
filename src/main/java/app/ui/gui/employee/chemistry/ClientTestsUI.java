package app.ui.gui.employee.chemistry;

import app.controller.App;
import app.controller.ClientTestsController;
import app.domain.model.client.Client;
import app.domain.model.test.Test;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.fxml.Initializable;
import app.controller.PropertiesController;

import java.lang.reflect.Method;
import java.util.Properties;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientTestsUI implements Initializable{


    ClientTestsController ctc = new ClientTestsController();
    Properties proper;

    @FXML
    private ChoiceBox<String> chbOrder;
    @FXML
    private ListView<String> testList;
    @FXML
    private Label testInfo;
    @FXML
    private ListView<String> clientList;

    private List<Client> cList = new ArrayList<>();
    private List<Test> tList = new ArrayList<>();

    private Client client;

    public void returnBtn(ActionEvent event) {
        SceneController sceneController = new SceneController();
        PopUpMessages popUpMessages = new PopUpMessages();

        try {
            sceneController.switchToScene(event, Constants.CHEMISTRY_UI_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }

        App app = App.getInstance();
        app.doLogout();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        proper = PropertiesController.getProperties();
        if (proper == null){
            throw new IllegalArgumentException("Properties are null");
        }
        String[] choices = {"Tin","Name"};
        chbOrder.getItems().addAll(choices);
        selectedOrder();
    }

    public void selectedOrder(){
        chbOrder.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            if(fillClientList(t1)){
                testList.getItems().clear();
                selectedClient();
            }
        });
    }

    public boolean fillClientList(String order) {

        Method method;
        clientList.getItems().clear();

        if(order.equalsIgnoreCase("Tin")){
            try{
                method = ctc.getClass().getMethod(proper.getProperty("order.TIN"));
                cList = (List<Client>) method.invoke(ctc);
            } catch (Exception e){
                return false;
            }
        } else if (order.equalsIgnoreCase("Name")){
            try{
                method = ctc.getClass().getMethod(proper.getProperty("order.NAME"));
                cList = (List<Client>) method.invoke(ctc);
            } catch (Exception e){
                return false;
            }
        }

        for (Client c : cList){
            clientList.getItems().add(c.getTin() + " / " + c.getName());
        }

        if(clientList.getItems().isEmpty() || clientList.getItems() == null){
            clientList.getItems().add("There are no items available");
            return false;
        }

        return true;
    }

    public void selectedClient(){
        clientList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            try{
                testInfo.setText("report label");
                String tin = t1.substring(0,Constants.TIN_LENGTH);
                fillTestList(tin);
            } catch (Exception e){
                //empty
            }

        });
    }

    public void fillTestList(String tin) {
        testList.getItems().clear();
        client = ctc.getClientChosen(tin);

        tList = ctc.getValidatedTests(client);

        for (Test t : tList){
            testList.getItems().add(t.getNhsCode());
        }

        if(testList.getItems().isEmpty()){
            testList.getItems().add("There are no items available");
        }else{
            selectedTest();
        }
    }

    public void selectedTest(){
        testList.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {
            try{
                testInfo.setText(ctc.toString(tList.get(t1.intValue())));
            } catch (Exception e){
                //empty
            }
        });
    }
}