/*
package app.ui.console;

import app.controller.CreateClinicalController;
import app.domain.model.testComponents.TestType;
import app.ui.console.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateClinicalUI implements Runnable {

    CreateClinicalController ccc;

    public CreateClinicalUI() {
        ccc = new CreateClinicalController();
    }

    public void run() {
        Scanner input = new Scanner(System.in);

        System.out.println("\n\nINFORMATION REQUIRED\n--------------------");
        String idLaboratory = Utils.readLineFromConsole("Laboratory ID: ");
        String name = Utils.readLineFromConsole("Name: ");
        String phoneNumber = Utils.readLineFromConsole("Phone number: ");
        String address = Utils.readLineFromConsole("Address: ");
        String tin = Utils.readLineFromConsole("Tin: ");

        List<TestType> testTypesList = ccc.getTestType();

        int n;
        System.out.println("How many tests will the clinical have?");
        n = input.nextInt();
        String[] tests = new String[n];
        tests[0] = "Blood Test";

        List<String> testDescription = new ArrayList<>();

        for (TestType tp : testTypesList) {
            testDescription.add(tp.getDescription());
        }

        for (int c = 1; c < n; c++) {
            tests[c] = (String) Utils.showAndSelectOne(testDescription, "Test Type List");
        }

        for(int c=0 ; c<n;c++) {
            if (tests[c] == null) {
                System.out.println("Operation canceled");
                return;
            }
        }
        boolean result = false;
        boolean duplicates = false;
        for (int j = 0; j < tests.length; j++)
            for (int k = j + 1; k < tests.length; k++)
                if (k != j && tests[k].equals(tests[j]))
                    duplicates = true;
        if (!duplicates) {
            try {
                result = ccc.createClinical(idLaboratory, name, address, tin, phoneNumber, tests);
            } catch (Exception e) {
                result = false;
            }
        } else {
            System.out.print("There cannot be repeated tests!");
            result = false;
        }
        if (result) {
            System.out.print("\nCLINICAL DATA\n-------------\nLaboratory ID: "+ idLaboratory + "\nName: "+ name + "\nPhone Number: " + phoneNumber + "\nAddress: " + address + "\nTin: "+tin+ "\nTests: ");
            for (int c = 0; c < n; c++) {
                System.out.print(tests[c] + "; ");
            }

            result = Utils.confirm("s -> confirm    n -> cancel");

            if (result) {
                result = ccc.saveClinical();
            } else {
                System.out.println("\nOperation Canceled");
                return;
            }


            if (result) {
                System.out.println("Clinical saved with success!\n\n");
            } else {
                System.out.println("Error with clinical save!");
            }
        } else {
            System.out.println("Error with clinical creation!");
        }

    }
}
*/

