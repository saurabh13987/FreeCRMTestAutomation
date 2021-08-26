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

	public void takeScreenshot(String filename) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(srcFile, new File(currentDir + filename));
		logger.info("Screenshot captured successfully -->" + currentDir + filename);
	}

}
