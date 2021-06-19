package salesforce.testcases;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.AccountsPage;
import salesforce.pages.LoginPage;

public class SortingAccounts extends SalesforceBase {
	
	@Test(groups= {"Accounts"})
	public void editAccount(String accName, String phNum) throws InterruptedException
	{
		new LoginPage(driver).enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll().searchApp("Account").clickOnAccount();
		
		AccountsPage accPg = new AccountsPage(driver);
		accPg.searchAccount(accName);
		
		String sortMsg = null;
		boolean flag = false;
		
//		Fetches run-time sorting value for validation
		WebElement AccountsSort = driver.findElementByXPath("//span[@aria-live='assertive' and contains(text(),'Sorted')]");
		wait.until(ExpectedConditions.visibilityOf(AccountsSort));
		sortMsg = AccountsSort.getText();
		if (sortMsg.contains("Sorted Ascending"))
		{
			System.out.println("Accounts are sorted" + ", Passed");
			flag = true;
		}
		else
		{
			driver.findElementByXPath("//span[text()='Sort']/following::span[text()='Account Name']/preceding-sibling::span/..").click();
			wait.until(ExpectedConditions.visibilityOf(AccountsSort));
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
}
