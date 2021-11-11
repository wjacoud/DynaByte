package app.controller;

import app.domain.model.*;
import app.domain.model.client.Client;
import app.domain.model.client.ClientNotification;
import app.domain.model.stores.*;
import app.domain.model.test.Test;
import app.domain.model.test.TestDiagnosis;
import app.domain.model.test.TestReport;
import app.domain.model.testComponents.Parameter;
import app.domain.model.testComponents.TestType;
import app.domain.shared.Constants;
import auth.AuthFacade;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * class responsible for the Test
 *
 * @author Gonçalo Pereira 1201500
 */
public class ImportTestsController {


    private AuthFacade authFacade;
    private ClientsStore clientStore;
    private TestStore testStore;
    private TestTypeStore testTypeStore;
    private ParameterStore parameterStore;
    private ClinicalStore clinicalStore;
    private String[] attribute;
    private List<Parameter> parameterList = new ArrayList<>();
    private Test test = null;
    private TestReport testReport; // Include Reports import - Wilson
    private TestDiagnosis testDiagnosis; // Include Diagnostic import - Wilson
    private int errors;
    private List<String> correctLine = new ArrayList<>();
    private List<String> listOfErrors = new ArrayList<>();

    /**
     * Constructor that create a empty ImportTestController object
     */
    public ImportTestsController() {
        App app = App.getInstance();
        Company company = app.getCompany();
        clientStore = company.getClientStore();
        authFacade = company.getAuthFacade();
        testStore = company.getTestStore();
        testTypeStore = company.getTestTypeStore();
        parameterStore = company.getParameterStore();
        clinicalStore = company.getClinicalStore();
    }

    /**
     *
     * @param path - path to the file
     * @return boolean
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public boolean readFile(String path) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        listOfErrors = new ArrayList<>();
        correctLine = new ArrayList<>();
        Client client;
        String line;
        BufferedReader br;
        errors = 0;
        try {
            br = new BufferedReader(new FileReader(path));
        } catch (Exception e) {
            throw new IllegalArgumentException("File not found!");
        }
        int l = 0;
        String attributeOrder = "Test_Code;NHS_Code;Lab_ID;CitizenCard_Number;NHS_Number;TIN;BirthDay;PhoneNumber;Name;E-mail ;Address;TestType;Category;HB000;WBC00;PLT00;RBC00;Category;HDL00;Category;IgGAN;Test_Reg_DateHour;Test_Chemical_DateHour;Test_Doctor_DateHour;Test_Validation_DateHour";
        String attributeOrder2 = "Test_Code;NHS_Code;Lab_ID;CitizenCard_Number;NHS_Number;TIN;BirthDay;PhoneNumber;Name;Email ;Address;TestType;Category;HB000;WBC00;PLT00;RBC00;Category;HDL00;Category;IgGAN;Test_Reg_DateHour;Test_Chemical_DateHour;Test_Doctor_DateHour;Test_Validation_DateHour";
        String attributeOrder3 = "Test_Code;NHS_Code;Lab_ID;CitizenCard_Number;NHS_Number;TIN;BirthDay;PhoneNumber;Name;E/mail ;Address;TestType;Category;HB000;WBC00;PLT00;RBC00;Category;HDL00;Category;IgGAN;Test_Reg_DateHour;Test_Chemical_DateHour;Test_Doctor_DateHour;Test_Validation_DateHour";
        line = br.readLine();

        if (line.equals(attributeOrder) || line.equals(attributeOrder2) || line.equals(attributeOrder3)) {
            while ((line = br.readLine()) != null) {
                l++;
                attribute = line.split(";");

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                String dateStr = attribute[6];
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = null;
                try {
                    date = dateFormat.parse(dateStr);
                } catch (Exception e) {
                    errors++;
                    continue;
                }
                if (!clinicalStore.verifyExistingClinical(attribute[2])) {
                    errors++;
                    listOfErrors.add("clinical not found on line: " + l);
                    continue;
                }
                try {
                    client = clientStore.createClient(attribute[3], attribute[4], date, attribute[5], attribute[8], attribute[7], "female");
                    client.setAddress(attribute[10]);
                } catch (Exception e) {
                    System.err.println(e+ ", error on line: " + l);
                    errors++;
                    listOfErrors.add(e+ ", error on line: " + l);
                    continue;
                }
                TestType testType1 = null;
                for (TestType testType : testTypeStore.getTestTypeList()) {
                    if (attribute[11].equalsIgnoreCase(testType.getDescription())) {
                        testType1 = testType;
                    }
                }
                if (testType1 == null) {
                    System.err.println("test type doesn't exist");
                    errors++;
                    listOfErrors.add("test type does not exist on line: " + l);
                    continue;
                }

                if (!clientStore.verifyExistingClient(attribute[5])) {
                    String password = RandomStringUtils.randomAlphanumeric(10);
                    String email = attribute[9];
                    String name = attribute[8];

                    if (ClientNotification.sendMessage("email", "User: " + email + "\npassword: " + password, client.getTin())) {
                        client.setEmail(email);
                        authFacade.addUserWithRole(name, email, password, Constants.ROLE_CLIENT);
                    }

                    clientStore.saveClient(client);
                }
                if (testStore.verifyExistingNhsCode(attribute[1])) {
                    System.err.println("nhs code already exists, on line: " + l);
                    errors++;
                    listOfErrors.add("nhs code already exists, on line: " + l);
                    continue;
                }

                try {
                    String[] datesArray = new String[4];  // Include reports, dignostic and validation dates import - Wilson
                    for (int i = 0; i < 4; i++) {
                        datesArray[i] = attribute[21 + i];
                        String[] dateTestArr = datesArray[i].split(" ");
                        if (dateTestArr[1].length() == 4) {
                            datesArray[i] = dateTestArr[0] + " 0" + dateTestArr[1];
                        }
                    }

                    test = testStore.createTest(attribute[5], attribute[0], attribute[1], testType1, datesArray[0]);
                    test.registerAnalyzedDate(LocalDateTime.parse(datesArray[1],dtf));
                    test.registerValidationDate(LocalDateTime.parse(datesArray[3],dtf));
                    testDiagnosis = new TestDiagnosis("Diagnostic Unavailable");
                    testReport = new TestReport(attribute[0],testDiagnosis,"Undefined",LocalDateTime.parse(datesArray[3],dtf));
                    test.setReport(testReport);
                } catch (Exception e) {
                    System.err.println(e+", error on line: " + l);
                    errors++;
                    listOfErrors.add(e+ ", error on line: " + l);
                    continue;
                }

                checkExistingParameter();

                test.testParameterCreator(attribute[0], parameterList);

                checkAddTestParameter();
                boolean result = testStore.saveTest(test);
                if (!result) {
                    System.out.println("error with test save");
                } else {
                    test.setState(Test.State.VALIDATED);
                    correctLine.add(Arrays.toString(attribute));
                }
            }
        }else{
            return false;
        }
        return true;
    }

    /**
     *
     * @return - list with all the correct lines from the file
     */
    public List<String> getCorrectLine(){
        return correctLine;
    }

