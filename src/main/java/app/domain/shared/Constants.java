package app.domain.shared;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>, Wilson Junior 1200630, José Cruz 1201401, Ricardo Faria, 1201405, Rodrigo Oliveira 1201406, Gonçalo Pereira 1201500
 */
public class Constants {

    /**
     * Empty Constructor
     */
    public Constants() {
        //Empty Constructor
    }

    /**
     * Administrator role name
     */
    public static final String ROLE_ADMIN = "ADMINISTRATOR";

    /**
     * Receptionist role name
     */
    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";

    /**
     * Chemistry Technologist role name
     */
    public static final String ROLE_CHEMISTRYTECHNOLOGIST = "CHEMISTRYTECHNOLOGIST";

    /**
     * Laboratory Coordinator role name
     */
    public static final String ROLE_LABORATORYCOORDINATOR = "LABORATORYCOORDINATOR";

    /**
     * Medical Lab Technician role name
     */
    public static final String ROLE_MEDICALLABTECHNICIAN = "MEDICALLABTECHNICIAN";

    /**
     * Specialist Doctor role name
     */
    public static final String ROLE_SPECIALISTDOCTOR = "SPECIALISTDOCTOR";

    /**
     * Client role name
     */
    public static final String ROLE_CLIENT = "CLIENT";

    /**
     * based on acceptance criteria, the citizen card number (cc) can only have 16 digits.
     */
    public static final int CITIZEN_CARD_LENGTH = 16;

    /**
     * based on acceptance criteria, the national healthcare service number (nhs) can only have 10 digits.
     */
    public static final int HEALTHCARE_NUMBER_LENGTH = 10;

    /**
     * based on acceptance criteria, the tax identification number (tin) can only have 10 digits.
     */
    public static final int TAX_IDENTIFICATION_NUMBER_LENGTH = 10;

    /**
     * based on acceptance criteria, the maximum age of the client is 150 years
     */
    public static final int MAX_AGE = 150;

    /**
     * based on acceptance criteria, the minimum age of the client is 150 years
     */
    public static final int MIN_AGE = 0;

    /**
     * based on acceptance criteria, the maximum of characters that a client name can have is 35 characters.
     */
    public static final int NAME_CLIENT_MAX_LENGTH = 35;

    /**
     * based on acceptance criteria, the maximum of characters that a employee name can have is 35 characters.
     */
    public static final int NAME_EMPLOYEE_MAX_LENGTH = 35;

    /**
     * based on acceptance criteria, the phone number can only have 11 digits
     */
    public static final int PHONE_NUMBER_LENGTH = 11;

    /**
     * based on acceptance criteria, the maximum of characters that a clinical name can have is 30 characters.
     */
    public static final int NAME_CLINICAL_MAX_LENGTH = 30;

    /**
     * based on acceptance criteria, the maximum of characters that a address can have is 30 characters.
     */
    public static final int ADDRESS_MAX_LENGTH = 90;

    /**
     * based on acceptance criteria, the clinical laboratory ID can only have 11 digits
     */
    public static final int LABORATORY_ID_LENGTH = 5;

    /**
     * based on acceptance criteria, the tax identification number (tin) can only have 10 digits.
     */
    public static final int TIN_LENGTH = 10;

    /**
     * based on acceptance criteria, the standard occupational classification (soc) can only have 4 digits.
     */
    public static final int SOC_LENGTH = 4;

    /**
     * based on acceptance criteria, the parameter code can only have 5 digits.
     */
    public static final int PARAMETER_CODE_LENGTH = 5;

    /**
     * based on acceptance criteria, the maximum of characters that a parameter name can have is 8 characters.
     */
    public static final int PARAMETER_NAME_LENGTH = 8;

    /**
     * based on acceptance criteria, the maximum of characters that a parameter description can have is 20 characters.
     */
    public static final int PARAMETER_DESCRIPTION_LENGTH = 20;

    /**
     * based on acceptance criteria, the national healthcare service number (nhs) can only have 12 digits.
     */
    public static final int NHS_CODE_LENGTH = 12;

    /**
     * based on acceptance criteria, the maximum of characters that a report can have is 400 characters.
     */
    public static final int REPORT_LENGTH = 400;

    /**
     * based on acceptance criteria, the test type code can only have 5 digits.
     */
    public static final int TEST_TYPE_CODE_LENGTH = 5;

    /**
     * based on acceptance criteria, the maximum of characters that a test type description can have is 15 characters.
     */
    public static final int TEST_TYPE_DESCRIPTION_LENGTH = 15;

    /**
     * based on acceptance criteria, the maximum of characters that a collecting methods can have is 20 characters.
     */
    public static final int COLLECTING_METHODS_LENGTH = 20;

    /**
     * based on acceptance criteria, the parameter category code can only have 5 digits.
     */
    public static final int PARAMETER_CATEGORY_CODE_LENGTH = 5;

    /**
     * based on acceptance criteria, the maximum of characters that a parameter category name can have is 10 characters.
     */
    public static final int PARAMETER_CATEGORY_NAME_LENGTH = 10;

    /**
     * path to the barcode folder
     */
    public static final String BARCODE_PATH = "./Barcode/";

    /**
     * path to the nhs report folder
     */
    public static final String NHS_PATH = "./NHSReport/";

    /**
     * path to the log folder
     */
    public static final String LOG_PATH = "./LogReport/";

