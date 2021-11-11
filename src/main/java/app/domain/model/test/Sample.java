package app.domain.model.test;


/**
 * Class responsible for sample
 *
 * @author Rodrigo Oliveira 1201406
 */
public class Sample implements java.io.Serializable{

    private String barcode;
    private String testID;
    private String date;


    /**
     * constructor that create a full sample object
     *
     * @param barcode - barcode
     * @param testID  - test id
     * @param date    - date
     */
    public Sample(String barcode, String testID, String date) {
        this.barcode = barcode;
        this.testID = testID;
        this.date = date;
    }

    /**
     * return barcode
     *
     * @return barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * return testID
     *
     * @return testID
     */
    public String getTestID() {
        return testID;
    }

    /**
     * return date
     *
     * @return date
     */
    public String getDate() {
        return date;
    }
}
