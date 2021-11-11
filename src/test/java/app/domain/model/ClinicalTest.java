package app.domain.model;

import app.domain.shared.exceptions.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClinicalTest {

    @Test()
    public void CreateValidClinical() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("12345", "clinical", "address", "1111111111", "11111111111", testTypes);
    }

    @Test(expected = PhoneNumberException.class)
    public void CreatePhoneNumberWithLetters() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("12345", "clinical", "address", "1111111111", "aaaaaaaaaaa", testTypes);
    }

    @Test(expected = PhoneNumberException.class)
    public void CreatePhoneNumberWithLettersAndNumbers() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("12345", "clinical", "address", "1111111111", "111111aaa11", testTypes);
    }

    @Test(expected = PhoneNumberException.class)
    public void CreateBlankPhoneNumber() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("12345", "clinical", "address", "1111111111", "", testTypes);
    }

    @Test(expected = PhoneNumberException.class)
    public void CreatePhoneNumberUnder11Chars() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("12345", "clinical", "address", "1111111111", "1111111111", testTypes);
    }

    @Test(expected = PhoneNumberException.class)
    public void CreatePhoneNumberAbove11Chars() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("12345", "clinical", "address", "1111111111", "1111111111111", testTypes);
    }

    @Test(expected = NameException.class)
    public void CreateBlankName() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("12345", "", "address", "1111111111", "11111111111", testTypes);
    }

    @Test(expected = NameException.class)
    public void CreateNameWithMoreThan30Chars() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("12345", "Clinical with more than thirty digits", "address", "1111111111", "11111111111", testTypes);
    }

    @Test(expected = IdLaboratoryException.class)
    public void CreateBlankIdLaboratory() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("", "Clinical", "address", "1111111111", "11111111111", testTypes);
    }

    @Test(expected = IdLaboratoryException.class)
    public void CreateIdLaboratoryWithMoreThan5Chars() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("a12346", "Clinical", "address", "1111111111", "11111111111", testTypes);
    }

    @Test(expected = IdLaboratoryException.class)
    public void CreateIdLaboratoryWithLessThan5Chars() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("a12", "Clinical", "address", "1111111111", "11111111111", testTypes);
    }

    @Test(expected = IdLaboratoryException.class)
    public void CreateInvalidIdLaboratory() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("a1!34", "Clinical", "address", "1111111111", "11111111111", testTypes);
    }

    @Test(expected = TinNumberException.class)
    public void CreateTinWithLetters() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("a1234", "Clinical", "address", "aaaaaaaaaa", "11111111111", testTypes);
    }

    @Test(expected = TinNumberException.class)
    public void CreateTinWithMoreThan10Chars() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("a1234", "Clinical", "address", "1111111111111", "11111111111", testTypes);
    }

    @Test(expected = TinNumberException.class)
    public void CreateTinWithLessThan10Chars() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("a1234", "Clinical", "address", "1111111", "11111111111", testTypes);
    }

    @Test(expected = TinNumberException.class)
    public void CreateBlankTin() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("a1234", "Clinical", "address", "", "11111111111", testTypes);
    }

    @Test(expected = TinNumberException.class)
    public void CreateTinWithNumbersAndLetters() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("a1234", "Clinical", "address", "11111111aa", "11111111111", testTypes);
    }

    @Test(expected = AddressException.class)
    public void CreateAddressWithInvalidChars() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("a1234", "Clinical", "address!!", "1111111111", "11111111111", testTypes);
    }

    @Test(expected = AddressException.class)
    public void CreateInvalidAddress() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("a1234", "Clinical", "address(!", "1111111111", "11111111111", testTypes);
    }

    @Test(expected = AddressException.class)
    public void CreateBlankAddress() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("a1234", "Clinical", "", "1111111111", "11111111111", testTypes);
    }

    @Test
    public void CheckGetIdLaboratory() {
        String expected = "a1234";
        String actual;
        String[] testTypes = {"blood tests", "covid tests"};
        Clinical clinical = new Clinical("a1234", "Clinical", "address", "1111111111", "11111111111", testTypes);
        actual = clinical.getIdLaboratory();
        assertEquals(expected, actual);
    }

    @Test
    public void CheckInvalidGetIdLaboratory() {
        String expected = "a1238";
        String actual;
        String[] testTypes = {"blood tests", "covid tests"};
        Clinical clinical = new Clinical("a1234", "Clinical", "address", "1111111111", "11111111111", testTypes);
        actual = clinical.getIdLaboratory();
        assertNotEquals(expected, actual);
    }

    @Test
    public void checkGetName() {
        String expected = "Clinical";
        String actual;
        String[] testTypes = {"blood tests", "covid tests"};
        Clinical clinical = new Clinical("a1234", "Clinical", "address", "1111111111", "11111111111", testTypes);
        actual = clinical.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void checkInvalidGetName() {
        String expected = "Clinical";
        String actual;
        String[] testTypes = {"blood tests", "covid tests"};
        Clinical clinical = new Clinical("a1234", "ClinicalDifferent", "address", "1111111111", "11111111111", testTypes);
        actual = clinical.getName();
        assertNotEquals(expected, actual);
    }

    @Test
    public void checkGetPhoneNumber() {
        String expected = "11111111111";
        String actual;
        String[] testTypes = {"blood tests", "covid tests"};
        Clinical clinical = new Clinical("a1234", "Clinical", "address", "1111111111", "11111111111", testTypes);
        actual = clinical.getPhoneNumber();
        assertEquals(expected, actual);
    }

    @Test
    public void checkGetAddress() {
        String expected = "address";
        String actual;
        String[] testTypes = {"blood tests", "covid tests"};
        Clinical clinical = new Clinical("a1234", "Clinical", "address", "1111111111", "11111111111", testTypes);
        actual = clinical.getAddress();
        assertEquals(expected, actual);
    }

    @Test
    public void checkGetTin() {
        String expected = "1111111111";
        String actual;
        String[] testTypes = {"blood tests", "covid tests"};
        Clinical clinical = new Clinical("a1234", "Clinical", "address", "1111111111", "11111111111", testTypes);
        actual = clinical.getTin();
        assertEquals(expected, actual);
    }

    @Test
    public void checkGetTestType() {
        String[] actual;
        String[] testTypes = {"blood tests", "covid tests"};
        Clinical clinical = new Clinical("a1234", "Clinical", "address", "1111111111", "11111111111", testTypes);
        actual = clinical.getTestType();
        assertEquals(testTypes, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNameRulesChars() {
        String[] testTypes = {"blood tests", "covid tests"};
        new Clinical("a1234", "Clinical!", "address", "1111111111", "11111111111", testTypes);
    }
}