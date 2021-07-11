package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;
import salesforce.pages.SalesPage;
import salesforce.utils.Logs;

public class DeleteLeadFromCampaign extends SalesforceBase {
	
	@BeforeTest
	public void setTestDetails() {
		excelFileName = "Campaign";
		excelSheetName = "CreateCampaign";
		testName = "Delete Lead from Campaign";
		testDescription = "Lead should be deleted from Campaign";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test(dataProvider = "getData")
	public void deleteLeadFromCampaign(String campName, String fName, String lName) throws InterruptedException {

		String lead = fName + " " + lName;
		SalesPage salesPage = new SalesPage();
		
		new LoginPage(prop)
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
