package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{  // 

	// Test method we need to create // other setup() tearDown() methods are coming from BaseClass itself 
	
	/*
	
	@Test
	public void verify_login()   // this TC is for valid credentials== we need to pass static data , means its separate TC for login with valid creds 
	{
		logger.info("****** Starting TC002_LoginTest******");
		
		try
		{
		//HomePage
		HomePage hp = new HomePage(driver);    // this driver is coming from BaseClass  & passing to HomePage class
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login
		LoginPage lp = new LoginPage(driver);
	//	lp.setEmail(null);      // this email & password we already keept i config file and in BaseClass we already loaded config.prpoerty file 
	//	lp.setPassword(null);  // we already have valid email & password in config.properties file 
		// we Should Pass hardCoded data in TestCase == data either maintain in excel file / properties file 
		// reading url from properties file.  ex---  p.getProperty("email")   p.getProperty("password")   p.getProperty("pass Key")== it will return the value
		
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//MyAccount 
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
	//	Assert.assertEquals(targetPage, true, "Login Failed");     // Assert.assertEquals(actaul value, expected Value); 
		// we can also use different assert 
		
		Assert.assertTrue(targetPage);  // Assert.assertTrue(targetPage) // here targetPage we are expecting true
		}
		catch(Exception e)     // if any exception comes catch block will execute means TC is failed 
		{
			Assert.fail();
		}
		
		logger.info("******* Finished TC002_LoginTest *********");
		
		// its separate TC for login with valid creds == if you want to cover invalid & valid creds the  doo dataDriver LoginTest
	}
	
	*/
	
	
	//Once after login it goes to MyAccount page ==== and validate text MyAccount in MyAccount page 
	//==== but in DDT additionally to take Another input  we have to  again Go Back to LoginPage === so here we need to click on logout Btn
   // == then again it will comeback to login page & ready to take another Input 

	
	@Test(groups={"Sanity", "Master"}) 
	public void verify_login()   
	{
		logger.info("****** Starting TC002_LoginTest******");
		
		try
		{
		//HomePage
		HomePage hp = new HomePage(driver);    // this driver is coming from BaseClass  & passing to HomePage class
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login
		LoginPage lp = new LoginPage(driver);
	//	lp.setEmail(null);      // this email & password we already keept i config file and in BaseClass we already loaded config.prpoerty file 
	//	lp.setPassword(null);  // we already have valid email & password in config.properties file 
		// we Should Pass hardCoded data in TestCase == data either maintain in excel file / properties file 
		// reading url from properties file.  ex---  p.getProperty("email")   p.getProperty("password")   p.getProperty("pass Key")== it will return the value
		
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//MyAccount 
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
	//	Assert.assertEquals(targetPage, true, "Login Failed");     // Assert.assertEquals(actaul value, expected Value); 
		// we can also use different assert 
		
		Assert.assertTrue(targetPage);  // Assert.assertTrue(targetPage) // here targetPage we are expecting true
		}
		catch(Exception e)     // if any exception comes catch block will execute means TC is failed 
		{
			Assert.fail();
		}
		
		logger.info("******* Finished TC002_LoginTest *********");
		
		// its separate TC for login with valid creds == if you want to cover invalid & valid creds the  doo dataDriver LoginTest
	} 
		
}
