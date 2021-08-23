package com.crm.qa.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.Logger;

public class WindowsActions {

	private Logger logger = Logger.getLogger(this.getClass());

	public Properties readPropsFile(String propsFileName) throws IOException {
		FileReader reader;
		Properties props = new Properties();
		reader = new FileReader(System.getProperty("user.dir") + propsFileName);
		props.load(reader);
		logger.info("Properties File read successfully");
		return props;
	}

	public void isFolderPresent(String folderName) {
		if (!new File(folderName).isDirectory()) {
			new File(folderName).mkdir();
			logger.info(folderName + "-->Folder created successfully");
		} else {
			logger.info(folderName + "--->Folder already exists");
		}
	}

	public String getTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(new Date());
	}
}
