package app.controller;

import app.domain.model.*;
import app.domain.model.stores.ParameterStore;
import app.domain.model.testComponents.ParameterCategory;

/**
 * Class responsible for communicating with the UI when the user wants to create a new Parameter
 * @author José Cruz 1201401
 */
public class ParameterController {
    private final ParameterStore parameterStore;


    /**
     * Constructor that initialized the company and calls for the store
     */
    public ParameterController() {
        App app = App.getInstance();
        Company company = app.getCompany();
        parameterStore = company.getParameterStore();
    }


    /**
     * Initialize a new ParameterStore object through the class Platform and then it creates a new Parameter through the object initialized
     * @param code - identifies the Parameter in a unique way
     * @param name - the name of the Parameter
     * @param description - Short text describing it
     * @param cat - Category it is in
     * @return the parameter created - pc
     */
    public boolean createParameter(String code, String name, String description, ParameterCategory cat) {
        return parameterStore.createParameter(code, name, description, cat) != null;
    }


    /**
     * Asks the ParameterStore object to save the Parameter created
     * @return parameterStore.saveParameter(pc)
     */
    public boolean saveParameter() {
        return parameterStore.saveParameter();
    }


    /**
     * Method to get the ParameterStore object
     * @return parameterStore
     */
    public ParameterStore getList(){
        return parameterStore;
    }

}
