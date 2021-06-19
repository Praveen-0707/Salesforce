package salesforce.pages;

import salesforce.base.SalesforceBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountsPage extends SalesforceBase {
	
	public AccountsPage(ChromeDriver driver)
	{
		this.driver = driver;
	}
	
	public AccountsPage searchAccount(String value) throws InterruptedException
	{
		WebElement searchAcc = driver.findElementByXPath("//input[@name='Account-search-input']");
		wait.until(ExpectedConditions.elementToBeClickable(searchAcc));
		searchAcc.clear();
		searchAcc.sendKeys(value);
		searchAcc.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		return this;
	}
	
	public AccountsPage clickOnCreateNewAccount()
	{
		driver.findElementByXPath("//div[text()='New']").click();
		return this;
	}
	
	public AccountsPage inputAccountName(String value)
	{
		driver.findElementByXPath("//input[@name='Name']").sendKeys(value);
		return this;
	}
	
	public AccountsPage inputPhoneNumber(String phoneNumber)
	{
		driver.findElementByXPath("//input[@name='Phone']").sendKeys(phoneNumber);
		return this;
	}
	
	public AccountsPage inputBillingAddress(String value)
	{
		driver.findElementByXPath("//label[text()='Billing Street']/following-sibling::div/textarea").sendKeys(value);
		return this;
	}
	
	public AccountsPage inputShippingAddress(String value)
	{
		driver.findElementByXPath("//label[text()='Shipping Street']/following-sibling::div/textarea").sendKeys(value);
		return this;
	}
	
	public AccountsPage inputDescription(String value)
	{
		driver.findElementByXPath("//label[text()='Description']/following-sibling::div/textarea").sendKeys(value);
		return this;
	}
	
	public AccountsPage selectType(String value) throws InterruptedException
	{
		WebElement dd_type = driver.findElement(By.xpath("//label[text()='Type']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
		wait.until(ExpectedConditions.elementToBeClickable(dd_type));
		dd_type.click();
		Thread.sleep(1000);
		dd_type.sendKeys(value);
		dd_type.sendKeys(Keys.ENTER);
		return this;
	}

	public AccountsPage selectOwnership(String value) throws InterruptedException
	{
		WebElement dd_ownership = driver.findElement(By.xpath("//label[text()='Ownership']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
		wait.until(ExpectedConditions.elementToBeClickable(dd_ownership));
		dd_ownership.click();
		Thread.sleep(1000);
		dd_ownership.sendKeys(value);
		dd_ownership.sendKeys(Keys.ENTER);
		return this;
	}

	public AccountsPage selectIndustry(String value) throws InterruptedException
	{
		WebElement dd_industry = driver.findElement(By.xpath("//label[text()='Industry']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
		wait.until(ExpectedConditions.elementToBeClickable(dd_industry));
		dd_industry.click();
		Thread.sleep(1000);
		dd_industry.sendKeys(value);
		dd_industry.sendKeys(Keys.ENTER);
		return this;
	}

	public AccountsPage selectUpsellOpportunity(String value) throws InterruptedException
	{
		WebElement dd_upsellOpp = driver.findElement(By.xpath("//label[text()='Upsell Opportunity']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
		wait.until(ExpectedConditions.visibilityOf(dd_upsellOpp));
		dd_upsellOpp.click();
		Thread.sleep(1000);
		dd_upsellOpp.sendKeys(value);
		dd_upsellOpp.sendKeys(Keys.ENTER);
		return this;
	}

	public AccountsPage selectActive(String value) throws InterruptedException
	{
		WebElement dd_active = driver.findElement(By.xpath("//label[text()='Active']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
		wait.until(ExpectedConditions.visibilityOf(dd_active));
		dd_active.click();
		Thread.sleep(1000);
		dd_active.sendKeys(value);
		dd_active.sendKeys(Keys.ENTER);
		return this;
	}

	public AccountsPage selectCustomerPriority(String value) throws InterruptedException
	{
		WebElement dd_CustomerPriority = driver.findElement(By.xpath("//label[text()='Customer Priority']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
		wait.until(ExpectedConditions.visibilityOf(dd_CustomerPriority));
		dd_CustomerPriority.click();
		Thread.sleep(1000);
		dd_CustomerPriority.sendKeys(value);
		dd_CustomerPriority.sendKeys(Keys.ENTER);
		return this;
	}

	public AccountsPage selectSLA(String value) throws InterruptedException
	{
		WebElement dd_SLA = driver.findElement(By.xpath("//label[text()='SLA']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
		wait.until(ExpectedConditions.visibilityOf(dd_SLA));
		dd_SLA.click();
		Thread.sleep(1000);
		dd_SLA.sendKeys(value);
		dd_SLA.sendKeys(Keys.ENTER);
		return this;
	}
	
	public AccountsPage clickRefreshButton()
	{
		driver.findElementByName("refreshButton");
		return this;
	}
	
	public AccountDetails clickOnSaveButton()
	{
		driver.findElementByXPath("//button[@name='SaveEdit' and text()='Save']").click();
		return new AccountDetails(driver);
	}
	
	public AccountsPage clickOnAccountNameHeader()
	{
		WebElement AccountsSorting = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Sort']/following::span[text()='Account Name']/preceding-sibling::span/..")));
		AccountsSorting.click();
		return this;
	}
}
