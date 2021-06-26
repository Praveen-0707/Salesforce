package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;

public class CreateNewWTGWithoutMandatoryField extends SalesforceBase {

	@BeforeTest
	public void setFileName() {
		excelFileName = "Testdata";
		excelSheetName = "WTG";
	}
	
	@Test(dataProvider = "getData")
	public void createWorkTypeGroupWithoutMandatoryFields(String descr, String errMsg) throws InterruptedException {

		new LoginPage(driver,prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Work Type Groups").clickOnWorkTypeGroups()
		
		.clickOnWorkTypeGroupsTabOptions().SelectNewWorkTypeGroup()
		.inputWorkTypeGroupDescr(descr).selectGroupType("Capacity")
		.clickOnSaveButton().errValidationOnNewWTGCreation(errMsg);
			
	}
}
