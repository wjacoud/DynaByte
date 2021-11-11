package app.domain.model.employees;

/**
 * class responsible for the Receptionist
 *
 * @author Ricardo Faria 1201405
 */
public class Receptionist extends Employee {
    /**
     * Constructor that create a full Client object
     *
     * @param id          - id Receptionist
     * @param name        - name Receptionist
     * @param phoneNumber - phone number Receptionist
     * @param email       - email Receptionist
     * @param address     - address Receptionist
     * @param soc         - Standard Occupational Classification (soc) Receptionist
     */
    public Receptionist(String id, String name, String address, String phoneNumber, String email, String soc) {
        super(id, name, "Receptionist", phoneNumber, email, address, soc);
    }
}
