package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
//import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.ImageHtmlEmail;
//import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	
	
	public ExtentSparkReporter sparkReporter ;    // UI of the report
	public ExtentReports extent;               // populate common info on the project
	public ExtentTest test;           // creating test case entries in the report and update status of the test methods 
	
	String repName;
	
	public void onStart(ITestContext testContext) {  // it trigger automatically whenever your TC are started execution 
		
		/*
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");   // specify in which format we have to generate the date 
		Date dt = new Date();
		String currentdatetimestamp = df.format(dt);    // to generate the timeStamp
		
		*/ 
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  // time stamp  above 3 statements we combined in 1 single statement 
		
		repName = "Test-Report-" + timestamp + ".html";                    // create report Name with current timestamp this timeStamp will be dynamically generated 
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);  // specify location of the report
		
	//	sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/myReport.html");  // specific loaction where u want to see report // here we are hardCoding the name of the report 
		// Problem suppose 1st round i have executed my test it generate Report with same Name -- 2nd time we i run again it will generate the report with the same name bcoz we have hardCoded this name of report (name --myReport.html)
		// everytime when you generate the new report-- the new report will save with the same name == problem we Cannot maintained history of the report 
		// To overcome this give the name with the timestamp
		// How to create this report with the timeStamp dynamically so we maintained the report history -- see in framework -- 
		
		sparkReporter.config().setDocumentTitle("opencart Automation Report");  // Title of report
		sparkReporter.config().setReportName("opencart Functional Testing");   // name of the report
		sparkReporter.config().setTheme(Theme.DARK);                  // We can change Theme from DARK  to STANDARD to which will change background color of report
	//	sparkReporter.config().setTheme(Theme.STANDARD);  
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);    // 
		
		extent.setSystemInfo("Application", "opencart");   // project specific detials
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));  // this method will return the current user of the system who is running TC-testerName
		extent.setSystemInfo("Environment", "QA");    //In above SS this Information we are hardCoding now (LocalHost, Env , Tester Name, OS ) this info also we can get at the RunTime automatically by using certain predefined methods ---- we will see in framework

		
		
		String os = testContext.getCurrentXmlTest().getParameter("os");  // here we have capture xml file parameters tag values dynamically in report 
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");   // getting from xml file //  .getCurrentXmlTest()  -- will return the xml file 
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();   // will capture the groups which are there insdie <include tag  in XML file & display in report which method belongs to which group
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());    // 1st checking if group names is available or NOT then this method will add groups Names to the report
		}
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());   // create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());      //to display groups in report
		test.log(Status.PASS, result.getName()+ "  got successfully executed"); // update status p/f/s
		
	}
	
	public void onTestFailure(ITestResult result) {                     // whenever the test Method got failed onTestFailure will trigger 
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); 
		
		test.log(Status.FAIL, result.getName()+ " got failed");   // onnTestFailure === I want to Also add failure Screenshot capture Failure Screenshot & same should display in report itself ---- we will see in framework
        test.log(Status.INFO, result.getThrowable().getMessage());
        
        try {
        	String imgPath= new BaseClass().captureScreen(result.getName()); // it will return the location of the image   // in BaseClass add method captureScreen(  )  because captureScreen() method is common for TC
        	test.addScreenCaptureFromPath(imgPath);        //test -represnt report  // this line will attach SS to the report                 //Screenshot also we need to save with timeStamp 
        
        } catch (IOException e1) {                    // Before Attaching ScreenShot to the report we have to 1st  capture the Screenshot  then only we can attach it to report 
        	e1.printStackTrace();                  // in try-catch bcoz if SS is NOT propertly taken or SS is NOT available -- still it will try to attach ss to report & u will get FileNOTFound Exception 
        }

	}      
	
	//e1.printStackTrace() -- mean it will just display the exception msg in console 
	// result.getName()  === mean name of the test 
	
	public void onTestSkipped(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());     // message why it got skipped 
		
	}
	
	// as soon as my report got generated after execution immediately my report should open automatically i NOT want to open manually 
	
	public void onFinish(ITestContext testContext)    // it is mandatory will update whole thing in the report
	{
		extent.flush();      // this write all the information from standard repositories to output view 
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;    //By this above blue code automatically report will be open in browser we NOT have to open it manually 
		File extentReport = new File(pathOfExtentReport);                                  // it representing extentReport File
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());   // this method will open extentReport ON the Browser automatically we NOT want to open it manually 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//After execution completed This Comment code will sent the  generated Report in email immediately 
       // we need to add javaEmail  dependency from mvn repository in pom.xml -- search java email == add Apache Common Email

/*   
		try {
		URL url = new URL("file:///"+ System.getProperty("user.dir")+"\\reports\\" + repName);
		
		// Create the email message 
		ImageHtmlEmail email = new ImageHtmlEmail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.googlemail.com");                     // this will work only for gmail id 
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com", "password"));   // provide who is sending email there email id & password 
		email.setSSLOnConnect(true);
		email.setFrom("pavanoltraining@gmail.com");   //Sender email id 
		email.setSubject("Test Results");                   // subject of the email
		email.setMsg("Please find Attached Report......");    // in email body whatever msg  we want to write send
		email.addTo("pavanKumar.busyqa@gmail.com ");    // Receiver    // to whom you want to send email 
		email.attach(url, "extent report", "please check report.....");
		email.send();         // send the email
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
	
		
		*/
		
	}
		
	
}
