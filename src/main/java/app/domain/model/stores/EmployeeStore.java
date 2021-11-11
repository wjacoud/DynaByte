package app.domain.model.stores;

import app.domain.model.employees.*;

import java.util.ArrayList;

import java.util.List;


/**
 * Class responsible for the employee store
 *
 * @author Rodrigo Oliveira 1201406
 */
public class EmployeeStore implements java.io.Serializable {
    private List<Receptionist> receptionistList;
    private List<ChemistryTechnologist> chemistryTechnologistList;
    private List<MedicalLabTechnician> medicalLabTechnicianList;
    private List<LaboratoryCoordinator> laboratoryCoordinatorList;
    private List<SpecialistDoctor> specialistDoctorList;
    private List<Administrator> administratorList;
    private static int numberEmployees = 1;

    /**
     * constructor that initialize the lists
     */
    public EmployeeStore() {
        receptionistList = new ArrayList<>();
        chemistryTechnologistList = new ArrayList<>();
        medicalLabTechnicianList = new ArrayList<>();
        laboratoryCoordinatorList = new ArrayList<>();
        specialistDoctorList = new ArrayList<>();
        administratorList = new ArrayList<>();
    }

    public List<Receptionist> getReceptionistList() {
        return receptionistList;
    }

    public List<ChemistryTechnologist> getChemistryTechnologistList() {
        return chemistryTechnologistList;
    }

    public List<MedicalLabTechnician> getMedicalLabTechnicianList() {
        return medicalLabTechnicianList;
    }

    public List<LaboratoryCoordinator> getLaboratoryCoordinatorList() {
        return laboratoryCoordinatorList;
    }

    public List<SpecialistDoctor> getSpecialistDoctorList() {
        return specialistDoctorList;
    }

    public List<Administrator> getAdministratorList() {
        return administratorList;
    }

    /**
     * create a new laboratory coordinator
     *
     * @param name        employee name
     * @param address     employee address
     * @param phoneNumber employee phone number
     * @param email       employee email
     * @param soc         employee soc
     * @return employee
     */
    public LaboratoryCoordinator createLaboratoryCoordinator(String name, String address, String phoneNumber, String email, String soc) {
        String id = createId(name);
        return new LaboratoryCoordinator(id, name, address, phoneNumber, email, soc);
    }


    /**
     * create a new receptionist
     *
     * @param name        employee name
     * @param address     employee address
     * @param phoneNumber employee phone number
     * @param email       employee email
     * @param soc         employee soc
     * @return employee
     */
    public Receptionist createReceptionist(String name, String address, String phoneNumber, String email, String soc) {
        String id = createId(name);
        return new Receptionist(id, name, address, phoneNumber, email, soc);
    }


    /**
     * create a new medical lab technician
     *
     * @param name        employee name
     * @param address     employee address
     * @param phoneNumber employee phone number
     * @param email       employee email
     * @param soc         employee soc
     * @return employee
     */
    public MedicalLabTechnician createMedicalLabTechnician(String name, String address, String phoneNumber, String email, String soc) {
        String id = createId(name);
        return new MedicalLabTechnician(id, name, address, phoneNumber, email, soc);
    }


    /**
     * create a new administrator
     *
     * @param name        employee name
     * @param address     employee address
     * @param phoneNumber employee phone number
     * @param email       employee email
     * @param soc         employee soc
     * @return employee
     */
    public Administrator createAdministrator(String name, String address, String phoneNumber, String email, String soc) {
        String id = createId(name);
        return new Administrator(id, name, address, phoneNumber, email, soc);
    }


    /**
     * create a new chemistry technologist
     *
     * @param name        employee name
     * @param address     employee address
     * @param phoneNumber employee phone number
     * @param email       employee email
     * @param soc         employee soc
     * @return employee
     */
    public ChemistryTechnologist createChemistryTechnologist(String name, String address, String phoneNumber, String email, String soc) {
        String id = createId(name);
        return new ChemistryTechnologist(id, name, address, phoneNumber, email, soc);
    }


