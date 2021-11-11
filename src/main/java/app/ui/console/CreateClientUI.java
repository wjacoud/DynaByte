///*
//package app.ui.console;
//
//
//import app.controller.CreateClientController;
//import app.ui.console.utils.Utils;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//
//public class CreateClientUI implements Runnable {
//
//    CreateClientController ccc;
//
//    public CreateClientUI() {
//        ccc = new CreateClientController();
//    }
//
//    public void run() {
//
//        boolean result;
//        System.out.println("\n\nINFORMATION REQUIRED\n--------------------");
//        String citizenNumber = Utils.readLineFromConsole("Citizen Card Number: ");
//        String healthcareNumber = Utils.readLineFromConsole("National Healthcare Service Number (NHS): ");
//        Date birthDate = Utils.readDateFromConsole("Birth Date (xx-yy-zzzz): ");
//        String tin = Utils.readLineFromConsole("Tax Identification Number (TIN): ");
//        String name = Utils.readLineFromConsole("Name: ");
//        String phoneNumber = Utils.readLineFromConsole("Phone Number: ");
//        String sex = Utils.readLineFromConsole("Sex (male/female): ");
//
//        try {
//            result = ccc.createClient(citizenNumber, healthcareNumber, birthDate, tin, name, phoneNumber, sex);
//        } catch (Exception e) {
//            result = false;
//        }
//
//        if (result) {
//
//            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//            String date = dateFormat.format(birthDate.getTime());
//
//            System.out.println("\n\nCLIENT DATA\n-----------\nCitizen Card Number: " + citizenNumber + "\nNational Healthcare Service Number (NHS): " + healthcareNumber + "\nBirth Date: " + date + "\nTax Identification Number (TIN): " + tin + "\nName: " + name + "\nPhone number: " + phoneNumber + "\nSex: " + sex);
//
//            result = Utils.confirm("s -> confirm    n -> cancel");
//
//            if (result) {
//                result = ccc.saveClient();
//            } else {
//
//                System.out.println("\nOperation canceled");
//                return;
//            }
//
//
//            if (result) {
//                System.out.println("\nClient save with success!");
//
//                do {
//                    System.out.println("\n\nUSER DATA\n----------");
//                    String email = Utils.readLineFromConsole("Email: ");
//                    result = ccc.addUser(email, name);
//
//                    if (result) {
//                        System.out.println("\nUser create with success!");
//                    } else {
//                        System.out.println("\nEmail already exist!");
//                    }
//                } while (!result);
//            } else {
//                System.out.println("Error with client save!");
//            }
//        } else {
//            System.out.println("Error with client creation!");
//        }
//    }
//}
//*/
//
