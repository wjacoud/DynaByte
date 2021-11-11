/*
package app.ui.console;

import app.controller.ParameterCategoryController;
import app.controller.ParameterController;
import app.domain.model.testComponents.ParameterCategory;
import app.ui.console.utils.Utils;

import java.util.List;
import java.util.Scanner;

public class ParameterUI implements Runnable {

    ParameterController par;
    ParameterCategoryController pcc;

    public ParameterUI() {
        par = new ParameterController();
        pcc = new ParameterCategoryController();
    }


    public void run() {
        List<ParameterCategory> store = null;
        try {
            store = pcc.getList().getPcList();
        } catch (Exception e) {
            //empty
        }

        Scanner input = new Scanner(System.in);
        String code;
        String name;
        String description;
        String op;
        ParameterCategory cat;
        int c;

        if (store != null && !store.isEmpty()) {

            cat = null;
            c = 1;

            System.out.println("\n\nINFORMATION REQUIRED\n--------------------");
            code = Utils.readLineFromConsole("Parameter Code: ");
            name = Utils.readLineFromConsole("Parameter Name: ");
            description = Utils.readLineFromConsole("Parameter Description: ");

            System.out.println("Category of the Parameter (Choose one of the following by typing the number showing on the left of the category you want):\n");
            for (ParameterCategory pc : store) {
                System.out.println(c + " - Code: " + pc.getCode() + " // Name: " + pc.getName());
                c++;
            }

            while (cat == null) {
                System.out.print("\nSelect the option you want: ");
                op = input.nextLine();

                if (Integer.parseInt(op) > store.size()) {
                    System.out.println("This category doesn't exist.");
                } else {
                    cat = store.get(Integer.parseInt(op) - 1);
                }
            }

            boolean resultcreate = true;
            try {
                par.createParameter(code, name, description, cat);
            } catch (Exception e) {
                resultcreate = false;
            }

            if (resultcreate) {
                System.out.println("\nPARAMETER DATA:\nCode: " + par.getList().getPr().getCode() + "\nName: " + par.getList().getPr().getName() + "\nDescription: " + par.getList().getPr().getDescription() + "\nParameter Category Code: " + par.getList().getPr().getCat().getCode() + "\nParameter Category Name: " + par.getList().getPr().getCat().getName() + "\n\n");

                boolean result = Utils.confirm("s -> confirm    n -> cancel");

                if (result) {
                    if (par.saveParameter()) {
                        System.out.println("Parameter created successfully");
                    } else {
                        System.out.println("Parameter wasn't saved, because it already exists");
                    }
                } else {
                    System.out.println("Operation canceled");
                }
            } else {
                System.out.println("Error with parameter creation!");
            }

        } else {
            System.out.println("Parameter Category list is empty");
        }
    }
}
*/

