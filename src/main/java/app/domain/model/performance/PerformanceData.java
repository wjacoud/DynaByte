package app.domain.model.performance;

import java.time.LocalDateTime;

/**
 * class responsible for the Performance Data
 *
 * @author Wilson Junior 1200630
 */
public class PerformanceData implements java.io.Serializable {
    private final LocalDateTime dateTime;
    private final int newTests;
    private final int newResults;
    private final int newDiagnostics;
    private final int newValidations;

    /**
     * Constructor that create a full Performance Data object
     *
     * @param dateTime          - date time
     * @param newTests          - number of new tests
     * @param newResults        - number of new results
     * @param newDiagnostics    - number of new diagnostics
     * @param newValidations    - number of new validations
     */
    public PerformanceData(LocalDateTime dateTime, int newTests, int newResults, int newDiagnostics, int newValidations) {
        this.dateTime = dateTime;
        this.newTests = newTests;
        this.newResults = newResults;
        this.newDiagnostics = newDiagnostics;
        this.newValidations = newValidations;
    }

    /**
     * Method to get the date time of Performance data
     *
     * @return date time
     */
    public LocalDateTime getDateTime() { return dateTime; }

    /**
     * Method to get the number of new tests
     *
     * @return number of new tests
     */
    public int getNewTests() { return newTests; }

    /**
     * Method to get the number of new results
     *
     * @return number of new results
     */
    public int getNewResults() { return newResults; }

    /**
     * Method to get the number of new diagnostics
     *
     * @return number of new diagnostics
     */
    public int getNewDiagnostics() { return newDiagnostics; }

    /**
     * Method to get the number of new validations
     *
     * @return number of new validations
     */
    public int getNewValidations() { return newValidations; }
}

