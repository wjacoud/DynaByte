/*
package app.ui.console;


import app.controller.UpdateClientController;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class UpdateClientUI implements Runnable {

    UpdateClientController ucc;

    public UpdateClientUI() {

        ucc = new UpdateClientController();
    }

    public void run() {

        System.out.println("\n\nCLIENT DATA\n-----------\nCitizen Card Number: " + ucc.getCitizenNumber() + "\nNational Healthcare Service Number (NHS): " + ucc.getHealthcareNumber() + "\nBirth Date: " + ucc.getBirthDate() + "\nTax Identification Number (TIN): " + ucc.getTin() + "\nName: " + ucc.getName() + "\nPhone number: " + ucc.getPhoneNumber() + "\nSex: " + ucc.getSex() + "\n");


        List<String> attributeNames = new ArrayList<>();

        attributeNames.add("Citizen Card Number");
        attributeNames.add("National Healthcare Service Number");
        attributeNames.add("Birth Date");
        attributeNames.add("Tax Identification Number");
        attributeNames.add("Name");
        attributeNames.add("Phone number");
        attributeNames.add("Sex");

        String attribute = (String) Utils.showAndSelectOne(attributeNames, "Which attribute do you want to change");

        if (attribute != null) {
            switch (attribute) {
                case "Name":
                    ucc.editName(Utils.readLineFromConsole("Write new name: "));
                    break;
                case "Phone number":
                    ucc.editPhoneNumber(Utils.readLineFromConsole("Write new phone number: "));
                    break;
                case "Sex":
                    ucc.editSex(Utils.readLineFromConsole("Write new sex: "));
                    break;
                default:
                    System.out.println("Invalid option");
                    break;

            }
        } else {
            System.out.println(Constants.OPERATION_CANCELED);
        }
    }
}*/
