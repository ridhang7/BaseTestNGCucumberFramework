package managerClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateDriverManager {
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
        String browserDriverType = "Local";
        switch (browserDriverType) {
            case "Local":
                return createLocalDriver();
            default:
                throw new IllegalArgumentException("Invalid browser driver type");
        }
    }

    private WebDriver createLocalDriver() {
    	WebDriver driver;
    	String browserDriver = "Chrome";
		switch (browserDriver) {
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\ridha\\eclipse-workspace\\paraBankTestngFramework\\localBrowserDriver\\chromedriver-win32\\chromedriver.exe");
			driver = new ChromeDriver();
			setDriver(driver);
		return driver;	
		
		default:
		return null;	
    }
		
    }
}