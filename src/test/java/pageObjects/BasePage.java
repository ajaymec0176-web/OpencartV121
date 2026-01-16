package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {  //  this BasePage is extended into every PageObject class

	
	WebDriver driver;     //BasePage class == which contains only webdriver variable and only constructor this BasePage is extended into every PageObject class
	
	public BasePage(WebDriver driver)   // this driver is received from the child class 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
}
