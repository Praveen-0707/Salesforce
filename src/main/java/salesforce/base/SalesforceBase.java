package salesforce.base;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
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
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import com.aventstack.extentreports.ExtentTest;
import salesforce.utils.*;
import io.github.bonigarcia.wdm.WebDriverManager;

// To access common methods in SalesForce Application
public class SalesforceBase extends Reporter {
	
	public RemoteWebDriver driver;
//	public EventFiringWebDriver driver;
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
//	public static ExtentHtmlReporter reporter;
//	public static ExtentReports extent;
	public ExtentTest testNode;
//	public String testName, testDescription, testAuthor, testCategory;
	public String browser;
	
//	@BeforeMethod(priority = 1)
//	public void init_testNode()
//	{
//		testNode = test.createNode(testName);
//		setTest(testNode);
//	}
	
	@BeforeMethod
	public void launchApp()
	{
//		killBrowserInstances();
//		Properties File Load
		testNode = test.createNode(testName);
		setTest(testNode);
		try {
			FileInputStream fis = new FileInputStream("./src/main/resources/config.properties");
			prop = new Properties();
			prop.load(fis);
			
//			browser = prop.getProperty(browser);
			if (browser.equalsIgnoreCase("Chrome"))
			  {
				  WebDriverManager.chromedriver().setup();
				  chromeOptions = new ChromeOptions();
				  chromeOptions.addArguments("--disable-notifications");
				  chromeOptions.addArguments("--disable-notifications");
//				  chromeOptions.addArguments("--headless");
//				  chromeOptions.setHeadless(true);
				  driver = new ChromeDriver(chromeOptions);
//				  driver = new EventFiringWebDriver(webDriver);
//				  getDriver().register((WebDriverEventListener) this);
				  setDriver(driver);
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
//				  firefoxOptions.addArguments("headless");
//				  firefoxOptions.setHeadless(true);
				  firefoxOptions.setProfile(profile);
				  firefoxOptions.addPreference("dom.webnotifications.enabled", false);
				  driver = new FirefoxDriver(firefoxOptions);
				  setDriver(driver);
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
				  setDriver(driver);
			  }
  
			  if (browser.equalsIgnoreCase("Edge"))
			  {
				  WebDriverManager.edgedriver().setup();
				  edgeOptions = new EdgeOptions();
				  edgeOptions.addArguments("disable-notifications");
				  driver = new EdgeDriver(edgeOptions);
				  setDriver(driver);
			  }
			  
			getDriver().manage().deleteAllCookies();
			getDriver().manage().window().maximize();
			wait = new WebDriverWait(getDriver(),Duration.ofSeconds(20));
			getDriver().manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			getDriver().manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
			
			actions = new Actions(getDriver());
			robot = new Robot();
			js = (JavascriptExecutor)getDriver();
			
//			String URL = "https://login.salesforce.com/";
			getDriver().get(prop.getProperty("url"));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
		
	@AfterMethod(alwaysRun = true)
	public void closeBrowser() throws InterruptedException
	{
		solidWait(2);
		getDriver().close();
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
		try {
			WebElement clkDelete = getDriver().findElementByXPath("//button//span[text()='Delete']");
			webDriverWait4ElementToBeClickable(clkDelete);
			clkDelete.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void scrollToVisibleElement(WebElement ele)
	{
		try {
			js.executeScript("arguments[0].scrollIntoView();", ele);
		} catch (JavascriptException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void scrollToPageEnd()
	{
		try {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		} catch (JavascriptException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void highlight(WebElement ele)
	{
		try {
			js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');",ele);
		} catch (JavascriptException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void highlightElement(WebElement ele)
	{
		try {
			js.executeScript("arguments[0].style.border='3px solid red'",ele);
		} catch (JavascriptException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			e.printStackTrace();
		}
	}
	
	public static void copyFilefromSourceToDestinationFolder(String srcFolder,
			String fileName, String fileExtension, String destFolder)
	{
		try {
			File srcFile = new File(srcFolder+fileName+"."+fileExtension+"");
			File destFile = new File(destFolder);
			FileUtils.copyFileToDirectory(srcFile,destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		try {
			WebElement ele = getDriver().findElementByXPath("//button[contains(@title,'"+value+"') and contains(@title,'Close')]");
			webDriverWait4ElementToBeClickable(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void solidWait(Integer sec)
	{
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
	
	public WebElement webDriverWait4VisibilityOfEle(WebElement ele){
		try {
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(ele));
		} catch(StaleElementReferenceException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			e.printStackTrace();			
		} catch (TimeoutException e) {
			e.printStackTrace();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ele;
	}
	
	public WebElement webDriverWait4ElementToBeClickable(WebElement ele){
		try {
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(ele));
		} catch(StaleElementReferenceException e) {
			e.printStackTrace();
		} catch (NoSuchElementException e) {
			e.printStackTrace();			
		} catch (TimeoutException e) {
			e.printStackTrace();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ele;
	}
	
	public String getRandomString(Integer length)
	{
		String randomString = "";
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		char[] text = new char[length];
		for (int i=0; i<length; i++)
		{
			text[i] = characters.charAt(random.nextInt(characters.length()));
			randomString += text[i];
		}
		return randomString;
	}
	
	public void clickOnTab(String value)
	{
		try {
			WebElement ele = getDriver().findElementByXPath("//a[@title='"+value+"']");
			webDriverWait4ElementToBeClickable(ele);
			js.executeScript("arguments[0].click();", ele);
		} catch (JavascriptException e) {
			e.printStackTrace();
		}
	}
	
	public void uploadAttachment(String fileLocation)
	{
		try {
			StringSelection up = new StringSelection(fileLocation);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(up, null);
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);

			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			solidWait(3);
//			click on the Done button to dismiss the alert
			WebElement ele = getDriver().findElement(By.xpath("//button[@type='button']//span[text()='Done']"));
			webDriverWait4VisibilityOfEle(ele);
			ele.click();
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
