package salesforce.pages;

import salesforce.base.SalesforceBase;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;

public class ServiceConsolePage extends SalesforceBase {
	
	public ServiceConsolePage()
	{
//		this.driver = driver;
//		this.node = node;
		getDriver().switchTo().defaultContent();
	}
	
	public NewDashboardPage clickOnNewDashboard()
	{
		try {
			webDriverWait4VisibilityOfEle(getDriver().findElementByXPath("//div[text()='New Dashboard']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new NewDashboardPage();
	}
	
	public ServiceConsolePage clickOnNewButton()
	{
		try {
			webDriverWait4VisibilityOfEle(getDriver().findElementByXPath("//div[text()='New']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceConsolePage();
	}
		
	public ServiceConsolePage setSubscriptionFrequencyAs(String value)
	{
		try {
			WebElement ele = webDriverWait4VisibilityOfEle(getDriver().findElementByXPath("//legend[text()='Frequency']/following::div//span[text()='"+value+"']"));
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage clickOnRecentFiles()
	{
		try {
			WebElement ele = webDriverWait4VisibilityOfEle(getDriver().findElementByXPath("//a[@title='Recent']"));
			ele.click();
			solidWait(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage selectTask(String value) throws InterruptedException
	{
		HomePage homepage = new HomePage();
		try
		{
			homepage.selectTaskFromNavigationControl(value);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			homepage.selectTaskFromNavigationControl(value);
		}
		return this;
	}
	
	public ServiceConsolePage clickonLastModifiedFilesColumn() throws InterruptedException
	{
		try {
			WebElement col = webDriverWait4ElementToBeClickable(getDriver().findElementByXPath("//span[@title='Last Modified Date']/parent::a"));
			col.click();
			solidWait(2);
			WebElement ele = webDriverWait4VisibilityOfEle(getDriver().findElementByXPath("//span[@title='Last Modified Date']/parent::a/following-sibling::span"));
			String sort = ele.getText();
			if (sort.contains("Sorted Ascending"))
			{
				col.click();
				solidWait(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage selectTime(String value)
	{
		try {
			WebElement dd_time = webDriverWait4VisibilityOfEle(getDriver().findElementByXPath("//select[@id='time']"));
			Select time = new Select(dd_time);
			time.selectByVisibleText(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage clickonSaveButton()
	{
		try {
			webDriverWait4ElementToBeClickable(getDriver().findElementByXPath("//button[@title='Save']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public String getContactRequestNumber()
	{
		String ConReqNum = null;
		solidWait(3);
		try {
			WebElement getConNum = webDriverWait4VisibilityOfEle(getDriver().findElementByXPath("//span[text()='Contact Request Number']/parent::div/following-sibling::div//span[@class='uiOutputText']"));
			ConReqNum = getConNum.getText();
			if (!ConReqNum.isEmpty() == true)
			{
				System.out.println("Contact Request Number Created: "+ConReqNum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ConReqNum;
	}
	
	public String selectLastModifiedFile()
	{
		String downloadedFileName = null;
		try {
			WebElement getFileName = webDriverWait4VisibilityOfEle(getDriver().findElementByXPath("//table/tbody/tr[1]/th//div//span[@data-aura-class='uiOutputText']"));
			downloadedFileName = getFileName.getText();
			WebElement lastModifiedfile = webDriverWait4ElementToBeClickable(getDriver().findElementByXPath("//table/tbody/tr[1]/th//a/following::td[3]//a[@role='button']"));
			lastModifiedfile.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return downloadedFileName;
	}
	
	public ServiceConsolePage selectFileOptionAs(String value)
	{
		try {
			WebElement selectoption = webDriverWait4VisibilityOfEle(getDriver().findElementByXPath("//div[@role='button' and @title='"+value+"']/.."));
			selectoption.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage clickToOpenFile(String fileName, String fileOption)
	{
		try
		{
			List<WebElement> listofFiles = getDriver().findElementsByXPath("//table/tbody/tr");
			for (int i=1; i<listofFiles.size(); i++)
			{
				WebElement file = webDriverWait4VisibilityOfEle(getDriver().findElementByXPath("//table/tbody/tr["+i+"]/th//a"));
				String getFileName = file.getText();
				if (getFileName.contains(fileName))
				{
					webDriverWait4ElementToBeClickable(getDriver().findElementByXPath("//table/tbody/tr["+i+"]/th//a/following::td[3]//a[@role='button']")).click();
					selectFileOptionAs(fileOption);
					solidWait(3);
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
	
	public ServiceConsolePage verifyNewDashboardCreation(String value)
	{
		try {
			WebElement output = getDriver().findElement(By.xpath("//span[contains(text(),'subscription')]"));
			webDriverWait4VisibilityOfEle(output);
			String outputValue = output.getText();
			if (outputValue.contains("You started a dashboard subscription"))
			{
				System.out.println("You started a dashboard subscription");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage verifyUploadedFileDetails(String fileName, String fileExtension)
	{
		try
		{
			WebElement TitleElement = webDriverWait4VisibilityOfEle(getDriver().findElementByXPath("//div[text()='File']/following-sibling::div"));
			String TitleVerification = TitleElement.getAttribute("title");	
			WebElement fileExt = webDriverWait4VisibilityOfEle(getDriver().findElementByXPath("//span[@title='File Extension']/following-sibling::div//span[@class='uiOutputText']"));
			String getFileExtension = fileExt.getText();
			if ((TitleVerification.contains(fileName)) && getFileExtension.equals(fileExtension))
			{
				System.out.println("...Uploaded file validation successful...");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage verifyPublicLinkInputFieldIsDisabled()
	{
		try {
			webDriverWait4ElementToBeClickable(getDriver().findElementByXPath("//table/tbody/tr[1]/th//a/following::td[3]//a[@role='button']")).click();
			WebElement clkLink = webDriverWait4VisibilityOfEle(getDriver().findElementByXPath("//input[@name='publicLinkURL']"));
			if (isDisabled(clkLink) == true)
			{
				getDriver().findElementByXPath("//button[@title='Close this window']").click();
				System.out.println("Public Link is disabled for inputs");
				solidWait(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage clickonPrivateDashboard()
	{
		try {
			webDriverWait4ElementToBeClickable(getDriver().findElementByXPath("(//a[@title='Private Dashboards'])[1]")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage inputShareWithUser(String userName)
	{
		try {
			WebElement ele = webDriverWait4ElementToBeClickable(getDriver().findElementByXPath("//span[text()='Share With']/following::input[@role='combobox']"));
			solidWait(1);
			actions.moveToElement(ele).click().perform();
			actions.sendKeys(Keys.ARROW_DOWN).perform();
			Thread.sleep(2000);
			ele = webDriverWait4VisibilityOfEle(getDriver().findElementByXPath("//span[text()='Share With']/following::input[@role='combobox']/following::div[@class='listContent']/ul//a//div[contains(@title,'"+userName+"')]"));
			ele.click();
			solidWait(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage clickOnNewContact(String contact)
	{
		try {
			WebElement ele = webDriverWait4ElementToBeClickable(getDriver().findElementByXPath("//input[@title='Search Contacts']"));
			solidWait(1);
			actions.moveToElement(ele).click().perform();
			actions.sendKeys(Keys.ARROW_DOWN).perform();
			solidWait(2);
			ele = webDriverWait4VisibilityOfEle(getDriver().findElementByXPath("//input[@title='Search Contacts']/following-sibling::div//span[text()='"+contact+"']"));
			ele.click();
			solidWait(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage clearExistingRecordAndAddNewShareFileWith(String userName)
	{
		try {
			webDriverWait4ElementToBeClickable(getDriver().findElementByXPath("(//span[@class='pillText']/following::a)[1]")).click();
			inputShareWithUser(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}		
	
	public ServiceConsolePage verifyContactName(String contactName)
	{
		try {
			WebElement ele = webDriverWait4VisibilityOfEle(getDriver().findElementByXPath("//span[@class='pillText']"));
			String contact = ele.getText();
			if (contact.contains(contactName))
			{
				System.out.println("Contact Created successfully: "+contact);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage inputShareAttachmentMessage(String value)
	{
		try {
			WebElement ele = webDriverWait4ElementToBeClickable(getDriver().findElementByXPath("//textArea[@class='textArea textarea']"));
			ele.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage clickOnShareAttachment()
	{
		try {
			WebElement ele = webDriverWait4ElementToBeClickable(getDriver().findElementByXPath("//span[text()='Share']/parent::button"));
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage verifyErrorMessageInShareScreen()
	{
		try {
			WebElement ele = getDriver().findElementByXPath("//ul[contains(@class,'has-error')]/li");
			String errtext = ele.getText();
			if (errtext.contains("Can't share file with the file owner"))
			{
				System.out.println("Error thrown in SHare Screen: Can't share file with the file owner");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage searchDashboardName(String value)
	{
		try {
			WebElement searchDashboard = webDriverWait4ElementToBeClickable(getDriver().findElementByXPath("//input[contains(@class,'search-text')]"));
			searchDashboard.clear();
			searchDashboard.sendKeys(value);
			searchDashboard.sendKeys(Keys.ENTER);
			solidWait(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage deleteSubscription(String dashboardName)
	{
		try {
			WebElement ele = getDriver().findElementByXPath("(//a[@title='"+dashboardName+"'])[1]//following::td[6]//button");
			webDriverWait4ElementToBeClickable(ele);
			ele.click();
			ele = webDriverWait4VisibilityOfEle(getDriver().findElementByXPath("//span[text()='Delete']/.."));
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage clickOnUploadFiles()
	{
		try {
			WebElement ele = getDriver().findElementByXPath("//a[@title='Upload Files']");
			webDriverWait4ElementToBeClickable(ele);
			ele.click();
			solidWait(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
		
	public ServiceConsolePage verifyShareAttachment(String fileName, String userName)
	{
		try
		{
			WebElement displayMsg = webDriverWait4VisibilityOfEle(getDriver().findElement(By.xpath("//span[contains(@class,'toastMessage')]")));
			String displayMsgValue = displayMsg.getText();
			System.out.println(displayMsgValue);
			String msgToValidate = "You shared "+ fileName + " with "+ userName;
			if (displayMsgValue.contains(msgToValidate))
			{
				System.out.println("...File successfully shared...");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage verifyDeleteAttachment(String fileName)
	{
		try
		{
			WebElement displayMsg = webDriverWait4VisibilityOfEle(getDriver().findElement(By.xpath("//span[contains(@class,'toastMessage')]")));
			String displayMsgValue = displayMsg.getText();
			System.out.println(displayMsgValue);
			String msgToValidate1 = "Content Document ";
			String msgToValidate2 = "was deleted";
			
			if (displayMsgValue.contains(msgToValidate1) && displayMsgValue.contains(fileName) && displayMsgValue.contains(msgToValidate2))
			{
				System.out.println("...File successfully deleted...");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage verifyDeleteSubscription(String dashboardName)
	{
		boolean flag_sort = false, flag_deleteValidation = false;
		String sortMsg;
		try
		{
			WebElement displayMsg = webDriverWait4VisibilityOfEle(getDriver().findElement(By.xpath("//span[contains(@class,'toastMessage')]")));
			String displayMsgValue = displayMsg.getText();
			System.out.println(displayMsgValue);
			
			if (displayMsgValue.contains("Dashboard was deleted"))
			{
				searchDashboardName(" ");
				flag_sort = true;
			}

//			Sorting the Table records in descending order to get new records
			if (flag_sort)
			{
				WebElement clkAccountsSort_1 = getDriver().findElementByXPath("//span[text()='Created On']/ancestor::a");
				webDriverWait4ElementToBeClickable(clkAccountsSort_1);
				clkAccountsSort_1.click();
				WebElement AccountsSort = getDriver().findElementByXPath("//span[text()='Created On']/following::span[@aria-live='assertive' and contains(text(),'Sorted')][1]");
				webDriverWait4VisibilityOfEle(AccountsSort);
				sortMsg = AccountsSort.getText();
				if (sortMsg.contains("Sorted Descending"))
				{
					flag_deleteValidation = true;
				}
				else
				{
					WebElement clkAccountsSort_2 = getDriver().findElementByXPath("//span[text()='Created On']/ancestor::a");
					webDriverWait4ElementToBeClickable(clkAccountsSort_2);
					clkAccountsSort_2.click();
					WebElement AccountsSort_2 = getDriver().findElementByXPath("//span[text()='Created On']/following::span[@aria-live='assertive' and contains(text(),'Sorted')][1]");
					webDriverWait4VisibilityOfEle(AccountsSort_2);
					sortMsg = AccountsSort_2.getText();
					if (sortMsg.contains("Sorted Descending"))
					{
						flag_deleteValidation = true;
					}
					else
					{
						System.out.println("Accounts are not sorted in order for validation" + ", Failed");
					}	
				}
			}
			
//			Searching for Deleted Subscription - Output Validation
			if (flag_deleteValidation)
			{
				List<WebElement> rows = getDriver().findElements(By.xpath("//table[contains(@class,'table_resizable-cols')]/tbody/tr"));
				int size = rows.size();
				for (int i = 1; i <= size; i++)
				{
					WebElement listofSubscriptions = getDriver().findElement(By.xpath("//table[contains(@class,'table_resizable-cols')]/tbody/tr["+i+"]/th//a"));
					String subscriptionNames = listofSubscriptions.getText();
				
					if (subscriptionNames.equals(dashboardName))
					{
						System.out.println("Unable to Delete Subscription, TC-Failed");
						break;
					}
					else
					{
						if (i == size)
						{
							System.out.println("Delete Subscription was successful, TC-Passed");
						}
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
	
	public ServiceConsolePage clickOnEditGoal()
	{
		try {
			WebElement ele = webDriverWait4ElementToBeClickable(getDriver().findElementByXPath("//button[@title='Edit Goal']"));
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage setGoalPrice(String value)
	{
		try {
			WebElement ele = webDriverWait4ElementToBeClickable(getDriver().findElementByXPath("//span[@id='currencyCode']/following-sibling::input"));
			ele.clear();
			ele.sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage clickonSaveGoalPrice()
	{
		try {
			WebElement ele = webDriverWait4ElementToBeClickable(getDriver().findElementByXPath("(//span[text()='Save']/ancestor::button)[last()]"));
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage validateGoalPrice(String setStrikePrice)
	{
		
		try {
			String ClosePrice = getClosePrice();
			ClosePrice = ClosePrice.replaceAll("\\D", "");
			int ClosePriceIntValue = Integer.parseInt(ClosePrice);
			
			String OpenPrice = getOpenPrice();
			OpenPrice = OpenPrice.replaceAll("\\D", "");
			int OpenPriceIntValue = Integer.parseInt(OpenPrice);
			
			int chkPrice = Integer.parseInt(setStrikePrice);
			String strikePrice = String.valueOf(chkPrice);
			int Price = ClosePriceIntValue + OpenPriceIntValue;
			if (Price < chkPrice)
			{
				clickOnEditGoal()
				.setGoalPrice(strikePrice)
				.clickonSaveGoalPrice();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public String getClosePrice()
	{
		String ClosePrice = null;
		try {
			WebElement ClosePriceTag = getDriver().findElementByXPath("//span[text()='Closed']/following-sibling::span[contains(text(),'$')]");
			ClosePrice = ClosePriceTag.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ClosePrice;
	}
	
	public String getOpenPrice()
	{
		String OpenPrice = null;
		try {
			WebElement OpenPriceTag = getDriver().findElementByXPath("//span[contains(text(),'Open')]/following-sibling::span[contains(text(),'$')]");
			OpenPrice = OpenPriceTag.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return OpenPrice;
	}
	
}
