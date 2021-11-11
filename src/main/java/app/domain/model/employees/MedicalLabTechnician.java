package app.domain.model.employees;

/**
 * class responsible for the MedicalLabTechnician
 *
 * @author Rodrigo Oliveira 1201406
 */
public class MedicalLabTechnician extends Employee {
    /**
     * Constructor that create a full MedicalLabTechnicians object
     *
     * @param id          - id
     * @param name        - name
     * @param phoneNumber - phone number
     * @param email       - email
     * @param address     - address
     * @param soc         - Standard Occupational Classification (soc)
     */
    public MedicalLabTechnician(String id, String name, String address, String phoneNumber, String email, String soc) {
        super(id, name, "MedicalLabTechnician", phoneNumber, email, address, soc);
    }

}