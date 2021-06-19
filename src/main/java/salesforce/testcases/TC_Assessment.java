package salesforce.testcases;

import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;
import salesforce.pages.ServiceConsolePage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC_Assessment extends SalesforceBase {
	
	@BeforeTest
	public void setFileName() {
		excelFileName = "Accounts";
		excelSheetName = "CreateAccounts";
	}
	
	@Test(dataProvider = "getData")
	public void createNewAccount(String accName) throws InterruptedException
	{
		
		WebElement ele;
		String dashboardName = "PraveenRaj_Workout";
		String descr = "Testing";
		boolean flag_sort = false, flag_deleteValidation = false;
		String sortMsg;
		
		new LoginPage(driver)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Service Console").clickOnServiceConsole()
		.selectTask("Home");
		
		ServiceConsolePage SC = new ServiceConsolePage(driver);
		
//		Fetching Close and Open Values and adding them
		WebElement ClosePriceTag = driver.findElementByXPath("//span[text()='Closed']/following-sibling::span[contains(text(),'$')]");
		String ClosePrice = ClosePriceTag.getText();
		ClosePrice = ClosePrice.replaceAll("\\D", "");
		int ClosePriceIntValue = Integer.parseInt(ClosePrice); 
		
		WebElement OpenPriceTag = driver.findElementByXPath("//span[contains(text(),'Open')]/following-sibling::span[contains(text(),'$')]");
		String OpenPrice = OpenPriceTag.getText();
		OpenPrice = OpenPrice.replaceAll("\\D", "");
		int OpenPriceIntValue = Integer.parseInt(OpenPrice); 
		
		int StrikePrice = 10000;
		int Price = ClosePriceIntValue + OpenPriceIntValue;
		if (Price < StrikePrice)
		{
			ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@title='Edit Goal']")));
			ele.click();
			
			ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@id='currencyCode']/following-sibling::input")));
			ele.clear();
			ele.sendKeys("10000");
			
			ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("(//span[text()='Save']/ancestor::button)[last()]")));
			ele.click();
		}
		
		SC.selectTask("Dashboards").clickOnNewDashboard();
		
//		Switching to Frame to input values
		WebElement frames = driver.findElementByXPath("(//iframe[@title='dashboard'])[last()]");
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frames));
		Thread.sleep(3000);
		
		SC.inputDashboardName(dashboardName).inputDashboardDescr(descr).clickOnCreateButton();
		
		driver.switchTo().defaultContent();		//Switching back to Window from Frame
		Thread.sleep(3000);
		
//		Switching to Frame-1 for Clicking on Done button
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
		
		SC.clickonDoneButton();
		Thread.sleep(3000);
		
//		Validating the New DashBoard title - Clicks on Subscribe
		WebElement TitleElement = driver.findElementByXPath("//div[@class='slds-page-header__name-title']//span[contains(text(),'Dashboard')]/following-sibling::span");
		wait.until(ExpectedConditions.visibilityOf(TitleElement));
		String TitleVerification = TitleElement.getText();					
		if (TitleVerification.contains(dashboardName))
		{
			SC.clickOnSubscribeButton();
			driver.switchTo().defaultContent();
		}
		
//		Input Values
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//legend[text()='Frequency']/following::div//span[text()='Daily']")));
		ele.click();
		
		SC.selectTime("10:00 AM").clickonSaveButton();
		
//		Validating if Subscription is created Successfully
		WebElement output = driver.findElement(By.xpath("//span[contains(text(),'subscription')]"));
		wait.until(ExpectedConditions.visibilityOf(output));
		String outputValue = output.getText();
		System.out.println(outputValue);
		
		if (outputValue.contains("You started a dashboard subscription"))
		{
//			Closes the Subscription Tab
			ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[contains(@title,'"+dashboardName+"') and contains(@title,'Close')]")));
			ele.click();
		}
		
//		Clicks on DashBoard tab
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@title='Dashboards']")));
		ele.click();
		
		SC.clickonPrivateDashboard().searchDashboardName(dashboardName);
		
//		Deletes the Subscription
		ele = driver.findElementByXPath("(//a[@title='"+dashboardName+"'])[1]//following::td[6]//button");
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.click();
				
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='Delete']/..")));
		ele.click();
		
		deletePopUpConfirmation();
		
		WebElement displayMsg = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(@class,'toastMessage')]"))));
		String displayMsgValue = displayMsg.getText();
		System.out.println(displayMsgValue);
		
		if (displayMsgValue.contains("Dashboard was deleted"))
		{
			SC.searchDashboardName(" ");
			flag_sort = true;
		}

//		Sorting the Table records in descending order to get new records
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
		
//		Searching for Deleted Subscription - Output Validation
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
 }
}
