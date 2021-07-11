package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.AccountsPage;
import salesforce.pages.LoginPage;
import salesforce.utils.Logs;

public class DeleteAccount extends SalesforceBase {
	
	@BeforeTest
	public void setTestDetails() {
		excelFileName = "Accounts";
		excelSheetName = "DeleteAccounts";
		testName = "Delete Account";
		testDescription = "Account should be deleted";
		testAuthor = "Praveen Raj A";
		testCategory = "Regression";
		browser = "chrome";
		
		Logs.startTestCase(testName);
		Logs.info(testDescription);
	}
	
	@Test(dataProvider = "getData", groups= {"Accounts"})
	public void deleteAccount(String accName) throws InterruptedException
	{
	
		new LoginPage(prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		
		.searchApp("Account").clickOnAccount();
		
		AccountsPage accPg = new AccountsPage();
		accPg.searchAccount(accName).deleteAccount(accName);
		
		deletePopUpConfirmation();
		accPg.deleteAccountValidation(accName);

	}

}
