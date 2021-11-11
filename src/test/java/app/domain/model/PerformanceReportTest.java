package app.domain.model;

import app.domain.model.performance.PerformanceData;
import app.domain.model.performance.PerformanceReport;
import junit.framework.TestCase;
import org.junit.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class PerformanceReportTest extends TestCase {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime dateTime = LocalDateTime.parse("2021/05/27 18:17:23", formatter);
    PerformanceData performanceData = new PerformanceData(dateTime, 25, 30, 35, 40);
    ArrayList<PerformanceData> basicList = new ArrayList<>(Arrays.asList(performanceData));
    double[][] growth = new double[4][4];
    int[] seq = {0,1,2,3};
    LocalDateTime[] dates = new LocalDateTime[]{LocalDateTime.parse("2021/05/27 18:17:23", formatter), LocalDateTime.parse("2021/05/27 18:17:23", formatter), LocalDateTime.parse("2021/05/27 18:17:23", formatter), LocalDateTime.parse("2021/05/27 18:17:23", formatter)};
    PerformanceReport performanceReport = new PerformanceReport("000001", dates, basicList, growth, 5, seq, seq);

    String Summary = "Report summary\n" +
            "\n" +
            "Start Date: 27-05-2021 18:17\n" +
            "End Date: 27-05-2021 18:17\n" +
            "Total days analyzed (Commercial days): 0\n" +
            "\n" +
            "Less Effective Period Start Date: 27-05-2021 18:17\n" +
            "Less Effective Period End Date: 27-05-2021 18:17\n" +
            "\n" +
            "Sequence of tests minus diagnostics: [0, 1, 2, 3]\n" +
            "Subsequence of maximum sum: [0, 1, 2, 3]\n" +
            "\n" +
            "Total Clients: 5\n" +
            "\n" +
            "Total incoming tests: 25\n" +
            "\n" +
            "Total test results: 30\n" +
            "Tests waiting for results: -5\n" +
            "\n" +
            "Total test Diagnostics: 35\n" +
            "Tests waiting for diagnostics: -10\n" +
            "\n" +
            "Total test Validations: 40";

    @org.junit.Test
    public void testGetPerformanceReportNumber() {
        String expected = "000001";
        Assert.assertEquals(expected, performanceReport.getPerformanceReportNumber());
    }

    @org.junit.Test
    public void testGetDateTimeStart() {
        LocalDateTime expected = LocalDateTime.parse("2021/05/27 18:17:23", formatter);
        Assert.assertEquals(expected, performanceReport.getDateTimeStart());
    }

    @org.junit.Test
    public void testGetDateTimeEnd() {
        LocalDateTime expected = LocalDateTime.parse("2021/05/27 18:17:23", formatter);
        Assert.assertEquals(expected, performanceReport.getDateTimeEnd());
    }

    @org.junit.Test
    public void getBasicPeriodicDataList() {
        LocalDateTime expected = LocalDateTime.parse("2021/05/27 18:17:23", formatter);
        Assert.assertNotNull(performanceReport.getBasicPeriodicDataList());
    }

    @org.junit.Test
    public void getSubSeqMaxSum() {
        LocalDateTime expected = LocalDateTime.parse("2021/05/27 18:17:23", formatter);
        Assert.assertNotNull(performanceReport.getSubSeqMaxSum());
    }


    @org.junit.Test
    public void testGetDateLessEffectiveStart() {
        LocalDateTime expected = LocalDateTime.parse("2021/05/27 18:17:23", formatter);
        Assert.assertEquals(expected, performanceReport.getDateLessEffectiveStart());
    }

    @org.junit.Test
    public void testGetDateLessEffectiveEnd() {
        LocalDateTime expected = LocalDateTime.parse("2021/05/27 18:17:23", formatter);
        Assert.assertEquals(expected, performanceReport.getDateLessEffectiveEnd());
    }

    @org.junit.Test
    public void testGetBasicPeriodicDataList() {
        Assert.assertEquals(basicList, performanceReport.getBasicPeriodicDataList());
    }

    @org.junit.Test
    public void testGetPeriodicGrowth() {
        Assert.assertEquals(growth, performanceReport.getPeriodicGrowth());
    }

    @org.junit.Test
    public void testGetTotalTests() {
        Assert.assertEquals(25, performanceReport.getTotalTests());
    }

    @org.junit.Test
    public void testGetTotalResults() {
        Assert.assertEquals(30, performanceReport.getTotalResults());
    }

    @org.junit.Test
    public void testGetTotalDiagnostics() {
        Assert.assertEquals(35, performanceReport.getTotalDiagnostics());
    }

    @org.junit.Test
    public void testGetTotalValidations() {
        Assert.assertEquals(40, performanceReport.getTotalValidations());
    }

    @org.junit.Test
    public void testGetTotalClients() {
        Assert.assertEquals(5, performanceReport.getTotalClients());
    }

    @org.junit.Test
    public void testGetAnalysisSequence() {
        Assert.assertEquals(seq, performanceReport.getAnalysisSequence());
    }

    @org.junit.Test
    public void testGetSubSeqMaxSum() {
        Assert.assertEquals(seq, performanceReport.getSubSeqMaxSum());
    }

    @org.junit.Test
    public void testDataPerformanceReportSummary() {
        Assert.assertEquals(Summary, performanceReport.dataPerformanceReportSummary());
    }
}