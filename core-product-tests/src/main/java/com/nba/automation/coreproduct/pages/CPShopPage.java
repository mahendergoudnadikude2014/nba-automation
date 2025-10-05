package com.nba.automation.coreproduct.pages;

import com.nba.automation.framework.BasePage;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class CPShopPage extends BasePage{
    @FindBy(xpath = "//h2[text()='Want Presale Ticket Access?']//ancestor::div/div[1]/div[1]") 
    private WebElement preSaleModalDialog; //Pre-sale modal dialog close icon
    
    @FindBy(css = "li[data-testid='nav-item-https://shop.warriors.com/'] a span") 
    private WebElement shopMenu; //Shop Menu

    @FindBy(css = "a[title=\"Men's\"]") 
    private WebElement mensMenu; //Men's category
    
    @FindBy(css = "button[data-trk-id='modal-close']") 
    private WebElement savingsModal; //Savings modal dialog

    @FindBy(css = "a[data-trk-id^='side-nav-jackets-all-departments-boxes']")  
    private WebElement jackets; //jackets selection in the Departments
    
    @FindBy(css = "div[class='product-card row']") 
    private List<WebElement> jacketCards; //jacket cards
    
    @FindBy(xpath = "(//a[@data-trk-id='next-page']/i)[1]") 
    private WebElement nextButton; //Next button in pagination

    private WebDriverWait wait;

    public CPShopPage(WebDriver driver){
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void navigateToCPHomePage(String url){
        driver.get(url);
        waitForPageLoad(10);
    }

    public void navigateToMensSection(){
    	Actions act = new Actions(driver);
    	act.moveToElement(shopMenu).moveToElement(mensMenu).click().build().perform();
    }
    
    public List<String> collectJacketDetails(){
    	List<String> jacketsDetails = new ArrayList<>();
        boolean hasNextPage = true;
        int pageNum = 1;
        while(hasNextPage){    	
        	waitForPageLoad(20);
        	wait.until(ExpectedConditions.visibilityOfAllElements(jacketCards));
            System.out.println("Scanning page: " + pageNum + ", cards found: " + jacketCards.size());
            int totalJackets = jacketCards.size();
            //Collect jacket info for this page and append to jacketsDetails list
            for(int i=0;i<totalJackets;i++){
                    WebElement cardElement = jacketCards.get(i);
                    //Create JacketCard instance passing driver and the root WebElement
                    JacketCard jacketCard = new JacketCard(driver, cardElement);
                    String title = jacketCard.getTitle();
                    String price = jacketCard.getPrice();
                    String topSellerMsg = jacketCard.getTopSellerMsg();
                    jacketsDetails.add("Title: " + title + " | Price: " + price + " | Top Seller: " + topSellerMsg);
            }
            //Pagination logic — only try to click Next button for page navigation if it's enabled
            try {
                // Using parent <a> for Next button enabled/disabled state
                WebElement nextParent = nextButton.findElement(By.xpath(".."));
                String ariaDisabled = nextParent.getDomAttribute("aria-disabled");
                String classVal = nextParent.getDomAttribute("class");

                if (nextParent.isDisplayed()
                        && (ariaDisabled == null || !"true".equals(ariaDisabled))
                        && (classVal == null || !classVal.contains("disabled"))
                        && nextParent.isEnabled()) {
                    nextParent.click();
                    pageNum++;
                } else {
                	hasNextPage = false; //No more pages present
                }
            } catch (Exception e) {
            	hasNextPage = false; //No more pages present
            }
        }
        return jacketsDetails;
    }

    public void closeModalDialog(){
    	try{
    		wait.until(ExpectedConditions.visibilityOf(preSaleModalDialog));
    		if(preSaleModalDialog.isDisplayed())
    			preSaleModalDialog.click();
    	}
    	catch(Exception NoSuchElementExpection){
    		//System.out.println("Pre-Sales Modal is not present");
    	}
    }
    
    public void closeSavingsModal(){
    	try{
    		wait.until(ExpectedConditions.visibilityOf(savingsModal));
    		if(savingsModal.isDisplayed());
    			savingsModal.click();
    	}
    	catch(Exception NoSuchElementExpection){
    		//System.out.println("Savings Modal is not present");
    	}
    }
    
    public void selectJackets(){
    	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5000));
    	wait.until(ExpectedConditions.visibilityOf(jackets));
    	if(!jackets.isDisplayed())
    	{
    		((JavascriptExecutor)driver).executeScript("arguments[0].scrollInotView();",jackets);
    	}
    	jackets.click();
    }
}


