package salesforce.pages;

import salesforce.base.SalesforceBase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

public class WorkTypeGroupsPage extends SalesforceBase {
	
	public WorkTypeGroupsPage()
	{
		this.driver = getDriver();
		driver.switchTo().defaultContent();
	}
	
	public WorkTypeGroupsPage searchWorkTypeGroup(String value)
	{
		try {
			WebElement searchWTG = driver.findElementByXPath("//input[contains(@name,'WorkTypeGroup-search-input')]");
			webDriverWait4ElementToBeClickable(searchWTG);
			searchWTG.clear();
			searchWTG.sendKeys(value);
			searchWTG.sendKeys(Keys.ENTER);
			solidWait(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
		
	public WorkTypeGroupsPage clickOnNewWorkTypeGroup()
	{
		try {
			driver.findElementByXPath("//div[text()='New']").click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public WorkTypeGroupsPage inputWorkTypeGroupDescr(String value)
	{
		try {
			WebElement worktypeGrpName = driver.findElementByXPath("//label/span[text()='Description']/following::textarea");
			webDriverWait4ElementToBeClickable(worktypeGrpName);
			worktypeGrpName.clear();
			worktypeGrpName.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public WorkTypeGroupsPage inputWorkTypeGroupName(String value)
	{
		try {
			WebElement worktypeGrpName = driver.findElementByXPath("(//span[text()='Work Type Group Name'])[2]/../following-sibling::input");
			webDriverWait4VisibilityOfEle(worktypeGrpName);
			worktypeGrpName.clear();
			worktypeGrpName.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public WorkTypeGroupsPage selectGroupType(String value)
	{
		try {
			WebElement dd_groupType = driver.findElement(By.xpath("//span[text()='Group Type']/following::a[@class='select' and @role='button']"));
			webDriverWait4ElementToBeClickable(dd_groupType);
			dd_groupType.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath("(//div[@class='select-options' and @role='menu']/ul//a[contains(@title,'"+value+"')])[1]");
			scrollToVisibleElement(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public WorkTypeGroupsPage clickOnSaveButton()
	{
		try {
			driver.findElementByXPath("(//button/span[text()='Save'])[last()]").click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public WorkTypeGroupsPage clickRefreshButton()
	{
		try {
			driver.findElementByName("refreshButton").click();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			webDriverWait4VisibilityOfEle(output);
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
		try {
			WebElement editWTG = driver.findElementByXPath("(//a[text()='" + wtgName + "'])[1]//following::td//a[@role='button']");
			webDriverWait4VisibilityOfEle(editWTG);
			editWTG.click();
			editWTG = driver.findElementByXPath("//div[@role='button' and @title='Edit']/..");
			webDriverWait4VisibilityOfEle(editWTG);
			editWTG.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public WorkTypeGroupsPage deleteWorkTypeGroup(String wtgName)
	{
		try {
			WebElement delWTG = driver.findElementByXPath("(//a[text()='" + wtgName + "'])[1]//following::td//a[@role='button']");
			webDriverWait4VisibilityOfEle(delWTG);
			delWTG.click();
			delWTG = driver.findElementByXPath("//div[@role='button' and @title='Delete']/..");
			webDriverWait4VisibilityOfEle(delWTG);
			delWTG.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public WorkTypeGroupsPage clickOnWorkTypeGroupsTabOptions()
	{
		try {
			WebElement WTG = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[contains(@title,'Work Type Groups')]/following::div[contains(@class,'context-bar')][1]")));
			webDriverWait4ElementToBeClickable(WTG);
			js.executeScript("arguments[0].click();", WTG);
			solidWait(2);
		} catch (JavascriptException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public WorkTypeGroupsPage SelectNewWorkTypeGroup()
	{
		try {
			WebElement WTG = driver.findElementByXPath("//span[text()='New Work Type Group']/ancestor::a[@role='menuitemcheckbox']");
			webDriverWait4ElementToBeClickable(WTG);
			js.executeScript("arguments[0].click();", WTG);
			solidWait(2);
		} catch (JavascriptException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
		
	public WorkTypeGroupsPage searchAndClickOnWorkTypeGroup(String wtgName)
	{
		try {
			searchWorkTypeGroup(wtgName);
			WebElement searchWTG = driver.findElementByXPath("(//a[text()='" + wtgName + "'])[1]");
			searchWTG.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public WorkTypeGroupsPage editWorkTypeGroupValidation(String value)
	{
		try
		{
			WebElement output = driver.findElement(By.xpath("(//span[text()='Description'])/following::div//span[text()='Description']/following::span[@class='uiOutputTextArea']"));
			webDriverWait4VisibilityOfEle(output);
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
			WebElement ele = driver.findElementByXPath("//span[@title='Work Type Group Name']/parent::a");
			webDriverWait4ElementToBeClickable(ele);
			ele.click();
			solidWait(3);
			
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
			WebElement errTag = driver.findElementByClassName("form-element__help");
			webDriverWait4VisibilityOfEle(errTag);
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
