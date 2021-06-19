package salesforce.pages;

import salesforce.base.SalesforceBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WorkTypeGroupsPage extends SalesforceBase {
	
	public WorkTypeGroupsPage(ChromeDriver driver)
	{
		this.driver = driver;
	}
	
	public WorkTypeGroupsPage searchWorkTypeGroup(String value) throws InterruptedException
	{
		WebElement searchWTG = driver.findElementByXPath("//input[contains(@name,'WorkTypeGroup-search-input')]");
		wait.until(ExpectedConditions.elementToBeClickable(searchWTG));
		searchWTG.clear();
		searchWTG.sendKeys(value);
		searchWTG.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		return this;
	}
	
	public WorkTypeGroupsPage clickOnNewWorkTypeGroup()
	{
		driver.findElementByXPath("//div[text()='New']").click();
		return this;
	}
	
	public WorkTypeGroupsPage inputWorkTypeGroupDescr(String value)
	{
		WebElement worktypeGrpName = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label/span[text()='Description']/following::textarea")));
		worktypeGrpName.clear();
		worktypeGrpName.sendKeys(value);
		return this;
	}
	
	public WorkTypeGroupsPage inputWorkTypeGroupName(String value)
	{
		WebElement worktypeGrpName = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//span[text()='Work Type Group Name'])[2]/../following-sibling::input")));
		worktypeGrpName.clear();
		worktypeGrpName.sendKeys(value);
		return this;
	}
	
	public WorkTypeGroupsPage selectGroupType(String value) throws InterruptedException
	{
		WebElement dd_groupType = driver.findElement(By.xpath("//span[text()='Group Type']/following::a[@class='select' and @role='button']"));
		wait.until(ExpectedConditions.elementToBeClickable(dd_groupType));
		dd_groupType.click();
		Thread.sleep(1000);
		dd_groupType.sendKeys(value);
		dd_groupType.sendKeys(Keys.ENTER);
		return this;
	}
	
	public AccountDetails clickOnSaveButton()
	{
		driver.findElementByXPath("(//button/span[text()='Save'])[last()]").click();
		return new AccountDetails(driver);
	}
	
	public WorkTypeGroupsPage clickRefreshButton()
	{
		driver.findElementByName("refreshButton");
		return this;
	}
	
	
	public WorkTypeGroupsPage clickOnWorkTypeGroupHeader()
	{
		WebElement WTGSorting = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@title='Work Type Group Name']/parent::a")));
		WTGSorting.click();
		return this;
	}
	
}
