package salesforce.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import salesforce.pages.SalesPage;
import salesforce.pages.ServiceConsolePage;
import salesforce.utils.*;
import io.github.bonigarcia.wdm.WebDriverManager;

// To access common methods in SalesForce Application
public class SalesforceBase {
	
	public RemoteWebDriver driver;
//	public ChromeDriver driver;
	public ChromeOptions chromeOptions;
	public FirefoxOptions firefoxOptions;
	public InternetExplorerOptions ieOptions;
	public EdgeOptions edgeOptions;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions actions;
	public static Robot robot;
	public String excelFileName;
	public String excelSheetName;
	public Properties prop;
	
	public static String browser;
	public static SessionId browserSession;
	
	
	@BeforeMethod
	public void launchApp() throws IOException, AWTException
	{
		killBrowserInstances();
		
//		Properties File Load
		FileInputStream fis = new FileInputStream("./src/main/resources/config.properties");
		prop = new Properties();
		prop.load(fis);
		
		if (browser.equalsIgnoreCase("Chrome"))
		  {
			  WebDriverManager.chromedriver().setup();
			  chromeOptions = new ChromeOptions();
			  chromeOptions.addArguments("--disable-notifications");
	//		  chromeOptions.addArguments("--headless");
	//		  chromeOptions.setHeadless(true);
			  driver = new ChromeDriver(chromeOptions);
//			  browserSession = driver.getSessionId();
//			  browser = browserSession.toString();
		  }
	  
		  if (browser.equalsIgnoreCase("Firefox"))
		  {
			  WebDriverManager.firefoxdriver().setup();
			  FirefoxProfile profile = new FirefoxProfile();
			  profile.setPreference("app.update.auto", false);
			  profile.setPreference("app.update.enabled", false);
			  profile.setAcceptUntrustedCertificates(false);
			  profile.setPreference("browser.shell.checkDefaultBrowser", false);
			  
			  firefoxOptions = new FirefoxOptions();
	//		  firefoxOptions.addArguments("headless");
	//		  firefoxOptions.setHeadless(true);
			  firefoxOptions.setProfile(profile);
			  firefoxOptions.addPreference("dom.webnotifications.enabled", false);
			  driver = new FirefoxDriver(firefoxOptions);
		  }
		  
		  if (browser.equalsIgnoreCase("InternetExplorer"))
		  {
			  WebDriverManager.iedriver().setup();
			  ieOptions = new InternetExplorerOptions();
			  ieOptions.disableNativeEvents();
			  ieOptions.requireWindowFocus();
			  ieOptions.ignoreZoomSettings();
			  ieOptions.introduceFlakinessByIgnoringSecurityDomains();
			  driver = new InternetExplorerDriver(ieOptions);
		  }
	  
		  if (browser.equalsIgnoreCase("Edge"))
		  {
			  WebDriverManager.edgedriver().setup();
			  edgeOptions = new EdgeOptions();
			  edgeOptions.addArguments("disable-notifications");
			  driver = new EdgeDriver(edgeOptions);
		  }
		  
		  driver.manage().deleteAllCookies();
		  driver.manage().window().maximize();
		  wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		  driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		  driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		actions = new Actions(driver);
		robot = new Robot();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		js = (JavascriptExecutor)driver;
		
//		String URL = "https://login.salesforce.com/";
		driver.get(prop.getProperty("url"));
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeBrowser() throws InterruptedException
	{
		Thread.sleep(2000);
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
	
	public void scrollToVisibleElement(WebElement ele)
	{
		js.executeScript("arguments[0].scrollIntoView();", ele);
	}
	
	public void scrollToPageEnd()
	{
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void highlight(WebElement ele)
	{
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');",ele);
	}
	
	public void killBrowserInstances()
	{
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("taskkill /F /IM ChromeDriver.exe /T");
			System.out.println("...Killing existing browser instances...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void takeScreenshotOnFailed(String methodName)
	{
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File("./screenshots/failedStep_"
													+ methodName + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void copyFilefromSourceToDestinationFolder(String fileName) throws IOException, InterruptedException
	{
		File srcFile = new File("C:\\Users\\HP\\Downloads\\"+fileName+".pdf"+"");
		File destFile = new File("E:\\Documents\\SeleniumBootcamp\\");
		Thread.sleep(3000);
		FileUtils.copyFileToDirectory(srcFile,destFile);
	}
	
	public boolean isDisabled(WebElement element)
	{
		boolean displayed = element.isDisplayed();
		
		if (displayed)
		{
			return true;
		}
		else
		{
			return displayed;
		}
	}
	
	public void closeAnOpenedTab(String value)
	{
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[contains(@title,'"+value+"') and contains(@title,'Close')]")));
		ele.click();
	}
	
	public void clickOnTab(String value)
	{
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='"+value+"']")));
		js.executeScript("arguments[0].click();", ele);
	}
	
	public void uploadAttachment(String fileLocation) throws InterruptedException
	{
		StringSelection up = new StringSelection(fileLocation);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(up, null);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
		
//		click on the Done button to dismiss the alert
		WebElement ele = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@type='button']//span[text()='Done']"))));
		ele.click();
	}
}
