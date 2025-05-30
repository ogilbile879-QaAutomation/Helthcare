package helthcare;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import org.BaseUtility.startUp;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DashBoard {

	// public static ChromeOptions options = new ChromeOptions();
	public static WebDriver driver;
	static startUp bu;

	public static WebDriverWait wait;
	public static XSSFSheet patientNameSheet;
	public static XSSFSheet womanNameSheet;
	public static List<String> Adress = new ArrayList<>();

//	@Test(priority=1,testName="Open Application")
	public static void main() throws InterruptedException, IOException {
		Properties properties = new Properties();

//		BatchFile batch = new BatchFile();
//		batch.readCsvFile();
//		List<String> Adress = new ArrayList<>();
		String projectPath = System.getProperty("user.dir");
		// System.setProperty("webdriver.chrome.driver", projectPath +
		// "/chromedriver.exe");
//		WebDriverManager.chromedriver().setup();
		File passwordFile = new File(projectPath + "/src/test/Resources/login.properties");
		FileInputStream fis = new FileInputStream(passwordFile);
		properties.load(fis);

		//Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

		bu = new startUp();
		driver = bu.startUp("chrome", "https://staff.hmis.beta.thsystems.net.in/");

		// Read properties
//         String username = properties.getProperty("username");
//         String password = properties.getProperty("password");

		// Print properties
//         System.out.println("Username: " + username);
//         System.out.println("Password: " + password);

//		System.out.println("password"+password);
//		 ChromeOptions options = new ChromeOptions();
		/*
		 * options.addArguments("--headless");
		 * 
		 * driver = new ChromeDriver(options); //
		 * options.setPageLoadStrategy(PageLoadStrategy.NORMAL); // driver = new
		 * ChromeDriver();
		 * 
		 * // options.addArguments("--log-level=3");
		 * driver.get("https://staff.hmis.beta.thsystems.net.in/login"); //
		 * driver.get("http://10.10.1.115:8080/HIMS/#/login");
		 * driver.manage().window().maximize(); Thread.sleep(1000);
		 */
		// Set Chrome options
		LocalDateTime currentTime = LocalDateTime.now();

		// Format the time to a readable format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);

		// Print the current time above the module line
		System.out.println("Current Time: " + formattedTime);
		System.out.println("Module : OPD");
		loginPage loginPage = new loginPage();

		loginPage.getUserCredentials(driver);
		loginPage.performLogin(driver);
//		 Thread.sleep(5000);
//		WebElement opd = driver.findElement(By.xpath("//span[text()=\"Registration\"]"));
//		opd.click();
//		WebDriverWait wait = new WebDriverWait(driver, 25);
//		WebElement opd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Registration']")));
//		opd.click();
//		Thread.sleep(4000);
//		WebElement closecc=driver.findElement(By.xpath("//ion-button[text()=\"Close\"]"));
//		closecc.click();
//		 Thread.sleep(2000);
//		 WebElement opd=driver.findElement(By.xpath(" //span[text()=\" OPD \"]"));
//		 opd.click();
		int maxRetries = 5;
		// Delay between retries in milliseconds
		int retryDelay = 3000;
		boolean opdClick = retryClick(driver, By.xpath(" //span[text()=\" OPD \"]"), maxRetries, retryDelay);

		if (opdClick) {
//	          System.out.println("Login button clicked successfully.");
		} else {
//	          System.out.println("Failed to click the login button after " + maxRetries + " retries.");
		}
		// Retry clicking the login button
		boolean success = retryClick(driver, By.xpath("//h3[text()=\"OPD Registration\"]"), maxRetries, retryDelay);

		if (success) {
//	          System.out.println("Login button clicked successfully.");
		} else {
//	          System.out.println("Failed to click the login button after " + maxRetries + " retries.");
		}
		Thread.sleep(2000);
		WebElement clickOnseeMore = driver.findElement(By.xpath("//ion-button[text()=\" See More \"]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickOnseeMore);
		clickOnseeMore.click();
		Thread.sleep(2000);
		int sampleSheetNumber;
//			String projectPath = System.getProperty("user.dir");
		File src = new File(projectPath + "/SAMPLEDATA.xlsx");
//			System.out.println("projectPath :"+projectPath);
		FileInputStream fisSampleData = new FileInputStream("src/test/resources/SAMPLEDATA.xlsx");

		XSSFWorkbook xssfSampleData = new XSSFWorkbook(fisSampleData);
		XSSFSheet sampleDataSheet = xssfSampleData.getSheetAt(0);
		sampleSheetNumber = xssfSampleData.getNumberOfSheets();
		patientNameSheet = xssfSampleData.getSheetAt(0);
		womanNameSheet = xssfSampleData.getSheetAt(1);

		String adressPath = System.getProperty("user.dir");
		//File add = new File(adressPath + "/Adress.xlsx");
//			System.out.println("adressPath :"+adressPath);
		FileInputStream adressData = new FileInputStream("src/test/resources/Adress.xlsx");

		XSSFWorkbook xssfSampleData1 = new XSSFWorkbook(adressData);
		XSSFSheet AdressSheet = xssfSampleData1.getSheetAt(0);
//			sampleSheetNumber = xssfSampleData.getNumberOfSheets();
//			XSSFSheet patientNameSheet = xssfSampleData.getSheetAt(0);
//			XSSFSheet womanNameSheet = xssfSampleData.getSheetAt(1);
		for (int M = 1; M <= AdressSheet.getLastRowNum(); M++) {
			Row rowA = AdressSheet.getRow(M);
			if (rowA != null) {
				Cell addressCell = rowA.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

				if (addressCell.getCellType() == CellType.ERROR) {
					// Handle error cell (optional)
					continue;
				}

				String city = getStringValueFromCell(addressCell);
				if (city != null) {
					Adress.add(city);
				}
			}
		}
//		newPatient newPatient=new newPatient();
//		newPatient.newRegistration(driver,patientNameSheet,womanNameSheet,Adress);

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

	public static boolean retryClick(WebDriver driver, By locator, int maxRetries, int retryDelay) {
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

	public void sleep(int i) {
		// TODO Auto-generated method stub

	}

}
