package com.nba.automation.DP1.pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.nba.automation.framework.BasePage;

public class DP1HomePage extends BasePage{

    @FindBy(css = "button[class^='TileHeroStories_tileHeroStoriesButton']")
    private List<WebElement> slideButtons; //to retrieve Slides

    private WebDriverWait wait;
    
    public DP1HomePage(WebDriver driver){
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void navigateToDP1HomePage(String url){
        driver.get(url);
        waitForPageLoad(10);
    }
    
    //Returns total number of slides
    public int getSlidesCount(){
        return slideButtons.size();
    }
    
    //Return titles of the slides using text from the button
    public List<String> getSlideTitles(){
        return slideButtons.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
 
    //Method to get slide durations by calling the API once per slide index
    public void validateSlideDurations(double[] expectedDurationsSeconds){
    	//Get all the slides
    	List<WebElement> slides = slideButtons;
    	
    	//Iterate through each slide and validate the actual slide duration with expected slide duration
    	for(int i = 0; i < slides.size(); i++){
            WebElement slide = slides.get(i);

            // Wait for slide to become active (aria-selected=true)
            wait.until((ExpectedCondition<Boolean>) d ->
                    "true".equals(slide.getDomAttribute("aria-selected")));
            long startTime = System.currentTimeMillis();

            // Wait for slide to become inactive (aria-selected=false)
            wait.until((ExpectedCondition<Boolean>) d ->
                    "false".equals(slide.getDomAttribute("aria-selected")));
            long endTime = System.currentTimeMillis();

            double durationSeconds = (endTime - startTime) / 1000.0;
            System.out.printf("Slide %d played for %.2f seconds%n", i + 1, durationSeconds);

            //Validate duration with a tolerance of 0.5 seconds
            double tolerance = 0.5;
            if (Math.abs(durationSeconds - expectedDurationsSeconds[i]) > tolerance) {
                throw new AssertionError(String.format(
                        "Slide %d duration mismatch: expected %.2fs but was %.2fs",
                        i + 1, expectedDurationsSeconds[i], durationSeconds));
            }
        }
    }
}


