package salesforce.pages;

import salesforce.base.SalesforceBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ServiceConsolePage extends SalesforceBase {
	
	public ServiceConsolePage(ChromeDriver driver)
	{
		this.driver = driver;
	}
	
	public ServiceConsolePage clickOnNewDashboard()
	{
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[text()='New Dashboard']"))).click();
		return this;
	}
	
	public ServiceConsolePage inputDashboardDescr(String value)
	{
		WebElement dashboardDescr = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[@id='dashboardDescriptionInput' and @type='text']")));
		dashboardDescr.clear();
		dashboardDescr.sendKeys(value);
		return this;
	}
	
	public ServiceConsolePage inputDashboardName(String value)
	{
		WebElement dashboardName = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//label[text()='Name']/following::div//input[@id='dashboardNameInput']")));
		dashboardName.clear();
		dashboardName.sendKeys(value);
		return this;
	}
	
	public ServiceConsolePage selectTask(String value) throws InterruptedException
	{
		WebElement dd_tasks = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@title='Show Navigation Menu']")));
		dd_tasks.click();
		Thread.sleep(1000);
		WebElement selectTask = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//a[@title='"+value+"'])[last()]")));
		selectTask.click();
		Thread.sleep(1000);
		return this;
	}
	
	public ServiceConsolePage clickOnCreateButton()
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@id='submitBtn']"))).click();
		return this;
	}
	
	public ServiceConsolePage clickonDoneButton()
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[contains(@class,'doneEditing') and text()='Done']"))).click();
		return this;
	}
	
	public ServiceConsolePage clickOnSubscribeButton()
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='Subscribe']"))).click();
		return this;
	}
	
	public ServiceConsolePage selectTime(String value) throws InterruptedException
	{
		WebElement dd_time = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//select[@id='time']")));
		Select time = new Select(dd_time);
		time.selectByVisibleText(value);
		return this;
	}
	
	public ServiceConsolePage clickonSaveButton()
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@title='Save']"))).click();
		return this;
	}
	
	public ServiceConsolePage clickonPrivateDashboard()
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//a[@title='Private Dashboards'])[1]"))).click();
		return this;
	}
	
	public ServiceConsolePage searchDashboardName(String value) throws InterruptedException
	{
		WebElement searchDashboard = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[contains(@class,'search-text')]")));
		searchDashboard.clear();
		searchDashboard.sendKeys(value);
		searchDashboard.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		return this;
	}
}
