package app.domain.model;

import app.domain.model.test.TestDiagnosis;
import app.domain.model.test.TestReport;
import app.domain.model.testComponents.ParameterCategory;
import app.domain.model.testComponents.TestType;
import app.domain.shared.exceptions.NhsNumberException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TestTest {

    public TestType getTestType() {
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory category = new ParameterCategory("12345", "example");
        categories[0] = category;
        return new TestType("COV19", "sample", "sample", categories);
    }

    @Test
    public void checkNhsCode() {
        TestType testType = getTestType();
        new app.domain.model.test.Test("1234567890123456", "000000000001", "123456789012", testType, "27/05/2021 18:17");
    }

    @Test(expected = NhsNumberException.class)
    public void checkNhsCodeBlank() {
        TestType testType = getTestType();
        new app.domain.model.test.Test("1234567890123456", "000000000001", "", testType, "27/05/2021 18:17");
    }

    @Test(expected = NhsNumberException.class)
    public void checkInvalidNhsCode() {
        TestType testType = getTestType();
        new app.domain.model.test.Test("1234567890123456", "000000000001", "!!!!!!!!!!!!", testType, "27/05/2021 18:17");
    }

    @Test(expected = NhsNumberException.class)
    public void checkNhsCodeWithMoreThen12Chars() {
        TestType testType = getTestType();
        new app.domain.model.test.Test("1234567890123456", "000000000001", "123456789012345", testType, "27/05/2021 18:17");
    }

    @Test(expected = NhsNumberException.class)
    public void checkNhsCodeWithLessThen12Chars() {
        TestType testType = getTestType();
        new app.domain.model.test.Test("1234567890123456", "000000000001", "1234567", testType, "27/05/2021 18:17");
    }

    @Test
    public void getTin() {
        String expected = "1234567890123456";
        TestType testType = getTestType();
        app.domain.model.test.Test test = new app.domain.model.test.Test(expected, "000000000001", "123456789012", testType, "27/05/2021 18:17");
        Assert.assertEquals(expected, test.getTin());
    }

    @Test
    public void getID() {
        String expected = "000000000001";
        TestType testType = getTestType();
        app.domain.model.test.Test test = new app.domain.model.test.Test("1234567890123456", expected, "123456789012", testType, "27/05/2021 18:17");
        Assert.assertEquals(expected, test.getTestCode());
    }

    @Test
    public void getState() {
        String expect = "REGISTERED";
        TestType testType = getTestType();
        app.domain.model.test.Test test = new app.domain.model.test.Test("1234567890123456", "000000000001", "123456789012", testType, "27/05/2021 18:17");
        Assert.assertEquals(expect, test.getState().toString());
    }

    @Test
    public void getValidationDate() {
        TestType testType = getTestType();
        app.domain.model.test.Test test = new app.domain.model.test.Test("1234567890123456", "000000000001", "123456789012", testType, "27/05/2021 18:17");
        Assert.assertNull(test.getValidationDate());
    }

    @Test
    public void getTestTypeMethod() {
        TestType testType = getTestType();
        app.domain.model.test.Test test = new app.domain.model.test.Test("1234567890123456", "000000000001", "123456789012", testType, "27/05/2021 18:17");
        Assert.assertEquals(testType, test.getTestType());
    }

    @Test
    public void getTestTypeDescription() {
        TestType testType = getTestType();
        app.domain.model.test.Test test = new app.domain.model.test.Test("1234567890123456", "000000000001", "123456789012", testType, "27/05/2021 18:17");
        Assert.assertEquals("sample", test.getTestTypeDescription());
    }

    @Test
    public void getDateStr() {
        TestType testType = getTestType();
        app.domain.model.test.Test test = new app.domain.model.test.Test("1234567890123456", "000000000001", "123456789012", testType, "27/05/2021 18:17");
        Assert.assertEquals("27/05/2021", test.getDateStr());
    }

    @Test
    public void getDate() {
        TestType testType = getTestType();
        app.domain.model.test.Test test = new app.domain.model.test.Test("1234567890123456", "000000000001", "123456789012", testType, "27/05/2021 18:17");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse("27/05/2021 18:17", dtf);
        Assert.assertEquals(date, test.getDate());
    }

    @Test
    public void setState() {
        TestType testType = getTestType();
        app.domain.model.test.Test test = new app.domain.model.test.Test("1234567890123456", "000000000001", "123456789012", testType, "27/05/2021 18:17");
        test.setState(app.domain.model.test.Test.State.REGISTERED);
    }

    @Test
    public void setReport() {
        TestType testType = getTestType();
        app.domain.model.test.Test test = new app.domain.model.test.Test("1234567890123456", "000000000001", "123456789012", testType, "27/05/2021 18:17");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse("30/05/2021 18:17", dtf);
        TestDiagnosis testDiagnosis = new TestDiagnosis("sdfujihsdfiahju");
        TestReport testReport = new TestReport("000000000001",testDiagnosis,"yesyes",date);
        test.setReport(testReport);
    }

    @Test
    public void getReport() {
        TestType testType = getTestType();
        app.domain.model.test.Test test = new app.domain.model.test.Test("1234567890123456", "000000000001", "123456789012", testType, "27/05/2021 18:17");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse("30/05/2021 18:17", dtf);
        TestDiagnosis testDiagnosis = new TestDiagnosis("sdfujihsdfiahju");
        TestReport testReport = new TestReport("000000000001",testDiagnosis,"yesyes",date);
        test.setReport(testReport);
        Assert.assertEquals(testReport, test.getReport());
    }

    @Test
    public void analyzedDate() {
        TestType testType = getTestType();
        app.domain.model.test.Test test = new app.domain.model.test.Test("1234567890123456", "000000000001", "123456789012", testType, "27/05/2021 18:17");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse("30/05/2021 18:17", dtf);
        TestDiagnosis testDiagnosis = new TestDiagnosis("sdfujihsdfiahju");
        TestReport testReport = new TestReport("000000000001",testDiagnosis,"yesyes",date);
        test.setReport(testReport);
        test.registerAnalyzedDate(date);
        Assert.assertNotNull(test.analyzedDate());
    }


    @Test
    public void getParameterList() {
        TestType testType = getTestType();
        app.domain.model.test.Test test = new app.domain.model.test.Test("1234567890123456", "000000000001", "123456789012", testType, "27/05/2021 18:17");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.parse("30/05/2021 18:17", dtf);
        TestDiagnosis testDiagnosis = new TestDiagnosis("sdfujihsdfiahju");
        TestReport testReport = new TestReport("000000000001",testDiagnosis,"yesyes",date);
        test.setReport(testReport);
        test.registerAnalyzedDate(date);
        Assert.assertNotNull(test.getParameterList());
    }
    @Test(expected = NullPointerException.class)
    public void addTestParameterResult() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        TestType testType = getTestType();
        app.domain.model.test.Test test = new app.domain.model.test.Test("1234567890123456", "000000000001", "123456789012", testType, "27/05/2021 18:17");
        test.addTestParameterResult("yes",2.4);
    }

    @Test
    public void checkGetParameterList() {
        TestType testType = getTestType();
        app.domain.model.test.Test test = new app.domain.model.test.Test("9999999999", "000000000001", "123456789012", testType, "27/05/2021 18:17");
        test.getParameterList();
    }


}
