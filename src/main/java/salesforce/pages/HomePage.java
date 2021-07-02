package salesforce.pages;

import salesforce.base.SalesforceBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

public class HomePage extends SalesforceBase {
	
	public HomePage(RemoteWebDriver driver, ExtentTest node)
	{
		this.driver = driver;
		this.node = node;
	}
	
	public HomePage clickToggleButton()
	{
		try {
			WebElement menuClk = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//div[@class=\"slds-icon-waffle\"]"));
			menuClk.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public HomePage clickViewAll()
	{
		try {
			WebElement viewALL = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//button[text()='View All' and @class='slds-button']"));
			viewALL.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public HomePage selectTaskFromNavigationControl(String control)
	{
		try {
			WebElement dd_tasks = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//button[@title='Show Navigation Menu']"));
			dd_tasks.click();
			solidWait(9);
			WebElement selectTask = webDriverWait4ElementToBeClickable(driver.findElementByXPath("(//a[@title='"+control+"'])[last()]"));
			scrollToVisibleElement(selectTask);
			selectTask.click();
			solidWait(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public HomePage searchApp(String value)
	{
		try {
			WebElement searchApp = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//input[@type='search' and @placeholder='Search apps or items...']"));
			searchApp.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AccountsPage clickOnAccount()
	{
		try {
			WebElement Accounts = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//p//mark[contains(text(),'Account')]"));
			Accounts.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AccountsPage(driver, node);
	}
	
	public WorkTypeGroupsPage clickOnWorkTypeGroups()
	{
		try {
			WebElement WTG = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//p/mark[text()='Work Type Groups']"));
			WTG.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new WorkTypeGroupsPage(driver, node);
	}
	
	public ServiceConsolePage clickOnServiceConsole()
	{
		try {
			WebElement serviceConsole = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//p/mark[text()='Service Console']"));
			serviceConsole.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceConsolePage(driver, node);
	}
	
	public SalesPage clickOnSales()
	{
		try {
			WebElement Sales = webDriverWait4ElementToBeClickable(driver.findElementByXPath("(//p/mark[text()='Sales'])[last()-1]"));
			Sales.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SalesPage(driver, node);
	}
}
