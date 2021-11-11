package app.domain.model.stores;

import app.domain.model.client.Client;

import java.util.*;

/**
 * class responsible for the ClientsStore
 *
 * @author Ricardo Faria 1201405
 */
public class ClientsStore implements java.io.Serializable {

    private List<Client> clientList;


    /**
     * Constructor that create a empty list of Client type
     */
    public ClientsStore() {
        clientList = new ArrayList<>();
    }


    /**
     * Create a new instance of client
     *
     * @param citizenNumber    - citizen card number Client
     * @param healthcareNumber - national healthcare service (nhs) Client
     * @param birthDate        - birth date (xx/yy/zzz) Client
     * @param tin              - tax identification number (tin) Client
     * @param name             - name Client
     * @param phoneNumber      - phone number Client
     * @param sex              - sex (male/female) Client
     * @return instance of client
     */
    public Client createClient(String citizenNumber, String healthcareNumber, Date birthDate, String tin, String name, String phoneNumber, String sex) {
        return new Client(citizenNumber, healthcareNumber, birthDate, tin, name, phoneNumber, sex);
    }


    public Client getClientFromClientEmail(String email) {

        for (Client c : clientList) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }

    /**
     * add the customer to the list
     *
     * @param client client
     * @return success of saving the client
     */
    public boolean saveClient(Client client) {
        if (validateClient(client)) {
            clientList.add(client);
            return true;
        }
        return false;
    }


    /**
     * return the client list
     *
     * @return client list
     */
    public List<Client> getClientList() {
        return clientList;
    }


    /**
     * Verify is exist a repeat client
     *
     * @param client - instance of client create with the createClient method
     * @return true if doesn't exist a repeat client
     */
    public boolean validateClient(Client client) {
        if (client == null)
            return false;

        for (Client client1 : clientList) {
            if (client1.getCitizenNumber().equalsIgnoreCase(client.getCitizenNumber()) || client1.getHealthcareNumber().equalsIgnoreCase(client.getHealthcareNumber()) || client1.getPhoneNumber().equalsIgnoreCase(client.getPhoneNumber()) || client1.getTin().equalsIgnoreCase(client.getTin())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verify is exist a repeat client
     *
     * @param client - instance of client create with the createClient method
     * @return true if doesn't exist a repeat client
     */
    public boolean validatePhoneNumber(Client client) {
        if (client == null)
            return false;

        int ok = 0;
        for (Client client1 : clientList) {
            if (client1.getPhoneNumber().equalsIgnoreCase(client.getPhoneNumber())) {
                ok++;
                if (ok == 2) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * checks if the client already exists
     *
     * @param tin - tax identification number (tin)
     * @return verification success
     */
    public boolean verifyExistingClient(String tin) {
        for (Client client1 : clientList) {
            if (client1.getTin().equalsIgnoreCase(tin)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method used to get the clients list ordered by tin
     * @return - list of clients ordered by tin
     */
    public List<Client> getClientOrderTin() {

        List<Client> clientListOrder = new ArrayList<>(clientList);

        for(int i = 0; i<clientListOrder.size(); i++){
            for (int j = i+1; j<clientListOrder.size();j++){
                Client aux = null;
                if (clientListOrder.get(j).getTin().compareTo(clientListOrder.get(i).getTin()) < 0){
                    aux = clientListOrder.get(i);
                    clientListOrder.set(i,clientListOrder.get(j));
                    clientListOrder.set(j,aux);
                }
            }
        }

        return clientListOrder;
    }

    /**
     * Method used to get a list of clients ordered by name
     * @return - clients list ordered by name
     */
    public List<Client> getClientOrderName() {

        List<Client> clientListOrder = new ArrayList<>(clientList);

        for(int i = 0; i<clientListOrder.size(); i++){
            for (int j = i+1; j<clientListOrder.size();j++){
                Client aux = null;
                if (clientListOrder.get(j).getName().compareTo(clientListOrder.get(i).getName()) < 0){
                    aux = clientListOrder.get(i);
                    clientListOrder.set(i,clientListOrder.get(j));
                    clientListOrder.set(j,aux);
                }
            }
        }

        return clientListOrder;
    }

}
