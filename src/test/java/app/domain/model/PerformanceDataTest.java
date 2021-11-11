package app.domain.model;

import app.domain.model.performance.PerformanceData;
import junit.framework.TestCase;
import org.junit.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PerformanceDataTest extends TestCase {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime dateTime = LocalDateTime.parse("2021/05/27 18:17:23", formatter);
    PerformanceData performanceData = new PerformanceData(dateTime, 25, 30, 35, 40);

    @org.junit.Test
    public void testGetDateTime() {
        LocalDateTime expected = LocalDateTime.parse("2021/05/27 18:17:23", formatter);
        Assert.assertEquals(expected, performanceData.getDateTime());
    }

    @org.junit.Test
    public void testGetNewTests() {
        int expected = 25;
        Assert.assertEquals(expected, performanceData.getNewTests());
    }

    @org.junit.Test
    public void testGetNewResults() {
        int expected = 30;
        Assert.assertEquals(expected, performanceData.getNewResults());
    }

    @org.junit.Test
    public void testGetNewDiagnostics() {
        int expected = 35;
        Assert.assertEquals(expected, performanceData.getNewDiagnostics());
    }

    @org.junit.Test
    public void testGetNewValidations() {
        int expected = 40;
        Assert.assertEquals(expected, performanceData.getNewValidations());
    }
}