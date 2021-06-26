package salesforce.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;
import salesforce.pages.SalesPage;

public class CreateNewCaseWithoutMandatoryField extends SalesforceBase {
	
	@BeforeTest
	public void setFileName() {
		excelFileName = "Testdata";
		excelSheetName = "NewCase";
	}
	
	@Test(dataProvider = "getData")
	public void createNewCaseWithoutMandatoryFields(String contactName, String subject, String description, String errMsg) throws InterruptedException {

		new LoginPage(driver,prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Sales").clickOnSales();
		
		clickOnTab("Cases");
		new SalesPage(driver).clickOnNewCase()
		.selectContractContactName(contactName).inputSubject(subject).inputCaseDescription(description)
		.clickonSaveButton().errValidationOnCaseCreation(errMsg);

	}

}
