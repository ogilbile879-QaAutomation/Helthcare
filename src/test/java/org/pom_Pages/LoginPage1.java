package org.pom_Pages;


import org.GenericMethod.GenericMethod;
import org.WaitUtility.WaitGenericMethod;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage1 {
	WebDriver driver;
	GenericMethod gn = new GenericMethod();
	DashboardPage opd = new DashboardPage(driver);
	
	// CONSTRUCTOR
		public LoginPage1(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
	// WEBELEMENTS
	@FindBy(xpath = "//input[@id='email']")
	private WebElement emailField;

	@FindBy(xpath = "//input[@id='pass']")
	private WebElement passwordField;

	@FindBy(xpath = "//button[text()=\"Log in\"]")
	private WebElement loginBtn;

	@FindBy(xpath = "//span[contains(text(),'Friends')]")
	private WebElement FriendsTxt;

	@FindBy(css = "div[class='xi81zsa x1lkfr7t xkjl1po x1mzt3pk xh8yej3 x13faqbe'] span[class='x1lliihq x6ikm8r x10wlt62 x1n2onr6']")
	private WebElement postInputFieldClick;

	@FindBy(css = ".x9f619.x1iyjqo2.xg7h5cd.x1pi30zi.x1swvt13.x1n2onr6.xh8yej3.x1ja2u2z.x1t1ogtf>div>div>div:first-child")
	private WebElement postInputField;

	@FindBy(xpath = "//h4[text()='OPD Bill']")
	private WebElement OPD_Bill_text;

	@FindBy(xpath = "//h4[text()=' Registration List ']")
	private WebElement Registration_List_text;
	
	@FindBy(xpath = "//ion-button[text()=' Search ']")
	private WebElement Registration_List_Search;
	
	
	
	public void Registration_List_Search() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", Registration_List_Search);
		Thread.sleep(2000);
		System.out.println("Search button click successfully");

	}



	public String OPD_Bill_text() {
		return OPD_Bill_text.getText();

	}
	
	
	public String Registration_List_text() {
		return Registration_List_text.getText();

	}

	public void ispostInputFieldClick() {
		WaitGenericMethod wt = new WaitGenericMethod();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		wt.waitForclickableWebElement(driver, 25, "css",
				"div[class='xi81zsa x1lkfr7t xkjl1po x1mzt3pk xh8yej3 x13faqbe'] span[class='x1lliihq x6ikm8r x10wlt62 x1n2onr6']");
		postInputFieldClick.click();

	}

	public void postInputField() {
		WaitGenericMethod wt = new WaitGenericMethod();
		wt.waitForVisibilityWebElement(driver, 25, "css",
				".x9f619.x1iyjqo2.xg7h5cd.x1pi30zi.x1swvt13.x1n2onr6.xh8yej3.x1ja2u2z.x1t1ogtf>div>div>div:first-child");
		postInputField.sendKeys("Hello word");
	}

}
