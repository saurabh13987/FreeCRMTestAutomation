package com.crm.qa.testproperties;

import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

import com.crm.qa.util.WindowsActions;

public class TestEvidenceProperties {

	// Properties File Variable
	private final String testEvidenceFolderPathProperties = "test.evidence.folder.path";
	private final String testEvidenceFolderNameProperties = "test.evidence.folder.name";

	private final String lineSeparator = "***********************************************************************";

	private static String testEvidenceFolderName;
	private static String testEvidenceFolderPath;

	private Logger logger = Logger.getLogger(this.getClass());

	public TestEvidenceProperties(String propsFileName) throws IOException {
		WindowsActions actions = new WindowsActions();
		logger.info("Reading Test Evidence Properties.....");
		Properties props = actions.readPropsFile(propsFileName);
		setTestEvidenceProperties(props);
	}

	public void setTestEvidenceProperties(Properties props) {
		testEvidenceFolderPath = props.getProperty(testEvidenceFolderPathProperties);
		testEvidenceFolderName = props.getProperty(testEvidenceFolderNameProperties);

		logger.info(lineSeparator);
		logger.info("Test Evidence Folder Name set as--->" + testEvidenceFolderName);
		logger.info("Test Evidence Folder Path set as--->" + testEvidenceFolderPath);
		logger.info(lineSeparator);
	}

	public String getTestEvidenceFolderName() {
		return testEvidenceFolderName;
	}

	public String getTestEvidenceFolderPath() {
		return testEvidenceFolderPath;
	}

	public String getTestEvidenceFolderFullPath() {
		return testEvidenceFolderPath + "\\" + testEvidenceFolderName;
	}

}
