package rahulshettyacademy.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	

	
	public static ExtentReports getReportObject() {
		// We have to remember 2 classes while dealing with Extend report:
		// 1. ExtentReports
		// 2. ExtentSparkReporter

		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("Test Reults");

//		ExtentReports extent = new ExtentReports();
		ExtentReports  extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester Name", "Karlo George");
		return extent;
		
	}

}
