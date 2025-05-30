package org.GenericMethod;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.commons.codec.language.bm.Lang;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.utilities.BaseUtility;

public class GenericMethod {

	BaseUtility bs = new BaseUtility();
	private WebDriver driver;
	
	 public void waitForLoaderToDisappear() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        By loader = By.cssSelector(".loading-wrapper.ion-overlay-wrapper.sc-ion-loading-md");

	        try {
	            wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
	        } catch (Exception e) {
	            System.out.println("Loader may have already disappeared or not found.");
	        }
	    }

	public boolean checkVisiblityflag(List<WebElement> elements) {
		for (WebElement element : elements) {
			if (element.isDisplayed())
				return true;
		}
		return false;
	}
	
	public void setText(WebElement element, String value) throws InterruptedException {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOf(element));
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        element.clear();
	        element.sendKeys(value);
	        System.out.println("Successfully entered value: " + value);
	    } catch (Exception e) {
	        System.out.println("Error while sending keys to element: " + e.getMessage());
	    }
	}
	
	public void elementClick(WebElement element) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOf(element));
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        element.click();  
	        System.out.println(element+" Clicked successfully.");
	    } catch (ElementClickInterceptedException ecie) {
	        System.out.println("Click intercepted: " + ecie.getMessage());
	    } catch (Exception e) {
	        System.out.println("Error while clicking element: " + e.getMessage());
	    }
	}
	
	 public void acceptAlert(WebDriver driver) {
	    try {
	         Alert alert = driver.switchTo().alert();
	          System.out.println("Alert Text: " + alert.getText());
	          alert.accept();
	       } catch (NoAlertPresentException e) {
	          System.out.println("No alert present to accept.");
	        }
	    }


	public String checkErrorMessage(WebDriver driver) {
		WebElement msgele = CheckVisibleElements(driver.findElements(By.xpath(
				".//div[@role='dialog']//p | .//div[contains(@style,'color: red')] | .//span[text()='Required'] |.//span[text()='Should be at least 5 characters'] | .//span[text()='Passwords do not match']| .//span[text()='Invalid'] ")));
		if (msgele != null && !msgele.getText().isEmpty() && (msgele.getText().contains("Review")
				|| msgele.getText().contains("Duplicate") || msgele.getText().contains("error")
				|| msgele.getText().contains("mandatory") || msgele.getText().contains("Required")
				|| msgele.getText().contains("Should be at least 5 characters")
				|| msgele.getText().contains("Passwords do not match") || msgele.getText().contains("Invalid"))) {
			return msgele.getText();
		}
		return null;
	}

	// end

	public void moveToCenterOfScreen(WebDriver driver) {
		try {
//				

			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1200)");
			Thread.sleep(300);
		} catch (Exception e) {
		}
	}

	public void moveToUppersideOfScreen(WebDriver driver) {
		try {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(500,0)");
			Thread.sleep(300);
		} catch (Exception e) {
		}
	}

	public void waitUntilLoadComplete(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
			wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//div[@class='oxd-loading-spinner-container']/div")));

			List<WebElement> eles = driver
					.findElements(By.xpath("//div[@class='oxd-loading-spinner-container']/div"));

			for (WebElement ele : eles) {

				if (ele.isEnabled() && ele.isDisplayed()) {

					System.out.println("loadder is present ");
				}

			}

		} catch (Exception e) {
			// log.error("No Loader are present");
		}

	}

	public void Screenshotq(WebDriver driver) {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		scrShot.getScreenshotAs(OutputType.BYTES);

	}

	public String Screenshot(WebDriver driver) throws IOException {
		if (driver == null) {
	        System.out.println("Driver is null, cannot take screenshot.");
	        return null;
	    }
	    File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	    String screenshotDir = "./screenshots/";
	    File dir = new File(screenshotDir);
	    if (!dir.exists()) {
	        dir.mkdirs(); // Create the folder if it doesn't exist
	    }

	    String fileName = "screenshot_" + System.currentTimeMillis() + ".png";
	    File destination = new File(screenshotDir + fileName);
	    FileUtils.copyFile(scrFile, destination);

	    return destination.getAbsolutePath();
	}

	public WebElement CheckVisibleElements(List<WebElement> elements) {
		for (WebElement ele : elements) {
			if (ele.isDisplayed()) {
				return ele;
			}

		}
		return null;

	}

	public void VisibilityofElement(WebElement element) {
		int i = 1000;
		for (int j = 1; j < 10; j++) {
			if (element.isDisplayed()) {
			} else {
				try {
					Thread.sleep(j * i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void listClick(List<WebElement> datas) {
		for (WebElement data : datas) {
			if (data.isDisplayed()) {
				data.click();
				break;
			}
		}
	}

	// this implemented for scroll and

	public void ScrollToElement(WebElement ele, WebDriver driver) {

		// driver.execute_script("arguments[0].scrollIntoView({ behavior: 'auto', block:
		// 'center' });", element)
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);

	}

	public void ScrollToElementAndClick(WebElement ele, WebDriver driver) {
		this.ScrollToElement(ele, driver);

		try {
			ele.click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollBy(0,-100)");

			ele.click();

		}

	}

	// switch to framtab
	public void SwichToFramforTab(WebDriver driver) {
		List<WebElement> iframs = driver.findElements(By.xpath(".//ifram"));
		for (WebElement ifram : iframs) {
			String framid = ifram.getAttribute("id");
			System.out.println(framid);

			if (framid.equals("iframsubscreen")) {

				driver.switchTo().frame(ifram);
			}
		}

	}

	public boolean isAlertprasent(WebDriver driver) {
		boolean Alertvalue = false;

		try {
			driver.switchTo().alert();
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			Alertvalue = true;
			return Alertvalue;

		}

		return Alertvalue;

	}

	// Success message
	public String successMessage() {
		String Expecxedmessage = "";

		try {

			WebElement tostMgs = driver.findElement(By.xpath(
					"//div[@class='oxd-toast-content oxd-toast-content--success']/p[@class='oxd-text oxd-text--p oxd-text--toast-title oxd-toast-content-text']"));

			Expecxedmessage = tostMgs.getText();

		} catch (Exception e) {

			System.out.println("message is not present ");

		}

		return Expecxedmessage;

	}

	// end

	// this is implemented for Textboxfiled = lebel , data
	public void inputField(WebDriver driver, String label, String data) {

		List<WebElement> eles = driver.findElements(
				By.xpath("//label[text()='" + label + "']/following::div/input[@class='oxd-input oxd-input--active']"));
		this.CheckVisibleElements(eles);

		for (WebElement ele : eles) {

			try {

				ele.sendKeys(Keys.DELETE);
				ele.sendKeys(data);

			} catch (Exception e) {

				bs.sendKeysByActions(driver, ele, data);

			}

		}

	}

	// end

	public void TextAreasInput(WebDriver driver, String placeholdername, String data) {
		;

		List<WebElement> ele = driver.findElements(By.tagName("textarea"));
		this.CheckVisibleElements(ele);

		for (WebElement singele : ele) {
			String name = singele.getAttribute("placeholder");

			if (name.equalsIgnoreCase(placeholdername)) {
				singele.sendKeys(data);
			} else {
				System.out.println("no data available ");
			}

		}

	}

	public void ButtonClick(WebDriver driver, String Btnname) {

		List<WebElement> elements = driver.findElements(By.xpath("//button[text()=' " + Btnname.trim() + " ']"));
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

	public void WorkingMinutFromAction(WebDriver driver, String uniqueTxt, String AM) {
		try {
			List<WebElement> eles = null;
			;

			eles = driver.findElements(By.xpath(
					"//label[text()='" + uniqueTxt + "']/following::div[@class=\"oxd-time-wrapper\"][1]/div/input"));

			for (WebElement ele : eles) {
				if (ele.isDisplayed() && ele.isEnabled()) {
					try {
						ele.click();
						break;
					} catch (Exception e) {
						bs.clickByJS(driver, ele);
					}
				}

			}

			WebElement ele = driver.findElement(By.xpath("//div[@class='oxd-time-hour-input']//input"));
			bs.waitForVisibilityOfElement(driver, 5, ele);
			;
			if (ele.isDisplayed() && ele.isDisplayed()) {

				ele.sendKeys(Keys.DELETE);
				ele.sendKeys(Keys.BACK_SPACE);
				ele.sendKeys(Keys.BACK_SPACE);
				ele.sendKeys(Keys.DELETE + "9");
			}

			bs.waitForVisibilityOfElement(driver, 5, ele);

			WebElement el = driver.findElement(By.xpath("//input[@value='" + AM + "']"));

			String type = el.getAttribute("value");

			if (type.equalsIgnoreCase(AM)) {

				el.click();
			}

			driver.findElement(By.tagName("html")).click();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void WorkingHoursFromAction(WebDriver driver, String uniqueTxt, String AM) {
		try {
			List<WebElement> eles = null;
			;

			eles = driver.findElements(By.xpath(
					"//label[text()='" + uniqueTxt + "']/following::div[@class=\"oxd-time-wrapper\"][1]/div/input"));

			for (WebElement ele : eles) {
				if (ele.isDisplayed() && ele.isEnabled()) {
					try {
						ele.click();
						break;
					} catch (Exception e) {
						bs.clickByJS(driver, ele);
					}
				}

			}

			WebElement ele = driver.findElement(By.xpath("//div[@class='oxd-time-hour-input']//input"));
			bs.waitForVisibilityOfElement(driver, 5, ele);
			;
			if (ele.isDisplayed() && ele.isDisplayed()) {

				ele.sendKeys(Keys.DELETE);
				ele.sendKeys(Keys.BACK_SPACE);
				ele.sendKeys(Keys.BACK_SPACE);
				ele.sendKeys(Keys.DELETE + "9");
			}

			bs.waitForVisibilityOfElement(driver, 5, ele);

			WebElement el = driver.findElement(By.xpath("//input[@value='AM']"));

			String type = el.getAttribute("value");

			if (type.equalsIgnoreCase(AM)) {

				el.click();
			}

			driver.findElement(By.tagName("html")).click();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Unique txt according to Delete Row
	public void deleteActionIntable(WebDriver driver, String uniqueTxt) {
		try {
			List<WebElement> eles = null;

			eles = driver.findElements(
					By.xpath("//div[text()='" + uniqueTxt + "']/following::div/button/i[@class='oxd-icon bi-trash']"));

			for (WebElement ele : eles) {
				if (ele.isDisplayed() && ele.isEnabled()) {
					try {
						ele.click();
						break;
					} catch (Exception e) {
						bs.clickByJS(driver, ele);
					}
				}

			}

			WebElement ele = driver.findElement(By.xpath("//button[@type='button' and text()=' Yes, Delete ']"));
			bs.waitForVisibilityOfElement(driver, 5, ele);
			ele.click();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// end

	// Unique txt according to Edit Row
	public void EditActionIntable(WebDriver driver, String uniqueTxt) {
		try {
			List<WebElement> eles = null;

			eles = driver.findElements(By.xpath(
					"//div[text()='" + uniqueTxt + "']/following::div/button[2]/i[@class='oxd-icon bi-pencil-fill']"));

			for (WebElement ele : eles) {
				if (ele.isDisplayed() && ele.isEnabled()) {
					try {
						ele.click();
						break;
					} catch (Exception e) {
						bs.clickByJS(driver, ele);
					}
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void WindowsHandles(WebDriver driver) {

		String Before_handlewindow = driver.getWindowHandle();
		System.out.println("Geting All Window Handles => " + driver.getWindowHandles());

		for (String getWindow : driver.getWindowHandles()) {

			if (!getWindow.equalsIgnoreCase(Before_handlewindow)) {

				driver.switchTo().window(getWindow);
			}

		}

	}

	public void FileUpload(WebDriver driver, String filepath) {

		Robot robot = null;

		List<WebElement> elements = driver
				.findElements(By.xpath("//input[@class=\"oxd-file-input\"]/following::div/div[text()='Browse']"));

		for (WebElement ele : elements) {

			try {

				ele.click();
			} catch (Exception e) {

				bs.clickByJS(driver, ele);

			}

			try {

				java.awt.datatransfer.Clipboard clipboad = Toolkit.getDefaultToolkit().getSystemClipboard();
				StringSelection str = new StringSelection(filepath);
				clipboad.setContents(str, null);

				robot = new Robot();
				robot.setAutoDelay(4000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_V);

				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);

				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_V);

				robot.setAutoDelay(4000);

				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);

			} catch (AWTException e) {

			}

		}

	}

}