    /**
     * method used to check if the parameters of the file exists in the system
     */
    public void checkExistingParameter() {
        parameterList.clear();
        if (!attribute[13].equalsIgnoreCase("NA")) {
            parameterList.add(parameterStore.getParameterWithCode("HB000"));
        }

        if (!attribute[14].equalsIgnoreCase("NA")) {
            parameterList.add(parameterStore.getParameterWithCode("WBC00"));
        }

        if (!attribute[15].equalsIgnoreCase("NA")) {
            parameterList.add(parameterStore.getParameterWithCode("PLT00"));
        }

        if (!attribute[16].equalsIgnoreCase("NA")) {
            parameterList.add(parameterStore.getParameterWithCode("RBC00"));
        }

        if (!attribute[20].equalsIgnoreCase("NA")) {
            parameterList.add(parameterStore.getParameterWithCode("IgGAN"));
        }
    }

    /**
     * method used to add the values of the parameters from the file
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public void checkAddTestParameter() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (!attribute[13].equalsIgnoreCase("NA")) {
            test.addTestParameterResult("HB000", Double.parseDouble(attribute[13].replace(",", ".")));
        }

        if (!attribute[14].equalsIgnoreCase("NA")) {
            test.addTestParameterResult("WBC00", Double.parseDouble(attribute[14].replace(",", ".")));
        }

        if (!attribute[15].equalsIgnoreCase("NA")) {
            test.addTestParameterResult("PLT00", Double.parseDouble(attribute[15].replace(",", ".")));
        }

        if (!attribute[16].equalsIgnoreCase("NA")) {
            test.addTestParameterResult("RBC00", Double.parseDouble(attribute[16].replace(",", ".")));
        }

        if (!attribute[20].equalsIgnoreCase("NA")) {
            test.addTestParameterResult("IgGAN", Double.parseDouble(attribute[20].replace(",", ".")));
        }
    }

    /**
     * method used to get the number of lines with errors
     * @return - errors
     */
    public int getNumberOfErrors() {
        return errors;
    }

    /**
     * method used to get a list with the errors on each line that have an error
     * @return - list of errors
     */
    public List<String> getListOfErrors() {
        return listOfErrors;
    }
}
