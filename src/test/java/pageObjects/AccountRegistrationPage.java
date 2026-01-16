package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*

//1 TC === Account registration   === so  2 Page Object classes we need to create here   ( Homepage)  and (registration page)
Step1 --- launch our application 
Step2 --- click my A/C link( Homepage) --- then click on register page(Homepage) ===>  you get registration form fill it click Continue Button (registration page) ===> then you get Confirmation message 
IN the  PageObject Class we Should NOT doo any Validation , validation we do inside the  TestCase Classes


*/

public class AccountRegistrationPage extends BasePage {

	// constructor 
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		
	}
	
	// Locators 
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLasttname;
	
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPolicy;
	
	@FindBy(xpath="//input[@class='btn btn-primary']")
	WebElement btnContinue;
	

	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")   // once your account is successfully registered the we get this message , its validation point 
	WebElement msgConfirmation;       // after click on continue button verify confirmation message , its validation point  but in PO Class we NOT doo any validation
	
	
	// Action Methods 
	
	public void setFirstName(String fname)    // this we will pass from actual TC
	{
		txtFirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLasttname.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String tel)
	{
		txtTelephone.sendKeys(tel);
	}
	
	
	public void setPassword(String pwd)  
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd)  // same  String pwd bcoz password & confirm password is same 
	{
		txtConfirmPassword.sendKeys(pwd);
	}
	
	public void setPrivacyPolicy()
	{
		chkPolicy.click();
	}
	
	public void clickContinue()   // different ways
	{
		
		//sol1
		btnContinue.click();      // click continue button 
		
		// Important Different ways to click a button in selenium -- Bocz .click() method sometimes NOT work it will through ElementInterceptable Exception 
		// different ways to click btnContinue button  Webelement 
		// is .click() method NOT working then use JavascriptExecutor
		
		/*
		//Sol2
		btnContinue.submit();   
		
		//Sol3
		Actions act = new Actions(driver);
		act.moveToElement(btnContinue).click().build().perform();
		
		//Sol4 
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("argumnets[0].click()",btnContinue );
		
		//Sol5
		btnContinue.sendKeys(Keys.RETURN);
		
		//Sol6
		WebDriverWait mywait = new WebDriverWait(driver,Duration.ofSeconds(10) );
		mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
		
		*/
		
	}
	
	public String getConfirmation() {     // based on this return value we will do validation inside the TC 
		
		try {
			return msgConfirmation.getText();  // capturing Text value & returning it 
		} catch (Exception e) {
			
			return (e.getMessage());            //if account regist got failed then it will through execption message will be return; 
		}
		
	}
	
}
