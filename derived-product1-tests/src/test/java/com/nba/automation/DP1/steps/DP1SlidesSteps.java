package com.nba.automation.DP1.steps;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.nba.automation.DP1.pages.DP1HomePage;
import com.nba.automation.DP1.testdata.DP1SlidesTestData;
import com.nba.automation.DP1.testdata.TestDataReader;
import com.nba.automation.framework.BaseTest;
import com.nba.automation.framework.ConfigReader;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DP1SlidesSteps{
    private WebDriver driver;
    private DP1HomePage dp1HomePage;
    
    /*//Expected data for Slide Titles
    private final List<String> expectedSlideTitles = Arrays.asList(
            "76ers Announce Details for 2025 Blue x White Scrimmage",
            "76ers to Face Knicks in NBA Abu Dhabi Games 2025",
            "JOIN OUR OPEN HOUSE EVENT - RSVP TODAY",
            "Grimes Re-Signs After Breakout Year With Sixers",
            "76ers Re-Sign Quentin Grimes"
    );
    
    //Expected durations in seconds corresponding to each slide
    private final double[] expectedDurationsSeconds = {10.00, 10.00, 10.00, 10.00, 10.00};*/
    private final DP1SlidesTestData testData = TestDataReader.getSlidesTestData();
    
    public DP1SlidesSteps(){
        //Access thread-safe driver instance from BaseTest
        this.driver = BaseTest.getDriver();
    }
    
    @Given("I navigate to the DP1 homepage")
    public void iNavigateToTheDP1Homepage(){
        dp1HomePage = new DP1HomePage(driver);
        dp1HomePage.navigateToDP1HomePage(ConfigReader.getProperty("DP1BaseUrl"));
    }

    @When("I count the number of slides under Tickets Menu")
    public void iCountSlidesUnderTicketsMenu(){
        int count = dp1HomePage.getSlidesCount();
        System.out.println("Number of slides detected: " + count);
    }
    
    @Then("the slide count should be {int}")
    public void the_slide_count_should_be(Integer numberOfSlides){
    	int actualCount = dp1HomePage.getSlidesCount();
        Assert.assertEquals(actualCount, numberOfSlides, "Slide count does not match expected value");
    }
    
    @And("I validate the slide titles with expected data")
    public void i_validate_the_slide_titles_with_expected_data(){
        List<String> actualSlideTitles = dp1HomePage.getSlideTitles();
        List<String> expectedSlideTitles = testData.getExpectedSlideTitles();
        Collections.sort(actualSlideTitles);
        Collections.sort(expectedSlideTitles);
        Assert.assertEquals(actualSlideTitles, expectedSlideTitles, "Slide titles do not match expected");
    }
    
    @And("I validate the slide durations with expected data")
    public void i_validate_the_slide_durations_with_expected_data(){
      dp1HomePage.validateSlideDurations(testData.getExpectedDurationsAsArray());
    }
}

