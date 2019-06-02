package PageObjectModelPractice_PO;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

import FreeCRM_Base.basepageObject;

public class homePage_CRM_PageFactory extends basepageObject {
	WebDriver driver;
	Actions action;

	@FindBy(xpath = "//a[@title='Companies']")
	WebElement companies;

	@FindBy(how = How.XPATH, using = "//a[@title='New Company']")
	WebElement newCompany;

	@FindAll(@FindBy(how = How.XPATH, using = "//ul[@class='mlddm']/child::li/following::ul[2]"))
	List<WebElement> newCompanyOptions;

	@FindBys(@FindBy(how = How.XPATH, using = "//ul[@class='mlddm']/child::li/following::ul[3]"))
	List<WebElement> contactsOptions;

	@FindBy(xpath = "//a[@onclick='quickCreatePanel();']")
	WebElement QuickCreateButton;

	@FindBy(xpath = "//div[@id='ibox_content']")
	WebElement QuickCreatePopUpHeading;

	@FindBy(xpath = "//input[@name='company_name']")
	WebElement QuickCreate_CompanyName;

	@FindBy(xpath = "//input[@name='contact_first_name']")
	WebElement quickCreate_FirstName;

	@FindBy(xpath = "//input[@name='contact_surname']")
	WebElement quickCreate_LastName;

	@FindBy(xpath = "//input[@value='Create']")
	WebElement quickCreate_CreateButton;
	
	@FindBy(xpath="//input[@value='Close']")
	WebElement quickCreate_CloseButton;

	@FindAll(@FindBy(how = How.XPATH, using = "//a[@context='company']"))
	List<WebElement> ListOfComplanies_Company;

	public homePage_CRM_PageFactory(WebDriver driver) {
		super(driver);
		this.driver = driver;
		action = new Actions(driver);
	}

	public void loginToCRM() {
		driver = new ChromeDriver();
		driver.navigate().to("https://classic.crmpro.com/login.cfm");
	}

	public void clickOnCompanies() {
		companies.click();
	}

	public void mouseHoverOnCompanies() {
		action.moveToElement(companies);
	}

	public void getCompaniesOptions() {
		action.moveToElement(companies);
		for (int i = 0; i < newCompanyOptions.size(); i++) {
			System.out.println(newCompanyOptions.get(i).getText());
		}
	}

	public void ClickOnQuickCreate() {
		QuickCreateButton.click();
	}

	public void verifyQuickCreateDisplayed() {
		verifyElementDisplayed(QuickCreatePopUpHeading);
	}

	public void insertValueOnQuickCreate() {
		String CompanyName = "ABC";
		String C_FirstName = "ABC_FName";
		String C_LName = "ABC+Lname";
		typeOnElement(QuickCreate_CompanyName, CompanyName);
		typeOnElement(quickCreate_FirstName, C_FirstName);
		typeOnElement(quickCreate_LastName, C_LName);
		clickOnElement(quickCreate_CreateButton);
		clickOnElement(quickCreate_CloseButton);
		clickOnElement(companies);
	}

	/*
	 * public void CompanyNameInsertVarify(String CompanyName) { companies.click();
	 * for (int i = 0; i < ListOfComplanies_Company.size(); i++) {
	 * ListOfComplanies_Company.get(i).getText().contains(CompanyName);
	 * 
	 * }
	 * 
	 * }
	 */
}
