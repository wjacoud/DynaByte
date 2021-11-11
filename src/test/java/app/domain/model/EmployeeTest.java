package app.domain.model;

import app.domain.model.employees.Receptionist;
import app.domain.shared.exceptions.NameException;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeTest {

    @Test
    public void getId() {
        Receptionist recep = new Receptionist("id12345", "name", "address", "12345678901", "email@email.com", "1234");
        assertEquals("id12345", recep.getId());
    }

    @Test
    public void getAddress() {
        Receptionist recep = new Receptionist("id12345", "name", "address", "12345678901", "email@email.com", "1234");
        assertEquals("address", recep.getAddress());
    }

    @Test
    public void getSoc() {
        Receptionist recep = new Receptionist("id12345", "name", "address", "12345678901", "email@email.com", "1234");
        assertEquals("1234", recep.getSoc());
    }

    @Test
    public void getName() {
        Receptionist recep = new Receptionist("id12345", "name", "address", "12345678901", "email@email.com", "1234");
        assertEquals("name", recep.getName());
    }

    @Test
    public void getFunction() {
        Receptionist recep = new Receptionist("id12345", "name", "address", "12345678901", "email@email.com", "1234");
        assertEquals("Receptionist", recep.getFunction());
    }

    @Test
    public void maxName() {
        Receptionist recep = new Receptionist("id12345", "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq", "address", "12345678901", "email@email.com", "1234");
        assertEquals("Receptionist", recep.getFunction());
    }

    @Test(expected = NameException.class)
    public void blankName() {
        Receptionist recep = new Receptionist("id12345", "", "address", "12345678901", "email@email.com", "1234");
        assertEquals("Receptionist", recep.getFunction());
    }
}