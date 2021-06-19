package salesforce.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import salesforce.base.SalesforceBase;
import salesforce.pages.AccountsPage;
import salesforce.pages.LoginPage;

public class EditAccount extends SalesforceBase {
	
	@BeforeTest
	public void setFileName() {
		excelFileName = "Accounts";
		excelSheetName = "EditAccounts";
	}
	
	@Test(dataProvider = "getData", groups= {"Accounts"})
	public void editAccount(String accName, String phNum) throws InterruptedException
	{
		new LoginPage(driver).enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll().searchApp("Account").clickOnAccount();
		
		AccountsPage accPg = new AccountsPage(driver);
		accPg.searchAccount(accName);
		
		WebElement ele;
		String outputValueSplit = null;
		String outputphno = null;
//		String phNum = "987456321";
		
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//a[text()='"+accName+"'])[1]//following::td//a[@role='button']")));
		ele.click();
		ele = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@role='button' and @title='Edit']/..")));
		ele.click();
		
		accPg.inputPhoneNumber(phNum).selectType("T").selectIndustry("H");
		
		ele = driver.findElementByXPath("//label[text()='Billing Street']/following-sibling::div/textarea");
		js.executeScript("arguments[0].scrollIntoView();", ele);
//		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		accPg.selectCustomerPriority("L").selectSLA("S")
		.selectUpsellOpportunity("N").selectActive("N").clickOnSaveButton();
		
//		Output validation
		WebElement output = driver.findElement(By.xpath("//span[contains(text(),'Account') and contains(@class,'toastMessage')]"));
		wait.until(ExpectedConditions.visibilityOf(output));		// explicit wait
		String outputValue = output.getText();

		if (outputValue.contains(accName))
		{
			System.out.println(outputValue);
			Thread.sleep(3000);
			WebElement editedVal = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//a[text()='"+accName+"'])[1]//following::td[2]//span[contains(@class,'forceOutputPhone')]")));
			String editedValue = editedVal.getText();
//			System.out.println(editedValue);
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
}
