package app.domain.model;

import app.domain.shared.Constants;
import app.domain.shared.exceptions.*;
import org.apache.commons.lang3.StringUtils;

/**
 * class responsible for the Clinical
 *
 * @author Gonçalo Pereira 1201500
 */
public class Clinical implements java.io.Serializable {
    private String idLaboratory;
    private String name;
    private String phoneNumber;
    private String address;
    private String tin;
    private String[] testType;


    /**
     * Constructor
     *
     * @param idLaboratory - identification code
     * @param name         - name
     * @param phoneNumber  - phone number
     * @param address      - address
     * @param tin          - taxpayer identification number
     * @param testType     - test type
     */
    public Clinical(String idLaboratory, String name, String address, String tin, String phoneNumber, String[] testType) {
        checkNameRules(name);
        checkIdLaboratoryRules(idLaboratory);
        checkPhoneNumberRules(phoneNumber);
        checkAddressRules(address);
        checkTinRules(tin);

        this.idLaboratory = idLaboratory;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.tin = tin;
        this.testType = testType;
    }


    /**
     * get the name
     *
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * get the phone number
     *
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * get the address
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }


    /**
     * get the tin
     *
     * @return tin
     */
    public String getTin() {
        return tin;
    }


    /**
     * get the laboratory id
     *
     * @return laboratory id
     */
    public String getIdLaboratory() {
        return idLaboratory;
    }


    /**
     * get the array with the tests
     *
     * @return testType
     */
    public String[] getTestType() {
        return testType;
    }


    /**
     * checks if the parameter meets the requirements
     *
     * @param phoneNumber - phone number
     */
    private void checkPhoneNumberRules(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber))
            throw new PhoneNumberException("Phone Number cannot be blank");
        if (!phoneNumber.matches("[0-9]+"))
            throw new PhoneNumberException("Phone Number only accepts numbers");
        if (phoneNumber.length() != Constants.PHONE_NUMBER_LENGTH)
            throw new PhoneNumberException("Phone Number must have " + Constants.PHONE_NUMBER_LENGTH + Constants.CODE_MESSAGE);
    }


    /**
     * checks if the parameter meets the requirements
     *
     * @param name - name
     */
    private void checkNameRules(String name) {
        if (StringUtils.isBlank(name))
            throw new NameException("Name cannot be blank");
        if (!name.matches("[ a-zA-Z0-9]+$"))
            throw new NameException("Name only accepts letters and numbers");
        if (name.length() > Constants.NAME_CLINICAL_MAX_LENGTH)
            throw new NameException("Name cannot have more than " + Constants.NAME_CLINICAL_MAX_LENGTH + Constants.CODE_MESSAGE);
    }


    /**
     * checks if the parameter meets the requirements
     *
     * @param address - address
     */
    private void checkAddressRules(String address) {
        if (StringUtils.isBlank(address))
            throw new AddressException("Address cannot be blank");
        if (!address.matches("[ a-zA-Z0-9]+$"))
            throw new AddressException("Address only accepts letters and numbers");
        if (address.length() > Constants.ADDRESS_MAX_LENGTH)
            throw new AddressException("Address cannot have more than " + Constants.ADDRESS_MAX_LENGTH + Constants.CODE_MESSAGE);
    }


    /**
     * checks if the parameter meets the requirements
     *
     * @param idLaboratory - identification code
     */
    private void checkIdLaboratoryRules(String idLaboratory) {
        if (StringUtils.isBlank(idLaboratory))
            throw new IdLaboratoryException("Laboratory ID cannot be blank");
        if (!idLaboratory.matches("[a-zA-Z0-9]*"))
            throw new IdLaboratoryException("Laboratory ID only accepts letters and numbers");
        if (idLaboratory.length() != Constants.LABORATORY_ID_LENGTH)
            throw new IdLaboratoryException("Laboratory ID must have " + Constants.LABORATORY_ID_LENGTH + Constants.CODE_MESSAGE);
    }


    /**
     * checks if the parameter meets the requirements
     *
     * @param tin - taxpayer identification number
     */
    private void checkTinRules(String tin) {
        if (StringUtils.isBlank(tin))
            throw new TinNumberException("Tin cannot be blank.");
        if (!tin.matches("[0-9]+"))
            throw new TinNumberException("Tin only accepts numbers");
        if (tin.length() != Constants.TIN_LENGTH)
            throw new TinNumberException("Tin must have " + Constants.TIN_LENGTH + Constants.CODE_MESSAGE);
    }
}
