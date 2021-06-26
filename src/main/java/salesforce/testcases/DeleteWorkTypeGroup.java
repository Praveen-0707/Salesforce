package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.pages.LoginPage;
import salesforce.pages.WorkTypeGroupsPage;
import salesforce.base.SalesforceBase;

public class DeleteWorkTypeGroup extends SalesforceBase {
	
	@BeforeTest
	public void setFileName() {
		excelFileName = "WorkTypeGroups";
		excelSheetName = "DeleteWorkTypeGroup";
		browser = "chrome";
	}
	
	@Test(dataProvider = "getData", groups= {"WorkTypeGroups"})
	public void deleteWorkTypeGroup(String workTypeGroup_Name) throws InterruptedException {

		new LoginPage(driver,prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Work Type Groups").clickOnWorkTypeGroups()
		.searchWorkTypeGroup(workTypeGroup_Name);
		
		WorkTypeGroupsPage works = new WorkTypeGroupsPage(driver);
		works.deleteWorkTypeGroup(workTypeGroup_Name);
		
		deletePopUpConfirmation();
		
		works.searchWorkTypeGroup("");

		Thread.sleep(3000);
		works.deleteWorkTypeGroupValidation(workTypeGroup_Name);
	}
}
