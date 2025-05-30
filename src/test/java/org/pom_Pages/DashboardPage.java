package org.pom_Pages;


import java.time.Duration;
import java.util.List;
import org.GenericMethod.GenericMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.utilities.BaseUtility;


public class DashboardPage {
	// CONSTRUCTOR
			public DashboardPage(WebDriver driver) {
				this.driver = driver;
				PageFactory.initElements(driver, this);
			}
			
	WebDriver driver;
	GenericMethod gm = new GenericMethod();
	//DashboardPage opd = new DashboardPage(driver);
	BaseUtility bs = new BaseUtility();
	// WEBELEMENTS
	
	@FindBy(xpath = "//span[@class='hide-menu' and normalize-space()='OPD']")
	private WebElement OPD;

	@FindBy(xpath = "//h2[text()='OPD Registration']")
	private WebElement OPDRegistration;

	@FindBy(xpath = "//h2[text()='Queue Management']")
	private WebElement Queue_Management;
	
	@FindBy(xpath = "//h2[text()='Tele Medicine']")
	private WebElement TeleMedicine;

	
	

	public void OPD() throws InterruptedException {
		boolean isclik = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		System.out.println(OPD.getText());
		Thread.sleep(2000);
		OPD.click();//		gm.elementClick(OPD);
		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	    JavascriptExecutor js = (JavascriptExecutor) driver;
//
//	    // Wait for element to be visible
//	    wait.until(ExpectedConditions.visibilityOf(OPD));
//
//	    // Scroll to the element instead of full scroll
//	    js.executeScript("arguments[0].scrollIntoView(true);", OPD);
//
//	    // Optional: wait a bit (only if needed)
//	    try { Thread.sleep(1000); } catch (InterruptedException e) {}
//
//	    // Wait until clickable and click
//	    wait.until(ExpectedConditions.elementToBeClickable(OPD)).click();
//
//	    System.out.println("Clicked on OPD: " + OPD.getText());
	}

	public void OPDRegistration() throws InterruptedException {
		gm.elementClick(OPDRegistration);
//		OPDRegistration.click();
//		System.out.println("OPD Registration clicked successfully.");
	}

	public void TeleMedicine() throws InterruptedException {
		gm.elementClick(TeleMedicine);
//		Queue_Management.click();
//		System.out.println("Queue_Management clicked successfully.");
//		Thread.sleep(2000);
	}
	
	public void Queue_Management() throws InterruptedException {
		gm.elementClick(Queue_Management);
//		Queue_Management.click();
//		System.out.println("Queue_Management clicked successfully.");
//		Thread.sleep(2000);
	}

	public void opdModule(WebDriver driver) throws InterruptedException {
		boolean isclick = true;
		int maxRetries = 5;
		int retryDelay = 3000;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		WebElement opd = driver.findElement(By.xpath("//span[@class='hide-menu' and text()=' OPD ']"));
		// gn.wait(3);

		System.out.println(opd.getText());
		Thread.sleep(2000);
		opd.click();
		System.out.println("opd button clicked successfully.");

		WebElement opd_registration = driver.findElement(By.xpath("//h3[text()='OPD Registration']"));
		opd_registration.click();

		if (isclick == true) {
			System.out.println("OPD Registration clicked successfully.");
		}
	}

	public void ButtonClick(WebDriver driver) {
		List<WebElement> elements = driver
				.findElements(By.xpath("//ion-button[text()=' Save ']|//ion-button[text()='Close']"));
		for (WebElement ele : elements) {
			{
				if (ele.isDisplayed()) {
					try {
						ele.click();
					} catch (Exception e) {
						bs.clickByJS(driver, ele);
					}
				}
			}
		}
	}

	public void WindowsHandles(WebDriver driver) {
		String Before_handlewindow = driver.getWindowHandle();
		System.out.println("Geting All Window Handles => " + driver.getWindowHandles());
		for (String getWindow : driver.getWindowHandles()) {
			if (!getWindow.equalsIgnoreCase(Before_handlewindow)) {
				driver.switchTo().window(Before_handlewindow);
			}
		}
	}

	public boolean retryClick(WebDriver driver, By locator, int maxRetries, int retryDelay) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement ele = driver.findElement(By.xpath("//span[@class='hide-menu' and text()=' OPD ']"));

		for (int attempt = 1; attempt <= maxRetries; attempt++) {
			try {
				ele.click();
				return true;
			} catch (Exception e) {
				try {
					Thread.sleep(retryDelay);
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();
				}
			}
		}
		return false;
	}

}

