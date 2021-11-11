/*
package app.ui.console;

import app.controller.ClientTestsController;
import app.domain.model.client.Client;
import app.domain.model.test.Test;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ClientTestsUI implements Runnable {
    ClientTestsController ctc;

    public ClientTestsUI() {
        ctc = new ClientTestsController();
    }

    public void run() {
        try {
            ctc.getClientListTin();
        } catch (Exception e) {
            System.out.println("Client List empty!!");
            return;
        }

        List<String> clientTin = new ArrayList<>();
        String tin;

        for (Client c : ctc.getClientListTin()) {
            clientTin.add(c.getTin());
        }

        tin = Utils.showAndSelectOne(clientTin, "Client List Ordered").toString();

        List<Test> testList = new ArrayList<>();
        List<String> testTin = new ArrayList<>();
        Client client = ctc.getClientChosen(tin);

        try {
            testList = ctc.getValidatedTests(client);
        } catch (Exception e) {
            System.out.println("ERROR TEST!");
            return;
        }

        for (Test t : testList) {
            testTin.add(t.getTin());
        }

        tin = Utils.showAndSelectOne(testTin, "Test List").toString();

        try {
            System.out.println(ctc.toString(ctc.getTestChosen(tin, testList)));
        } catch (Exception e) {
            System.out.println("ERROR FINAL!!");
            return;
        }
    }
}*/
