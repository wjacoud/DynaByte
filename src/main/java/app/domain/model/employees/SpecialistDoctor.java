package app.domain.model.employees;

import org.apache.commons.lang3.StringUtils;

/**
 * class responsible for the SpecialistDoctor
 *
 * @author Rodrigo Oliveira 1201406
 */
public class SpecialistDoctor extends Employee {

    private String doctorIndexNumber;

    /**
     * Constructor that create a full SpecialistDoctor object
     *
     * @param id          - id
     * @param name        - name
     * @param phoneNumber - phone number
     * @param email       - email
     * @param address     - address
     * @param soc         - Standard Occupational Classification (soc)
     */
    public SpecialistDoctor(String id, String name, String address, String phoneNumber, String email, String soc, String doctorIndexNumber) {
        super(id, name, "SpecialistDoctor", phoneNumber, email, address, soc);

        boolean error = false;

        try {
            checkDoctorIndexNumber(doctorIndexNumber);
        } catch (Exception e) {
            error = true;
        }

        if (!error) {
            this.doctorIndexNumber = doctorIndexNumber;
        } else {
            throw new IllegalArgumentException("Error with creation");
        }
    }

    private void checkDoctorIndexNumber(String doctorIndexNumber) {
        if (StringUtils.isBlank(doctorIndexNumber))
            throw new IllegalArgumentException("Doctor Index Numbe cannot be blank");
        if (!(doctorIndexNumber.matches("[0-9]+")))
            throw new IllegalArgumentException("Doctor Index Number only accepts numbers");
    }

    public String getDoctorIndexNumber() {
        return doctorIndexNumber;
    }
}
