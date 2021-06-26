package salesforce.pages;

import salesforce.base.SalesforceBase;

import java.awt.event.KeyEvent;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ServiceConsolePage extends SalesforceBase {
	
	public ServiceConsolePage(RemoteWebDriver driver)
	{
		this.driver = driver;
		driver.switchTo().defaultContent();
	}
	
	public NewDashboardPage clickOnNewDashboard()
	{
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[text()='New Dashboard']"))).click();
		return new NewDashboardPage(driver);
	}
		
	
	
	public ServiceConsolePage setSubscriptionFrequencyAs(String value)
	{
		WebElement ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//legend[text()='Frequency']/following::div//span[text()='"+value+"']")));
		ele.click();
		return this;
	}
	
	public ServiceConsolePage clickOnRecentFiles() throws InterruptedException
	{
		WebElement ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[@title='Recent']")));
		ele.click();
		Thread.sleep(2000);
		return this;
	}
	
	public ServiceConsolePage selectTask(String value) throws InterruptedException
	{
		HomePage homepage = new HomePage(driver);
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
		WebElement col = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@title='Last Modified Date']/parent::a")));
		col.click();
		Thread.sleep(2000);
		WebElement ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[@title='Last Modified Date']/parent::a/following-sibling::span")));
		String sort = ele.getText();
		if (sort.contains("Sorted Ascending"))
		{
			col.click();
			Thread.sleep(2000);
		}
		return this;
	}
	
	public ServiceConsolePage selectTime(String value) throws InterruptedException
	{
		WebElement dd_time = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//select[@id='time']")));
		Select time = new Select(dd_time);
		time.selectByVisibleText(value);
		return this;
	}
	
	public ServiceConsolePage clickonSaveButton()
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@title='Save']"))).click();
		return this;
	}

