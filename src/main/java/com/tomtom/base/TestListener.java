package com.tomtom.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

	private WebDriver driver;
	static final Logger logger = LogManager.getLogger(TestListener.class);

	public void onStart(ITestContext context) {
		logger.info("*** Test Suite " + context.getName() + " started ***");
	}

	public void onFinish(ITestContext context) {
		logger.info(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		logger.info(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		logger.info("*** Executed " + result.getMethod().getMethodName() + " test successfully...\n\n");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {
		logger.info("*** Test execution of " + result.getMethod().getMethodName() + " test is failed...");
		
		ExtentTestManager.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " test is failed");
		
		this.driver = ((TestBase)result.getInstance()).getDriver();
		String targetLocation = null;

		String testClassName = result.getTestClass().getName();
		Date d = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss_a");
		String timeStamp = sd.format(d);
		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + timeStamp + ".png";
		String fileSeperator = System.getProperty("file.separator");
		String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
				+ "screenshots";
		logger.info("Screen shots reports path - " + reportsPath);
		try {
			File file = new File(reportsPath + fileSeperator + testClassName); // Set
																				// screenshots
																				// folder
			if (!file.exists()) {
				if (file.mkdirs()) {
					logger.info("Directory: " + file.getAbsolutePath() + " is created!");
				} else {
					logger.info("Failed to create directory: " + file.getAbsolutePath());
				}

			}

			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			targetLocation = reportsPath + fileSeperator + testClassName + fileSeperator + screenShotName;// define
																											// location
			File targetFile = new File(targetLocation);
			logger.info("Screen shot file location - " + screenshotFile.getAbsolutePath());
			logger.info("Target File location - " + targetFile.getAbsolutePath()+ "\n\n");
			FileHandler.copy(screenshotFile, targetFile);

		} catch (FileNotFoundException e) {
			logger.info("File not found exception occurred while taking screenshot " + e.getMessage());
		} catch (Exception e) {
			logger.info("An exception occurred while taking screenshot " + e.getStackTrace());
		}

		// attach screenshots to report
		try {
			ExtentTestManager.getTest().fail(screenShotName,
					MediaEntityBuilder.createScreenCaptureFromPath(targetLocation).build());
		} catch (IOException e) {
			logger.info("An exception occured while taking screenshot " + e.getCause());
		}
		ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		logger.info("*** Test " + result.getMethod().getMethodName() + " skipped...\n\n");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

}
