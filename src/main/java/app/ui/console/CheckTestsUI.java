/*
package app.ui.console;

import app.controller.CheckTestsController;
import app.domain.model.test.Test;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CheckTestsUI implements Runnable {

    CheckTestsController ctc;
    String cancelMessage = "Operation canceled";


    public CheckTestsUI() {

    }

    public void run() {
        ctc = new CheckTestsController();

        List<Test> testList;
        List<String> listToShow = new ArrayList<>();
        String ttn;
        String[] arr;
        testList = ctc.getClientTests();
        testList = ctc.organizeClientTestList(testList);

        for(Test test : testList) {
            if(test.getState().equals(Test.State.VALIDATED)) {
                listToShow.add(test.getNhsCode()+", "+test.getDateStr());
            }
        }

        ttn = (String) Utils.showAndSelectOne(listToShow, "\nTest List");
        if (ttn == null) {
            System.out.println(cancelMessage);
            return;
        }

        arr = ttn.split(", ");
        for(Test test : testList) {
            if(test.getNhsCode().equalsIgnoreCase(arr[0])) {
                System.out.println(test.getReport().getReport());
            }
        }
    }
}
*/

