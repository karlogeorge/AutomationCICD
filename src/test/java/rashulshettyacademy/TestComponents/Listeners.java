package rashulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	// ITestListener - is a interface provided by TestNG
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); //Thread safe
	//If we do not use ThreadLoacal, submitOrder will overright ErrorValidation test whe  running in parallel 
	

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		//Whenever it is set in trheadLocal, for this object it will set a unique thread ID
		//It creates one map 
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test PAssed");
	}

	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			//Field are only associated with class level and not Method level
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Screenshot, attach to report
		String filePath = null;
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
