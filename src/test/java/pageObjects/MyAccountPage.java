package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//NOTE == if Login is successful -- then Only we have to click on Logout link = this Logout Link we need to add inside MyAccountPage object class 


public class MyAccountPage extends BasePage{

	
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	@FindBy(xpath="//h2[text()='My Account']") //My Account Page heading
	WebElement msgHeading;
	
	@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']")
	WebElement btnLogout;
	
	public boolean isMyAccountPageExists()   // then return typr will be true or false   // Not validation method but will verify if page Exist & display return true , if not exist return exception 
	{
		try
		{
			return msgHeading.isDisplayed();     // based on return type we will doo validation in test method
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	
	public void clickLogout()
	{
		btnLogout.click();
	}
	
}
