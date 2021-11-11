# OO Analysis #

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used.

## Rationale to identify domain conceptual classes ##

### _Conceptual Class Category List_ ###

**Business Transactions**

* Tests

---

**Transaction Line Items**

* Samples

---

**Product/Service related to a Transaction or Transaction Line Item**

* Blood Analysis
* Covid-19 Tests

---

**Transaction Records**

* Test Register to ChemistryTechnologist
* Tests Results to Specialist Doctor
* Diagnosis and Tests Results to Laboratory Coordinator
* Diagnosis and Tests Results to client
* Covid-19 Reports to NHS
* Diary Reports to NHS

---  

**Roles of People or Organizations**

* Client
* Administrator
* Receptionist
* Medical Lab Technician
* Clinical Chemistry Technologist
* Specialist Doctor
* Laboratory Coordinator

---

**Places**

* Company's Headquarters
* Chemical Laboratory
* Clinical Analysis Laboratory

---

**Noteworthy Events**

* Chemical Analysis
* Client Register

---

**Physical Objects**

* Needle
* Swab

---

**Descriptions of Things**

* Type of Test
* Parameters of Test
* Category of Test
* Sample Identification

---

**Organizations**

* Many Labs
* NHS

---

**Other External/Collaborating Systems**

* NHS API
* Module Automatic Validation
* Barcode External API

---

**Records of finance, work, contracts, legal matters**

* Test Results
* Diagnosis
* CovidReport
* NHSReport
* Covid Tests Exclusivity

---

**Documents mentioned/used to perform some work/**

* Lab Order
* Test Register
* Client Notification
* User Manual

---



###**Rationale to identify associations between conceptual classes**###


| Concept (A)         |  Association       |  Concept (B) |
|----------           |:-------------:     |------:       |
|Administrator|create|Parameters_Category|
|Administrator|create|Parameters|
|Administrator|create|TestType|
|Administrator|create|ClinicalAnalysisLaboratory|
|Administrator|is/create|Employee|
|AutomaticValidation | is used to | TestDiagnosis |
|BarcodeAPI | is used to | SampleIdentification |
|ChemicalLab | gives sample | ChemistryTechnologist |
|ChemicalLab | gives test results and test report | LaboratoryCoordinator |
|ChemicalLab | gives test results | SpecialistDoctor |
|ChemistryTechnologist | analysis sample | SampleAnalysis |
|ChemistryTechnologist | is | Employee |
|Client | is called by | MedicalLabTechnicians |
|Client | asks for | Test |
|ClinicalAnalysisLaboratory| sends samples daily | ChemicalLab |
|ClinicalAnalysisLaboratory| capable of analysing | Parameters | 
|ClinicalAnalysisLaboratory| performs | Test |
|ClinicalAnalysisLaboratory| conducts | TestType |
|ClinicalAnalysisLaboratory| adopts | Parameters_Category |
|Company | owns | ChemicalLab |
|Company |owns | ClinicalAnalysisLaboratory |
|Company |reports |Report |
|Company | has | Employee |
|Employee | uses | Platform |
|LaboratoryCoordinator | does | TestValidation 
|LaboratoryCoordinator | is | Employee |
|MedicalLabTechnicians | collect | Sample |
|MedicalLabTechnicians | is | Employee |
|NHSAPI | is used to |Report|
|Notification | is received by | Client |
|Parameters | presented under | Parameters_Category |
|Receptionist|register|Client|
|Receptionist|is|Employee|
|Sample | identifies sample | SampleIdentification|
|SampleAnalysis | sends analysis | TestResults |
|SampleIdentification | sends sample | ClinicalAnalysisLaboratory |
|SpecialistDoctor | create | TestDiagnosis |
|SpecialistDoctor | is | Employee |
|Test | registered by | Receptionist |
|Test | request analysis for | Test_Parameters |
|Test | is of | TestType |
|Test_Parameters | request analysis for | Parameters | 
|TestDiagnosis | information | TestReport | 
|TestValidation | create |Notification |



## Domain Model

![DM.svg](DM.svg)



