/*
package app.ui.console;

import app.controller.RegisterEmployeeController;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.util.Scanner;

public class CreateEmployeeUI implements Runnable {

    RegisterEmployeeController rec;

    public CreateEmployeeUI() {
        rec = new RegisterEmployeeController();
    }

    public void run() {

        Scanner input = new Scanner(System.in);
        boolean result;
        boolean roleverification;
        String name;
        String address;
        String phoneNumber;
        String emailEmployee;
        String soc;
        String din = "";
        String role = "";

        do {
            System.out.print("\nEmployee Role(MedicalLabTechnician, Receptionist, Administrator, LaboratoryCoordinator, SpecialistDoctor, ChemistryTechnologist): ");
            role = input.nextLine();
            role = role.toUpperCase();
            if (role.equals(Constants.ROLE_MEDICALLABTECHNICIAN) || role.equals(Constants.ROLE_RECEPTIONIST) || role.equals(Constants.ROLE_ADMIN) || role.equals(Constants.ROLE_LABORATORYCOORDINATOR) || role.equals(Constants.ROLE_SPECIALISTDOCTOR) || role.equals(Constants.ROLE_CHEMISTRYTECHNOLOGIST)) {
                roleverification = true;
            } else {
                roleverification = false;
                System.out.println("Invalid Role please insert valid role");
            }

        } while (!roleverification);

        System.out.println("\n\nINFORMATION REQUIRED\n--------------------");
        name = Utils.readLineFromConsole("Name: ");
        address = Utils.readLineFromConsole("Address: ");
        phoneNumber = Utils.readLineFromConsole("Phone Number: ");
        emailEmployee = Utils.readLineFromConsole("EmailEmployee: ");
        soc = Utils.readLineFromConsole("Standard Occupational Classification (soc): ");

        if (role.equals(Constants.ROLE_SPECIALISTDOCTOR)) {
            din = Utils.readLineFromConsole("Doctor Index Number: ");
        }

        try {
            switch (role) {
                case Constants.ROLE_RECEPTIONIST:
                    result = rec.createReceptionist(name, address, phoneNumber, emailEmployee, soc);
                    break;
                case Constants.ROLE_ADMIN:
                    result = rec.createAdministrator(name, address, phoneNumber, emailEmployee, soc);
                    break;
                case Constants.ROLE_MEDICALLABTECHNICIAN:
                    result = rec.createMedicalLabTechnician(name, address, phoneNumber, emailEmployee, soc);
                    break;
                case Constants.ROLE_LABORATORYCOORDINATOR:
                    result = rec.createLaboratoryCoordinator(name, address, phoneNumber, emailEmployee, soc);
                    break;
                case Constants.ROLE_CHEMISTRYTECHNOLOGIST:
                    result = rec.createChemistryTechnologist(name, address, phoneNumber, emailEmployee, soc);
                    break;
                case Constants.ROLE_SPECIALISTDOCTOR:
                    result = rec.createSpecialistDoctor(name, address, phoneNumber, emailEmployee, soc, din);
                    break;
                default:
                    result = false;
                    break;

            }
        } catch (Exception e) {
            result = false;
        }

        if (result) {
            System.out.println("\n\nEMPLOYEE DATA\n-----------\nname: " + name + "\naddress: " + address + "\nPhone Number: " + phoneNumber + "\nEmail Employee: " + emailEmployee + "\nSoc: " + soc);

            if (role.equals(Constants.ROLE_SPECIALISTDOCTOR)) {
                System.out.println("\nDoctor Index Number: " + din);
            }

            result = Utils.confirm("s -> confirm    n -> cancel");

            if (result) {
                switch (role) {
                    case Constants.ROLE_RECEPTIONIST:
                        result = rec.saveReceptionist();
                        break;
                    case Constants.ROLE_ADMIN:
                        result = rec.saveAdministrator();
                        break;
                    case Constants.ROLE_MEDICALLABTECHNICIAN:
                        result = rec.saveMedicalLabTechnician();
                        break;
                    case Constants.ROLE_LABORATORYCOORDINATOR:
                        result = rec.saveLaboratoryCoordinator();
                        break;
                    case Constants.ROLE_CHEMISTRYTECHNOLOGIST:
                        result = rec.saveChemistryTechnologist();
                        break;
                    case Constants.ROLE_SPECIALISTDOCTOR:
                        result = rec.saveSpecialistDoctor();
                        break;
                    default:
                        break;
                }
            } else {
                System.out.println("\nOperation canceled");
                return;
            }

            System.out.println();

            if (result) {
                System.out.println("Employee saved with success!");
            } else {
                System.out.println("Error with Employee creation!");
            }

        } else {
            System.out.println("Error with Employee creation!");
        }
    }
}


*/

