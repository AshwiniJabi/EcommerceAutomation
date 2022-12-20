package com.testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/com/feature/",
		glue = {"com/stepdefinations"}
		,tags = "@GetCart"
		,plugin = {"json:target/jsonReports/CucumberReport.json"}		
		
		)
public class TestRunner {

}