    /**
     * path to the log file
     */
    public static final String LOGFILE_PATH = "./LogReport/report.log";

    /**
     * report hour
     */
    public static final int REPORT_HOURS = 6;

    /**
     * report minutes
     */
    public static final int REPORT_MINUTES = 0;

    /**
     * report seconds
     */
    public static final int REPORT_SECONDS = 0;

    /**
     * path to the Client Notification folder
     */
    public static final String CLIENT_NOTIFICATION_PATH = "./ClientNotification/";

    /**
     * path to the Client Notification folder
     */
    public static final String CLIENT_NOTIFICATION_DIRECTORY_PATH = "./ClientNotification/emailAndSMSMessages";

    /**
     * path to company serialization
     */
    public static final String SERIALIZATION_COMPANY_PATH = "./tmp/company.ser";

    /**
     * path to serialization directory
     */
    public static final String SERIALIZATION_DIRECTORY_PATH = "./tmp";

    /**
     * path to serialization directory
     */
    public static final String OPERATION_CANCELED = "Operation canceled";

    /**
     * path to the config.properties
     */
    public static final String PARAMS_FILENAME = "config.properties";

    /**
     * company designation
     */
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";

    /**
     * code message - chars
     */
    public static final String CODE_MESSAGE = " chars";

    /**
     * code message - digits
     */
    public static final String DIGITS_MESSAGE = " digits";

    /**
     * regex that only accepts numeric characters
     */
    public static final String ONLY_NUMBERS = "[0-9]+";

    /**
     * regex that only accepts alpha characters
     */
    public static final String ONLY_LETTERS = "^[ A-Za-z]+$";

    /**
     * regex that only accepts alphanumeric characters
     */
    public static final String NUMBERS_LETTERS = "[ a-zA-Z0-9]+$";
    
    public static final String CODE_COVID = "COV19";

    public static final String MAIN_MENU_PATH = "/fxml/MainMenu.fxml";

    public static final String AUTH_UI_PATH = "/fxml/AuthUI.fxml";

    public static final String DEV_TEAM_PATH = "/fxml/DevTeamUI.fxml";

    public static final String CLIENT_UI_PATH = "/fxml/client/ClientUI.fxml";

    public static final String CHECK_TESTS_UI_PATH = "/fxml/client/CheckTestsUI.fxml";

    public static final String UPDATE_DATA_UI_PATH = "/fxml/client/UpdateDataUI.fxml";

    public static final String IMPORT_TESTS_UI_PATH = "/fxml/employee/coordinator/ImportTestsUI.fxml";

    public static final String ADMIN_UI_PATH = "/fxml/employee/administrator/AdminUI.fxml";

    public static final String CHEMISTRY_UI_PATH = "/fxml/employee/chemistry/ChemistryUI.fxml";

    public static final String COORDINATOR_UI_PATH = "/fxml/employee/coordinator/CoordinatorUI.fxml";

    public static final String MEDICAL_TECH_UI_PATH = "/fxml/employee/medicalTech/MedicalTechUI.fxml";

    public static final String RECEPTIONIST_PATH = "/fxml/employee/receptionist/ReceptionistUI.fxml";

    public static final String CREATE_TEST_PATH = "/fxml/employee/receptionist/CreateTestUI.fxml";

    public static final String SPECIALIST_UI_PATH = "/fxml/employee/specialist/SpecialistUI.fxml";

    public static final String PARAMETER_CATEGORY_PATH = "/fxml/employee/administrator/ParameterCategoryUI.fxml";

    public static final String PARAMETER_PATH = "/fxml/employee/administrator/ParameterUI.fxml";

    public static final String CREATE_EMPLOYEE_PATH = "/fxml/employee/administrator/RegistEmployeeUI.fxml";

    public static final String CREATE_CLIENT_PATH = "/fxml/employee/receptionist/RegistClientsUI.fxml";

    public static final String CREATE_SAMPLE_PATH = "/fxml/employee/medicalTech/RecordSampleUI.fxml";

    public static final String CREATE_TEST_TYPE_PATH = "/fxml/employee/administrator/CreateTestTypeUI.fxml";

    public static final String CREATE_RESULTS_PATH = "/fxml/employee/chemistry/RecordResultsUI.fxml";

    public static final String CREATE_DIAGNOSIS_PATH = "/fxml/employee/specialist/TestReportUI.fxml";

    public static final String VALIDATE_DIAGNOSIS_PATH = "/fxml/employee/coordinator/ValidateDiagnosisUI.fxml";

    public static final String CREATE_CLINICAL_PATH = "/fxml/employee/administrator/ClinicalUI.fxml";

    public static final String CLIENT_TESTS_UI = "/fxml/employee/chemistry/ClientTestsUI.fxml";

    public static final String NHS_REPORT_PATH = "/fxml/employee/administrator/NHSReportUI.fxml";

    public static final String SIMPLE_LINEAR_REGRESSION_PATH = "/fxml/employee/administrator/SimpleLinearRegressionUI.fxml";

    public static final String MULTI_LINEAR_REGRESSION_PATH = "/fxml/employee/administrator/MultiLinearRegressionUI.fxml";

    public static final String PERFORMANCE_REPORT_UI_PATH = "/fxml/employee/coordinator/PerformanceReportUI.fxml";

    public static final String GRAPH_PERFORMANCE_REPORT_UI_PATH = "/fxml/employee/coordinator/GraphPerformanceReportUI.fxml";
}
