package app.controller;

import app.domain.model.Company;
import app.domain.model.Data;
import app.domain.model.client.Client;
import app.domain.model.test.Test;
import app.domain.model.test.TestParameter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

/**
 * Class responsible for creating the vectors
 *
 * @author Ricardo Faria 1201405 and Rodrigo Oliveira 1201406
 */
public class LinearRegressionController {
    private Data data = App.getInstance().getCompany().getData();
    private LocalDate todayDate = LocalDate.now();
    private LocalDate beginDate;

    private List<Client> clientList;
    private List<Test> testList;
    private List<Test> tempValidatedTests = new ArrayList<>();
    private List<Test> validatedTests = new ArrayList<>();
    private List<Test> positiveCovidTests = new ArrayList<>();
    private List<Test> validTestInsideInterval = new ArrayList<>();
    private List<Test> positiveCovidTestInsideInterval = new ArrayList<>();
    private List<Test> positiveCovidTestsPerDayHistorical = new ArrayList<>();
    private List<Test> covidTestsPerDayHistorical = new ArrayList<>();

    private int size = data.getHistoricalDaysInt();
    private double[] positiveCovidTestsPerDayHistoric = new double[size];
    private double[] covidTestsPerDayInterval = new double[size];
    private double[] positiveCovidTestsPerDayInterval = new double[size];
    private double[] ages = new double[size];
    private double[] covidTestsPerDayInsideTheIntervalOfDates = new double[size];
    private double[] covidTestsPerDayHistoric = new double[size];

    /**
     * constructor that calculates all vectors
     */
    public LinearRegressionController() {
        Company company = App.getInstance().getCompany();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -data.getHistoricalDaysInt());
        Date toDate = calendar.getTime();
        beginDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        clientList = company.getClientStore().getClientList();
        testList = company.getTestStore().getTests();
        testList = organizeTestList(testList);

        for (Test test : testList) {
            if (test.getState().equals(Test.State.VALIDATED)) {
                tempValidatedTests.add(test);
            }
        }
        tempValidatedTests = organizeTestList(tempValidatedTests);

