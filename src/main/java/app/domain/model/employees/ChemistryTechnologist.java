package app.domain.model.employees;

/**
 * class responsible for the ChemistryTechnologist
 *
 * @author Rodrigo Oliveira 1201406
 */
public class ChemistryTechnologist extends Employee {
    /**
     * Constructor that create a full ChemistryTechnologist object
     *
     * @param id          - id
     * @param name        - name
     * @param phoneNumber - phone number
     * @param email       - email
     * @param address     - address
     * @param soc         - Standard Occupational Classification (soc)
     */
    public ChemistryTechnologist(String id, String name,String address , String phoneNumber,String email, String soc) {
        super(id, name, "ChemistryTechnologist", phoneNumber, email, address, soc);
    }

}

