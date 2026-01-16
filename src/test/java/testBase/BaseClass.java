package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;   //Log4j
import org.apache.logging.log4j.Logger;       // Log4j

/*
//So whatever thing is Commonly required for Multiple Test Cases=== we will separate them into Another Class  === To avoid duplication === so under testCases Package create a Base Class --- is a Base Class/parent class  for all the test cases classes, Contains 
Reusable Methods -- which are Commonly required for all the Test cases -- 
soo we can achieve reusability & avoid duplication
// Now Every Test Class must be extends BaseClass
// Log Levels ====  All  <  Trace  < Debug  < Info  < Warn  < Error  < Fatal  <  Off
// 
// for each level there is method in logger
//

== Now Create separate config.properties file == where we will create all the common variables which are required for all the  Test cases ,properties file follow Key & value pair == NO syntax noo 

Keep the config.properties file in src/test/resources

*/



public class BaseClass {

		
 public static WebDriver driver;   // Global class variable 
 public Logger logger;     // Log4j
 public Properties p;
 
	@BeforeClass(groups= {"Sanity", "Regression", "Master"})   // these also groups bcoz these setup() ,teardown() method required for all cases
	@Parameters({"os", "browser"})          //this way we can pass parameter from xml file to setup() method at the RunTime 
	public void setup(String os, String br) throws IOException
	{
		
		//Loading config.properties file
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(file);    // loaded file by using this p == we can capture the data from the properties file 
		
		//== but no specific class bcoz we are running multiple classses so at run time its should take class name dynamically
		
		logger = LogManager.getLogger(this.getClass()); //loG4J2  // it will load the log4j.xml file  //logger == variable we use to generate log for every TestCase // this.getClass() === this will get the ClassName &  
		
		switch(br.toLowerCase())  // this switch() case will decide which browser we have to launch based on the parameter we passed
		{
		case "chrome": driver = new ChromeDriver(); break;
		case "edge":  driver= new EdgeDriver();  break;
		case "firefox": driver= new FirefoxDriver(); break;
		default : System.out.println("Invalid browser name...."); return;  // return mean if will totally exit from the  execution bcoz if browser itself is Invalid we NOO need to continue the futher stuff
		
		} // if not used  return it will continue execution
		
		driver.manage().deleteAllCookies();   // we delete all the cookies from webpage 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	//	driver.get("https://tutorialsninja.com/demo/index.php");    // we hardcode this url here =-- instead now we will get this url from properties file 
		
		driver.get(p.getProperty("appURL2"));  // reading url from properties file.  ex---  p.getProperty("email")   p.getProperty("password")
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups ={"Sanity", "Regression", "Master"})
	public void tearDown()
	{
		driver.quit();
		
		
	}
	
	
	
	
	// very Important New Concept === How  to generate Random String in Java 
		// RandomStringUtils.randomAlphabetic(pass how many character you want in string )
		// RandomStringUtils == is predefined Class in from commons-lang3 dependency 
		// in same Class 1 method can call another method 
		
		public String randomString()   //if we call this method it will generate  random string everytime and return
		{
			String generatedstring = RandomStringUtils.randomAlphabetic(5);
			return generatedstring;
			
		}
		
		public String randomNumber()   // this method will generate random Number 
		{
			String generatednumber = RandomStringUtils.randomNumeric(10);
			return generatednumber;
		}
		
		public String randomAlphaNumberic()  // this method will generate random  randomAlphaNumberic -- we can fill password by this 
		{
			String generatedstring = RandomStringUtils.randomAlphabetic(3);
			String generatednumber = RandomStringUtils.randomNumeric(3);
			return (generatedstring+"@"+generatednumber);
			
		}
		
		public String captureScreen(String tname) throws IOException    // will capture the SS of the  test where it will fail=== whenever the TC Failure is happened then we have to capture the SS  this method 
		{
			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());   // even SS also we need to save with timestamp , if same TC we run multiple time everytime we will get New SS So it with timestamp
			
			TakesScreenshot takesScreenshot = (TakesScreenshot)driver;           //TakesScreenshot is interface 
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
			File targetFile = new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
			
			return targetFilePath;       // we returned targetFilePath where SS is located based on this targetFilePath only we will attach SS to the report
		}
		
		

}
