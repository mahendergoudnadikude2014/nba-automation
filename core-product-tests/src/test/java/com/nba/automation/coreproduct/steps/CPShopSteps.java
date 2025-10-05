package com.nba.automation.coreproduct.steps;

import com.nba.automation.coreproduct.pages.CPShopPage;
import com.nba.automation.framework.BaseTest;
import com.nba.automation.framework.ConfigReader;
import com.nba.automation.framework.FileUtils;

import io.cucumber.java.en.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class CPShopSteps{
    private WebDriver driver;
    private CPShopPage cpShopPage;
    private List<String> jacketDetails;
    private String filePath = ConfigReader.getProperty("FilePath");
    private String parentWindowHandle;  //Store parent window handle
    
    public CPShopSteps() {
        //Access thread-safe driver instance from BaseTest
        this.driver = BaseTest.getDriver();
    }
    
    @Given("I am on the Core Product homepage")
    public void i_am_on_the_core_product_homepage(){
    	cpShopPage = new CPShopPage(driver);
    	cpShopPage.navigateToCPHomePage(ConfigReader.getProperty("CPBaseUrl"));
        cpShopPage.closeModalDialog();
        //Save parent window handle
        parentWindowHandle = driver.getWindowHandle();
    }

    @When("I navigate to Shop > Mens section")
    public void i_navigate_to_shop_mens_section(){
    	//Logic to switch to the Mens child window 
        cpShopPage.navigateToMensSection();
        Set<String> allWindows=driver.getWindowHandles();
        for(String window:allWindows)
        {
        	if(!window.equals(parentWindowHandle))
        	{
        		driver.switchTo().window(window);
        	}
        }
        //Close the Savings modal dialog it exists/shown
        cpShopPage.closeSavingsModal();
    }
    
    @When("I select Jackets in the Departments list")
    public void i_select_jackets_in_the_departments_list(){
        cpShopPage.selectJackets();
    }

    @Then("I collect all Jackets Title, Price, and Top Seller message from all pages")
    public void i_collect_all_jackets_title_price_and_top_seller_message_from_all_pages(){
        jacketDetails = cpShopPage.collectJacketDetails();
        System.out.println("jacketDetails are :"+jacketDetails);
        //Switch back to the parent window
        driver.switchTo().window(parentWindowHandle);
    }

    @Then("save details to a text file and attach it to the report")
    public void save_details_to_file_and_attach_to_report() throws IOException{
        try {
            FileUtils.writeListToFile(filePath, jacketDetails);
            System.out.println("File written successfully at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to write jacket details file");
        }
    }
}

