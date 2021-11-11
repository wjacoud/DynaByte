package app.controller;

import app.domain.model.*;
import app.domain.model.stores.ParameterCategoryStore;
import app.domain.model.testComponents.ParameterCategory;

/**
 * Class responsible for communicating with the UI when the user wants to create a new ParameterCategory
 * @author José Cruz 1201401
 */
public class ParameterCategoryController {

    private ParameterCategory pc = null;
    private final ParameterCategoryStore paramCatStore;


    /**
     * Constructor that initialize the company and calls for the store
     */
    public ParameterCategoryController() {
        App app = App.getInstance();
        Company company = app.getCompany();
        paramCatStore = company.getParameterCategoryStore();
    }


    /**
     * Initialize a new ParameterCategoryStore object through the class Platform and then creates a new ParameterCategory through the object initialized
     * @param code - The Category code that identifies it in a unique way
     * @param name - The name of the Category
     * @return The category created - pc
     */
    public boolean createParameterCategory(String code, String name) {
        pc = paramCatStore.createParameterCategory(code, name);

        return pc != null;
    }


    /**
     * Asks the ParameterCategoryStore object to save the ParameterCategory created
     * @return paramCatStore.saveParameterCategory(pc)
     */
    public boolean saveParameterCategory() {
        return paramCatStore.saveParameterCategory(pc);
    }


    /**
     * Method to get the ParameterCategoryStore object
     * @return paramCatStore
     */
    public ParameterCategoryStore getList(){
        return paramCatStore;
    }
}
