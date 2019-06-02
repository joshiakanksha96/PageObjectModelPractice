package PageObjectModelPractice_TestScenarios;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import FreeCRM_Base.baseTest;
import PageObjectModelPractice_Module.HomePafeCRM_ModulePF;
import PageObjectModelPractice_PO.loginToCRM_PO;

public class FreeCRM_homeTest extends baseTest {

	@BeforeMethod 
	public void CRMpreCondition() {
		driver.get("https://classic.crmpro.com/login.cfm");
		loginToCRM_PO LoginPOObj = new loginToCRM_PO(driver);
		//LoginPOObj.insertValueInUsername();
		//LoginPOObj.insertValueInPassword();
		LoginPOObj.clikOnLoginButton();
	}


	@Test(priority=1)
	public void companyTest() {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HomePafeCRM_ModulePF HomePageModulePFObj = new HomePafeCRM_ModulePF(driver);
		HomePageModulePFObj.complaniesHomePage_ModulePF();
	}	

	
}
