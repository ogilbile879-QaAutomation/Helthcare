package org.BaseUtility;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class startUp {
	
	
	 
	public void ScrollbBy(WebElement ele, int Scrolby) {
		for (int i = 0; i < Scrolby; i++) {
			ele.sendKeys(Keys.PAGE_DOWN);
		}

	}

	public void Scrollbyjs(WebElement ele, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", ele);

	}
	
	public String GeTAttributePassByClass(WebElement ele) {

		return ele.getDomAttribute("class");
	}

	public String GeTAttributePassById(WebElement ele) {
		return ele.getDomAttribute("id");

	}

	public String Gettext(WebElement ele) {
		return ele.getText();
	}

	
	public ArrayList<String> getAlltabNames(List<WebElement>allEle)
	{
		ArrayList<String> al =new ArrayList<String>();
		
		for(int i=0;i<allEle.size();i++)
		{
			
			al.add(allEle.get(i).getText());
		}
		return al;
		
	}

	
	public WebDriver startUp(String bname, String Url) {

		WebDriver driver = null;
		
		 WebDriverManager.chromedriver().browserVersion("136.0.7103.114").setup();

		if (bname.equalsIgnoreCase("ch") || bname.equalsIgnoreCase("Chrome")) {
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--remote-allow-origins=*");
			option.addArguments("start-maximized");
			option.addArguments("--disable-notifications");
			
			
			driver = new ChromeDriver(option);

		} else if (bname.equalsIgnoreCase("ff") || bname.equalsIgnoreCase("Firefox")) {
			FirefoxOptions option = new FirefoxOptions();
			option.addArguments("start-maximized");
			option.addArguments("--disable-notifications");
			driver = new FirefoxDriver();

		} else if (bname.equalsIgnoreCase("ed") || bname.equalsIgnoreCase("edge")) {
			EdgeOptions option = new EdgeOptions();
			option.addArguments("start-maximized");
			driver = new EdgeDriver();

		} else {
			System.out.println("input not match");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(Url);

		return driver;

	}
	
	public WebDriver Hiturl(String Url) {

		WebDriver driver = null;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(Url);

		return driver;
		

	}
	
	public ArrayList<String>getAllTextFromList(List<WebElement> allEles) {
		
		 ArrayList<String> ar = new ArrayList<String>();
		 for(int i=0; i<allEles.size();i++) {
			ar.add(allEles.get(i).getText());
		 }
		 return ar;
	}
	
	public ArrayList<String>getAllTextFromList2(List<WebElement> allEles) {
		
		 ArrayList<String> ar = new ArrayList<String>();
		 for(int i=0; i<=allEles.size()-1;i++) {
			ar.add(allEles.get(i).getText());
		 }
		 return ar;
	}

	public WebDriver initalize(String bname) {

		WebDriver driver = null;

		if (bname.equalsIgnoreCase("ch") || bname.equalsIgnoreCase("Chrome")) {
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--remote-allow-origins=*");
			option.addArguments("start-maximized");
			driver = new ChromeDriver(option);

		} else if (bname.equalsIgnoreCase("ff") || bname.equalsIgnoreCase("Firefox")) {
			FirefoxOptions option = new FirefoxOptions();
			option.addArguments("start-maximized");
			driver = new FirefoxDriver();

		} else if (bname.equalsIgnoreCase("ed") || bname.equalsIgnoreCase("edge")) {
			EdgeOptions option = new EdgeOptions();
			option.addArguments("start-maximized");
			driver = new EdgeDriver();

		} else {
			System.out.println("input not match");
		}

		// driver.manage().window().maximize();
		// it is work on selenium 3.0
		// driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		// it is work on selenium 4.0 on word
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		

		return driver;

	}


	}


