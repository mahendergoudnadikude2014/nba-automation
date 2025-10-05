package com.nba.automation.framework;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Hooks{
	//private static final String FILE_PATH = ConfigReader.getProperty("FilePath");
	
	//TestNG annotated method is handling this activity
    /*@Before
    public void setUp() {
        WebDriverManager.getDriver();
    }*/ 

    /*@After
    public void tearDown(Scenario scenario){
    	System.out.println("[Hooks] After scenario — Starting teardown for: " + scenario.getName());
        try {
            if (Files.exists(Paths.get(FILE_PATH))) {
                byte[] fileContent = Files.readAllBytes(Paths.get(FILE_PATH));
                scenario.attach(fileContent, "text/plain", "JacketDetails.txt");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //WebDriverManager.quitDriver(); //TestNG annotated method is handling this activity
        System.out.println("[Hooks] WebDriver quit completed.");
    }*/
}

