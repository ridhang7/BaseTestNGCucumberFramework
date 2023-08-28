package pageObjs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePageObject {
	public HomePageObject(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	
}
