/*
package app.ui.console;

import app.controller.ParameterCategoryController;
import app.ui.console.utils.Utils;

public class ParameterCategoryUI implements Runnable {

    ParameterCategoryController pcc;

    public ParameterCategoryUI() {
        pcc = new ParameterCategoryController();
    }

    public void run() {
        String code;
        String name;

        System.out.println("\n\nINFORMATION REQUIRED\n--------------------");
        code = Utils.readLineFromConsole("Parameter Category Code: ");
        name = Utils.readLineFromConsole("Parameter Category Name: ");

        boolean resultcreate = true;
        try {
            pcc.createParameterCategory(code, name);
        } catch (Exception e) {
            resultcreate = false;
        }

        if (resultcreate) {
            System.out.println("\nPARAMETER CATEGORY DATA\n--------------------\nCode: " + pcc.getList().getPc().getCode() + "\nName: " + pcc.getList().getPc().getName() + "\n\n");

            boolean result = Utils.confirm("s -> confirm    n -> cancel");

            if (result) {
                result = pcc.saveParameterCategory();
                if (result) {
                    System.out.println("Category saved successfully");
                } else {
                    System.out.println("Category wasn't saved, because it already exists");
                }
            } else {
                System.out.println("Operation canceled");
            }
        } else {
            System.out.println("Error with parameter category creation!");
        }
    }
}
*/