        validatedTests();
        positiveCovidTests();
        validTestInsideInterval();
        positiveCovidTestInsideInterval();
        positiveCovidTestsPerDayHistorical();
        positiveCovidTestsPerDayHistoric();
        covidTestsPerDayInterval();
        positiveCovidTestsPerDayInterval();
        ages();
        covidTestsPerDayInsideTheIntervalOfDates();
        covidTestsPerDayHistorical();
        covidTestsPerDayHistoric();
    }

    /**
     * Method used to get the data of the day in the interval
     *
     * @param n - day
     * @return - data of the day in the interval
     */
    private LocalDate getCurrentDayInsideInterval(int n) {
        int startDayInterval = Period.between(data.getIntervalStartDate(), todayDate).getDays();
        int interW = startDayInterval - n;

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -interW);
        Date toDate = calendar.getTime();

        return toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Method used to organize a list of tests
     *
     * @param testList - list of tests
     * @return organized list of tests
     */
    public List<Test> organizeTestList(List<Test> testList) {
        Comparator<Test> criteria1 = new Comparator<Test>() {
            @Override
            public int compare(Test t1, Test t2) {
                Date date1 = null;
                Date date2 = null;

                String sDate1 = t1.getDateStr();
                try {
                    date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String sDate2 = t2.getDateStr();
                try {
                    date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                assert date1 != null;
                if (date1.before(date2)) {
                    return 1;
                } else if (date1.after(date2)) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        testList.sort(criteria1);

        return testList;
    }

    /**
     * Method used to add to a list all of the validated tests
     */
    public void validatedTests() {
        for (Test test : tempValidatedTests) {
            LocalDate testDate = test.getDate().toLocalDate();
            if (Period.between(beginDate, testDate).getDays() >= 0 && Period.between(testDate, todayDate).getDays() >= 0) {
                validatedTests.add(test);
            }
        }
        validatedTests = organizeTestList(validatedTests);
    }

    /**
     * Method used to add to a list all of the positive covid tests
     */
    public void positiveCovidTests() {
        for (Test test : validatedTests) {
            for (TestParameter testParameter : test.getTestParameterList()) {
                if (testParameter != null) {
                    if (testParameter.getParameter().getCode().equals("IgGAN") && testParameter.getTestParameterResult().getResult() > 1.4) {
                        positiveCovidTests.add(test);
                    }
                }
            }
        }
        positiveCovidTests = organizeTestList(positiveCovidTests);
    }

    /**
     * Method used to add to a list all positive covid tests per historical day
     */
    public void positiveCovidTestsPerDayHistorical() {
        for (Test test : tempValidatedTests) {
            for (TestParameter testParameter : test.getTestParameterList()) {
                if (testParameter != null) {
                    if (testParameter.getParameter().getCode().equals("IgGAN") && testParameter.getTestParameterResult().getResult() > 1.4) {
                        positiveCovidTestsPerDayHistorical.add(test);
                    }
                }
            }
        }
        positiveCovidTestsPerDayHistorical = organizeTestList(positiveCovidTestsPerDayHistorical);
    }

    /**
     * Method used to add to a list all validated tests in a interval of days
     */
    public void validTestInsideInterval() {
        for (Test test : tempValidatedTests) {
            LocalDate testDate = test.getDate().toLocalDate();
            if (Period.between(data.getIntervalStartDate(), testDate).getDays() >= 0 && Period.between(testDate, data.getIntervalEndDate()).getDays() >= 0) {
                validTestInsideInterval.add(test);
            }
        }
        validTestInsideInterval = organizeTestList(validTestInsideInterval);
    }

    /**
     * Method used to add to a list all positive covid tests in a interval of days
     */
    public void positiveCovidTestInsideInterval() {
        for (Test test : validTestInsideInterval) {
            for (TestParameter testParameter : test.getTestParameterList()) {
                if (testParameter != null) {
                    if (testParameter.getParameter().getCode().equals("IgGAN") && testParameter.getTestParameterResult().getResult() > 1.4) {
                        positiveCovidTestInsideInterval.add(test);
                    }
                }
            }
        }
        positiveCovidTestInsideInterval = organizeTestList(positiveCovidTestInsideInterval);
    }

    /**
     * Method used to add to a list all positive covid tests per historical day
     */
    public void positiveCovidTestsPerDayHistoric() {
        Calendar calendar = Calendar.getInstance();
        Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        calendar.setTime(date);

        for (int c = 0; c < size; c++) {
            calendar.add(Calendar.DATE, -1);
            if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                String currentDay = String.format("%02d", calendar.get(Calendar.DATE)) + "/" + String.format("%02d", (calendar.get(Calendar.MONTH) + 1)) + "/" + calendar.get(Calendar.YEAR);
                for (Test test : positiveCovidTestsPerDayHistorical) {
                    String testDateStr = test.getDate().toString();
                    String[] testDateArr = testDateStr.split("T");
                    String[] testDateArr2 = testDateArr[0].split("-");
                    testDateStr = testDateArr2[2] + "/" + testDateArr2[1] + "/" + testDateArr2[0];

                    if (testDateStr.equals(currentDay)) {
                        positiveCovidTestsPerDayHistoric[c] += 1;
                    }
                }
            } else {
                c--;
            }
        }
    }

    /**
     * Method used to the covid tests per historical day
     */
    public void covidTestsPerDayInterval() {
        for (int c = 0; c < size; c++) {
            LocalDate currentDay = getCurrentDayInsideInterval(c);
            for (Test test : validTestInsideInterval) {
                LocalDate testDate = test.getDate().toLocalDate();
                if (testDate.equals(currentDay)) {
                    covidTestsPerDayInterval[c] += 1;
                }
            }
        }
        validTestInsideInterval = organizeTestList(validTestInsideInterval);
    }

    public void covidTestsPerDayHistorical() {
        for (Test test : tempValidatedTests) {
            for (TestParameter testParameter : test.getTestParameterList()) {
                if (testParameter != null) {
                    if (testParameter.getParameter().getCode().equals("IgGAN")) {
                        covidTestsPerDayHistorical.add(test);
                    }
                }
            }
        }
        covidTestsPerDayHistorical = organizeTestList(covidTestsPerDayHistorical);
    }

    public void covidTestsPerDayHistoric(){
        Calendar calendar = Calendar.getInstance();
        Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        calendar.setTime(date);

        for (int c = 0; c < size; c++) {
            calendar.add(Calendar.DATE, -1);
            if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                String currentDay = String.format("%02d", calendar.get(Calendar.DATE)) + "/" + String.format("%02d", (calendar.get(Calendar.MONTH) + 1)) + "/" + calendar.get(Calendar.YEAR);
                for (Test test : covidTestsPerDayHistorical) {
                    String testDateStr = test.getDate().toString();
                    String[] testDateArr = testDateStr.split("T");
                    String[] testDateArr2 = testDateArr[0].split("-");
                    testDateStr = testDateArr2[2] + "/" + testDateArr2[1] + "/" + testDateArr2[0];

                    if (testDateStr.equals(currentDay)) {
                        covidTestsPerDayHistoric[c] += 1;
                    }
                }
            } else {
                c--;
            }
        }
    }

    /**
     * Method used to the positive covid tests per historical day
     */
    public void positiveCovidTestsPerDayInterval() {
        for (int c = 0; c < size; c++) {
            LocalDate currentDay = getCurrentDayInsideInterval(c);
            for (Test test : positiveCovidTestInsideInterval) {
                LocalDate testDate = test.getDate().toLocalDate();
                if (testDate.equals(currentDay)) {
                    positiveCovidTestsPerDayInterval[c] += 1;
                }
            }
        }
    }

    /**
     * Method used to calculate de mean age
     */
    public void ages() {
        Calendar calendar = Calendar.getInstance();
        Date date1 = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());


        int c = 0;
        int x = 0;
        int age;
        double sum = 0;
        for (int i = 0; i < size; i++) {
            LocalDate currentDay = getCurrentDayInsideInterval(c);
            calendar.setTime(date1);
            for (Test test : validatedTests) {
                calendar.add(Calendar.DATE, -1);
                if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    LocalDate testDate = test.getDate().toLocalDate();
                    if (testDate.equals(currentDay)) {
                        Client client = null;
                        for (Client client1 : clientList) {
                            if (test.getTin().equals(client1.getTin())) {
                                client = client1;
                            }
                        }
                        assert client != null;
                        LocalDate date = client.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        age = Period.between(date, LocalDate.now()).getYears();
                        sum += age;
                        x++;
                    }
                }
            }
            if (x != 0) ages[c] = sum / x;
            c++;
            x = 0;
            sum = 0;
        }
    }

    /**
     * Method used to the covid tests per day in a interval of dates
     */
    public void covidTestsPerDayInsideTheIntervalOfDates() {
        for (int n = 0; n < size; n++) {
            LocalDate currentDay = getCurrentDayInsideInterval(n);
            for (Test test : testList) {
                LocalDate testDate = test.getDate().toLocalDate();
                if (testDate.equals(currentDay)) {
                    covidTestsPerDayInsideTheIntervalOfDates[n] += 1;
                }
            }
        }
    }

    /**
     * Method used to get the validated tests
     *
     * @return - validated tests
     */
    public List<Test> getValidatedTests() {
        return validatedTests;
    }

    /**
     * Method used to get the positive covid tests per historical day
     *
     * @return - positive covid tests per historical day
     */
    public double[] getPositiveCovidTestsPerDayHistoric() {
        return positiveCovidTestsPerDayHistoric;
    }

    /**
     * Method used to get the covid tests in the interval of days
     *
     * @return - covid tests per day in the interval of days
     */
    public double[] getCovidTestsPerDayInterval() {
        return covidTestsPerDayInterval;
    }

    /**
     * Method used to get the positive covid tests per day
     *
     * @return - positive covid tests per day
     */
    public double[] getPositiveCovidTestsPerDayInterval() {
        return positiveCovidTestsPerDayInterval;
    }

    /**
     * Method to get the ages
     *
     * @return - ages
     */
    public double[] getAges() {
        return ages;
    }

    public double[] getCovidTestsPerDayHistoric(){
        return covidTestsPerDayHistoric;
    }
}
