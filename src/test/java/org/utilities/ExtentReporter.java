package org.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {

	static ExtentReporter extentReport = null;

	public static ExtentReports getExtentReport() {

        String extentReportPath = "./Reports/ExtentReport.html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(extentReportPath);

        reporter.config().setReportName("Orm orange  Automation Results");
        reporter.config().setDocumentTitle(" Automation Results");

        ExtentReports extentReport = new ExtentReports();
        extentReport.attachReporter(reporter);
        extentReport.setSystemInfo("Operating System", "Windows 11");
        extentReport.setSystemInfo("Executed By", "Nikhil patil");

        return extentReport;
    }

 

}
