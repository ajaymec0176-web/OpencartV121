package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	
	//DataProvider 1
	
	@DataProvider(name="LoginData") 
	public String [][] getData() throws IOException 
	{
		String path = ".\\testData\\Opencart_LoginData_FrameworkDesign_YT.xlsx";  // taking xl file from testData 
		
		ExcelUtility xlutil = new ExcelUtility(path); //creating an object for XLUtility 
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);     //Size of 2-Dimensional array is Same as  No.of Rows and No. of columns in excel sheet
		
		                    
		String logindata[][] = new String[totalrows][totalcols];  // created for two dimension array which can store
				
	                          // Now we want to read Data from excel sheet & store it in 2-Dimensional array 
		
		for(int i=1; i<=totalrows; i++)   //1     // read the data from xl storing in two dimensional array  
		{
			for(int j=0; j<totalcols; j++)    //0   i is rows j is col
			{
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j);     // 1 , 0
			}
		}
		
		return logindata;   // returning two dimension array 
	}
	
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4 
	
}
