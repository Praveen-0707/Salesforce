package salesforce.pages;

import salesforce.base.SalesforceBase;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

public class LoginPage extends SalesforceBase {
	
	public LoginPage(RemoteWebDriver driver, Properties prop, ExtentTest node)
	{
		this.driver = driver;
		this.prop = prop;
		this.node = node;
	}
	
	public LoginPage enterUsername()
	{
		try {
			webDriverWait4VisibilityOfEle(driver.findElement(By.id("username"))).sendKeys(prop.getProperty("username"));
			reportStep("Username entered as: "+prop.getProperty("username"), "Pass", false);
		} catch (Exception e) {
			reportStep("Username not entered in Login Page", "Fail");
			e.printStackTrace();
		}
		return this;
	}
	
	public LoginPage enterPassword()
	{
		try {
			webDriverWait4VisibilityOfEle(driver.findElement(By.id("password"))).sendKeys(prop.getProperty("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public HomePage clickLogin()
	{
		try {
			webDriverWait4ElementToBeClickable(driver.findElement(By.id("Login"))).click();
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
		
		return new HomePage(driver, node);
	}
}
