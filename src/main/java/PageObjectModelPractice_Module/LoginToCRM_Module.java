package PageObjectModelPractice_Module;

import java.util.LinkedHashMap;
import org.openqa.selenium.WebDriver;
import PageObjectModelPractice_PO.loginToCRM_PO;

public class LoginToCRM_Module {
	WebDriver driver;
	String extectedTittle ="abc";
	
	public LoginToCRM_Module(WebDriver driver){
		this.driver =driver;
	}
	
	public void loginFreeCrmModule() {
		loginToCRM_PO LoginPOObj = new loginToCRM_PO(driver);
		//LoginPOObj.insertValueInUsername();
		//LoginPOObj.insertValueInPassword();
		LoginPOObj.clikOnLoginButton();
	}
	
	public void tittleVerify(String extectedTittle) {
		this.extectedTittle = extectedTittle;
	    String actualTittle = driver.getTitle();
	    if(extectedTittle.equalsIgnoreCase(actualTittle))
	    	System.out.println("tittle matched");
	    else
	    	System.out.println("Incorect tittle");
	}
	
	public void receivingSheetData(LinkedHashMap<String,String> sheetData) {
		loginToCRM_PO LoginPOObj = new loginToCRM_PO(driver);
		LoginPOObj.insertValueInUsername(sheetData.get("UserName"));
		LoginPOObj.insertValueInPassword(sheetData.get("Password"));
		
		
	}

}
