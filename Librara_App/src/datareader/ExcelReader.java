package datareader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	
	private static Workbook wb;
	private static Sheet sh;
	private static Row row;
	private static Cell cell;
	private static FileInputStream fis;
	private static FileOutputStream fos;
	
public static void main(String[] args) throws Exception {
	
	fis = new FileInputStream("E:/Selenium/Automation/Librara_App/src/data.xlsx");
	wb = WorkbookFactory.create(fis);
	sh = wb.getSheet("Sheet1");
	int noOfRows = sh.getLastRowNum();
	System.out.println(noOfRows);
	
	for(int i=1;i<=noOfRows;i++)
	{
		System.out.println(sh.getRow(i).getCell(0));
		System.out.println(sh.getRow(i).getCell(1));
	}
	
	row = sh.createRow(1);
	cell = row.createCell(0);
	cell.setCellValue("QAV");
	System.out.println(cell.getStringCellValue());
	fos = new  FileOutputStream("E:/Selenium/Automation/Librara_App/src/data.xlsx");
	wb.write(fos);
	fos.flush();
	fos.close();
	System.out.println("done");
}

}