    /**
     * create a new specialist doctor
     *
     * @param name              employee name
     * @param address           employee address
     * @param phoneNumber       employee phone number
     * @param email             employee email
     * @param soc               employee soc
     * @param doctorIndexNumber doctor index number
     * @return employee
     */
    public SpecialistDoctor createSpecialistDoctor(String name, String address, String phoneNumber, String email, String soc, String doctorIndexNumber) {
        String id = createId(name);
        return new SpecialistDoctor(id, name, address, phoneNumber, email, soc, doctorIndexNumber);
    }

    /**
     * create a new id with the employee initials and five numbers
     *
     * @param name employee name
     * @return id
     */
    public String createId(String name) {
        String id;
        StringBuilder initials = new StringBuilder();

        String[] myName = name.split(" ");
        for (String s : myName) {
            initials.append(s.charAt(0));
        }
        id = initials + String.format("%05d", numberEmployees);
        return id;
    }


    /**
     * save the specialist doctor
     *
     * @param specialDoc specialist doctor object
     * @return success of the save
     */
    public boolean saveSpecialistDoctor(SpecialistDoctor specialDoc) {
        if (validateEmployee(specialDoc.getEmail(), specialDoc.getPhoneNumber())) {
            specialistDoctorList.add(specialDoc);
            return true;
        }
        return false;
    }


    /**
     * save the medical lab technician
     *
     * @param medicTech medical lab technician object
     * @return success of the save
     */
    public boolean saveMedicalLabTechnician(MedicalLabTechnician medicTech) {
        if (validateEmployee(medicTech.getEmail(), medicTech.getPhoneNumber())) {
            medicalLabTechnicianList.add(medicTech);
            return true;
        }
        return false;
    }


    /**
     * save the laboratoru coordinator
     *
     * @param labCoord laboratory coordinator object
     * @return success of the save
     */
    public boolean saveLaboratoryCoordinator(LaboratoryCoordinator labCoord) {
        if (validateEmployee(labCoord.getEmail(), labCoord.getPhoneNumber())) {
            laboratoryCoordinatorList.add(labCoord);
            return true;
        }
        return false;
    }


    /**
     * save the chemistry technologist
     *
     * @param chemisTech chemistry technologist object
     * @return success of the save
     */
    public boolean saveChemistryTechnologist(ChemistryTechnologist chemisTech) {
        if (validateEmployee(chemisTech.getEmail(), chemisTech.getPhoneNumber())) {
            chemistryTechnologistList.add(chemisTech);
            return true;
        }
        return false;
    }


    /**
     * save the administrator
     *
     * @param admin administrator object
     * @return success of the save
     */
    public boolean saveAdministrator(Administrator admin) {
        if (validateEmployee(admin.getEmail(), admin.getPhoneNumber())) {
            administratorList.add(admin);
            return true;
        }
        return false;
    }


    /**
     * save the receptionist
     *
     * @param recep receptionist object
     * @return success of the save
     */
    public boolean saveReceptionist(Receptionist recep) {
        if (validateEmployee(recep.getEmail(), recep.getPhoneNumber())) {
            receptionistList.add(recep);
            return true;
        }
        return false;
    }


    /**
     * verify if the employee already exist
     *
     * @param email       employee email
     * @param phoneNumber employee phone number
     * @return success of the verification
     */
    public boolean validateEmployee(String email, String phoneNumber) {

        for (SpecialistDoctor value : specialistDoctorList) {
            if (email.equals(value.getEmail()) || phoneNumber.equals(value.getPhoneNumber())) {
                return false;
            }
        }

        for (MedicalLabTechnician value : medicalLabTechnicianList) {
            if (value.getEmail().equals(email) || value.getPhoneNumber().equals(phoneNumber)) {
                return false;
            }
        }

        for (LaboratoryCoordinator value : laboratoryCoordinatorList) {
            if (value.getEmail().equals(email) || value.getPhoneNumber().equals(phoneNumber)) {
                return false;
            }
        }

        for (ChemistryTechnologist value : chemistryTechnologistList) {
            if (value.getEmail().equals(email) || value.getPhoneNumber().equals(phoneNumber)) {
                return false;
            }
        }

        for (Administrator value : administratorList) {
            if (value.getEmail().equals(email) || value.getPhoneNumber().equals(phoneNumber)) {
                return false;
            }
        }

        for (Receptionist value : receptionistList) {
            if (value.getEmail().equals(email) || value.getPhoneNumber().equals(phoneNumber)) {
                return false;
            }
        }

        return true;
    }
}
