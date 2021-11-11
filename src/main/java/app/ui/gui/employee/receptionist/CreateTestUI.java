package app.ui.gui.employee.receptionist;

import app.controller.App;
import app.controller.CreateTestController;
import app.domain.model.Company;
import app.domain.model.testComponents.Parameter;
import app.domain.model.testComponents.ParameterCategory;
import app.domain.model.testComponents.TestType;
import app.domain.shared.Constants;
import app.ui.gui.utils.PopUpMessages;
import app.ui.gui.utils.SceneController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CreateTestUI implements Initializable {
    @FXML
    private TextField tinTxt;
    @FXML
    private TextField nhsTxt;
    @FXML
    private ComboBox<String> testTypeCombo;
    @FXML
    private ListView<String> categoriesListView;
    @FXML
    private ListView<String> parameterListView;
    @FXML
    private ListView<String> selectedList;
    @FXML
    private Label selectedParam;

    private final PopUpMessages popUpMessages = new PopUpMessages();
    private final SceneController sceneController = new SceneController();
    private final Company company = App.getInstance().getCompany();
    private CreateTestController ctc = new CreateTestController();
    private TestType testType;
    private List<ParameterCategory> catList = new ArrayList<>();
    private List<Parameter> paramList = new ArrayList<>();
    private List<String> selectedParameter = new ArrayList<>();

    public void confirm(ActionEvent event) throws IOException {
        try {

            if (!this.ctc.findClient(this.tinTxt.getText())) {
                this.popUpMessages.errorMessage(event, "System Error\n\nClient not valid");
                return;
            }

            if (tinTxt.getText().equals("")) {
                this.popUpMessages.errorMessage(event, "System Error\n\nTin cannot be blank");
                return;
            }

            if (nhsTxt.getText().equals("")) {
                this.popUpMessages.errorMessage(event, "System Error\n\nNHS cannot be blank");
                return;
            }

            for (int i = 0; i < paramList.size(); i++) {
                for (int j = 0; j < paramList.size(); j++) {
                    if (i != j && paramList.get(i) == paramList.get(j)) {
                        paramList.remove(i);
                    }
                }
            }

            if (ctc.createTest(tinTxt.getText(), nhsTxt.getText(), testType, paramList)) {
                ctc.saveTest();
                popUpMessages.informationMessage(event, "Success\n\nTest create with success");
            }
        } catch (Exception error) {
            this.popUpMessages.errorMessage(event, "System Error\n\nTest not create!\n" + error.getMessage());
            return;
        }
        sceneController.switchToScene(event, Constants.RECEPTIONIST_PATH);
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

    public void initialize(URL location, ResourceBundle resources) {
        selectedList.setEditable(false);
        selectedList.setVisible(false);
        selectedParam.setVisible(false);
        if (this.fillTestType()) {
            this.selectedTestType();
        }
        System.out.println(App.getInstance().getCompany().getParameterCategoryStore().getPcList().size());

    }

    private boolean fillTestType() {
        try {
            List<TestType> testTypeList = company.getTestTypeStore().getTestTypeList();
            String[] testTypeStr = new String[testTypeList.size()];

            for (int c = 0; c < testTypeList.size(); c++) {
                testTypeStr[c] = testTypeList.get(c).getDescription();
            }

            testTypeCombo.getItems().addAll(testTypeStr);
            return true;
        } catch (Exception var4) {
            return false;
        }
    }

    private void selectedTestType() {
        this.testTypeCombo.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            List<TestType> testTypeList = company.getTestTypeStore().getTestTypeList();
            for (TestType testType1 : testTypeList) {
                if (testType1.getDescription().equals(t1)) {
                    testType = testType1;
                }
            }

            if (fillCategories()) {
                selectedCategories();
            }
        });
    }

    private boolean fillCategories() {
        try {
            testTypeCombo.setDisable(true);
            boolean check = false;

            categoriesListView.getItems().clear();

            ParameterCategory[] paramCat = testType.getCategories();
            List<String> paramCatStr = new ArrayList<>();

            for (int c = 0; c < paramCat.length; c++) {
                for (ParameterCategory cat : catList) {
                    if (cat.getName().equals(paramCat[c].getName())) {
                        check = true;
                    }
                }
                if (!check)
                    paramCatStr.add(paramCat[c].getName());
                else
                    check = false;
            }
            if (!paramCatStr.isEmpty()) {
                categoriesListView.getItems().addAll(paramCatStr);
            } else {
                categoriesListView.getItems().add("Categories not found");
                categoriesListView.setDisable(true);
            }
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    private void selectedCategories() {
        categoriesListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            List<ParameterCategory> catList1 = company.getParameterCategoryStore().getPcList();

            for (ParameterCategory cat : catList1) {
                if (cat.getName().equals(t1)) {
                    catList.add(cat);
                    break;
                }
            }
            parameterListView.setDisable(false);
            fillCategories();
            fillParameters();
        });
    }

    private void fillParameters() {
        parameterListView.getItems().clear();

        List<Parameter> parameterList = company.getParameterStore().getParameterList();
        boolean check = false;

        for (ParameterCategory cat : catList) {
            for (Parameter param : parameterList) {
                if (param.getCat().getName().equals(cat.getName())) {
                    for (Parameter param1 : paramList) {
                        if (param1.getName().equals(param.getName())) {
                            check = true;
                        }
                    }
                    if (!check)
                        parameterListView.getItems().add(param.getName());
                    else
                        check = false;
                }
            }
        }

        if (parameterListView.getItems().isEmpty()) {
            parameterListView.getItems().add("Parameters not found");
            parameterListView.setDisable(true);
        } else {
            selectedParameters();
        }
    }

    private void selectedParameters() {
        parameterListView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            categoriesListView.setDisable(true);
            List<Parameter> paramList1 = company.getParameterStore().getParameterList();

            for (Parameter param : paramList1) {
                if (param.getName().equals(t1)) {
                    paramList.add(param);
                    selectedParameter.add(param.getName());
                    fillSelectedParam();
                    break;
                }
            }
            fillParameters();
        });
    }

    private void fillSelectedParam() {
        selectedList.setVisible(true);
        selectedParam.setVisible(true);

        for (int i = 0; i < selectedParameter.size(); i++) {
            for (int j = 0; j < selectedParameter.size(); j++) {
                if (i != j && selectedParameter.get(i).equalsIgnoreCase(selectedParameter.get(j))) {
                    selectedParameter.remove(i);
                }
            }
        }
        selectedList.getItems().clear();
        selectedList.getItems().addAll(selectedParameter);
    }
}