package com.nba.automation.DP1.testdata;

import java.util.List;

public class DP1SlidesTestData{
    private List<String> expectedSlideTitles;
    private List<Double> expectedDurationsSeconds;

    public List<String> getExpectedSlideTitles(){ 
    	return expectedSlideTitles; 
    	}
    public List<Double> getExpectedDurationsSeconds(){ 
    	return expectedDurationsSeconds; 
    	}
    public double[] getExpectedDurationsAsArray(){
        return expectedDurationsSeconds.stream().mapToDouble(Double::doubleValue).toArray();
    	}
}
