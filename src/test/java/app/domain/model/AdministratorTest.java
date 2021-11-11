package app.domain.model;

import app.domain.model.employees.Administrator;
import org.junit.Test;

public class AdministratorTest {

    @Test
    public void CreateAdministrator() {
        new Administrator("id", "name", "fdgdafgs", "10101015101", "address@gmail.com", "1010");
    }
}