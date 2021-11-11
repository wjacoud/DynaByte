package app.ui.gui.employee.administrator;

import app.controller.App;
import app.controller.TestTypeController;
import app.domain.model.Company;
import app.domain.model.testComponents.ParameterCategory;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CreateTestTypeUI implements Initializable {
    @FXML
    private TextField codeTxt;
    @FXML
    private TextField descriptionTxt;
    @FXML
    private TextField methodsTxt;
    @FXML
    private ListView<String> catList;

    TestTypeController ttc = new TestTypeController();
    List<ParameterCategory> categoryList = new ArrayList<>();
    List<String> categorySelectedList = new ArrayList<>();
    List<String> banCat = new ArrayList<>();

    SceneController sceneController = new SceneController();
    PopUpMessages popUpMessages = new PopUpMessages();

    Company company = App.getInstance().getCompany();

    public void confirm(ActionEvent event) throws IOException {
        ParameterCategory[] catChoosed = new ParameterCategory[categorySelectedList.size()];
        for (int c = 0; c < catChoosed.length; c++) {
            for (ParameterCategory cat : company.getParameterCategoryStore().getPcList()) {
                for (int f = 0; f < categorySelectedList.size(); f++) {
                    if (categorySelectedList.get(c).equalsIgnoreCase(cat.getName())) {
                        catChoosed[c] = cat;
                    }
                }
            }
        }
        try {
            ttc.createTestType(codeTxt.getText(), descriptionTxt.getText(), methodsTxt.getText(), catChoosed);
            if (ttc.saveTestType()) {
                popUpMessages.informationMessage(event, "Test type registered with success!");

            } else {
                popUpMessages.errorMessage(event, "Error with test type creation!");
            }
        } catch (Exception e) {
            popUpMessages.errorMessage(event, "System Error\n\n" + e.getMessage());
        }
        sceneController.switchToScene(event, Constants.ADMIN_UI_PATH);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (fillList()){
            selectedList();
        }
    }

    private boolean fillList() {
        catList.getItems().clear();

        boolean found = false;
        categoryList = company.getParameterCategoryStore().getPcList();
        boolean check = false;

        for (ParameterCategory cat : categoryList) {
            for (String ban : banCat) {
                if (cat.getName().equalsIgnoreCase(ban)) {
                    check = true;
                }
            }
            if (!check) {
                found = true;
                catList.getItems().add(cat.getName());
            }
            check = false;
        }

        if (!found) {
            catList.getItems().add("There is no categories available");
            return false;
        }
        return true;
    }

    public void returnBtn(ActionEvent event) {
         try {
             sceneController.switchToScene(event, Constants.ADMIN_UI_PATH );
         } catch (Exception error) {
             popUpMessages.errorMessage(event, "System Error\n\nAdmin menu not found");
         }

         App app = App.getInstance();
         app.doLogout();
    }

    private void selectedList() {
        catList.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            categorySelectedList.add(t1);
            banCat.add(t1);
            fillList();
        });
    }
}

