package salesforce.pages;

import salesforce.base.SalesforceBase;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

public class SalesPage extends SalesforceBase {
	
	public SalesPage(RemoteWebDriver driver)
	{
		this.driver = driver;
	}
	
//	public SalesPage clickOnCampaignTab()
//	{
//		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='Campaigns']")));
//		js.executeScript("arguments[0].click();", ele);
//		return this;
//	}
//	
//	public SalesPage clickOnCasesTab()
//	{
//		WebElement cases = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='Cases']")));
//		js.executeScript("arguments[0].click();", cases);
//		return this;
//	}
//	
//	public SalesPage clickOnLeadsTab()
//	{
//		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='Leads']")));
//		js.executeScript("arguments[0].click();", ele);
//		return this;
//	}
	
	public SalesPage clickOnNewCase() throws InterruptedException
	{
		WebElement newCase = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='New' and @role='button']")));
		newCase.click();
		Thread.sleep(3000);
		return this;
	}
	
	public SalesPage searchCampaign(String value) throws InterruptedException
	{
		WebElement searchCampaign = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[contains(@placeholder,'Search this list')]")));
		searchCampaign.clear();
		searchCampaign.sendKeys(value);
		searchCampaign.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		return this;
	}
	
	public SalesPage searchAndClickOnCampaign(String value) throws InterruptedException
	{
		searchCampaign(value);
		WebElement searchWTG = driver.findElementByXPath("(//a[text()='" + value + "'])[1]");
		searchWTG.click();
		return this;
	}
	
	public SalesPage clickAndViewAllCampaignMembers() throws InterruptedException
	{
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@title='Campaign Members']/../../../../../following-sibling::div//span[text()='View All']")));
		ele.click();
		return this;
	}
	
//	public SalesPage uploadAttachmentToCampaign(String fileLocation) throws InterruptedException
//	{
//		StringSelection up = new StringSelection(fileLocation);
//		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(up, null);
//		
//		robot.keyPress(KeyEvent.VK_CONTROL);
//		robot.keyPress(KeyEvent.VK_V);
//
//		robot.keyRelease(KeyEvent.VK_CONTROL);
//		robot.keyRelease(KeyEvent.VK_V);
//
//		robot.keyPress(KeyEvent.VK_ENTER);
//		robot.keyRelease(KeyEvent.VK_ENTER);
//		Thread.sleep(3000);
//		
////		click on the Done button to dismiss the alert
//		WebElement ele = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@type='button']//span[text()='Done']"))));
//		ele.click();
//		return this;
//	}
	
	public SalesPage verifyUploadedAttachment(String fileName) throws InterruptedException
	{
		try
		{
			String anchorTag = "a";
//			String fileName = "Bike_Insurance";
			String tagName = driver.findElement(By.xpath("//a[@title='"+fileName+"']")).getTagName();
			String linkText = driver.findElement(By.xpath("//a[@title='"+fileName+"']")).getText();
			if((tagName.equals(anchorTag))&&(linkText.contains(fileName)))
			{
				System.out.println("It is a hyperlink, since the WebElement begins with anchor tag : <"+tagName+">."
						+" The file uploaded is :"+linkText+" correct.");
			}

//			Click on the uploaded file
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@title='"+fileName+"']")).click();
			Thread.sleep(3000);
					
//			Close File Preview
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this;
	}
	
	public SalesPage clickOnViewAllAttachments() throws InterruptedException
	{
		WebElement ele = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='view-all-label']/span[text()='Attachments']"))));
		js.executeScript("arguments[0].click();", ele);
		Thread.sleep(2000);
		return this;
	}
	
	public SalesPage deleteAttachedFile(String fileName) throws InterruptedException
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
					WebElement ele = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//table[contains(@class,'uiVirtualDataTable') and not(contains(@class,'slds-no-cell-focus'))]//tbody/tr["+i+"]/td[5]//a[@role='button']"))));
					js.executeScript("arguments[0].click();", ele);
					Thread.sleep(500);
					ele = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@role='button' and @title='Delete']/.."))));
					ele.click();
					
