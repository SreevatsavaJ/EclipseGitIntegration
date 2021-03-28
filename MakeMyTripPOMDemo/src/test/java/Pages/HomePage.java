package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.ExtentTest;

import Utils.CommonUtils;

public class HomePage extends CommonUtils{
	WebDriver driver;
	private By lnkFlights = By.xpath("(//span[contains(@class,'Flights')])[1]");
	private By txt_fromCity = By.id("fromCity");
	private By tfld_fromCity = By.xpath("//input[@placeholder='From']");
	private By txt_toCity = By.id("toCity");
	private By tfld_toCity = By.xpath("//input[@placeholder='To']");
	private By btnSearch = By.xpath("//a[text()='Search']");
	private By clickCity(String city) {return By.xpath("//p[text()='"+city+"']");}
	private By FromdatePicker = By.xpath("//div[@aria-selected='true']");
	private By lblPrice = By.xpath("//span[text()='Price']");
	public HomePage(WebDriver driver,ExtentTest test) {
		super(driver,test);
	}
	
	public void isPageLoaded(){
		this.waitUntilTitleContains("MakeMyTrip");
		
	}
	public void selectFlights(){
		this.doClick(lnkFlights, "Clicking Flights link");
	}
	public void selectBuses(){
		this.doClick(lnkFlights, "Clicking Buses link");
	}
	
	public void enterFromCity(String fromCity) throws InterruptedException{
		this.doClick(txt_fromCity, "Selecting the From City Input");
		Thread.sleep(3000);
		this.doEnterText(tfld_fromCity, fromCity, "Entering From City: "+fromCity);
		Thread.sleep(2000);
		this.doClick(this.clickCity(fromCity), "Selecting From City");
	}
	
	public void enterToCity(String toCity) throws InterruptedException{
		//this.doClick(txt_toCity, "Selecting the To City Input");
		Thread.sleep(3000);
		this.doEnterText(tfld_toCity, toCity, "Entering To City: "+toCity);
		Thread.sleep(2000);
		this.doClick(this.clickCity(toCity), "Selecting To City");
	}
	
	public void selectDepartureDate(){
		this.doClick(FromdatePicker, "Selecting Travel Date");
	}

	public void clickSearch(){
		this.doClick(btnSearch, "Clicking Search Button");
		this.waitUntilElementLocated(lblPrice, "Validating if Price is present");
	}
	
	public boolean CheckFlights(String fromCity, String toCity) throws InterruptedException{
		this.isPageLoaded();
		this.selectFlights();
		this.enterFromCity(fromCity);
		this.enterToCity(toCity);
		this.selectDepartureDate();	
		this.clickSearch();
		return this.driver.findElement(lblPrice).isDisplayed();
	}
	
	public boolean CheckBuses(String fromCity, String toCity) throws InterruptedException{
		this.isPageLoaded();
		this.selectBuses();
		this.enterFromCity(fromCity);
		this.enterToCity(toCity);
		this.selectDepartureDate();	
		this.clickSearch();
		return this.driver.findElement(lblPrice).isDisplayed();
	}
}
