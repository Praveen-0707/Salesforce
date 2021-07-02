package salesforce.pages;

import salesforce.base.SalesforceBase;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

public class AccountsPage extends SalesforceBase {
	
	public AccountsPage(RemoteWebDriver driver, ExtentTest node)
	{
		this.driver = driver;
		this.node = node;
	}
	
	public AccountsPage searchAccount(String value)
	{
		try {
			WebElement searchAcc = driver.findElementByXPath("//input[@name='Account-search-input']");
			webDriverWait4ElementToBeClickable(searchAcc);
			searchAcc.clear();
			searchAcc.sendKeys(value);
			searchAcc.sendKeys(Keys.ENTER);
			solidWait(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AccountsPage clickOnCreateNewAccount()
	{
		try {
			webDriverWait4ElementToBeClickable(driver.findElementByXPath("//div[text()='New']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AccountsPage inputAccountName(String value)
	{
		try {
			webDriverWait4VisibilityOfEle(driver.findElementByXPath("//input[@name='Name']")).sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AccountsPage inputPhoneNumber(String phoneNumber)
	{
		try {
			webDriverWait4VisibilityOfEle(driver.findElementByXPath("//input[@name='Phone']")).sendKeys(phoneNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AccountsPage inputBillingAddress(String value)
	{
		try {
			webDriverWait4VisibilityOfEle(driver.findElementByXPath("//label[text()='Billing Street']/following-sibling::div/textarea")).sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AccountsPage inputShippingAddress(String value)
	{
		try {
			webDriverWait4VisibilityOfEle(driver.findElementByXPath("//label[text()='Shipping Street']/following-sibling::div/textarea")).sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AccountsPage inputDescription(String value)
	{
		try {
			webDriverWait4VisibilityOfEle(driver.findElementByXPath("//label[text()='Description']/following-sibling::div/textarea")).sendKeys(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AccountsPage selectType(String value)
	{
		try {
			WebElement dd_type = driver.findElement(By.xpath("//label[text()='Type']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
			webDriverWait4ElementToBeClickable(dd_type);
			dd_type.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath("(//label[text()='Type']/following::input/parent::div/following-sibling::div//lightning-base-combobox-item//span[@class='slds-truncate' and text()='"+value+"'])[1]");
			scrollToVisibleElement(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public AccountsPage selectOwnership(String value)
	{
		try {
			WebElement dd_ownership = driver.findElement(By.xpath("//label[text()='Ownership']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
			webDriverWait4ElementToBeClickable(dd_ownership);
			dd_ownership.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath("(//label[text()='Ownership']/following::input/parent::div/following-sibling::div//lightning-base-combobox-item//span[@class='slds-truncate' and text()='"+value+"'])[1]");
			scrollToVisibleElement(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public AccountsPage selectIndustry(String value)
	{
		try {
			WebElement dd_industry = driver.findElement(By.xpath("//label[text()='Industry']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
			webDriverWait4ElementToBeClickable(dd_industry);
			dd_industry.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath("(//label[text()='Industry']/following::input/parent::div/following-sibling::div//lightning-base-combobox-item//span[@class='slds-truncate' and text()='"+value+"'])[1]");
			scrollToVisibleElement(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public AccountsPage selectUpsellOpportunity(String value)
	{
		try {
			WebElement dd_upsellOpp = driver.findElement(By.xpath("//label[text()='Upsell Opportunity']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
			webDriverWait4VisibilityOfEle(dd_upsellOpp);
			dd_upsellOpp.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath("(//label[text()='Upsell Opportunity']/following::input/parent::div/following-sibling::div//lightning-base-combobox-item//span[@class='slds-truncate' and text()='"+value+"'])[1]");
			scrollToVisibleElement(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public AccountsPage selectActive(String value)
	{
		try {
			WebElement dd_active = driver.findElement(By.xpath("//label[text()='Active']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
			webDriverWait4VisibilityOfEle(dd_active);
			dd_active.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath("(//label[text()='Active']/following::input/parent::div/following-sibling::div//lightning-base-combobox-item//span[@class='slds-truncate' and text()='"+value+"'])[1]");
			scrollToVisibleElement(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public AccountsPage selectCustomerPriority(String value)
	{
		try {
			WebElement dd_CustomerPriority = driver.findElement(By.xpath("//label[text()='Customer Priority']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
			webDriverWait4VisibilityOfEle(dd_CustomerPriority);
			dd_CustomerPriority.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath("(//label[text()='Customer Priority']/following::input/parent::div/following-sibling::div//lightning-base-combobox-item//span[@class='slds-truncate' and text()='"+value+"'])[1]");
			scrollToVisibleElement(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public AccountsPage selectSLA(String value)
	{
		try {
			WebElement dd_SLA = driver.findElement(By.xpath("//label[text()='SLA']/following-sibling::div//input[@class='slds-input slds-combobox__input' and @type ='text']"));
			webDriverWait4VisibilityOfEle(dd_SLA);
			dd_SLA.click();
			solidWait(1);
			WebElement ele = driver.findElementByXPath("(//label[text()='SLA']/following::input/parent::div/following-sibling::div//lightning-base-combobox-item//span[@class='slds-truncate' and text()='"+value+"'])[1]");
			scrollToVisibleElement(ele);
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AccountsPage clickRefreshButton()
	{
		try {
			webDriverWait4ElementToBeClickable(driver.findElementByName("refreshButton")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AccountsPage clickOnSaveButton()
	{
		try {
			webDriverWait4ElementToBeClickable(driver.findElementByXPath("//button[@name='SaveEdit' and text()='Save']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AccountsPage clickOnAccountNameHeader()
	{
		try {
			WebElement AccountsSorting = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//span[text()='Sort']/following::span[text()='Account Name']/preceding-sibling::span/.."));
			AccountsSorting.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AccountsPage editAccount(String accName)
	{
		try {
			WebElement editAcc = webDriverWait4VisibilityOfEle(driver.findElementByXPath("(//a[text()='"+accName+"'])[1]//following::td//a[@role='button']"));
			editAcc.click();
			editAcc = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//div[@role='button' and @title='Edit']/.."));
			editAcc.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AccountsPage deleteAccount(String accName)
	{
		try {
			WebElement delAcc = webDriverWait4VisibilityOfEle(driver.findElementByXPath("(//a[text()='" + accName + "'])[1]//following::td//a[@role='button']"));
			delAcc.click();
			delAcc = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//div[@role='button' and @title='Delete']/.."));
			delAcc.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public AccountsPage createAccountValidation(String accName)
	{
		try
		{
			WebElement output = driver.findElement(By.xpath("//span[contains(text(),'Account')]//a"));
			webDriverWait4VisibilityOfEle(output);
			String outputValue = output.getText();
			
			if (outputValue.contains(accName))
			{
				System.out.print("Account " + outputValue + " was created" + ", Passed");
			}
			else
			{
				System.out.print("Unable to create Account" + ", Failed");
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return this;
	}
	
	public AccountsPage editAccountValidation(String accName, String phNum)
	{
		String outputValueSplit = null;
		String outputphno = null;
		
		try
		{
			WebElement output = driver.findElement(By.xpath("//span[contains(text(),'Account') and contains(@class,'toastMessage')]"));
			webDriverWait4VisibilityOfEle(output);
			String outputValue = output.getText();
			if (outputValue.contains(accName))
			{
				System.out.println(outputValue);
				solidWait(3);
				WebElement editedVal = webDriverWait4VisibilityOfEle(driver.findElementByXPath("(//a[text()='"+accName+"'])[1]//following::td[2]//span[contains(@class,'forceOutputPhone')]"));
				String editedValue = editedVal.getText();
				outputphno = editedValue;
				if (editedValue.contains("-"))
				{
					outputValueSplit = editedValue.split("\\-")[0];
					outputValueSplit = outputValueSplit.replace("(", "");
					outputValueSplit = outputValueSplit.replace(")", "");
					outputValueSplit = outputValueSplit.replace(" ", "");
					outputphno = outputValueSplit + editedValue.split("\\-")[1];
					System.out.println(outputphno);
				}
				
				if (phNum.equals(outputphno))
				{
					System.out.println("Phone No validated" + ", Passed");
				}
				else
				{
					System.out.print("Unable to validate Phone No" + ", Failed");
				}
			}
			else
			{
				System.out.print("Unable to edit Account" + ", Failed");
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return this;
	}
	
	public AccountsPage deleteAccountValidation(String accName)
	{
		try
		{
			WebElement output = driver.findElement(By.xpath("//span[contains(text(),'Account') and contains(@class,'toastMessage')]"));
			webDriverWait4VisibilityOfEle(output);
			String outputValue = output.getText();
			if (outputValue.contains(accName))
			{
				outputValue = outputValue.split("\\.")[0];
				System.out.println(outputValue + ", Passed");
			}
			else
			{
				System.out.println("Unable to delete Account" + ", Failed");
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return this;
	}
	
	public AccountsPage sortAccountsByName()
	{
		String sortMsg = null;
		boolean flag = false;
		
		try
		{
			WebElement AccountsSorting = webDriverWait4ElementToBeClickable(driver.findElementByXPath("//span[text()='Sort']/following::span[text()='Account Name']/preceding-sibling::span/.."));
			AccountsSorting.click();
			solidWait(3);
			WebElement AccountsSort = webDriverWait4VisibilityOfEle(driver.findElementByXPath("//span[@aria-live='assertive' and contains(text(),'Sorted')]"));
			sortMsg = AccountsSort.getText();
			if (sortMsg.contains("Sorted Ascending"))
			{
				System.out.println("Accounts are sorted" + ", Passed");
				flag = true;
			}
			else
			{
				driver.findElementByXPath("//span[text()='Sort']/following::span[text()='Account Name']/preceding-sibling::span/..").click();
				webDriverWait4VisibilityOfEle(AccountsSort);
				sortMsg = AccountsSort.getText();
				if (sortMsg.contains("Sorted Ascending"))
				{
					System.out.println("Accounts are sorted");
					flag = true;
				}
				else
				{
					System.out.println("Accounts are not sorted" + ", Failed");
				}
			}
			
	//		WebElements comparison post sorting
			if (flag)
			{		
				List<WebElement> rows = driver.findElements(By.xpath("//table[contains(@class,'uiVirtualDataTable')]/tbody/tr"));
				int size = rows.size();
				System.out.println("Row Count: " +size);
				String[] actual_order = new String[size-1];
				String[] sorted_order = new String[size-1];
				for (int i=1; i<size; i++)
				{
					WebElement listofAcc_Names = driver.findElement(By.xpath("//table[contains(@class,'uiVirtualDataTable')]/tbody/tr["+i+"]/th//a"));
					listofAcc_Names.sendKeys(Keys.PAGE_DOWN); 	//scrolls page dynamically adjusted to Web Table content
					String str = listofAcc_Names.getText();
					actual_order[i-1] = str;
					sorted_order[i-1] = str;
				}
				
				Arrays.sort(sorted_order); 		// sorting the Array in ascending order
				int length = actual_order.length;
	//			int length1 = sorted_order.length;
				System.out.println("Array Count: " +length);
				for (int j=0; j<=length-1; j++)
				{
					if (actual_order[j].equals(sorted_order[j]))
					{
						if (j==length-1) {
						System.out.println("Accounts are displayed in ascending order" + ", Passed");}
					}
					else
					{
						System.out.println("Unable to sort Accounts" + ", Failed");
					}
				}
			}
		}
		catch (Exception ex)
		{
			System.out.println("TC Failed");
			System.out.println("Exception Trace...");
			ex.printStackTrace();
		}
		return this;
	}
}
