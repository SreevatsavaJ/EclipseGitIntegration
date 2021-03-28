package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class CommonUtils {
	WebDriver driver;
	ExtentTest test;
	WebDriverWait wait;
	@SuppressWarnings("deprecation")
	public CommonUtils(WebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;
		wait = new WebDriverWait(driver,20);
	}
	public void doScrollToView(By locator){
		Actions actions = new Actions(driver);
		actions.moveToElement(this.driver.findElement(locator)).build().perform();
	}
	public void doClick(By locator,String message){
		doScrollToView(locator);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
		test.info(message);
	}
	public void waitUntilElementLocated(By locator,String message){
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		test.info(message);
	}
	public void doEnterText(By locator, String text, String message){
		this.doScrollToView(locator);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
		test.info(message);
	}
	public void waitUntilTitleContains(String title){
		wait.until(ExpectedConditions.titleContains(title));
	}
}
