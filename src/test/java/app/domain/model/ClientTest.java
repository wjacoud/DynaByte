package app.domain.model;

import app.domain.model.client.Client;
import app.domain.shared.exceptions.*;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ClientTest {

    Client client;
    String dateStr = "11-11-2000";
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Test
    public void CreateValidClient() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
    }


    //CITIZEN NUMBER TESTS

    @Test(expected = CitizenNumberException.class)
    public void CreateCitizenNumberBlank() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("", "1010101010", date, "1010101010", "client", "10101010101", "male");
    }

    @Test(expected = CitizenNumberException.class)
    public void CreateCitizenNumberWithLetters() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("10101aa01010bb10", "1010101010", date, "1010101010", "client", "10101010101", "male");
    }

    @Test(expected = CitizenNumberException.class)
    public void CreateCitizenNumberWithMoreThan16Characters() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("10101010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
    }

    @Test(expected = CitizenNumberException.class)
    public void CreateCitizenNumberWithLessThan16Characters() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
    }


    //NHS TESTS

    @Test(expected = NhsNumberException.class)
    public void CreateNHSBlank() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "", date, "1010101010", "client", "10101010101", "male");
    }

    @Test(expected = NhsNumberException.class)
    public void CreateNHSWithLetters() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1aa0101aa0", date, "1010101010", "client", "10101010101", "male");
    }

    @Test(expected = NhsNumberException.class)
    public void CreateNHSWithMoreThan10Characters() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "10101010101010", date, "1010101010", "client", "10101010101", "male");
    }

    @Test(expected = NhsNumberException.class)
    public void CreateNHSWithLessThan10Characters() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "101010", date, "1010101010", "client", "10101010101", "male");
    }


    //TIN TESTS

    @Test(expected = TinNumberException.class)
    public void CreateTinBlank() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "", "client", "10101010101", "male");
    }

    @Test(expected = TinNumberException.class)
    public void CreateTinWithLetters() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1aa0101aa0", "client", "10101010101", "male");
    }

    @Test(expected = TinNumberException.class)
    public void CreateTinWithMoreThan10Characters() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "10101010101010", "client", "10101010101", "male");
    }

    @Test(expected = TinNumberException.class)
    public void CreateTinWithLessThan10Characters() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "101010", "client", "10101010101", "male");
    }


    //BIRTHDATE TESTS

    @Test(expected = BirthDateException.class)
    public void CreateNegativeAge() throws ParseException {
        dateStr = "11-11-2050";
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
    }

    @Test(expected = BirthDateException.class)
    public void CreateOldAge() throws ParseException {
        dateStr = "11-11-1050";
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
    }

    @Test
    public void CreateMinAge() throws ParseException {
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
    }

    @Test
    public void CreateMaxAge() throws ParseException {
        LocalDate localDate = LocalDate.now().minusYears(150);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
    }

    //NAME RULES

    @Test(expected = NameException.class)
    public void CreateNameBlank() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "", "10101010101", "male");
    }

    @Test(expected = NameException.class)
    public void CreateNameWithNumbers() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client0", "10101010101", "male");
    }

    @Test(expected = NameException.class)
    public void CreateNameWithMoreThan35Digits() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "Client with more than thirty five digits", "10101010101", "male");
    }

    @Test(expected = NameException.class)
    public void CreateInvalidEmail() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "Client with more than thirty five digits", "10101010101", "male");
        client.setEmail("iasdfuhiosdfhuhuipfgds");
    }

    @Test(expected = NameException.class)
    public void CreateInvalidEmail1() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "Client with more than thirty five digits", "10101010101", "male");
        client.setEmail("");
    }

    @Test(expected = NameException.class)
    public void CreateInvalidSex1() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "Client with more than thirty five digits", "10101010101", "");
        client.setEmail("");
    }

    @Test(expected = NameException.class)
    public void CreateNameMax() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq", "10101010101", "male");
    }


    //PHONE NUMBER TESTS

    @Test(expected = PhoneNumberException.class)
    public void CreatePhoneNumberBlank() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "", "male");
    }

    @Test(expected = PhoneNumberException.class)
    public void CreatePhoneNumberWithLetters() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "1aa01010aa1", "male");
    }

    @Test(expected = PhoneNumberException.class)
    public void CreatePhoneNumberWithMoreThan11Characters() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "1010101010101010", "male");
    }

    @Test(expected = PhoneNumberException.class)
    public void CreatePhoneNumberWithLessThan11Characters() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "101010", "male");
    }


    //SEX TESTS


    @Test(expected = SexException.class)
    public void CreateSexOther() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "invalid");
    }

    @Test
    public void GetBirthDate() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        assertEquals(date, client.getBirthDate());
    }


    @Test
    public void GetName() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        assertEquals("client", client.getName());
    }

    @Test
    public void GetSex() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        assertEquals("male", client.getSex());
    }

    @Test
    public void GetEmail() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setEmail("client35@gmail.com");
        assertEquals("client35@gmail.com", client.getEmail());
    }

    @Test
    public void SetCitizenNumber() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setCitizenNumber("1010171010101010");

    }

    @Test(expected = CitizenNumberException.class)
    public void SetWrongCitizenNumber() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setCitizenNumber("!!!!!!!!!!!!!!!!");

    }

    @Test
    public void SetHealthcareNumber() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setHealthcareNumber("1010151010");
    }

    @Test(expected = NhsNumberException.class)
    public void SetWrongHealthcareNumber() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setHealthcareNumber("11111111111111111111111111111");
    }

    @Test
    public void SetTin() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setTin("1018101010");
    }

    @Test (expected = TinNumberException.class)
    public void SetWrongTin() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setTin("!!!!!");
    }

    @Test
    public void SetName() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setName("isjdfjisdf");
    }

    @Test(expected = NameException.class)
    public void SetWrongName() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setName("!!!!!!");
    }

    @Test
    public void SetPhoneNumber() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setPhoneNumber("10101010101");
    }

    @Test(expected = PhoneNumberException.class)
    public void SetWrongPhoneNumber() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setPhoneNumber("101010!!!01");
    }

    @Test
    public void SetSex() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setSex("female");
    }

    @Test(expected = SexException.class)
    public void SetWrongSex() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setSex("aaaaaaa");
    }

    @Test
    public void SetBirthDate() throws ParseException {
        //Arrange + Act
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setBirthDate(date);
    }

    //ADDRESS TESTS

    @Test
    public void CheckAddress() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setAddress("example");

        Assert.assertEquals("example",client.getAddress());
    }

    @Test
    public void CheckAddressMax() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setAddress("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAddressRules() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setAddress("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAddressRules2() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setAddress("!!!!!!!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkAddressRules3() throws ParseException {
        Date date = dateFormat.parse(dateStr);
        client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        client.setAddress("ipncrnmduvjqspurhcbukdzbgbgvfjgeubixhrqfjsaahdtyxfoguetpjaveihcozazaivpkmecmykqrnkdxnxabhgabjwvfdznbilsqxwpezzpbvaujulssacqidmyykbgh");
    }
}