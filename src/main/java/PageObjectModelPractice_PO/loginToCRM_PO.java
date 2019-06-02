package PageObjectModelPractice_PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import FreeCRM_Base.basepageObject;

public class loginToCRM_PO extends basepageObject {

	WebDriver driver;

	By username = By.xpath("//input[@name='username']");
	By password = By.xpath("//input[@name='password']");
	By loginButton = By.xpath("//input[@class='btn btn-small']");

	public loginToCRM_PO(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	

	public void insertValueInUsername(String UserName) {
		driver.findElement(username).clear();
		//driver.findElement(username).sendKeys("himansja");
		//driver.findElement(username);
		typeOnElement(username,UserName);
	}

	public void insertValueInPassword(String Password) {
		driver.findElement(password).clear();
		//driver.findElement(password).sendKeys("ispl123#");
		typeOnElement(password, Password);
	}

	public void clikOnLoginButton() {
		driver.findElement(loginButton).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
