package com.runner;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.reports.Reporting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;



@RunWith(Cucumber.class)
@CucumberOptions(dryRun=true,snippets=SnippetType.CAMELCASE,features="src/test/resources",glue= {"com.stepdefinition"},
plugin="json:C:\\Users\\Nishu\\eclipse-workspace111new\\ApiIntegration\\src\\test\\resources\\OMR\\OMR.json",monochrome=true)
public class Testrunner {
	@AfterClass
	public static void Afterclass() {
		Reporting.generateJvmReport("C:\\Users\\Nishu\\eclipse-workspace111new\\ApiIntegration\\src\\test\\resources\\OMR\\OMR.json");

	}
	
}


