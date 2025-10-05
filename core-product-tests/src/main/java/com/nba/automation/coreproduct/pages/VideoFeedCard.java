package com.nba.automation.coreproduct.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import com.nba.automation.framework.BasePage;

public class VideoFeedCard extends BasePage{
	private WebElement rootElement;

    public VideoFeedCard(WebDriver driver, WebElement rootElement) {
        super(driver);
        this.rootElement = rootElement;
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }

    /*public VideoFeedCard(WebDriver driver){
        super(driver);
    }*/
    
    /*@FindBy(css = "[data-testid='tile-article']")  
    private WebElement rootElement; //Select the main card element*/
    
    @FindBy(css = "[data-testid='tile-meta'] div time") 
    private WebElement timeElement; //Video time as seen on the card

    public WebElement getTimeElement(){
        return timeElement;
    }

    public WebElement getRootElement() {
        return rootElement;
    }
}

