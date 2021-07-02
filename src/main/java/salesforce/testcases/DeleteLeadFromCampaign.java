package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;
import salesforce.pages.SalesPage;

public class DeleteLeadFromCampaign extends SalesforceBase {
	
	@BeforeTest
	public void setTestDetails() {
		excelFileName = "Campaign";
		excelSheetName = "CreateCampaign";
		browser = "chrome";
	}
	
	@Test(dataProvider = "getData")
	public void deleteLeadFromCampaign(String campName, String fName, String lName) throws InterruptedException {

		String lead = fName + " " + lName;
		SalesPage salesPage = new SalesPage(driver, node);
		
		new LoginPage(driver,prop, node)
		.enterUsername().enterPassword().clickLogin()
		.clickToggleButton().clickViewAll()
		.searchApp("Sales").clickOnSales();
		clickOnTab("Leads");
		salesPage.searchLead(lead).clickOndeleteLead(fName, lName);
		
		deletePopUpConfirmation();
		
		clickOnTab("Campaigns");
		salesPage.searchAndClickOnCampaign(campName)
		.clickAndViewAllCampaignMembers().deleteLeadValidation(fName, lName);

	}
}
