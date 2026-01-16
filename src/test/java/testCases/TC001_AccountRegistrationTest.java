package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

// throughout the project make everything all methods as public == so that we can access everywhere 
//we always execute Test Class == see now we have only 1 test method in test class 


public class TC001_AccountRegistrationTest extends BaseClass {
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()  // from home page click account link then click registration link
	{
		// it this whole TC suppose if any any line Got failed bcoz of element Not visible , or bcoz of message if TC failed u will get exception ==Put everything in try-catch() block
		// & we need to log error log NOT info log == means logger.error()
		// logs fill-- Not deleted if we run test cases again & again new logs gets appended in the same old log file & if the memory size of log file gets full then new log file will create automatically
		//
		    logger.info("***** Starting TC001_AccountRegistrationTest ******");  // this same message will be display in log file 
		    
		    try
		    {
		    HomePage hp = new HomePage(driver);
		    hp.clickMyAccount();
		    logger.info("Clicked on MyAccount Link ");  // this same message will be display in log file 

		    hp.clickRegister();
		    logger.info("Clicked on Register Link ");  // this same message will be display in log file 

		    AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		    
		    
		 /*   
		    regpage.setFirstName("John");
		    regpage.setLastName("David");
		    regpage.setEmail("abcjohndavies@gmail.com");     // randomly generated the email as everytime we needed new mail id 
		    regpage.setTelephone("23123123");
		    
		    //String password = randomAlphaNumeric();
		    
		    regpage.setPassword("xyz12356");
		    regpage.setConfirmPassword("xyz12356");
		    
		    regpage.setPrivacyPolicy();    // checkBox
		    regpage.clickContinue();       // click button
		  
		    
		    // validation part confirm the message
		    
		   String confmsg = regpage.getConfirmation();   // 
		   Assert.assertEquals(confmsg, "Your Account Has Been Created!"); 
		   
		   */
		    
		    
		   // note ==  if we run Run as testNg test  1st time   ==== it will passed
		  // Now run again 2nd time same TestNG test  === it will failed == Bcoz same email id we Cannot pass for every acc registration
		   // everyTime we have to pass different email address == so generate it randomly everytime
		   // 2 kinds of testData 
		   // 1-- before exeecuting testCase we will prepare our own Test data static data == That will Never Change
		   // 2-- Dynamic Data === that we will create Randomly at the RunTime
		  
		   
		   // How to generate Random Data === we have to create our own User-defined java methods( which will randomly generate some String) == NO - built-in methods available 
		   	// so randomString()  == we can use for all blanks 
		    
		    
		    logger.info("Providing customer details.....");
		    
		    regpage.setFirstName(randomString().toUpperCase());   //randomString().toUpperCase() == random string will converted in upperCase 
		    regpage.setLastName(randomString().toUpperCase());
		    regpage.setEmail(randomString()+"@gmail.com");       // // randomly generated the email
		    regpage.setTelephone(randomNumber());
		    
		    
		    String password = randomAlphaNumberic();
		    
		    regpage.setPassword(password);
		    regpage.setConfirmPassword(password);  // but as per requirment password & confirm password should be same but callling randomAlphaNumberic() will give new value each time
		   
		    regpage.setPrivacyPolicy(); 
		    regpage.clickContinue();
		    
		    
		    // validation part confirm the message
		    logger.info("Validating expected message.....");
		    String confmsg = regpage.getConfirmation();   // 
		    if(confmsg.equals("Your Account Has Been Created!"))   // if(confmsg.equals("Your Account Has Been Created!!!"))  == we intentioanlly failed & checked the logger.error message also
		    {
		    	Assert.assertTrue(true);
		    }
		    else
		    {
		    	
		    	logger.error("Test failed...."); 
		    	logger.debug("Debug logs....");
		    	Assert.assertTrue(false);
		    }
		
		    //Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");  //hard assert failed so rest of the down statement will NOT execute so catch block not executed   // if asssertion got failed then i want to log error message in the log file 
		    }
		    catch(Exception e)
		    {
		    
		    	Assert.fail();
		    }
			   
		    logger.info("***** Finished TC001_AccountRegistrationTest ******");
	}
	
	
}

