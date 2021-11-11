/*
package app.ui.console;

import app.controller.ParameterCategoryController;
import app.controller.TestTypeController;
import app.domain.model.testComponents.ParameterCategory;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestTypeUI implements Runnable {
    TestTypeController controller;

    public TestTypeUI() {
        controller = new TestTypeController();
        pcc = new ParameterCategoryController();
    }

    ParameterCategoryController pcc;


    public void run() {
        Scanner input = new Scanner(System.in);
        List<ParameterCategory> store = null;
        try {
            store = pcc.getList().getPcList();
        } catch (Exception e) {
            //error
        }
        if (store != null && !store.isEmpty()) {
            System.out.println("\n\nINFORMATION REQUIRED\n--------------------");
            String code = Utils.readLineFromConsole("Enter Test Type Code: ");
            String description = Utils.readLineFromConsole("Enter Test Type description: ");
            String collectingMethods = Utils.readLineFromConsole("Enter Collecting Methods description: ");

            List<ParameterCategory> categoryList = pcc.getList().getPcList();

            int n;
            System.out.println("How many categories will the Test Type have?");
            n = input.nextInt();
            ParameterCategory[] categories = new ParameterCategory[n];
            String cn;
            List<String> categoryName = new ArrayList<>();

            for (ParameterCategory pc : categoryList) {
                categoryName.add(pc.getName());
            }
            int t = 0;
            while (t < n) {
                cn = (String) Utils.showAndSelectOne(categoryName, "Category List");
                if (cn == null) {
                    System.out.println("Operation canceled");
                    return;
                }
                for (ParameterCategory pc : categoryList) {
                    if (pc.getName().equalsIgnoreCase(cn)) {
                        categories[t] = pc;
                        t++;
                    }
                }
            }
            boolean resultcreate = false;
            boolean duplicates = false;
            for (int j = 0; j < categories.length; j++)
                for (int k = j + 1; k < categories.length; k++)
                    if (k != j && categories[k] == categories[j])
                      duplicates = true;

            if (!duplicates) {
                try {
                    resultcreate = controller.createTestType(code, description, collectingMethods, categories);
                } catch (Exception e) {
                    resultcreate = false;
                }
            } else {
                System.out.print("There cannot be repeated categories!");
                resultcreate = false;
            }


            if (resultcreate) {
                System.out.println("\n\nTEST TYPE DATA\n--------------------\nCode: " + code + "\nDescription: " + description + "\nCollecting Methods: " + collectingMethods + "\nCategories: ");

                for (int c = 0; c < n; c++) {
                    System.out.print(categories[c].getName() + "; ");
                }
                boolean result = Utils.confirm("s -> confirm    n -> cancel");

                if (result) {
                    result = controller.saveTestType();

                    if (result) {
                        System.out.println("Test Type saved successfully");
                    } else {
                        System.out.println("Test Type already existed!");
                    }
                } else {
                    System.out.println("\nOperation canceled");
                }
            } else {
                System.out.println("Error with Test Type creation!");
            }

        } else {
            System.out.println("Parameter Category list is empty");
        }
    }
}
*/

