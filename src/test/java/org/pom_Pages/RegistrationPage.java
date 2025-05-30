package org.pom_Pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;


import org.GenericMethod.GenericMethod;
import org.WaitUtility.WaitGenericMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
	WebDriver driver;
	GenericMethod gm = new GenericMethod();
	DashboardPage opd = new DashboardPage(driver);
	

	// CONSTRUCTOR
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WEBELEMENTS
	@FindBy(css = "[name='RegType *']")
	private WebElement RegType;

	@FindBy(xpath = "//span[contains(text(),'Friends')]")
	private WebElement FriendsTxt;

	@FindBy(css = "div[class='xi81zsa x1lkfr7t xkjl1po x1mzt3pk xh8yej3 x13faqbe'] span[class='x1lliihq x6ikm8r x10wlt62 x1n2onr6']")
	private WebElement postInputFieldClick;

	@FindBy(css = ".x9f619.x1iyjqo2.xg7h5cd.x1pi30zi.x1swvt13.x1n2onr6.xh8yej3.x1ja2u2z.x1t1ogtf>div>div>div:first-child")
	private WebElement postInputField;

	@FindBy(css = "[name='Gender*']")
	private WebElement select_Gender;

	@FindBy(xpath = "//input[@name='Title*'and @id='ion-input-5']")
	private WebElement select_Title;

	@FindBy(xpath = "//input[@name='firstName']")
	private WebElement firstName;
	
	@FindBy(xpath = "//input[@name='middleName']")
	private WebElement middleName;

	@FindBy(xpath = "//input[@name='lastName']")
	private WebElement lastName;

	@FindBy(xpath = "//input[@name='mobileNo']")
	private WebElement mobileNo;

	@FindBy(xpath = "//input[@id='ion-input-12']")
	private WebElement Year;
	
	@FindBy(xpath = "//input[@id='ion-input-13']")
	private WebElement Months;
	
	@FindBy(xpath = "//input[@id='ion-input-14']")
	private WebElement Days;

	@FindBy(xpath = "//input[@name='aadharNo' and contains(@id,'ion-input')]")
	private WebElement aadharNo;

	@FindBy(xpath = "//input[@name='address' and contains(@id,'ion-input')]")
	private WebElement address;

	@FindBy(xpath = "//input[@name='Department*' and contains(@id,'ion-input')]")
	private WebElement Department;

	@FindBy(xpath = "//input[@name='Doctor*' and contains(@id,'ion-input')]")
	private WebElement Doctor;

	@FindBy(xpath = "//input[@name='VisitType *' and contains(@id,'ion-input')]")
	private WebElement VisitType;

	@FindBy(xpath = "//input[@name='PatientSource *' and contains(@id,'ion-input')]")
	private WebElement PatientSource;
	
	@FindBy(xpath = "//ion-row[@class='md hydrated']//ion-checkbox[@id='paRegOnly']")
	private WebElement RegisterOnlyChkbx;
	
	@FindBy(xpath = "//*[local-name()='svg' and @id='barcode']")
	private WebElement patient_BarcodePRN;

	@FindBy(xpath = "//h4[normalize-space()='OPD Registration']")
	private WebElement OPD_Registration;

	@FindBy(xpath = "//h2[text()='Registration List']")
	private WebElement RegistrationList;
	
	@FindBy(xpath = "//input[@id='ion-input-5']")
	private WebElement prn_Input_RegistrationList;

	@FindBy(xpath = "(//ion-col[@class='cardHeader md hydrated'])[1]/label[2]")
	private WebElement PRN_NO;

	@FindBy(xpath = "(//ion-button[text()='OPD Bill'])[1]")
	private WebElement Opd_bill;

	@FindBy(xpath = "//h4[text()='OPD Bill']")
	private WebElement OPD_Bill_text;

	@FindBy(xpath = "//h4[text()=' Registration List ']")
	private WebElement Registration_List_text;
	
	@FindBy(xpath = "//ion-button[text()=' Search ']")
	private WebElement Registration_List_Search;
	
	@FindBy(xpath = "//h2[text()='Queue Management']")
	private WebElement Queue_Management;
	
	@FindBy(xpath = "//div[@class='ion-delegate-host ion-page']//ion-button[normalize-space()='Next']")
	private WebElement OPD_ServiceWindowNextBtn;
	
	
	

	// PAGE ACTION METHODS

	public void select_Gender(String gender) throws InterruptedException {
		WaitGenericMethod wt = new WaitGenericMethod();
		gm.elementClick(select_Gender);
		select_Gender.sendKeys(gender);

		Thread.sleep(2000);
		WebElement gender_male = driver.findElement(By.xpath(
		    String.format("//input[@name='Gender*']/following::div/ion-item[normalize-space(text())='%s']", gender)
		));
		try {
			if (gender_male.isDisplayed()){
				int attempts = 0;
				while (attempts < 3) {
					try {
						WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
						WebElement dropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
								String.format("//input[@name='Gender*']/following::div/ion-item[normalize-space(text())='%s']", gender))));
						dropdownElement.click();
						break; // success
					} catch (StaleElementReferenceException e) {
						attempts++;
					}
				}
			}
		} catch (StaleElementReferenceException elementHasDisappeared) {

			select_Gender.sendKeys("Male");
			gender_male.click();

		}
	}
	
	public void select_Title(String prefix) throws InterruptedException {
		Thread.sleep(2000);
		select_Title.sendKeys(prefix);
		System.out.println("Title add successfully");
	}
	

	public void Opd_bill() throws InterruptedException {
		gm.elementClick(Opd_bill);
		System.out.println("Opd_bill click successfully");
	}

	public void Queue_Management() throws InterruptedException {
		gm.elementClick(Queue_Management);
		System.out.println("Queue_Management clicked successfully.");
	}
	
	public void AppointmentButton(String barcodePRN) {
	    try {
	        WebElement button = driver.findElement(By.xpath("//td[text()='"+barcodePRN+"']//ancestor::tr//ion-button"));
	        gm.elementClick(button);
	        System.out.println("Clicked ion-button for barcode: " + barcodePRN);
	    } catch (NoSuchElementException e) {
	        System.out.println("Button not found for barcode: " + barcodePRN);
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.out.println("Unexpected error occurred while clicking button for barcode: " + barcodePRN);
	        e.printStackTrace();
	    }
	}


	public void RegistrationList() throws InterruptedException {
		gm.elementClick(RegistrationList);
	}
	
	public void Registration_List_Search() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", Registration_List_Search);
		Thread.sleep(2000);
		System.out.println("Search button click successfully");
	}
	
	public void Department(String departmentName) throws InterruptedException {
		gm.ScrollToElement(Department, driver);

	    Actions actions = new Actions(driver);
	    actions.moveToElement(Department).perform();
	    gm.elementClick(Department);
	    Thread.sleep(2000);
	    WebElement deptElement = driver.findElement(By.xpath(String.format("//div[@id='Department*']//ion-item[normalize-space()='%s']", departmentName)));
	    gm.elementClick(deptElement);
	    Thread.sleep(2000);
	    System.out.println("Department '" + departmentName + "' added successfully");
	    Department.sendKeys(Keys.TAB);
	}

	public void PatientSource(String source) throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.moveToElement(PatientSource).perform();
		Thread.sleep(2000);
		PatientSource.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(String.format("//div[@id='PatientSource *']//ion-item[normalize-space()='%s']", source)));
		System.out.println("PatientSource '" + source + "' selected successfully");
	}
	
	public void OPD_ServiceWindowNextBtn() throws InterruptedException {
		gm.elementClick(OPD_ServiceWindowNextBtn);
    }
	
	public void RegisterOnlyCheckbox() throws InterruptedException {
		gm.elementClick(RegisterOnlyChkbx);
    }
	public String savebutton() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    opd.ButtonClick(driver);
	    Thread.sleep(2000);
	    opd.WindowsHandles(driver);
	    wait.until(ExpectedConditions.visibilityOf(patient_BarcodePRN));
	    WebElement barcodeElement = driver.findElement(By.xpath("//*[local-name()='svg' and @id='barcode']//*[local-name()='text']"));
	    String barcode = barcodeElement.getText();
	    System.out.println(barcode + " => successfully generated");
	    opd.ButtonClick(driver);
	    return barcode;  
	}

	public void address() throws InterruptedException {
		address.clear();
		gm.setText(address,"pune");
		System.out.println("address add successfully");
		Thread.sleep(2000);
	}

	public void Year(String year) throws InterruptedException {
		gm.setText(Year,year);
		System.out.println("Age  Years added successfully.");
		Thread.sleep(2000);
	}
	
	public void Months(String months) throws InterruptedException {
		gm.setText(Months, months);
		System.out.println("Age Months added successfully.");
		Thread.sleep(2000);
	}
	
	public void Days(String days) throws InterruptedException {
		Days.clear();
		gm.setText(Days, days);
		System.out.println("Age Days added successfully.");
		Thread.sleep(2000);
	}


	public void VisitType(String visittype) throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.moveToElement(VisitType).perform();
		Thread.sleep(2000);
		gm.elementClick(VisitType);
		Thread.sleep(2000);
		WebElement department = driver.findElement(By.xpath(String.format("//div[@id='VisitType *']//ion-item[normalize-space()='%s']", visittype)));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		gm.setText(VisitType, visittype);
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", department);
		Thread.sleep(2000);
		System.out.println("VisitType '" + visittype + "' added successfully");
		VisitType.sendKeys(Keys.TAB);

	}

	public void Doctor() throws InterruptedException {
		Actions actions = new Actions(driver);
		actions.moveToElement(Doctor).perform();
		Thread.sleep(2000);
		Doctor.click();
		Thread.sleep(2000);
		WebElement department = driver
				.findElement(By.xpath("//div[@id='Doctor*']/ion-item[text()=' Daksha  Vijay kumar Dhondgee ']"));
		department.click();
		Thread.sleep(2000);
		System.out.println("Doctor* add successfully");
		Doctor.sendKeys(Keys.TAB);

	}
	
	public class NameGenerator {
	    private static final String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	    public static String generateUniqueName(int length) {
	        Random random = new Random();
	        StringBuilder name = new StringBuilder(length);
	        for (int i = 0; i < length; i++) {
	            name.append(ALPHABETS.charAt(random.nextInt(ALPHABETS.length())));
	        }
	        return name.toString();
	    }
	}

	public void firstName() throws InterruptedException {
//		firstName.clear();
		gm.setText(firstName, "Test");
//		firstName.sendKeys("Test");
//		System.out.println("firstname added successfully for Test User");
	}
	
	public void middleName() throws InterruptedException {
		middleName.clear();
		middleName.sendKeys("Automation");
		System.out.println("Middlename added successfully for Test User");
	}
	
	public void prn_Input_RegistrationList(String PRN_no) throws InterruptedException {
		prn_Input_RegistrationList.sendKeys(Keys.DELETE);
		prn_Input_RegistrationList.sendKeys(PRN_no);
		Thread.sleep(2000);
		prn_Input_RegistrationList.sendKeys(Keys.ENTER);
	}
	
	public String lastName() throws InterruptedException {
	    Random random = new Random();
	    int nameLength = 6; // Set the name length you want
	    StringBuilder name = new StringBuilder();

	    for (int i = 0; i < nameLength; i++) {
	        char randomChar = (char) ('a' + random.nextInt(26)); 
	        name.append(randomChar);
	    }

	    // Capitalize the first letter
	    String randomName = name.substring(0, 1).toUpperCase() + name.substring(1);

	    // Now using the random name
	    lastName.sendKeys(Keys.DELETE); // Clear the input field
	    lastName.sendKeys(randomName);  // Send the random name to the input field
	    System.out.println("lastName added successfully: " + randomName);
	    
	    Thread.sleep(2000);

	    // Return the generated random name
	    return randomName;
	}


	public void mobileNo() throws InterruptedException {
		mobileNo.sendKeys(Keys.DELETE);
		mobileNo.sendKeys("1234567891");
		System.out.println("mobileNo add successfully");
		Thread.sleep(2000);
		}


	public void aadharNo() throws InterruptedException {
		gm.elementClick(aadharNo);
		gm.setText(aadharNo, "112234567891");
	}

	public String OPD_Registration() {

		return OPD_Registration.getText();

	}

	public String OPD_Bill_text() {

		return OPD_Bill_text.getText();

	}

	public String PRN_NO() {
		return PRN_NO.getText();
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
