package com.crm.qa.testproperties;

import java.io.IOException;
import java.util.HashMap;

public class TestProperties {

	private BrowserProperties browserProperties;
	private TestEvidenceProperties testEvidenceProperties;

	public void setBrowserprops(String propsFileName, HashMap<String, String> browserParams) throws IOException {
		browserProperties = new BrowserProperties(propsFileName, browserParams);
	}

	public BrowserProperties getBrowserProps() {
		return browserProperties;
	}

	public void setTestEvidenceProps(String propsFileName) throws IOException {
		testEvidenceProperties = new TestEvidenceProperties(propsFileName);
	}

	public TestEvidenceProperties getTestEvidenceProps() {
		return testEvidenceProperties;
	}

}
