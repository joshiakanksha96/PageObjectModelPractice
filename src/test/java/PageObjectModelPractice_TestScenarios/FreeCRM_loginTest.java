package PageObjectModelPractice_TestScenarios;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.Test;
import FreeCRM_Base.baseTest;
import PageObjectModelPractice_Module.LoginToCRM_Module;

//import PageObjectModelPractice_Module.LoginToCRM_Module;
//import PageObjectModelPractice_Module.LoginToCRM_Module;

public class FreeCRM_loginTest extends baseTest {

	@Test(dataProvider="inputDataMethode",description="sheetName=c")
	public void loginToFreeCRM(String TC_ID,LinkedHashMap<String,String> sheetData) {
		driver.get("https://classic.crmpro.com/login.cfm");
		LoginToCRM_Module LoginModuleObj = new LoginToCRM_Module(driver);
		//LoginModuleObj.loginFreeCrmModule();
		LoginModuleObj.receivingSheetData(sheetData);
	}
}
