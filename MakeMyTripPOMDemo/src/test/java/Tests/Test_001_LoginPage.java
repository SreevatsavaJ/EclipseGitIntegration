package Tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Pages.HomePage;
import Pages.TestNGBeforeMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Test_001_LoginPage extends TestNGBeforeMethod{
	HomePage hm;
	@BeforeMethod
	public void beforeMethod() throws FileNotFoundException, IOException {
		testConfigSetUp();
		extentReportSetUp();
		//testDataSetUp(this.getClass().getSimpleName());
	}

	@DataProvider(name="TestDataSetUp")
	public String[][] getData() throws FileNotFoundException, IOException{
		return testDataSetUp(this.getClass().getSimpleName());
	}

	@SuppressWarnings("deprecation")
	@Test(dataProvider="TestDataSetUp")
	public void testLoginPage(String fromCity, String toCity) {
		try{
			System.out.println(prop.getProperty("browser"));
			launchBrowser();
			test.info("Class Name: "+ this.getClass().getSimpleName());
			driver.manage().window().maximize();
			driver.get("https://www.makemytrip.com");
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			Actions actions = new Actions(driver);
			actions.moveByOffset(100, 100).click().build().perform();
			test.log(Status.INFO, "Click performed successfully");
			//driver.navigate().refresh();
			hm = new HomePage(driver,test);
			Assert.assertTrue(hm.CheckFlights(fromCity,toCity));
			test.pass("Checking Flights");
		}
		catch(Exception e){
			test.fail("Failed to execute Script: "+e.getMessage());
		}
	}
	
	@Test
	public void testLoginPage2() {
		try{
			driver.findElement(By.xpath("//img[contains(@alt,'LOGO')]")).click();
			Thread.sleep(5000);
			//hm.CheckBuses("Mumbai Darshan, Maharashtra","Hyderabad, Telangana");
			Assert.assertTrue(hm.CheckBuses("Mumbai Darshan, Maharashtra","Hyderabad, Telangana"));
			test.pass("Checking Buses");
		}
		catch(Exception e){
			test.fail("Failed to execute Script: "+e.getMessage());
		}
	}
	

	@AfterMethod
	public void afterMethod() {
		report.flush();
	}

}
