package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;

public class CreateNewWTGWithoutMandatoryField extends SalesforceBase {

	@BeforeTest
	public void setTestDetails() {
		excelFileName = "Testdata";
		excelSheetName = "WTG";
	}
	
	@Test(dataProvider = "getData")
	public void createWorkTypeGroupWithoutMandatoryFields(String descr, String errMsg) throws InterruptedException {

		new LoginPage(driver,prop, node)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Work Type Groups").clickOnWorkTypeGroups()
		
		.clickOnWorkTypeGroupsTabOptions().SelectNewWorkTypeGroup()
		.inputWorkTypeGroupDescr(descr).selectGroupType("Capacity")
		.clickOnSaveButton().errValidationOnNewWTGCreation(errMsg);
			
	}
}
