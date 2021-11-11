package app.domain.model.performance;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * class responsible for the Performance Report
 *
 * @author Wilson Junior 1200630
 */
public class PerformanceReport implements java.io.Serializable {

    private final String performanceReportNumber;
    private final LocalDateTime dateTimeStart;
    private final LocalDateTime dateTimeEnd;
    private final LocalDateTime dateLessEffectiveStart;
    private final LocalDateTime dateLessEffectiveEnd;
    private final int totalClients;
    private final int totalTests;
    private final int totalResults;
    private final int totalDiagnostics;
    private final int totalValidations;
    private final ArrayList<PerformanceData> basicPeriodicDataList;
    private final double[][] periodicGrowth; // tests, reports, diagnostics, validations
    private final int[] analysisSequence;
    private final int[] subSeqMaxSum;

    /**
     * Constructor that create a full Performance Report object
     *
     * @param performanceReportNumber   - Unique identification number
     * @param dates             - dates
     * @param basicPeriodicDataList     - List of Performance Data objects
     * @param periodicGrowth            - Matrix of Performance Data growth from one period to another
     * @param totalClients              - total clients
     * @param analysisSequence          - sequence
     * @param subSeqMaxSum              - sub sequence of max sum
     */
    public PerformanceReport(String performanceReportNumber, LocalDateTime[] dates, List<PerformanceData> basicPeriodicDataList, double[][] periodicGrowth, int totalClients, int[] analysisSequence, int[] subSeqMaxSum) {
        this.performanceReportNumber = performanceReportNumber;
        this.dateTimeStart = dates[0];
        this.dateTimeEnd = dates[1];
        this.dateLessEffectiveStart = dates[2];
        this.dateLessEffectiveEnd = dates[3];
        this.basicPeriodicDataList = (ArrayList<PerformanceData>) basicPeriodicDataList;
        this.periodicGrowth = periodicGrowth;
        int[] totalBasicData = countBasicTotalData();
        this.totalTests = totalBasicData[0];
        this.totalResults = totalBasicData[1];
        this.totalDiagnostics = totalBasicData[2];
        this.totalValidations = totalBasicData[3];
        this.totalClients = totalClients;
        this.analysisSequence = analysisSequence;
        this.subSeqMaxSum = subSeqMaxSum;
    }

    /**
     * Method to get the performance report number
     *
     * @return performanceReportNumber
     */
    public String getPerformanceReportNumber() { return performanceReportNumber; }

    /**
     * Method to get the date time of performance report start
     *
     * @return dateTimeStart
     */
    public LocalDateTime getDateTimeStart() { return dateTimeStart; }

    /**
     * Method to get the date time of performance report end
     *
     * @return dateTimeEnd
     */
    public LocalDateTime getDateTimeEnd() { return dateTimeEnd; }

    /**
     * Method to get the date time of less effective period start
     *
     * @return dateLessEffectiveStart
     */
    public LocalDateTime getDateLessEffectiveStart() { return dateLessEffectiveStart; }

    /**
     * Method to get the date time of less effective period end
     *
     * @return dateLessEffectiveEnd
     */
    public LocalDateTime getDateLessEffectiveEnd() { return dateLessEffectiveEnd; }

    /**
     * Method to get the List of Performance Data objects
     *
     * @return basicPeriodicDataList
     */
    public List<PerformanceData> getBasicPeriodicDataList() { return basicPeriodicDataList; }

    /**
     * Method to get the matrix of performance data growth
     *
     * @return periodicGrowth
     */
    public double[][] getPeriodicGrowth() { return periodicGrowth; }

    /**
     * Method to get total tests
     *
     * @return totalTests
     */
    public int getTotalTests() { return totalTests; }

    /**
     * Method to get total results
     *
     * @return totalResults
     */
    public int getTotalResults() { return totalResults; }

    /**
     * Method to get total Diagnostics
     *
     * @return totalDiagnostics
     */
    public int getTotalDiagnostics() { return totalDiagnostics; }

    /**
     * Method to get total Validations
     *
     * @return totalValidations
     */
    public int getTotalValidations() { return totalValidations; }

    /**
     * Method to get total clients
     *
     * @return totalClients
     */
    public int getTotalClients() { return totalClients; }

    /**
     * Method to get AnalysisSequence
     *
     * @return AnalysisSequence
     */
    public int[] getAnalysisSequence() { return analysisSequence; }

    /**
     * Method to get subSeqMaxSum
     *
     * @return subSeqMaxSum
     */
    public int[] getSubSeqMaxSum() { return subSeqMaxSum; }

    private int[] countBasicTotalData() {
        int periods = getBasicPeriodicDataList().size();
        int[] newTestRepDiaValid = new int[4];
        for (int i = 0; i < periods; i++) {
            newTestRepDiaValid[0] += getBasicPeriodicDataList().get(i).getNewTests();
            newTestRepDiaValid[1] += getBasicPeriodicDataList().get(i).getNewResults();
            newTestRepDiaValid[2] += getBasicPeriodicDataList().get(i).getNewDiagnostics();
            newTestRepDiaValid[3] += getBasicPeriodicDataList().get(i).getNewValidations();
        }
        return newTestRepDiaValid;
    }

    /**
     * Method to get the performance Report Summary
     *
     * @return report summary
     */
    public String dataPerformanceReportSummary() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        int periods = getBasicPeriodicDataList().size();
        int commercialDays = periods/24;
        return "Report summary" + "\n\nStart Date: " + getDateTimeStart().format(dtf) +
                "\nEnd Date: " + getDateTimeEnd().format(dtf) +
                "\nTotal days analyzed (Commercial days): " + commercialDays +
                "\n\nLess Effective Period Start Date: " + getDateLessEffectiveStart().format(dtf) +
                "\nLess Effective Period End Date: " + getDateLessEffectiveEnd().format(dtf) +
                "\n\nSequence of tests minus diagnostics: " + Arrays.toString(getAnalysisSequence()) +
                "\nSubsequence of maximum sum: " + Arrays.toString(getSubSeqMaxSum()) +
                "\n\nTotal Clients: " + totalClients +
                "\n\nTotal incoming tests: " + totalTests +
                "\n\nTotal test results: " + totalResults +
                "\nTests waiting for results: " + (totalTests-totalResults) +
                "\n\nTotal test Diagnostics: " + totalDiagnostics +
                "\nTests waiting for diagnostics: " + (totalTests-totalDiagnostics) +
                "\n\nTotal test Validations: " + totalValidations;
    }
}