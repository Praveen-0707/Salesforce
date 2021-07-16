package salesforce.pages;

import salesforce.base.SalesforceBase;
import org.openqa.selenium.WebElement;

public class HomePage extends SalesforceBase {
	
	public HomePage()
	{
		this.driver = getDriver();
		driver.switchTo().defaultContent();
	}
	
	public HomePage clickToggleButton()
	{
		try {
			WebElement menuClk = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//div[@class='slds-icon-waffle']"));
			menuClk.click();
			reportStep("Clicked on Application Launcher", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Application Launcher", "Fail");
			e.printStackTrace();
		}
		return this;
	}
	
	public HomePage clickViewAll()
	{
		try {
			WebElement viewALL = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//button[text()='View All' and @class='slds-button']"));
			viewALL.click();
			reportStep("Clicked on View All from Application Launcher", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on View All from Application Launcher", "Fail");
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
			reportStep("Selected Task: "+ control +" from Navigation Menu on HomePage", "Pass");
			solidWait(3);
		} catch (Exception e) {
			reportStep("Unable to selected Task: "+ control +" from Navigation Menu on HomePage", "Fail");
			e.printStackTrace();
		}
		return this;
	}
	
	public HomePage searchApp(String value)
	{
		try {
			WebElement searchApp = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//input[@type='search' and @placeholder='Search apps or items...']"));
			searchApp.sendKeys(value);
			reportStep("Search for "+value+" application on HomePage", "Pass");
		} catch (Exception e) {
			reportStep("Unable to search for "+value+" application on HomePage", "Fail");
			e.printStackTrace();
		}
		return this;
	}
	
	public void clickOnApp(String appValue)
	{
		WebElement ele;
		try {
			ele = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//p//mark[contains(text(),'"+appValue+"')]"));
			ele.click();
			reportStep("Clicked on "+appValue+" Link", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on "+appValue+" Link", "Fail");
			e.printStackTrace();
		}
	}
	
	public AccountsPage clickOnAccount()
	{
		WebElement ele;
		try {
			ele = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//p//mark[contains(text(),'Account')]"));
			ele.click();
			reportStep("Clicked on Accounts Link", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Accounts Link", "Fail");
			e.printStackTrace();
		}
		return new AccountsPage();
	}
	
	public WorkTypeGroupsPage clickOnWorkTypeGroups()
	{
		try {
			WebElement WTG = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//p/mark[text()='Work Type Groups']"));
			WTG.click();
			reportStep("Clicked on WorkTypeGroups Link", "Pass");
		} catch (Exception e) {
			reportStep("unable to click on WorkTypeGroups Link", "Fail");
			e.printStackTrace();
		}
		return new WorkTypeGroupsPage();
	}
	
	public ServiceConsolePage clickOnServiceConsole()
	{
		try {
			WebElement serviceConsole = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//p/mark[text()='Service Console']"));
			serviceConsole.click();
			reportStep("Clicked on Service Console Link", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Service Console Link", "Fail");
			e.printStackTrace();
		}
		return new ServiceConsolePage();
	}
	
	public SalesPage clickOnSales()
	{
		try {
			WebElement Sales = webDriverWait4ElementToBeClickable(driver.findElementByXPath("(//p/mark[text()='Sales'])[last()-1]"));
			Sales.click();
			reportStep("Clicked on Sales Link", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Sales Link", "Fail");
			e.printStackTrace();
		}
		return new SalesPage();
	}
	
	public ServiceConsolePage clickOnContactRequest()
	{
		try {
			WebElement contact = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//p/mark[text()='Contact Request']"));
			contact.click();
			reportStep("Clicked on Contact Request Link", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Contact Request Link", "Fail");
			e.printStackTrace();
		}
		return new ServiceConsolePage();
	}
}
