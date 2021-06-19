package salesforce.testcases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import salesforce.base.SalesforceBase;
import salesforce.pages.LoginPage;

public class WorkTypeGroupSortingByName extends SalesforceBase {

	@Test(groups= {"WorkTypeGroups"})
	public void sortWorkTypeGroup() throws InterruptedException {

		WebElement ele;

		new LoginPage(driver)
		.enterUsername().enterPassword().clickLogin()
		
		.clickToggleButton().clickViewAll()
		.searchApp("Work Type Groups").clickOnWorkTypeGroups();
		Thread.sleep(2000);
		
		List<String> namesSet1 = new ArrayList<String>(); 
		List<WebElement> elements = driver.findElementsByXPath("//table[contains(@class,'slds-table forceRecordLayout')]/tbody/tr");
		int size = elements.size();
		for (int i=1; i<=size; i++)
		{
			WebElement workGroupNames = driver.findElementByXPath("//table[contains(@class,'slds-table forceRecordLayout')]/tbody/tr["+i+"]/th//a");
			String workGroupNamesBeforeSort = workGroupNames.getText();
			namesSet1.add(workGroupNamesBeforeSort);
			
		}
		Collections.sort(namesSet1);
		System.out.println("Set1 names after sort: " + namesSet1);
		
//		clicks on Name Sort
		ele = wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@title='Work Type Group Name']/parent::a")));
		ele.click();
		Thread.sleep(3000);
		
		List<String> namesSet2 = new ArrayList<String>(); 
		List<WebElement> wtgToSort = driver.findElementsByXPath("//table[contains(@class,'slds-table forceRecordLayout')]/tbody/tr");
		int cnt = wtgToSort.size();
		for (int j=1; j<=cnt; j++)
		{
			WebElement workGroupNamesToSort = driver.findElementByXPath("//table[contains(@class,'slds-table forceRecordLayout')]/tbody/tr["+j+"]/th//a");
			String workGroupNamesAfterSort = workGroupNamesToSort.getText();
			namesSet2.add(workGroupNamesAfterSort);
		}
		System.out.println("Set2 name without sort: " + namesSet2);
		
		boolean isEqual = namesSet1.equals(namesSet2);
		System.out.println(isEqual);
		if (isEqual)
		{
			System.out.println("Names are sorted in order." + namesSet2);
		}
		else
		{
			System.out.println("Unable to sorted the names");
		}
	}

}
