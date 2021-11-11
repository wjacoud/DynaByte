/*
package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * @author 1201401 José Cruz
 *//*


public class SpecialistDoctorUI implements Runnable {

    public SpecialistDoctorUI() {
        //empty
    }

    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Create a new diagnosis and report ", new CreateReportUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nSpecialist Doctor Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}

*/

