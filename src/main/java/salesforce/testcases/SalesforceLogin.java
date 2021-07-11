package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;

public class SalesforceLogin extends SalesforceBase {
	
	@BeforeTest
	public void setTestDetails() {
		excelFileName = "Login";
		excelSheetName = "Login";
	}
	
	@Test(dataProvider = "getData")
	public void login(String userName, String pass)
	{
		new LoginPage(prop)
		.enterUsername()
		.enterPassword()
		.clickLogin();
	}
}
