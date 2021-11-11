package app.domain.model.testComponents;

import app.domain.shared.Constants;
import org.apache.commons.lang3.StringUtils;

/**
 * Class responsible for the creation of new ParameterCategory objects
 *
 * @author José Cruz 1201401
 */
public class ParameterCategory implements java.io.Serializable{

    private String code;
    private String name;

    /**
     * Constructor to create the new object and to check if every field is correctly written
     *
     * @param code - The Category code that identifies it in a unique way
     * @param name - The name of the Category
     */
    public ParameterCategory(String code, String name) {
        boolean error = false;
        try {
            checkNameRules(name);
        } catch (Exception e) {
            error = true;
        }

        try {
            checkCodeRules(code);
        } catch (Exception e) {
            error = true;
        }

        if (!error) {
            this.code = code;
            this.name = name;
        } else {
            throw new IllegalArgumentException("Error with creation");
        }
    }

    /**
     * Method to get the Category code
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Method to get the Category name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the code obeys to the acceptance criteria
     *
     * @param code - The Category code that identifies it in a unique way
     */
    private void checkCodeRules(String code) {
        if (StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be blank.");
        if ((code.length() != Constants.PARAMETER_CATEGORY_CODE_LENGTH))
            throw new IllegalArgumentException("Code must have " + Constants.PARAMETER_CATEGORY_CODE_LENGTH + " chars.");
    }

    /**
     * Checks if the name obeys to the acceptance criteria
     *
     * @param name - The name of the Category
     */
    private void checkNameRules(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if ((name.length() > Constants.PARAMETER_CATEGORY_NAME_LENGTH))
            throw new IllegalArgumentException("Name mustn't have more than " + Constants.PARAMETER_CATEGORY_NAME_LENGTH + " characters.");
    }

}
