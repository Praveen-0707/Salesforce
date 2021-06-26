package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;
import salesforce.pages.WorkTypeGroupsPage;

public class WorkTypeGroupSortingByName extends SalesforceBase {
	
	@BeforeTest
	public void setFileName() {
		browser = "chrome";
	}
	
	@Test(groups= {"WorkTypeGroups"})
	public void sortWorkTypeGroup() throws InterruptedException {

		new LoginPage(driver,prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Work Type Groups").clickOnWorkTypeGroups();
		Thread.sleep(3000);
		
		WorkTypeGroupsPage WTG =new WorkTypeGroupsPage(driver);
		WTG.sortWorkTypeGroups();
	}

}
