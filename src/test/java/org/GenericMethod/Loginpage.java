package org.GenericMethod;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pom_Pages.DashboardPage;
import org.utilities.BaseUtility;

public class Loginpage {

	BaseUtility bs = new BaseUtility();
	private WebDriver driver;
	GenericMethod gn = new GenericMethod();
	DashboardPage  db = new DashboardPage(driver);
	
	@FindBy(xpath = "//input[@id='email']")
	private WebElement emailField;

	@FindBy(xpath = "//input[@id='pass']")
	private WebElement passwordField;

	@FindBy(xpath = "//button[text()=\"Log in\"]")
	private WebElement loginBtn;

	public void LoginField(WebDriver driver) throws InterruptedException {

		List<WebElement> eles = driver.findElements(By.xpath(
				"//input[@id='ion-input-0' and @name='loginName']|//input[@id='ion-input-1' and @name='password']"));
		for (WebElement ele : eles) {

			try {
				if (ele.getDomAttribute("name").equalsIgnoreCase("loginName")) {
					ele.sendKeys("smbt-08-sa");
				} else if (ele.getDomAttribute("name").equalsIgnoreCase("password")) {
					ele.sendKeys(Keys.DELETE);
					ele.sendKeys("Smbt@123");
					gn.wait(10);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		gn.SwichToFramforTab(driver);
		WebElement submitbutton = driver.findElement(By.xpath("//ion-button[@type='submit']"));
		submitbutton.click();
		System.out.println("successfully click login button");
		WebElement dashboadpage = driver.findElement(By.xpath("//h4[text()=' Dashboard ']"));
		if (dashboadpage.getText().equalsIgnoreCase("Dashboard")) {
			System.out.println("successfully display '" + dashboadpage.getText() + "'");
		}
	}

}
