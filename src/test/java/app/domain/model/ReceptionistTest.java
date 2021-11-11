package app.domain.model;

import app.domain.model.employees.Receptionist;
import org.junit.Test;

import java.text.SimpleDateFormat;

public class ReceptionistTest {

    String dateStr = "11/11/2000";
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    public void CreateReceptionist() {
        //Arrange + Act

        new Receptionist("id", "name", "fdgdafgs", "14131015101", "Receptionist@gmail.com", "1010");
    }
}