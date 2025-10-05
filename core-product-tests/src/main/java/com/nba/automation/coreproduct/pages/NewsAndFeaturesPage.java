package com.nba.automation.coreproduct.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nba.automation.framework.BasePage;

import java.time.Duration;
import java.util.List;

public class NewsAndFeaturesPage extends BasePage {
    // Locators 
    @FindBy(css = "[data-testid='nav-item-#']")
    private WebElement menuItem; //Top Menu
    
    @FindBy(css = "nav [data-testid='nav-item-#'] [title='News & Features']")
    private WebElement menuNewsAndFeatures; //News and Features menu item
    
    @FindBy(css = "[data-testid='tile-article'] [data-testid='tile-meta'] div time")
    private List<WebElement> videoTimeElements; //list of all published dates

    private WebDriverWait wait;

    public NewsAndFeaturesPage(WebDriver driver){
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void hoverOnMenuAndClickNewsAndFeatures(){
        WebElement menuElement = wait.until(ExpectedConditions.visibilityOf(menuItem));
    	Actions act = new Actions(driver);
    	act.moveToElement(menuElement).moveToElement(menuNewsAndFeatures).click().build().perform();
        wait.until(ExpectedConditions.visibilityOfAllElements(videoTimeElements));
    }

    public int countTotalVideoFeeds(){
        wait.until(ExpectedConditions.visibilityOfAllElements(videoTimeElements));
        List<WebElement> feeds = videoTimeElements;
        return feeds.size();
    }

    public int countVideosLengthGreaterOrEqual3Days(){
        wait.until(ExpectedConditions.visibilityOfAllElements(videoTimeElements));
        List<WebElement> feeds = videoTimeElements;
        int count = 0;
        for (WebElement feed : feeds){
            try {
                String lengthText = feed.getText().trim(); // e.g. "3d", "7d", "2h"
                if (lengthText.endsWith("d")) {
                    int days = Integer.parseInt(lengthText.replace("d", ""));
                    if (days >= 3){
                        count++;
                    }
                }
                // Ignore "h" (hours) entirely
            } catch (Exception e){
                // Log or ignore missing/invalid formats
                System.err.println("Could not parse video length: " + e.getMessage());
            }
        }
        return count;
    }
}

