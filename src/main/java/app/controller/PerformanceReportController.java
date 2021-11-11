package app.controller;

import app.domain.model.*;
import app.domain.model.client.Client;
import app.domain.model.performance.*;
import app.domain.model.stores.*;
import app.domain.model.test.*;
import app.domain.shared.PerformanceAlgorithms;
import com.isep.mdis.Sum;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Class responsible for communicating with the UI when the user wants to create a new performance report
 * @author Wilson Jacoud 1200630
 */
public class PerformanceReportController {

    private final TestStore testStore;
    private final PerformanceReportStore performanceReportStore;
    private final ClientsStore clientsStore;
    private PerformanceReport performanceReport;

    DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
    LocalTime startTime = LocalTime.parse("08:00", tf);
    LocalTime endTime = LocalTime.parse("20:00", tf);
    LocalDateTime jr = LocalDateTime.

    /**
     * Constructor that initialize the company and calls for the test store and performance report store
     */
    public PerformanceReportController() {
        App app = App.getInstance();
        Company company = app.getCompany();
        testStore = company.getTestStore();
        performanceReportStore = company.getPerformanceReportStore();
        clientsStore = company.getClientStore();
    }


    /**
     * Public method to Start creation of the PerformanceReport object with Date param
     * @param startDateIn - Start date
     * @param endDateIn   - End Date
     * @param algorithm   - Integer to chose algorithm
     */
    public void createReport(Date startDateIn, Date endDateIn, int algorithm) {
        LocalDateTime startDate = LocalDateTime.of(convertToLocalDateViaInstant(startDateIn), startTime);
        LocalDateTime endDate = LocalDateTime.of(convertToLocalDateViaInstant(endDateIn), endTime);
        int[][] data = fillDataArray(startDate, endDate);
        createPerformanceReport(startDate, endDate, data, algorithm);
    }

    /**
     * Public method to Start creation of the PerformanceReport object with LOCALDATE param
     * @param startDateIn - Start date
     * @param endDateIn   - End Date
     * @param algorithm   - Integer to chose algorithm
     */
    public void createReport(LocalDate startDateIn, LocalDate endDateIn, int algorithm) {
        LocalDateTime startDate = LocalDateTime.of(startDateIn, startTime);
        LocalDateTime endDate = LocalDateTime.of(endDateIn, endTime);
        int[][] data = fillDataArray(startDate, endDate);
        createPerformanceReport(startDate, endDate, data, algorithm);
    }

    /**
     * Method to convert Date to Localdate
     * @param dateToConvert -  date
     */
    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * Method to count clients analyzed
     * @param startDate -  date
     * @param endDate - date
     */
    private int countClients(LocalDateTime startDate, LocalDateTime endDate) {
        HashMap<String, Client> clientsMap = new HashMap<>();
        for (Test test : testStore.getTests()) {
            if (!test.getDate().isBefore(startDate) && test.getDate().isBefore(endDate)){
                for (Client client : clientsStore.getClientList()) {
                    if (client.getTin().equals(test.getTin())) {
                        clientsMap.put(client.getTin(), client);
                    }
                }
            }
        }

        return clientsMap.size();
    }

    /**
     * Method to count events, organize and fill a matrix with this data
     * @param startDate - Start date
     * @param endDate   - End Date
     *
     * @return data - int matrix with new (tests/results/diagnostics/validations)Rows and (periods of 30 minutes)columns
     */
    private int[][] fillDataArray(LocalDateTime startDate, LocalDateTime endDate) {
        int periods = countPeriods(startDate, endDate);
        int[][] data = new int[4][periods];

        for (int[] row : data)
            Arrays.fill(row, 0);

        int registrationIndex;
        int resultIndex;
        int diagnosticIndex;
        int validationIndex;

        for (Test test : testStore.getTests()) {
            try {
                registrationIndex = periodIndex(startDate, endDate, test.getDate());
                resultIndex = periodIndex(startDate, endDate, test.analyzedDate());
                diagnosticIndex = periodIndex(startDate, endDate, test.getReport().getDate());
                if (test.getReport() == null)
                    diagnosticIndex = periodIndex(startDate, endDate, test.getValidationDate());
                validationIndex = periodIndex(startDate, endDate, test.getValidationDate());
            } catch (Exception e) {
                continue;
            }
            if (registrationIndex >= 0) {
                data[0][registrationIndex]++;
            }
            if (resultIndex >= 0) {
                data[1][resultIndex]++;
            }
            if (diagnosticIndex >= 0) {
                data[2][diagnosticIndex]++;
            }
            if (validationIndex >= 0) {
                data[3][validationIndex]++;
            }
        }

        return data;
    }

    /**
     * Method to count total periods (30 min each in commercial days)
     * @param start - Start date
     * @param end  - End Date
     *
     * @return data - Integer with number of total periods
     */
    private int countPeriods(LocalDateTime start, LocalDateTime end) {
        LocalDateTime temp = start;
        Duration tmpDuration = Duration.between(start, end);
        int days = (int) (tmpDuration.toDays() + 1);

        while (!temp.isAfter(end)) {
            if (temp.getDayOfWeek() == DayOfWeek.SUNDAY) {
                days--;
            }
            temp = temp.plusDays(1);
        }

        return days * 24;
    }

    /**
     * Method to find the related period index by the date
     * @param start - Start date
     * @param end  - End Date
     * @param tmp  - Date to find
     *
     * @return data - Integer with period index
     */
    private int periodIndex(LocalDateTime start, LocalDateTime end, LocalDateTime tmp) {
        long sundays = 0;

        for (LocalDateTime date = start; date.isBefore(tmp); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY)
                sundays++;
        }

