package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	// constructor 
	public HomePage(WebDriver driver) {   //  this driver we will pass from actual TC
		super(driver);         // this driver is passing to the parent clASS constructor 
		
	}
	
	// from the child class we can invoke immediate parent class variable , method & constructor == by using super keyword 
	
	// Locators
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(linkText ="Login")   // Login link added in step5
	WebElement linkLogin;
	
	
	// action methods 
	public void clickMyAccount()        
	{
		lnkMyaccount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	
	public void clickLogin()
	{
		linkLogin.click();
	}
}
