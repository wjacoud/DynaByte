package app.controller;

import app.domain.model.*;
import app.domain.model.client.Client;
import app.domain.model.client.ClientNotification;
import app.domain.model.stores.ClientsStore;
import app.domain.model.stores.TestStore;
import app.domain.model.test.Test;
import app.domain.model.test.TestReport;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for communicating with the UI when the user wants to validate test reports and diagnostics.
 *
 * @author Wilson Jacoud 1200630
 */

public class ValidateTestReportController {
    TestStore testStore;
    String date;

    /**
     * Constructor that initialize the ValidateTestReportController
     */
    public ValidateTestReportController() {
        App app = App.getInstance();
        Company company = app.getCompany();
        testStore = company.getTestStore();
    }

    /**
     * Find a given test report by testId
     * @param idTest - identifies tests and test Reports
     * @return testReport
     */
    public TestReport findTestReport(String idTest) {
        List<Test> testList = testStore.getTests();

        for (Test test: testList) {
            if (test.getTestCode().equals(idTest)){
                return test.getReport();
            }
        }
        return null;
    }

    /**
     * Find all test reports available for validation
     * @return testReportsToValidate - List
     */
    public List<TestReport> testReportsToValidate(){
        List<TestReport> testReportsToValidate = new ArrayList<>();
        List<Test> testList = testStore.getTests();

        for (Test test: testList) {
            if (test.getState().equals(Test.State.DIAGNOSED)){
                testReportsToValidate.add(findTestReport(test.getTestCode()));
            }
        }
        return testReportsToValidate;
    }

    /**
     * Validate a given test report by testId - Setting test's state to VALIDATED
     * and save the validation date.
     * @param idTest - identifies tests and test Reports
     */
    public void validateTestReport(String idTest) {
        LocalDateTime now = LocalDateTime.now();
        for (Test test : testStore.getTests()){
            if(idTest.equalsIgnoreCase(test.getTestCode())){
                test.setState(Test.State.VALIDATED);
                test.registerValidationDate(now);
                clientMessage(test.getTin());
            }
        }
    }

    /**
     * Validate a all test reports
     */
    public void validateAllTestReport() {
        List<TestReport> testReportsToValidate = testReportsToValidate();
        for (TestReport testReport: testReportsToValidate) {
            validateTestReport(testReport.getIdTest());
        }
    }

    /**
     * Send a message for the client informing the validation of a given test report and diagnosis
     * @param clientId - client identifier
     */
    private void clientMessage(String clientId) {
        ClientsStore clientsStore = new ClientsStore();
        for (Client client: clientsStore.getClientList()) {
            if (client.getTin().equals(clientId)){
                ClientNotification.sendMessage("email", "User: " + "user@isep.ipp.pt" + "\npassword: " + "123456" + "\nDear Client,\nYour test report is available.", client.getTin());
            }
        }
    }
}
