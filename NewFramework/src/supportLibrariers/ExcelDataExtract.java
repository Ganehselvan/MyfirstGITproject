package supportLibrariers;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;



public class ExcelDataExtract {
	public static DataFormatter formatter=new DataFormatter();  
	
	
	public static String getCellData(HSSFSheet sheet,int row,int col)
	{
		Cell cell=sheet.getRow(row).getCell(col);
		return formatter.formatCellValue(cell).trim();
	}

	public static int getRowUsed(HSSFSheet sheet)
	{
		return sheet.getLastRowNum();
	}

	public static int getColumnUsed(HSSFSheet sheet,int row)
	{
		return sheet.getRow(row).getLastCellNum();
	}

	public static int getRowcontains(HSSFSheet sheet,int col,String value)
	{
		int rowcount=getRowUsed(sheet);
		int i=0;
		for( i=0;i<=rowcount;i++)
		{
			if(getCellData(sheet, i, col).equalsIgnoreCase(value))
			{
				return i;
			}
		}
		return i;
	}

	public static int getColumnContains(HSSFSheet sheet,int row,String value)
	{
		int lastcell=getColumnUsed(sheet,row);
		int i=0;
		for(i=0;i<lastcell;i++)
		{
			if(getCellData(sheet, row, i).equalsIgnoreCase(value))
				return i;
		}
		return i;
	}

	
	public static String[][] getTestData(String Tc_Name) throws FileNotFoundException, IOException
	{
		Properties GlobalProperty=new Properties();
		FileInputStream inload=new FileInputStream("config.properties");
		GlobalProperty.load(inload);
		

		 String	TestScript=GlobalProperty.getProperty("TestScript");
		 String	TestScript_Sheetname=GlobalProperty.getProperty("TestScript_Sheetname");
		 String	TestData=GlobalProperty.getProperty("TestData");
		 String	TestData_Sheetname=GlobalProperty.getProperty("TestData_Sheetname");
		 inload.close();
		 
		 FileInputStream in=new FileInputStream(TestScript);
		 HSSFWorkbook wb=new HSSFWorkbook(in);
		 HSSFSheet sheet=wb.getSheet(TestScript_Sheetname);
		 int rowTS=ExcelDataExtract.getRowcontains(sheet, 0, Tc_Name);
		 String datacell=ExcelDataExtract.getCellData(sheet, rowTS, 1);
		 datacell=datacell.replace("\n", "");
		 String DataList[]=datacell.split(",");
		 String TabArray[][]=new String[1][DataList.length];
		 in.close();
		 wb.close();
		 in=new FileInputStream(TestData);
		 wb=new HSSFWorkbook(in);
		 sheet=wb.getSheet(TestData_Sheetname);
		 int rowTD=ExcelDataExtract.getRowcontains(sheet, 0, Tc_Name);
		 for(int i=0;i<DataList.length;i++)
		 {
			 TabArray[0][i]=ExcelDataExtract.getCellData(sheet, rowTD, ExcelDataExtract.getColumnContains(sheet, 0, DataList[i]));
		 }
		 
		 in.close();
		 wb.close();
		 
		 
		
		return TabArray;
	}
	
}
