/*
package app.ui.console;

import app.controller.PerformanceReportController;
import app.domain.model.performance.PerformanceData;
import app.ui.console.utils.Utils;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class PerformanceReportUI implements Runnable {

    PerformanceReportController performanceReportController;
    public PerformanceReportUI() { performanceReportController = new PerformanceReportController(); }

    public void run() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        System.out.println("Performance Report Menu\n");
        Date startDate = Utils.readDateFromConsole("Insert start date of the analysis:\"dd-MM-yyyy\"");
        Date endtDate = Utils.readDateFromConsole("Insert end date of the analysis:\"dd-MM-yyyy\"");
        ArrayList<String> options = new ArrayList<>();
        options.add("brute-force algorithm");
        options.add("Benchmark algorithm");
        int selection = Utils.showAndSelectIndex(options, "Select an analysis algorithm:");
        performanceReportController.createReport(startDate, endtDate, selection);
        System.out.println();
        System.out.println("Performance Report :" + performanceReportController.getPerformanceReport().getPerformanceReportNumber());
        System.out.println("Start date of analysis: " + performanceReportController.getPerformanceReport().getDateTimeStart().format(dtf));
        System.out.println("End date of analysis: " + performanceReportController.getPerformanceReport().getDateTimeEnd().format(dtf));
        System.out.println("Start date of the less effective period during the analysis: " + performanceReportController.getPerformanceReport().getDateLessEffectiveStart().format(dtf));
        System.out.println("End date of the less effective period during the analysis: " + performanceReportController.getPerformanceReport().getDateLessEffectiveEnd().format(dtf));
//        int i = 0;
//        for (PerformanceData basicData : performanceReportController.getPerformanceReport().getBasicPeriodicDataList()) {
//            System.out.println();
//            System.out.println(basicData.getDateTime().format(dtf)+"\n  New Tests: "+basicData.getNewTests()+"  New Results: "+basicData.getNewResults()+"  New Diagnostics: "+basicData.getNewDiagnostics()+"  New Validations: "+basicData.getNewValidations());
//            System.out.println();
//            System.out.println("Growth Rate:\n"+"\n  New Tests: "+performanceReportController.getPerformanceReport().getPeriodicGrowth()[0][i]+"  New Results: "+performanceReportController.getPerformanceReport().getPeriodicGrowth()[1][i]+"  New Diagnostics: "+performanceReportController.getPerformanceReport().getPeriodicGrowth()[2][i]+"  New Validations: "+performanceReportController.getPerformanceReport().getPeriodicGrowth()[3][i]);
//            i++;
//            System.out.println(i);
//        }
        if (Utils.confirm("Save report? (s/n)")) {
            if (performanceReportController.savePerformanceReport()) {
                System.out.println("Report successfully saved");
            }
        }
        System.out.println();
        System.out.println("sucess");
    }
}
*/

