package app.domain.model.adpaters;

import app.domain.shared.Constants;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * adapter for the barcode api
 *
 * @author Rodrigo Oliveira 1201406
 */
public class BarcodeAdapter implements java.io.Serializable {

    private int numerobarcode = 0;
    Barcode barcode;

    /**
     * empty constructor
     */
    public BarcodeAdapter() {
        //Barcode Adapter
    }

    /**
     * return the barcode in String
     *
     * @return barcode (String)
     * @throws BarcodeException
     */
    public String createBarcode() throws BarcodeException {
        numerobarcode++;
        barcode = generateUPCABarcode();
        return barcode.toString();
    }

    /**
     * generate the upca barcode
     *
     * @return barcode
     * @throws BarcodeException
     */
    public Barcode generateUPCABarcode() throws BarcodeException {
        return BarcodeFactory.createUPCA((String.format("%011d", numerobarcode)));
    }

    /**
     * create a new image for the barcode
     *
     * @return image
     * @throws OutputException
     */
    public BufferedImage generateUPCABarcodeImage() throws OutputException {
        barcode.setPreferredBarHeight(100);
        return BarcodeImageHandler.getImage(barcode);
    }

    /**
     * introduce the barcode in the image created
     *
     * @throws OutputException
     * @throws IOException
     */
    public void writeBarcodeImage() throws OutputException, IOException {

        File file1 = new File(Constants.BARCODE_PATH);
        file1.mkdir();
        File outputFile = new File(Constants.BARCODE_PATH + "Barcode_" + barcode.toString() + ".jpg");
        BufferedImage barcodeImage = generateUPCABarcodeImage();
        ImageIO.write(barcodeImage, "jpg", outputFile);
    }
}
