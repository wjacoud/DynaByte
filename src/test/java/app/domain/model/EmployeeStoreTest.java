package app.domain.model;

import app.controller.App;
import app.domain.model.employees.*;
import app.domain.model.stores.EmployeeStore;
import org.junit.Assert;
import org.junit.Test;

public class EmployeeStoreTest {

    App app = App.getInstance();
    Company company = app.getCompany();
    EmployeeStore employeeStore = company.getEmployeeStore();


    @Test(expected = StringIndexOutOfBoundsException.class)
    public void createInvalidNameLaboratoryCoordinator() {

        employeeStore.createLaboratoryCoordinator("", "njosdfds", "12345678445", "aewgrfhyuipy@iewfhudawf.com", "1234");

    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidName1LaboratoryCoordinator() {

        employeeStore.createLaboratoryCoordinator("wer_WErw", "njosdfds", "12345678445", "aewgrfhyuipy@iewfhudawf.com", "1234");

    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidName2LaboratoryCoordinator() {

        employeeStore.createLaboratoryCoordinator("werjndfsgujinsdfjignjidfgnujidfngWErw", "njosdfds", "12345678445", "aewgrfhyuipy@iewfhudawf.com", "1234");

    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidEmailLaboratoryCoordinator() {

        employeeStore.createLaboratoryCoordinator("asdaf", "njosdfds", "12345678445", "", "1234");

    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidEmail1LaboratoryCoordinator() {

        employeeStore.createLaboratoryCoordinator("asdaf", "njosdfds", "12345678445", "fdhjgujns", "1234");

    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidAddressLaboratoryCoordinator() {

        employeeStore.createLaboratoryCoordinator("sdfsdf", "", "12345678445", "aewgrfhyuipy@iewfhudawf.com", "1234");

    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidAddress1LaboratoryCoordinator() {

        employeeStore.createLaboratoryCoordinator("sdfsdf", "sdfasd_Sdfasdf", "12345678445", "aewgrfhyuipy@iewfhudawf.com", "1234");

    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidAddress2LaboratoryCoordinator() {

        employeeStore.createLaboratoryCoordinator("sdfsdf", "sdfasdrftgkifgjhuoijnfgouihjnfguiohjnuiofgSdfasdfsdfasdrftgkifgjhuoijnfgouihjnfguiohjnuiofgSdfasdfsdfasdrftgkifgjhuoijnfgouihjnfguiohjnuiofgSdfasdf", "12345678445", "aewgrfhyuipy@iewfhudawf.com", "1234");

    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidPhoneNumberLaboratoryCoordinator() {

        employeeStore.createLaboratoryCoordinator("sdfsdf", "sdfsfds", "", "aewgrfhyuipy@iewfhudawf.com", "1234");

    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidPhoneNumber1LaboratoryCoordinator() {

        employeeStore.createLaboratoryCoordinator("sdfsdf", "sdfsfds", "1234234", "aewgrfhyuipy@iewfhudawf.com", "1234");

    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidPhoneNumber2LaboratoryCoordinator() {

        employeeStore.createLaboratoryCoordinator("sdfsdf", "sdfsfds", "jndfgujidfg", "aewgrfhyuipy@iewfhudawf.com", "1234");

    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidSocLaboratoryCoordinator() {

        employeeStore.createLaboratoryCoordinator("sdfsdf", "sdfsfds", "12345678905", "aewgrfhyuipy@iewfhudawf.com", "");

    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidSoc1LaboratoryCoordinator() {

        employeeStore.createLaboratoryCoordinator("sdfsdf", "sdfsfds", "12345678905", "aewgrfhyuipy@iewfhudawf.com", "asfdf");

    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidSoc2LaboratoryCoordinator() {

        employeeStore.createLaboratoryCoordinator("sdfsdf", "sdfsfds", "12345678905", "aewgrfhyuipy@iewfhudawf.com", "123456");

    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidDinSpecialistDoctor() {

        employeeStore.createSpecialistDoctor("sdfsdf", "sdfsfds", "12345678905", "aewgrfhyuipy@iewfhudawf.com", "1234", "");

    }

    @Test(expected = IllegalArgumentException.class)
    public void createInvalidDin1SpecialistDoctor() {

        employeeStore.createSpecialistDoctor("sdfsdf", "sdfsfds", "12345678905", "aewgrfhyuipy@iewfhudawf.com", "1234", "sadfasdf");

    }

    @Test
    public void createLaboratoryCoordinator() {

        employeeStore.createLaboratoryCoordinator("asfhyudoh", "njosdfds", "12345678445", "aewgrfhyuipy@iewfhudawf.com", "1234");

    }

    @Test
    public void createReceptionist() {

        employeeStore.createReceptionist("asfhyudoh", "njosdfds", "12346678945", "aewgryuipy@iewfhudawf.com", "1234");

    }


    @Test
    public void createMedicalLabTechnician() {

        employeeStore.createMedicalLabTechnician("asfhyudoh", "njosdfds", "12345678948", "aewgruipy@iewfhudawf.com", "1234");

    }

    @Test
    public void createAdministrator() {

        employeeStore.createAdministrator("asfhyudoh", "njosdfds", "52345678945", "aewghyuipy@iewfhudawf.com", "1234");

    }

    @Test
    public void createChemistryTechnologist() {

        employeeStore.createChemistryTechnologist("asfhyudoh", "njosdfds", "52349678945", "aewghyuipy@gmail.com", "1234");

    }

    @Test
    public void createSpecialistDoctor() {

        employeeStore.createSpecialistDoctor("asfhyudoh", "njosdfds", "12345678945", "aewgrfhyuipy@iewfhudawf.com", "1234", "111");

    }

    @Test
    public void saveSpecialistDoctor() {

        SpecialistDoctor employee = employeeStore.createSpecialistDoctor("asfhyudoh", "njosdfds", "15345678945", "aewghipy@iewfdawf.com", "1234", "111");

        Assert.assertTrue(employeeStore.saveSpecialistDoctor(employee));
    }

    @Test
    public void saveMedicalLabTechnician() {

        MedicalLabTechnician employee = employeeStore.createMedicalLabTechnician("asfhyudoh", "njosdfds", "75445978945", "rfhyuipy@iefwf.com", "1234");

        Assert.assertTrue(employeeStore.saveMedicalLabTechnician(employee));
    }

    @Test
    public void saveLaboratoryCoordinator() {

        LaboratoryCoordinator employee = employeeStore.createLaboratoryCoordinator("asfhyudoh", "njosdfds", "12345678975", "aewgryuipy@iewfhudawf.com", "1234");

        Assert.assertTrue(employeeStore.saveLaboratoryCoordinator(employee));

    }

    @Test
    public void saveChemistryTechnologist() {

        ChemistryTechnologist employee = employeeStore.createChemistryTechnologist("asfhyudoh", "njosdfds", "02345678945", "arfhyuipy@iewfhudawf.com", "1234");

        Assert.assertTrue(employeeStore.saveChemistryTechnologist(employee));
    }

    @Test
    public void saveAdministrator() {

        Administrator employee = employeeStore.createAdministrator("asfhyudoh", "njosdfds", "12340698945", "awgrfhy@iewfhudawf.com", "1234");

        Assert.assertTrue(employeeStore.saveAdministrator(employee));
    }

    @Test
    public void saveReceptionist() {

        Receptionist employee = employeeStore.createReceptionist("asfhyudoh", "njosdfds", "12345612345", "aewgrfyuipy@iewfhudawf.com", "1234");

        Assert.assertTrue(employeeStore.saveReceptionist(employee));
    }

    @Test
    public void saveInvalidSpecialistDoctor() {

        SpecialistDoctor employee = employeeStore.createSpecialistDoctor("asfhyudoh", "njosdfds", "10101010107", "recep@lei.sem2.pt", "1234", "111");

        Assert.assertFalse(employeeStore.saveSpecialistDoctor(employee));
    }

    @Test
    public void saveInvalidMedicalLabTechnician() {

        MedicalLabTechnician employee = employeeStore.createMedicalLabTechnician("asfhyudoh", "njosdfds", "10101010107", "recep@lei.sem2.pt", "1234");

        Assert.assertFalse(employeeStore.saveMedicalLabTechnician(employee));
    }

    @Test
    public void saveInvalidLaboratoryCoordinator() {

        LaboratoryCoordinator employee = employeeStore.createLaboratoryCoordinator("asfhyudoh", "njosdfds", "10101010107", "recep@lei.sem2.pt", "1234");

        Assert.assertFalse(employeeStore.saveLaboratoryCoordinator(employee));

    }

    @Test
    public void saveInvalidChemistryTechnologist() {

        ChemistryTechnologist employee = employeeStore.createChemistryTechnologist("asfhyudoh", "njosdfds", "10101010107", "recep@lei.sem2.pt", "1234");

        Assert.assertFalse(employeeStore.saveChemistryTechnologist(employee));
    }

    @Test
    public void saveInvalidAdministrator() {

        Administrator employee = employeeStore.createAdministrator("asfhyudoh", "njosdfds", "10101010107", "recep@lei.sem2.pt", "1234");

        Assert.assertFalse(employeeStore.saveAdministrator(employee));
    }

    @Test
    public void saveInvalidReceptionist() {

        Receptionist employee = employeeStore.createReceptionist("asfhyudoh", "njosdfds", "10101010107", "recep@lei.sem2.pt", "1234");

        Assert.assertFalse(employeeStore.saveReceptionist(employee));
    }


    @Test
    public void saveInvalidEmailSpecialistDoctor() {

        SpecialistDoctor employee = employeeStore.createSpecialistDoctor("asfhyudoh", "njosdfds", "20101010107", "recep@lei.sem2.pt", "1234", "111");

        Assert.assertFalse(employeeStore.saveSpecialistDoctor(employee));
    }

    @Test
    public void saveInvalidEmailMedicalLabTechnician() {

        MedicalLabTechnician employee = employeeStore.createMedicalLabTechnician("asfhyudoh", "njosdfds", "30101010107", "recep@lei.sem2.pt", "1234");

        Assert.assertFalse(employeeStore.saveMedicalLabTechnician(employee));
    }

    @Test
    public void saveInvalidEmailLaboratoryCoordinator() {

        LaboratoryCoordinator employee = employeeStore.createLaboratoryCoordinator("asfhyudoh", "njosdfds", "40101010107", "recep@lei.sem2.pt", "1234");

        Assert.assertFalse(employeeStore.saveLaboratoryCoordinator(employee));

    }

    @Test
    public void saveInvalidEmailChemistryTechnologist() {

        ChemistryTechnologist employee = employeeStore.createChemistryTechnologist("asfhyudoh", "njosdfds", "50101010107", "recep@lei.sem2.pt", "1234");

        Assert.assertFalse(employeeStore.saveChemistryTechnologist(employee));
    }

    @Test
    public void saveInvalidEmailAdministrator() {

        Administrator employee = employeeStore.createAdministrator("asfhyudoh", "njosdfds", "60101010107", "recep@lei.sem2.pt", "1234");

        Assert.assertFalse(employeeStore.saveAdministrator(employee));
    }

    @Test
    public void saveInvalidEmailReceptionist() {

        Receptionist employee = employeeStore.createReceptionist("asfhyudoh", "njosdfds", "70101010107", "recep@lei.sem2.pt", "1234");

        Assert.assertFalse(employeeStore.saveReceptionist(employee));
    }


    @Test
    public void saveInvalidPhoneNumberSpecialistDoctor() {

        SpecialistDoctor employee = employeeStore.createSpecialistDoctor("asfhyudoh", "njosdfds", "10101010107", "recep6@lei.sem2.pt", "1234", "111");

        Assert.assertFalse(employeeStore.saveSpecialistDoctor(employee));
    }

    @Test
    public void saveInvalidPhoneNumberMedicalLabTechnician() {

        MedicalLabTechnician employee = employeeStore.createMedicalLabTechnician("asfhyudoh", "njosdfds", "10101010107", "recep5@lei.sem2.pt", "1234");

        Assert.assertFalse(employeeStore.saveMedicalLabTechnician(employee));
    }

    @Test
    public void saveInvalidPhoneNumberLaboratoryCoordinator() {

        LaboratoryCoordinator employee = employeeStore.createLaboratoryCoordinator("asfhyudoh", "njosdfds", "10101010107", "recep4@lei.sem2.pt", "1234");

        Assert.assertFalse(employeeStore.saveLaboratoryCoordinator(employee));

    }

    @Test
    public void saveInvalidPhoneNumberChemistryTechnologist() {

        ChemistryTechnologist employee = employeeStore.createChemistryTechnologist("asfhyudoh", "njosdfds", "10101010107", "recep3@lei.sem2.pt", "1234");

        Assert.assertFalse(employeeStore.saveChemistryTechnologist(employee));
    }

    @Test
    public void saveInvalidPhoneNumberAdministrator() {

        Administrator employee = employeeStore.createAdministrator("asfhyudoh", "njosdfds", "10101010107", "recep2@lei.sem2.pt", "1234");

        Assert.assertFalse(employeeStore.saveAdministrator(employee));
    }



    @Test
    public void saveInvalidPhoneNumberReceptionist() {

        Receptionist employee = employeeStore.createReceptionist("asfhyudoh", "njosdfds", "10101010107", "recep1@lei.sem2.pt", "1234");

        Assert.assertFalse(employeeStore.saveReceptionist(employee));
    }

    @Test
    public void getReceptionistList() {
        employeeStore.getReceptionistList();
    }

    @Test
    public void getChemistryTechnologistList() {
        employeeStore.getChemistryTechnologistList();
    }

    @Test
    public void getMedicalLabTechnicianList() {
        employeeStore.getMedicalLabTechnicianList();
    }

    @Test
    public void getLaboratoryCoordinatorList() {
        employeeStore.getLaboratoryCoordinatorList();
    }

    @Test
    public void getSpecialistDoctorList() {
        employeeStore.getSpecialistDoctorList();
    }

    @Test
    public void getAdministratorList() {
        employeeStore.getAdministratorList();
    }

    @Test
    public void getReceptionistListMutant() {
        Assert.assertNotNull(employeeStore.getReceptionistList());
    }

    @Test
    public void getChemistryTechnologistListMutant() {
        Assert.assertNotNull(employeeStore.getChemistryTechnologistList());
    }

    @Test
    public void getMedicalLabTechnicianListMutant() {
        Assert.assertNotNull(employeeStore.getMedicalLabTechnicianList());
    }

    @Test
    public void getLaboratoryCoordinatorListMutant() {
        Assert.assertNotNull(employeeStore.getLaboratoryCoordinatorList());
    }

    @Test
    public void getSpecialistDoctorListMutant() {
        Assert.assertNotNull(employeeStore.getSpecialistDoctorList());
    }

    @Test
    public void getAdministratorListMutant() {
        Assert.assertNotNull(employeeStore.getAdministratorList());
    }


}