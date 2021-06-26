package salesforce.pages;

import salesforce.base.SalesforceBase;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends SalesforceBase {
	
	public LoginPage(RemoteWebDriver driver, Properties prop)
	{
		this.driver = driver;
		this.prop = prop;
	}
	
	public LoginPage enterUsername()
	{
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("username")))).sendKeys(prop.getProperty("username"));
		return this;
	}
	
	public LoginPage enterPassword()
	{
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password")))).sendKeys(prop.getProperty("password"));
		return this;
	}
	
	public HomePage clickLogin()
	{
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("Login")))).click();
		
		try {
//			option-1
//			WebElement pageLoad = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class='linkElements']/a[text()='Switch to Lightning Experience']")));
//			pageLoad.click();
			
//			option-2
			WebElement pageLoad1 = driver.findElementByXPath("//div[@class='linkElements']/a[text()='Switch to Lightning Experience']");
			boolean isDisplayed = pageLoad1.isDisplayed();
			if (isDisplayed)
			{
				js.executeScript("arguments[0].click();", pageLoad1);
//				pageLoad1.click();
			}
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return new HomePage(driver);
	}
}
