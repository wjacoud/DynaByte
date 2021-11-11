package app.controller;

import app.domain.model.*;
import app.domain.model.client.Client;
import app.domain.model.stores.ClientsStore;
import auth.AuthFacade;
import auth.UserSession;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * class responsible for updating client data
 *
 * @author Rodrigo Oliveira 1201406
 */

public class UpdateClientController {
    private Client client = null;
    private AuthFacade authFacade;
    private ClientsStore clientStore;


    /**
     * Constructor that initializes the stores and gets userSession
     */
    public UpdateClientController() {
        App app = App.getInstance();
        Company company = app.getCompany();
        clientStore = company.getClientStore();
        authFacade = company.getAuthFacade();
        UserSession userSession = authFacade.getCurrentUserSession();
        client = clientStore.getClientFromClientEmail(userSession.getUserId().getEmail());
    }

    public void editName(String name) {
        client.setName(name);
    }

    public void editPhoneNumber(String phoneNumber) {
        String aux = client.getPhoneNumber();

        client.setPhoneNumber(phoneNumber);
        if (!clientStore.validatePhoneNumber(client)) {
            client.setPhoneNumber(aux);
            throw new IllegalArgumentException("Phone number already exists!");
        }
    }

    public String getAddress() {
        return client.getAddress();
    }

    public void editAddress(String address) {
        client.setAddress(address);
    }

    public void editSex(String sex) {
        client.setSex(sex);
    }

    public String getCitizenNumber() {
        return client.getCitizenNumber();
    }

    public String getHealthcareNumber() {
        return client.getHealthcareNumber();
    }

    public String getTin() {
        return client.getTin();
    }

    public String getBirthDate() {
        Date date = client.getBirthDate();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public String getName() {
        return client.getName();
    }

    public String getPhoneNumber() {
        return client.getPhoneNumber();
    }

    public String getSex() {
        return client.getSex();
    }

}