        if (tmp.isBefore(start) || !tmp.isBefore(end) || tmp.getDayOfWeek() == DayOfWeek.SUNDAY)
            return -1;
        LocalTime tmpHour = tmp.toLocalTime();
        if (tmpHour.isBefore(startTime) || !tmpHour.isBefore(endTime))
            return -1;

        Duration tmpDuration = Duration.between(start, tmp);

        int hourIndex = (tmpHour.getHour() - startTime.getHour()) * 2;
        if (tmpHour.getMinute() >= 30)
            hourIndex++;

        return (int) ((tmpDuration.toDays() - sundays) * 24) + hourIndex;
    }

    /**
     * Private method create the PerformanceReport
     * @param startDate - Start date
     * @param endDate   - End Date
     * @param data      - Matrix with basic data per period
     * @param algorithm - Integer to chose algorithm
     */
    private void createPerformanceReport(LocalDateTime startDate, LocalDateTime endDate, int[][] data, int algorithm) {
        int periods = data[0].length;

        int[] testsMinusValidations = new int[periods];
        for (int i = 0; i < periods; i++) {
            testsMinusValidations[i] = data[0][i] - data[3][i];
        }
        int[] lessEffectiveSubSequence;

        if (algorithm == 0) {
            lessEffectiveSubSequence = PerformanceAlgorithms.bruteForceAlgorithm(testsMinusValidations);
        } else {
            lessEffectiveSubSequence = Sum.Max(testsMinusValidations);
        }
        int[] subSeqIndexes = PerformanceAlgorithms.findSubSeqIndexes(testsMinusValidations, lessEffectiveSubSequence);

        double[][] periodicGrowth = createPeriodicGrowth(data, periods);
        ArrayList<PerformanceData> basicPeriodicDataList = createBasicPeriodicDataList(startDate, data, periods);

        LocalDateTime dateLessEffectiveStart = basicPeriodicDataList.get(subSeqIndexes[0]).getDateTime();
        LocalDateTime dateLessEffectiveEnd = basicPeriodicDataList.get(subSeqIndexes[subSeqIndexes.length - 1]).getDateTime();
        Integer number = performanceReportStore.getPerformanceReportList().size() + 1;
        String numberStr = String.format("%06d", number);
        int totalClients = countClients(startDate, endDate);
        LocalDateTime[] dates = new LocalDateTime[]{startDate, endDate, dateLessEffectiveStart, dateLessEffectiveEnd};
        performanceReport = performanceReportStore.createPerformanceReport(numberStr, dates, basicPeriodicDataList, periodicGrowth, totalClients, testsMinusValidations, lessEffectiveSubSequence);
    }


    /**
     * Private method create the growth rate from one period to another
     * @param data      - basic data matrix
     * @param periods   - periods
     *
     * @return periodicGrowth
     */
    private double[][] createPeriodicGrowth(int[][] data, int periods) {
        double[][] periodicGrowth = new double[4][periods];

        for (double[] row : periodicGrowth) {
            row[0] = 0.0;
        }

        for (int i = 1; i < periods; i++) {
            for (int j = 0; j < 4; j++) {
                periodicGrowth[j][i] = (double) (data[j][i] - data[j][i - 1]) / data[j][i - 1];
            }
        }

        return periodicGrowth;
    }

    /**
     * Private method create an arraylist with PerformanceData objects
     * @param startDate  - start Date
     * @param data      - basic data matrix
     * @param periods   - periods
     *
     * @return basicPeriodicDataList
     */
    private ArrayList<PerformanceData> createBasicPeriodicDataList(LocalDateTime startDate, int[][] data, int periods) {
        LocalDateTime tempDate = startDate;
        ArrayList<PerformanceData> basicPeriodicDataList = new ArrayList<>();

        for (int i = 0; i < periods; i++) {
            PerformanceData performanceData = new PerformanceData(tempDate, data[0][i], data[1][i], data[2][i], data[3][i]);

            if (tempDate.getHour() >= 8 && tempDate.getHour() < 19) {
                tempDate = tempDate.plusMinutes(30);
            } else if (tempDate.getHour() == 19) {
                if (tempDate.getMinute() < 30) {
                    tempDate = tempDate.plusMinutes(30);
                } else {
                    LocalDate auxDate = tempDate.toLocalDate();
                    auxDate = auxDate.plusDays(1);
                    tempDate = LocalDateTime.of(auxDate, startTime);
                }
            }

            if (tempDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                tempDate = tempDate.plusDays(1);
            }

            basicPeriodicDataList.add(performanceData);
        }

        return basicPeriodicDataList;
    }

    /**
     * Asks the PerformanceReportStore object to save the performanceReport created
     *
     * @return performanceReportStore.savePerformanceReport(performanceReport)
     */
    public boolean savePerformanceReport() {
        return performanceReportStore.savePerformanceReport(performanceReport);
    }


    /**
     * Method to get a String PerformanceReport Summary
     * @return dataPerformanceReportSummary
     */
    public String getDataPerformanceReportSummary() {
        return performanceReport.dataPerformanceReportSummary();
    }

    /**
     * Method to get the PerformanceReportStore object
     * @return performanceReportStore
     */
    public PerformanceReportStore getList(){
        return performanceReportStore;
    }

    /**
     * Method to get the PerformanceReport object
     * @return performanceReport
     */
    public PerformanceReport getPerformanceReport() {
        return performanceReport;
    }
}
