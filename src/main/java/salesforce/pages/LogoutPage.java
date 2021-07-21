package salesforce.pages;

import salesforce.base.SalesforceBase;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogoutPage extends SalesforceBase {
	
	public LogoutPage()
	{
		this.driver = getDriver();
		driver.switchTo().defaultContent();
	}
	
	public LoginPage clickLogout()
	{
		driver.findElementByXPath("//img[@alt='User' and @title='User']/ancestor::button").click();
		webDriverWait4VisibilityOfEle(driver.findElementByLinkText("Log Out")).click();
		return new LoginPage(prop);
	}
}
