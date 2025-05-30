package org.pom_Pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.GenericMethod.GenericMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookAppointmentPage {
	WebDriver driver;
	GenericMethod gm = new GenericMethod();
	

	// CONSTRUCTOR
	public BookAppointmentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "ion-select[name='selectService']")
	private WebElement selectServiceDropdown;
	
	@FindBy(xpath = "//h6[normalize-space()='In Person Consultation']//parent::label//parent::ion-col//input[@name='consultationType']")
	private WebElement InPersonRadioBtn;
	
	@FindBy(xpath = "//textarea[@name='medicalComplaint']")
	private WebElement MedicalComplaintInputBox;
	
	@FindBy(xpath = "//ion-select[@name='department']")
	private WebElement Department;
	
	@FindBy(xpath = "//ion-select[@name='subDepartment']")
	private WebElement SubDepartment;
	
	@FindBy(css = "input#ion-input-36")
	private WebElement InputDateOfAppointment;
	
	@FindBy(xpath = "//ion-button[normalize-space()='Confirm']")
	private WebElement ConfirmTimeslot;
	
	@FindBy(xpath = "(//ion-button[normalize-space()='Select to proceed'])[2]")
	private WebElement SelectToProceed;
	
	
	
	public void SelectService() throws InterruptedException {
		gm.waitForLoaderToDisappear();
		JavascriptExecutor jse=(JavascriptExecutor) driver;
		WebElement selectservice=(WebElement) jse.executeScript ("return document.querySelector('#main-content > app-appointment-action > ion-content > ion-list > ion-col:nth-child(2) > div > app-book-appointment > form > ion-row > ion-col:nth-child(2) > ion-select')");
		jse.executeScript("arguments[0].click();", selectservice);
	}
	
	public void selectoption(String optionText) throws InterruptedException {
		WebElement serviceoption = driver.findElement(By.xpath(String.format("//ion-list[contains(@class, 'ion-select-popover')]//ion-radio[text()='%s']", optionText)));
	    gm.elementClick(serviceoption);
	}
	
	
	public void InPersonRadioBtn() {
		gm.elementClick(InPersonRadioBtn);
	}
	
	public void DescribeMedicalComplaint(String complaint) throws InterruptedException{
		gm.setText(MedicalComplaintInputBox,complaint);
	}
	
	public void Departmentdropdown() {
		gm.elementClick(Department);
	}
	
	public void SubDepartmentdropdown() {
		gm.elementClick(SubDepartment);
	}
	
	public void selectDepartmentOption(String department) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    String xpath = String.format("//ion-item//ion-radio[normalize-space(text())='%s']", department);
	    WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		gm.elementClick(radioButton);
		System.out.println("Selected radio: " + department);
	}
	
	public void SelectToProceedByName() {
	    gm.elementClick(SelectToProceed);
	}
		
	
	public String selectAvailableTimeSlot() throws InterruptedException {
		/*
		 * This method selects an available time slot for a user.
		 * First checks today's slots and if available, selects the first slot and confirms the selection.
		 * If no slots are available today, it checks for available slots on weekdays (skipping weekends) by navigating through a calendar
		 *  and once a slot is found, it selects and confirms the time.
		 */
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE");
	    
	    List<WebElement> todaysSlots = driver.findElements(
	        By.xpath("//ion-col[@size='4']//ion-button[@class='md button button-solid ion-activatable ion-focusable hydrated']")); 
	    System.out.println("Selected today's time slot: "+ todaysSlots.size());
	    if (!todaysSlots.isEmpty()) {
			Thread.sleep(2000);
	        WebElement firstSlot = todaysSlots.get(0);
	        String selectedTime = firstSlot.getText().trim();
	        System.out.println("Selected today's time slot: " + selectedTime);
//	        firstSlot.click();
	        gm.elementClick(firstSlot);
	        Thread.sleep(1000);

	        // Accept confirmation popup.
	        gm.acceptAlert(driver); 

	        System.out.println("Selected today's time slot: " + selectedTime);
	        return selectedTime;
	    }

	 // STEP 2: No slots today, start checking next weekdays
	    LocalDate currentDate = LocalDate.now().plusDays(1); // Start from tomorrow
	    while (true) {
	        String dayOfWeek = currentDate.format(dateFormatter);
	        if (dayOfWeek.equalsIgnoreCase("Saturday") || dayOfWeek.equalsIgnoreCase("Sunday")) {
	            currentDate = currentDate.plusDays(1); // Skip weekends
	            continue;
	        }

	        // STEP 3: Manually open the calendar if no slots found
	        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(
	            By.cssSelector("label[for='ion-input-38'] div[class='native-wrapper sc-ion-input-md sc-ion-input-md-s'] input"))); // Adjust the input selector
	        dateInput.click(); // Open the calendar

	       
	            // STEP 4: Click the "Next" button to go to the next month or year, if needed
	            WebElement nextButton = driver.findElement(By.xpath("//button[@aria-label='Next month']"));  // Adjust the button's XPath if needed
	            nextButton.click(); // Click the "Next" button to move to the next month

	            // STEP 5: Select the next available date number from the calendar
	            String dateStr = currentDate.toString(); // Get the date in yyyy-MM-dd format
	            String xpath = String.format("//td[@data-date='%s' and not(contains(@class, 'disabled'))]", dateStr); // XPath for available date
	            WebElement dateCell = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	            dateCell.click(); // Click the date to select it

	            // STEP 6: Check for time slots after selecting the date
	            List<WebElement> timeSlots = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
	                By.cssSelector(".time-slot:not(.disabled)"))); // Adjust the selector for available time slots
	            if (!timeSlots.isEmpty()) {
	                WebElement firstAvailable = timeSlots.get(0);
	                String selectedTime = firstAvailable.getText().trim();
	                gm.elementClick(firstAvailable);
	                gm.acceptAlert(driver);
	                System.out.println("Selected future time slot: " + selectedTime + " on " + currentDate);
	                return selectedTime;
	            }

	            // Move to the next day if no slots are found on this day
	            currentDate = currentDate.plusDays(1);
	        }	}
	
	public void ConfirmTimeSlot() {
		gm.elementClick(ConfirmTimeslot);
	}


	



	


}
