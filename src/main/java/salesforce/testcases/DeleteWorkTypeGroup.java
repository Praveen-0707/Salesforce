package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.pages.LoginPage;
import salesforce.pages.WorkTypeGroupsPage;
import salesforce.utils.Logs;
import salesforce.base.SalesforceBase;

public class DeleteWorkTypeGroup extends SalesforceBase {
	
	@BeforeTest
	public void setTestDetails() {
		excelFileName = "WorkTypeGroups";
		excelSheetName = "DeleteWorkTypeGroup";
		testName = "Delete WorkTypeGroup";
		testDescription = "WorkTypeGroup should be deleted";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test(dataProvider = "getData", groups= {"WorkTypeGroups"})
	public void deleteWorkTypeGroup(String workTypeGroup_Name) throws InterruptedException {

		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Work Type Groups").clickOnWorkTypeGroups()
		.searchWorkTypeGroup(workTypeGroup_Name);
		
		WorkTypeGroupsPage works = new WorkTypeGroupsPage();
		works.deleteWorkTypeGroup(workTypeGroup_Name);
		
		deletePopUpConfirmation();
		
		works.searchWorkTypeGroup("");

		solidWait(3);
		works.deleteWorkTypeGroupValidation(workTypeGroup_Name);
	}
}
