package salesforce.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;

public class CreateWorkTypeGroup extends SalesforceBase {
	
	@BeforeTest
	public void setFileName() {
		excelFileName = "WorkTypeGroups";
		excelSheetName = "CreateWorkTypeGroup";
	}
	
	@Test(dataProvider = "getData", groups= {"WorkTypeGroups"})
	public void createWorkTypeGroup(String wtgName, String wtgDescr) throws InterruptedException {

		new LoginPage(driver)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Work Type Groups").clickOnWorkTypeGroups()
		.clickOnNewWorkTypeGroup().inputWorkTypeGroupName(wtgName).inputWorkTypeGroupDescr(wtgDescr)
		.clickOnSaveButton();
		
//		Output validation
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

}
