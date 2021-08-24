package com.crm.qa.testproperties;

import java.io.IOException;
import java.util.HashMap;

public class TestProperties {

	private BrowserProperties browserProperties;

	public void setBrowserprops(String propsFileName, HashMap<String, String> browserParams) throws IOException {
		browserProperties = new BrowserProperties(propsFileName, browserParams);
	}

	public BrowserProperties getBrowserProps() {
		return browserProperties;
	}
}
