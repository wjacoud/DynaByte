/*
package app.ui.console;

import app.controller.RecordTestResultController;
import app.domain.model.testComponents.Parameter;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class RecordTestResultUI implements Runnable {

    RecordTestResultController rtrc;

    public RecordTestResultUI() {
        rtrc = new RecordTestResultController();
    }

    public void run() {

        System.out.println("-------Information Required-------");
        String barcode = Utils.readLineFromConsole("Barcode: ");

        List<Parameter> parameterList = rtrc.getParameters(barcode);

        if (parameterList == null) {
            System.out.println("\nTest not found or already has results");
            return;
        }

        List<Double> value;
        int n;
        boolean result;
        boolean again;

        do {
            again = false;
            result = true;
            value = new ArrayList<>();
            n = 0;
            System.out.println("\n-------Parameters Information Required-------");
            for (Parameter parameter : parameterList) {
                System.out.print("\nParameter to Analyze: " + parameter.getName());

                value.add(Utils.readDoubleFromConsole("Value: "));

                try {
                    if(value.get(n) < 0){
                        result = false;
                    }else{
                        result = rtrc.addTestParameterResult(parameter.getCode(), value.get(n));
                        n++;
                    }
                } catch (Exception e) {
                    result = false;
                }

                if (result) {
                    System.out.println("\nParameter result saved with success!");
                } else {
                    System.out.println("\nError with parameter result record!\nDo you want to start recording results again?");
                    again = Utils.confirm("s -> confirm    n -> cancel");
                    break;
                }
            }
        } while (again);

        if(result) {
            System.out.println("\nConfirm the results:");
            n = 0;
            for (Parameter parameter : parameterList) {
                System.out.println(parameter.getName() + " = " + value.get(n));
                n++;
            }

            result = Utils.confirm("s -> confirm    n -> cancel");
        }


        if (result) {
            rtrc.setState(true);
            System.out.println("\nAll test results have been recorded!");
        } else {
            rtrc.setState(false);
            System.out.println("\nOperation canceled!");
        }
    }
}
*/

