package app.domain.model.test;

import app.domain.shared.Constants;

import java.time.LocalDateTime;

/**
 * Class responsible for the Test Report
 *
 * @author José Cruz 1201401
 */
public class TestReport implements java.io.Serializable {

    private String idTest;
    private TestDiagnosis diagnosis;
    private String report;
    private LocalDateTime date;

    /**
     * Constructor that instantiates a new TestReport object and checks if every data is correct
     * @param idTest - id from the test that is going to have a report
     * @param diagnosis - the diagnosis from the test
     * @param report - the text done by the specialist doctor to explain the diagnosis
     * @param date - the date that the object was created
     */
    public TestReport(String idTest, TestDiagnosis diagnosis, String report, LocalDateTime date) {
        boolean error = false;

        try {
            checkDiagnosisRules(diagnosis);
        } catch (Exception e) {
            error = true;
            System.out.println(e.getMessage());
        }

        try {
            checkReportRules(report);
        } catch (Exception e) {
            error = true;
            System.out.println(e.getMessage());
        }

        if (!error) {
            this.idTest = idTest;
            this.diagnosis = diagnosis;
            this.report = report;
            this.date = date;
        } else {
            throw new IllegalArgumentException("Error with creation");
        }

    }

    /**
     * Method that returns the test id
     * @return - idTest
     */
    public String getIdTest() {
        return idTest;
    }

    /**
     * Method that returns the test diagnosis
     * @return - diagnosis
     */
    public TestDiagnosis getDiagnosis() {
        return diagnosis;
    }

    /**
     * Method that returns the report text
     * @return - report
     */
    public String getReport() {
        return report;
    }

    /**
     * Method that returns the date the object was created
     * @return - date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * register a new Report date
     *
     * @param ReportDate Report date
     */
    public void registerRportDate(LocalDateTime ReportDate) {
        try {
            this.date = ReportDate;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method that checks if the diagnosis fits the acceptance criteria
     * @param diagnosis - the test diagnosis
     */
    private void checkDiagnosisRules(TestDiagnosis diagnosis) {
        if (diagnosis == null) {
            throw new IllegalArgumentException("The diagnosis can't be null");
        }
    }

    /**
     * Method that checks if the report text fits the acceptance criteria
     * @param report - the text report that is done by the specialist doctor
     */
    private void checkReportRules(String report) {
        if (report == null || report.equalsIgnoreCase("")) {
            throw new IllegalArgumentException("The report can't be null or empty");
        }
        if (report.length() > Constants.REPORT_LENGTH) {
            throw new IllegalArgumentException("The report mustn't have more than " + Constants.REPORT_LENGTH + " chars");
        }
    }

}

