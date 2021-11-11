package app.controller;

import app.domain.model.*;
import app.domain.model.client.Client;
import app.domain.model.client.ClientNotification;
import app.domain.model.stores.ClientsStore;
import app.domain.shared.Constants;
import auth.AuthFacade;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;

/**
 * class responsible for the Client Creator Controller
 *
 * @author Ricardo Faria 1201405
 */

public class CreateClientController {
    private Client client = null;
    private AuthFacade authFacade;
    private ClientsStore clientStore;


    /**
     * Constructor that initializes the stores
     */
    public CreateClientController() {
        App app = App.getInstance();
        Company company = app.getCompany();
        clientStore = company.getClientStore();
        authFacade = company.getAuthFacade();
    }


    /**
     * Create a new instance of Client
     *
     * @param citizenNumber    - Client citizen card number
     * @param healthcareNumber - Client national healthcare service (nhs)
     * @param birthDate        - Client birth date (xx/yy/zzz)
     * @param tin              - Client tax identification number (tin)
     * @param name             - Client name
     * @param phoneNumber      - Client phone number
     * @param sex              - Client sex (male/female)
     * @return success of the operation
     */
    public boolean createClient(String citizenNumber, String healthcareNumber, Date birthDate, String tin, String name, String phoneNumber, String sex) {
        client = clientStore.createClient(citizenNumber, healthcareNumber, birthDate, tin, name, phoneNumber, sex);

        return client != null;
    }


    /**
     * gives the order to the Receptionist to save the Client
     *
     * @return success of the operation
     */
    public boolean saveClient() {
        return clientStore.saveClient(client);
    }


    /**
     * @param email - client email
     * @param name  - client name
     * @return whether add user was successful or failed
     */
    public boolean addUser(String email, String name) {
        String password = RandomStringUtils.randomAlphanumeric(10);

        if (!authFacade.existsUser(email)) {
            if (ClientNotification.sendMessage("email", "User: " + email + "\npassword: " + password, client.getTin())) {
                client.setEmail(email);
                return authFacade.addUserWithRole(name, email, password, Constants.ROLE_CLIENT);
            }
            return false;
        }
        return false;
    }
}
