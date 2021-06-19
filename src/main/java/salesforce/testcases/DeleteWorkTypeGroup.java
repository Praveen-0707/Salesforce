package salesforce.testcases;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.pages.LoginPage;
import salesforce.base.SalesforceBase;

public class DeleteWorkTypeGroup extends SalesforceBase {
	
	@BeforeTest
	public void setFileName() {
		excelFileName = "WorkTypeGroups";
		excelSheetName = "DeleteWorkTypeGroup";
	}
	
	@Test(dataProvider = "getData", groups= {"WorkTypeGroups"})
	public void deleteWorkTypeGroup(String workTypeGroup_Name) throws InterruptedException {

		WebElement ele;
		
		new LoginPage(driver)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Work Type Groups").clickOnWorkTypeGroups()
		.searchWorkTypeGroup(workTypeGroup_Name);
		
//		selecting drop down value as Delete
		ele = driver.findElementByXPath("(//a[text()='" + workTypeGroup_Name + "'])[1]//following::td//a[@role='button']");
		ele.click();
		
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='Delete']/..")));
		ele.click();
		
		deletePopUpConfirmation();
		
//		Clearing Search Values
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[contains(@name,'WorkTypeGroup-search-input')]")));
		ele.clear();
		
//		Refreshing the Table
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@name='refreshButton']")));
		ele.click();
		Thread.sleep(3000);
		
//		Search for Deleted WTG - Output Validation
		List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@class,'uiVirtualDataTable')]/tbody/tr"));
		int size = rows.size();
		for (int i = 1; i <= size; i++)
		{
			WebElement listofWTG_Names = driver.findElement(By.xpath("//table[contains(@class,'uiVirtualDataTable')]/tbody/tr["+i+"]/th//a"));
			String WTG_Names = listofWTG_Names.getText();
		
			if (WTG_Names.equals(workTypeGroup_Name))
			{
				System.out.println("Unable to Delete WorkTypeGroup, TC-Failed");
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
}
