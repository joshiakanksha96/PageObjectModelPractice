package PageObjectModelPractice_Module;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import PageObjectModelPractice_PO.homePage_CRM_PageFactory;

public class HomePafeCRM_ModulePF {
	WebDriver driver;
	
	
	public HomePafeCRM_ModulePF(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void complaniesHomePage_ModulePF() {
		homePage_CRM_PageFactory pageFactObj = PageFactory.initElements(driver, homePage_CRM_PageFactory.class);
		driver.switchTo().frame("mainpanel");
		pageFactObj.getCompaniesOptions();
		pageFactObj.clickOnCompanies();
		pageFactObj.ClickOnQuickCreate();
		pageFactObj.verifyQuickCreateDisplayed();
		pageFactObj.insertValueOnQuickCreate();
	}
}
