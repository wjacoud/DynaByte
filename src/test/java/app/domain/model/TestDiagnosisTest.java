package app.domain.model;

import app.domain.model.test.RefValue;
import app.domain.model.test.TestDiagnosis;
import app.domain.model.test.TestParameter;
import app.domain.model.test.TestParameterResult;
import app.domain.model.testComponents.Parameter;
import app.domain.model.testComponents.ParameterCategory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestDiagnosisTest {

    @Test
    public void createDiagnosisTest1(){
        ParameterCategory cat = new ParameterCategory("11111","name");
        Parameter par = new Parameter("ESR00", "paramBl1", "aaa", cat);
        TestParameterResult tpr = new TestParameterResult("ESR00",30.0D);
        TestParameter tp = new TestParameter("0001",par);
        RefValue rv = new RefValue("a",30.0D,30.0D);

        tpr.setRefValue(rv);
        tp.setTestParameterResult(tpr);
        List<TestParameter> tpl = new ArrayList<>();
        tpl.add(tp);
        TestDiagnosis td = new TestDiagnosis();

        Assert.assertNotNull(td.createTestDiagnosis(tpl));
    }

    @Test
    public void createDiagnosisTest2(){
        ParameterCategory cat = new ParameterCategory("11111","name");
        Parameter par = new Parameter("ESR00", "paramBl1", "aaa", cat);
        TestParameterResult tpr = new TestParameterResult("ESR00",30.0D);
        TestParameter tp = new TestParameter("0001",par);
        RefValue rv = new RefValue("a",25.0D,30.0D);

        tpr.setRefValue(rv);
        tp.setTestParameterResult(tpr);
        List<TestParameter> tpl = new ArrayList<>();
        tpl.add(tp);
        TestDiagnosis td = new TestDiagnosis();

        Assert.assertNotNull(td.createTestDiagnosis(tpl));
    }

    @Test
    public void createDiagnosisTest3(){
        ParameterCategory cat = new ParameterCategory("11111","name");
        Parameter par = new Parameter("ESR00", "paramBl1", "aaa", cat);
        TestParameterResult tpr = new TestParameterResult("ESR00",30.0D);
        TestParameter tp = new TestParameter("0001",par);
        RefValue rv = new RefValue("a",25.0D,35.0D);

        tpr.setRefValue(rv);
        tp.setTestParameterResult(tpr);
        List<TestParameter> tpl = new ArrayList<>();
        tpl.add(tp);
        TestDiagnosis td = new TestDiagnosis();

        Assert.assertNotNull(td.createTestDiagnosis(tpl));
    }

    @Test
    public void createDiagnosisTest4(){
        ParameterCategory cat = new ParameterCategory("11111","name");
        Parameter par = new Parameter("ESR00", "paramBl1", "aaa", cat);
        TestParameterResult tpr = new TestParameterResult("ESR00",30.0D);
        TestParameter tp = new TestParameter("0001",par);
        RefValue rv = new RefValue("a",31.0D,35.0D);

        tpr.setRefValue(rv);
        tp.setTestParameterResult(tpr);
        List<TestParameter> tpl = new ArrayList<>();
        tpl.add(tp);
        TestDiagnosis td = new TestDiagnosis();

        Assert.assertNotNull(td.createTestDiagnosis(tpl));
    }

    @Test
    public void createDiagnosisTest5(){
        ParameterCategory cat = new ParameterCategory("11111","name");
        Parameter par = new Parameter("ESR00", "paramBl1", "aaa", cat);
        TestParameterResult tpr = new TestParameterResult("ESR00",30.0D);
        TestParameter tp = new TestParameter("0001",par);
        RefValue rv = new RefValue("a",20.0D,25.0D);

        tpr.setRefValue(rv);
        tp.setTestParameterResult(tpr);
        List<TestParameter> tpl = new ArrayList<>();
        tpl.add(tp);
        TestDiagnosis td = new TestDiagnosis();

        Assert.assertNotNull(td.createTestDiagnosis(tpl));
    }

    @Test
    public void getDiagnosis(){
        TestDiagnosis td = new TestDiagnosis("aaa");

        Assert.assertNotNull(td.getDiagnosis());
    }

    @Test
    public void checkCreateTestDiagnosis() {
    }

}