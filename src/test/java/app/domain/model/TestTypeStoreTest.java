package app.domain.model;

import app.domain.model.stores.TestTypeStore;
import app.domain.model.testComponents.ParameterCategory;
import app.domain.model.testComponents.TestType;
import org.junit.Assert;
import org.junit.Test;

public class TestTypeStoreTest {

    @Test(expected = IllegalArgumentException.class)
    public void createTestTypeNullv1() {
        TestTypeStore store = new TestTypeStore();

        store.createTestType(null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTestTypeNullv2() {
        TestTypeStore store = new TestTypeStore();
        store.createTestType("12345", null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTestTypeNullv3() {
        TestTypeStore test = new TestTypeStore();
        test.createTestType("12345", "something", null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createTestTypeNullv4() {
        TestTypeStore test = new TestTypeStore();
        test.createTestType("12345", "something", "something", null);
    }

    @Test
    public void createTestType() {
        ParameterCategory[] cat = new ParameterCategory[1];
        ParameterCategory category = new ParameterCategory("98765","example");
        cat[0] = category;
        TestTypeStore test = new TestTypeStore();
        test.createTestType("BL000", "something", "something", cat);
    }

    @Test
    public void CheckCreateTestTypeStore() {
        TestTypeStore testTypeList;

        testTypeList = new TestTypeStore();
    }

    @Test
    public void validateTestTypeTest3() {
        TestTypeStore store = new TestTypeStore();
        Assert.assertNotEquals(true, store.validateTestType(null));
    }

    @Test
    public void checkGetTestTypeList() {
        TestTypeStore store = new TestTypeStore();
        store.getTestTypeList();
    }

    @Test
    public void checkGetParameterCategory() {
        TestTypeStore testTypeStore = new TestTypeStore();

        ParameterCategory[] cat1 = new ParameterCategory[1];
        ParameterCategory cat2 = new ParameterCategory("66666", "cat");
        cat1[0] = cat2;

        TestType testType = new TestType("COV19", "example", "example", cat1);
        testTypeStore.saveTestType(testType);

        Assert.assertEquals(cat1, testTypeStore.getParameterCategory("COV19"));
    }

    @Test
    public void checkGetParameterCategoryWrong() {
        TestTypeStore testTypeStore = new TestTypeStore();

        ParameterCategory[] cat1 = new ParameterCategory[1];
        ParameterCategory cat2 = new ParameterCategory("66666", "cat");
        cat1[0] = cat2;

        TestType testType = new TestType("COV19", "example", "example", cat1);
        testTypeStore.saveTestType(testType);

        Assert.assertNotEquals(cat1, testTypeStore.getParameterCategory("66666"));
    }

    @Test
    public void saveInvalidTestType() {
        TestTypeStore testTypeStore = new TestTypeStore();

        ParameterCategory[] cat1 = new ParameterCategory[1];
        ParameterCategory cat2 = new ParameterCategory("66666", "cat");
        cat1[0] = cat2;

        TestType testType = new TestType("COV19", "example", "example", cat1);
        testTypeStore.saveTestType(testType);

        TestType testType1 = new TestType("COV19", "example", "example", cat1);
        Assert.assertFalse(testTypeStore.saveTestType(testType1));
    }

    @Test
    public void checkGetParameterCategoryEmpty() {
        TestTypeStore store = new TestTypeStore();
        ParameterCategory[] emptyArray = new ParameterCategory[0];

        Assert.assertEquals(emptyArray,store.getParameterCategory("00000"));
    }

    @Test
    public void checkSaveTestType() {
        ParameterCategory[] cat = new ParameterCategory[1];
        ParameterCategory category = new ParameterCategory("98765","example");
        cat[0] = category;
        TestTypeStore store = new TestTypeStore();
        TestType tt;
        tt = store.createTestType("BL000", "something", "something", cat);

        Assert.assertEquals(true,store.saveTestType(tt));
    }

}
