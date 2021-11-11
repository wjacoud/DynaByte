package app.domain.model.stores;

import app.domain.model.performance.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Class responsible for the Performance Report management
 *
 * @author Wilson Jacoud 1200630
 */
public class PerformanceReportStore implements java.io.Serializable {
    private final List<PerformanceReport> performanceReportList;

    /**
     * Constructor that initialized the List
     */
    public PerformanceReportStore() { performanceReportList = new ArrayList<>(); }

    /**
     * Creates a new ParameterCategory object
     *
     * @param performanceReportNumber - The report number that identifies it in a unique way
     * @param dates - dates
     * @param basicPeriodicDataList - List of performance data objects with basic information
     * @param periodicGrowth - 2D Array with growth rate from one period to the next in basic periodic data list
     * @param totalClients - total of clients
     * @param analysisSequence - difference between tests and diagnostics sequence
     * @param subSeqMaxSum - subsequence with the maximum sum
     * @return - performanceReport = new PerformanceReport(performanceReportNumber, dateTimeStart, dateTimeEnd, dateLessEffectiveStart, dateLessEffectiveEnd, basicPeriodicDataList, periodicGrowth)
     */
    public PerformanceReport createPerformanceReport(String performanceReportNumber, LocalDateTime[] dates, List<PerformanceData> basicPeriodicDataList, double[][] periodicGrowth, int totalClients,  int[] analysisSequence, int[] subSeqMaxSum) {
        return new PerformanceReport(performanceReportNumber, dates, basicPeriodicDataList, periodicGrowth, totalClients, analysisSequence, subSeqMaxSum);
    }

    /**
     * Verifies if the Performance Report already exists in the list
     *
     * @param performanceReport - Performance Report
     * @return true - if the Performance Report doesn't exist yet, or false - if the Performance Report is null or it already exists
     */
    public boolean validatePerformanceReport(PerformanceReport performanceReport) {
        if (performanceReport == null)
            return false;
        for (PerformanceReport performanceReportTmp : performanceReportList) {
            if (performanceReport.getPerformanceReportNumber().equalsIgnoreCase(performanceReportTmp.getPerformanceReportNumber())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Saves the Performance Report in the list after verifying if it already exists or not
     *
     * @param performanceReport - Performance Report
     * @return true - if performanceReportList.add(performanceReport) if the validation of the Performance Report returns true, or false - if validation of the Performance Report returns false
     */
    public boolean savePerformanceReport(PerformanceReport performanceReport) {
        if (!validatePerformanceReport(performanceReport))
            return false;
        return performanceReportList.add(performanceReport);
    }

    /**
     * Method to get the List of Performance Report
     *
     * @return performanceReportList
     */
    public List<PerformanceReport> getPerformanceReportList() { return performanceReportList; }

    /**
     * Method to get the Last of List of Performance Report
     *
     * @return performanceReport
     */
    public PerformanceReport getLastPerformanceReport() { return performanceReportList.get(performanceReportList.size() - 1); }

}
