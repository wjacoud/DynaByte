package app.controller;

import app.domain.model.*;
import app.domain.model.client.Client;
import app.domain.model.employees.*;
import app.domain.model.stores.*;
import app.domain.model.test.Test;
import app.domain.model.test.TestDiagnosis;
import app.domain.model.test.TestReport;
import app.domain.model.testComponents.Parameter;
import app.domain.model.testComponents.ParameterCategory;
import app.domain.model.testComponents.TestType;
import app.domain.shared.Constants;
import auth.AuthFacade;
import auth.UserSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {

    private Company company;
    private AuthFacade authFacade;

    private App() {
        Properties props = getProperties();
        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION), Constants.REPORT_HOURS, Constants.REPORT_MINUTES, Constants.REPORT_SECONDS);
        this.authFacade = this.company.getAuthFacade();
        bootstrap();
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
        this.authFacade = this.company.getAuthFacade();
    }

    public UserSession getCurrentUserSession() {
        return this.authFacade.getCurrentUserSession();
    }

    public boolean doLogin(String email, String pwd) {
        return this.authFacade.doLogin(email, pwd).isLoggedIn();
    }

    public void doLogout() {
        this.authFacade.doLogout();
    }

    private Properties getProperties() {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "Many Labs");


        // Read configured values
        try {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException ex) {

        }
        return props;
    }


    private void bootstrap(){
        this.authFacade.addUserRole(Constants.ROLE_ADMIN, Constants.ROLE_ADMIN);
        this.authFacade.addUserRole(Constants.ROLE_RECEPTIONIST, Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserRole(Constants.ROLE_MEDICALLABTECHNICIAN, Constants.ROLE_MEDICALLABTECHNICIAN);
        this.authFacade.addUserRole(Constants.ROLE_LABORATORYCOORDINATOR, Constants.ROLE_LABORATORYCOORDINATOR);
        this.authFacade.addUserRole(Constants.ROLE_SPECIALISTDOCTOR, Constants.ROLE_SPECIALISTDOCTOR);
        this.authFacade.addUserRole(Constants.ROLE_CHEMISTRYTECHNOLOGIST, Constants.ROLE_CHEMISTRYTECHNOLOGIST);
        this.authFacade.addUserRole(Constants.ROLE_CLIENT, Constants.ROLE_CLIENT);

        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", "123456", Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("Main Receptionist", "recep@lei.sem2.pt", "123456", Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserWithRole("Main Medical Lab Technician", "medicaltech@lei.sem2.pt", "123456", Constants.ROLE_MEDICALLABTECHNICIAN);
        this.authFacade.addUserWithRole("Main Chemistry Technologist", "chemistry@lei.sem2.pt", "123456", Constants.ROLE_CHEMISTRYTECHNOLOGIST);
        this.authFacade.addUserWithRole("Main Specialist Doctor", "specialist@lei.sem2.pt", "123456", Constants.ROLE_SPECIALISTDOCTOR);
        this.authFacade.addUserWithRole("Main Laboratory coordinator", "coordinator@lei.sem2.pt", "123456", Constants.ROLE_LABORATORYCOORDINATOR);
        this.authFacade.addUserWithRole("Main Client", "client@lei.sem2.pt", "123456", Constants.ROLE_CLIENT);

        EmployeeStore employeeStore = company.getEmployeeStore();

        Administrator admin = new Administrator("ma0001", "Main Administrator", "address", "10101010101", "admin@lei.sem2.pt", "1010");
        employeeStore.saveAdministrator(admin);

        Receptionist recep = new Receptionist("mr0002", "Main Receptionist", "address", "10101010107", "recep@lei.sem2.pt", "1010");
        employeeStore.saveReceptionist(recep);

//        Receptionist recep1 = new Receptionist("mr00022", "Main Receptionist two", "addresss", "10101750107", "recep2@lei.sem2.pt", "1010");
//        employeeStore.saveReceptionist(recep1);

        MedicalLabTechnician mlt = new MedicalLabTechnician("mmlt0003", "Main Medical Lab Technician", "address", "10101810107", "medicallabtechnician@lei.sem2.pt", "1010");
        employeeStore.saveMedicalLabTechnician(mlt);

        SpecialistDoctor sd = new SpecialistDoctor("spd0001", "Main Specialist Doctor", "address", "10101810109", "specialist@lei.sem2.pt", "1010", "0000");
        employeeStore.saveSpecialistDoctor(sd);

        LaboratoryCoordinator lc = new LaboratoryCoordinator("mlc0001", "Main Laboratory Coordinator", "address", "13101819109", "laboratorycoordinator@lei.sem2.pt", "1010");
        employeeStore.saveLaboratoryCoordinator(lc);
        ClinicalStore clinicalStore = company.getClinicalStore();

        String[] tests = {"Blood", "Covid"};
        Clinical clinical = new Clinical("001DO", "clinical1", "address1", "1234567890", "66666666666", tests);
        clinicalStore.saveClinical(clinical);
        Clinical clinical1 = new Clinical("001LN", "clinical2", "address2", "1234567891", "66666666661", tests);
        clinicalStore.saveClinical(clinical1);
        Clinical clinical2 = new Clinical("001LR", "clinical3", "address3", "1234567892", "66666666663", tests);
        clinicalStore.saveClinical(clinical2);
        Clinical clinical3 = new Clinical("001MA", "clinical4", "address4", "1234567893", "66666666664", tests);
        clinicalStore.saveClinical(clinical3);
        Clinical clinical4 = new Clinical("001SO", "clinical5", "address5", "1234567895", "66666666665", tests);
        clinicalStore.saveClinical(clinical4);
        Clinical clinical5 = new Clinical("001WA", "clinical6", "address6", "1234567897", "66666666667", tests);
        clinicalStore.saveClinical(clinical5);

        ClientsStore clientstore = company.getClientStore();
        String dateStr = "11/11/2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            //empty
        }

        Client client = new Client("1234567890123450", "1000003136", date, "1100003136", "clientB", "12345678900", "male");
        client.setEmail("client@lei.sem2.pt");
        clientstore.saveClient(client);

        Client client2 = new Client("1234567890123455", "1000003137", date, "2100003136", "clientA", "12348678900", "male");
        client2.setEmail("client2@lei.sem2.pt");
        clientstore.saveClient(client2);

        TestTypeStore testTypeStore = company.getTestTypeStore();

        ParameterCategoryStore parameterCategoryStore = company.getParameterCategoryStore();
        ParameterCategory parameterCategory = new ParameterCategory("12345", "hemogram");
        ParameterCategory parameterCategory0 = new ParameterCategory("12340", "choles");
        ParameterCategory parameterCategory1 = new ParameterCategory("12346", "covid");
        parameterCategoryStore.saveParameterCategory(parameterCategory);
        parameterCategoryStore.saveParameterCategory(parameterCategory0);
        parameterCategoryStore.saveParameterCategory(parameterCategory1);

        ParameterCategory[] categories = new ParameterCategory[2];
        categories[0] = parameterCategory;
        categories[1] = parameterCategory0;
        ParameterCategory[] categories1 = new ParameterCategory[1];
        categories1[0] = parameterCategory1;

        TestType bloodTest = new TestType("BL000", "Blood", "Needle", categories);
        TestType covidTest = new TestType("COV19", "Covid", "Swap", categories1);

        testTypeStore.saveTestType(bloodTest);
        testTypeStore.saveTestType(covidTest);

        ParameterStore parameterStore = company.getParameterStore();

        parameterStore.createParameter("IgGAN", "paramCv1", "000", parameterCategory1);
        parameterStore.saveParameter();
        parameterStore.createParameter("HB000", "paramBl1", "bbb", parameterCategory);
        parameterStore.saveParameter();
        parameterStore.createParameter("WBC00", "paramBl2", "ccc", parameterCategory);
        parameterStore.saveParameter();
        parameterStore.createParameter("PLT00", "paramBl3", "ddd", parameterCategory);
        parameterStore.saveParameter();
        parameterStore.createParameter("RBC00", "paramBl4", "eee", parameterCategory);
        parameterStore.saveParameter();
        parameterStore.createParameter("HDL00", "paramBl5", "fff", parameterCategory0);

        TestStore testStore = company.getTestStore();

        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(parameterStore.getParameterList().get(0));

        DateTimeFormatter formatter;
        LocalDateTime dateTime;
        String str;
        String idTest;
        Test test;
        TestDiagnosis testDiagnosis;
        TestReport testReport;


        //TEST 1
        idTest = "000010000001";
        test = new Test("1100003136", idTest, "123156789020", covidTest, "09/06/2021 12:17");
        test.testParameterCreator(idTest, parameterList);
        testStore.saveTest(test);
        try {
            test.addTestParameterResult("IgGAN", 0.6);
        }catch (Exception error){
            //empty
        }
        testDiagnosis = new TestDiagnosis();
        testDiagnosis.createTestDiagnosis(test.getTestParameterList());
        str = "12/06/2021 12:17";
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dateTime = LocalDateTime.parse(str, formatter);
        testReport = new TestReport(idTest, testDiagnosis, "report1", dateTime);
        test.setReport(testReport);
        test.registerValidationDate(dateTime);
        test.setState(Test.State.VALIDATED);



        //TEST 2
        idTest = "000020000001";
        test = new Test("2100003136", idTest, "223156789020", covidTest, "09/06/2021 13:17");
        test.testParameterCreator(idTest, parameterList);
        testStore.saveTest(test);
        try {
            test.addTestParameterResult("IgGAN", 1.5);
        }catch (Exception error){
            //empty
        }
        testDiagnosis = new TestDiagnosis();
        testDiagnosis.createTestDiagnosis(test.getTestParameterList());
        str = "12/06/2021 13:17";
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dateTime = LocalDateTime.parse(str, formatter);
        testReport = new TestReport(idTest, testDiagnosis, "report2", dateTime);
        test.setReport(testReport);
        test.setState(Test.State.VALIDATED);
        test.registerValidationDate(dateTime);

        //TEST 3
        idTest = "000030000001";
        test = new Test("1100003136", idTest, "323156789020", covidTest, "09/06/2021 14:17");
        test.testParameterCreator(idTest, parameterList);
        testStore.saveTest(test);
        try {
            test.addTestParameterResult("IgGAN", 1.5);
        }catch (Exception error){
            //empty
        }
        testDiagnosis = new TestDiagnosis();
        testDiagnosis.createTestDiagnosis(test.getTestParameterList());
        str = "12/06/2021 14:17";
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dateTime = LocalDateTime.parse(str, formatter);
        testReport = new TestReport(idTest, testDiagnosis, "report3", dateTime);
        test.setReport(testReport);
        test.setState(Test.State.VALIDATED);
        test.registerValidationDate(dateTime);

        //TEST 4
        idTest = "000040000001";
        test = new Test("2100003136", idTest, "423156789020", covidTest, "10/06/2021 15:17");
        test.testParameterCreator(idTest, parameterList);
        testStore.saveTest(test);
        try {
            test.addTestParameterResult("IgGAN", 0.5);
        }catch (Exception error){
            //empty
        }
        testDiagnosis = new TestDiagnosis();
        testDiagnosis.createTestDiagnosis(test.getTestParameterList());
        str = "13/06/2021 15:17";
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dateTime = LocalDateTime.parse(str, formatter);
        testReport = new TestReport(idTest, testDiagnosis, "report4", dateTime);
        test.setReport(testReport);
        test.setState(Test.State.VALIDATED);
        test.registerValidationDate(dateTime);

        //TEST 5
        idTest = "000050000001";
        test = new Test("2100003136", idTest, "523156789020", covidTest, "10/06/2021 15:17");
        test.testParameterCreator(idTest, parameterList);
        testStore.saveTest(test);
        try {
            test.addTestParameterResult("IgGAN", 1.6);
        }catch (Exception error){
            //empty
        }
        testDiagnosis = new TestDiagnosis();
        testDiagnosis.createTestDiagnosis(test.getTestParameterList());
        str = "13/06/2021 15:17";
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dateTime = LocalDateTime.parse(str, formatter);
        testReport = new TestReport(idTest, testDiagnosis, "report4", dateTime);
        test.setReport(testReport);
        test.setState(Test.State.VALIDATED);
        test.registerValidationDate(dateTime);

        //TEST 6
        idTest = "000060000001";
        test = new Test("2100003136", idTest, "623156789020", covidTest, "08/06/2021 15:17");
        test.testParameterCreator(idTest, parameterList);
        testStore.saveTest(test);
        try {
            test.addTestParameterResult("IgGAN", 2.1);
        }catch (Exception error){
            //empty
        }
        testDiagnosis = new TestDiagnosis();
        testDiagnosis.createTestDiagnosis(test.getTestParameterList());
        str = "13/06/2021 15:17";
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dateTime = LocalDateTime.parse(str, formatter);
        testReport = new TestReport(idTest, testDiagnosis, "report4", dateTime);
        test.setReport(testReport);
        test.setState(Test.State.VALIDATED);
        test.registerValidationDate(dateTime);

        //TEST 7
        idTest = "000070000001";
        test = new Test("2100003136", idTest, "723156789020", covidTest, "14/06/2021 15:17");
        test.testParameterCreator(idTest, parameterList);
        testStore.saveTest(test);
        try {
            test.addTestParameterResult("IgGAN", 1.9);
        }catch (Exception error){
            //empty
        }
        testDiagnosis = new TestDiagnosis();
        testDiagnosis.createTestDiagnosis(test.getTestParameterList());
        str = "13/06/2021 15:17";
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dateTime = LocalDateTime.parse(str, formatter);
        testReport = new TestReport(idTest, testDiagnosis, "report4", dateTime);
        test.setReport(testReport);
        test.setState(Test.State.VALIDATED);
        test.registerValidationDate(dateTime);

        //TEST 8
        idTest = "000080000001";
        test = new Test("2100003136", idTest, "823156789020", covidTest, "08/06/2021 15:17");
        test.testParameterCreator(idTest, parameterList);
        testStore.saveTest(test);
        try {
            test.addTestParameterResult("IgGAN", 2.4);
        }catch (Exception error){
            //empty
        }
        testDiagnosis = new TestDiagnosis();
        testDiagnosis.createTestDiagnosis(test.getTestParameterList());
        str = "13/06/2021 15:17";
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        dateTime = LocalDateTime.parse(str, formatter);
        testReport = new TestReport(idTest, testDiagnosis, "report4", dateTime);
        test.setReport(testReport);
        test.setState(Test.State.VALIDATED);
        test.registerValidationDate(dateTime);


    }

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;

    public static App getInstance() {
        if (singleton == null) {
            synchronized (App.class) {
                singleton = new App();
            }
        }
        return singleton;
    }
}
