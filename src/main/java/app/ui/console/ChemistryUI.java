/*
package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 *//*


public class ChemistryUI implements Runnable {
    public ChemistryUI() {
        //empty
    }

    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Record the results of a test ", new RecordTestResultUI()));
        //options.add(new MenuItem("Consult Clients' Tests info ",  new ClientTestsUI()));
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nClinical Chemistry Technologist Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}
*/

