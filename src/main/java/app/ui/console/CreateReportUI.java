/*
package app.ui.console;

import app.controller.CreateReportController;
import app.domain.model.test.Test;
import app.domain.model.test.TestDiagnosis;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CreateReportUI implements Runnable{

    CreateReportController crc;

    public CreateReportUI(){
        crc = new CreateReportController();
    }

    public void run() {

        String idList = "";
        String rp;
        TestDiagnosis td = null;



        List<String> testId = new ArrayList<>();

        for(Test test : crc.getTestStore().getTests()){
            if(test.getState().equals(Test.State.ANALYZED)) {
                testId.add(test.getTestCode() + " / " + crc.getClientName(test));
            }
        }

        if (!testId.isEmpty()){
            System.out.println("\n\n-------Choose one test-------");
            idList = (String) Utils.showAndSelectOne(testId, "Test List");

            if (idList == null){
                return;
            }

            idList = idList.substring(0,12);

            boolean result = false;
            try {
                td = crc.createTestDiagnosis(idList);
                result = true;
            } catch (Exception e){
                result = false;
            }

            if(result){
                System.out.println("\n\n-------Diagnosis data-------\n" + td.getDiagnosis() + "\n\n-------Insert the data fo the report-------");

                rp = Utils.readLineFromConsole("");

                try {
                    crc.createTestReport(idList,td,rp);
                } catch (Exception e){
                    result = false;
                }

                if(result){
                    System.out.println("\nDo you want to save the diagnosis and report?");
                    boolean save = Utils.confirm("s -> confirm    n -> cancel");

                    if (save){
                        try{
                            System.out.println("Diagnosis and report saved with success!");
                        } catch (Exception e){
                            System.out.println("This test already has a diagnosis and report");
                        }
                    } else {
                        System.out.println("Saving process cancelled.");
                    }

                } else {
                    System.out.println("Error with the creation of the report!");
                }

            } else {
                System.out.println("Error with the creation of the diagnosis!");
            }
        } else {
            System.out.println("Test List is empty!");
        }


    }

}

*/
