package utils;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelUtils 
{
	/*
	 * Method Name : readExcel
	 * Description : Reads an Excel file
	 */
	public static String[] readExcel(String fPath,String sheetName,int rowNum,int noOfData)
	{
		String[] data=new String[10];
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		XSSFRow row;
		try
		{
			FileInputStream fis=new FileInputStream(fPath);
			workbook=new XSSFWorkbook(fis);
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rowNum);
			for(int i=0;i<noOfData;i++)
			{
				data[i]=String.valueOf(row.getCell(i));
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return data;
	}
	/*
	 * Methods Name : writeExcel
	 * Description : Writes to an Excel file
	 */
	public static void writeExcel(String fPath,String sheetName,int rowNum,int cellNum,String message)
	{
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		XSSFRow row;
		try
		{
			FileInputStream fis=new FileInputStream(fPath);
			workbook=new XSSFWorkbook(fis);
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rowNum);
			if(row==null)
				row=sheet.createRow(rowNum);
			XSSFCell cell=row.getCell(cellNum);
			if(cell==null)
				cell=row.createCell(cellNum);
			cell.setCellType(CellType.BLANK);
			cell.setCellValue(message);
			FileOutputStream fos=new FileOutputStream(fPath);
			workbook.write(fos);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}