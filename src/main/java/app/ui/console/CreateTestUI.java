/*
package app.ui.console;

import app.controller.CreateTestController;
import app.controller.ParameterCategoryController;
import app.controller.ParameterController;
import app.domain.model.testComponents.Parameter;
import app.domain.model.testComponents.ParameterCategory;
import app.domain.model.testComponents.TestType;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.util.ArrayList;

import java.util.List;

public class CreateTestUI implements Runnable {

    CreateTestController ctc;
    ParameterController par;
    ParameterCategoryController pcc;
    List<Parameter> parametersSelected = new ArrayList<>();


    public CreateTestUI() {
        ctc = new CreateTestController();
        par = new ParameterController();
        pcc = new ParameterCategoryController();
    }

    public void run() {
        List<ParameterCategory> catList = null;
        List<Parameter> parList = null;
        try {
            catList = pcc.getList().getPcList();
            parList = par.getList().getParameterList();
        } catch (Exception e) {
            //error
        }


        if (catList != null && !catList.isEmpty() && parList != null && !parList.isEmpty()) {


            System.out.println("-------------INFORMATION REQUIRED-------------");
            String tin = Utils.readLineFromConsole("Tin: ");
            if (!ctc.findClient(tin)) {
                System.out.println("\nClient not found!");
                return;
            }
            String nhsCode = Utils.readLineFromConsole("NHS Code: ");
            if (ctc.nhsCodeVerification(nhsCode)) {
                System.out.println("\nThere is already a test registered with that NHS Code!");
                return;
            }
            List<TestType> testTypesList = ctc.getTestType();

            TestType testType = null;
            String ttn;
            List<String> testTypeDescription = new ArrayList<>();

            for (TestType tp : testTypesList) {
                testTypeDescription.add(tp.getDescription());
            }


            ttn = (String) Utils.showAndSelectOne(testTypeDescription, "\nTest Type List");
            if (ttn == null) {
                System.out.println(Constants.OPERATION_CANCELED);
                return;
            }
            for (TestType tt : testTypesList) {
                if (tt.getDescription().equalsIgnoreCase(ttn)) {
                    testType = tt;
                }
            }
            List<ParameterCategory> categoriesList = new ArrayList<>();
            for (int p = 0; p < testType.getCategories().length; p++) {
                categoriesList.add(testType.getCategories()[p]);
            }
            List<Parameter> parameterList = ctc.getParameter();

            int n;

            n = Utils.readIntegerFromConsole("\nHow many categories will the test have?");
            String[] categories = new String[n];

            List<String> categoryName = new ArrayList<>();

            for (ParameterCategory pc : categoriesList) {
                categoryName.add(pc.getName());
            }


            for (int c = 0; c < n; c++) {
                categories[c] = (String) Utils.showAndSelectOne(categoryName, "\nCategory List");
            }

            for (int c = 0; c < n; c++) {
                if (categories[c] == null) {
                    System.out.println(Constants.OPERATION_CANCELED);
                    return;
                }
            }
            boolean result = false;
            boolean duplicates = false;

            for (int j = 0; j < categories.length; j++) {
                for (int k = 0; k < categories.length; k++) {
                    if (k != j && categories[k].equals(categories[j])) {
                        duplicates = true;
                        break;
                    }
                }
            }

            int m = 0;

            if (!duplicates) {

                parametersSelected = new ArrayList<>();
                m = Utils.readIntegerFromConsole("\nHow many parameters will the test have?");


                List<String> parameterName = new ArrayList<>();
                String select;

                for (int r = 0; r < n; r++) {
                    for (Parameter parameter : parameterList) {
                        if (parameter.getCat().getName().equals(categories[r])) {
                            parameterName.add(parameter.getName());
                        }
                    }
                }

                for (int l = 0; l < m; l++) {

                    select = (String) Utils.showAndSelectOne(parameterName, "\nParameter List");
                    if (select == null) {
                        System.out.println(Constants.OPERATION_CANCELED);
                        return;
                    }
                    for (int c = 0; c < parameterList.size(); c++) {
                        if (select.equals(parameterList.get(c).getName())) {
                            parametersSelected.add(parameterList.get(c));
                        }
                    }
                }

                if (parametersSelected.size() > 1) {
                    for (int i = 0; i < parametersSelected.size(); i++) {
                        for (int j = 0; j < parametersSelected.size(); j++) {
                            if (i != j && parametersSelected.get(i) == parametersSelected.get(j)) {
                                duplicates = true;
                            }
                        }
                    }
                }


                if (!duplicates) {
                    try {
                        result = ctc.createTest(tin, nhsCode, testType, parametersSelected);
                    } catch (Exception e) {
                        System.out.println("\nError with Test creation!");
                    }
                } else {
                    System.out.print("\nThere cannot be repeated parameters!");
                }
            } else {
                System.out.print("\nThere cannot be repeated categories!");
            }

            if (result) {
                System.out.print("-------------TEST DATA-------------\nNHS Code: " + nhsCode + "\ndate: " + ctc.getDate() + "\nTest Type: " + testType.getDescription() + "\nCategories: ");

                for (int c = 0; c < n; c++) {
                    System.out.print(categories[c] + "; ");
                }
                System.out.print("\nParameters: ");
                for (int c = 0; c < parametersSelected.size(); c++) {
                    System.out.print(parametersSelected.get(c).getName() + "; ");
                }

                result = Utils.confirm("s -> confirm    n -> cancel");

                if (result) {
                    result = ctc.saveTest();
                } else {
                    System.out.println(Constants.OPERATION_CANCELED);
                    return;
                }

                if (result) {
                    System.out.println("\nTest saved with success!");
                } else {
                    System.out.println("\nError with test save!");
                }
            }
        } else {
            System.out.println("\nParameter Category list and Parameter List cannot be empty");
        }
    }
}
*/

