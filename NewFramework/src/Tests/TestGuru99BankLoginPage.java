package Tests;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
//import org.testng.annotations.Parameters;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;


import PageObjects.HomePageOFGURUBank;
import PageObjects.LoginPage;
import supportLibrariers.ExcelDataExtract;
import supportLibrariers.ExcelTestResult;
import org.uncommons.reportng.HTMLReporter;


@Listeners (org.uncommons.reportng.HTMLReporter.class)
public class TestGuru99BankLoginPage  {
 
	WebDriver driver;
	LoginPage objLoginPage;	
	HomePageOFGURUBank objHomePageOFGURUBank;
	String runnig_TC;
	ExcelTestResult objExcelTestResult;
	

	
	
@BeforeTest
//@Parameters({"browser"})
public void beforetest(ITestContext m) throws IOException
{
	
	Reporter.log("Before Test function called");
	System.setProperty("webdriver.chrome.driver", "D:\\ChromeDriver\\chromedriver.exe");
	ChromeOptions option=new ChromeOptions();
	option.addArguments("start-maximized");
	option.addArguments("disable-infobars");
	option.addArguments("disable-notifications");			
	driver=new ChromeDriver(option);	
	Reporter.log("Chrome Driver is launched");
	objExcelTestResult=new ExcelTestResult(m.getCurrentXmlTest().getSuite().getName(),driver);
	Reporter.log("Test Result object is Created");

}
@DataProvider(name = "Testdata")
public  Object[][] getTestData(Method m) throws FileNotFoundException, IOException {

       Reporter.log("Data Provider function called and running Test ="+m.getName());
    
       return ExcelDataExtract.getTestData(m.getName());

}
@DataProvider(name = "Testdata1")
public  Object[][] getTestData1(Method m) throws FileNotFoundException, IOException {

       Reporter.log("Data Provider function called and running Test ="+m.getName());
       
       return ExcelDataExtract.getTestData(m.getName());

}


@Test(priority=0)
public void Guru99BankLoginPage_HeaderCheck(Method m) throws FileNotFoundException, IOException
  {
	objExcelTestResult.setTestName(m.getName());
	
	Reporter.log("Guru99BankLoginPage_HeaderCheck method is called");
	driver.get("http://demo.guru99.com/V4/");
	Reporter.log("URL IS LAUNCHED http://demo.guru99.com/V4/");
	
	objLoginPage=new LoginPage(driver,objExcelTestResult);
	String ss=objLoginPage.GetText_check_guruBank99_hdr();
	if(ss.equalsIgnoreCase("Guru99 Bank"))
	{
		Reporter.log("The Value is dislayed "+ss);
		
	}
  }

@Test(dataProvider = "Testdata",priority=1)
public void Guru99BankLoginPage(String sUsername,String pass,Method m) throws FileNotFoundException, IOException
  {
	objExcelTestResult.setTestName(m.getName());
	
	Reporter.log("Guru99BankLoginPage This test is called ");
	//objLoginPage=new LoginPage(driver);
	//objLoginPage.check_guruBank99_hdr();
	
	objLoginPage.loginToguru99(sUsername, pass);
	System.out.println("IT WILL PRINT");
	  
  }


@Test(dataProvider="Testdata1",priority=2)
public void Check_ManagerID(String Username,Method m) throws FileNotFoundException, IOException, InterruptedException
{
	objExcelTestResult.setTestName(m.getName());
	objHomePageOFGURUBank =new HomePageOFGURUBank(driver,objExcelTestResult);
	String value=objHomePageOFGURUBank.ManegerIDText();
	Assert.assertTrue(value.contains(Username),"The Manager ID is not the same");
	Reporter.log("The Manager Id "+value+" contains the "+Username);
	Thread.sleep(2500);
}


	
  @AfterTest
  public void afterTest() {
	  driver.quit();
	  Reporter.log("Driver Closed");
	  System.out.println("after Test");
  }


}
