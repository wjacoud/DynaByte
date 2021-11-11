package app.domain.model;

import app.domain.model.test.TestDiagnosis;
import app.domain.model.test.TestReport;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class TestReportTest {

    LocalDateTime now = LocalDateTime.now();

    @Test
    public void getIdTest() {
        TestDiagnosis td = new TestDiagnosis("aaa");
        TestReport tr = new TestReport("0001",td,"bbb", now);

        Assert.assertNotNull(tr.getIdTest());
    }

    @Test
    public void getDiagnosis() {
        TestDiagnosis td = new TestDiagnosis("aaa");
        TestReport tr = new TestReport("0001",td,"bbb", now);

        Assert.assertNotNull(tr.getDiagnosis());
    }

    @Test
    public void getReport() {
        TestDiagnosis td = new TestDiagnosis("aaa");
        TestReport tr = new TestReport("0001",td,"bbb", now);

        Assert.assertNotNull(tr.getReport());
    }

    @Test
    public void getNow() {
        TestDiagnosis td = new TestDiagnosis("aaa");
        TestReport tr = new TestReport("0001",td,"bbb", now);

        Assert.assertNotNull(tr.getDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidDiagnosis() {
        TestDiagnosis td = null;
        TestReport tr = new TestReport("0001",td,"bbb", now);

        Assert.assertNotNull(tr.getDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidReport() {
        TestDiagnosis td = new TestDiagnosis("aaa");
        TestReport tr = new TestReport("0001",td,"", now);
    }

    @Test(expected = IllegalArgumentException.class)
    public void CreateInvalidReport1() {
        TestDiagnosis td = new TestDiagnosis("aaa");
        TestReport tr = new TestReport("0001",td,"ujasdfrhyhusdbfuygbsdfyugbsdyhufgbyusdfbgyuisdbfyuigbsdfyuigbsdyhuifgbyhujsdfbgyhudsfbgyhudsbfyhiugbsduifhjyghbsyhujfdgboyhujsdfbgoyuihshbdofhujigbsdoujigbnsujidfbngiusdbfgu8isdbrhujigbsdfujigbsdfhiujgbnsdhjifgbnsidujfbngihujsdfbngujihsdbnfgiusdfniogbnsdiujgnsdifgnbsdfbgiosdfbgisdfbgiusdhboiugusdojihbngisuidogfbsaduioujasdfrhyhusdbfuygbsdfyugbsdyhufgbyusdfbgyuisdbfyuigbsdfyuigbsdyhuifgbyhujsdfbgyhudsfbgyhudsbfyhiugbsduifhjyghbsyhujfdgboyhujsdfbgoyuihshbdofhujigbsdoujigbnsujidfbngiusdbfgu8isdbrhujigbsdfujigbsdfhiujgbnsdhjifgbnsidujfbngihujsdfbngujihsdbnfgiusdfniogbnsdiujgnsdifgnbsdfbgiosdfbgisdfbgiusdhboiugusdojihbngisuidogfbsaduio", now);
    }

    @Test
    public void checkReportRulesMutation() {
        TestDiagnosis td = new TestDiagnosis("aaa");
        TestReport tr = new TestReport("0001",td,"djfbngihujsdfbngujihsdbgfgnbsdfbgiosdfbgisdfbgiusdhboiugusdojihbngisuidogfbsaduioujasdfrhyhusdbfuygbsdfyugbsdyhufgbyusdfbgyuisdbfyuigbsdfyuigbsdyhuifgbyhujsdfbgyhudsfbgyhudsbfyhiugbsduifhjyghbsyhujfdgboyhujsdfbgoyuihshbdofhujigbsdoujigbnsujidfbngiusdbfgu8isdbrhujigbsdfujigbsdfhiujgbnsdhjifgbnsidujfbngihujsdfbngujihsdbnfgiusdfniogbnsdiujgnsdifgnbsdfbgiosdfbgisdfbgiusdhboiugusdojihbngisuidogfbsaduio", now);

        Assert.assertNotNull(tr);
    }

}
