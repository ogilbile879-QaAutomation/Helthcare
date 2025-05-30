package listeners;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import org.GenericMethod.GenericMethod;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.utilities.ExtentReporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyListeners implements ITestListener {

	ExtentSparkReporter htmlreport;
	ExtentReports report;
	ExtentTest extentTest;

	GenericMethod gn = new GenericMethod();
	public WebDriver driver = null;
	static Date d = new Date();
	// static String fileName = "Extent_" + d.toString().replace(":", "_").replace("
	// ", "_") + ".html";

	public void configer_Report() {
		htmlreport = new ExtentSparkReporter("HIMS application Report");
		report = new ExtentReports();
		report.attachReporter(htmlreport);

		String currentDate = LocalDate.now().toString();

		report.attachReporter(htmlreport);
		report.setSystemInfo("Organization", "THSystems");
		report.setSystemInfo("Project", "HIMS");
		report.setSystemInfo("Environment", "QA");
		report.setSystemInfo("Browser", "Chrome");
		report.setSystemInfo("Date", currentDate);
		report.setSystemInfo("Test Run", " Demo Automation");
		report.setSystemInfo("Automation Tester", "Bhagyashree Belawadi");

		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setDocumentTitle("HIMS application Report");
		htmlreport.config().setEncoding("utf-8");
		htmlreport.config().setReportName("HIMS application Report");
		htmlreport.config().setTimeStampFormat("EEEE,MMMM dd,yyyy,hh:mm a '('zzz')'");

	}

	@Override
	public void onTestStart(ITestResult result) {

		System.out.println("onTestStart of test method name " + result.getName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		System.out.println("onTestSuccess of test method name " + result.getName());
		extentTest = report.createTest(result.getName());
		extentTest.log(Status.PASS,
				MarkupHelper.createLabel("Name of the  Pass testcase : " + result.getName(), ExtentColor.GREEN));
		/*
		 * try { extentTest.log(Status.PASS, (Markup)
		 * extentTest.addScreenCaptureFromPath(gn.Screenshot(driver))); } catch
		 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		  System.out.println("onTestFailure of test method name: " + result.getName());

		  extentTest = report.createTest(result.getName());
		  extentTest.log(Status.FAIL, MarkupHelper.createLabel(
		  "Name of the failed test case: " + result.getName(), ExtentColor.RED));

		  // Log the exception (if any)
		   Throwable throwable = result.getThrowable();
		    if (throwable != null) {
		        extentTest.log(Status.FAIL, "Exception: " + throwable.getMessage());
		    }

		    try {
		        String screenshotPath = gn.Screenshot(driver);
		        if (screenshotPath != null) {
		            extentTest.addScreenCaptureFromPath(screenshotPath);
		        } else {
		            extentTest.log(Status.WARNING, "Screenshot path is null. Screenshot not attached.");
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		        extentTest.log(Status.WARNING, "Failed to attach screenshot due to IOException");
		    }
	}


//	@Override
//	public void onTestFailure(ITestResult result) {
//
//		System.out.println("onTestFailure of test method name " + result.getName());
//		extentTest = report.createTest(result.getName());
//		extentTest.log(Status.FAIL,
//				MarkupHelper.createLabel("Name of the  fail testcase : " + result.getName(), ExtentColor.RED));
//
//		Date d = new Date();
//		String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
//
//		
//		  try { extentTest.log(Status.FAIL, (Markup)
//		  extentTest.addScreenCaptureFromPath(gn.Screenshot(driver))); } catch
//		  (IOException e) { // TODO Auto-generated catch block e.printStackTrace(); }
//		 
//
//	}

	@Override
	public void onTestSkipped(ITestResult result) {

		System.out.println("onTestSkipped method name " + result.getName());
		extentTest = report.createTest(result.getName());
		extentTest.log(Status.SKIP,
				MarkupHelper.createLabel("Name of the  Skip testcase : " + result.getName(), ExtentColor.YELLOW));

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("onstart method name " + context.getName());
		configer_Report();

	}

	@Override
	public void onFinish(ITestContext context) {

		report.flush();

	}

}
