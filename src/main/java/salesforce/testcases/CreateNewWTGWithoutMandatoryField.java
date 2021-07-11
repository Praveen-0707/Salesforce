package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;
import salesforce.utils.Logs;

public class CreateNewWTGWithoutMandatoryField extends SalesforceBase {

	@BeforeTest
	public void setTestDetails() {
		excelFileName = "Testdata";
		excelSheetName = "WTG";
		testName = "Create New WorkTypeGroup Request without Mandatory Field";
		testDescription = "New WorkTypeGroup request should not be created";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test(dataProvider = "getData")
	public void createWorkTypeGroupWithoutMandatoryFields(String descr, String errMsg) throws InterruptedException {

		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Work Type Groups").clickOnWorkTypeGroups()
		
		.clickOnWorkTypeGroupsTabOptions().SelectNewWorkTypeGroup()
		.inputWorkTypeGroupDescr(descr).selectGroupType("Capacity")
		.clickOnSaveButton().errValidationOnNewWTGCreation(errMsg);
			
	}
}
