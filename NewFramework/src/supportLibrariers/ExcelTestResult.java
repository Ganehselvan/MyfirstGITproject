package supportLibrariers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.openqa.selenium.WebDriver;



public class ExcelTestResult {
	
	static public String filepath;
	static public String path=null;
	static public String screenshotlink=null;
	 String suitename="";
	 WebDriver driver;
	
	
	
	public ExcelTestResult(String suitename, WebDriver driver) throws IOException
	{
		/*Properties GlobalProperty=new Properties();
		FileInputStream inload=new FileInputStream("config.properties");
		GlobalProperty.load(inload);
		filepath=GlobalProperty.getProperty("TestResultPath");*/
		filepath=System.getProperty("user.dir")+"\\TestResults\\";
		this.suitename=suitename;
		this.driver=driver;
		CreateExcelFileReport();
		
	}
	
	
	//static public String 
	
	public void CreateExcelFileReport() throws IOException
	{
		String time=new SimpleDateFormat("dd-MMM-yyyy_hh.mm.ssa").format(new Date());

	
		File file=new File(filepath+suitename+"/"+suitename+time);
		if(!file.exists())
		{
			file.mkdirs();
		}
		path=filepath+suitename+"/"+suitename+time+"/TestResult_"+time+".xls";
		screenshotlink=filepath+suitename+"\\"+suitename+time+"\\";
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet shet=wb.createSheet("Report");
		
		Cell cell=shet.createRow(0).createCell(2);
		cell.setCellType(CellType.STRING);
		cell.setCellValue("Automation Report");
		shet.autoSizeColumn(2);
		cell=shet.createRow(1).createCell(1);
		cell.setCellType(CellType.STRING);
		cell.setCellValue("No Of Tests in the Suite");
		shet.autoSizeColumn(1);
		cell=shet.createRow(2).createCell(1);
		cell.setCellType(CellType.STRING);
		cell.setCellValue("No Of Tests Passed");
		shet.autoSizeColumn(1);
		cell=shet.createRow(3).createCell(1);
		cell.setCellType(CellType.STRING);
		cell.setCellValue("No Of Tests Failed");
		shet.autoSizeColumn(1);
		
		shet.setDisplayGridlines(false);
		try {
			wb.write(new FileOutputStream(new File(path)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wb.close();
			
	}
	
	public void setMessage(String message,String parameter,String status) throws FileNotFoundException, IOException
	{
		HSSFWorkbook wb=new HSSFWorkbook(new FileInputStream(path));
		HSSFSheet sheet= wb.getSheet("Report");
		int row=sheet.getLastRowNum()+1;
		Cell cell=sheet.createRow(row).createCell(2);
		cell.setCellType(CellType.STRING);
		cell.setCellValue(message);
		sheet.autoSizeColumn(2);
		Cell cell1=sheet.getRow(row).createCell(3);
		cell1.setCellType(CellType.STRING);
		cell1.setCellValue(parameter);
		Cell cell2=sheet.getRow(row).createCell(4);
		cell2.setCellType(CellType.STRING);
		cell2.setCellValue(status);
		
		if(!status.equalsIgnoreCase("PASS"))
		{
			
			String filename=new ScreenShot(driver).TakeScreenshot();
			
			CellStyle style=wb.createCellStyle();
			Font f=wb.createFont();
			f.setUnderline(Font.U_SINGLE);
			f.setColor(IndexedColors.BLUE.getIndex());
			style.setFont(f);;
			Cell cell4=sheet.getRow(row).createCell(5);
			cell4.setCellType(CellType.STRING);
			cell4.setCellValue(filename);
			sheet.autoSizeColumn(5);;
			
			CreationHelper helper=wb.getCreationHelper();
			HSSFHyperlink hype=(HSSFHyperlink)helper.createHyperlink(HyperlinkType.URL);
			hype.setAddress(filename);
			cell4.setHyperlink(hype);
			cell4.setCellStyle(style);
			
			
		}
		sheet.setDisplayGridlines(false);
		
		wb.write(new FileOutputStream(path));
		wb.close();
		
		
	}
	
	public void setTestName(String Keywordname) throws FileNotFoundException, IOException
	{
		HSSFWorkbook wb=new HSSFWorkbook(new FileInputStream(path));
		HSSFSheet sheet= wb.getSheet("Report");
		int row=sheet.getLastRowNum()+1;
		
		Cell cell=sheet.createRow(row).createCell(1);
		cell.setCellType(CellType.STRING);
		cell.setCellValue(Keywordname);
		sheet.setDisplayGridlines(false);
		wb.write(new FileOutputStream(path));
		wb.close();
		
	
	}
	
	

}
