package app.domain.model.test;

import app.domain.model.testComponents.Parameter;
import app.domain.model.testComponents.TestType;
import app.domain.model.adpaters.RefValueAdapter;
import app.domain.shared.Constants;
import app.domain.shared.exceptions.NhsNumberException;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * class responsible for the Test
 *
 * @author Gonçalo Pereira 1201500 and Ricardo Faria 1201405
 */
public class Test implements java.io.Serializable {
    private String tin;
    private String idTest;
    private String nhsCode;
    private LocalDateTime date;
    private LocalDateTime analyzedDate;
    private LocalDateTime validationDate;
    private TestReport report;
    private TestType testType;
    private State state;
    private List<TestParameter> testParameterList;


    /**
     * constructor that create a full test object
     *
     * @param tin      - tax identification number (tin)
     * @param idTest   - test id
     * @param nhsCode  - national health service (nhs)
     * @param testType - test type
     * @param date     - date
     */
    public Test(String tin, String idTest, String nhsCode, TestType testType, String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        checkNshCode(nhsCode);

        this.tin = tin;
        this.idTest = idTest;
        this.date = LocalDateTime.parse(date, dtf);
        this.analyzedDate = null;
        this.validationDate = null;
        this.report = null;
        this.nhsCode = nhsCode;
        this.testType = testType;
        this.state = State.REGISTERED;
        this.testParameterList = new ArrayList<>();
    }


    /**
     * Check if national healthcare service (nhs) respects the acceptance criteria
     *
     * @param nhsCode - national healthcare service (nhs)
     */
    private void checkNshCode(String nhsCode) {
        if (StringUtils.isBlank(nhsCode))
            throw new NhsNumberException("The NHS code cannot be blank");
        if (!nhsCode.matches("[ a-zA-Z0-9]+$"))
            throw new NhsNumberException("NHS code only accepts letters and numbers");
        if (nhsCode.length() != Constants.NHS_CODE_LENGTH)
            throw new NhsNumberException("the nhs code must be " + Constants.NHS_CODE_LENGTH + " characters long");
    }


    /**
     * possible test states
     */
    public enum State {
        REGISTERED, SAMPLE_COLLECTED, ANALYZED, DIAGNOSED, VALIDATED
    }


    /**
     * return the tax identification number (tin
     *
     * @return tax identification number (tin)
     */
    public String getTin() {
        return tin;
    }

    /**
     * return the Test Type
     *
     * @return Test Type
     */
    public TestType getTestType() {
        return testType;
    }

    /**
     * return the Test Type
     *
     * @return Test Type
     */
    public String getTestTypeDescription() {
        return testType.getDescription();
    }

    /**
     * return test id
     *
     * @return test id
     */
    public String getTestCode() {
        return idTest;
    }


    /**
     * return the date string
     *
     * @return date string
     */
    public String getDateStr() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(dtf);
    }

    /**
     * return the date
     *
     * @return date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * return the analyzedDate
     *
     * @return analyzedDate
     */
    public LocalDateTime analyzedDate() {
        return analyzedDate;
    }

    /**
     * return the validationDate
     *
     * @return validationDate
     */
    public LocalDateTime getValidationDate() {
        return validationDate;
    }


    /**
     * return the national health service (nhs)
     *
     * @return national health service (nhs)
     */
    public String getNhsCode() {
        return nhsCode;
    }


    /**
     * return the state
     *
     * @return state
     */
    public State getState() {
        return state;
    }


    /**
     * return the parameter list
     *
     * @return parameter list
     */
    public List<Parameter> getParameterList() {
        List<Parameter> parameterList = new ArrayList<>();

        for (TestParameter testParameter : testParameterList) {
            parameterList.add(testParameter.getParameter());
        }

        return parameterList;
    }

    public TestReport getReport() {
        return report;
    }

    /**
     * return the test parameter list
     *
     * @return test parameter list
     */
    public List<TestParameter> getTestParameterList() {
        return testParameterList;
    }


    /**
     * change the state of the test
     *
     * @param state test state
     */
    public void setState(State state) {
        this.state = state;
    }

    public void setReport(TestReport report) {
        this.report = report;
    }


    /**
     * creates a new test parameter for each parameter entered
     *
     * @param idTest        test id
     * @param parameterList selected parameter list
     */
    public void testParameterCreator(String idTest, List<Parameter> parameterList) {
        TestParameter testParameter;
        for (Parameter parameter : parameterList) {
            testParameter = new TestParameter(idTest, parameter);
            testParameterList.add(testParameter);
        }
    }

    /**
     * register a new analyzed date
     *
     * @param analyzedDate analyzed date
     */
    public void registerAnalyzedDate(LocalDateTime analyzedDate) {
        this.analyzedDate = analyzedDate;
    }

    /**
     * register a new validation date
     *
     * @param validationDate validation date
     */
    public void registerValidationDate(LocalDateTime validationDate) {
        this.validationDate = validationDate;
    }


    /**
     * creates a new test parameter result for the parameter entered
     *
     * @param parameterCode parameter code
     * @param result        result of the parameter
     * @return success of adding the test parameter result
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public boolean addTestParameterResult(String parameterCode, double result) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        TestParameter testParameterActual = null;

        for (TestParameter testParameter : testParameterList) {
            if (testParameter.getParameter().getCode().equalsIgnoreCase(parameterCode)) {
                testParameterActual = testParameter;
            }
        }

        String em = testType.getExternalModule();
        Class<?> oClass = Class.forName(em);
        RefValueAdapter rva = (RefValueAdapter) oClass.newInstance();
        String paramCode = testParameterActual.getParameter().getCode();

        TestParameterResult testParameterResult = new TestParameterResult(paramCode, result);
        testParameterResult.setRefValue(rva.getRefValue(paramCode));

        return testParameterActual.setTestParameterResult(testParameterResult);
    }
}