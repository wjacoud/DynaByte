package app.domain.model;

import app.domain.shared.exceptions.DateException;
import app.domain.shared.exceptions.HistorialDaysException;
import app.domain.shared.exceptions.LevelException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

import static org.junit.Assert.*;

public class DataTest {

    Data data = new Data();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate startDay = LocalDate.parse("14/07/2018", dateTimeFormatter);
    LocalDate endDay = LocalDate.parse("20/07/2018", dateTimeFormatter);

    @Test
    public void setInformationCorrect() {
        data.setInformation("10", "10", startDay, endDay, "10");
    }

    @Test(expected = HistorialDaysException.class)
    public void setInformationHistoricWrong() {
        data.setInformation("", "10", startDay, endDay, "10");
    }

    @Test(expected = LevelException.class)
    public void setInformationConfidenceWrong() {
        data.setInformation("10", "", startDay, endDay, "10");
    }

    @Test(expected = LevelException.class)
    public void setInformationSignificanceWrong() {
        data.setInformation("10", "10", startDay, endDay, "");
    }

    @Test(expected = DateException.class)
    public void setInformationDaysWrong() {
        startDay = LocalDate.parse("24/07/2018", dateTimeFormatter);
        endDay = LocalDate.parse("20/07/2018", dateTimeFormatter);
        data.setInformation("10", "10", startDay, endDay, "10");
    }

    @Test
    public void getHistoricalDaysCorrect() {
        int expected = 10;
        data.setInformation("10", "10", startDay, endDay, "10");
        int result = data.getHistoricalDaysInt();

        Assert.assertTrue(expected == result);
    }

    @Test
    public void getHistoricalDaysWrong() {
        int expected = 15;
        data.setInformation("10", "10", startDay, endDay, "10");
        int result = data.getHistoricalDaysInt();

        Assert.assertFalse(expected == result);
    }

    @Test
    public void getConfidenceCorrect() {
        double expected = 10;
        data.setInformation("10", "10", startDay, endDay, "10");
        double result = data.getConfidenceLevelIC();

        Assert.assertTrue(expected == result);
    }

    @Test
    public void getConfidenceWrong() {
        double expected = 15;
        data.setInformation("10", "10", startDay, endDay, "10");
        double result = data.getConfidenceLevelIC();

        Assert.assertFalse(expected == result);
    }

    @Test
    public void getSignificanceCorrect() {
        double expected = 10;
        data.setInformation("10", "10", startDay, endDay, "10");
        double result = data.getSignificanceLevelAnova();

        Assert.assertTrue(expected == result);
    }

    @Test
    public void getSignificanceWrong() {
        double expected = 15;
        data.setInformation("10", "10", startDay, endDay, "10");
        double result = data.getSignificanceLevelAnova();

        Assert.assertFalse(expected == result);
    }

    @Test
    public void getDaysCorrect() {
        int expected = 6;
        data.setInformation("10", "10", startDay, endDay, "10");
        int result = data.getNumberDays();

        Assert.assertTrue(expected == result);
    }

    @Test
    public void getDaysWrong() {
        int expected = 10;
        data.setInformation("10", "10", startDay, endDay, "10");
        int result = data.getNumberDays();

        Assert.assertFalse(expected == result);
    }

    @Test
    public void getStartCorrect() {
        data.setInformation("10", "10", startDay, endDay, "10");
        LocalDate result = data.getIntervalStartDate();

        Assert.assertEquals(startDay, result);
    }

    @Test
    public void getStartWrong() {
        data.setInformation("10", "10", startDay, endDay, "10");
        LocalDate result = data.getIntervalStartDate();

        Assert.assertNotEquals(endDay, result);
    }

    @Test
    public void getEndCorrect() {
        data.setInformation("10", "10", startDay, endDay, "10");
        LocalDate result = data.getIntervalEndDate();

        Assert.assertEquals(endDay, result);
    }

    @Test
    public void getEndWrong() {
        data.setInformation("10", "10", startDay, endDay, "10");
        LocalDate result = data.getIntervalEndDate();

        Assert.assertNotEquals(startDay, result);
    }

    @Test(expected = LevelException.class)
    public void checkLevelMore100() {
        data.setInformation("10", "101", startDay, endDay, "10");
    }

    @Test
    public void checkLevel100() {
        data.setInformation("10", "100", startDay, endDay, "10");
    }

    @Test
    public void checkLevel0() {
        data.setInformation("10", "0", startDay, endDay, "10");
    }

    @Test(expected = LevelException.class)
    public void checkTestLess0() {
        data.setInformation("10", "-3", startDay, endDay, "10");
    }

    @Test(expected = HistorialDaysException.class)
    public void checkHistoricalDaysMore365() {
        data.setInformation("400", "10", startDay, endDay, "10");
    }

    @Test(expected = HistorialDaysException.class)
    public void checkHistoricalDaysLess1() {
        data.setInformation("-3", "10", startDay, endDay, "10");
    }

    @Test
    public void checkHistoricalDays365() {
        data.setInformation("365", "10", startDay, endDay, "10");
    }

    @Test
    public void checkHistoricalDays1() {
        data.setInformation("1", "10", startDay, endDay, "10");
    }

    @Test(expected = DateException.class)
    public void checkDateIntervals() {
        data.setInformation("1", "10", startDay, startDay, "10");
    }
}