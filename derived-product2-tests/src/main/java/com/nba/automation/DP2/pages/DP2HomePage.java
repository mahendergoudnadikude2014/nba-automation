package com.nba.automation.DP2.pages;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.nba.automation.framework.BasePage;

public class DP2HomePage extends BasePage{
    @FindBy(css = "footer[data-testid='footer']")
    private WebElement footerSection; //Locates the footerSection
    
    @FindBy(css = "footer[data-testid='footer'] a")
    private List<WebElement> hyperLinks; //Locates all anchor tag elements inside footerSection
    
    public DP2HomePage(WebDriver driver){
        super(driver);
    }

    public void navigateToDP2HomePage(String url){
        driver.get(url);
        waitForPageLoad(10);
    }
    
    //Scroll to the footer to make the section visible.
    public void scrollToFooter(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", footerSection);
        // Optional short wait for rendering
        try {Thread.sleep(500);} 
        catch(InterruptedException ignored){}
    }
    
    //Extract all hyperLinks from footer into a list
    public List<String> getFooterHyperlinks(){
        List<String> links = new ArrayList<>();
        //Find all anchor tags inside the footer section
        for(WebElement anchor : hyperLinks){
            String href = anchor.getDomAttribute("href");
            if(href != null && !href.isEmpty()){
                links.add(href.trim());
            }
        }
        return links;
    }
}


