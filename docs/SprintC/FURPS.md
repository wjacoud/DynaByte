# Supplementary Specification (FURPS+)

## Functionality


- Localisation:
    - "The application must support the English language only."
- Persistence:
    - "The application should use object serialization to ensure data persistence between two runs of the application."
- Security:
    - "All those who wish to use the application must be authenticated"
    - "password holding seven alphanumeric characters, including three capital letters and two digits."
    - "Only the specialist doctor is allowed to access all client data"
- Reporting:
    - "The client receives the notification by SMS and e-mail."
    - "The company is also required to generate daily (automatic) reports."

## Usability

- Accessibility:
    - "The client receives the notification by SMS and e-mail."
    - "To facilitate the access to the results, the application must allow ordering the clients by TIF and by
      name."
    - "Only the specialist doctor is allowed to access all client data."
- Aesthetics:
    - "All the images/figures produced during the software development process should be recorded in SVG format."
    - "The user interface must be simple, intuitive and consistent."

## Reliability

- Accuracy:
    - "for any interval of time ...  a list with 144 integers is obtained ... positive integer ... half an hour more tests were processed than results were obtained ... negative integer means the opposite ...  determining what the contiguous subsequence of the initial sequence is ... This will show ... when the company was less effective in responding. So, the application should implement a brute-force algorithm"
    - "To make the predictions, the NHS contract defines that a linear regression algorithm should be used. "
    - "The accuracy of the prediction models should be analysed and
      documented in the application user manual (in the annexes) that must be delivered with the
      application."

## Performance

- Response Time:
    - "The complexity analysis must be accompanied by the observation of the execution time of the
      algorithms"
    - "It is intended that the choice of the ordering algorithm is based on the algorithm complexity
       (mainly the execution time)."
    - "Any interface between a user and the system shall have a maximum response time of 3 seconds."
- Shutdown time
    - "The system should not fail more than 5 days in one year."
- Start-up time
    - "The system should start up in less than 10 seconds."
- Throughput
    - "The implemented algorithm should be analysed in terms of its worst-case time complexity, and it should be compared to a provided benchmark algorithm."
    - "the difference between the number of new tests and the number of results available to the client during each half an hour period is computed."


## Supportability

- Adaptability:
    -  "the system should be developed having in mind the need to easily support other kinds of tests"
- Configurability:
    - "The algorithm to be used by the application must be defined through a configuration
      file."
- Testability:
    - "The development team must implement unit tests for all methods except methods that implement Input/Output operations."
    - "The JaCoCo plugin should be used to generate the coverage report."
- Compability:
    - "The application should run on all platforms for which there exists a Java Virtual Machine."

### Design Constraints


### Implementation Constraints

- Implementation languages:
    - "The application must be developed in Java language using the IntelliJ IDE or Netbeans."
    - "The application graphical interface is to be developed in JavaFX 11."
    - "adopt best practices for identifying requirements and for OO software analysis and design"
    - "adopt recognized coding standards"
    - "use Javadoc to generate useful documentation for Java code."
- Plataform support:
    - "The application should run on all platforms for which there exists a Java Virtual Machine."
- Resource limits:
    - "The application will be deployed to a machine with 8GB of RAM."
  
### Interface Constraints

- External Systems:
    - "identifying each sample with a barcode that is automatically generated using an external API."
    - "the application uses an external module that is responsible for doing an automatic validation"
    - "The company is also required to generate daily (automatic) reports
      with all the information demanded by the NHS and should send them to the NHS using their API."


### Physical Constraints