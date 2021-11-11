/*
package app.ui.console;

import app.controller.RecordSampleController;
import app.domain.model.client.Client;
import app.domain.model.testComponents.Parameter;
import app.domain.model.test.Test;
import app.ui.console.utils.Utils;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecordSampleUI implements Runnable {

    RecordSampleController rsc;
    Client client;

    public RecordSampleUI() {
        rsc = new RecordSampleController();
    }

    public void run() {

        List<Test> testList = rsc.getTestList();

        List<String> testDescription = new ArrayList<>();

        for (Test t : testList) {
            if (t.getState().equals(Test.State.REGISTERED)) {
                client = rsc.getClientFromTest(t);
                testDescription.add(t.getTestCode() + "/ " + client.getName());
            }
        }

        if (!testList.isEmpty()) {

            System.out.println("\n\nINFORMATION REQUIRED\n--------------------");

            String testID = (String) Utils.showAndSelectOne(testDescription, "Test List");

            if (testID == null) {
                System.out.println("Operation canceled");
                return;
            }
            testID = testID.substring(0, 12);

            Test test = rsc.getTestFromTestID(testID);

            client = rsc.getClientFromTest(test);

            int n = Utils.readIntegerFromConsole("How many samples do you want to record:");

            for (int i = 0; i < n; i++) {

                boolean result = false;
                try {
                    result = rsc.createSample(testID);
                } catch (BarcodeException e) {
                    e.printStackTrace();
                }

                if (result) {
                    System.out.println("\n\nSample/Test Data \n------------- \nTest code: " + testID + "\nNHS code: " + test.getNhsCode() + "\nTest Type: " + test.getTestTypeDescription() + "\nParameters: ");

                    for (Parameter p : test.getParameterList()) {
                        System.out.println("-" + p.getName());
                    }

                    System.out.println("Barcode: " + rsc.getBarcode() + "\nDate: " + rsc.getDate() + "\n\nCLIENT DATA\n-----------\n" + "Name: " + client.getName());
                } else {
                    System.out.println("Error with sample creation!");
                    return;
                }

                result = Utils.confirm("s -> confirm    n -> cancel");

                if (result) {
                    try {
                        result = rsc.saveSample();
                    } catch (OutputException | IOException e) {
                        e.printStackTrace();
                    }
                    rsc.setState(testID);
                } else {
                    System.out.println("\nOperation Canceled");
                    return;
                }

                if (result) {
                    System.out.println("\nSample saved with success!");
                } else {
                    System.out.println("\nError with sample save!");
                }

            }
        } else {
            System.out.println("You need to create a test first");
        }


    }
}*/
