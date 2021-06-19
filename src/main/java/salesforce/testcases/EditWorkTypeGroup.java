package salesforce.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.pages.LoginPage;
import salesforce.pages.WorkTypeGroupsPage;
import salesforce.base.SalesforceBase;

public class EditWorkTypeGroup extends SalesforceBase {
	
	@BeforeTest
	public void setFileName() {
		excelFileName = "WorkTypeGroups";
		excelSheetName = "EditWorkTypeGroup";
	}
	
	@Test(dataProvider = "getData", groups= {"WorkTypeGroups"})
	public void editWorkTypeGroup(String workTypeGroup_Name, String descr) throws InterruptedException {

		WebElement ele;
		
		new LoginPage(driver)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Work Type Groups").clickOnWorkTypeGroups()
		.searchWorkTypeGroup(workTypeGroup_Name);

		WorkTypeGroupsPage WTG = new WorkTypeGroupsPage(driver);
		
//		selecting drop down value as edit
		ele = driver.findElementByXPath("(//a[text()='" + workTypeGroup_Name + "'])[1]//following::td//a[@role='button']");
		ele.click();
		
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='Edit']/..")));
		ele.click();
		
		WTG.inputWorkTypeGroupDescr(descr);
		WTG.selectGroupType("C").clickOnSaveButton();
		Thread.sleep(2000);
		
//		selecting WorkTypegroup
		ele = driver.findElementByXPath("(//a[text()='" + workTypeGroup_Name + "'])[1]");
		ele.click();
	
//		output validation - Description field
		WebElement output = driver.findElement(By.xpath("(//span[text()='Description'])/following::div//span[text()='Description']/following::span[@class='uiOutputTextArea']"));
		wait.until(ExpectedConditions.visibilityOf(output));
		String outputValue = output.getText();
		
		if (outputValue.contains(descr))
		{
			System.out.println("Description updated as: "+descr);
			System.out.println("Work Type Group - " + outputValue + " was updated successfully" + ", Passed");
		}
		else
		{
			System.out.println("Unable to update Work Type Group" + ", Failed");
		}
	}

}
