package app.domain.model;

import app.domain.model.stores.ParameterCategoryStore;
import app.domain.model.stores.ParameterStore;
import app.domain.model.testComponents.Parameter;
import app.domain.model.testComponents.ParameterCategory;
import org.junit.Assert;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

public class ParameterStoreTest {

    @Test(expected = IllegalArgumentException.class)
    public void createParameterNullv1() {
        ParameterStore test = new ParameterStore();

        test.createParameter(null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterNullv2() {
        ParameterStore test = new ParameterStore();
        test.createParameter("12345", null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterNullv3() {
        ParameterStore test = new ParameterStore();
        test.createParameter("12345", "name", null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterNullv4() {
        ParameterStore test = new ParameterStore();
        test.createParameter("12345", "name", "description", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterAC1v1() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        test.createParameter("123456", "name", "description", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterAC1v2() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        test.createParameter("1", "name", "description", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterAC2() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        test.createParameter("12345", "very big name", "description", cat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createParameterAC3() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        test.createParameter("12345", "name", "description with more than twenty chars", cat);
    }

    @Test
    public void createParameterTest1() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        Parameter par = new Parameter("12345", "name", "description", cat);
        ParameterStore test = new ParameterStore();
        Assert.assertEquals(par.getCode(), test.createParameter("12345", "name", "description", cat).getCode());
        Assert.assertEquals(par.getName(), test.createParameter("12345", "name", "description", cat).getName());
        Assert.assertEquals(par.getDescription(), test.createParameter("12345", "name", "description", cat).getDescription());
        Assert.assertEquals(par.getCat(), test.createParameter("12345", "name", "description", cat).getCat());
    }

    @Test
    public void createParameterTest2() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        Assert.assertNotNull(test.createParameter("12345", "name", "description", cat));
    }

    @Test
    public void createParameterTest3() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        Assert.assertNotNull(test.createParameter("12345", "heyeight", "description", cat));
    }

    @Test
    public void createParameterTest4() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        Assert.assertNotNull(test.createParameter("12345", "heyeih", "heydescriptiontwenty", cat));
    }

    @Test
    public void createParameterTest5() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        Assert.assertNotNull(test.createParameter("12345", "heyeih", "heydescriptiontwenty", cat).getName());
    }

    @Test
    public void createParameterTest6() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        Assert.assertNotNull(test.createParameter("12345", "heyeih", "heydescriptiontwenty", cat).getDescription());
    }

    @Test
    public void validateParameterNull() {
        ParameterStore test = new ParameterStore();
        Assert.assertFalse(test.validateParameter(null));
    }

    @Test
    public void validateParameterTest1() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        Parameter par = test.createParameter("13245", "name", "description", cat);

        Assert.assertTrue(test.validateParameter(par));
    }

    @Test
    public void validateParameterTest2() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        Parameter par = test.createParameter("12345", "name", "description", cat);

        test.saveParameter();

        Assert.assertFalse(test.validateParameter(par));
    }

    @Test
    public void validateParameterTest3() {
        ParameterStore test = new ParameterStore();
        Assert.assertNotEquals(true, test.validateParameter(null));
    }

    @Test
    public void validateParameterTest4() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        Parameter pc = test.createParameter("12345", "name", "description", cat);


        Assert.assertNotNull(pc);
    }

    @Test
    public void validateParameterTest5() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        Parameter par = test.createParameter("12345", "name", "description", cat);

        test.saveParameter();

        Assert.assertNotEquals(par.getCode(), test.createParameter("13254", "name2", "description", cat).getCode());
    }

    @Test
    public void validateParameterTest6() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        Parameter par = test.createParameter("12345", "name", "description", cat);

        test.saveParameter();

        Assert.assertNotEquals(true, test.validateParameter(par));
    }

    @Test
    public void validateParameterTest7() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        Parameter par = test.createParameter("32145", "name", "description", cat);

        Assert.assertNotEquals(false, test.validateParameter(par));
    }


    @Test
    public void saveParameteryNull() {
        ParameterStore test = new ParameterStore();
        Assert.assertFalse(test.saveParameter());
    }

    @Test
    public void saveParameterTest1() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        Parameter par = test.createParameter("78954", "name", "description", cat);

        Assert.assertTrue(test.saveParameter());
    }

    @Test
    public void saveParameterTest2() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        test.createParameter("12345", "name", "description", cat);

        test.saveParameter();
        test.createParameter("12345", "name", "description", cat);

        Assert.assertFalse(test.saveParameter());
    }

    @Test
    public void saveParameterTest3() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        Parameter par = test.createParameter("12345", "name", "description", cat);

        test.saveParameter();


        Assert.assertNotEquals(true, test.validateParameter(par));
    }

    @Test
    public void saveParameterTest4() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        Parameter par = test.createParameter("12345", "name", "description", cat);

        test.saveParameter();


        Assert.assertNotEquals(true, test.saveParameter());
    }

    @Test
    public void saveParameterTest5() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        test.createParameter("65478", "name", "description", cat);

        Assert.assertNotEquals(false, test.saveParameter());
    }

    @Test
    public void getPrTest1() {
        ParameterCategory pc = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        Parameter par = new Parameter("12345", "name", "description", pc);

        test.createParameter("12345", "name", "description", pc);

        Assert.assertEquals(par.getCode(), test.getPr().getCode());
        Assert.assertEquals(par.getName(), test.getPr().getName());
        Assert.assertEquals(par.getDescription(), test.getPr().getDescription());
        Assert.assertEquals(par.getCat(), test.getPr().getCat());
    }

    @Test
    public void getPrTest2() {
        ParameterCategory cat = new ParameterCategory("12345", "name");
        ParameterStore test = new ParameterStore();
        test.createParameter("12345", "name", "description", cat);

        Assert.assertNotNull(test.getPr());
    }

    @Test
    public void testToStringTest2() {
        ParameterStore test = new ParameterStore();
        ParameterCategory cat = new ParameterCategory("12345", "name");
        test.createParameter("12345", "name", "description", cat);

        test.saveParameter();

        Assert.assertNotNull(test.toString());
    }

    @Test
    public void getPcListTest2() {
        ParameterStore test = new ParameterStore();
        ParameterCategoryStore store = new ParameterCategoryStore();
        store.saveParameterCategory(store.createParameterCategory("12345", "name"));

        Assert.assertNotNull(test.getParameterCategory(store));
    }

    @Test
    public void getParameterList() {
        ParameterStore test = new ParameterStore();
        ParameterCategory store = new ParameterCategory("12345", "name");
        test.createParameter("11111", "name", "description", store);
        test.saveParameter();

        Assert.assertNotNull(test.getParameterList());
    }

    @Test
    public void getParameterWithCode() {
        ParameterStore test = new ParameterStore();
        ParameterCategory store = new ParameterCategory("12345", "name");
        Parameter parameter = test.createParameter("11111", "name", "description", store);
        test.saveParameter();

       Assert.assertEquals(parameter,test.getParameterWithCode("11111"));
    }
    @Test
    public void getInvalidParameterWithCode() {
        ParameterStore test = new ParameterStore();
        ParameterCategory store = new ParameterCategory("12345", "name");
        Parameter parameter = test.createParameter("11111", "name", "description", store);
        test.saveParameter();

        Assert.assertEquals(null,test.getParameterWithCode("11161"));
    }
}