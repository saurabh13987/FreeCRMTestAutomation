package com.crm.qa.listener;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.SeleniumUtilities;
import com.relevantcodes.extentreports.LogStatus;

public class TestExecutionListener extends TestBase implements ITestListener {

	private Logger logger = Logger.getLogger(this.getClass());
	SeleniumUtilities seleniumUtils = new SeleniumUtilities();

	@Override
	public void onFinish(ITestContext result) {

	}

	@Override
	public void onStart(ITestContext result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.info("Test Case Failed : " + result.getMethod().getMethodName());
		try {
			String screenshot = seleniumUtils.takeScreenshot(TestBase.testEvidenceFolder + "executionFailure/"
					+ result.getMethod().getMethodName() + "_" + windowsActions.getTimeStamp() + ".png");
			extentTest.log(LogStatus.FAIL, "Captured Screenshot is : ");
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshot));
		} catch (IOException exception) {
			StringWriter exceptionLogs = new StringWriter();
			exception.printStackTrace(new PrintWriter(exceptionLogs));
			logger.error(exceptionLogs.toString());
		}
		logger.info("Test Case Execution Completed : " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.info("Test Case Execution Skipped : " + result.getMethod().getMethodName());
		logger.info("Test Case Execution Completed : " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("Test Case Execution Started : " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("Test Case Execution Passed : " + result.getMethod().getMethodName());
		logger.info("Test Case Execution Completed : " + result.getMethod().getMethodName());
	}

}
