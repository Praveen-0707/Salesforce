package salesforce.pages;

import salesforce.base.SalesforceBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.ExtentTest;

public class NewDashboardPage extends SalesforceBase {
	
	public NewDashboardPage(RemoteWebDriver driver, ExtentTest node)
	{
		this.driver = driver;
		this.node = node;
		try
		{
			solidWait(5);
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
		try {
			WebElement dashboardDescr = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//input[@id='dashboardDescriptionInput' and @type='text']"));
			dashboardDescr.clear();
			dashboardDescr.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public NewDashboardPage inputDashboardName(String value)
	{
		try {
			WebElement dashboardName = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//label[text()='Name']/following::div//input[@id='dashboardNameInput']"));
			dashboardName.clear();
			dashboardName.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
		
	public NewDashboardPage clickOnCreateButton()
	{
		try {
			webDriverWait4ElementToBeClickable(driver.findElementByXPath("//button[@id='submitBtn']")).click();
			solidWait(3);
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public NewDashboardPage clickonDoneButton()
	{
		try {
			WebElement clkDone = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//button[contains(@class,'doneEditing') and text()='Done']"));
			highlight(clkDone);
			clkDone.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage clickOnSubscribeButton()
	{
		try {
			webDriverWait4ElementToBeClickable(driver.findElementByXPath("//button[text()='Subscribe']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceConsolePage(driver, node);
	}
	
	public NewDashboardPage verifyDashboardTitle(String value)
	{
		try {
			WebElement TitleElement = driver.findElementByXPath("//div[@class='slds-page-header__name-title']//span[contains(text(),'Dashboard')]/following-sibling::span");
			webDriverWait4VisibilityOfEle(TitleElement);
			solidWait(1);
			String TitleVerification = TitleElement.getText();					
			if (!(TitleVerification.contains(value)))
			{
				throw new RuntimeException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
}
