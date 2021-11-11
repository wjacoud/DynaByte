package app.domain.model.stores;

import app.domain.model.testComponents.ParameterCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for the ParameterCategory management
 *
 * @author José Cruz 1201401
 */
public class ParameterCategoryStore implements java.io.Serializable{

    private List<ParameterCategory> parameterCategoryList;
    private ParameterCategory pc;


    /**
     * Constructor that initialized the List
     */
    public ParameterCategoryStore() {
        parameterCategoryList = new ArrayList<>();
    }


    /**
     * Creates a new ParameterCategory object
     *
     * @param code - The Category code that identifies it in a unique way
     * @param name - The name of the Category
     * @return - pc = new ParameterCategory(code,name)
     */
    public ParameterCategory createParameterCategory(String code, String name) {
        pc = new ParameterCategory(code, name);
        return pc;
    }


    /**
     * Saves the ParameterCategory in the list after verifying if it already exists or not
     *
     * @param pc - Parameter Category
     * @return this.parameterCategoryList.add(pc) - if the validation of the ParameterCategory returns true
     * @return false - if the validation of the Parameter Category returns false
     */
    public boolean saveParameterCategory(ParameterCategory pc) {
        if (!validateParameterCategory(pc))
            return false;
        return parameterCategoryList.add(pc);
    }


    /**
     * Verifies if the ParameteraCategory in the method's parameters already exists in the list
     *
     * @param pc - Parameter Category
     * @return true - if the ParameterCategory doesn't exist yet
     * @return false - if the ParameterCategory is null or it already exists
     */
    public boolean validateParameterCategory(ParameterCategory pc) {
        if (pc == null)
            return false;

        for (ParameterCategory cat : parameterCategoryList) {
            if (pc.getCode().equalsIgnoreCase(cat.getCode())) {
                return false;
            }
        }
        return true;
    }


    /**
     * Method to get the ParameterCategory created
     *
     * @return pc
     */
    public ParameterCategory getPc() {
        return pc;
    }


    /**
     * Method to get the List of ParameterCategory
     *
     * @return parameterCategoryList
     */
    public List<ParameterCategory> getPcList() {
        return parameterCategoryList;
    }


    /**
     * Output to see the list of the Parameter Category
     *
     * @return output
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ParameterCategory cat : parameterCategoryList) {
            sb.append("Code: " + cat.getCode() + "\nName: " + cat.getName() + "\n\n");
        }
        return sb.toString();
    }
}
