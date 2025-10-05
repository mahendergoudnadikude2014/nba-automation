package com.nba.automation.DP1.testdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

public class TestDataReader {
    public static DP1SlidesTestData getSlidesTestData(){
        ObjectMapper mapper = new ObjectMapper();
        // Use ClassLoader to load file from resources!
        System.out.println("classpath url: " + TestDataReader.class.getClassLoader().getResource("dp1slides_testdata.json"));
        try (InputStream is = TestDataReader.class.getClassLoader()
                .getResourceAsStream("testdata/dp1slides_testdata.json")){

            if(is == null){
                throw new RuntimeException("Test data file not found in resources!");
            }
            return mapper.readValue(is, DP1SlidesTestData.class);
        } catch(IOException e){
            throw new RuntimeException("Error reading test data JSON from classpath", e);
        }
    }
}


