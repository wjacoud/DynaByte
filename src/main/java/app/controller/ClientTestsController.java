package app.controller;

import app.domain.model.*;
import app.domain.model.client.Client;
import app.domain.model.stores.ClientsStore;
import app.domain.model.stores.TestStore;
import app.domain.model.test.Test;
import app.domain.model.test.TestParameter;

import java.util.ArrayList;
import java.util.List;
/**
 * class responsible for the Test
 *
 * @author José Cruz 1201401
 */
public class ClientTestsController {

    ClientsStore clientsStore;
    TestStore testStore;
    List<Client> clientListOrder;
    Client client;

    /**
     * Constructor that create a empty ClientTestController object
     */
    public ClientTestsController(){
        App app = App.getInstance();
        Company company = app.getCompany();
        clientsStore = company.getClientStore();
        testStore = company.getTestStore();
        clientListOrder = new ArrayList<>();
    }

    /**
     * Method used to get a list of clients ordered by tin
     * @return - client list ordered by tin
     */
    public List<Client> getClientListTin(){
        clientListOrder = new ArrayList<>();
        clientListOrder.addAll(clientsStore.getClientOrderTin());
        return clientListOrder;
    }

    /**
     * Method used to get a list of clients ordered by name
     * @return - client list ordered by name
     */
    public List<Client> getClientListName(){
        clientListOrder = new ArrayList<>();
        clientListOrder.addAll(clientsStore.getClientOrderName());
        return clientListOrder;
    }

    /**
     * Method used to get the chosen client
     * @param tin - tin
     * @return - client
     */
    public Client getClientChosen(String tin){
        for (Client c : clientListOrder){
            try{
                if (tin.equalsIgnoreCase(c.getTin())){
                    client = c;
                }
            } catch (Exception e) {
                //empty
            }

        }

        return client;
    }

    /**
     * Method used to get all validated tests
     * @param c - client
     * @return - list with all validated tests
     */
    public List<Test> getValidatedTests(Client c){
        return testStore.getValidatedTests(c);
    }

    /**
     * Method used to get chosen test
     * @param tin - tin
     * @param testList - test list
     * @return - test
     */
    public Test getTestChosen(String tin, List<Test> testList){
        Test test = null;

        for (Test t : testList){
            if (tin.equalsIgnoreCase(t.getTin())){
                test = t;
            }
        }

        return test;
    }

    /**
     * Method used to get the output of a test
     * @param t - test
     * @return - string with the information of the test
     */
    public String toString(Test t){
        StringBuilder sb = new StringBuilder();
        sb.append("Test ID: ").append(t.getTestCode()).append("\nClient Tin: ").append(t.getTin()).append("\nNHS code: ").append(t.getNhsCode()).append("\nTest Type: ").append(t.getTestType().getDescription()).append("\nDeliver Data: ").append(t.getValidationDate().toString()).append("\nTest Result: ");

        for(TestParameter tp : t.getTestParameterList()){
            sb.append("\n").append(tp.getParameter().getName()).append(": result = ").append(tp.getTestParameterResult().getResult()).append("; min reference value = ").append(tp.getTestParameterResult().getRefValue().getMinRefValue()).append("; max reference value = ").append(tp.getTestParameterResult().getRefValue().getMaxRefValue());
        }

        return sb.toString();
    }

}
