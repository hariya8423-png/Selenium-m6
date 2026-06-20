package Com.DemoWebShop_GenericUtility;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import Com.DemoWebShop_POM.Home_Page;
import Com.DemoWebShop_POM.Login_Page;
import Com.DemoWebShop_POM.Welcome_Page;

public class BaseTest {
	public WebDriver driver;
	public static WebDriver sDriver;
	public ExtentSparkReporter spark;
	public ExtentReports reports;
	public ExtentTest test;
	public FileUtility fileutility=new FileUtility();
	public Welcome_Page welcomepPage;
	public Login_Page loginpPage;
	public Home_Page homepPage;
	public WebDriverUtility webDriverUtility=new WebDriverUtility();
	public WebDriverWait wait;
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Before Suite-------Database Connection");
	}
	@BeforeTest
	public void beforeTest() {
		System.out.println("Before Test-------Report Generation");
		
		spark=new ExtentSparkReporter(FrameWorkConstants.extentReportPath);
		reports=new ExtentReports();
		reports.attachReporter(spark);
		test=reports.createTest("DemoWebShop Address Module");
	}
	@BeforeClass
	public void beforeClass() throws InterruptedException, IOException {
		System.out.println("Before Class-------Browser Setup");	String browser = fileutility.readDataFromPropertyFile("browserName");
	String url = fileutility.readDataFromPropertyFile("url");
		
//		String browser = System.getProperty("browserName");
//		String url = System.getProperty("baseUrl");
		
		if(browser.equalsIgnoreCase("chrome")) {
			System.out.println("chrome browser");
		    driver = new ChromeDriver();
		    
			}else if(browser.equalsIgnoreCase("edge")) {
				System.out.println("edge browser");
			    driver = new EdgeDriver();
			    
			}else if(browser.equalsIgnoreCase("firefox")) {
				System.out.println("firefox browser");
			    driver = new FirefoxDriver();
			}else {
				System.out.println("invalid browser");
			}
		wait= new WebDriverWait(driver, Duration.ofSeconds(15));
		sDriver= driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(url);
		System.out.println("logout sucessfull");		    
	}
	@BeforeMethod
	public void beforeMethod() throws IOException {
		System.out.println("Before Method-------Login to Application");
		
		welcomepPage = new Welcome_Page(driver);
		welcomepPage.getLoginLink().click();
		
		loginpPage=new Login_Page(driver);
		loginpPage.getEmailTextField().sendKeys(fileutility.readDataFromPropertyFile("username"));
		loginpPage.getPasswordTextField().sendKeys(fileutility.readDataFromPropertyFile("password"));
		loginpPage.getLoginButton().click();
		homepPage=new Home_Page(driver);
		
	}
	@AfterMethod
	public void afterMethod() throws InterruptedException {
		System.out.println("After Method-------Logout from Application");
		Thread.sleep(3000);
		homepPage.getLogoutLink().click();
	}
	@AfterClass
	public void afterClass() throws InterruptedException {
		System.out.println("After Class-------Browser Setup");
		Thread.sleep(3000);
		driver.quit();
	}
	@AfterTest
	public void afterTest() {
		System.out.println("After Test------Generate Test Report");
		reports.flush();
	}
	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite-------Database Connection");
	
	}
}


