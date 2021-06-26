package salesforce.pages;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import salesforce.base.SalesforceBase;

public class AccountDetails extends SalesforceBase {
	
	public AccountDetails(RemoteWebDriver driver)
	{
		this.driver = driver;
	}
}
