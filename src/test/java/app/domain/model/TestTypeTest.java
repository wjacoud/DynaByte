package app.domain.model;

import app.domain.model.stores.TestTypeStore;
import app.domain.model.testComponents.ParameterCategory;
import app.domain.model.testComponents.TestType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestTypeTest {

    //Test Create With Empty Code
    @Test(expected = IllegalArgumentException.class)
    public void CreateWithEmptyCode() {
        System.out.println("Test Create With Empty Code");
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("12345", "example");
        categories[0] = cat;
        TestType testType = new TestType("", "Blood", "Shot", categories);
    }

    //Test Create With Empty Description
    @Test(expected = IllegalArgumentException.class)
    public void CreateWithEmptyDescription() {
        System.out.println("Test Create With Empty Description");
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("12345", "example");
        categories[0] = cat;
        TestType testType = new TestType("12345", "", "Shot", categories);
    }

    //Test Create With Empty Collecting Methods
    @Test(expected = IllegalArgumentException.class)
    public void CreateWithEmptyCollectingMethods() {
        System.out.println("Test Create With Empty Collecting Methods");
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("12345", "example");
        categories[0] = cat;
        TestType testType = new TestType("12345", "Blood", "", categories);
    }

    //Test Create With Code Overflow
    @Test(expected = IllegalArgumentException.class)
    public void CreateWithCodeOverflow() {
        System.out.println("Test Create With Code Overflow");
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("12345", "example");
        categories[0] = cat;
        TestType testType = new TestType("12365498", "Blood", "Shot", categories);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkTestTypeDescriptionRulesMaxLength() {
        System.out.println("Test Create With Code Overflow");
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("12345", "example");
        categories[0] = cat;
        TestType testType = new TestType("12365498", "qwerqqwewerqwer", "Shot", categories);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkTestTypeCollectingMethodsRulesMaxLength() {
        System.out.println("Test Create With Code Overflow");
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("12345", "example");
        categories[0] = cat;
        TestType testType = new TestType("12365498", "qwerqerqwer", "qwertyuisdasdfffsdfa", categories);
    }

    //Test Create With Description Overflow
    @Test(expected = IllegalArgumentException.class)
    public void CreateWithDescriptionOverflow() {
        System.out.println("Test Create With Description Overflow");
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("12345", "example");
        categories[0] = cat;

        TestType testType = new TestType("12345", "askdjuh asdkjhsajdho9i", "Shot", categories);
    }

    //Test Create With Collecting Methods Overflow
    @Test(expected = IllegalArgumentException.class)
    public void CreateWithCollectingMethodsOverflow() {
        System.out.println("Test Create With Collecting Methods Overflow");
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("12345", "example");
        categories[0] = cat;

        TestType testType = new TestType("12345", "Blood", "aasdjhg dsakiuha8dysdiuhsdkjh", categories);
    }

    //Test Create With Empty Code
    @Test(expected = IllegalArgumentException.class)
    public void CreateCodeWithNo5Chars() {
        System.out.println("Test Create With Empty Code");
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("12345", "example");
        categories[0] = cat;
        TestType testType = new TestType("1234", "Blood", "Shot", categories);
    }

    @Test
    public void GetDescription() {
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("12345", "example");
        categories[0] = cat;
        TestType testType = new TestType("BL000", "Blood", "Shot", categories);

        assertEquals("Blood", testType.getDescription());
    }

    @Test
    public void GetCollectingMethod() {
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("12345", "example");
        categories[0] = cat;
        TestType testType = new TestType("BL000", "Blood", "Shot", categories);

        assertEquals("Shot", testType.getCollectingMethods());
    }

    @Test
    public void GetExternalModule() {
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("12345", "example");
        categories[0] = cat;
        TestType testType = new TestType("BL000", "Blood", "Shot", categories);
        assertNotNull(testType.getExternalModule());
    }

    @Test(expected = IllegalArgumentException.class)
    public void CodeInvalid() {
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("12345", "example");
        categories[0] = cat;
        TestType testType = new TestType("12345", "Blood", "Shot", categories);

    }

    @Test
    public void checkCreateTestType() {
        TestTypeStore store = new TestTypeStore();
        ParameterCategory[] categories = new ParameterCategory[1];
        ParameterCategory cat = new ParameterCategory("12345", "example");
        categories[0] = cat;
        TestType testType = store.createTestType("BL000", "Blood", "Shot", categories);
    }
}