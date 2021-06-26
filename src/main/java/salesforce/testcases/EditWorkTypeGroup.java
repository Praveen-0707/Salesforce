package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.pages.LoginPage;
import salesforce.pages.WorkTypeGroupsPage;
import salesforce.base.SalesforceBase;

public class EditWorkTypeGroup extends SalesforceBase {
	
	@BeforeTest
	public void setFileName() {
		excelFileName = "WorkTypeGroups";
		excelSheetName = "EditWorkTypeGroup";
		browser = "chrome";
	}
	
	@Test(dataProvider = "getData", groups= {"WorkTypeGroups"})
	public void editWorkTypeGroup(String workTypeGroup_Name, String descr) throws InterruptedException {

		new LoginPage(driver,prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Work Type Groups").clickOnWorkTypeGroups()
		.searchWorkTypeGroup(workTypeGroup_Name);

		WorkTypeGroupsPage WTG = new WorkTypeGroupsPage(driver);
		WTG.editWorkTypeGroup(workTypeGroup_Name)
		.inputWorkTypeGroupDescr(descr).selectGroupType("Capacity").clickOnSaveButton()
		.searchAndClickOnWorkTypeGroup(workTypeGroup_Name).editWorkTypeGroupValidation(descr);
	}

}
