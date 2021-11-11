package app.domain.model;

import app.domain.model.stores.ParameterCategoryStore;
import app.domain.model.testComponents.ParameterCategory;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterCategoryStoreTest {

    @Test(expected = IllegalArgumentException.class)
    public void createParameterCategoryNull() {
        ParameterCategoryStore test = new ParameterCategoryStore();

        test.createParameterCategory(null,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterCategoryNullV2() {
        ParameterCategoryStore test = new ParameterCategoryStore();

        test.createParameterCategory("12345",null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterCategoryAC1v1() {
        ParameterCategoryStore test = new ParameterCategoryStore();

        test.createParameterCategory("1","name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterCategoryAC1v2() {
        ParameterCategoryStore test = new ParameterCategoryStore();

        test.createParameterCategory("123456","name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterCategoryAC2() {
        ParameterCategoryStore test = new ParameterCategoryStore();

        test.createParameterCategory("12345","name with more than 10 chars");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterCategoryAC1andAC2v1() {
        ParameterCategoryStore test = new ParameterCategoryStore();

        test.createParameterCategory("1","name with more than 10 chars");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterCategoryAC1andAC2v2() {
        ParameterCategoryStore test = new ParameterCategoryStore();

        test.createParameterCategory("123456","name with more than 10 chars");
    }

    @Test
    public void createParameterCategoryTest1() {
        ParameterCategoryStore test = new ParameterCategoryStore();
        ParameterCategory cat = new ParameterCategory("12345","name");


        assertEquals(cat.getCode(),test.createParameterCategory("12345","name").getCode());
        assertEquals(cat.getName(),test.createParameterCategory("12345","name").getName());
    }

    @Test
    public void createParameterCategoryTest2() {
        ParameterCategoryStore test = new ParameterCategoryStore();

        assertNotNull(test.createParameterCategory("12345","name"));
    }

    @Test
    public void createParameterCategoryTest3() {
        ParameterCategoryStore test = new ParameterCategoryStore();

        assertNotNull(test.createParameterCategory("12345","characters"));
    }

    @Test
    public void createParameterCategoryTest4() {
        ParameterCategoryStore test = new ParameterCategoryStore();

        assertNotNull(test.createParameterCategory("12345","name").getName());
    }

    @Test
    public void validateParameterCategoryNull() {
        ParameterCategoryStore test = new ParameterCategoryStore();

        assertFalse(test.validateParameterCategory(null));
    }

    @Test
    public void validateParameterCategoryTest1() {
        ParameterCategoryStore test = new ParameterCategoryStore();
        ParameterCategory cat = test.createParameterCategory("21354","name");

        assertTrue(test.validateParameterCategory(cat));
    }

    @Test
    public void validateParameterCategoryTest2() {
        ParameterCategoryStore test = new ParameterCategoryStore();
        ParameterCategory cat = test.createParameterCategory("12345","name");

        test.saveParameterCategory(cat);

        assertFalse(test.validateParameterCategory(cat));
    }

    @Test
    public void validateParameterCategoryTest3() {
        ParameterCategoryStore test = new ParameterCategoryStore();

        assertNotEquals(true, test.validateParameterCategory(null));
    }

    @Test
    public void validateParameterCategoryTest4() {
        ParameterCategoryStore test = new ParameterCategoryStore();
        ParameterCategory pc = test.createParameterCategory("12345", "name");


        assertNotNull(pc);
    }

    @Test
    public void validateParameterCategoryTest5() {
        ParameterCategoryStore test = new ParameterCategoryStore();
        ParameterCategory cat = test.createParameterCategory("12345", "name");

        test.saveParameterCategory(cat);

        assertNotEquals(cat.getCode(),test.createParameterCategory("13254","name2").getCode());
    }

    @Test
    public void validateParameterCategoryTest6() {
        ParameterCategoryStore test = new ParameterCategoryStore();
        ParameterCategory cat = test.createParameterCategory("12345","name");

        test.saveParameterCategory(cat);

        assertNotEquals(true,test.validateParameterCategory(cat));
    }

    @Test
    public void validateParameterCategoryTest7() {
        ParameterCategoryStore test = new ParameterCategoryStore();
        ParameterCategory cat = test.createParameterCategory("54798","name");

        assertNotEquals(false,test.validateParameterCategory(cat));
    }



    @Test
    public void saveParameterCategoryNull() {
        ParameterCategoryStore test = new ParameterCategoryStore();

        assertFalse(test.saveParameterCategory(null));
    }

    @Test
    public void saveParameterCategoryTest1() {
        ParameterCategoryStore test = new ParameterCategoryStore();
        ParameterCategory cat = test.createParameterCategory("25896","name");

        assertTrue(test.saveParameterCategory(cat));
    }

    @Test
    public void saveParameterCategoryTest2() {
        ParameterCategoryStore test = new ParameterCategoryStore();
        ParameterCategory cat = test.createParameterCategory("12345","name");
        test.saveParameterCategory(cat);


        assertFalse(test.saveParameterCategory(cat));
    }

    @Test
    public void saveParameterCategoryTest3() {
        ParameterCategoryStore test = new ParameterCategoryStore();
        ParameterCategory cat = test.createParameterCategory("12345","name");
        test.saveParameterCategory(cat);


        assertNotEquals(true,test.validateParameterCategory(cat));
    }

    @Test
    public void saveParameterCategoryTest4() {
        ParameterCategoryStore test = new ParameterCategoryStore();
        ParameterCategory cat = test.createParameterCategory("12345","name");
        test.saveParameterCategory(cat);


        assertNotEquals(true,test.saveParameterCategory(cat));
    }

    @Test
    public void saveParameterCategoryTest5() {
        ParameterCategoryStore test = new ParameterCategoryStore();
        ParameterCategory cat = test.createParameterCategory("65478","name");

        assertNotEquals(false,test.saveParameterCategory(cat));
    }



    @Test
    public void getPcTest1() {
        ParameterCategoryStore test = new ParameterCategoryStore();
        ParameterCategory pc = new ParameterCategory("12345","name");

        test.createParameterCategory("12345","name");

        assertEquals(pc.getCode(), test.getPc().getCode());
        assertEquals(pc.getName(), test.getPc().getName());
    }

    @Test
    public void getPcTest2() {
        ParameterCategoryStore test = new ParameterCategoryStore();
        test.createParameterCategory("12345","name");

        assertNotNull(test.getPc());
    }

    @Test
    public void testToStringTest2() {
        ParameterCategoryStore test = new ParameterCategoryStore();

        test.saveParameterCategory(test.createParameterCategory("12345","name"));

        assertNotNull(test.toString());
    }


}
