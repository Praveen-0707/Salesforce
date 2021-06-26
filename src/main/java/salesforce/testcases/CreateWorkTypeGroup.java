package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;

public class CreateWorkTypeGroup extends SalesforceBase {
	
	@BeforeTest
	public void setFileName() {
		excelFileName = "WorkTypeGroups";
		excelSheetName = "CreateWorkTypeGroup";
		browser = "chrome";
	}
	
	@Test(dataProvider = "getData", groups= {"WorkTypeGroups"})
	public void createWorkTypeGroup(String wtgName, String wtgDescr) throws InterruptedException {

		new LoginPage(driver,prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Work Type Groups").clickOnWorkTypeGroups()
		.clickOnNewWorkTypeGroup().inputWorkTypeGroupName(wtgName).inputWorkTypeGroupDescr(wtgDescr)
		.clickOnSaveButton().createNewWorkTypeGroupValidation(wtgName);
	}

}
