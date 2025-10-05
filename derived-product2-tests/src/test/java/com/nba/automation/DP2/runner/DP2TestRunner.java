package com.nba.automation.DP2.runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.nba.automation.framework.BaseTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/DP2_Home.feature",
    glue = {"com.nba.automation.DP2.steps","com.nba.automation.framework"},
    plugin = {"pretty", "html:target/cucumber-html-report.html","json:target/cucumber.json"},
    monochrome = true
)
public class DP2TestRunner extends AbstractTestNGCucumberTests{
	private BaseTest baseTestSetup = new BaseTest();

    @Parameters("browser")
    @BeforeClass(alwaysRun = true)
    public void setUp(String browser){
        baseTestSetup.setUp(browser);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown(){
        baseTestSetup.tearDown();
    }
}

