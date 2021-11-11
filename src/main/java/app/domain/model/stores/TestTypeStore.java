package app.domain.model.stores;

import app.domain.model.testComponents.ParameterCategory;
import app.domain.model.testComponents.TestType;

import java.util.ArrayList;
import java.util.List;

/**
 * class responsible for the TestTypeStore
 *
 * @author Gonçalo Pereira 1201500
 */
public class TestTypeStore implements java.io.Serializable{

    private List<TestType> testTypeList;



    /**
     * Constructor that create a empty list of TestType type
     */
    public TestTypeStore() {
        testTypeList = new ArrayList<>();
    }


    /**
     * Return all the test types
     *
     * @return test type list
     */
    public List<TestType> getTestTypeList() {
        return testTypeList;
    }

    public ParameterCategory[] getParameterCategory(String code) {
         ParameterCategory[] emptyArray = new ParameterCategory[0]; // empty array created only to return something empty instead of null to avoid a code smell
         for(TestType tt : testTypeList){
             if(code.equalsIgnoreCase(tt.getCode())){
                 return tt.getCategories();
             }
         }
         return emptyArray;
    }

    public TestType createTestType(String code, String description, String collectingMethods, ParameterCategory[] categories) {
        return new TestType(code, description, collectingMethods, categories);
    }


    public boolean saveTestType(TestType testType) {
        if (validateTestType(testType)) {
            testTypeList.add(testType);
            return true;
        }
        return false;
    }


    public boolean validateTestType(TestType testType) {
        if (testType == null)
            return false;

        for (TestType testType1 : testTypeList) {
            if (testType1.getCode().equalsIgnoreCase(testType.getCode())) {
                return false;
            }
        }

        return true;
    }
}
