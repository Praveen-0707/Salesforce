package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.AccountsPage;
import salesforce.pages.LoginPage;

public class DeleteAccount extends SalesforceBase {
	
	@BeforeTest
	public void setFileName() {
		excelFileName = "Accounts";
		excelSheetName = "DeleteAccounts";
		browser = "chrome";
	}
	
	@Test(dataProvider = "getData", groups= {"Accounts"})
	public void deleteAccount(String accName) throws InterruptedException
	{
	
		new LoginPage(driver,prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		
		.searchApp("Account").clickOnAccount();
		
		AccountsPage accPg = new AccountsPage(driver);
		accPg.searchAccount(accName).deleteAccount(accName);
		
		deletePopUpConfirmation();
		accPg.deleteAccountValidation(accName);

	}

}
