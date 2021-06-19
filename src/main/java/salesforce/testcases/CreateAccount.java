package salesforce.testcases;

import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateAccount extends SalesforceBase {
	
	@BeforeTest
	public void setFileName() {
		excelFileName = "Accounts";
		excelSheetName = "CreateAccounts";
	}
	
	@Test(dataProvider = "getData", groups= {"Accounts"})
	public void createNewAccount(String accName) throws InterruptedException
	{
		new LoginPage(driver)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Account").clickOnAccount()
		
		.clickOnCreateNewAccount().inputAccountName(accName)
		.selectOwnership("P").clickOnSaveButton();
		
		
	}
}
