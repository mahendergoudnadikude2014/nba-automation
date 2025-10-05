package com.nba.automation.DP2.steps;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.nba.automation.DP2.pages.DP2HomePage;
import com.nba.automation.framework.BaseTest;
import com.nba.automation.framework.ConfigReader;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DP2Steps{
	private WebDriver driver;
    private DP2HomePage dp2HomePage;
    private List<String> footerLinks;
    
    public DP2Steps(){
        //Access thread-safe driver instance from BaseTest
        this.driver = BaseTest.getDriver();
    }
    
    @Given("I navigate to the DP2 homepage")
    public void i_navigate_to_the_dp2_homepage(){
    	dp2HomePage = new DP2HomePage(driver);
    	dp2HomePage.navigateToDP2HomePage(ConfigReader.getProperty("DP2BaseUrl"));
    }
    
    @When("I scroll down to the footer section")
    public void i_scroll_down_to_the_footer_section(){
        dp2HomePage.scrollToFooter();
    }

    @Then("I extract all footer hyperlinks")
    public void i_extract_all_footer_hyperlinks(){
        footerLinks = dp2HomePage.getFooterHyperlinks();
        System.out.println("Extracted footer hyperlinks count: " + footerLinks.size());
        footerLinks.forEach(System.out::println);
    }

    @And("I write footer links to CSV file and check for duplicates")
    public void i_write_footer_links_to_csv_file_and_check_for_duplicates(){
        String csvFilePath = "footer_links.csv";
        //Write to CSV
        try(FileWriter writer = new FileWriter(csvFilePath)){
            writer.write("Footer Link\n");
            for (String link : footerLinks){
                writer.write(link + "\n");
            }
            System.out.println("Footer links written to CSV: " + csvFilePath);
        }catch(IOException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to write footer links to CSV");
        }
        //Detect duplicates
        Set<String> uniqueLinks = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        for (String link : footerLinks){
            if (!uniqueLinks.add(link)){
                duplicates.add(link);
            }
        }
        if(!duplicates.isEmpty()){
            System.err.println("Duplicate footer links found:");
            duplicates.forEach(System.err::println);
            throw new AssertionError("Duplicates exist in footer hyperlinks");
        } else{
            System.out.println("No duplicate footer links found.");
        }
    }
}

