package helthcare;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class loginPage {
//	public static String password;
//	public static String username ;
//	@Test(dataProvider = "userCredentials")
	public static   List<Object[]> credentials = new ArrayList<>();
	private static final Random random = new Random();
	
    public static void performLogin(WebDriver driver) throws InterruptedException {
        // Enter Username
    	 synchronized (credentials) { // Ensure thread-safe access to credentials
             if (credentials.isEmpty()) {
                 System.out.println("No credentials available.");
                 return;
             }

             int randomIndex = random.nextInt(credentials.size()-1);
             Object[] selectedCredential = credentials.remove(randomIndex); // Remove after selection

             if (selectedCredential != null && selectedCredential.length >= 2) {
                 String username = (String) selectedCredential[0];
                 String password = (String) selectedCredential[1];
//                 System.out.println("username: " + username);
//                 System.out.println("password: " + password);

                 // Check if username and password are non-null
                 if (username != null && password != null) {
                     WebElement userField = driver.findElement(By.id("ion-input-0"));
                     userField.clear();
                     userField.sendKeys("SMBT-08-Mahendrab");

                     WebElement passField = driver.findElement(By.id("ion-input-1"));
                     passField.clear();
                     passField.sendKeys("Smbt@111");
                     Thread.sleep(2000);
//                     WebElement image=driver.findElement(By.xpath("//img[@src=\"assets/imges/logo.png\"]"));
//                     image.click();
                     Thread.sleep(2000);
                 } else {
                     System.out.println("Username or password is null.");
                 }
             } else {
                 System.out.println("Selected credentials are invalid.");
             }
             

             driver.manage().timeouts().implicitlyWait(11, TimeUnit.SECONDS);
             credentials.remove(randomIndex);
//             try {
////               WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//               WebDriverWait wait = new WebDriverWait(driver, 20);
//               wait.until(ExpectedConditions.alertIsPresent()); // Wait for alert
//
//               Alert alert = driver.switchTo().alert();
//               System.out.println("Alert Text: " + alert.getText()); // Print alert text
//
//               alert.accept(); // Click OK (Accept the alert)
//               // alert.dismiss(); // Use this if you want to click Cancel instead
//
//           } catch (NoAlertPresentException e) {
//               System.out.println("No alert found, continuing execution...");
//           }
             // Click the login button with retry
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//      String XpathloginButton="//ion-button[@type=\"submit\"]";
      int maxRetries = 5;
      // Delay between retries in milliseconds
      int retryDelay = 3000;

      // Retry clicking the login button
      boolean success = retryClick(driver, By.xpath("//ion-button[@type='submit']"), maxRetries, retryDelay);

      if (success) {
//          System.out.println("Login button clicked successfully.");
      } else {
//          System.out.println("Failed to click the login button after " + maxRetries + " retries.");
      }
    	 }  
    	 
    	 
    }

    public static boolean retryClick(WebDriver driver,By locator, int maxRetries, int retryDelay) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                element.click();
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
    
//				    @DataProvider(name = "userCredentials")
				   
				    public static Object[][] getUserCredentials(WebDriver driver) throws IOException {
				        Properties props = new Properties();
						/*
						 * String projectPath = System.getProperty("user.dir"); File add = new
						 * File(projectPath +"/login.xlsx"); //
						 * System.out.println("adressPath :"+adressPath);
						 */					
				        FileInputStream loginData = new FileInputStream("src/test/resources/login.xlsx");

						XSSFWorkbook loginData1 = new XSSFWorkbook(loginData);
						XSSFSheet loginSheet = loginData1.getSheetAt(0);
				
				        // Extract usernames and passwords
//				        XSSFWorkbook xssfSampleData = new XSSFWorkbook(input);
				        for (int i = 1; i <= loginSheet.getLastRowNum(); i++) {
				        	Row rowA = loginSheet.getRow(i);
				        	String user = null;
				        	String pass = null;
				        	 if (rowA != null) {
				                    Cell usernameCell = rowA.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

				                    if (usernameCell.getCellType() == CellType.ERROR) {
				                        // Handle error cell (optional)
				                        continue;
				                    }

				                     user = getStringValueFromCell(usernameCell);
				                    Cell passwordCell = rowA.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				                    if (passwordCell.getCellType() == CellType.ERROR) {
				                        // Handle error cell (optional)
				                        continue;
				                    }
				                     pass = getStringValueFromCell(passwordCell);
				                }
//				           String  username = props.getProperty("user" + i + ".username");
//				           String  password = props.getProperty("user" + i + ".password");
				            credentials.add(new Object[]{user, pass});
				        }
				        return credentials.toArray(new Object[0][]);
				    }
				    private static String getStringValueFromCell(Cell cell) {
				        switch (cell.getCellType()) {
				            case STRING:
				                return cell.getStringCellValue();
				            case NUMERIC:
				                if (DateUtil.isCellDateFormatted(cell)) {
				                    // Handle date formatted cell if necessary
				                    return cell.getDateCellValue().toString();
				                } else {
				                    // Convert numeric cell to string
				                    return String.valueOf(cell.getNumericCellValue());
				                }
				            case BOOLEAN:
				                return String.valueOf(cell.getBooleanCellValue());
				            case FORMULA:
				                return cell.getCellFormula();
				            case BLANK:
				                return "";
				            default:
				                return null;
				        }
				    }

}



