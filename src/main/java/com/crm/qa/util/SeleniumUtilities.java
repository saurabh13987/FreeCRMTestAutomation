package com.crm.qa.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.crm.qa.base.TestBase;

public class SeleniumUtilities extends TestBase {

	private Logger logger = Logger.getLogger(this.getClass());

	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
	}

	public void takeScreenshotAtEndOfTest() throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(srcFile, new File(currentDir + "\\screenshots\\" + System.currentTimeMillis() + ".png"));
		logger.info("Screenshot captured successfully -->" + currentDir + "\\screenshots\\" + System.currentTimeMillis()
				+ ".png");
	}

	public void takeScreenshotAtEndOfTest(String destinationFile) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(destinationFile));
		logger.info("Screenshot captured successfully at-->" + destinationFile);
	}

}
