package app.controller;

import app.domain.model.*;
import app.domain.model.stores.SampleStore;
import app.domain.model.stores.TestStore;
import app.domain.model.test.Sample;
import app.domain.model.test.Test;
import app.domain.model.testComponents.Parameter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * class responsible for the Record Test Result Controller
 *
 * @author Ricardo Faria 1201405
 */
public class RecordTestResultController {

    private TestStore testStore;
    private SampleStore sampleStore;
    private Test test = null;


    /**
     * Constructor that initializes the stores
     */
    public RecordTestResultController() {
        App app = App.getInstance();
        Company company = app.getCompany();
        testStore = company.getTestStore();
        sampleStore = company.getSampleStore();
    }


    /**
     * return the test
     *
     * @return test
     */
    public Test getTest() {
        return test;
    }


    /**
     * tries to find the test that matches the sample barcode
     *
     * @param sampleBarcode sample barcode
     */
    private void findTest(String sampleBarcode) {

        List<Sample> sampleList = sampleStore.getSampleList();

        String idTest = "";

        for (Sample sample : sampleList) {
            if (sample.getBarcode().equals(sampleBarcode)) {
                idTest = sample.getTestID();
            }
        }

        List<Test> testList = testStore.getTests();

        for (Test test1 : testList) {
            if (test1.getTestCode().equals(idTest)) {
                test = test1;
                return;
            }
        }
        test = null;
    }


    /**
     * tries to return the parameter list of a test that matches the sample barcode
     *
     * @param sampleBarcode sample barcode
     * @return parameter list
     */
    public List<Parameter> getParameters(String sampleBarcode) {
        findTest(sampleBarcode);

        if (test == null || !test.getState().equals(Test.State.SAMPLE_COLLECTED)) {
            return null;
        }

        return test.getParameterList();
    }


    /**
     * add the test parameter result for the respective test
     *
     * @param parameterCode parameter code
     * @param result        value of the parameter analyzed
     * @return success of adding the test parameter result
     */
    public boolean addTestParameterResult(String parameterCode, double result) {
        try {
            return test.addTestParameterResult(parameterCode, result);
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * changes the state of the test to analyzed
     */
    public void setState(boolean save) {
        LocalDateTime now = LocalDateTime.now();

        if (save) {
            test.setState(Test.State.ANALYZED);
            test.registerAnalyzedDate(now);
        } else {
            test.setState(Test.State.SAMPLE_COLLECTED);
        }
    }
}

