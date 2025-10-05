Here is a detailed README file content and an architecture diagram description for the NBA-Automation multi-module Maven framework.

NBA Automation Framework
Overview
NBA Automation is a modular, scalable, maintainable automation framework designed for automating multiple NBA web applications using Selenium WebDriver, Cucumber BDD, and TestNG. The framework follows a multi-module Maven project architecture to separate core reusable framework code and individual product test suites. This enables clean code reuse, independent test development, and easier maintenance.
The framework automates test cases for multiple NBA team websites like:
(https://www.nba.com/warriors)
(https://www.nba.com/sixers)
(https://www.nba.com/bulls)
Each team website is represented as an independent Maven test module, while the reusable automation code is centralized in a common framework module.

Project Structure
The root project is nba-automation, consisting of 4 Maven modules:
1. automation-framework (Core Framework Module)
Contains reusable core framework components and utility classes shared across all product test modules.
src/main/java: Contains base classes and utilities:
BaseTest.java: WebDriver initialization & teardown logic supporting multiple browsers.
BasePage.java: PageFactory initialization and page load synchronization methods.
ConfigReader.java: Reads project-wide configurable properties from config.properties.
CucumberReportGeneratorInvoker.java: Generates consolidated Cucumber reports after test execution.
FileUtils.java: File read/write utilities.
Hooks.java: Cucumber hooks for setup and teardown.
WebDriverManager.java: Manages driver lifecycle.
src/main/resources: config.properties file with project-wide parameters (URLs, file paths, driver executable names, etc.).
src/test/resources: WebDriver executables (e.g.,,geckodriver.exe).
pom.xml: Contains dependencies for Selenium, Cucumber, TestNG, JSON parsing, reporting plugins, etc.

2. core-test-products (Core Product Module)
Automates core NBA product web pages, e.g., https://www.nba.com/warriors
src/main/java: Page Object classes defining locators and page methods.
src/test/java:
com.nba.automation.coreproduct.runner.CPTestRunner.java:Cucumber runner configured with TestNG. Reads browser parameter from testng.xml and calls browser setup/teardown from runner.
src/test/resources: Contains Cucumber feature files and testng.xml.
pom.xml: Declares dependency on automation-framework and configures maven-surefire-plugin to run tests per testng.xml.

3. derived-product1-tests (Derived Product 1 Module)
Automates derived NBA product at https://www.nba.com/sixers/
src/main/java: Page Object classes for derived product 1 pages.
src/test/java:
com.nba.automation.DP1Runner.runner.DP1TestRunner.java:Cucumber runner with browser setup/teardown.
com.nba.automation.DP1.testdata: Utility classes to read JSON test data for slides.
src/test/resources: Feature files, testng.xml, test data under TestData/dp1slides_testdata.json.
pom.xml: Depends on automation-framework, configured for TestNG execution.

4. derived-product2-tests (Derived Product 2 Module)
Automates derived NBA product at https://www.nba.com/bulls/
src/main/java: Page Object classes for derived product 2 pages.
src/test/java:
com.nba.automation.DP2Runner.runner.DP2TestRunner.java:Cucumber runner, browser setup/teardown from testng.xml.
src/test/resources: Feature files and testng.xml.
pom.xml: Depends on automation-framework with surefire plugin configured.

Technologies and Tools
Java 17+
Maven multi-module build
Selenium WebDriver for browser automation
Cucumber BDD for readable test scenarios
TestNG for test orchestration
JSON for externalizing test data
Page Object Model with PageFactory
Cucumber Reporting plugins for HTML reports
Eclipse IDE for development

How to Run Tests
Set browser parameter in the testng.xml of the respective Maven module or via command line.
Run tests via Maven command:
mvn clean verify -pl derived-product2-tests -am #Example module. 
mvn clean verify -fae  #To run all modules
Reports generated under target/aggregated-report/cucumber-html-reports or configured output folder.
View consolidated Cucumber reports using the framework report generator.

Key Features
Modular separation of reusable framework code and test modules
Browser flexibility through dynamic WebDriver initialization
Config-driven approach using config.properties
Externalized test data in JSON files for data-driven testing
Clean separation of Page Objects, Step Definitions, and Test Runners
Robust reporting with consolidated Cucumber HTML reports
Maven Surefire plugin integration for TestNG and parallel execution support

Architecture Diagram
Root Project: nba-automation (Multi-module Maven)

|-- Module: automation-framework
    |-- src/main/java 
        |-- BaseTest.java
        |-- BasePage.java
        |-- ConfigReader.java
        |-- CucumberReportGeneratorInvoker.java
        |-- FileUtils.java
        |-- Hooks.java
        |-- WebDriverManager.java
    |-- src/main/resources
        |-- config.properties
    |-- src/test/resources
        |-- driver executables (chromedriver.exe, etc.)
    |-- pom.xml (Framework dependencies)

|-- Module: core-test-products
    |-- src/main/java
        |-- PageObjects (e.g., CoreProductPages)
    |-- src/test/java
        |-- runner.CPTestRunner.java (Cucumber TestNG runner)
    |-- src/test/resources
        |-- Feature Files (*.feature)
        |-- testng.xml
    |-- pom.xml (depends on automation-framework)

|-- Module: derived-product1-tests
    |-- src/main/java
        |-- PageObjects (Derived Product 1 POs)
    |-- src/test/java
        |-- runner.DP1TestRunner.java
        |-- testdata (test data reader classes)
    |-- src/test/resources
        |-- Feature Files (*.feature)
        |-- testng.xml
        |-- TestData (JSON files)
    |-- pom.xml (depends on automation-framework)

|-- Module: derived-product2-tests
    |-- src/main/java
        |-- PageObjects (Derived Product 2 POs)
    |-- src/test/java
        |-- runner.DP2TestRunner.java
    |-- src/test/resources
        |-- Feature Files (*.feature)
        |-- testng.xml
    |-- pom.xml (depends on automation-framework)



