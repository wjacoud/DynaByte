package app.domain.model;

import app.domain.model.employees.LaboratoryCoordinator;
import org.junit.Test;

public class LaboratoryCoordinatorTest {

    @Test
    public void CreateLaboratoryCoordinator() {
        //Arrange + Act

        new LaboratoryCoordinator("id", "name", "fdgdafgs", "10131015101", "LaboratoryCoordinator@gmail.com", "1010");
    }

}