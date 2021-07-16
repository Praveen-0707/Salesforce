package salesforce.pages;

import salesforce.base.SalesforceBase;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebElement;

public class NewDashboardPage extends SalesforceBase {
	
	public NewDashboardPage()
	{
		this.driver = getDriver();
		try
		{
			solidWait(5);
			WebElement iframe = driver.findElementByXPath("(//iframe[@title='dashboard'])[last()]");
			webDriverWait4FrameToBeAvailableAndSwitchTo(iframe);
			reportStep("Switched to Frame", "Pass");
		}
		catch(NoSuchFrameException e)
		{
			e.printStackTrace();
			System.out.println("...Exception in Frames...");
			webDriverWait4FrameToBeAvailableAndSwitchTo(1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("...Exception in Frames...");
			reportStep("Unable to switched to Frame", "Fail");
		}
	}
	
	public NewDashboardPage inputDashboardDescr(String value)
	{
		try {
			WebElement dashboardDescr = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//input[@id='dashboardDescriptionInput' and @type='text']"));
			dashboardDescr.clear();
			dashboardDescr.sendKeys(value);
			reportStep("Enter Dashboard Description: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to enter Dashboard Description: "+value, "Fail");
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
			reportStep("Enter DashboardName: "+value, "Pass");
		} catch (Exception e) {
			reportStep("Unable to enter DashboardName: "+value, "Fail");
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
			reportStep("Clicked on Create Button", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Create Button", "Fail");
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
			reportStep("Clicked on Done Button", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Done Button", "Fail");
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage clickOnSubscribeButton()
	{
		try {
			webDriverWait4ElementToBeClickable(driver.findElementByXPath("//button[text()='Subscribe']")).click();
			reportStep("Clicked on Subscribe Button", "Pass");
		} catch (Exception e) {
			reportStep("Unable to click on Subscribe Button", "Fail");
			e.printStackTrace();
		}
		return new ServiceConsolePage();
	}
	
	public NewDashboardPage verifyDashboardTitle(String value)
	{
		try {
			WebElement TitleElement = driver.findElementByXPath("//div[@class='slds-page-header__name-title']//span[contains(text(),'Dashboard')]/following-sibling::span");
			webDriverWait4VisibilityOfEle(TitleElement);
			solidWait(1);
			String TitleVerification = TitleElement.getText();					
			if (TitleVerification.contains(value))
			{
				reportStep("Verified Dashboard title"+value, "Fail");
			}
			else
			{
				reportStep("Unable to verify Dashboard title", "Fail");
//				throw new RuntimeException();
			}
		} catch (Exception e) {
			reportStep("Unable to verify Dashboard title", "Fail");
			e.printStackTrace();
		}
		return this;
	}
}
