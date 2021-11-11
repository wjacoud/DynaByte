package app.controller;

import app.domain.model.*;
import app.domain.model.client.Client;
import app.domain.model.stores.ClientsStore;
import app.domain.model.stores.TestStore;
import app.domain.model.test.Test;
import app.domain.model.test.TestDiagnosis;
import app.domain.model.test.TestReport;

import java.time.LocalDateTime;


/**
 * Class responsible for communicating with the UI when the user wants to create a new TestReport
 *
 * @author José Cruz 1201401
 */
public class CreateReportController {

    private TestStore testStore;
    private TestDiagnosis testDiagnosis = new TestDiagnosis();

    private ClientsStore clientsStore;
    private Test test;

    /**
     * Constructor that initialize the company and calls for the store
     */
    public CreateReportController() {
        App app = App.getInstance();
        Company company = app.getCompany();
        testStore = company.getTestStore();
        clientsStore = company.getClientStore();
    }

    /**
     * Method that returns the store that contains the tests
     *
     * @return - testStore - store object that contains all the tests
     */
    public TestStore getTestStore() {
        return testStore;
    }

    /**
     * Method that uses the store object to create a new TestReport
     *
     * @param idTest    - test id that is being diagnosed and reported
     * @param diagnosis - the test diagnosis
     * @param report    - the text done by the specialist doctor to explain the diagnosis
     * @return - returns true or false, depending if the creation was successful or not
     */
    public boolean createTestReport(String idTest, TestDiagnosis diagnosis, String report) {
        LocalDateTime now = LocalDateTime.now();
        TestReport testReport;

        testReport = new TestReport(idTest, diagnosis, report, now);
        if (!validateTestReport()) {
            return false;
        }
        test.setReport(testReport);
        test.setState(Test.State.DIAGNOSED);
        return testReport != null;
    }


    /**
     * Method that sets the state of the test to "DIAGNOSED"
     *
     * @param idTest - id from the test that is having the state changed
     */
    public void setState(String idTest) {
        for (Test test1 : testStore.getTests()) {
            if (idTest.equalsIgnoreCase(test1.getTestCode())) {
                test1.setState(Test.State.DIAGNOSED);
            }
        }
    }

    /**
     * Method that verifies if the TestReport object already exists in the store
     *
     * @return - returns true if the object doesn't exist or false if it already exists
     */
    public boolean validateTestReport() {
        return test.getState() == Test.State.ANALYZED;
    }

    private boolean findTest(String idTest) {
        for (Test test1 : testStore.getTests()) {
            if (test1.getTestCode().equalsIgnoreCase(idTest)) {
                this.test = test1;
                return true;
            }
        }
        return false;
    }

    /**
     * Method that used the TestDiagnosis object to create a new diagnosis
     *
     * @param idTest - id from the test that is being diagnosed
     * @return - returns the diagnosis if the idTest exists in the store, if not, returns null
     */
    public TestDiagnosis createTestDiagnosis(String idTest) {
        if (!findTest(idTest)) {
            return null;
        }
        return testDiagnosis.createTestDiagnosis(test.getTestParameterList());
    }

    public String getClientName(Test test) {
        for (Client c : clientsStore.getClientList()) {
            if (test.getTin().equalsIgnoreCase(c.getTin())) {
                return c.getName();
            }
        }
        return null;
    }
}
