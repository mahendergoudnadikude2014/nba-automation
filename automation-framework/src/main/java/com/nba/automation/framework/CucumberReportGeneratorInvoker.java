package com.nba.automation.framework;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CucumberReportGeneratorInvoker{

    public static void generateReport(List<String> jsonReportPaths, String outputDir) {
        File outputDirectory = new File(outputDir);
        
        //Prepare list of JSON files from paths
        List<String> jsonFiles = new ArrayList<>(jsonReportPaths); 
        
        //Create configuration for the report
        Configuration config = new Configuration(outputDirectory, "NBA Automation Aggregated Report");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, config);
        reportBuilder.generateReports();
    }
    
    
    //Standalone invoker main method.Can be called from Maven exec plugin or manually to generate the report.
    public static void main(String[] args){
        try {
            List<String> jsonReports = List.of(
            		"core-product-tests/target/cucumber.json",
                    "derived-product1-tests/target/cucumber.json",
                    "derived-product2-tests/target/cucumber.json"
                    // Add more module JSON paths if needed based on the new test modules
            );

            String outputDir = "target/aggregated-report";

            generateReport(jsonReports, outputDir);

            System.out.println("Aggregated Cucumber report generated at: " + outputDir);
        } catch (Exception e) {
            System.err.println("Failed to generate aggregated report: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}



