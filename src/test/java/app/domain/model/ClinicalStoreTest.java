package app.domain.model;

import app.domain.model.stores.ClinicalStore;
import org.junit.Assert;
import org.junit.Test;

public class ClinicalStoreTest {

    @Test(expected = IllegalArgumentException.class)
    public void createClinicalNullv1() {
        ClinicalStore store = new ClinicalStore();

        store.createClinical(null, null, null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClinicalNullv2() {
        ClinicalStore store = new ClinicalStore();
        store.createClinical("12345", null, null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createClinicalNullv3() {
        ClinicalStore test = new ClinicalStore();
        test.createClinical("12345", "name", null, null, null, null);
    }

    @Test
    public void CheckCreateClinicalStore() {
        ClinicalStore clinicalList;

        clinicalList = new ClinicalStore();
    }

    @Test
    public void CheckSaveClinical() {
        ClinicalStore clinicalList;
        clinicalList = new ClinicalStore();

        String[] testTypes = {"blood tests", "covid tests"};
        Clinical clinical = new Clinical("12345", "clinica aaa", "rua", "1111111111", "11111111111", testTypes);

        clinicalList.saveClinical(clinical);
    }

    @Test
    public void checkCreateClinical() {
        ClinicalStore clinicalList;
        clinicalList = new ClinicalStore();

        String[] testTypes = {"blood tests", "covid tests"};
        clinicalList.createClinical("12345", "Clinica 123", "Rua 123", "1111111111", "11111111111", testTypes);
    }

    @Test
    public void checkValidateClinical() {
        ClinicalStore clinicalList;
        clinicalList = new ClinicalStore();

        String[] testTypes = {"blood tests", "covid tests"};
        Clinical clinical = new Clinical("12345", "clinica aaa", "rua", "1111111111", "11111111111", testTypes);

        clinicalList.validateClinical(clinical);
    }

    @Test
    public void checkInvalidSaveClinical() {
        ClinicalStore clinicalList;
        clinicalList = new ClinicalStore();

        String[] testTypes = {"blood tests", "covid tests"};
        Clinical clinical = new Clinical("12345", "clinica aaa", "rua", "1111111111", "11111111111", testTypes);

        clinicalList.saveClinical(clinical);

        Clinical clinical1 = new Clinical("12345", "clinica aaa", "rua", "1111111111", "11111111111", testTypes);

        clinicalList.saveClinical(clinical1);
    }

    @Test
    public void saveClinicalTest1() {
        String[] testTypes = {"blood tests", "covid tests"};
        Clinical clinical = new Clinical("12345", "clinica aaa", "rua", "1111111111", "11111111111", testTypes);
        ClinicalStore store = new ClinicalStore();
        Clinical clinical1 = store.createClinical("12345", "clinica aaa", "rua", "1111111111", "11111111111", testTypes);

        Assert.assertTrue(store.saveClinical(clinical1));
    }

    @Test
    public void saveClinicalTest2() {
        String[] testTypes = {"blood tests", "covid tests"};
        ClinicalStore store = new ClinicalStore();
        Clinical clinical = store.createClinical("12345", "clinica aaa", "rua", "1111111111", "11111111111", testTypes);

        store.saveClinical(clinical);
        Clinical clinicalTest = store.createClinical("12345", "clinica aaa", "rua", "1111111111", "11111111111", testTypes);

        Assert.assertFalse(store.saveClinical(clinicalTest));
    }

    @Test
    public void saveClinicalTest3() {
        String[] testTypes = {"blood tests", "covid tests"};
        ClinicalStore store = new ClinicalStore();
        Clinical clinical = store.createClinical("12345", "clinica aaa", "rua", "1111111111", "11111111111", testTypes);

        store.saveClinical(clinical);


        Assert.assertNotEquals(true, store.validateClinical(clinical));
    }

    @Test
    public void validateClinicalTest1() {
        String[] testTypes = {"blood tests", "covid tests"};
        Clinical clinical = new Clinical("12345", "clinica aaa", "rua", "1111111111", "11111111111", testTypes);
        ClinicalStore store = new ClinicalStore();

        Assert.assertTrue(store.validateClinical(clinical));
    }

    @Test
    public void validateClinicalTest2() {
        String[] testTypes = {"blood tests", "covid tests"};
        ClinicalStore store = new ClinicalStore();
        Clinical clinical = store.createClinical("12345", "clinica aaa", "rua", "1111111111", "11111111111", testTypes);

        store.saveClinical(clinical);

        Assert.assertFalse(store.validateClinical(clinical));
    }

    @Test
    public void validateClinicalTest3() {
        ClinicalStore store = new ClinicalStore();
        Assert.assertNotEquals(true, store.validateClinical(null));
    }

    @Test
    public void validateClinicalTest4() {
        String[] testTypes = {"blood tests", "covid tests"};
        ClinicalStore store = new ClinicalStore();
        Clinical clinical = store.createClinical("12345", "clinica aaa", "rua", "1111111111", "11111111111", testTypes);


        Assert.assertNotNull(clinical);
    }

    @Test
    public void verifyExistingClinical() {
        String[] testTypes = {"blood tests", "covid tests"};
        ClinicalStore store = new ClinicalStore();
        Clinical clinical = store.createClinical("12345", "clinica aaa", "rua", "1111111111", "11111111111", testTypes);
        store.saveClinical(clinical);

        Assert.assertEquals(true, store.verifyExistingClinical("12345"));

    }

    @Test
    public void verifyNonExistingClinical() {
        String[] testTypes = {"blood tests", "covid tests"};
        ClinicalStore store = new ClinicalStore();
        Clinical clinical = store.createClinical("12345", "clinica aaa", "rua", "1111111111", "11111111111", testTypes);
        store.saveClinical(clinical);

        Assert.assertEquals(false, store.verifyExistingClinical("43212"));

    }
}