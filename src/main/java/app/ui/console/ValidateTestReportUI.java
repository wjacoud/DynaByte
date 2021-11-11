/*
package app.ui.console;

import app.controller.ValidateTestReportController;
import app.domain.model.test.TestReport;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class ValidateTestReportUI implements Runnable {

    ValidateTestReportController vtrc;

    public ValidateTestReportUI() {
        vtrc = new ValidateTestReportController();
    }

    public void run() {

        List<TestReport> testReportsToValidate = new ArrayList<>(vtrc.testReportsToValidate());

        if (testReportsToValidate.isEmpty()) {
            System.out.println("There are no available tests for validation!");
            return;
        }

        int option = 1;
        for (TestReport testReport : testReportsToValidate) {
            System.out.println(option + " - Test Code: " + testReport.getIdTest() + " - Date: " + testReport.getDate() + "\nReport: " + testReport.getReport() + "\nDiagnosis: " + testReport.getDiagnosis().getDiagnosis());
            option++;
        }
        System.out.println(option + " - SELECT ALL\n0 - Cancel");

        do {
            option = Utils.readIntegerFromConsole("Select :");
            if (option < 0 || option > testReportsToValidate.size() + 1)
                System.out.println("Invalid option!");
        } while (option < 0 || option > testReportsToValidate.size() + 1);

        if (option == 0)
            return;

        if (option == testReportsToValidate.size() + 1) {
            System.out.println("Validate all available tests?");
        } else {
            System.out.println("Validate test (" + testReportsToValidate.get(option - 1).getIdTest() + ")?");
        }
        boolean result = Utils.confirm("s -> confirm    n -> cancel");
        if (result) {
            if (option == testReportsToValidate.size() + 1) {
                vtrc.validateAllTestReport();
            } else {
                vtrc.validateTestReport(testReportsToValidate.get(option - 1).getIdTest());
            }
        }

    }
}*/
