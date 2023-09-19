package utils;

public class XmlConfigReader {
	public static String executionJsonFile = null;
	public static String browserType = null;
	public static String browserStackExecutionFlag = null;
	
	public static String getbrowserStackExecutionFlag() {
		if (browserStackExecutionFlag!=null ) {
			return browserStackExecutionFlag;
		}
		else {
			throw new RuntimeException("invalid browserStackExecutionFlag in XML");
		}
	}
	
	public static String getExecutionJsonFile() {
		if (executionJsonFile!=null ) {
			return executionJsonFile;
		}
		else {
			throw new RuntimeException("invalid file name in XML");
		}
	}
	
	public static String getBrowserType() {
		if (browserType!=null) {
			return browserType;
		}
		else {
			throw new RuntimeException("invalid browser type selected in XML");
		}
	}
	
	public static void setbrowserStackExecutionFlag(String BStackExecutionFlag) {
		browserStackExecutionFlag = BStackExecutionFlag;
	}
	
	public static void setExecutionJsonFile(String jsonFile) {
		executionJsonFile = jsonFile;
	}
	
	public static void setBrowserType(String browser) {
		browserType = browser;
	}
}
