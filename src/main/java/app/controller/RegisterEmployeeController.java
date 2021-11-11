package app.controller;

import app.domain.model.*;
import app.domain.model.employees.*;
import app.domain.model.stores.EmployeeStore;
import auth.AuthFacade;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * class responsible for the Employee Creator Controller
 *
 * @author Rodrigo Oliveira 1201406
 */

public class RegisterEmployeeController {
    private Receptionist recep = null;
    private Administrator admin = null;
    private ChemistryTechnologist chemistryTec = null;
    private LaboratoryCoordinator laboratoryCoordinator = null;
    private MedicalLabTechnician medicalLabTech = null;
    private SpecialistDoctor specialistDoc = null;
    private EmployeeStore employeeStore;
    private AuthFacade authFacade;


    /**
     * Constructor that create a empty RegisterEmployeeController object
     */
    public RegisterEmployeeController() {
        App app = App.getInstance();
        Company company = app.getCompany();
        employeeStore = company.getEmployeeStore();
        authFacade = company.getAuthFacade();
    }


    /**
     * Add user
     *
     * @param email - Employee email
     * @param name  - Employee name
     * @return whether add user was successful or failed
     */
    public boolean addUser(String email, String name, String role) {
        String password = RandomStringUtils.randomAlphanumeric(10);

        if (!authFacade.existsUser(email)) {
            role = role.toUpperCase();
            System.out.println("\nLogin data\n\nEmail: " + email + "\nPassword: " + password + "\n\n");
            return authFacade.addUserWithRole(name, email, password, role);
        }

        return false;
    }


    /**
     * Create Receptionist
     *
     * @param name          - name
     * @param address       - address
     * @param phoneNumber   - phoneNumber
     * @param emailEmployee - emailEmployee
     * @param soc           - soc
     * @return return if Receptionist was created
     */
    public boolean createReceptionist(String name, String address, String phoneNumber, String emailEmployee, String soc) {
        recep = employeeStore.createReceptionist(name, address, phoneNumber, emailEmployee, soc);
        return recep != null;
    }


    /**
     * gives the order to the Administrator to save the Employee
     *
     * @return success of the operation
     */
    public boolean saveReceptionist() {
        addUser(recep.getEmail(), recep.getName(), recep.getFunction());
        return employeeStore.saveReceptionist(recep);
    }


    /**
     * Create Administrator
     *
     * @param name          - name
     * @param address       - address
     * @param phoneNumber   - phoneNumber
     * @param emailEmployee - emailEmployee
     * @param soc           - soc
     * @return return if administrator was created
     */
    public boolean createAdministrator(String name, String address, String phoneNumber, String emailEmployee, String soc) {
        admin = employeeStore.createAdministrator(name, address, phoneNumber, emailEmployee, soc);
        return admin != null;
    }


    /**
     * gives the order to the Administrator to save the Employee
     *
     * @return success of the operation
     */
    public boolean saveAdministrator() {
        addUser(admin.getEmail(), admin.getName(), admin.getFunction());
        return employeeStore.saveAdministrator(admin);

    }


    /**
     * Create ChemistryTechnologist
     *
     * @param name          - name
     * @param address       - address
     * @param phoneNumber   - phoneNumber
     * @param emailEmployee - emailEmployee
     * @param soc           - soc
     * @return return if ChemistryTechnologist was created
     */
    public boolean createChemistryTechnologist(String name, String address, String phoneNumber, String emailEmployee, String soc) {
        chemistryTec = employeeStore.createChemistryTechnologist(name, address, phoneNumber, emailEmployee, soc);
        return chemistryTec != null;
    }


    /**
     * gives the order to the Administrator to save the Employee
     *
     * @return success of the operation
     */
    public boolean saveChemistryTechnologist() {
        addUser(chemistryTec.getEmail(), chemistryTec.getName(), chemistryTec.getFunction());
        return employeeStore.saveChemistryTechnologist(chemistryTec);
    }


    /**
     * Create LaboratoryCoordinator
     *
     * @param name          - name
     * @param address       - address
     * @param phoneNumber   - phoneNumber
     * @param emailEmployee - emailEmployee
     * @param soc           - soc
     * @return return if LaboratoryCoordinator was created
     */
    public boolean createLaboratoryCoordinator(String name, String address, String phoneNumber, String emailEmployee, String soc) {
        laboratoryCoordinator = employeeStore.createLaboratoryCoordinator(name, address, phoneNumber, emailEmployee, soc);
        return laboratoryCoordinator != null;
    }


    /**
     * gives the order to the Administrator to save the Employee
     *
     * @return success of the operation
     */
    public boolean saveLaboratoryCoordinator() {
        addUser(laboratoryCoordinator.getEmail(), laboratoryCoordinator.getName(), laboratoryCoordinator.getFunction());
        return employeeStore.saveLaboratoryCoordinator(laboratoryCoordinator);
    }


    /**
     * Create MedicalLabTechnician
     *
     * @param name          - name
     * @param address       - address
     * @param phoneNumber   - phoneNumber
     * @param emailEmployee - emailEmployee
     * @param soc           - soc
     * @return return if MedicalLabTechnician was created
     */
    public boolean createMedicalLabTechnician(String name, String address, String phoneNumber, String emailEmployee, String soc) {
        medicalLabTech = employeeStore.createMedicalLabTechnician(name, address, phoneNumber, emailEmployee, soc);
        return medicalLabTech != null;
    }


    /**
     * gives the order to the Administrator to save the Employee
     *
     * @return success of the operation
     */
    public boolean saveMedicalLabTechnician() {
        addUser(medicalLabTech.getEmail(), medicalLabTech.getName(), medicalLabTech.getFunction());
        return employeeStore.saveMedicalLabTechnician(medicalLabTech);
    }


    /**
     * Create SpecialistDoctor
     *
     * @param name          - name
     * @param address       - address
     * @param phoneNumber   - phoneNumber
     * @param emailEmployee - emailEmployee
     * @param soc           - soc
     * @return return if SpecialistDoctor was created
     */
    public boolean createSpecialistDoctor(String name, String address, String phoneNumber, String emailEmployee, String soc, String doctorIndexNumber) {
        specialistDoc = employeeStore.createSpecialistDoctor(name, address, phoneNumber, emailEmployee, soc, doctorIndexNumber);
        return specialistDoc != null;
    }


    /**
     * gives the order to the Administrator to save the Employee
     *
     * @return success of the operation
     */
    public boolean saveSpecialistDoctor() {
        addUser(specialistDoc.getEmail(), specialistDoc.getName(), specialistDoc.getFunction());
        return employeeStore.saveSpecialistDoctor(specialistDoc);
    }

}