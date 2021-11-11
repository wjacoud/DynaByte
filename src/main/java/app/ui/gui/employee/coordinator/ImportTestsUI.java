package app.ui.gui.employee.coordinator;

import app.controller.App;
import app.controller.ImportTestsController;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ImportTestsUI implements Initializable {

    @FXML
    private Label outputLbl;
    @FXML
    private Label pathLbl;
    @FXML
    private ListView listView;
    @FXML
    private ListView errorList;

    private final SceneController sceneController = new SceneController();
    private final PopUpMessages popUpMessage = new PopUpMessages();

    private String path;
    private boolean result;
    private List<String> lstFile;
    private ImportTestsController itc = new ImportTestsController();

    public void confirm(ActionEvent event) {
        listView.getItems().clear();
        errorList.getItems().clear();
        try {
            result = itc.readFile(path);
        } catch (Exception error) {
            popUpMessage.errorMessage(event, "System error\n\nFile not found!");
        }
        if (result) {
            outputLbl.setText("Error with " + itc.getNumberOfErrors() + " lines!");
        } else {
            outputLbl.setText("Please select a valid file!");
        }
        listView.getItems().addAll(itc.getCorrectLine());

        errorList.getItems().addAll(itc.getListOfErrors());

    }

    @FXML
    public void fileBtn(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", lstFile));
        File f = fc.showOpenDialog(null);

        if (f != null) {
            path = f.getAbsolutePath();
            pathLbl.setText(path);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        outputLbl.setText("");
        pathLbl.setText("");
        lstFile = new ArrayList<>();
        lstFile.add("*.csv");
    }

    public void returnBtn(ActionEvent event) {

        try {
            sceneController.switchToScene(event, Constants.COORDINATOR_UI_PATH);
        } catch (Exception error) {
            popUpMessage.errorMessage(event, "System Error\n\nMenu not found");
        }

        App app = App.getInstance();
        app.doLogout();
    }
}
