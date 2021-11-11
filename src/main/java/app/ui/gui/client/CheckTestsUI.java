package app.ui.gui.client;

import app.controller.CheckTestsController;
import app.domain.model.test.Test;
import app.domain.model.test.TestParameter;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class CheckTestsUI implements Initializable {

    @FXML
    private ListView<String> testListView;

    @FXML
    private Label simpleLbl;

    @FXML
    private Label resultLbl;

    List<Test> testList;

    CheckTestsController ctc = new CheckTestsController();

    public void returnBtn(ActionEvent event) {
        SceneController sceneController = new SceneController();
        PopUpMessages popUpMessages = new PopUpMessages();

        try {
            sceneController.switchToScene(event, Constants.CLIENT_UI_PATH);
        } catch (Exception error) {
            popUpMessages.errorMessage(event, "System Error\n\nMenu not found");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (fillList())
            selectedList();
    }

    private boolean fillList() {
        boolean found = false;

        simpleLbl.setText("");
        resultLbl.setText("");

        testList = ctc.getClientTests();
        testList = ctc.organizeClientTestList(testList);

        for (Test test : testList) {
            if (test.getState().equals(Test.State.VALIDATED)) {
                testListView.getItems().add(test.getNhsCode() + ", " + test.getDateStr());
                found = true;
            }
        }

        if (!found) {
            testListView.getItems().add("There is no tests available");
            return false;
        }
        return true;
    }

    private void selectedList() {
        testListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                resultLbl.setText("");
                String[] contain = t1.split(",");
                simpleLbl.setText(ctc.getTestChosen(contain[0]).getReport().getReport());
                for(TestParameter tp : ctc.getTestChosen(contain[0]).getTestParameterList()){
                    resultLbl.setText(resultLbl.getText()+tp.getParameter().getName()+" = "+tp.getTestParameterResult().getResult()+"\n");
                }
            }
        });
    }
}
