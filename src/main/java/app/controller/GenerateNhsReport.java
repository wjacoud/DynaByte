package app.controller;

import app.domain.model.Company;
import app.domain.model.stores.TestStore;
import app.domain.model.test.Test;

import java.util.Date;
import java.util.List;

/**
 * class responsible to generate the nhs report
 *
 * @author Rodrigo Oliveira 1201406
 */

public class GenerateNhsReport {
    private List<Test> testList;

    /**
     * Constructor that create a empty GenerateNhsReport object
     */
    public GenerateNhsReport() {
        App app = App.getInstance();
        Company company = app.getCompany();
        TestStore testStore = company.getTestStore();
        CheckTestsController ctc = new CheckTestsController();
        testList = ctc.organizeClientTestList(testStore.getTests());
    }


}