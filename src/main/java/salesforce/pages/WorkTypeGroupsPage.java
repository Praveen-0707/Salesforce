package salesforce.pages;

import salesforce.base.SalesforceBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

public class WorkTypeGroupsPage extends SalesforceBase {
	
	public WorkTypeGroupsPage(RemoteWebDriver driver)
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
	
//	public WorkTypeGroupsPage selectTask(String value) throws InterruptedException
//	{
//		HomePage homepage = new HomePage(driver);
//		try
//		{
//			homepage.selectTaskFromNavigationControl(value);
//		}
//		catch (Exception e)
//		{
//			System.out.println(e.getMessage());
//			homepage.selectTaskFromNavigationControl(value);
//		}
//		return this;
//	}
	
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
		WebElement ele = driver.findElementByXPath("(//div[@class='select-options' and @role='menu']/ul//a[contains(@title,'"+value+"')])[1]");
		scrollToVisibleElement(ele);
		ele.click();
		return this;
	}
	
	public WorkTypeGroupsPage clickOnSaveButton()
	{
		driver.findElementByXPath("(//button/span[text()='Save'])[last()]").click();
		return this;
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
	
	public WorkTypeGroupsPage createNewWorkTypeGroupValidation(String wtgName)
	{
		try
		{
			WebElement output = driver.findElement(By.xpath("(//span[text()='Description'])[1]/following::div//span[@class='uiOutputText']"));
			wait.until(ExpectedConditions.visibilityOf(output));
			String outputValue = output.getText();
			
			if (outputValue.contains(wtgName))
			{
				System.out.println("Work Type Group - " + outputValue + " was created" + ", Passed");
			}
			else
			{
				System.out.println("Unable to create Work Type Group" + ", Failed");
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return this;
	}
	
	public WorkTypeGroupsPage editWorkTypeGroup(String wtgName)
	{
		WebElement editWTG = driver.findElementByXPath("(//a[text()='" + wtgName + "'])[1]//following::td//a[@role='button']");
		editWTG.click();
		editWTG = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='Edit']/..")));
		editWTG.click();
		return this;
	}
	
	public WorkTypeGroupsPage deleteWorkTypeGroup(String wtgName)
	{
		WebElement delWTG = driver.findElementByXPath("(//a[text()='" + wtgName + "'])[1]//following::td//a[@role='button']");
		delWTG.click();
		delWTG = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='Delete']/..")));
		delWTG.click();
		return this;
	}
	
	public WorkTypeGroupsPage clickOnWorkTypeGroupsTabOptions() throws InterruptedException
	{
		WebElement WTG = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[contains(@title,'Work Type Groups')]/following::div[contains(@class,'context-bar')][1]")));
		js.executeScript("arguments[0].click();", WTG);
		Thread.sleep(2000);
		return this;
	}
	
	public WorkTypeGroupsPage SelectNewWorkTypeGroup() throws InterruptedException
	{
		WebElement WTG = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='New Work Type Group']/ancestor::a[@role='menuitemcheckbox']")));
		js.executeScript("arguments[0].click();", WTG);
		Thread.sleep(2000);
		return this;
	}
	
	
	public WorkTypeGroupsPage searchAndClickOnWorkTypeGroup(String wtgName) throws InterruptedException
	{
		searchWorkTypeGroup(wtgName);
		WebElement searchWTG = driver.findElementByXPath("(//a[text()='" + wtgName + "'])[1]");
		searchWTG.click();
		return this;
	}
	
	public WorkTypeGroupsPage editWorkTypeGroupValidation(String value)
	{
		try
		{
			WebElement output = driver.findElement(By.xpath("(//span[text()='Description'])/following::div//span[text()='Description']/following::span[@class='uiOutputTextArea']"));
			wait.until(ExpectedConditions.visibilityOf(output));
			String outputValue = output.getText();
			
			if (outputValue.contains(value))
			{
				System.out.println("Description updated as: "+value);
				System.out.println("Work Type Group - " + outputValue + " was updated successfully" + ", Passed");
			}
			else
			{
				System.out.println("Unable to update Work Type Group" + ", Failed");
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return this;
	}
	
	public WorkTypeGroupsPage deleteWorkTypeGroupValidation(String value)
	{
		try
		{
		List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@class,'uiVirtualDataTable')]/tbody/tr"));
		int size = rows.size();
		for (int i = 1; i <= size; i++)
		{
			WebElement listofWTG_Names = driver.findElement(By.xpath("//table[contains(@class,'uiVirtualDataTable')]/tbody/tr["+i+"]/th//a"));
			String WTG_Names = listofWTG_Names.getText();
		
			if (WTG_Names.equals(value))
			{
				System.out.println("Unable to Delete WorkTypeGroup or Duplicate entry exist, TC-Failed");
				break;
			}
			else
			{
				if (i == size)
				{
					System.out.println("Delete WorkTypeGroup was successful, TC-Passed");
				}
			}
		}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return this;
	}
	
	public WorkTypeGroupsPage sortWorkTypeGroups()
	{
		try
		{
			List<String> namesSet1 = new ArrayList<String>(); 
			List<WebElement> elements = driver.findElementsByXPath("//table[contains(@class,'slds-table forceRecordLayout')]/tbody/tr");
			int size = elements.size();
			for (int i=1; i<=size; i++)
			{
				WebElement workGroupNames = driver.findElementByXPath("//table[contains(@class,'slds-table forceRecordLayout')]/tbody/tr["+i+"]/th//a");
				String workGroupNamesBeforeSort = workGroupNames.getText();
				namesSet1.add(workGroupNamesBeforeSort);
			}
			Collections.sort(namesSet1);
//			System.out.println("Set1 names after sort: " + namesSet1);
			
//			clicks on Name Sort
			WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@title='Work Type Group Name']/parent::a")));
			ele.click();
			Thread.sleep(3000);
			
			List<String> namesSet2 = new ArrayList<String>(); 
			List<WebElement> wtgToSort = driver.findElementsByXPath("//table[contains(@class,'slds-table forceRecordLayout')]/tbody/tr");
			int cnt = wtgToSort.size();
			for (int j=1; j<=cnt; j++)
			{
				WebElement workGroupNamesToSort = driver.findElementByXPath("//table[contains(@class,'slds-table forceRecordLayout')]/tbody/tr["+j+"]/th//a");
				String workGroupNamesAfterSort = workGroupNamesToSort.getText();
				namesSet2.add(workGroupNamesAfterSort);
			}
			System.out.println("Set2 name without sort: " + namesSet2);
			
			boolean isEqual = namesSet1.equals(namesSet2);
			System.out.println(isEqual);
			if (isEqual)
			{
				System.out.println("Names are sorted in order." + namesSet2);
			}
			else
			{
				System.out.println("Unable to sorted the names");
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return this;
	}
	
	public WorkTypeGroupsPage errValidationOnNewWTGCreation(String errMsg)
	{
		try
		{
			WebElement errTag = wait.until(ExpectedConditions.visibilityOf(driver.findElementByClassName("form-element__help")));
			String errMsgValue = errTag.getText();
			System.out.println(errMsgValue);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(errMsg, errMsgValue);
			System.out.println(softAssert);
			softAssert.assertAll();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return this;
	}
	
}
