package managerClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import helper.BrowerStackConfig;
import utils.XmlConfigReader;

public class CreateDriverManager {
	public static WebDriver driver;
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    private static final CreateDriverManager instance = new CreateDriverManager();

    private CreateDriverManager() {
        // Private constructor to enforce Singleton pattern
    }
    public static CreateDriverManager getInstance() {
        return instance;
    }

    public WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            threadLocalDriver.remove();
        }
    }

    public WebDriver getWebDriver() {
        WebDriver driver = getDriver();
        if (driver == null) {
            driver = createDriver();
            System.out.println("WebDriver created: " + threadLocalDriver.get());
        }
        return driver;
    }
    
    
    private WebDriver createDriver() {
        // browserDriverType can be Local or Remote
        String browserExecutionType = XmlConfigReader.getbrowserStackExecutionFlag();
        switch (browserExecutionType) {
            case "No":
                return createLocalDriver();
            case "Yes":
			try {
				return createBrowserStackDriver();
			} catch (Exception e) {
				e.printStackTrace();
			}
            default:
                throw new IllegalArgumentException("Invalid browser driver type");
        }
    }

    private WebDriver createLocalDriver() {
    	WebDriver driver;
    	String browserDriver = XmlConfigReader.getBrowserType();
		switch (browserDriver) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\ridha\\eclipse-workspace\\TestNGCucumberFramework\\localBrowserDriver\\chromedriver-win32\\chromedriver.exe");
			driver = new ChromeDriver();
			setDriver(driver);
		return driver;	
		default:
		return null;	
    }
	
	
	}
    @SuppressWarnings("static-access")
	private WebDriver createBrowserStackDriver() {
		
    	try {
			driver = BrowerStackConfig.setUp(XmlConfigReader.getExecutionJsonFile(), XmlConfigReader.getBrowserType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		CreateDriverManager.getInstance().setDriver(driver);
    	return driver;
		
    }
}