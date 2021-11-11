package app.domain.model;

import app.controller.App;
import app.domain.model.stores.SampleStore;
import app.domain.model.test.Sample;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SampleStoreTest {

    App app = App.getInstance();
    Company company = app.getCompany();
    SampleStore sampleStore = company.getSampleStore();
    private List<Sample> sampleList1 = new ArrayList<>();

    @Test
    public void createSample() throws OutputException, BarcodeException {

        assertNotNull(sampleStore.createSample("12345678909", "2021/05/27 18:17:24"));
    }

    @Test
    public void getSampleList() {
        sampleList1 = sampleStore.getSampleList();
        assertNotNull(sampleList1);

    }

    @Test
    public void checkValidateSample() throws IOException, OutputException {
        Sample sample = new Sample("88888888888","12345678955","2021/05/27 18:17:25");
        sampleStore.saveSample(sample);
        Sample sample1 = new Sample("88888888888","12345678956","2021/05/27 18:17:29");

        Assert.assertEquals(false,sampleStore.saveSample(sample1));
    }
}