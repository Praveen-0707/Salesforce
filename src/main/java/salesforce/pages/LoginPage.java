package salesforce.pages;

import salesforce.base.SalesforceBase;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class LoginPage extends SalesforceBase {
	
	public LoginPage(Properties prop)
	{
		this.driver = getDriver();
		this.prop = prop;
	}
	
	public LoginPage enterUsername()
	{
		try {
			webDriverWait4VisibilityOfEle(driver.findElement(By.id("username"))).sendKeys(prop.getProperty("username"));
			reportStep("Username entered as: "+prop.getProperty("username"), "Pass");
		} catch (Exception e) {
			reportStep("Username not entered in Login Page", "Fail");
			e.printStackTrace();
			throw new RuntimeException();
		}
		return this;
	}
	
	public LoginPage enterPassword()
	{
		try {
			webDriverWait4VisibilityOfEle(driver.findElement(By.id("password"))).sendKeys(prop.getProperty("password"));
			reportStep("Password entered as: "+prop.getProperty("password"), "Pass");
		} catch (Exception e) {
			reportStep("Password not entered in Login Page", "Fail");
			e.printStackTrace();
			throw new RuntimeException();
		}
		return this;
	}
	
	public HomePage clickLogin()
	{
		try {
			webDriverWait4ElementToBeClickable(driver.findElement(By.id("Login"))).click();
//			option-1
//			WebElement pageLoad = wait.until(ExpectedConditions.visibilityOf(getDriver().findElementByXPath("//div[@class='linkElements']/a[text()='Switch to Lightning Experience']")));
//			pageLoad.click();
			
//			option-2
			WebElement pageLoad1 = driver.findElementByXPath("//div[@class='linkElements']/a[text()='Switch to Lightning Experience']");
			boolean isDisplayed = pageLoad1.isDisplayed();
			if (isDisplayed)
			{
				js.executeScript("arguments[0].click();", pageLoad1);
				reportStep("Successfully Logged into Saleforce", "Pass", false);
//				pageLoad1.click();
			}
		} catch (NoSuchElementException e) {
			reportStep("Salesforce Classic Login is not displayed", "Info");
//			System.out.println(e.getMessage());
//			throw new RuntimeException();
		}
		catch (Exception e) {
			reportStep("Login into Saleforce application failed", "Fail");
//			System.out.println(e.getMessage());
//			throw new RuntimeException();
		}
		
		return new HomePage();
	}
}
