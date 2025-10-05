package com.nba.automation.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;
import java.util.Objects;

public class WebDriverManager{
    private static WebDriver driver;

    private WebDriverManager() {}

    public static WebDriver getDriver(){
        if (Objects.isNull(driver)) {
        	String browser = System.getProperty("browser", ConfigReader.getProperty("browser")).toLowerCase();
            switch (browser) {
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "chrome":
                default:
                    driver = new ChromeDriver();
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver(){
        if (Objects.nonNull(driver)){
            driver.quit();
            driver = null;
        }
    }
}

