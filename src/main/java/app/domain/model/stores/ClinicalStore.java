package app.domain.model.stores;

import app.domain.model.Clinical;

import java.util.ArrayList;
import java.util.List;

/**
 * class responsible for the ClinicalStore
 *
 * @author Gonçalo Pereira 1201500
 */
public class ClinicalStore implements java.io.Serializable{

    private List<Clinical> clinicalList;


    /**
     * Constructor that create a empty list of Clinical type
     */
    public ClinicalStore() {
        clinicalList = new ArrayList<>();
    }


    /**
     * Create a Clinical
     *
     * @param idLaboratory - id laboratory
     * @param name         - name
     * @param address      - address
     * @param phoneNumber  - phone number
     * @param tin          - Taxpayer Identification Number
     * @param testType     - test type
     * @return clinical
     */
    public Clinical createClinical(String idLaboratory, String name, String address, String tin, String phoneNumber, String[] testType) {
        return new Clinical(idLaboratory, name, address, tin, phoneNumber, testType);
    }

    /**
     *
     * @param clinical - clinical
     * @return validation
     */
    public boolean saveClinical(Clinical clinical) {
        if (validateClinical(clinical)) {
            clinicalList.add(clinical);
            return true;
        }
        return false;
    }


    /**
     * validate the new clinical
     *
     * @param clinical - clinical
     * @return return id
     */
    public boolean validateClinical(Clinical clinical) {
        if (clinical == null)
            return false;

        for (Clinical clinical1 : clinicalList) {
            if (clinical1.getIdLaboratory().equalsIgnoreCase(clinical.getIdLaboratory())) {
                return false;
            }
        }

        return true;
    }
    public boolean verifyExistingClinical(String idLaboratory) {
        for(Clinical clinical : clinicalList) {
            if(clinical.getIdLaboratory().equalsIgnoreCase(idLaboratory)){
                return true;
            }
        }
        return false;
    }
}

