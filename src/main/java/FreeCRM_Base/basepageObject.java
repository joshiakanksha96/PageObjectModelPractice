package FreeCRM_Base;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

public class basepageObject {
	WebDriver driver;
	WebElement elm;

	public basepageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnElement(By locator) {
		elm = findElementsafely(locator);
		elm.click();
	}
	
	public void clickOnElement(WebElement webElement) {
		elm = findElementsafely(webElement);
		elm.click();
	}

	public void typeOnElement(By locator, String value) {
		elm = findElementsafely(locator);
		elm.sendKeys(value);
	}
	
	public void typeOnElement(WebElement webElement, String value) {
		elm = findElementsafely(webElement);
		elm.sendKeys(value);
	}

	public void getElementText(By locator) {
		elm = findElementsafely(locator);
		elm.getText();
	}

	public void verifyElementDisplayed(By locator) {
		elm = findElementsafely(locator);
		elm.isDisplayed();
	}

	public void verifyElementDisplayed(WebElement webElement) {
		elm = findElementsafely(webElement);
		elm.isDisplayed();
	}

	public WebElement findElementsafely(final By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(180, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(StaleElementReferenceException.class, NoSuchElementException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return foo;
	}

	public WebElement findElementsafely(final WebElement element) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(180, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(StaleElementReferenceException.class, NoSuchElementException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return element;
			}
		});
		return foo;
	}
}
