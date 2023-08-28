package managerClass;

import org.openqa.selenium.WebDriver;

import pageObjs.HomePageObject;

public class PageObjectManager {
	
	private WebDriver driver;
	private HomePageObject homePageObject;
	
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	public HomePageObject getHomePage(){
		return (homePageObject == null) ? homePageObject = new HomePageObject(driver) : homePageObject;
	}

}
