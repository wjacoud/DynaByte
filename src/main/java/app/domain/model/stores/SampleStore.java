package app.domain.model.stores;

import app.domain.model.adpaters.BarcodeAdapter;
import app.domain.model.test.Sample;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.output.OutputException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for the sample store
 *
 * @author Rodrigo Oliveira 1201406
 */
public class SampleStore implements java.io.Serializable {


    private List<Sample> sampleList = new ArrayList<>();
    BarcodeAdapter bar = new BarcodeAdapter();
    String barcode;

    /**
     * Constructor that create a empty list of samples
     */
    public SampleStore() {
        //Sample Store
    }

    /**
     * @return sample list
     */
    public List<Sample> getSampleList() {
        return sampleList;
    }

    /**
     * create sample
     *
     * @param testID - testID
     * @param date   - date
     * @return return the sample
     */
    public Sample createSample(String testID, String date) throws BarcodeException {
        barcode = bar.createBarcode();

        return new Sample(barcode, testID, date);
    }

    /**
     * save sample
     *
     * @param sample - sample
     * @return if the sample was saved
     */
    public boolean saveSample(Sample sample) throws OutputException, IOException {
        if (validateSample(sample)) {
            try {
                bar.writeBarcodeImage();
            } catch (Exception e) {
                return false;
            }
            sampleList.add(sample);
            return true;
        }
        return false;
    }

    /**
     * Validate sample
     *
     * @param sample - sample
     * @return validate the sample
     */
    public boolean validateSample(Sample sample) {

        for (Sample sample1 : sampleList) {
            if (sample1.getBarcode().equalsIgnoreCase(sample.getBarcode())) {
                return false;
            }
        }
        return true;
    }
}
