package app.controller;

import app.domain.model.*;
import app.domain.model.stores.TestTypeStore;
import app.domain.model.testComponents.ParameterCategory;
import app.domain.model.testComponents.TestType;

/**
 * Class responsible for communicating with the UI when the user wants to create a new Test Type
 *
 * @author Wilson Jacoud 1200630
 */
public class TestTypeController {

    private TestType testType;
    private final TestTypeStore testTypeStore;


    public TestTypeController() {
        App app = App.getInstance();
        Company company = app.getCompany();
        testTypeStore = company.getTestTypeStore();
    }


    /**
     * Initialize a new TestTypeStore object and then creates a new TestType
     *
     * @param code              - identifies the TestType in a unique way
     * @param description       - String describing the Test Type
     * @param collectingMethods - collectingMethods description
     * @return false            - if creating the object goes wrong
     */
    public boolean createTestType(String code, String description, String collectingMethods, ParameterCategory[] categories) {
        testType = testTypeStore.createTestType(code, description, collectingMethods, categories);
        return testType != null;
    }


    /**
     * Asks the TestTypeStore object to save the TestType created
     *
     * @return this.list.AddTestType
     */
    public boolean saveTestType() {
        return testTypeStore.saveTestType(testType);
    }
}
