package app.domain.model.client;

import app.domain.shared.Constants;
import app.domain.shared.exceptions.*;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * class responsible for the Client
 *
 * @author Ricardo Faria 1201405
 */
public class Client implements java.io.Serializable {
    private String citizenNumber;
    private String healthcareNumber;
    private String tin;
    private String name;
    private String phoneNumber;
    private String sex;
    private String email;
    private Date birthDate;
    private String address = "undefined";

    /**
     * Constructor that create a full Client object
     *
     * @param citizenNumber    - Client citizen card number
     * @param healthcareNumber - Client national healthcare service (nhs)
     * @param birthDate        - Client birth date (xx/yy/zzz)
     * @param tin              - Client tax identification number (tin)
     * @param name             - Client name
     * @param phoneNumber      - Client phone number
     * @param sex              - Client sex (male/female)
     */
    public Client(String citizenNumber, String healthcareNumber, Date birthDate, String tin, String name, String phoneNumber, String sex) {
        checkCitizenNumberRules(citizenNumber);
        checkHealthcareNumberRules(healthcareNumber);
        checkBirthDateRules(birthDate);
        checkTinRules(tin);
        checkNameRules(name);
        checkPhoneNumberRules(phoneNumber);
        checkSexRules(sex);

        this.citizenNumber = citizenNumber;
        this.healthcareNumber = healthcareNumber;
        this.tin = tin;
        this.birthDate = birthDate;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.email = null;
    }


    /**
     * get the citizen card number
     *
     * @return Client citizen card number
     */
    public String getCitizenNumber() {
        return citizenNumber;
    }


    /**
     * get the national healthcare service (nhs)
     *
     * @return Client national healthcare service (nhs)
     */
    public String getHealthcareNumber() {
        return healthcareNumber;
    }

    /**
     * set address
     *
     * @param address - address
     */
    public void setAddress(String address) {
        checkAddressRules(address);
        this.address = address;
    }

    /**
     * get the address
     *
     * @return Client address
     */
    public String getAddress() {
        return address;
    }

    /**
     * get the tax identification number (tin)
     *
     * @return Client tax identification number (tin)
     */
    public String getTin() {
        return tin;
    }


    /**
     * get the birth date (xx/yy/zzz)
     *
     * @return Client birth date (xx/yy/zzz)
     */
    public Date getBirthDate() {
        return birthDate;
    }


    /**
     * get the name
     *
     * @return Client name
     */
    public String getName() {
        return name;
    }


    /**
     * get the phone number
     *
     * @return Client phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }


    /**
     * get the sex (male/female)
     *
     * @return Client sex (male/female)
     */
    public String getSex() {
        return sex;
    }


    /**
     * get the email
     *
     * @return Client email
     */
    public String getEmail() {
        return email;
    }


    /**
     * set email
     *
     * @param email - email
     */
    public void setEmail(String email) {
        checkEmailRules(email);
        this.email = email;
    }


    /**
     * set citizenNumber
     *
     * @param citizenNumber - citizenNumber
     */
    public void setCitizenNumber(String citizenNumber) {
        checkCitizenNumberRules(citizenNumber);
        this.citizenNumber = citizenNumber;
    }


    /**
     * set healthcareNumber
     *
     * @param healthcareNumber - healthcareNumber
     */
    public void setHealthcareNumber(String healthcareNumber) {
        checkHealthcareNumberRules(healthcareNumber);
        this.healthcareNumber = healthcareNumber;
    }


    /**
     * set tin
     *
     * @param tin - tin
     */
    public void setTin(String tin) {
        checkTinRules(tin);
        this.tin = tin;
    }


    /**
     * set name
     *
     * @param name - name
     */
    public void setName(String name) {
        checkNameRules(name);
        this.name = name;
    }


    /**
     * set phoneNumber
     *
     * @param phoneNumber - phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        checkPhoneNumberRules(phoneNumber);
        this.phoneNumber = phoneNumber;
    }


    /**
     * set sex
     *
     * @param sex - sex
     */
    public void setSex(String sex) {
        checkSexRules(sex);
        this.sex = sex;
    }


    /**
     * set birthDate
     *
     * @param birthDate - birthDate
     */
    public void setBirthDate(Date birthDate) {
        checkBirthDateRules(birthDate);
        this.birthDate = birthDate;
    }


    /**
     * Check if citizen card number respects the acceptance criteria
     *
     * @param citizenNumber - Client citizen card number
     */
    private void checkCitizenNumberRules(String citizenNumber) {
        if (StringUtils.isBlank(citizenNumber))
            throw new CitizenNumberException("Citizen Card Number cannot be blank.");
        if (!citizenNumber.matches(Constants.ONLY_NUMBERS))
            throw new CitizenNumberException("Citizen Card Number can only have numbers.");
        if (citizenNumber.length() != Constants.CITIZEN_CARD_LENGTH)
            throw new CitizenNumberException("Citizen Card Number need to be " + Constants.CITIZEN_CARD_LENGTH + Constants.DIGITS_MESSAGE);
    }


