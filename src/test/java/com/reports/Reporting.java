package com.reports;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class Reporting {
public static void generateJvmReport(String jsonFile) {
		//1mention the path of where to store the JVM report
		File file =new File("C:\\Users\\Nishu\\eclipse-workspace111new\\ApiIntegration\\src\\test\\resources");
		//2 crate object for configuration class
		Configuration configuration = new Configuration(file, "OMRBRANCH");
		//3 pass the os version details to display the report
		configuration.addClassifications("os", "win10");
		configuration.addClassifications("browser", "chrome");
		configuration.addClassifications("browserversion", "101");
		// create the list and add the json file
	List<String>jsonfiles = new ArrayList<String>();
	jsonfiles.add(jsonFile);
	// create object for report builder class
	ReportBuilder builder = new ReportBuilder(jsonfiles, configuration);
	builder.generateReports();
	
	}
}
