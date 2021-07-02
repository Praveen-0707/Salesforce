package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;
import salesforce.pages.SalesPage;

public class CreateLeadforCampaign extends SalesforceBase {
	
	@BeforeTest
	public void setTestDetails() {
		excelFileName = "Campaign";
		excelSheetName = "Leads";
		browser = "chrome";
	}
	
	@Test(dataProvider = "getData")
	public void createLeadforCampaign(String campName, String fName, String lName, String compName) throws InterruptedException {

		String createLead = "";	// creating New Lead
		
		new LoginPage(driver,prop, node)
		.enterUsername().enterPassword().clickLogin()
		.clickToggleButton().clickViewAll()
		.searchApp("Sales").clickOnSales();
		
		clickOnTab("Campaigns");
		new SalesPage(driver, node).searchCampaign(campName).ClickOnCampaignName(campName)
		.clickOnAddLead().selectLead(createLead)
		.selectSalutation("Mr").inputFirstName(fName).inputLastName(lName).inputCompanyName(compName)
		.clickonSaveButton().clickonNextButton().clickOnSubmitButton()
		.createLeadValidation(campName, fName, lName);
	}

}
