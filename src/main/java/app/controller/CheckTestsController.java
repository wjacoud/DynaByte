package app.controller;

import app.domain.model.*;
import app.domain.model.client.Client;
import app.domain.model.stores.ClientsStore;
import app.domain.model.stores.TestStore;
import app.domain.model.test.Test;
import app.domain.model.test.TestReport;
import auth.AuthFacade;
import auth.UserSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CheckTestsController {
    TestStore testStore;
    private ClientsStore clientsStore;
    List<Test> clientTestList = new ArrayList<>();
    List<Test> testList = new ArrayList<>();
    private Client client;
    private AuthFacade authFacade;


    public CheckTestsController() {
        App app = App.getInstance();
        Company company = app.getCompany();
        clientsStore = company.getClientStore();
        authFacade = company.getAuthFacade();
        UserSession userSession = authFacade.getCurrentUserSession();
        client = clientsStore.getClientFromClientEmail(userSession.getUserId().getEmail());
        testStore = company.getTestStore();
    }

    public List<Test> getClientTests() {
        testList = testStore.getTests();

        for (Test test : testList) {
            if (test.getTin().equalsIgnoreCase(client.getTin())) {
                clientTestList.add(test);
            }
        }
        return clientTestList;
    }

    public List<Test> organizeClientTestList(List<Test> clientTestList) {

        Comparator<Test> criteria1 = new Comparator<Test>() {

            @Override
            public int compare(Test t1, Test t2) {

                Date date1 = null;
                Date date2 = null;

                String sDate1 = t1.getDateStr();
                try {
                    date1 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String sDate2 = t2.getDateStr();
                try {
                    date2 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (date1.before(date2)) {
                    return 1;
                } else if (date1.after(date2)) {
                    return -1;
                } else {
                    return 0;
                }


            }
        };

        Collections.sort(clientTestList, criteria1);

        return clientTestList;
    }

    public Test getTestChosen(String nhsCode) {
        for (Test test : testList) {
            if (test.getNhsCode().equalsIgnoreCase(nhsCode)) {
                return test;
            }
        }
        return null;
    }
}
