package Pages;

import static Pages.TestNGBeforeMethod.path;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestNGBeforeMethod {
	protected static Properties prop;
	static String path;
	protected static ExtentTest test;
	protected static ExtentReports report;
	public static WebDriver driver;
	private String excelWorkbook="/src/test/java/TestData/TestData.xlsx";
	
	public static void testConfigSetUp(){
		path = System.getProperty("user.dir");
		System.out.println("Path: "+path);
		prop = new Properties();
		try {
			FileInputStream file = new FileInputStream(path+"/src/test/java/TestData/TestConfig.properties");
			prop.load(file);
		} catch (Exception e) {
			System.out.println("Error is: "+e.getMessage());
		}
	}
	
	public static void extentReportSetUp(){
		ExtentSparkReporter htmlReport = new ExtentSparkReporter(path+"/Results/HTMLReports");
		report = new ExtentReports();
		report.attachReporter(htmlReport);
		test = report.createTest("Testing Make My trip site");
	}
	
	public void launchBrowser(){
		if(getConfigProperty("browser").equalsIgnoreCase("chrome")){
			driver = new ChromeDriver();
			test.info("Launched Chrome Driver");
		}
	}
	
	public String[][] testDataSetUp(String sheetName) throws FileNotFoundException, IOException{
		String data[][];
		path = System.getProperty("user.dir");
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(path+excelWorkbook));
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		data = new String[rowCount][2];
		for(int i=0;i<rowCount;i++){
			int colCount = sheet.getRow(i).getPhysicalNumberOfCells();
			//data = new String[i][colCount];
			for(int j=0;j<colCount;j++){
				data[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return data;
	}
	
	public static String getConfigProperty(String propName){
		return prop.getProperty(propName);
	}
	
}
