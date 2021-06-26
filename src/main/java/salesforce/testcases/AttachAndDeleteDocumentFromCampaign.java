package salesforce.testcases;

import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;
import salesforce.pages.SalesPage;

public class AttachAndDeleteDocumentFromCampaign extends SalesforceBase {
	
	@Test
	public void uploadNDeleteDocsFromCampaign() throws InterruptedException {

		String fileLocation = "C:\\Users\\pravi\\Desktop\\Bike_Insurance.pdf";
		
		new LoginPage(driver,prop)
		.enterUsername().enterPassword().clickLogin()
		.clickToggleButton().clickViewAll()
		.searchApp("Sales").clickOnSales();
		clickOnTab("Campaigns");
		
		SalesPage sales = new SalesPage(driver);
		
		sales.searchAndClickOnCampaign("BootCamp")
		.clickonUploadAttachment();
		uploadAttachment(fileLocation);
		
		sales.verifyUploadedAttachment("TexFile")
		.clickOnViewAllAttachments()
		.deleteAttachedFile("TexFile").verifyDeleteAttachment("TexFile");
			
	}
}
