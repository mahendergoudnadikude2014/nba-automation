package com.nba.automation.framework;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Optional;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseTest{
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
	
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser){
    	System.out.println("browser value is: "+browser);
    	WebDriver driver;
        // fallback logic for robust parameter reading
        if(browser == null || browser.isEmpty() || browser.equals("${browser}")){
            browser = System.getProperty("browser", "chrome"); // default to chrome if no param
        }
        if(browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")){
            String os = System.getProperty("os.name").toLowerCase();
            String driverPathProperty,relativePath;
            //Get the absolute path of geckoDriver and set it
            String userDir = System.getProperty("user.dir"); //gets current working directory, typically project root
            if (os.contains("win")){
            	relativePath = ConfigReader.getProperty("FirefoxDriverPath_Win");
            } else if(os.contains("mac")){
            	relativePath = ConfigReader.getProperty("FirefoxDriverPath_Mac");
            } else {
                throw new RuntimeException("Unsupported OS: " + os);
            }
            
            //Concatenate path properly
            File driverFile = new File(userDir, relativePath);
            driverPathProperty = driverFile.getAbsolutePath();
            System.setProperty("webdriver.gecko.driver", driverPathProperty);
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
        }else {
            throw new IllegalArgumentException("Invalid browser: " + browser);
        }
        driver.manage().window().maximize();
        threadDriver.set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        WebDriver driver = threadDriver.get();
        if (driver != null) {
            driver.quit();
            threadDriver.remove();
        }
    }
    public static WebDriver getDriver(){
        return threadDriver.get();
    }
}