//					Delete PopUp
					ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@title='Delete']"))));
					ele.click();
					Thread.sleep(2000);
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
	
	public SalesPage verifyDeleteAttachment(String fileName) throws InterruptedException
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
	
	public SalesPage clickOnViewAllKeyDeals() throws InterruptedException
	{
		WebElement allDeals = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a/span[text()='View All Key Deals']")));
		allDeals.click();
		Thread.sleep(2000);
		return this;
	}
	
	
	public SalesPage clickOnOpportunityMenu() throws InterruptedException
	{
		WebElement ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[@title='Opportunities']/following::div")));
		ele.click();
		Thread.sleep(3000);
		return this;
	}
	 
	public SalesPage clickOnCreateNewOpportunity() throws InterruptedException
	{
		WebElement ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[text()='New']")));
		ele.click();
		Thread.sleep(2000);
		return this;
	}
	
	public SalesPage selectAllOpportunities() throws InterruptedException
	{
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='All Opportunities']/ancestor::a[@role='menuitem']")));
		js.executeScript("arguments[0].click();", ele);
		Thread.sleep(2000);
		return this;
	}
	
	public SalesPage searchLead(String value) throws InterruptedException
	{
		WebElement searchLead = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[contains(@placeholder,'Search this list')]")));
		searchLead.clear();
		searchLead.sendKeys(value);
		searchLead.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		return this;
	}
	
	public SalesPage selectLead(String value) throws InterruptedException
	{
		
		WebElement dd_lead = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//input[@title='Search Leads']")));		
		Thread.sleep(2000);
		int length = value.length();
		if (length < 2)
		{
			WebElement dd_leadsearch = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//lightning-icon[contains(@class,'inputLookupIcon')]")));
			actions.moveToElement(dd_leadsearch).click().perform();
			Thread.sleep(2000);
			WebElement ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[@title='New Lead']")));
			ele.click();
			Thread.sleep(2000);
		}
		else
		{
			dd_lead.sendKeys(value);
			dd_lead.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
		}
		
		return this;
	}
	
	public SalesPage selectSalutation(String value) throws InterruptedException
	{
		WebElement dd_salutation = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//span[text()='Salutation']/following::div/a[contains(text(),'None')])[1]")));
		dd_salutation.click();
		Thread.sleep(1000);
		WebElement ele = driver.findElementByXPath("(//div[@class='select-options' and @role='menu']/ul//a[contains(@title,'"+value+"')])[1]");
		scrollToVisibleElement(ele);
		ele.click();
		return this;
	}
	
	public SalesPage selectContractContactName(String value) throws InterruptedException
	{
		WebElement dd_contract = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[@title='Search Contacts']")));
		dd_contract.click();
		Thread.sleep(1000);
		WebElement ele = driver.findElementByXPath("(//input[@title='Search Contacts']/following-sibling::div//div[@class='listContent']/ul/li/a//div[contains(@title,'"+value+"')])[1]");
		scrollToVisibleElement(ele);
		ele.click();
		return this;
	}
	
	public SalesPage inputFirstName(String value)
	{
		WebElement firstName = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label/span[text()='First Name']/following::input[contains(@class,'firstName')]")));
		firstName.clear();
		firstName.sendKeys(value);
		return this;
	}
	
	public SalesPage inputLastName(String value)
	{
		WebElement lastName = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label/span[text()='Last Name']/following::input[contains(@class,'lastName')]")));
		lastName.clear();
		lastName.sendKeys(value);
		return this;
	}
	
	public SalesPage inputSubject(String value)
	{
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Subject']/parent::label/following-sibling::input")));
		ele.clear();
		ele.sendKeys(value);
		return this;
	}
	
	public SalesPage inputCaseDescription(String value)
	{
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Description']/parent::label/following-sibling::textarea")));
		ele.clear();
		ele.sendKeys(value);
		return this;
	}
	
	public SalesPage inputCompanyName(String value)
	{
		WebElement compName = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//label/span[text()='Company']/following::input[contains(@class,'input')])[1]")));
		compName.clear();
		compName.sendKeys(value);
		return this;
	}
	
	public SalesPage inputCloseDate(String value)
	{
		WebElement closeDate = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label[text()='Close Date']/following-sibling::div//input[@name ='CloseDate']")));
		closeDate.clear();
		closeDate.sendKeys(value);
		return this;
	}
	
	public SalesPage selectType(String value) throws InterruptedException
	{
		WebElement dd_type = driver.findElement(By.xpath("//label[text()='Type']/following-sibling::div//input[@type ='text']"));
		wait.until(ExpectedConditions.elementToBeClickable(dd_type));
		dd_type.click();
		Thread.sleep(1000);
		WebElement ele = driver.findElementByXPath("(//label[text()='Type']/following-sibling::div//input[@type ='text']/parent::div/following-sibling::div//lightning-base-combobox-item//span[contains(text(),'"+value+"')])[1]");
		scrollToVisibleElement(ele);
		ele.click();
		return this;
	}
	
	public SalesPage selectLeadSource(String value) throws InterruptedException
	{
		WebElement dd_leadsource = driver.findElement(By.xpath("//label[text()='Lead Source']/following-sibling::div//input[@type ='text']"));
		wait.until(ExpectedConditions.elementToBeClickable(dd_leadsource));
		dd_leadsource.click();
		Thread.sleep(1000);
		WebElement ele = driver.findElementByXPath("(//label[text()='Lead Source']/following-sibling::div//input[@type ='text']/parent::div/following-sibling::div//lightning-base-combobox-item//span[contains(text(),'"+value+"')])[1]");
		scrollToVisibleElement(ele);
		ele.click();
		return this;
	}
	
	public SalesPage selectStage(String value) throws InterruptedException
	{
		WebElement dd_stage = driver.findElement(By.xpath("//label[text()='Stage']/following-sibling::div//input[@type ='text']"));
		wait.until(ExpectedConditions.elementToBeClickable(dd_stage));
		dd_stage.click();
		Thread.sleep(1000);
		WebElement ele = driver.findElementByXPath("(//label[text()='Stage']/following-sibling::div//input[@type ='text']/parent::div/following-sibling::div//lightning-base-combobox-item//span[contains(text(),'"+value+"')])[1]");
		scrollToVisibleElement(ele);
		ele.click();
		return this;
	}
	
	public SalesPage selectPrimaryCampaignSource(String value) throws InterruptedException
	{
		WebElement dd_PCS = driver.findElement(By.xpath("//label[text()='Primary Campaign Source']/following-sibling::div//input[@type='text']"));
		wait.until(ExpectedConditions.elementToBeClickable(dd_PCS));
		dd_PCS.click();
		Thread.sleep(1000);
		WebElement ele = driver.findElementByXPath("(//label[text()='Primary Campaign Source']/following-sibling::div//input[@type ='text']/parent::div/following-sibling::div//lightning-base-combobox-item//span[contains(text(),'"+value+"')])[1]");
		scrollToVisibleElement(ele);
		ele.click();
		return this;
	}
			
	public SalesPage inputOpportunityName(String value)
	{
		WebElement oppName = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label[text()='Opportunity Name']/following-sibling::div//input[@name ='Name']")));
		oppName.clear();
		oppName.sendKeys(value);
		return this;
	}
	
	public SalesPage inputAmount(String value)
	{
		WebElement oppAmt = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label[text()='Amount']/following-sibling::div//input[@name ='Amount']")));
		oppAmt.clear();
		oppAmt.sendKeys(value);
		return this;
	}
	
	public SalesPage clickOnAddLead() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='Add Leads']"))).click();
		Thread.sleep(2000);
		return this;
	}
	
	public SalesPage clickOnSubmitButton()
	{
		WebElement clkSubmit = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Submit']")));
		js.executeScript("arguments[0].click();", clkSubmit);
		return this;
	}
	
	public SalesPage clickonSaveButton() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//button/span[text()='Save'])[last()]"))).click();
		Thread.sleep(1000);
		return this;
	}
	
	public SalesPage clickonSaveOpportunity() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@name='SaveEdit' and text()='Save']"))).click();
		Thread.sleep(1000);
		return this;
	}
	
	public SalesPage clickonNextButton() throws InterruptedException
	{
		WebElement clkNext = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Next']")));
		js.executeScript("arguments[0].click();", clkNext);
		Thread.sleep(1000);
		return this;
	}
	
	public SalesPage clickonUploadAttachment()
	{
		WebElement clkUpload = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='Upload Files']")));
		clkUpload.click();
		return this;
	}
	
	public SalesPage ClickOnCampaignName(String campName) throws InterruptedException
	{
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//a[contains(@title,'" + campName + "')])[1]")));
		ele.click();
		return this;
	}
	
	public SalesPage clickOndeleteLead(String fName, String lName)
	{
		String lead = fName + " " + lName;
		WebElement delLead = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//a[text()='" +lead+ "']/following::td//a[@role='button'])[1]")));
		delLead.click();
		delLead = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='Delete']/..")));
		delLead.click();
		return this;
	}
	
	public SalesPage errValidationOnCaseCreation(String errMsg)
	{
		try
		{
			WebElement errTag = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//ul[@class='errorsList']/li")));
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
			wait.until(ExpectedConditions.visibilityOf(output));
			String outputValue = output.getText();
			
			if (outputValue.contains(oppName))
			{
				System.out.println("Stage 1 Passed");
				WebElement ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[contains(text(),'Opportunity')]/following-sibling::slot/slot[@slot='primaryField']")));
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
			wait.until(ExpectedConditions.visibilityOf(output));
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
				WebElement ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//a[contains(@class,'forceOutputLookup')])[1]")));
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
