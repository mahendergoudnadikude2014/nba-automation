package com.nba.automation.coreproduct.steps;

import com.nba.automation.coreproduct.pages.NewsAndFeaturesPage;
import com.nba.automation.framework.BaseTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class NewsAndFeaturesSteps{
	private WebDriver driver;
    private NewsAndFeaturesPage newsAndFeaturesPage;

    private int totalFeeds;
    private int feedsOlderThan3Days;
    
    public NewsAndFeaturesSteps() {
        //Access thread-safe driver instance from BaseTest
        this.driver = BaseTest.getDriver();
    }

    @When("I hover on menu item and click on New & Features")
    public void i_hover_and_click_new_and_featured() {
    	newsAndFeaturesPage = new NewsAndFeaturesPage(driver);
    	newsAndFeaturesPage.hoverOnMenuAndClickNewsAndFeatures();
    }

    @Then("I count total number of video feeds")
    public void i_count_total_number_of_video_feeds() {
        totalFeeds = newsAndFeaturesPage.countTotalVideoFeeds();
        System.out.println("Total Video Feeds: " + totalFeeds);
    }

    @And("I count the video feeds those are present in the page >= 3d")
    public void i_count_video_feeds_older_than_3_days() {
        feedsOlderThan3Days = newsAndFeaturesPage.countVideosLengthGreaterOrEqual3Days();
        System.out.println("Video Feeds older or equal to 3 days: " + feedsOlderThan3Days);
    }

    @Then("the total feeds count should be greater than or equal to the count of 3 days old feeds")
    public void verify_feed_counts() {
        Assert.assertTrue(totalFeeds >= feedsOlderThan3Days,
                "Total feeds count should be >= feeds older than or equal to 3 days");
    }
}

