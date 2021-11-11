package app.controller;

import app.domain.model.*;
import app.domain.model.stores.ClinicalStore;
import app.domain.model.stores.TestTypeStore;
import app.domain.model.testComponents.TestType;

import java.util.List;

/**
 * class responsible for the Creator Clinical Controller
 *
 * @author Gonçalo Pereira 1201500
 */
public class CreateClinicalController {

    Clinical clinical = null;
    ClinicalStore clinicalStore;
    TestTypeStore testTypeStore;

    /**
     * Constructor that create a empty CreateClinicalController object
     */
    public CreateClinicalController() {
        App app = App.getInstance();
        Company company = app.getCompany();
        clinicalStore = company.getClinicalStore();
        testTypeStore = company.getTestTypeStore();
    }

    /**
     * @return
     */
    public List<TestType> getTestType() {
        return testTypeStore.getTestTypeList();
    }

    /**
     * Create a new instance of Clinical
     *
     * @param idLaboratory - id laboratory
     * @param name         - name
     * @param address      - address
     * @param tin          - taxpayer identification number
     * @param phoneNumber  - phone number
     * @param testType     - test type
     * @return success of the operation
     */
    public boolean createClinical(String idLaboratory, String name, String address, String tin, String phoneNumber, String[] testType) {
        clinical = clinicalStore.createClinical(idLaboratory, name, address, tin, phoneNumber, testType);

        return clinical != null;
    }


    /**
     * gives the order to the Administrator to save the Clinical
     *
     * @return success of the operation
     */
    public boolean saveClinical() {
        return clinicalStore.saveClinical(clinical);
    }
}
