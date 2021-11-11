package app.domain.model;

import app.domain.model.performance.PerformanceData;
import app.domain.model.performance.PerformanceReport;
import app.domain.model.stores.PerformanceReportStore;
import junit.framework.TestCase;
import org.junit.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class PerformanceReportStoreTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime dateTime = LocalDateTime.parse("2021/05/27 18:17:23", formatter);
    PerformanceData performanceData = new PerformanceData(dateTime, 25, 30, 35, 40);
    ArrayList<PerformanceData> basicList = new ArrayList<>(Arrays.asList(performanceData));
    double[][] growth = new double[4][4];
    int[] seq = {0,1,2,3};
    LocalDateTime[] dates = new LocalDateTime[]{LocalDateTime.parse("2021/05/27 18:17:23", formatter), LocalDateTime.parse("2021/05/27 18:17:23", formatter), LocalDateTime.parse("2021/05/27 18:17:23", formatter), LocalDateTime.parse("2021/05/27 18:17:23", formatter)};
    private PerformanceReportStore performanceReportStore = new PerformanceReportStore();
    PerformanceReport performanceReport = performanceReportStore.createPerformanceReport("000001", dates, basicList, growth, 5, seq, seq);



    @org.junit.Test
    public void testCreatePerformanceReport() {
        Assert.assertTrue(performanceReport instanceof PerformanceReport);
    }

    @org.junit.Test
    public void testValidatePerformanceReport() {
        Assert.assertTrue(performanceReportStore.validatePerformanceReport(performanceReport));
    }

    @org.junit.Test
    public void testSavePerformanceReport() {
        performanceReportStore.savePerformanceReport(performanceReport);
        PerformanceReport performanceReportGot = performanceReportStore.getLastPerformanceReport();
        Assert.assertEquals(performanceReportGot, performanceReport);
    }

    @org.junit.Test
    public void testGetPerformanceReportList() {
        performanceReportStore.savePerformanceReport(performanceReport);
        ArrayList list = (ArrayList) performanceReportStore.getPerformanceReportList();
        Assert.assertEquals(list.get(0),performanceReport);
    }

    @org.junit.Test
    public void testGetLastPerformanceReport() {
        performanceReportStore.savePerformanceReport(performanceReport);
        PerformanceReport performanceReportGot = performanceReportStore.getLastPerformanceReport();
        Assert.assertEquals(performanceReportGot, performanceReport);
    }
}