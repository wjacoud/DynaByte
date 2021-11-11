package app.domain.model;

import app.domain.model.employees.MedicalLabTechnician;
import org.junit.Test;

public class MedicalLabTechnicianTest {

    @Test
    public void CreateMedicalLabTechnician() {
        //Arrange + Act

        new MedicalLabTechnician("id", "name", "fdgdafgs", "10131915101", "MedicalLabTechnician@gmail.com", "1010");
    }

}