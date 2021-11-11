package app.controller;

import app.domain.model.*;
import app.domain.model.stores.*;
import app.domain.model.test.Test;
import app.domain.model.testComponents.Parameter;
import app.domain.model.testComponents.ParameterCategory;
import app.domain.model.testComponents.TestType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * class responsible for the Creator Clinical Controller
 *
 * @author Gonçalo Pereira 1201500
 */
public class CreateTestController {

    ClientsStore clientStore;
    TestTypeStore testTypeStore;
    ParameterCategoryStore parameterCategoryStore;
    ParameterStore parameterStore;
    TestStore testStore;
    String date;
    Test test = null;


    /**
     * Constructor that create a empty CreateTestController object
     */
    public CreateTestController() {
        App app = App.getInstance();
        Company company = app.getCompany();
        clientStore = company.getClientStore();
        testTypeStore = company.getTestTypeStore();
        parameterStore = company.getParameterStore();
        parameterCategoryStore = company.getParameterCategoryStore();
        testStore = company.getTestStore();
    }

    /**
     * Method to find the Client object
     *
     * @param tin - taxpayer identification number
     * @return boolean
     */
    public boolean findClient(String tin) {
        return clientStore.verifyExistingClient(tin);
    }

    /**
     * Method to check the nhs code
     *
     * @param nhsCode - nhs code
     * @return boolean
     */
    public boolean nhsCodeVerification(String nhsCode) {
        return testStore.verifyExistingNhsCode(nhsCode);
    }

    /**
     * Get the test type list
     *
     * @return testTypeList
     */
    public List<TestType> getTestType() {
        return testTypeStore.getTestTypeList();
    }

    /**
     * Get the categories list
     *
     * @return parameter category list
     */
    public List<ParameterCategory> getParameterCategory() {
        return parameterCategoryStore.getPcList();
    }

    /**
     * get the parameter list
     *
     * @return parameter list
     */
    public List<Parameter> getParameter() {
        return parameterStore.getParameterList();
    }

    /**
     * get the date
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Create the test
     *
     * @param tin       - taxpayer identification number
     * @param nhsCode   - nhs code
     * @param testType  - test type
     * @param paramList - parameter list
     * @return boolean
     */
    public boolean createTest(String tin, String nhsCode, TestType testType, List<Parameter> paramList) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);

        String id = testStore.createId();
        test = testStore.createTest(tin, id, nhsCode, testType, date);
        test.testParameterCreator(id, paramList);
        return test != null;
    }

    /**
     * save the test
     *
     * @return boolean
     */
    public boolean saveTest() {
        return testStore.saveTest(test);
    }
}