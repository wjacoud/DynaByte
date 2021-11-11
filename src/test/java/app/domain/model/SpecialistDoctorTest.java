package app.domain.model;

import app.domain.model.employees.SpecialistDoctor;
import org.junit.Assert;
import org.junit.Test;

public class SpecialistDoctorTest {

    @Test
    public void CreateSpecialistDoctor() {
        //Arrange + Act

        new SpecialistDoctor("id", "name", "fdgdafgs", "10131015181", "SpecialistDoctor@gmail.com", "1010", "1321");
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidSpecialistDoctor() {
        //Arrange + Act

        new SpecialistDoctor("id", "name", "fdgdafgs", "10131015181", "SpecialistDoctor@gmail.com", "1010", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidSpecialistDoctorV2() {
        //Arrange + Act

        new SpecialistDoctor("id", "name", "fdgdafgs", "10131015181", "SpecialistDoctor@gmail.com", "1010", "aaa");
    }

    @Test
    public void getDoctorIndexNumber() {
        //Arrange + Act

        SpecialistDoctor sd = new SpecialistDoctor("id", "name", "fdgdafgs", "10131015181", "SpecialistDoctor@gmail.com", "1010", "1234");

        Assert.assertNotNull(sd.getDoctorIndexNumber());
    }
}