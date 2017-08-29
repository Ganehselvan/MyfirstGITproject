package PageObjects;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import supportLibrariers.ExcelTestResult;

/**
 * 
 * @author 547858
 * This LoginPage page contains all the locators and method for the Login page of the guru99.com
 *
 */


public class LoginPage {
	
	/**
	 *  The Required Driver for the this LoginPage and ExcelTestResult object  
	 *  */
	WebDriver driver;
	ExcelTestResult objExcelTestResult;
	
	
	/**
	 *  The Required locators for this LoginPage
	 *  */
	By txtbx_username=By.xpath("//*[@name='uid' and @type='text']");
	By txtbx_password=By.xpath("//*[@name='password' and @type='password']");
	By btn_login=By.xpath("//*[@name='btnLogin' and @type='submit']");
	By hdr_Gurubank=By.xpath("//h2");
	
	
	
	
	public LoginPage(WebDriver driver,ExcelTestResult  objExcelTestResult)
	{
		this.driver=driver;
		this.objExcelTestResult=objExcelTestResult;
	}
	
	/**
	 * The Required methods for this LoginPage 
	 */
	
	
	/**
	 * This Method will be used in the Test method to test the Login functionality
	 * 
	 * @param str_username
	 * @param str_password
	 * @return 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void loginToguru99(String str_username,String str_password) throws FileNotFoundException, IOException 
	{
		try
		{
		driver.findElement(txtbx_username).clear();
		objExcelTestResult.setMessage("The Username textbox is cleared", "", "PASS");
		driver.findElement(txtbx_username).sendKeys(str_username);
		objExcelTestResult.setMessage("The value entered in the Username textbox ", str_username, "PASS");
		driver.findElement(txtbx_password).clear();
		objExcelTestResult.setMessage("The Password textbox is cleared", "", "PASS");
		driver.findElement(txtbx_password).sendKeys(str_password);
		objExcelTestResult.setMessage("The value entered in the Password textbox ", str_password, "PASS");
		driver.findElement(btn_login).click();
		objExcelTestResult.setMessage("The Button Login is clicked", "", "PASS");
		}
		catch(Exception e)
		{
			objExcelTestResult.setMessage("Exception Occured in loginToguru99 Method", e.getLocalizedMessage(), "FAIL");
		}
	}
	
	
	/**
	 * This method is used to Test method to for checking the guru bank Header
	 * @return bln_flag
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public boolean check_guruBank99_hdr() throws FileNotFoundException, IOException
	{
		boolean bln_flag=false;
		WebElement element=driver.findElement(hdr_Gurubank);
		try
		{
		if(element.isDisplayed())
		{
			objExcelTestResult.setMessage("The GuruBank Header is Displayed", "", "PASS");
			bln_flag=true;
		}
		else
		{
			objExcelTestResult.setMessage("The GuruBank Header is not Displayed", "", "FAIL");
			bln_flag=false;
		}
		}
		catch(Exception e)
		{
			objExcelTestResult.setMessage("Exception Occured in check_guruBank99_hdr Method", e.getLocalizedMessage(), "FAIL");
		}
		return bln_flag;
		
	}
	public String GetText_check_guruBank99_hdr() throws FileNotFoundException, IOException
	{
		String bln_flag=null;
		WebElement element=driver.findElement(hdr_Gurubank);
		try
		{
		if(element.isDisplayed())
		{			
			bln_flag=element.getText();
			objExcelTestResult.setMessage("The GuruBank Header is Displayed and Text is ", bln_flag, "PASS");
		}
		else
		{
			objExcelTestResult.setMessage("The GuruBank Header is not Displayed", "", "FAIL");
		}
		}
		catch(Exception e)
		{
			objExcelTestResult.setMessage("Exception Occured in GetText_check_guruBank99_hdr Method", e.getLocalizedMessage(), "FAIL");
		}
		return bln_flag;
		
	}
	
	
	

}
