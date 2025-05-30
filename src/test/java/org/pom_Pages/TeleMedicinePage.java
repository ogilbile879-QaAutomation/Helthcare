package org.pom_Pages;

import java.time.Duration;
import java.util.NoSuchElementException;
import org.GenericMethod.GenericMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TeleMedicinePage {
	// CONSTRUCTOR
			public TeleMedicinePage(WebDriver driver) {
				this.driver = driver;
				PageFactory.initElements(driver, this);
			}
			
	WebDriver driver;
	GenericMethod gm = new GenericMethod();
	// WEBELEMENTS
	
	@FindBy(xpath = "//h2[text()='List Of Appointment']")
	private WebElement ListOfAppointment;
	
	
	public void ListOfAppointment() throws InterruptedException {
		gm.elementClick(ListOfAppointment);
	}
	
	public boolean checkBookedOrConfirm(String username) { 
		/*
		 * First Checks if the "Confirm" button is visible. 
		 * If found, the method skips further actions and returns false (order already confirmed).
		 * Second Check If the "Confirm" button is not found, it checks for the "Booked" button and clicks it.
		 * Then, it accepts any alert and Confirm the order.
		 * Return Values: If neither button is found, it prints a message and returns false.
		 */
	    String Booked = "//div//table//td[text()='"+username+"']//ancestor::tr/td/ion-button[1]";
	    String Confirm = "//div//table//td[text()='"+username+"']//ancestor::tr/td/span[text()='Confirm']";
	    
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    try {
	        WebElement ConfirmButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Confirm)));
	        if (ConfirmButton != null) {
	            System.out.println("Order is already confirmed. Skipping further actions.");
	            return false; 
	        }
	    } catch (Exception e) {
	        System.out.println("Confirm button not found. Checking for Booked button...");
	    }

	    try {
	        WebElement bookedButton = driver.findElement(By.xpath(Booked));
	        if (bookedButton != null) {
	            System.out.println("Booked button is available. Clicking the Booked button...");
	            wait.until(ExpectedConditions.elementToBeClickable(bookedButton)).click();
	            System.out.println("Booked button clicked successfully.");
	     
	            gm.acceptAlert(driver);
	            return true; 
	        }
	    } catch (NoSuchElementException e) {
	        System.out.println("Booked button not found.");
	    }
	    System.out.println("Neither Booked nor Confirm button is available.");
	    return false;  
	}
	
	public boolean isOrderConfirmed(String username) {
	    /*
	     * This method checks if the "Confirm" button is present for the given username. 
	     * If the button is found, it means the order is confirmed, and the method returns true.
	     * If the button is not found, the order is not confirmed, and the method returns false.
	     */
	    String confirmXPath = "//div//table//td[text()='"+username+"']//ancestor::tr/td/span[text()='Confirm']";
	    
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    try {
	        WebElement confirmButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(confirmXPath)));
	        
	        if (confirmButton != null) {
	            System.out.println("The order for " + username + " is confirmed.");
	            return true; 
	        }
	    } catch (NoSuchElementException e) {
	        System.out.println("The order for " + username + " is not confirmed (Confirm button not found).");
	        return false;  
	    } catch (Exception e) {
	        System.out.println("An error occurred while checking the order status: " + e.getMessage());
	        return false;
	    }
	    System.out.println("The order for " + username + " is not confirmed.");
	    return false;
	}






}