package salesforce.testcases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.AccountsPage;
import salesforce.pages.LoginPage;

public class EditAccount extends SalesforceBase {
	
	@BeforeTest
	public void setTestDetails() {
		excelFileName = "Accounts";
		excelSheetName = "EditAccounts";
		browser = "chrome";
	}
	
	@Test(dataProvider = "getData", groups= {"Accounts"})
	public void editAccount(String accName, String phNum) throws InterruptedException
	{
		WebElement ele;
		
		new LoginPage(driver,prop, node)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll().searchApp("Account").clickOnAccount();
		
		AccountsPage accPg = new AccountsPage(driver, node);
		accPg.searchAccount(accName).editAccount(accName);
		accPg.inputPhoneNumber(phNum).selectType("Technology Partner").selectIndustry("Healthcare");
		
		ele = driver.findElementByXPath("//label[text()='Billing Street']/following-sibling::div/textarea");
		scrollToVisibleElement(ele);
		
		accPg.selectCustomerPriority("Low").selectSLA("Silver")
		.selectUpsellOpportunity("No").selectActive("No").clickOnSaveButton().editAccountValidation(accName, phNum);
				
	}
}
