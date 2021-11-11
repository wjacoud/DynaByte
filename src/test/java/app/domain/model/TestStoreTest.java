package app.domain.model;

import app.domain.model.client.Client;
import app.domain.model.stores.ClientsStore;
import app.domain.model.stores.TestStore;
import app.domain.model.testComponents.ParameterCategory;
import app.domain.model.testComponents.TestType;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestStoreTest {
    @Test(expected = IllegalArgumentException.class)
    public void createTestNullv1() {
        TestStore store = new TestStore();
        app.domain.model.test.Test test = new app.domain.model.test.Test(null, null, null, null, "2021/05/27 18:17");

        store.saveTest(test);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTestNullv2() {
        TestStore store = new TestStore();
        app.domain.model.test.Test test = new app.domain.model.test.Test("1234567890123456", null, null, null, "2021/05/27 18:17");

        store.saveTest(test);
    }

    @Test
    public void saveInvalidTest() {
        TestStore store = new TestStore();
        ParameterCategory[] parameterCategories = new ParameterCategory[1];
        parameterCategories[0] = new ParameterCategory("12345", "yes");
        TestType testType = new TestType("BL000", "sdfyhbiug", "syhbuidf", parameterCategories);
        app.domain.model.test.Test test = new app.domain.model.test.Test("1234567890123456", "000000000001", "1234567890df", testType, "27/05/2021 18:17");
        store.saveTest(test);

        app.domain.model.test.Test test1 = new app.domain.model.test.Test("1234567890123456", "000000000001", "1234567890df", testType, "27/05/2021 18:17");
        Assert.assertFalse(store.saveTest(test1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTestNullv3() {
        TestStore store = new TestStore();
        app.domain.model.test.Test test = new app.domain.model.test.Test("1234567890123456", "000000000001", null, null, "2021/05/27 18:17");

        store.saveTest(test);
    }

    @Test
    public void validateTest3() {
        TestStore store = new TestStore();
        Assert.assertNotEquals(true, store.validateTest(null));
    }

    @Test
    public void checkGetTests() {
        TestStore testStore = new TestStore();

        List<app.domain.model.test.Test> testList = new ArrayList<>();
        testList = testStore.getTests();
    }

    @Test
    public void checkSaveTest() {
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("33333", "example");
        categories[0] = cat;
        TestType testType = new TestType("COV19", "something", "something", categories);
        TestStore store = new TestStore();
        store.saveTest(store.createTest("1234567890", "000000000010", "123456789017", testType, "11/11/2011 20:20"));
    }

    @Test
    public void checkWrongCreateTest() {
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("33333", "example");
        categories[0] = cat;
        TestType testType = new TestType("COV19", "something", "something", categories);
        TestStore store = new TestStore();
        store.saveTest(store.createTest("1234567890", "000000000010", "123456789017", testType, "11/11/2011 20:20"));

        Assert.assertEquals(null, store.createTest("COV19", "000000000010", "123456789016", testType, "20/10/2011 20:20"));
    }

    @Test
    public void checkCreateTest() {
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("33333", "example");
        categories[0] = cat;
        TestType testType = new TestType("COV19", "something", "something", categories);
        TestStore store = new TestStore();
        store.saveTest(store.createTest("1234567890", "000000000010", "123456789017", testType, "11/11/2011 20:20"));
        app.domain.model.test.Test test;
        test = store.createTest("1234567890", "000000000100", "123456789015", testType, "20/10/2011 20:20");

        Assert.assertNotEquals(null, test);
    }

    @Test
    public void checkVerifyExistingNhsCode() {
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("33333", "example");
        categories[0] = cat;
        TestType testType = new TestType("COV19", "something", "something", categories);
        TestStore store = new TestStore();
        store.saveTest(store.createTest("1234567890", "000000000010", "123456789017", testType, "11/11/2011 20:20"));

        Assert.assertTrue(store.verifyExistingNhsCode("123456789017"));
    }

    @Test
    public void checkFalseVerifyExistingNhsCode() {
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("33333", "example");
        categories[0] = cat;
        TestType testType = new TestType("COV19", "something", "something", categories);
        TestStore store = new TestStore();
        store.saveTest(store.createTest("1234567890", "000000000010", "123456789017", testType, "11/11/2011 20:20"));

        Assert.assertFalse(store.verifyExistingNhsCode("123456789014"));
    }

    @Test
    public void createId() {
        TestStore store = new TestStore();
        Assert.assertNotNull(store.createId());
    }

    @Test
    public void getValidatedTests() throws ParseException {
        ParameterCategory[] categories = new ParameterCategory[1];
        String dateStr = "11-11-2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse(dateStr);
        Client client = new Client("1010101010101010", "1010101010", date, "1010101010", "client", "10101010101", "male");
        ParameterCategory cat = new ParameterCategory("33333", "example");
        categories[0] = cat;
        TestType testType = new TestType("COV19", "something", "something", categories);
        TestStore store = new TestStore();
        store.saveTest(store.createTest("1234567890", "000000000010", "123456789017", testType, "11/11/2011 20:20"));

        store.getValidatedTests(client);
    }

    @Test
    public void getValidatedTests1() throws ParseException {
        ParameterCategory[] categories = new ParameterCategory[1];
        String dateStr = "11-11-2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse(dateStr);
        Client client = new Client("1010101010101010", "1010101010", date, "1234567890", "client", "10101010101", "male");
        ParameterCategory cat = new ParameterCategory("33333", "example");
        categories[0] = cat;
        TestType testType = new TestType("COV19", "something", "something", categories);
        TestStore store = new TestStore();
        app.domain.model.test.Test test = store.createTest("1234567890", "000000000010", "123456789017", testType, "11/11/2011 20:20");
        test.setState(app.domain.model.test.Test.State.VALIDATED);
        store.saveTest(test);

        store.getValidatedTests(client);
    }

    @Test
    public void checkSaveTestTrue() throws ParseException {
        ParameterCategory[] categories = new ParameterCategory[1];
        String dateStr = "11-11-2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse(dateStr);
        Client client = new Client("1010101010101010", "1010101010", date, "1234567890", "client", "10101010101", "male");
        ParameterCategory cat = new ParameterCategory("33333", "example");
        categories[0] = cat;
        TestType testType = new TestType("COV19", "something", "something", categories);
        TestStore store = new TestStore();
        app.domain.model.test.Test test = store.createTest("1234567890", "000000000010", "123456789017", testType, "11/11/2011 20:20");

        Assert.assertEquals(true, store.saveTest(test));
    }


    @Test
    public void getTests() throws ParseException {
        ParameterCategory[] categories = new ParameterCategory[1];
        String dateStr = "11-11-2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse(dateStr);
        Client client = new Client("1010101010101010", "1010101010", date, "1234567890", "client", "10101010101", "male");
        ParameterCategory cat = new ParameterCategory("33333", "example");
        categories[0] = cat;
        TestType testType = new TestType("COV19", "something", "something", categories);
        TestStore store = new TestStore();
        app.domain.model.test.Test test = store.createTest("1234567890", "000000000010", "123456789017", testType, "11/11/2011 20:20");

        Assert.assertNotNull(store.getTests());
    }

    @Test
    public void getValidatedTests2() throws ParseException {
        ParameterCategory[] categories = new ParameterCategory[1];
        String dateStr = "11-11-2000";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = dateFormat.parse(dateStr);
        Client client = new Client("1010101010101010", "1010101010", date, "1234567890", "client", "10101010101", "male");
        ParameterCategory cat = new ParameterCategory("33333", "example");
        categories[0] = cat;
        TestType testType = new TestType("COV19", "something", "something", categories);
        TestStore store = new TestStore();
        app.domain.model.test.Test test = store.createTest("1234567890", "000000000010", "123456789017", testType, "11/11/2011 20:20");

        Assert.assertNotNull(store.getValidatedTests(client));
    }

}
