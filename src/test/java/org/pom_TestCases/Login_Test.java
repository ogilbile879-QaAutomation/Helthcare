package org.pom_TestCases;

import org.BaseUtility.startUp;
import org.GenericMethod.Loginpage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pom_Pages.DashboardPage;
import org.pom_Pages.LoginPage1;
import org.propertiesUtility.PropertiesGeneric;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(listeners.MyListeners.class)
public class Login_Test {

	public WebDriver driver;
	startUp bu;
	PropertiesGeneric pro;
	Loginpage lg = new Loginpage();
	DashboardPage dp = new DashboardPage(driver);

	@BeforeMethod
	public void initBrowser() throws InterruptedException {
		bu = new startUp();
		driver = bu.startUp("chrome", "https://staff.hmis.beta.thsystems.net.in/");
		lg.LoginField(driver);

	}

	@AfterMethod
	public void closeBrowser() {
		 driver.quit();

	}

}
