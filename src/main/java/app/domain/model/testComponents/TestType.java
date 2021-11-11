package app.domain.model.testComponents;

import app.domain.shared.Constants;
import app.controller.PropertiesController;
import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 * class responsible for the Test Type
 *
 * @author Wilson Junior 1200630 and Ricardo Faria 1201405
 */
public class TestType implements java.io.Serializable{

    private String code;
    private String description;
    private String collectingMethods;
    private ParameterCategory[] categories;
    private String externalModule;

    /**
     * Constructor that create a full Test Type object
     *
     * @param code              - code
     * @param description       - description
     * @param collectingMethods - collecting methods
     */
    public TestType(String code, String description, String collectingMethods, ParameterCategory[] categories) {
        boolean error = false;

        try {
            checkTestTypeCodeRules(code);
        } catch (Exception e) {
            error = true;
        }

        try {
            checkTestTypeDescriptionRules(description);
        } catch (Exception e) {
            error = true;
        }

        try {
            checkTestTypeCollectingMethodsRules(collectingMethods);
        } catch (Exception e) {
            error = true;
        }
        try {
            checkCategories(categories);
        } catch (Exception e) {
            error = true;
        }

        if (!error) {
            this.code = code;
            this.description = description;
            this.collectingMethods = collectingMethods;
            this.categories = categories;
        } else {
            throw new IllegalArgumentException("Error with creation");
        }

        externalModule = setExternalModule(code);
        if (externalModule == null) {
            throw new IllegalArgumentException("There is no API for this type of test");
        }
    }


    /**
     * get the test type code
     *
     * @return test type code
     */
    public String getCode() {
        return code;
    }


    /**
     * get the description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }


    /**
     * get the collecting methods
     *
     * @return - collecting methods
     */
    public String getCollectingMethods() {
        return collectingMethods;
    }


    /**
     * get the categories
     *
     * @return - categories
     */
    public ParameterCategory[] getCategories() {
        return categories;
    }


    /**
     * checks if the parameter meets the requirements
     *
     * @param code - code
     */
    private void checkTestTypeCodeRules(String code) {
        if (StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be blank.");
        if ((code.length() != Constants.TEST_TYPE_CODE_LENGTH))
            throw new IllegalArgumentException("Code must have " + Constants.TEST_TYPE_CODE_LENGTH + Constants.CODE_MESSAGE);
    }


    /**
     * checks if the parameter meets the requirements
     *
     * @param description - description
     */
    private void checkTestTypeDescriptionRules(String description) {
        if (StringUtils.isBlank(description))
            throw new IllegalArgumentException("Description cannot be blank.");
        if ((description.length() > Constants.TEST_TYPE_DESCRIPTION_LENGTH))
            throw new IllegalArgumentException("Description must have maximum of " + Constants.TEST_TYPE_DESCRIPTION_LENGTH + Constants.CODE_MESSAGE);
    }


    /**
     * checks if the parameter meets the requirements
     *
     * @param collectingMethods - collecting methods
     */
    private void checkTestTypeCollectingMethodsRules(String collectingMethods) {
        if (StringUtils.isBlank(collectingMethods))
            throw new IllegalArgumentException("Collecting methods description cannot be blank.");
        if ((collectingMethods.length() > Constants.COLLECTING_METHODS_LENGTH))
            throw new IllegalArgumentException("Collecting methods description must have maximum of " + Constants.COLLECTING_METHODS_LENGTH + Constants.CODE_MESSAGE);
    }


    /**
     * verify if the categories is null.
     *
     * @param categories categories
     */
    private void checkCategories(ParameterCategory[] categories) {
        if (categories == null)
            throw new IllegalArgumentException("Categories cannot be blank");
    }


    /**
     * set the external module to the respective test type
     *
     * @param code test type code
     * @return external module name
     */
    private String setExternalModule(String code) {

        Properties proper = PropertiesController.getProperties();
        if(proper == null){
            throw new IllegalArgumentException();
        }

        if (code.equals("COV19")) {
            return proper.getProperty("covid.API");
        }

        if (code.equals("BL000")) {
            return proper.getProperty("blood.API");
        }

        return null;
    }


    /**
     * return the external module name
     *
     * @return external module name
     */
    public String getExternalModule() {
        return externalModule;
    }
}
