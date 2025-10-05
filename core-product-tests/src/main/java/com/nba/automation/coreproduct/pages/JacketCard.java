package com.nba.automation.coreproduct.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import com.nba.automation.framework.BasePage;

public class JacketCard extends BasePage{
	@SuppressWarnings("unused")
	private WebElement rootElement;

    public JacketCard(WebDriver driver, WebElement rootElement){
        super(driver);
        this.rootElement = rootElement;
        // Initialize inner elements scoped to rootElement
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }
    
    @FindBy(css = "a[data-talos='linkSearchResult']")
    private WebElement title; //jacket/card title

    @FindBy(css = "span.lowest span.money-value")
    private WebElement price; //jacket/card price

    @FindBy(css = "div[class*='product-badges-container']")
    private WebElement topSellerBadge; //jacket/card top seller badge

    public String getTitle() {
        try {
        	return (title.isDisplayed()) ? title.getText().trim() : "";
        } catch (Exception e) {
            return "";
        }
    }

    public String getPrice(){
        try {
        	return (price.isDisplayed()) ? price.getText().trim() : "";
        } catch (Exception e) {
            return "";
        }
    }

    public String getTopSellerMsg(){
        try {
            return (topSellerBadge.isDisplayed()) ? topSellerBadge.getText().trim() : "";
        } catch (Exception e) {
            return "";
        }
    }
}
