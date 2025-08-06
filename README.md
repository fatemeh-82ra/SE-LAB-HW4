
### **Feature 1: Project Setup & BDD Configuration**
*(Overall Priority: P0, Overall Estimate: 4)*

* **Task:** Create a new Maven project in IntelliJ. *(Priority: P0, Estimate: 1)*
* **Task:** Install the "Cucumber for Java" and "Gherkin" IntelliJ plugins. *(Priority: P0, Estimate: 1)*
* **Task:** Add the `junit` and `io.cucumber` dependencies to the `pom.xml` file. *(Priority: P0, Estimate: 1)*
* **Task:** Create the initial project folder structure (`src/main/java`, `src/test/java`, and `src/test/resources/features`). *(Priority: P1, Estimate: 1)*

---
### **Feature 2: Define Calculator Behaviors (Gherkin)**
*(Overall Priority: P0, Overall Estimate: 5)*

* **Task:** Create a new file named `calculator.feature` inside the `features` folder. *(Priority: P0, Estimate: 1)*
* **Task:** Write a `Scenario Outline` to test the four valid mathematical operations (+, -, \*, /) using an `Examples` table with various test cases. *(Priority: P0, Estimate: 3)*
* **Task:** Add a separate `Scenario` to the same file to specifically test the edge case of division by zero. *(Priority: P1, Estimate: 1)*

---
### **Feature 3: Implement Step Definitions (The "Glue" Code)**
*(Overall Priority: P1, Overall Estimate: 8)*

* **Task:** Create a `CalculatorStepDefs.java` class for the step definitions. *(Priority: P0, Estimate: 1)*
* **Task:** Implement the `@Given` step to initialize the calculator with input numbers. *(Priority: P1, Estimate: 2)*
* **Task:** Implement the `@When` step to perform the calculation based on the operator from the scenario. *(Priority: P1, Estimate: 2)*
* **Task:** Implement the `@Then` step to assert that the actual result matches the expected result. *(Priority: P1, Estimate: 2)*
* **Task:** Implement a separate `@Then` step to verify that an exception is correctly thrown for the division-by-zero scenario. *(Priority: P1, Estimate: 1)*

---
### **Feature 4: Implement the Calculator Business Logic**
*(Overall Priority: P1, Overall Estimate: 5)*

* **Task:** Create the `Calculator.java` class. *(Priority: P0, Estimate: 1)*
* **Task:** Implement the core logic in the `Calculator` class to perform addition, subtraction, multiplication, and division. *(Priority: P1, Estimate: 3)*
* **Task:** Ensure the calculation logic throws an `IllegalArgumentException` when a division by zero is attempted. *(Priority: P1, Estimate: 1)*

---
### **Feature 5: Finalize and Document for Submission**
*(Overall Priority: P2, Overall Estimate: 5)*

* **Task:** Create a `RunnerTest.java` class to run all Cucumber scenarios with JUnit. *(Priority: P1, Estimate: 1)*
* **Task:** Run all tests a final time to ensure the entire feature set passes. *(Priority: P1, Estimate: 1)*
* **Task:** Update the project board to reflect the division of tasks and who completed them. *(Priority: P2, Estimate: 1)*
* **Task:** Create or update the `README.md` file to explain the project and how to run the tests. *(Priority: P2, Estimate: 2)*
