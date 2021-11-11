package app.domain.model.stores;

import app.domain.model.client.Client;
import app.domain.model.test.Test;
import app.domain.model.testComponents.TestType;

import java.util.ArrayList;
import java.util.List;

/**
 * class responsible for the TestStore
 *
 * @author Gonçalo Pereira 1201500
 */
public class TestStore implements java.io.Serializable{
    private List<Test> testList = new ArrayList<>();

    /**
     * Method to get the all Tests
     *
     * @return testList
     */
    public List<Test> getTests() {
        return testList;
    }


    /**
     * Create a Test
     *
     * @param tin      - taxpayer identification number
     * @param id       - test id
     * @param nhsCode  - nhs code
     * @param testType - test type
     * @param date     - date
     * @return test
     */
    public Test createTest(String tin, String id, String nhsCode, TestType testType, String date) {
        Test test = new Test(tin, id, nhsCode, testType, date);

        if (validateTest(test)) {
            return test;
        }
        return null;
    }

    /**
     * Validate the Test
     *
     * @param test - test
     * @return boolean
     */
    public boolean validateTest(Test test) {

        if (test == null)
            return false;

        for (Test test1 : testList) {
            if (test1.getTestCode().equalsIgnoreCase(test.getTestCode()) || (test1.getNhsCode().equalsIgnoreCase(test.getNhsCode()))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Save the new Test
     *
     * @param test - test
     * @return boolean
     */
    public boolean saveTest(Test test) {
        if (validateTest(test)) {
            testList.add(test);
            return true;
        }
        return false;
    }

    /**
     * Generate a new Id to a new test
     *
     * @return id
     */
    public String createId() {
        String id;

        id = String.format("%012d", testList.size() + 1);
        return id;
    }

    /**
     * verify if the nhs code is unique
     *
     * @param nhsCode - nhs code
     * @return - boolean
     */
    public boolean verifyExistingNhsCode(String nhsCode) {
        for (Test test1 : testList) {
            if (test1.getNhsCode().equalsIgnoreCase(nhsCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method used to get all validated tests of a client
     * @param c - client
     * @return - list with validated tests
     */
    public List<Test> getValidatedTests(Client c){
        List<Test> validatedTest = new ArrayList<>();

        for (Test t : testList){
            if (c.getTin().equalsIgnoreCase(t.getTin()) && t.getState().equals(Test.State.VALIDATED)){
                validatedTest.add(t);
            }
        }

        return validatedTest;
    }

}
