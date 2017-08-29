package supportLibrariers;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
	public String scrnshotLink;
	public static int screenshotNo=0;
	WebDriver driver;
	
	public ScreenShot(WebDriver dirver)
	{
		this.driver=dirver;		
	}
	public String TakeScreenshot() throws IOException
	{
		String filename=ExcelTestResult.screenshotlink+"Screenshot_"+ ++screenshotNo+".png";
		File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(filename));
		return filename;
		
	}
	
	

}
