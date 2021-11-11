package app.domain.model;

import app.domain.model.stores.*;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CompanyTest {

    @Test(expected = IllegalArgumentException.class)
    public void checkCompanyWithEmptyDesigantion() {
        Company comp;
        String designation = "";
        comp = new Company(designation, 6, 0, 0);
    }

    @Test
    public void checkCompanyWithDesignation() {
        Company comp;
        String designation = "something";
        comp = new Company(designation, 6, 0, 0);
    }

    @Test
    public void checkCompany() {
        ClientsStore clientStore = new ClientsStore();
        ClinicalStore clinicalStore = new ClinicalStore();
        ParameterCategoryStore parameterCategoryStore = new ParameterCategoryStore();
        EmployeeStore employeeStore = new EmployeeStore();
        ParameterStore parameterStore = new ParameterStore();
        TestTypeStore testTypeStore = new TestTypeStore();
    }

    @Test
    public void checkGetDesignation() {
        //Arrange + Act
        String expected = "something";
        String actual;
        Company comp = new Company("something", 6, 0, 0);
        actual = comp.getDesignation();
        assertEquals(expected, actual);
    }

    @Test
    public void checkInvalidGetDesignation() {
        //Arrange + Act
        String expected = "somethinggg";
        String actual;
        Company comp = new Company("something", 6, 0, 0);
        actual = comp.getDesignation();
        assertNotEquals(expected, actual);
    }

    @Test
    public void checkGetClientStore() {
        ClientsStore clientStore;
        String designation = "something";
        Company comp = new Company(designation, 6, 0, 0);
        clientStore = comp.getClientStore();
    }

    @Test
    public void checkGetClinicalStore() {
        ClinicalStore clinicalStore;
        String designation = "something";
        Company comp = new Company(designation, 6, 0, 0);
        clinicalStore = comp.getClinicalStore();
    }

    @Test
    public void checkGetEmployeeStore() {
        EmployeeStore employeeStore;
        String designation = "something";
        Company comp = new Company(designation, 6, 0, 0);
        employeeStore = comp.getEmployeeStore();
    }

    @Test
    public void checkGetParameterCategory() {
        ParameterCategoryStore parameterCategoryStore;
        String designation = "something";
        Company comp = new Company(designation, 6, 0, 0);
        parameterCategoryStore = comp.getParameterCategoryStore();
    }

    @Test
    public void checkGetParameterStore() {
        ParameterStore parameterStore;
        String designation = "something";
        Company comp = new Company(designation, 6, 0, 0);
        parameterStore = comp.getParameterStore();
    }

    @Test
    public void checkGetTestTypeStore() {
        TestTypeStore testTypeStore;
        String designation = "something";
        Company comp = new Company(designation, 6, 0, 0);
        testTypeStore = comp.getTestTypeStore();
    }

    @Test
    public void checkGetSampleStore() {
        SampleStore sampleStore;
        String designation = "something";
        Company comp = new Company(designation, 6, 0, 0);
        sampleStore = comp.getSampleStore();
    }

    @Test
    public void checkGetPerformanceReportStore() {
        PerformanceReportStore performanceReportStore;
        String designation = "something";
        Company comp = new Company(designation, 6, 0, 0);
        performanceReportStore = comp.getPerformanceReportStore();
    }

    @Test
    public void checkGetData() {
        String designation = "something";
        Company comp = new Company(designation, 6, 0, 0);
        comp.getData();
    }

    @Test
    public void GetData() {
        String designation = "something";
        Company comp = new Company(designation, 6, 0, 0);
        Assert.assertNotNull(comp.getData());
    }

    @Test
    public void getClinicalStore() {
        String designation = "something";
        Company comp = new Company(designation, 6, 0, 0);
        Assert.assertNotNull(comp.getClinicalStore());
    }

    @Test
    public void getParameterStore() {
        String designation = "something";
        Company comp = new Company(designation, 6, 0, 0);
        Assert.assertNotNull(comp.getParameterStore());
    }

    @Test
    public void getTestStore() {
        String designation = "something";
        Company comp = new Company(designation, 6, 0, 0);
        Assert.assertNotNull(comp.getTestStore());
    }

    @Test
    public void getPerformanceReportStore() {
        String designation = "something";
        Company comp = new Company(designation, 6, 0, 0);
        Assert.assertNotNull(comp.getPerformanceReportStore());
    }
}