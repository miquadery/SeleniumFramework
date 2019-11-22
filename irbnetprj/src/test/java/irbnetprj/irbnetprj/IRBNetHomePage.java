package irbnetprj.irbnetprj;

import static org.testng.AssertJUnit.assertTrue;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import pageObjects.IRBNetHomePageObjects;
import utility.Constant;
import utility.ExcelUtils;

public class IRBNetHomePage {

	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest LoginTest;

	private static WebDriver driver = null;

	@BeforeSuite
	public void setUp() {
		//start reporters
		htmlReporter = new ExtentHtmlReporter("IRBNet_ExtentReport_LoginTest.html");

		//create ExtentReports and attach reporters
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}


	@BeforeTest
	public void setupTest() {
		//Google Chrome setup
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}


	@Test
	public void IRBNet_Login_Test() throws Exception {

		//creates a toggle for the given test, adds all log events under it
		LoginTest = extent.createTest("IRBNet Login Test", "This is a test to validate IRBNet login functionality.");


		//Creating object class
		IRBNetHomePageObjects IRBNetHomePageObj = new IRBNetHomePageObjects(driver);

		LoginTest.log(Status.INFO,"Starting Test Case");
		
		driver.get(Constant.strURL);
		driver.manage().window().maximize();
		LoginTest.pass("Visiting IRBNet portal");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Sheet1");
		String sUserName = ExcelUtils.getCellData(1, 0);
		String sPassword = ExcelUtils.getCellData(1, 1);

		IRBNetHomePageObj.setLogin_Username(sUserName);
		LoginTest.pass("Inserting login Username");

		IRBNetHomePageObj.setLogin_Password(sPassword);
		LoginTest.pass("Inserting login Password");
		
		
		IRBNetHomePageObj.click_LoginButton();
		LoginTest.pass("Clicking on 'Login' button");
		
		String message = IRBNetHomePageObj.getWelcomeMsg();
		System.out.println("The string is "+message);
		Assert.assertTrue(message.contains("Welcome to IRBNet"));
		


		Thread.sleep(3000);
		LoginTest.pass("Waiting for some seconds");

		IRBNetHomePageObj.click_LogoutButton();
		LoginTest.pass("Clicking on 'Logout' button");

		Thread.sleep(3000);
		LoginTest.pass("Waiting for some seconds");
		
		//String message = IRBNetHomePageObj.getWelcomeMsg();
		//Assert.assertTrue(message.contains("Welcome to IRBNet"));
		//System.out.println("The string is "+message);
	}


	@AfterTest
	public void tearDownTest() {
		driver.close();
		LoginTest.pass("Closing the browser");

		LoginTest.info("Test Completed Successfully");
	}


	@AfterSuite
	public void tearDown() {
		//calling flush writes everything to the log file
		extent.flush();
	}

}