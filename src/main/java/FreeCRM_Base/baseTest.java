package FreeCRM_Base;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.ExcelReadAndWrite;

public class baseTest {

	public WebDriver driver;
	static ConfigFileReader ConfigFileReaderObj;
	static String projPath;
	static ExcelReadAndWrite exceObj;
	static String currentDateTime;
	static String  reportingDirectory;
	
	static {
		// fetch current date and time from calendar
		Calendar calendar= Calendar.getInstance();
		System.out.println(calendar.getTime());
		SimpleDateFormat formater= new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		currentDateTime=formater.format(calendar.getTime());
		System.out.println(currentDateTime);

		projPath = System.getProperty("user.dir");
		ConfigFileReaderObj = new ConfigFileReader(projPath);
		ConfigFileReaderObj.loadPropertiesFile();
		String fileName = ConfigFileReaderObj.getInputdataFile();
		exceObj=new ExcelReadAndWrite();
		exceObj.ReadDataFromExcel(projPath, fileName);
		
		reportingDirectory=CreateDirectoryStructure.getReportingDirectory(ConfigFileReaderObj.getApplicationName(), projPath, currentDateTime);
		ExtentManager.createInstance(reportingDirectory+"POMHTMLReport.html", ConfigFileReaderObj.getApplicationName(), ConfigFileReaderObj.getEnvironmentDetails());
	}

	@BeforeTest
	public void invokeBrowser() {
		String driverPath = projPath + "/Drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.navigate().to(ConfigFileReaderObj.getApplicationURL());
	}


	@DataProvider(name = "inputDataMethode")
	public static Object[][] data(Method m){
		String sheetName=m.getAnnotation(Test.class).description().split("=")[1];
		LinkedHashMap<String, LinkedHashMap<String, String>> SheetValue = exceObj.getValueBySheetName(sheetName);

		System.out.println("SheetValue "+SheetValue);
		System.out.println("sheetName"+sheetName);

		Object[][] data = new Object[SheetValue.size()][2];  //initialize the array dimension

		Set<String> keyset = SheetValue.keySet();
		Iterator<String> key = keyset.iterator();

		int counter =0;
		while(key.hasNext()){
			String  TC_ID= key.next();
			data[counter][0]=TC_ID;
			data[counter][1]=SheetValue.get(TC_ID);
			counter++;
		}
		return data;

		/*return new Object[][] {
		{"hello","joshi"},
		{"hello","Bhoraskar"}};*/
	}
}