//	public ServiceConsolePage clickOnPublicLinkFromFileOptions()
//	{
//		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//table/tbody/tr[1]/th//a/following::td[3]//a[@role='button']"))).click();
//		WebElement clkLink = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='Public Link']/..")));
//		clkLink.click();
//		return this;
//	}
	
	public String selectLastModifiedFile()
	{
		WebElement getFileName = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//table/tbody/tr[1]/th//div//span[@data-aura-class='uiOutputText']")));
		String downloadedFileName = getFileName.getText();
		WebElement lastModifiedfile = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//table/tbody/tr[1]/th//a/following::td[3]//a[@role='button']")));
		lastModifiedfile.click();
		return downloadedFileName;
	}
	
	public ServiceConsolePage selectFileOptionAs(String value)
	{
		WebElement selectoption = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='"+value+"']/..")));
		selectoption.click();
		return this;
	}
	
	public ServiceConsolePage clickToOpenFile(String fileName, String fileOption)
	{
		try
		{
			List<WebElement> listofFiles = driver.findElementsByXPath("//table/tbody/tr");
			for (int i=0; i<listofFiles.size(); i++)
			{
				WebElement file = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//table/tbody/tr["+i+"]/th//a")));
				String getFileName = file.getText();
				if (getFileName.equalsIgnoreCase(fileName))
				{
					wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//table/tbody/tr["+i+"]/th//a/following::td[3]//a[@role='button']"))).click();
					selectFileOptionAs("View File Details");
					Thread.sleep(3000);
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
		WebElement output = driver.findElement(By.xpath("//span[contains(text(),'subscription')]"));
		wait.until(ExpectedConditions.visibilityOf(output));
		String outputValue = output.getText();
//		System.out.println(outputValue);
		if (outputValue.contains("You started a dashboard subscription"))
		{
			System.out.println("You started a dashboard subscription");
		}
		return this;
	}
	
	public ServiceConsolePage verifyUploadedFileDetails(String fileName, String fileExtension) throws InterruptedException
	{
		try
		{
			WebElement TitleElement = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[text()='File']/following-sibling::div")));
			String TitleVerification = TitleElement.getAttribute("title");	
			WebElement fileExt = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[@title='File Extension']/following-sibling::div//span[@class='uiOutputText']")));
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
	
//	public String clickOnDownloadFromFileOptions()
//	{
//		WebElement getFileName = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//table/tbody/tr[1]/th//div//span[@data-aura-class='uiOutputText']")));
//		String downloadedFileName = getFileName.getText();
//		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//table/tbody/tr[1]/th//a/following::td[3]//a[@role='button']"))).click();
//		WebElement clkLink = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='Download']/..")));
//		clkLink.click();
//		return downloadedFileName;
//	}
	
//	public ServiceConsolePage clickOnShareFromFileOptions()
//	{
//		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//table/tbody/tr[1]/th//a/following::td[3]//a[@role='button']"))).click();
//		WebElement clkLink = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='Share']/..")));
//		clkLink.click();
//		return this;
//	}
	
	public ServiceConsolePage verifyPublicLinkInputFieldIsDisabled() throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//table/tbody/tr[1]/th//a/following::td[3]//a[@role='button']"))).click();
		WebElement clkLink = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//input[@name='publicLinkURL']")));
		if (isDisabled(clkLink) == true)
		{
			driver.findElementByXPath("//button[@title='Close this window']").click();
			System.out.println("Public Link is disabled for inputs");
			Thread.sleep(2000);
		}
		return this;
	}
	
	public ServiceConsolePage clickonPrivateDashboard()
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//a[@title='Private Dashboards'])[1]"))).click();
		return this;
	}
	
	public ServiceConsolePage inputShareWithUser(String userName) throws InterruptedException
	{
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Share With']/following::input[@role='combobox']")));
		Thread.sleep(1000);
		actions.moveToElement(ele).click().perform();
		actions.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(2000);
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Share With']/following::input[@role='combobox']/following::div[@class='listContent']/ul//a//div[contains(@title,'"+userName+"')]")));
		ele.click();
		Thread.sleep(2000);
		return this;
	}
	
	public ServiceConsolePage clearExistingRecordAndAddNewShareFileWith(String userName) throws InterruptedException
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//span[@class='pillText']/following::a)[1]"))).click();
		inputShareWithUser(userName);
		return this;
	}		
	
	public ServiceConsolePage inputShareAttachmentMessage(String value) throws InterruptedException
	{
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//textArea[@class='textArea textarea']")));
		ele.sendKeys(value);
		return this;
	}
	
	public ServiceConsolePage clickOnShareAttachment() throws InterruptedException
	{
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[text()='Share']/parent::button")));
		ele.click();
		return this;
	}
	
	public ServiceConsolePage verifyErrorMessageInShareScreen()
	{
		WebElement ele = driver.findElementByXPath("//ul[contains(@class,'has-error')]/li");
		String errtext = ele.getText();
		if (errtext.contains("Can't share file with the file owner"))
		{
			System.out.println("Error thrown in SHare Screen: Can't share file with the file owner");
		}
		return this;
	}
	
	public ServiceConsolePage searchDashboardName(String value) throws InterruptedException
	{
		WebElement searchDashboard = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//input[contains(@class,'search-text')]")));
		searchDashboard.clear();
		searchDashboard.sendKeys(value);
		searchDashboard.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		return this;
	}
	
	public ServiceConsolePage deleteSubscription(String dashboardName) throws InterruptedException
	{
		WebElement ele = driver.findElementByXPath("(//a[@title='"+dashboardName+"'])[1]//following::td[6]//button");
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.click();
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Delete']/..")));
		ele.click();
		return this;
	}
	
	public ServiceConsolePage clickOnUploadFiles() throws InterruptedException
	{
		WebElement ele = driver.findElementByXPath("//a[@title='Upload Files']");
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.click();
		Thread.sleep(3000);
		return this;
	}
		
	public ServiceConsolePage verifyShareAttachment(String fileName, String userName) throws InterruptedException
	{
		try
		{
			WebElement displayMsg = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]"))));
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
	
	public ServiceConsolePage verifyDeleteAttachment(String fileName) throws InterruptedException
	{
		try
		{
			WebElement displayMsg = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]"))));
			String displayMsgValue = displayMsg.getText();
			System.out.println(displayMsgValue);
			String msgToValidate1 = "Content Document ";
			String msgToValidate2 = "was deleted";
			
			if (displayMsgValue.contains(msgToValidate1) && displayMsgValue.contains(fileName) && displayMsgValue.contains(msgToValidate2))
			{
				System.out.println(displayMsgValue);
				System.out.println("...File successfully deleted...");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this;
	}
	
	public ServiceConsolePage verifyDeleteSubscription(String dashboardName) throws InterruptedException
	{
		boolean flag_sort = false, flag_deleteValidation = false;
		String sortMsg;
		try
		{
			WebElement displayMsg = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]"))));
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
				WebElement clkAccountsSort_1 = driver.findElementByXPath("//span[text()='Created On']/ancestor::a");
				wait.until(ExpectedConditions.elementToBeClickable(clkAccountsSort_1));
				clkAccountsSort_1.click();
				WebElement AccountsSort = driver.findElementByXPath("//span[text()='Created On']/following::span[@aria-live='assertive' and contains(text(),'Sorted')][1]");
				wait.until(ExpectedConditions.visibilityOf(AccountsSort));
				sortMsg = AccountsSort.getText();
				if (sortMsg.contains("Sorted Descending"))
				{
					flag_deleteValidation = true;
				}
				else
				{
					WebElement clkAccountsSort_2 = driver.findElementByXPath("//span[text()='Created On']/ancestor::a");
					wait.until(ExpectedConditions.elementToBeClickable(clkAccountsSort_2));
					clkAccountsSort_2.click();
					WebElement AccountsSort_2 = driver.findElementByXPath("//span[text()='Created On']/following::span[@aria-live='assertive' and contains(text(),'Sorted')][1]");
					wait.until(ExpectedConditions.visibilityOf(AccountsSort_2));
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
				List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@class,'table_resizable-cols')]/tbody/tr"));
				int size = rows.size();
				for (int i = 1; i <= size; i++)
				{
					WebElement listofSubscriptions = driver.findElement(By.xpath("//table[contains(@class,'table_resizable-cols')]/tbody/tr["+i+"]/th//a"));
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
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@title='Edit Goal']")));
		ele.click();
		return this;
	}
	
	public ServiceConsolePage setGoalPrice(String value)
	{
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@id='currencyCode']/following-sibling::input")));
		ele.clear();
		ele.sendKeys(value);
		return this;
	}
	
	public ServiceConsolePage clickonSaveGoalPrice()
	{
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//span[text()='Save']/ancestor::button)[last()]")));
		ele.click();
		return this;
	}
	
	public ServiceConsolePage validateGoalPrice(String setStrikePrice)
	{
		
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
		return this;
	}
	
	public String getClosePrice()
	{
		WebElement ClosePriceTag = driver.findElementByXPath("//span[text()='Closed']/following-sibling::span[contains(text(),'$')]");
		String ClosePrice = ClosePriceTag.getText();
		return ClosePrice;
	}
	
	public String getOpenPrice()
	{
		WebElement OpenPriceTag = driver.findElementByXPath("//span[contains(text(),'Open')]/following-sibling::span[contains(text(),'$')]");
		String OpenPrice = OpenPriceTag.getText();
		return OpenPrice;
	}
	
}
