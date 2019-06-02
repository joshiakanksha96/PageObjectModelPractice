package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadAndWrite {
	LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>> UniqueKeySheetData;

	public void ReadDataFromExcel(String projPath, String fileName) {
		String excelFileName=fileName;
		
		String TestDataFilePath = projPath+"/TestData/"+excelFileName;
		File f = new File(TestDataFilePath);
		try {
			Workbook workbook = null;
			Sheet workSheet;
			Cell cell;
			FileInputStream fis = new FileInputStream(f);
			UniqueKeySheetData = new LinkedHashMap<String, LinkedHashMap<String, LinkedHashMap<String, String>>>();			
			if(TestDataFilePath.contains(".xlsx"))
			{
				workbook = new XSSFWorkbook(fis);
			}
			else if (TestDataFilePath.contains(".xls")) 
			{
				workbook = new HSSFWorkbook(fis);
			}

			/**Get number of sheets present in your code**/
			//System.out.println(workbook.getNumberOfSheets());

			/**Get name of  All sheets present in the workbooks**/
			for(int i =0;i<workbook.getNumberOfSheets();i++)
			{
				LinkedHashMap<String, LinkedHashMap<String, String>> UniqueKeyAndColValue = new LinkedHashMap<String, LinkedHashMap<String, String>>();
				String sheetName=workbook.getSheetAt(i).getSheetName();
				workSheet = workbook.getSheetAt(i);
				//System.out.println(workbook.getSheetAt(i).getSheetName());
				//System.out.println(workbook.getSheetName(i));
				Row Headerrow = workSheet.getRow(0);
				int executionFlagcellNumber = 0;
				for(int k=0;k<Headerrow.getPhysicalNumberOfCells();k++)
				{
					cell = Headerrow.getCell(k);
					if(cell.getStringCellValue().equalsIgnoreCase("ExecutionFlag"))
					{
						executionFlagcellNumber=k;
						break;
					}
				}
				int ActiveNumOfRow = workSheet.getPhysicalNumberOfRows();
				for(int j =1;j<ActiveNumOfRow;j++)
				{
					LinkedHashMap<String, String> ColumnAndValue = new LinkedHashMap<String, String>();
					Row row = workSheet.getRow(j);
					//System.out.println(row.getPhysicalNumberOfCells() + "  getPhysicalNumberOfCells");
					Cell executionflagObj=row.getCell(executionFlagcellNumber);
					if(executionflagObj.toString().equalsIgnoreCase("Y")) {
						int cellcount=row.getPhysicalNumberOfCells();
						for(int l=0;l<cellcount;l++) {
							/*System.out.println(Headerrow.getCell(l).toString());;
								System.out.println(row.getCell(l).toString());
								System.out.println("");*/
							ColumnAndValue.put(Headerrow.getCell(l).toString(), row.getCell(l).toString());
							String TC_ID=row.getCell(0).toString();
							//UniqueKeyAndColValue.put(Integer.toString(j), ColumnAndValue);
							UniqueKeyAndColValue.put(TC_ID, ColumnAndValue);
							
						}
						//System.out.println(ColumnAndValue);
					}
					UniqueKeySheetData.put(sheetName, UniqueKeyAndColValue);
					//System.out.println(UniqueKeySheetData);
				}
				//System.out.println(UniqueKeyAndColValue);
				//UniqueKeySheetData.put(sheetName, UniqueKeyAndColValue);		
			}
			//System.out.println(UniqueKeySheetData);
		} 

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public LinkedHashMap<String, LinkedHashMap<String, String>> getValueBySheetName(String sheetName) {
		return UniqueKeySheetData.get(sheetName);

	}

	public LinkedHashMap<String, String> getValueByTestCaseName(String tcid,String sheetName ) {
		return UniqueKeySheetData.get(sheetName).get(tcid);
	}

	public String getValueByColumnName(String tcid,String sheetName, String colName) {
		return UniqueKeySheetData.get(sheetName).get(tcid).get(colName);

	}
}
