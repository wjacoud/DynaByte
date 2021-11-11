package app.domain.model.testComponents;

import app.domain.shared.Constants;
import org.apache.commons.lang3.StringUtils;

/**
 * Class responsible for the creation of new Parameter objects
 *
 * @author José Cruz 1201401
 */
public class Parameter implements java.io.Serializable{
    private String code;
    private String name;
    private String description;
    private ParameterCategory cat;

    /**
     * Constructor to create a new object and to check if every field is correctly written
     *
     * @param code        - identifies the Parameter in a unique way
     * @param name        - the name of the Parameter
     * @param description - Short text describing is
     * @param cat         - Category it is in
     */
    public Parameter(String code, String name, String description, ParameterCategory cat) {

        boolean error = false;

        try {
            checkCodeRules(code);
        } catch (Exception e) {
            error = true;
        }

        try {
            checkNameRules(name);
        } catch (Exception e) {
            error = true;
        }

        try {
            checkDescriptionRules(description);
        } catch (Exception e) {
            error = true;
        }

        try {
            checkCategoryRules(cat);
        } catch (Exception e) {
            error = true;
        }

        if (!error) {
            this.code = code;
            this.name = name;
            this.description = description;
            this.cat = cat;
        } else {
            throw new IllegalArgumentException("Error with creation");
        }
    }

    /**
     * Method to get the code
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Method to get the name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get the description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method to get the Category
     *
     * @return cat
     */
    public ParameterCategory getCat() {
        return cat;
    }

    /**
     * Checks if the name obeys to the acceptance criteria
     *
     * @param code - identifies the Parameter in a unique way
     */
    private void checkCodeRules(String code) {
        if (StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be blank.");
        if ((code.length() != Constants.PARAMETER_CODE_LENGTH))
            throw new IllegalArgumentException("Code must have " + Constants.PARAMETER_CODE_LENGTH + " chars.");
    }

    /**
     * Checks if the name obeys to the acceptance criteria
     *
     * @param name - the name of the Parameter
     */
    private void checkNameRules(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if ((name.length() > Constants.PARAMETER_NAME_LENGTH))
            throw new IllegalArgumentException("Name mustn't have more than " + Constants.PARAMETER_NAME_LENGTH + " characters.");
    }

    /**
     * Checks if the description obeys to the acceptance criteria
     *
     * @param description - Short text describing is
     */
    private void checkDescriptionRules(String description) {
        if (StringUtils.isBlank(description)) {
            throw new IllegalArgumentException("Description cannot be blank.");
        }
        if ((description.length() > Constants.PARAMETER_DESCRIPTION_LENGTH))
            throw new IllegalArgumentException("Name mustn't have more than " + Constants.PARAMETER_DESCRIPTION_LENGTH + " characters.");
    }

    /**
     * Checks if the Category obeys to the acceptance criteria
     *
     * @param cat - Category it is in
     */
    private void checkCategoryRules(ParameterCategory cat) {
        if (cat == null) {
            throw new IllegalArgumentException("Category cannot be blank.");
        }
    }
}