    /**
     * Check if national healthcare service (nhs) respects the acceptance criteria
     *
     * @param healthcareNumber - national healthcare service (nhs)
     */
    private void checkHealthcareNumberRules(String healthcareNumber) {
        if (StringUtils.isBlank(healthcareNumber))
            throw new NhsNumberException("National Healthcare Service Number (NHS) cannot be blank.");
        if (!healthcareNumber.matches(Constants.ONLY_NUMBERS))
            throw new NhsNumberException("National Healthcare Service Number (NHS) can only have numbers.");
        if (healthcareNumber.length() != Constants.HEALTHCARE_NUMBER_LENGTH)
            throw new NhsNumberException("National Healthcare Service Number (NHS) need to be " + Constants.HEALTHCARE_NUMBER_LENGTH + Constants.DIGITS_MESSAGE);
    }


    /**
     * Check if tax identification number (tin) respects the acceptance criteria
     *
     * @param tin - Client tax identification number (tin)
     */
    private void checkTinRules(String tin) {
        if (StringUtils.isBlank(tin))
            throw new TinNumberException("Tax Identification Number (TIN) cannot be blank.");
        if (!tin.matches(Constants.ONLY_NUMBERS))
            throw new TinNumberException("Tax Identification Number (TIN) can only have numbers.");
        if (tin.length() != Constants.TAX_IDENTIFICATION_NUMBER_LENGTH)
            throw new TinNumberException("Tax Identification Number (TIN) need to be " + Constants.TAX_IDENTIFICATION_NUMBER_LENGTH + Constants.DIGITS_MESSAGE);
    }


    /**
     * Calculate the age of the client
     *
     * @param birthDate - Client birth date (xx/yy/zzz)
     * @return - age of the client
     */
    private int calculateAge(Date birthDate) {
        LocalDate date = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(date, LocalDate.now()).getYears();
    }


    /**
     * Check if birth date respects the acceptance criteria
     *
     * @param birthDate - Client birth date (xx/yy/zzz)
     */
    private void checkBirthDateRules(Date birthDate) {
        int age = calculateAge(birthDate);
        if (age < Constants.MIN_AGE)
            throw new BirthDateException("Age cannot be negative");
        if (age > Constants.MAX_AGE)
            throw new BirthDateException("Age cannot be superior of " + Constants.MAX_AGE + " years");
    }


    /**
     * Check if name the respects acceptance criteria
     *
     * @param name - Client name
     */
    private void checkNameRules(String name) {
        if (StringUtils.isBlank(name))
            throw new NameException("Name cannot be blank.");
        if (!name.matches(Constants.ONLY_LETTERS))
            throw new NameException("Name can only have alpha characters");
        if (name.length() >= Constants.NAME_CLIENT_MAX_LENGTH)
            throw new NameException("Name cannot have more than " + Constants.NAME_CLIENT_MAX_LENGTH + Constants.DIGITS_MESSAGE);
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
     * Check if phone number respects the acceptance criteria
     *
     * @param phoneNumber - Client phone number
     */
    private void checkPhoneNumberRules(String phoneNumber) {
        if (StringUtils.isBlank(phoneNumber))
            throw new PhoneNumberException("Phone number cannot be blank.");
        if (!phoneNumber.matches(Constants.ONLY_NUMBERS))
            throw new PhoneNumberException("Phone number can only have numbers.");
        if (phoneNumber.length() != Constants.PHONE_NUMBER_LENGTH)
            throw new PhoneNumberException("Phone number need to be " + Constants.PHONE_NUMBER_LENGTH + " digits");
    }


    /**
     * Check if sex respects the acceptance criteria
     *
     * @param sex - Client sex (male/female)
     */
    private void checkSexRules(String sex) {
        if (!sex.equals("") && (!(sex.equalsIgnoreCase("male") || sex.equalsIgnoreCase("female"))))
            throw new SexException("Invalid Sex");
    }


    /**
     * Check if email respects the acceptance criteria
     *
     * @param email - email
     */
    private void checkEmailRules(String email) {
        if (StringUtils.isBlank(email))
            throw new EmailException("Email cannot be blank");
        if (!checkFormat(email))
            throw new EmailException("Email needs to have the correct format");
    }


    /**
     * checks if the email format is valid
     *
     * @param email - email
     * @return if the email is a valid email
     */
    private boolean checkFormat(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }
}
