package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import utils.ElementActions;
import utils.ExcelReader;
import utils.ExtentReportClass;
import utils.OtherActions;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties locators = new Properties();
	public static FileInputStream fileInputStream;
	public static ExcelReader excelReader = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public ExtentReports extentReports = ExtentReportClass.getExtentReport();
	public static ExtentTest extentTest;
	public static SoftAssert softAssert = new SoftAssert();
	public static ElementActions elementActions = new ElementActions();
	public static OtherActions otherActions = new OtherActions();
	public static WebDriverWait wait;

	@BeforeSuite
	public void setUp() throws IOException {
		if (driver == null) {
			fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			config.load(fileInputStream);

			fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Locators.properties");
			locators.load(fileInputStream);
		}

		switch (config.getProperty("browser")) {
			case "chrome": {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			}
			case "firefox": {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			}
			case "opera": {
				System.setProperty("webdriver.opera.driver",System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\operadriver.exe");
				driver = new OperaDriver();
				break;
			}
		}

		driver.get(config.getProperty("pageUrl") + "/login.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")),
				TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
	}

	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
