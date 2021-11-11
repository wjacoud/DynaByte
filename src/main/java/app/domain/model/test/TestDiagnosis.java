package app.domain.model.test;

import java.util.List;


/**
 * Class responsible for the Test Diagonist
 *
 * @author José Cruz 1201401
 */
public class TestDiagnosis implements java.io.Serializable{

    private String diagnosis;

    /**
     * Constructor that instantiates the TestDiagnosis object
     * @param diagnosis - the string that contains the diagnosis information
     */
    public TestDiagnosis(String diagnosis){
        this.diagnosis = diagnosis;
    }

    /**
     * Empty constructor used to instantiate an empty TestDiagnosis object to call the createTestDiagnosis method
     */
    public TestDiagnosis(){

    }

    /**
     * Method that creates the string that contains every parameter from the chosen test and says if the results of each parameter is a normal or abnormal value according to the reference values
     * @param testParameterList - List of parameters to be diagnosed
     * @return - returns the TestDiagnosis object with the string created
     */
    public TestDiagnosis createTestDiagnosis(List<TestParameter> testParameterList){
        StringBuilder sb = new StringBuilder();

        for (TestParameter tp : testParameterList){
            if(tp.getTestParameterResult().getResult() >= tp.getTestParameterResult().getRefValue().getMinRefValue() && tp.getTestParameterResult().getResult() <= tp.getTestParameterResult().getRefValue().getMaxRefValue()){
                sb.append(tp.getParameter().getName() + " ---- Normal Result\n");
            } else {
                sb.append(tp.getParameter().getName() + " ---- Abnormal Result\n");
            }
        }

        return new TestDiagnosis(sb.toString());
    }

    /**
     * Method that returns the string that contains the diagnosis information
     * @return - diagnosis
     */
    public String getDiagnosis() {
        return diagnosis;
    }
}
