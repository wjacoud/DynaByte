/*
package app.ui.console;

import app.controller.*;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class LabCoordinatorUI implements Runnable {

    public void run() {

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Validate test report ", new ValidateTestReportUI()));
        options.add(new MenuItem("Import tests from file ", new ImportTestsUI()));
        options.add(new MenuItem("Run Performance Analysis ", new PerformanceReportUI()));
        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nLaboratory coordinator Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);

    }
}

*/

