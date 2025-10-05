package com.nba.automation.coreproduct.runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.nba.automation.framework.BaseTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.nba.automation.coreproduct.steps","com.nba.automation.framework"},
    plugin = {"pretty", "html:target/cucumber-html-report.html","json:target/cucumber.json"},
    monochrome = true
)
public class CPTestRunner extends AbstractTestNGCucumberTests {
    /* @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    } */
	private BaseTest baseTestSetup = new BaseTest();

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUp(String browser) {
        baseTestSetup.setUp(browser);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        baseTestSetup.tearDown();
    }
}

