package salesforce.testcases;

import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;
import salesforce.pages.ServiceConsolePage;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC_AssessmentCase extends SalesforceBase {
	
	public String fileName;
	
	@BeforeTest
	public void setFileName() {
//		excelFileName = "Accounts";
//		excelSheetName = "CreateAccounts";
		browser = "chrome";
	}
	
	@Test
	public void assessmentTestcase() throws InterruptedException, IOException
	{		
		String fileLocation = "C:\\Users\\HP\\Downloads\\sampleuploadfile.txt";
		String uploadFile = "sampleuploadfile";
		String uploadFileExtension = "txt";
		
		new LoginPage(driver,prop)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Service Console").clickOnServiceConsole()
		.selectTask("Files").clickOnRecentFiles().clickonLastModifiedFilesColumn();
		
		ServiceConsolePage servObj = new ServiceConsolePage(driver);
		fileName = servObj.selectLastModifiedFile();
		
		servObj.selectFileOptionAs("Public Link")
		.verifyPublicLinkInputFieldIsDisabled();
				
		servObj.selectLastModifiedFile();
		servObj.selectFileOptionAs("Download");
		
		copyFilefromSourceToDestinationFolder(fileName);
		
		servObj.selectLastModifiedFile();
		servObj.selectFileOptionAs("Share")
		.inputShareWithUser("Derrick").verifyErrorMessageInShareScreen()
		.clearExistingRecordAndAddNewShareFileWith("Integration User")
		.inputShareAttachmentMessage("Bootcamp_Nupela_Praveen").clickOnShareAttachment()
		.verifyShareAttachment(fileName, "Integration User").clickOnUploadFiles();
		
		uploadAttachment(fileLocation);
		Thread.sleep(3000);
		
		servObj.clickToOpenFile(uploadFile,"View File Details")
		.verifyUploadedFileDetails(uploadFile, uploadFileExtension);
		
		closeAnOpenedTab(uploadFile);
		clickOnTab("Files");
		servObj.clickToOpenFile(uploadFile, "Delete");
		deletePopUpConfirmation();
		servObj.verifyDeleteAttachment(uploadFile);
		
	}
}
