/*
package app.ui.console;

import app.controller.App;

import app.controller.MultiLinearRegressionController;
import app.controller.SerializationClass;

import app.domain.model.Company;
import app.ui.console.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


*/
/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 *//*

public class MainMenuUI {

    private Company company;
    private App app;
    SerializationClass serializationClass;

    public MainMenuUI() {
        app = App.getInstance();
        this.company = app.getCompany();
        serializationClass = new SerializationClass();
    }

    public void run() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        System.out.println("\nDefault administrator: \nemail: admin@lei.sem2.pt    password: 123456\n\nDefault receptionist: \nemail: recep@lei.sem2.pt    password: 123456\n\nDefault medical lab technician: \nemail: medicaltech@lei.sem2.pt    password: 123456\n\nDefault ChemistryTechnologist:\nemail: chemistry@lei.sem2.pt    password:123456\n\nDefault Specialist Doctor\nemail: specialist@lei.sem2.pt    password: 123456\n\nDefault Laboratory Coordinator: \nemail: coordinator@lei.sem2.pt    password: 123456\n\n");

        //serializationClass.serializeCompany();

        serializationClass.deserializeCompany();

        double[][] matrixX = {
                {1, 50, 30},
                {1, 100, 45},
                {1, 65, 40},
                {1, 85, 30},
                {1, 90, 38},
                {1, 70, 34}
        };
        double[] matrixY = {30.0, 26.0, 22.0, 20.0, 25.0, 30.0};


        MultiLinearRegressionController multiLinearRegressionController = new MultiLinearRegressionController(matrixX, matrixY);

        //itc.readFile("CSVfiles/tests_BloodCovidMATCPMDISCCSV.csv");

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Do Login", new AuthUI()));
        options.add(new MenuItem("Know the Development Team", new DevTeamUI()));
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nMain Menu");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
            if ((option == -1)) {
                serializationClass.serializeCompany();
            }

        }
        while (option != -1);

    }
}*/
