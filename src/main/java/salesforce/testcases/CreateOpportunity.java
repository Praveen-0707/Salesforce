package salesforce.testcases;

import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;

public class CreateOpportunity extends SalesforceBase {
	
	@Test
	public void createOpportunity() throws InterruptedException
	{
		String oppName = "TMT Steel";
		
		new LoginPage(driver,prop, node)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Sales").clickOnSales().clickOnViewAllKeyDeals()
		.clickOnOpportunityMenu().selectAllOpportunities()
		.clickOnCreateNewOpportunity()
		.inputOpportunityName(oppName).inputAmount("10000").inputCloseDate("6/10/2021")
		.selectType("New Customer").selectLeadSource("Partner Referral")
		.selectStage("Needs Analysis").selectPrimaryCampaignSource("BootCamp")
		.clickonSaveOpportunity().createOpportunityValidation(oppName);
	}
		
}


