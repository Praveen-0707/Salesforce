package salesforce.pages;

import salesforce.base.SalesforceBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends SalesforceBase {
	
	public HomePage(RemoteWebDriver driver)
	{
		this.driver = driver;
	}
	
	public HomePage clickToggleButton()
	{
		WebElement menuClk = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//div[@class=\"slds-icon-waffle\"]")));
		menuClk.click();
		return this;
	}
	
	public HomePage clickViewAll()
	{
		WebElement viewALL = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='View All' and @class='slds-button']")));
		viewALL.click();
		return this;
	}
	
	public HomePage selectTaskFromNavigationControl(String control) throws InterruptedException
	{
		WebElement dd_tasks = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@title='Show Navigation Menu']")));
		dd_tasks.click();
		Thread.sleep(9000);
		WebElement selectTask = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@title='"+control+"'])[last()]")));
		scrollToVisibleElement(selectTask);
		selectTask.click();
		Thread.sleep(3000);
		return this;
	}
	
	public HomePage searchApp(String value)
	{
		WebElement searchApp = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[@type='search' and @placeholder='Search apps or items...']")));
		searchApp.sendKeys(value);
		return this;
	}
	
	public AccountsPage clickOnAccount()
	{
		WebElement Accounts = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//p//mark[contains(text(),'Account')]")));
		Accounts.click();
		return new AccountsPage(driver);
	}
	
	public WorkTypeGroupsPage clickOnWorkTypeGroups()
	{
		WebElement WTG = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//p/mark[text()='Work Type Groups']")));
		WTG.click();
		return new WorkTypeGroupsPage(driver);
	}
	
	public ServiceConsolePage clickOnServiceConsole()
	{
		WebElement serviceConsole = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//p/mark[text()='Service Console']")));
		serviceConsole.click();
		return new ServiceConsolePage(driver);
	}
	
	public SalesPage clickOnSales()
	{
		WebElement Sales = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//p/mark[text()='Sales'])[last()-1]")));
		Sales.click();
		return new SalesPage(driver);
	}
}
