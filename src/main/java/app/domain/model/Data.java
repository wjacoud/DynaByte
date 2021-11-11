package app.domain.model;

import app.domain.shared.exceptions.*;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.Period;

/**
 * Class responsible for saving data information
 *
 * @author Ricardo Faria 1201405 and Rodrigo Oliveira 1201406
 */
public class Data implements java.io.Serializable {

    private String historicalDays;
    private LocalDate intervalStartDate;
    private LocalDate intervalEndDate;

    private double significanceLevelAnova;
    private double confidenceLevelIC;
    private int numberDays;

    /**
     * empty constructor
     */
    public Data() {

    }

    /**
     * Set the information
     *
     * @param historicalDays         historical days
     * @param confidenceLevelIC      confidence level
     * @param start                  start date
     * @param end                    end state
     * @param significanceLevelAnova significance level of anova
     */
    public void setInformation(String historicalDays, String confidenceLevelIC, LocalDate start, LocalDate end, String significanceLevelAnova) {
        checkHistoricalDays(historicalDays);
        this.historicalDays = historicalDays;


        checkLevel(confidenceLevelIC);
        this.confidenceLevelIC = Double.parseDouble(confidenceLevelIC);

        this.intervalStartDate = start;

        this.intervalEndDate = end;

        checkLevel(significanceLevelAnova);
        this.significanceLevelAnova = Double.parseDouble(significanceLevelAnova);

        setNumberDays();
    }

    /**
     * set the number of days
     */
    private void setNumberDays() {
        numberDays = Period.between(intervalStartDate, intervalEndDate).getDays();
        checkIntervalDates(numberDays);
    }

    /**
     * return the historical days
     *
     * @return historical days
     */
    public int getHistoricalDaysInt() {
        return Integer.parseInt(historicalDays);
    }

    /**
     * return the confidence level
     *
     * @return the confidence level
     */
    public double getConfidenceLevelIC() {
        return confidenceLevelIC;
    }

    /**
     * return the interval end date
     *
     * @return the interval end date
     */
    public LocalDate getIntervalEndDate() {
        return intervalEndDate;
    }

    /**
     * return the interval start date
     *
     * @return the interval start date
     */
    public LocalDate getIntervalStartDate() {
        return intervalStartDate;
    }

    /**
     * return the significance level of anova
     *
     * @return the significance level of anova
     */
    public double getSignificanceLevelAnova() {
        return significanceLevelAnova;
    }

    /**
     * return the number of days
     *
     * @return the number of days
     */
    public int getNumberDays() {
        return numberDays;
    }

    /**
     * check if the confidence level is in the right format
     *
     * @param confidenceLevelIC confidence level
     */
    private void checkLevel(String confidenceLevelIC) {
        if (confidenceLevelIC.isEmpty() || StringUtils.isBlank(confidenceLevelIC)) {
            throw new LevelException("The level cannot be blank");
        }

        double n = Double.parseDouble(confidenceLevelIC);

        if (n > 100 || n < 0) {
            throw new LevelException("The level needs to be between 0 and 100");
        }
    }

    /**
     * check if the interval dates is in the right format
     *
     * @param date interval dates
     */
    private void checkIntervalDates(long date) {
        if (date <= 0) {
            throw new DateException("Interval of dates invalid");
        }
    }

    /**
     * check if the historic days is in the right format
     *
     * @param historicalDays historic days
     */
    private void checkHistoricalDays(String historicalDays) {
        if (historicalDays.isEmpty() || StringUtils.isBlank(historicalDays)) {
            throw new HistorialDaysException("The historical days cannot be blank");
        }

        int n = Integer.parseInt(historicalDays);

        if (n > 365 || n < 1) {
            throw new HistorialDaysException("Historical days invalid number");
        }
    }
}