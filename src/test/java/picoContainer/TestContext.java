package picoContainer;

import org.openqa.selenium.WebDriver;

import managerClass.CreateDriverManager;
import managerClass.PageObjectManager;

public class TestContext {
	private PageObjectManager _pageObjectManager;
	private WebDriver driver;
	
	public TestContext() {
		driver = getWebDriver();
		_pageObjectManager = new PageObjectManager(driver);
	}
	
	public PageObjectManager getPageObjectManager() {
		return _pageObjectManager;
	}
	
	public WebDriver getWebDriver() {
		return CreateDriverManager.getInstance().getWebDriver();
	}
	
	public void closeDriver() {
		CreateDriverManager.getInstance().tearDown();
	}
}
