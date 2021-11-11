package app.domain.model.employees;

/**
 * class responsible for the LaboratoryCoordinator
 *
 * @author Rodrigo Oliveira 1201406
 */
public class LaboratoryCoordinator extends Employee {
    /**
     * Constructor that create a full LaboratoryCoordinator object
     *
     * @param id          - id
     * @param name        - name
     * @param phoneNumber - phone number
     * @param email       - email
     * @param address     - address
     * @param soc         - Standard Occupational Classification (soc)
     */
    public LaboratoryCoordinator(String id, String name, String address, String phoneNumber, String email, String soc) {
        super(id, name, "LaboratoryCoordinator", phoneNumber, email, address, soc);
    }
}