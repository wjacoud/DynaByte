package app.domain.model.stores;

import app.domain.model.testComponents.Parameter;
import app.domain.model.testComponents.ParameterCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for the Parameter management
 *
 * @author José Cruz 1201401
 */
public class ParameterStore implements java.io.Serializable{
    private List<Parameter> parameterList;
    private Parameter pr;

    /**
     * Constructor that initialized the List
     */
    public ParameterStore() {
        parameterList = new ArrayList<>();
    }


    /**
     * Creates a new Parameter object
     *
     * @param code        - identifies the Parameter in a unique way
     * @param name        - the name of the Parameter
     * @param description - Short text describing it
     * @param cat         - Category it is in
     * @return pr = new Parameter(code,name,description,cat)
     */
    public Parameter createParameter(String code, String name, String description, ParameterCategory cat) {
        pr = new Parameter(code, name, description, cat);
        return pr;
    }


    /**
     * Verifies if the Parameter in the method's parameters already exists in the list or not
     *
     * @param pc - Parameter
     * @return true - if the Parameter doesn't exist in the list
     * @return false - if the Parameter is null or already exists in the list
     */
    public boolean validateParameter(Parameter pc) {
        if (pc == null)
            return false;

        for (Parameter cat : parameterList) {
            if (pc.getCode().equalsIgnoreCase(cat.getCode())) {
                return false;
            }
        }
        return true;
    }


    /**
     * Saves the Parameter in the list after verifying if it exists or not in the list
     *
     * @return this.parameterList.add(pc) - if the Parameter doesn't exist in the list yet
     * @return false - if the Parameter already exists
     */
    public boolean saveParameter() {
        if (!validateParameter(pr))
            return false;
        return parameterList.add(pr);
    }


    /**
     * Gets the list of the existing ParameterCategory
     *
     * @param store - ParameterCategory Object
     * @return store.getPcList()
     */
    public List<ParameterCategory> getParameterCategory(ParameterCategoryStore store) {
        return store.getPcList();
    }

    public List<Parameter> getParameterList() {
        return parameterList;
    }

    /**
     * Method to get the Parameter
     *
     * @return pr - Parameter
     */
    public Parameter getPr() {
        return pr;
    }

    public Parameter getParameterWithCode(String code) {
        for(Parameter par : parameterList) {
            if(par.getCode().equalsIgnoreCase(code)){
                return par;
            }
        }
        return null;
    }


    /**
     * Output to see the list of Parameter
     *
     * @return output
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Parameter par : this.parameterList) {
            sb.append("Code: ").append(par.getCode()).append("\nName: ").append(par.getName()).append("\nDescription: ").append(par.getDescription()).append("\nParameter Category Code: ").append(par.getCat().getCode()).append("\nParameter Category Name: ").append(par.getCat().getName()).append("\n\n");
        }
        return sb.toString();
    }


}
