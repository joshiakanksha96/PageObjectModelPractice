package FreeCRM_Base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigFileReader {
	String sourceFolder;
	Properties pro;

	public ConfigFileReader(String sourceFolder) {
		this.sourceFolder=sourceFolder;
	}

	public void loadPropertiesFile() {
		try {

			File propertiesFileObj = new File(sourceFolder + "/globalConfig/config.properties");
			FileInputStream fls = new FileInputStream(propertiesFileObj);
			pro = new Properties();
			pro.load(fls);
			fls.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("config file not found at " + sourceFolder + "/GlobalConfig/config.properties");
		}
	}

	@SuppressWarnings("unused")
	public String getApplicationName() {
		String appName = pro.getProperty("applicationName");
		if (appName != null && (appName.isEmpty() != true)) {
			return appName;
		} else {
			throw new RuntimeException("appname is not specified");
		}
	}
	
	@SuppressWarnings("unused")
	public String getEnvironmentDetails() {
		String envDetail = pro.getProperty("environment");
		if (envDetail != null && (envDetail.isEmpty()!=true)) {
			return envDetail;
		} else {
			throw new RuntimeException("envDetail is not specified");
		}
	}
	
	@SuppressWarnings("unused")
	public String getApplicationURL() {
		String appURL = pro.getProperty("applicationURL");
		if (appURL != null && appURL.isEmpty()!=true) {
			return appURL;
		} else {
			throw new RuntimeException("appURL is not specified");
		}
	}
	
	@SuppressWarnings("unused")
	public String getInputdataFile() {
		String inputDataile = pro.getProperty("inputDataFile");
		if (inputDataile != null && inputDataile.isEmpty()!=true) {
			return inputDataile;
		} else {
			throw new RuntimeException("appURL is not specified");
		}
	}

}
