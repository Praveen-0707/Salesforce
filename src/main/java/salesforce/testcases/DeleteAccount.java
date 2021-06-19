package salesforce.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.AccountsPage;
import salesforce.pages.LoginPage;

public class DeleteAccount extends SalesforceBase {
	
	@BeforeTest
	public void setFileName() {
		excelFileName = "Accounts";
		excelSheetName = "DeleteAccounts";
	}
	
	@Test(dataProvider = "getData", groups= {"Accounts"})
	public void deleteAccount(String accName) throws InterruptedException
	{
		
		new LoginPage(driver)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		
		.searchApp("Account").clickOnAccount();
		
		AccountsPage accPg = new AccountsPage(driver);
		accPg.searchAccount(accName);
		
		WebElement ele;
		
//		Selecting drop down value as Delete
		ele = driver.findElementByXPath("(//a[text()='" + accName + "'])[1]//following::td//a[@role='button']");
		ele.click();
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='Delete']/..")));
		ele.click();
		
		deletePopUpConfirmation();
		
//		Output validation
		WebElement output = driver.findElement(By.xpath("//span[contains(text(),'Account') and contains(@class,'toastMessage')]"));
		wait.until(ExpectedConditions.visibilityOf(output));
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

}
