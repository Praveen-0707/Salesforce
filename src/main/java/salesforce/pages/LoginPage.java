package salesforce.pages;

import salesforce.base.SalesforceBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends SalesforceBase {
	
	public LoginPage(ChromeDriver driver)
	{
		this.driver = driver;
	}
	
	public LoginPage enterUsername()
	{
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("username")))).sendKeys("cypress@testleaf.com");
		return this;
	}
	
	public LoginPage enterPassword()
	{
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password")))).sendKeys("Selbootcamp@123");
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
			WebElement pageLoad1 = driver.findElementByLinkText("Switch to Lightning Experience");
			boolean isDisplayed = pageLoad1.isDisplayed();
			if (isDisplayed)
			{
				js.executeScript("argument[0].click();", pageLoad1);
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
