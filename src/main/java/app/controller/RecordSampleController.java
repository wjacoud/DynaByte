package app.controller;

import app.domain.model.*;
import app.domain.model.client.Client;
import app.domain.model.stores.ClientsStore;
import app.domain.model.stores.SampleStore;
import app.domain.model.stores.TestStore;
import app.domain.model.test.Sample;
import app.domain.model.test.Test;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * Class responsible for the Record Sample Controller
 *
 * @author Rodrigo Oliveira 1201406
 */
public class RecordSampleController {

    TestStore testStore;
    SampleStore sampleStore;
    ClientsStore clientStore;
    Sample sample = null;
    String barcode;
    String date;


    /**
     * constructor that create the controller and call the stores
     */
    public RecordSampleController() {
        App app = App.getInstance();
        Company company = app.getCompany();
        testStore = company.getTestStore();
        sampleStore = company.getSampleStore();
        clientStore = company.getClientStore();
    }


    /**
     * return the test list
     *
     * @return test list
     */
    public List<Test> getTestList() {
        return testStore.getTests();
    }


    /**
     * return the barcode in string
     *
     * @return barcode (string)
     */
    public String getBarcode() {
        return barcode;
    }


    /**
     * create a new sample
     *
     * @param testID test id
     * @return success of the create
     * @throws BarcodeException
     * @throws OutputException
     */
    public boolean createSample(String testID) throws BarcodeException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);

        sample = sampleStore.createSample(testID, date);
        barcode = sample.getBarcode();

        return sample != null;
    }


    /**
     * return the date in string
     *
     * @return date (string)
     */
    public String getDate() {
        return date;
    }


    /**
     * save the sample created
     *
     * @return success of the save
     * @throws OutputException
     * @throws IOException
     */
    public boolean saveSample() throws OutputException, IOException {
        return sampleStore.saveSample(sample);
    }


    /**
     * change the state to sample collected
     *
     * @param testId test id
     */
    public void setState(String testId) {
        for (Test t : testStore.getTests()) {
            if (t.getTestCode().equals(testId)) {
                t.setState(Test.State.SAMPLE_COLLECTED);
            }
        }
    }


    /**
     * return the client associated with the test
     *
     * @param test test object
     * @return client object
     */
    public Client getClientFromTest(Test test) {

        for (Client c : clientStore.getClientList()) {
            if (c.getTin().equals(test.getTin())) {
                return c;
            }
        }

        return null;
    }


    /**
     * return the test associated with the id
     *
     * @param testCode test id
     * @return test
     */
    public Test getTestFromTestID(String testCode) {

        for (Test t : testStore.getTests()) {
            if (t.getTestCode().equals(testCode)) {
                return t;
            }
        }

        return null;
    }
}
