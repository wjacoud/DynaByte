package app.domain.model.employees;

import app.domain.shared.Constants;
import app.domain.shared.exceptions.*;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * class responsible for the Platform
 *
 * @author All group
 */
public class Employee implements java.io.Serializable {
    private String id;
    private String name;
    private String function;
    private String phoneNumber;
    private String email;
    private String address;
    private String soc;


    /**
     * Constructor that create a full Employee object
     *
     * @param name        - name
     * @param function    - function
     * @param phoneNumber - phone number
     * @param email       - email
     * @param address     - address
     */
    public Employee(String id, String name, String function, String phoneNumber, String email, String address, String soc) {
        checkNameRules(name);
        checkEmail(email);
        checkAddressRules(address);
        checkPhoneNumberRules(phoneNumber);
        checkSocRules(soc);

        this.id = id;
        this.name = name;
        this.function = function;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.soc = soc;
    }

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getSoc() {
        return soc;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getFunction() {
        return function;
    }


    /**
     * checks if the parameter meets the requirements
     *
     * @param name - name
     */
    private void checkNameRules(String name) {
        if (StringUtils.isBlank(name))
            throw new NameException("Name cannot be blank");
        if (!name.matches(Constants.ONLY_LETTERS))
            throw new NameException("Name only accepts letters");
        if (name.length() > Constants.NAME_EMPLOYEE_MAX_LENGTH)
            throw new NameException("Name cannot have more than " + Constants.NAME_EMPLOYEE_MAX_LENGTH + Constants.CODE_MESSAGE);
    }


    /**
     * checks if the parameter meets the requirements
     *
     * @param phoneNumber - phone number
     */
    private void checkPhoneNumberRules(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber))
            throw new PhoneNumberException("Phone Number cannot be blank");
        if (!(phoneNumber.matches(Constants.ONLY_NUMBERS)))
            throw new PhoneNumberException("Phone Number only accepts numbers");
        if (phoneNumber.length() != Constants.PHONE_NUMBER_LENGTH)
            throw new PhoneNumberException("Phone Number must have " + Constants.PHONE_NUMBER_LENGTH + Constants.CODE_MESSAGE);
    }


    /**
     * checks if the parameter meets the requirements
     *
     * @param address - address
     */
    private void checkAddressRules(String address) {
        if (StringUtils.isBlank(address))
            throw new AddressException("Address cannot be blank");
        if (!address.matches(Constants.NUMBERS_LETTERS))
            throw new AddressException("Address only accepts letters and numbers");
        if (address.length() > Constants.ADDRESS_MAX_LENGTH)
            throw new AddressException("Address cannot have more than " + Constants.ADDRESS_MAX_LENGTH + Constants.CODE_MESSAGE);
    }


    /**
     * checks if the parameter meets the requirements
     *
     * @param soc - Standard Occupational Classification
     */
    private void checkSocRules(String soc) {
        if (StringUtils.isBlank(soc))
            throw new SocException("soc number cannot be blank.");
        if (!soc.matches(Constants.ONLY_NUMBERS))
            throw new SocException("soc number only accepts numbers");
        if (soc.length() != Constants.SOC_LENGTH)
            throw new SocException("soc number must have " + Constants.SOC_LENGTH + Constants.CODE_MESSAGE);
    }


    /**
     * checks if the parameter meets the requirements
     *
     * @param email - email
     */
    private void checkEmail(String email) {
        if (StringUtils.isBlank(email))
            throw new EmailException("Email cannot be blank");
        if (!checkFormat(email))
            throw new EmailException("Email needs to have the correct format");
    }


    private boolean checkFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }
}
