package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;

public class SortingAccounts extends SalesforceBase {
	
	@BeforeTest
	public void setFileName() {
		browser = "chrome";
	}
	
	@Test(groups= {"Accounts"})
	public void sortAccounts() throws InterruptedException
	{
		new LoginPage(driver,prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll().searchApp("Account").clickOnAccount().sortAccountsByName();
		
	}
}
