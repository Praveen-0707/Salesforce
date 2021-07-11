package salesforce.pages;

import salesforce.base.SalesforceBase;

import java.awt.event.KeyEvent;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

public class SalesPage extends SalesforceBase {
	
//	public SalesPage(RemoteWebDriver driver, ExtentTest node)
//	{
//		this.driver = driver;
//		this.node = node;
//	}
	
	public SalesPage clickOnNewCase()
	{
		try {
			WebElement newCase = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//a[@title='New' and @role='button']"));
			newCase.click();
			reportStep("Clicked on New Case Link", "INFO");
			solidWait(3);
		} catch (Exception e) {
			reportStep("Unable to click on New Case Link", "Fail");
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage searchCampaign(String value)
	{
		try {
			WebElement searchCampaign = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//input[contains(@placeholder,'Search this list')]"));
			searchCampaign.clear();
			searchCampaign.sendKeys(value);
			searchCampaign.sendKeys(Keys.ENTER);
			reportStep("Search for Campaign: "+value, "INFO");
			solidWait(2);
		} catch (Exception e) {
			reportStep("Unable to search for Campaign: "+value, "Fail");
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage searchAndClickOnCampaign(String value)
	{
		try {
			searchCampaign(value);
			WebElement searchWTG = driver.findElementByXPath("(//a[text()='" + value + "'])[1]");
			searchWTG.click();
			reportStep("Search for Campaign: "+value +" and clicked on it", "INFO");
		} catch (Exception e) {
			reportStep("Unable to search and click on Campaign: "+value, "Fail");
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage clickAndViewAllCampaignMembers()
	{
		try {
			WebElement ele = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//span[@title='Campaign Members']/../../../../../following-sibling::div//span[text()='View All']"));
			ele.click();
			reportStep("Click on view all campaign members", "INFO");
		} catch (Exception e) {
			reportStep("Unable to click on view all campaign members", "Fail");
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage verifyUploadedAttachment(String fileName)
	{
		try
		{
			String anchorTag = "a";
			String tagName = driver.findElement(By.xpath("//a[@title='"+fileName+"']")).getTagName();
			String linkText = driver.findElement(By.xpath("//a[@title='"+fileName+"']")).getText();
			if((tagName.equals(anchorTag))&&(linkText.contains(fileName)))
			{
				System.out.println("It is a hyperlink, since the WebElement begins with anchor tag : <"+tagName+">."
						+" The file uploaded is :"+linkText+" correct.");
				reportStep("Upload attachment is successful: "+fileName, "INFO");
//				Click on the uploaded file
				solidWait(2);
				driver.findElement(By.xpath("//a[@title='"+fileName+"']")).click();
			}
			solidWait(3);
					
//			Close File Preview
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
		}
		catch(Exception e)
		{
			reportStep("Unable to verify uploaded attachment: "+fileName, "Fail");
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage clickOnViewAllAttachments()
	{
		try {
			WebElement ele = webDriverWait4VisibilityOfEle(driver.findElement(By.xpath("//span[@class='view-all-label']/span[text()='Attachments']")));
			js.executeScript("arguments[0].click();", ele);
			solidWait(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage deleteAttachedFile(String fileName)
	{
		try
		{
			List<WebElement> files = driver.findElements(By.xpath("//table[contains(@class,'uiVirtualDataTable') and not(contains(@class,'slds-no-cell-focus'))]//tbody/tr"));
			int cnt = files.size();
			for (int i = 1; i <= cnt; i++)
			{
				WebElement listofFiles = driver.findElement(By.xpath("//table[contains(@class,'uiVirtualDataTable') and not(contains(@class,'slds-no-cell-focus'))]//tbody/tr["+i+"]/th//a"));
				String UploadedFile = listofFiles.getText();
				if (UploadedFile.contains(fileName))
				{
					WebElement ele = webDriverWait4VisibilityOfEle(driver.findElement(By.xpath("//table[contains(@class,'uiVirtualDataTable') and not(contains(@class,'slds-no-cell-focus'))]//tbody/tr["+i+"]/td[5]//a[@role='button']")));
					js.executeScript("arguments[0].click();", ele);
					solidWait(1);
					ele = webDriverWait4VisibilityOfEle(driver.findElement(By.xpath("//div[@role='button' and @title='Delete']/..")));
					ele.click();
					
//					Delete PopUp
					deletePopUpConfirmation();
					solidWait(2);
					break;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage verifyDeleteAttachment(String fileName)
	{
		try
		{
			List<WebElement> tb_rows = driver.findElements(By.xpath("//table[contains(@class,'uiVirtualDataTable') and not(contains(@class,'slds-no-cell-focus'))]//tbody/tr"));
			int size = tb_rows.size();
			for (int j = 1; j <= size; j++)
			{
				WebElement searchDelFile = driver.findElement(By.xpath("//table[contains(@class,'uiVirtualDataTable') and not(contains(@class,'slds-no-cell-focus'))]//tbody/tr["+j+"]/th//a"));
				String delFile = searchDelFile.getText();
			
				if (delFile.equals(fileName))
				{
					System.out.println("Unable to Delete Uploaded File, TC-Failed");
					break;
				}
				else
				{
					if (j == size)
					{
						System.out.println("Delete File was successful, TC-Passed");
					}
				}	
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage clickOnViewAllKeyDeals()
	{
		try {
			WebElement allDeals = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//a/span[text()='View All Key Deals']"));
			allDeals.click();
			solidWait(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	
	public SalesPage clickOnOpportunityMenu()
	{
		try {
			WebElement ele = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//a[@title='Opportunities']/following::div"));
			ele.click();
			solidWait(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	 
	public SalesPage clickOnCreateNewOpportunity()
	{
		try {
			WebElement ele = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//div[text()='New']"));
			ele.click();
			solidWait(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage selectAllOpportunities()
	{
		try {
			WebElement ele = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//span[text()='All Opportunities']/ancestor::a[@role='menuitem']"));
			js.executeScript("arguments[0].click();", ele);
			solidWait(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage searchLead(String value)
	{
		try {
			WebElement searchLead = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//input[contains(@placeholder,'Search this list')]"));
			searchLead.clear();
			searchLead.sendKeys(value);
			searchLead.sendKeys(Keys.ENTER);
			solidWait(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage selectLead(String value)
	{
		
		try {
			WebElement dd_lead = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//input[@title='Search Leads']"));		
			solidWait(2);
			int length = value.length();
			if (length < 2)
			{
				WebElement dd_leadsearch = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//lightning-icon[contains(@class,'inputLookupIcon')]"));
				actions.moveToElement(dd_leadsearch).click().perform();
				solidWait(2);
				WebElement ele = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//span[@title='New Lead']"));
				ele.click();
				solidWait(2);
			}
			else
			{
				dd_lead.sendKeys(value);
				dd_lead.sendKeys(Keys.ENTER);
				solidWait(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	public SalesPage selectSalutation(String value)
	{
		try {
			WebElement dd_salutation = webDriverWait4ElementToBeClickable(driver.findElementByXPath("(//span[text()='Salutation']/following::div/a[contains(text(),'None')])[1]"));
			dd_salutation.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath("(//div[@class='select-options' and @role='menu']/ul//a[contains(@title,'"+value+"')])[1]");
			scrollToVisibleElement(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage selectContractContactName(String value)
	{
		try {
			WebElement dd_contract = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//input[@title='Search Contacts']"));
			dd_contract.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath("(//input[@title='Search Contacts']/following-sibling::div//div[@class='listContent']/ul/li/a//div[contains(@title,'"+value+"')])[1]");
			scrollToVisibleElement(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage inputFirstName(String value)
	{
		try {
			WebElement firstName = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//label/span[text()='First Name']/following::input[contains(@class,'firstName')]"));
			firstName.clear();
			firstName.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage inputLastName(String value)
	{
		try {
			WebElement lastName = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//label/span[text()='Last Name']/following::input[contains(@class,'lastName')]"));
			lastName.clear();
			lastName.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage inputSubject(String value)
	{
		try {
			WebElement ele = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//span[text()='Subject']/parent::label/following-sibling::input"));
			ele.clear();
			ele.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage inputCaseDescription(String value)
	{
		try {
			WebElement ele = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//span[text()='Description']/parent::label/following-sibling::textarea"));
			ele.clear();
			ele.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage inputCompanyName(String value)
	{
		try {
			WebElement compName = webDriverWait4ElementToBeClickable(driver.findElementByXPath("(//label/span[text()='Company']/following::input[contains(@class,'input')])[1]"));
			compName.clear();
			compName.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage inputCloseDate(String value)
	{
		try {
			WebElement closeDate = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//label[text()='Close Date']/following-sibling::div//input[@name ='CloseDate']"));
			closeDate.clear();
			closeDate.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage selectType(String value)
	{
		try {
			WebElement dd_type = driver.findElement(By.xpath("//label[text()='Type']/following-sibling::div//input[@type ='text']"));
			webDriverWait4ElementToBeClickable(dd_type);
			dd_type.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath("(//label[text()='Type']/following-sibling::div//input[@type ='text']/parent::div/following-sibling::div//lightning-base-combobox-item//span[contains(text(),'"+value+"')])[1]");
			scrollToVisibleElement(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage selectLeadSource(String value)
	{
		try {
			WebElement dd_leadsource = driver.findElement(By.xpath("//label[text()='Lead Source']/following-sibling::div//input[@type ='text']"));
			webDriverWait4ElementToBeClickable(dd_leadsource);
			dd_leadsource.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath("(//label[text()='Lead Source']/following-sibling::div//input[@type ='text']/parent::div/following-sibling::div//lightning-base-combobox-item//span[contains(text(),'"+value+"')])[1]");
			scrollToVisibleElement(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage selectStage(String value)
	{
		try {
			WebElement dd_stage = driver.findElement(By.xpath("//label[text()='Stage']/following-sibling::div//input[@type ='text']"));
			webDriverWait4ElementToBeClickable(dd_stage);
			dd_stage.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath("(//label[text()='Stage']/following-sibling::div//input[@type ='text']/parent::div/following-sibling::div//lightning-base-combobox-item//span[contains(text(),'"+value+"')])[1]");
			scrollToVisibleElement(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage selectPrimaryCampaignSource(String value)
	{
		try {
			WebElement dd_PCS = driver.findElement(By.xpath("//label[text()='Primary Campaign Source']/following-sibling::div//input[@type='text']"));
			webDriverWait4ElementToBeClickable(dd_PCS);
			dd_PCS.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath("(//label[text()='Primary Campaign Source']/following-sibling::div//input[@type ='text']/parent::div/following-sibling::div//lightning-base-combobox-item//span[contains(text(),'"+value+"')])[1]");
			scrollToVisibleElement(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
			
	public SalesPage inputOpportunityName(String value)
	{
		try {
			WebElement oppName = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//label[text()='Opportunity Name']/following-sibling::div//input[@name ='Name']"));
			oppName.clear();
			oppName.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage inputAmount(String value)
	{
		try {
			WebElement oppAmt = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//label[text()='Amount']/following-sibling::div//input[@name ='Amount']"));
			oppAmt.clear();
			oppAmt.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage clickOnAddLead()
	{
		try {
			webDriverWait4ElementToBeClickable(driver.findElementByXPath("//a[@title='Add Leads']")).click();
			solidWait(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage clickOnSubmitButton()
	{
		try {
			WebElement clkSubmit = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//span[text()='Submit']"));
			js.executeScript("arguments[0].click();", clkSubmit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage clickonSaveButton()
	{
		try {
			webDriverWait4ElementToBeClickable(driver.findElementByXPath("(//button/span[text()='Save'])[last()]")).click();
			solidWait(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage clickonSaveOpportunity()
	{
		try {
			webDriverWait4ElementToBeClickable(driver.findElementByXPath("//button[@name='SaveEdit' and text()='Save']")).click();
			solidWait(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage clickonNextButton()
	{
		try {
			WebElement clkNext = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//span[text()='Next']"));
			js.executeScript("arguments[0].click();", clkNext);
			solidWait(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage clickonUploadAttachment()
	{
		try {
			WebElement clkUpload = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//div[@title='Upload Files']"));
			clkUpload.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage ClickOnCampaignName(String campName)
	{
		try {
			WebElement ele = webDriverWait4ElementToBeClickable(driver.findElementByXPath("(//a[contains(@title,'" + campName + "')])[1]"));
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage clickOndeleteLead(String fName, String lName)
	{
		String lead = fName + " " + lName;
		try {
			WebElement delLead = webDriverWait4VisibilityOfEle(driver.findElementByXPath("(//a[text()='" +lead+ "']/following::td//a[@role='button'])[1]"));
			delLead.click();
			delLead = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//div[@role='button' and @title='Delete']/.."));
			delLead.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage errValidationOnCaseCreation(String errMsg)
	{
		try
		{
			WebElement errTag = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//ul[@class='errorsList']/li"));
			String errMsgValue = errTag.getText();
			System.out.println(errMsgValue);
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(errMsg, errMsgValue);
			System.out.println(softAssert);
			softAssert.assertAll();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return this;
	}
	
	public SalesPage createOpportunityValidation(String oppName)
	{
		try
		{
			WebElement output = driver.findElement(By.xpath("//span[contains(text(),'Opportunity')]//a"));
			webDriverWait4VisibilityOfEle(output);
			String outputValue = output.getText();
			
			if (outputValue.contains(oppName))
			{
				System.out.println("Stage 1 Passed");
				WebElement ele = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//div[contains(text(),'Opportunity')]/following-sibling::slot/slot[@slot='primaryField']"));
				String val = ele.getText();
				if (val.contains(oppName))
				System.out.println("Opportunity " + outputValue + " was created" + ", Passed");
			}
			else
			{
				System.out.println("Unable to create Account" + ", Failed");
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return this;
	}
	
	public SalesPage createLeadValidation(String campName, String fName, String lName)
	{
		String Flag_Validation = null;
		String leadName = fName + " " + lName;
		try
		{
			WebElement output = driver.findElement(By.xpath("//div[contains(@class,'toastTitle')]"));
			webDriverWait4VisibilityOfEle(output);
			String outputValue = output.getText();
			
			if (outputValue.contains(campName))
			{
				System.out.println(outputValue);
				Flag_Validation = "True";
			}
			else
			{
				System.out.println("Unable to update "+ campName + ", Failed");
			}
			
			if (Flag_Validation == "True")
			{
				clickOnTab("Leads");
				searchLead(leadName);
				WebElement ele = webDriverWait4VisibilityOfEle(driver.findElementByXPath("(//a[contains(@class,'forceOutputLookup')])[1]"));
				String val = ele.getText();
				if (val.contains(leadName))
				{
					System.out.println("TC-Passed");
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return this;
	}
	
	public SalesPage deleteLeadValidation(String fName, String lName)
	{
		String lead = fName + " " + lName;
		try
		{
			List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@class,'uiVirtualDataTable') and not(contains(@class,'slds-no-cell-focus'))]//tbody/tr"));
			int size = rows.size();
			for (int i = 1; i <= size; i++)
			{
				WebElement listofleadNames = driver.findElement(By.xpath("//table[contains(@class,'uiVirtualDataTable') and not(contains(@class,'slds-no-cell-focus'))]//tbody/tr[" + i + "]//td[4]//a"));
				String leadNames = listofleadNames.getText();
			
				if (leadNames.equals(lead))
				{
					System.out.println("Unable to Delete Lead or Duplicate entry exist, TC-Failed");
					break;
				}
				else
				{
					if (i == size)
					{
						System.out.println("Delete Lead was successful, TC-Passed");
					}
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return this;
	}
	
}
