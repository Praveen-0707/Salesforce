package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;
import salesforce.pages.WorkTypeGroupsPage;
import salesforce.utils.Logs;

public class WorkTypeGroupSortingByName extends SalesforceBase {
	
	@BeforeTest
	public void setTestDetails() {
		testName = "Sort WorkTypeGroups";
		testDescription = "WorkTypeGroups should be sorted by Name in ascending order";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test(groups= {"WorkTypeGroups"})
	public void sortWorkTypeGroup() throws InterruptedException {

		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Work Type Groups").clickOnWorkTypeGroups();
		solidWait(3);
		
		WorkTypeGroupsPage WTG =new WorkTypeGroupsPage();
		WTG.sortWorkTypeGroups();
	}

}
