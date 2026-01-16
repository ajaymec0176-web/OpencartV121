package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*

Data is valid == login success = test pass  == logout 
Data is valid == login failed == test fail

Data is invalid == login success == test fail  == logout 
Data is invalid == login failed  == test pass 

// we can doo DDT for any page Not only a/c login  , account registration page also we can do DDT
/// ddt -- mean any Scenario or TC we want to test with different sets of Data   
*/


public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class , groups="Datadriven")  // getting data provider from different //But if DataProvider Method & Test Method is in Different Class & packages then Syntax 
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException     // so after putting dataProvider name in @Test(  ) annotation now this method knows that data is coming from dataPrvider method
	{
		logger.info("****** starting TC003_LoginDDT ********");
		
		try
		{
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login
		LoginPage lp = new LoginPage(driver);     // 3 data coming from excel file with dp help == eamil, pass , expected res == valid or invalid 
	//	lp.setEmail(p.getProperty("email"));     
	//	lp.setPassword(p.getProperty("password"));   
		lp.setEmail(email);                         // this we will now get from DataProvider 
		lp.setPassword(pwd);                        // this we will now get from DataProvider 
		lp.clickLogin();
		
		//MyAccount 
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();   // if true = mean login successful  , if false == means 
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		}catch(Exception e)
		{
			Assert.fail();
		}
		
		Thread.sleep(3000);
		logger.info("***** Finished TC003_LoginDDT ********");
	}
}
