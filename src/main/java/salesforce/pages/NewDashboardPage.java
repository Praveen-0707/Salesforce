package salesforce.pages;

import salesforce.base.SalesforceBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class NewDashboardPage extends SalesforceBase {
	
	public NewDashboardPage(RemoteWebDriver driver)
	{
		this.driver = driver;
		try
		{
			Thread.sleep(5000);
			WebElement iframe = driver.findElementByXPath("(//iframe[@title='dashboard'])[last()]");
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("...Exception in Frames...");
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
		}
	}
	
	public NewDashboardPage inputDashboardDescr(String value)
	{
		WebElement dashboardDescr = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[@id='dashboardDescriptionInput' and @type='text']")));
		dashboardDescr.clear();
		dashboardDescr.sendKeys(value);
		return this;
	}
	
	public NewDashboardPage inputDashboardName(String value)
	{
		WebElement dashboardName = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//label[text()='Name']/following::div//input[@id='dashboardNameInput']")));
		dashboardName.clear();
		dashboardName.sendKeys(value);
		return this;
	}
		
	public NewDashboardPage clickOnCreateButton() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@id='submitBtn']"))).click();
		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		return this;
	}
	
	public NewDashboardPage clickonDoneButton()
	{
		WebElement clkDone = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[contains(@class,'doneEditing') and text()='Done']")));
		highlight(clkDone);
		clkDone.click();
		return this;
	}
	
	public ServiceConsolePage clickOnSubscribeButton()
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[text()='Subscribe']"))).click();
		return new ServiceConsolePage(driver);
	}
	
	public NewDashboardPage verifyDashboardTitle(String value) throws InterruptedException
	{
		WebElement TitleElement = driver.findElementByXPath("//div[@class='slds-page-header__name-title']//span[contains(text(),'Dashboard')]/following-sibling::span");
		wait.until(ExpectedConditions.visibilityOf(TitleElement));
		Thread.sleep(1000);
		String TitleVerification = TitleElement.getText();					
		if (!(TitleVerification.contains(value)))
		{
			throw new RuntimeException();
		}
		return this;
	}
}
