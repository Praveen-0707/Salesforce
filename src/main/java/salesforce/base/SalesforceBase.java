package salesforce.base;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import salesforce.utils.*;
import io.github.bonigarcia.wdm.WebDriverManager;

// To access common methods in SalesForce Application
public class SalesforceBase {
	
	public ChromeDriver driver;
	public ChromeOptions chromeOptions;
//	public FirefoxOptions firefoxOptions;
//	public InternetExplorerOptions ieOptions;
//	public EdgeOptions edgeOptions;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public String excelFileName;
	public String excelSheetName;
	
	@BeforeMethod
	public void launchApp()
	{
		
		WebDriverManager.chromedriver().setup();
		chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		js = (JavascriptExecutor)driver;
		
		String URL = "https://login.salesforce.com/";
		driver.get(URL);
	}
	
	@AfterMethod
	public void closeBrowser()
	{
		driver.close();
	}
	
	 @DataProvider(name="getData")
	  public String[][] sendData() throws IOException
	  {
		  System.out.println("Inside Base data: " +excelFileName);
		  String[][] readData = ReadExcel.readData(excelFileName,excelSheetName);
		  return readData;
	  }
	 
	public void deletePopUpConfirmation()
	{
		WebElement clkDelete = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button//span[text()='Delete']")));
		clkDelete.click();
	}
}
