package app.domain.model;

import app.controller.ReportTaskController;
import app.domain.model.stores.*;
import auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>, Wilson Junior 1200630, José Cruz 1201401, Ricardo Faria, 1201405, Rodrigo Oliveira 1201406, Gonçalo Pereira 1201500
 */
public class Company implements java.io.Serializable {
    private final String designation;
    private final AuthFacade authFacade;

    private ClientsStore clientStore;
    private ClinicalStore clinicalStore;
    private EmployeeStore employeeStore;
    private ParameterCategoryStore parameterCategoryStore;
    private ParameterStore parameterStore;
    private TestTypeStore testTypeStore;
    private TestStore testStore;
    private SampleStore sampleStore;
    private PerformanceReportStore performanceReportStore;

    private Data data;

    /**
     * creates all stores when the program is started for the first time
     *
     * @param designation designation
     */
    public Company(String designation, int hour, int min, int sec) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();

        clientStore = new ClientsStore();
        clinicalStore = new ClinicalStore();
        parameterCategoryStore = new ParameterCategoryStore();
        employeeStore = new EmployeeStore();
        parameterStore = new ParameterStore();
        testTypeStore = new TestTypeStore();
        testStore = new TestStore();
        sampleStore = new SampleStore();
        performanceReportStore = new PerformanceReportStore();

        this.data = new Data();

        //scheduleReport(hour, min, sec);
    }

    private void scheduleReport(int hour, int min, int sec) {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, hour);
        today.set(Calendar.MINUTE, min);
        today.set(Calendar.SECOND, sec);
        Timer timer = new Timer();
        timer.schedule(new ReportTaskController(), today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }

    public Data getData() {
        return data;
    }

    /**
     * return the designation
     *
     * @return designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * return the auth facade
     *
     * @return auth facade
     */
    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    /**
     * return the client store
     *
     * @return client store
     */
    public ClientsStore getClientStore() {
        return clientStore;
    }

    /**
     * return the clinical store
     *
     * @return clinical store
     */
    public ClinicalStore getClinicalStore() {
        return clinicalStore;
    }

    /**
     * return the employee store
     *
     * @return employee store
     */
    public EmployeeStore getEmployeeStore() {
        return employeeStore;
    }

    /**
     * return the parameter category store
     *
     * @return parameter category store
     */
    public ParameterCategoryStore getParameterCategoryStore() {
        return parameterCategoryStore;
    }

    /**
     * return the parameter store
     *
     * @return parameter store
     */
    public ParameterStore getParameterStore() {
        return parameterStore;
    }

    /**
     * return the test type store
     *
     * @return test type store
     */
    public TestTypeStore getTestTypeStore() {
        return testTypeStore;
    }

    /**
     * return the test store
     *
     * @return test store
     */
    public TestStore getTestStore() {
        return testStore;
    }

    /**
     * return the sample store
     *
     * @return sample store
     */
    public SampleStore getSampleStore() {
        return sampleStore;
    }

    /**
     * return the PerformanceReport Store
     *
     * @return PerformanceReport Store
     */
    public PerformanceReportStore getPerformanceReportStore() {
        return performanceReportStore;
    }
}