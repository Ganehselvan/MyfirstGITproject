package PageObjects;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import supportLibrariers.ExcelTestResult;

/**
 * 
 * @author 547858
 * This HomePageOFGURUBank page contains all the locators and method for the HomePageOFGURUBank page of the guru99.com
 *
 */


public class HomePageOFGURUBank {
	
	/**
	 *  The Required Driver for the this LoginPage and ExcelTestResult object  
	 *  */
	WebDriver driver;
	ExcelTestResult objExcelTestResult;
	
	/**
	 *  The Required locators for this LoginPage
	 *  */
	By lbl_ManagerID=By.xpath("//*[contains(text(),'Manger Id  :')]");
	
	
	
	
	public HomePageOFGURUBank(WebDriver driver,ExcelTestResult objExcelTestResult)
	{
		this.driver=driver;
		this.objExcelTestResult=objExcelTestResult;
	}
	
	/**
	 * The Required methods for this LoginPage 
	 */
	
	
	/**
	 * This method is used to Test method to for checking the guru bank Header
	 * @return bln_flag
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String ManegerIDText() throws FileNotFoundException, IOException
	{
		String returnvalue="";
		try
		{
			returnvalue= driver.findElement(lbl_ManagerID).getText().trim();
			objExcelTestResult.setMessage("The Home Page ID is Displayed adn Retrived Value is ",returnvalue, "PASS");
			
		}
		catch(Exception e)
		{
			objExcelTestResult.setMessage("Exception Occured in ManegerIDText Method", e.getLocalizedMessage(), "FAIL");
			
		}
		return returnvalue;
	}
	
	
	
	

}
