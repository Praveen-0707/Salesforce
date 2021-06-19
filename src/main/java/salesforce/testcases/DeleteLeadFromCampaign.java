package salesforce.testcases;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;
import salesforce.pages.SalesPage;

public class DeleteLeadFromCampaign extends SalesforceBase {
	
	@BeforeTest
	public void setFileName() {
		excelFileName = "Campaign";
		excelSheetName = "CreateCampaign";
	}
	
	@Test(dataProvider = "getData")
	public void deleteLeadFromCampaign(String campName, String fName, String lName) throws InterruptedException {
		
		WebElement ele;
		String lead = fName + lName;
//		String campName = "BootCamp";

		new LoginPage(driver)
		.enterUsername().enterPassword().clickLogin()
		.clickToggleButton().clickViewAll()
		.searchApp("Sales").clickOnSales()
		.clickOnLeadsTab().searchLead(lead);
		
		SalesPage salesPage = new SalesPage(driver);

//		Selecting Drop Down value as Delete for selected Lead
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//a[text()='" +lead+ "']/following::td//a[@role='button'])[1]")));
		ele.click();
		
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='Delete']/..")));
		ele.click();
		
		deletePopUpConfirmation();
		
		salesPage.clickOnCampaignTab().searchCampaign(campName);
		
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//a[contains(@title,'" + campName + "')])[1]")));
		ele.click();
		
//		View BootCamp Member details
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@title='Campaign Members']/../../../../../following-sibling::div//span[text()='View All']")));
		ele.click();
		
//		Search for Deleted Lead - Output Validation
		List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@class,'uiVirtualDataTable') and not(contains(@class,'slds-no-cell-focus'))]//tbody/tr"));
		int size = rows.size();
		for (int i = 1; i <= size; i++)
		{
			WebElement listofleadNames = driver.findElement(By.xpath("//table[contains(@class,'uiVirtualDataTable') and not(contains(@class,'slds-no-cell-focus'))]//tbody/tr[" + i + "]//td[4]//a"));
			String leadNames = listofleadNames.getText();
		
			if (leadNames.equals(lead))
			{
				System.out.println("Unable to Delete Lead, TC-Failed");
				break;
			}
			else
			{
				if (i == size)
				{
					System.out.println("Delete Lead was successful, TC-Passed");
				}
			}
			
		}
	}
}
