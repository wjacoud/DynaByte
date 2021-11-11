package app.domain.model.employees;

/**
 * class responsible for the Administrator
 *
 * @author Rodrigo Oliveira 1201406 and Gonçalo Pereira 1201500
 */
public class Administrator extends Employee implements java.io.Serializable{


    /**
     * Create a new instance of clinical
     *
     * @param id          - id laboratory
     * @param name        - name Administrator
     * @param phoneNumber - phone number Administrator
     * @param email       - email Administrator
     * @param address     - address Administrator
     * @param soc         - Standard Occupational Classification (soc) Administrator
     */
    public Administrator(String id, String name, String address, String phoneNumber, String email, String soc) {
        super(id, name, "Administrator", phoneNumber, email, address, soc);
    }

}

