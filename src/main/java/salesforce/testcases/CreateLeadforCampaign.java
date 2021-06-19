package salesforce.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;
import salesforce.pages.SalesPage;

public class CreateLeadforCampaign extends SalesforceBase {
	
	@BeforeTest
	public void setFileName() {
		excelFileName = "Campaign";
		excelSheetName = "CreateCampaign";
	}
	
	@Test(dataProvider = "getData")
	public void createLeadforCampaign(String campName, String fName, String lName) throws InterruptedException {
		
		WebElement ele;
//		String campName = "BootCamp";
//		String fName = "Test";
//		String lName = "Sample";
		String Flag_Validation = null;
		String leadName = fName + " " + lName;
		
		new LoginPage(driver)
		.enterUsername().enterPassword().clickLogin()
		.clickToggleButton().clickViewAll()
		.searchApp("Sales").clickOnSales()
		.clickOnCampaignTab().searchCampaign(campName);
		
		SalesPage salesPage = new SalesPage(driver);
		
//		Expands selected BootCamp
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//a[contains(@title,'" + campName + "')])[1]")));
		ele.click();
		
//		Adding Lead to BootCamp
		salesPage.clickOnAddLead().selectLead("New Lead")
		.selectSalutation("M").inputFirstName(fName).inputLastName(lName).inputCompanyName("Testleaf")
		.clickonSaveButton().clickonNextButton().clickOnSubmitButton();
		
//		Output validation
		WebElement output = driver.findElement(By.xpath("//div[contains(@class,'toastTitle')]"));
		wait.until(ExpectedConditions.visibilityOf(output));
		String outputValue = output.getText();
		
		if (outputValue.contains(campName))
		{
			System.out.println(outputValue);
			Flag_Validation = "True";
		}
		else
		{
			System.out.println("Unable to update "+ campName + ", Failed");
		}
		
		if (Flag_Validation == "True")
		{
			salesPage.clickOnLeadsTab().searchLead(leadName);
						
			ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//a[contains(@class,'forceOutputLookup')])[1]")));
			String val = ele.getText();
			if (val.contains(leadName))
			{
				System.out.println("TC-Passed");
			}
		}
	}

}
