package salesforce.pages;

import salesforce.base.SalesforceBase;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SalesPage extends SalesforceBase {
	
	public SalesPage(ChromeDriver driver)
	{
		this.driver = driver;
	}
	
	public SalesPage clickOnCampaignTab()
	{
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='Campaigns']")));
		js.executeScript("argument[0].click();", ele);
		return this;
	}
	
	public SalesPage clickOnLeadsTab()
	{
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='Leads']")));
		js.executeScript("argument[0].click();", ele);
		return this;
	}
	
	public SalesPage searchCampaign(String value) throws InterruptedException
	{
		WebElement searchCampaign = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByName("Campaign-search-input")));
		searchCampaign.clear();
		searchCampaign.sendKeys(value);
		searchCampaign.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		return this;
	}
	
	public SalesPage searchLead(String value) throws InterruptedException
	{
		WebElement searchLead = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByName("Lead-search-input")));
		searchLead.clear();
		searchLead.sendKeys(value);
		searchLead.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		return this;
	}
	
	public SalesPage selectLead(String value) throws InterruptedException
	{
		WebElement dd_lead = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//lightning-icon[contains(@class,'inputLookupIcon')]")));
		js.executeScript("arguments[0].click();", dd_lead);
		Thread.sleep(1000);
		dd_lead.sendKeys(value);
		Thread.sleep(1000);
		dd_lead.sendKeys(Keys.ARROW_DOWN);
		dd_lead.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		return this;
	}
	
	public SalesPage selectSalutation(String value) throws InterruptedException
	{
		WebElement dd_salutation = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//span[text()='Salutation']/following::div/a[contains(text(),'None')])[1]")));
		dd_salutation.click();
		dd_salutation.sendKeys("M");
		dd_salutation.sendKeys(Keys.ENTER);
		return this;
	}
	
	public SalesPage inputFirstName(String value)
	{
		WebElement firstName = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label/span[text()='First Name']/following::input[contains(@class,'firstName')]")));
		firstName.clear();
		firstName.sendKeys(value);
		return this;
	}
	
	public SalesPage inputLastName(String value)
	{
		WebElement lastName = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label/span[text()='Last Name']/following::input[contains(@class,'lastName')]")));
		lastName.clear();
		lastName.sendKeys(value);
		return this;
	}
	
	public SalesPage inputCompanyName(String value)
	{
		WebElement compName = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//label/span[text()='Company']/following::input[contains(@class,'input')])[1]")));
		compName.clear();
		compName.sendKeys(value);
		return this;
	}
	
	public SalesPage clickOnAddLead()
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='Add Leads']"))).click();
		return this;
	}
	
	public SalesPage clickOnSubmitButton()
	{
		WebElement clkSubmit = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Submit']")));
		js.executeScript("arguments[0].click();", clkSubmit);
		return this;
	}
	
	public SalesPage clickonSaveButton()
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//button/span[text()='Save'])[last()]"))).click();
		return this;
	}
	
	public SalesPage clickonNextButton() throws InterruptedException
	{
		WebElement clkNext = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Next']")));
		js.executeScript("arguments[0].click();", clkNext);
		Thread.sleep(1000);
		return this;
	}
	
	public SalesPage clickonUploadAttachment()
	{
		WebElement clkUpload = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='Upload Files']")));
		clkUpload.click();
		return this;
	}
	
}
