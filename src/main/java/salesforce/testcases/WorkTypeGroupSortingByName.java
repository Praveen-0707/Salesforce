package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;
import salesforce.pages.WorkTypeGroupsPage;

public class WorkTypeGroupSortingByName extends SalesforceBase {
	
	@BeforeTest
	public void setTestDetails() {
		browser = "chrome";
	}
	
	@Test(groups= {"WorkTypeGroups"})
	public void sortWorkTypeGroup() throws InterruptedException {

		new LoginPage(driver,prop, node)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Work Type Groups").clickOnWorkTypeGroups();
		solidWait(3);
		
		WorkTypeGroupsPage WTG =new WorkTypeGroupsPage(driver, node);
		WTG.sortWorkTypeGroups();
	}

}
