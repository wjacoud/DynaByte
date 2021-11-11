package app.domain.model;

import app.domain.model.employees.ChemistryTechnologist;
import org.junit.Test;

public class ChemistryTechnologistTest {

    @Test
    public void CreateChemistryTechnologist() {
        //Arrange + Act

        new ChemistryTechnologist("id", "name", "sdbfhsdf", "47389298767", "ChemistryTechnologist@gmail.com", "1010");
    }

}